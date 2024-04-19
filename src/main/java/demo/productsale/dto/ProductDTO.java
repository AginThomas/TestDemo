package demo.productsale.dto;

import java.util.List;

public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private List<SaleDTO> sales;

    public ProductDTO() {}
    

	public ProductDTO(String name, String description, double price, int quantity,
			List<SaleDTO> sales) {
		super();

		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.sales = sales;
	}


	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<SaleDTO> getSales() {
		return sales;
	}
	public void setSales(List<SaleDTO> sales) {
		this.sales = sales;
	}


}

