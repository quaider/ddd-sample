package vip.kratos.ddd.zmall.infrastructure.repository;

import org.springframework.stereotype.Repository;
import vip.kratos.ddd.zmall.domain.product.entity.Product;
import vip.kratos.ddd.zmall.domain.product.repository.IProductRepository;
import vip.kratos.ddd.zmall.infrastructure.po.ProductPO;
import vip.kratos.ddd.zmall.infrastructure.repository.dao.ProductDao;

@Repository
public class ProductRepository implements IProductRepository {

    private final ProductDao productDao;

    public ProductRepository(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product findById(long productId) {
        ProductPO productPO = productDao.findById(productId).orElse(null);
        if (productPO == null) return null;
        return Product.fromProductPO(productPO);
    }
}
