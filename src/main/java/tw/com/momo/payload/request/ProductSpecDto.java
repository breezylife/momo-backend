package tw.com.momo.payload.request;

public class ProductSpecDto {
	private String name;
	private Integer stock;
	
	@Override
	public String toString() {
		return "ProductSpecDto [name=" + name + ", stock=" + stock + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
