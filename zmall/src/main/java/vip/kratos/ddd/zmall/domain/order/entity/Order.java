package vip.kratos.ddd.zmall.domain.order.entity;

import com.google.common.base.Verify;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import vip.kratos.ddd.zmall.domain.order.entity.vo.Address;
import vip.kratos.ddd.zmall.domain.order.entity.vo.OrderStatus;
import vip.kratos.ddd.zmall.domain.order.event.OrderCreatedEvent;
import vip.kratos.ddd.zmall.domain.shared.AggregateRoot;
import vip.kratos.ddd.zmall.domain.shared.DomainEvent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 订单聚合根
 */
@Entity
@Table(name = "t_order",
        indexes = {@Index(name = "idx_userId", columnList = "userId")})
@Getter
@NoArgsConstructor
public class Order extends AggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long orderId;

    // 订单号
    @Column(nullable = false)
    private String orderSn;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    // 总优惠金额
    @Column(nullable = false)
    private BigDecimal couponAmount;

    // 总支付金额
    @Column(nullable = false)
    private BigDecimal payAmount;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private Date orderDate;

    //  送货地址
    @Embedded
    private Address receiverAddress;

    @OneToMany(cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "none"), nullable = false, updatable = false)
    @Getter
    private Set<OrderLine> lines;

    public Order(long userId, Set<OrderLine> lines) {
        Objects.requireNonNull(lines);
        this.userId = userId;
        this.lines = Collections.unmodifiableSet(lines);
    }

    /**
     * 新建订单
     */
    public void create(String orderSn, Address address, BigDecimal couponAmount) {
        this.orderSn = orderSn;
        // 待支付
        this.status = OrderStatus.CREATED;
        this.orderDate = new Date();
        this.couponAmount = couponAmount;
        this.receiverAddress = address;

        reCalAmount();

        raiseEvent(new OrderCreatedEvent(this.orderSn, this.status));
    }

    /**
     * 支付订单
     */
    public void pay() {
        // 已支付待发货
        this.status = OrderStatus.PAID;
    }

    /**
     * 计算订单金额
     */
    private void reCalAmount() {
        this.totalAmount = lines.stream()
                .map(f -> f.getProduct().getPrice().multiply(BigDecimal.valueOf(f.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        BigDecimal shouldPay = totalAmount.subtract(couponAmount);
        Verify.verify(shouldPay.compareTo(BigDecimal.ZERO) >= 0, "支付金额不能为非负数");
        this.payAmount = shouldPay;
    }

    @Override
    public Long identity() {
        return orderId;
    }

    private transient List<DomainEvent> events = new ArrayList<>();
}
