package vip.kratos.ddd.zmall.application.service;

import com.google.common.base.Verify;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.application.assembler.OrderAssembler;
import vip.kratos.ddd.zmall.application.common.ApplicationException;
import vip.kratos.ddd.zmall.application.common.DomainEventAware;
import vip.kratos.ddd.zmall.application.vm.CreateCartItemCommand;
import vip.kratos.ddd.zmall.application.vm.CreateOrderCommand;
import vip.kratos.ddd.zmall.domain.order.entity.Order;
import vip.kratos.ddd.zmall.domain.order.entity.OrderLine;
import vip.kratos.ddd.zmall.domain.order.entity.vo.Address;
import vip.kratos.ddd.zmall.domain.order.repository.IOrderRepository;
import vip.kratos.ddd.zmall.domain.product.entity.Product;
import vip.kratos.ddd.zmall.domain.product.repository.IProductRepository;
import vip.kratos.ddd.zmall.domain.shared.vo.ProductSnapshot;
import vip.kratos.ddd.zmall.interfaces.vm.OrderVM;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderApplicationService {

    private final IProductRepository productRepository;
    private final IOrderRepository orderRepository;
    private final OrderAssembler orderAssembler;

    public OrderApplicationService(IProductRepository productRepository, IOrderRepository orderRepository, OrderAssembler orderAssembler) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderAssembler = orderAssembler;
    }

    @DomainEventAware
    @Transactional(rollbackOn = Exception.class)
    public OrderVM createOrder(long userId, CreateOrderCommand command) {

        Verify.verifyNotNull(command);

        // 验证库存
        command.getItemCommand().forEach(this::assetSkuEnough);

        // 验证优惠卷可用
        assetCouponValid(command.getCouponId());

        BigDecimal couponAmount = BigDecimal.valueOf(5);

        List<Product> products = productRepository.findAllById(() ->
                command.getItemCommand().stream()
                        .mapToLong(CreateCartItemCommand::getProductId).iterator());

        Set<ProductSnapshot> snapshots = products.stream()
                .map(ProductSnapshot::fromProduct)
                .collect(Collectors.toSet());

        Set<OrderLine> lines = command.getItemCommand().stream()
                .map(f -> toOrderLine(f, snapshots))
                .collect(Collectors.toSet());

        // 锁定商品库存

        Order order = new Order(userId, lines);
        order.create(UUID.randomUUID().toString(), toDomainAddress(command.getAddress()), couponAmount);

        orderRepository.store(order);

        return orderAssembler.toOrderVM(order);
    }

    private void assetCouponValid(long couponId) {

    }

    private void assetSkuEnough(CreateCartItemCommand command) {
        if (!(command.getQuantity() > 1 && command.getQuantity() < 5))
            throw ApplicationException.verify("产品：%s 的库存不足", command.getProductId());
    }

    private OrderLine toOrderLine(CreateCartItemCommand command, Set<ProductSnapshot> productSnapshots) {
        return orderAssembler.toOrderLine(command, buildFun(productSnapshots));
    }

    private Function<Long, ProductSnapshot> buildFun(Set<ProductSnapshot> snapshots) {
        return (productId) -> snapshots.stream().filter(f -> f.getProductId().equals(productId)).findFirst().orElse(null);
    }

    private Address toDomainAddress(vip.kratos.ddd.zmall.shared.Address sharedAddr) {
        Address address = new Address();
        BeanUtils.copyProperties(sharedAddr, address);
        return address;
    }

}
