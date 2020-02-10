package vip.kratos.ddd.zmall.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.kratos.ddd.zmall.domain.product.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
