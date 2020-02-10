package vip.kratos.ddd.zmall.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;

import java.util.Optional;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProductProductId(long productId);
}
