package vip.kratos.ddd.zmall.infrastructure.repository.jpa;

import org.springframework.stereotype.Repository;
import vip.kratos.ddd.zmall.domain.order.entity.Order;
import vip.kratos.ddd.zmall.domain.order.repository.IOrderRepository;

import javax.persistence.EntityManager;

@Repository
public class OrderRepositoryJpa extends BaseRepository<Order> implements IOrderRepository {

    public OrderRepositoryJpa(EntityManager em) {
        super(Order.class, em);
    }

    @Override
    public void store(Order order) {
        super.save(order);
    }
}
