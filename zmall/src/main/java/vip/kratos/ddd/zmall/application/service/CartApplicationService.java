package vip.kratos.ddd.zmall.application.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.application.assembler.ShoppingCartAssembler;
import vip.kratos.ddd.zmall.application.common.ApplicationException;
import vip.kratos.ddd.zmall.application.dto.CartDto;
import vip.kratos.ddd.zmall.application.vm.CartItemModel;
import vip.kratos.ddd.zmall.domain.cart.entity.Cart;
import vip.kratos.ddd.zmall.domain.cart.entity.CartItem;
import vip.kratos.ddd.zmall.domain.cart.service.CartDomainService;
import vip.kratos.ddd.zmall.domain.common.vo.ProductSnapshot;
import vip.kratos.ddd.zmall.domain.product.entity.Product;
import vip.kratos.ddd.zmall.domain.product.service.ProductDomainService;

import javax.transaction.Transactional;

@Service
public class CartApplicationService {

    private final CartDomainService cartDomainService;
    private final ProductDomainService productDomainService;
    private final ShoppingCartAssembler assembler;

    public CartApplicationService(CartDomainService cartService, ProductDomainService productDomainService, ShoppingCartAssembler assembler) {
        this.cartDomainService = cartService;
        this.productDomainService = productDomainService;
        this.assembler = assembler;
    }

    @Transactional(rollbackOn = Exception.class)
    public void addCartItem(long userId, CartItemModel cartItemModel) {

    }

    @Transactional(rollbackOn = Exception.class)
    public void updateQuantity(long userId, CartItemModel cartItemModel) {
        
    }

    public CartDto findCart(long userId) {
        return null;
    }

    private ProductSnapshot fromProduct(Product product) {
        return ProductSnapshot.builder()
                .name(product.getName())
                .productId(product.getId())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

}
