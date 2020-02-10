package vip.kratos.ddd.zmall.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import vip.kratos.ddd.zmall.domain.shared.AggregateRoot;
import vip.kratos.ddd.zmall.domain.order.entity.vo.Address;
import vip.kratos.ddd.zmall.domain.order.entity.vo.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 订单聚合根
 */
@Getter
@Setter
public class Order extends AggregateRoot<Order> {
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
        this.lines = new HashSet<>();
    }

    public void create() {
        this.totalAmount = calTotalAmount();
        this.payAmount = this.totalAmount.multiply(this.couponAmount);
        if (this.payAmount.compareTo(BigDecimal.ZERO) < 0) this.payAmount = BigDecimal.ZERO;
        status = OrderStatus.INIT;
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
    public Long getIdentity() {
        return null;
    }
}
