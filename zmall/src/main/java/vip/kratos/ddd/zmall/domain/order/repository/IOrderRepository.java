package vip.kratos.ddd.zmall.domain.order.repository;

import vip.kratos.ddd.zmall.domain.order.entity.Order;

public interface IOrderRepository {

    void store(Order order);

}
