package vip.kratos.ddd.zmall.infrastructure.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.kratos.ddd.zmall.infrastructure.po.ProductPO;

public interface ProductDao extends JpaRepository<ProductPO, Long> {
}
