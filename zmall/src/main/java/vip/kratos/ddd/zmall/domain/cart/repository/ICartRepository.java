package vip.kratos.ddd.zmall.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(long userId);
}
