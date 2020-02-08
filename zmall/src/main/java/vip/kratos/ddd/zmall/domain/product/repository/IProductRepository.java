package vip.kratos.ddd.zmall.domain.product.repository;

import vip.kratos.ddd.zmall.domain.product.entity.Product;

public interface IProductRepository {
    Product findById(long productId);
}
