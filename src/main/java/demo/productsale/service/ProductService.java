package demo.productsale.service;

import java.util.List;

import demo.productsale.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts(int page, int size);
    ProductDTO getProductById(Long id);
    void addProduct(ProductDTO productDTO);
    void updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    double getTotalRevenue();
    double getRevenueByProduct(Long productId);
}
