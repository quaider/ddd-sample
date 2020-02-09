package vip.kratos.ddd.zmall.infrastructure.repository;

import org.springframework.stereotype.Repository;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.cart.repository.ICartRepository;
import vip.kratos.ddd.zmall.infrastructure.factory.CartFactory;
import vip.kratos.ddd.zmall.infrastructure.po.CartItemPO;
import vip.kratos.ddd.zmall.infrastructure.po.CartPO;
import vip.kratos.ddd.zmall.infrastructure.repository.dao.CartDao;
import vip.kratos.ddd.zmall.infrastructure.repository.dao.CartItemDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CartRepository implements ICartRepository {
    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final CartFactory cartFactory;

    public CartRepository(CartDao cartDao, CartItemDao cartItemDao, CartFactory cartFactory) {
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.cartFactory = cartFactory;
    }

    @Override
    public Cart findCart(long userId, boolean load) {
        CartPO cartPO = cartDao.findByUserId(userId);

        if (cartPO == null) return null;

        List<CartItemPO> cartItemPOList = new ArrayList<>();
        if (load) {
            cartItemPOList = cartItemDao.findByCartId(cartPO.getId());
        }

        return cartFactory.toCart(cartPO, cartItemPOList);
    }

    @Override
    public CartItem findCartItemByProductId(long productId) {
        return cartFactory.toCartItem(cartItemDao.findByProductId(productId));
    }

    @Override
    public void saveCart(Cart cart) {
        CartPO cartPO = cartFactory.toCartPO(cart);
        cartDao.save(cartPO);

        Set<CartItemPO> cartItemPOSet = cart.getCartItems().stream()
                .map(f -> cartFactory.toCartItemPO(cartPO.getId(), f))
                .collect(Collectors.toSet());
        cartItemDao.saveAll(cartItemPOSet);
    }
}
