package vip.kratos.ddd.zmall.infrastructure.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.kratos.ddd.zmall.infrastructure.po.CartPO;

public interface CartDao extends JpaRepository<CartPO, Long> {
    CartPO findByUserId(long userId);
}
