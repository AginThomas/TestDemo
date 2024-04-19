package demo.productsale.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import demo.productsale.dto.ProductDTO;
import demo.productsale.dto.SaleDTO;
import demo.productsale.entity.Product;
import demo.productsale.entity.Sale;
import demo.productsale.exception.ResourceNotFoundException;
import demo.productsale.repository.ProductRepository;
import demo.productsale.repository.SaleRepository;
import demo.productsale.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private SaleRepository saleRepository;
    


    @Override
    public List<ProductDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        
        return productPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        return convertToDTO(product);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        productRepository.save(product);
        Product productReturn=productRepository.findTopByOrderByProductIdDesc()
        		.orElseThrow(() -> new IllegalStateException("Failed to retrieve newly created product."));
        for (SaleDTO saleDTO : productDTO.getSales()) {
        	createSale(productReturn.getProductId(),saleDTO.getQuantity(),saleDTO.getSaleDate());
        }   
     }

    public void createSale(Long productId, int quantity, Date saleDate) {
        // Retrieve the Product object by its ID
        Product product = productRepository.findById(productId)
                                           .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        // Create a new Sale object
        Sale sale = new Sale();
        sale.setProduct(product);
        sale.setQuantity(quantity);
        sale.setSaleDate(saleDate);

        // Save the Sale object
        saleRepository.save(sale);
    }
    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());

        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        productRepository.delete(product);
    }

    @Override
    public double getTotalRevenue() {
        List<Sale> sales = saleRepository.findAll();
        double totalRevenue = 0.0;
        for (Sale sale : sales) {
            Product product = sale.getProduct();
            if (product != null) {
                totalRevenue += sale.getQuantity() * product.getPrice();
            }
        }
        return totalRevenue;
    }

    public double getRevenueByProduct(Long productId) {
        List<Sale> sales = saleRepository.findByProductId(productId);
        if (sales.isEmpty()) {
            throw new ResourceNotFoundException("No sales found for product with ID: " + productId);
        }
        double totalRevenue = 0.0;
        for (Sale sale : sales) {
            totalRevenue += sale.getQuantity() * sale.getProduct().getPrice();
        }
        return totalRevenue;
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
   
        productDTO.setSales(product.getSales().stream()
                .map(this::convertToSaleDTO)
                .collect(Collectors.toList()));
        return productDTO;
    }

    private SaleDTO convertToSaleDTO(Sale sale) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(sale.getSaleId());
        saleDTO.setQuantity(sale.getQuantity());
        saleDTO.setSaleDate(sale.getSaleDate());
        return saleDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        // Assuming sales are not handled in addProduct method
        return product;
    }

}
