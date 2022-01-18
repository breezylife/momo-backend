package tw.com.momo.payload.request;

import java.util.List;

import tw.com.momo.domain.PictureBean;
import tw.com.momo.domain.ProdspecBean;

public class ProductDto {
	private String name;
	private int price;
	private int stock;
	private String description;
	private String category;
	private List<String> url;
	private List<ProductSpecDto> specs;

	
	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ProductSpecDto> getSpecs() {
		return specs;
	}

	public void setSpecs(List<ProductSpecDto> specs) {
		this.specs = specs;
	}
}