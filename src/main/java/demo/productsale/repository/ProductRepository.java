package demo.productsale.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.productsale.entity.Product;

public interface ProductRepository extends  JpaRepository<Product, Long>{

	Optional<Product> findTopByOrderByProductIdDesc();
	

    @Query("SELECT p.price FROM Product p WHERE p.productId = ?1")
    Optional<Double> findPriceByProductId(Long productId);
}
