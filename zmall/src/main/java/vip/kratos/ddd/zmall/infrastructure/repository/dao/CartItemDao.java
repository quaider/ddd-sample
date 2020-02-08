package vip.kratos.ddd.zmall.infrastructure.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.kratos.ddd.zmall.infrastructure.po.CartItemPO;

import java.util.List;

public interface CartItemDao extends JpaRepository<CartItemPO, Long> {
    List<CartItemPO> findByCartId(long cartId);
}
