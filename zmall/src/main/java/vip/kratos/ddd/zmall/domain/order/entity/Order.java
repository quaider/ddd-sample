package vip.kratos.ddd.zmall.domain.order.entity;

import com.google.common.base.Verify;
import lombok.Getter;
import vip.kratos.ddd.zmall.domain.order.entity.vo.Address;
import vip.kratos.ddd.zmall.domain.order.entity.vo.OrderStatus;
import vip.kratos.ddd.zmall.domain.shared.AggregateRoot;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;

import java.math.BigDecimal;
import java.util.*;

/**
 * 订单聚合根
 */
@Getter
public class Order extends AggregateRoot<Order> {

    protected Long orderId;

    // 订单号
    private String orderSn;
    private long userId;
    private BigDecimal totalAmount;
    // 总优惠金额
    private BigDecimal couponAmount;
    // 总支付金额
    private BigDecimal payAmount;
    private OrderStatus status;
    private Date orderDate;
    //  送货地址
    private Address receiverAddress;

    private Set<OrderLine> lines;

    public Order(long userId) {
        this(userId, new HashSet<>());
    }

    public Order(long userId, Set<OrderLine> lines) {
        Objects.requireNonNull(lines);
        this.userId = userId;
        this.lines = Collections.unmodifiableSet(lines);
        this.totalAmount = calTotalAmount();
    }

    /**
     * 新建订单
     */
    public void create() {
        // 待支付
        this.status = OrderStatus.CREATED;
        orderDate = new Date();
    }

    /**
     * 支付订单
     *
     * @param totalCoupon 优惠金额
     */
    public void pay(BigDecimal totalCoupon) {
        BigDecimal shouldPay = totalAmount.subtract(totalCoupon);
        Verify.verify(shouldPay.compareTo(BigDecimal.ZERO) >= 0, "支付金额不能为非负数");
        this.payAmount = shouldPay;
        this.couponAmount = totalCoupon;
        // 已支付待发货
        this.status = OrderStatus.PAID;
    }

    /**
     * 计算订单总金额
     */
    private BigDecimal calTotalAmount() {
        return lines.stream()
                .map(f -> f.getProduct().getPrice().multiply(BigDecimal.valueOf(f.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public Long identity() {
        return orderId;
    }

    public static class Builder {
        private Long userId;
        private String orderSn;
        private Set<OrderLine> lines;

        private Builder(long userId) {
            this.userId = userId;
            this.lines = new HashSet<>();
        }

        private Builder orderSn(String orderSn) {
            this.orderSn = orderSn;
            return this;
        }

        public void addLine(int quantity, ProductSnapshot product) {
            Verify.verifyNotNull(product);
            OrderLine line = new OrderLine(quantity, product);
            lines.add(line);
        }

        public Order build() {
            Order order = new Order(userId, lines);
            order.orderSn = this.orderSn;
            return order;
        }
    }
}
