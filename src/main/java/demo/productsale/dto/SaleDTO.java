package demo.productsale.dto;

import java.util.Date;

public class SaleDTO {
    private Long saleId;
    private int quantity;
    private Date saleDate;

    public SaleDTO() {}
    
	public SaleDTO(int quantity, Date saleDate) {
		super();
		this.quantity = quantity;
		this.saleDate = saleDate;
	}
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}


}

