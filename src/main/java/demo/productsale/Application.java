package demo.productsale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import demo.productsale.dto.ProductDTO;
import demo.productsale.dto.SaleDTO;
import demo.productsale.entity.User;
import demo.productsale.repository.UserRepository;
import demo.productsale.service.ProductService;

@SpringBootApplication
public class Application {

	 @Autowired
	    private UserRepository repository;

	    @PostConstruct
	    public void initUsers() {
	        List<User> users = Stream.of(
	                new User(101, "Agin", "password", "aginth90@gmail.com","ADMIN"),
	                new User(102, "user1", "pwd1", "user1@gmail.com","ADMIN"),
	                new User(103, "user2", "pwd2", "user2@gmail.com","USER"),
	                new User(104, "user3", "pwd3", "user3@gmail.com","USER")
	        ).collect(Collectors.toList());
	        repository.saveAll(users);
	        
	        
	    }

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		 ProductService productService = context.getBean(ProductService.class);

	        // Create and add sale,Product objects
	        List<SaleDTO> sale1 = new ArrayList<>();
	        sale1.add(new SaleDTO( 5, new Date()));
	        ProductDTO product1 = new ProductDTO("Product 1", "Description 1", 10.99, 100, sale1);
	        productService.addProduct(product1);

	        List<SaleDTO> sale2 = new ArrayList<>();
	        sale2.add(new SaleDTO( 8, new Date()));
	        ProductDTO product2 = new ProductDTO("Product 2", "Description 2", 19.99, 50, sale2);
	        productService.addProduct(product2);


	}

}
