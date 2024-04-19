package demo.productsale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.productsale.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

    @Query("SELECT s FROM Sale s WHERE s.product.productId = :productId")
    List<Sale> findByProductId(Long productId);
}
