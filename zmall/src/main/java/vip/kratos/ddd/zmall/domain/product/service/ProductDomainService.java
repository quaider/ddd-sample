package vip.kratos.ddd.zmall.domain.product.service;

import org.springframework.stereotype.Service;
import vip.kratos.ddd.zmall.domain.product.entity.Product;
import vip.kratos.ddd.zmall.domain.product.repository.IProductRepository;

@Service
public class ProductDomainService {

    private final IProductRepository productRepository;

    public ProductDomainService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProduct(long productId) {
        return productRepository.findById(productId);
    }

}
