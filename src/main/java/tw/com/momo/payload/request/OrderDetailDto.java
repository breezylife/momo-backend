package tw.com.momo.payload.request;

public class OrderDetailDto {
	
	private Integer id;
	private String name;
	private Integer num;
	private Integer price;
	private String spec;
	
	@Override
	public String toString() {
		return "OrderDetailDto [id=" + id + ", name=" + name + ", num=" + num + ", price=" + price + ", spec=" + spec
				+ "]";
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
}
