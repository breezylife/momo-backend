package tw.com.momo.payload.request;

public class OrderDetailDto {
	
	private Integer id;
	private String name;
	private Integer stock;
	private Integer price;
	
	@Override
	public String toString() {
		return "OrderDetailDto [id=" + id + ", name=" + name + ", stock=" + stock + ", price=" + price + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
