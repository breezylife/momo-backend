package tw.com.momo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productspec")
public class ProdspecBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "SPEC")
	private String spec;
	
	@Column(name = "STOCK")
	private Integer stock;
//	@Column(name = "PRODUCTSID")
//	private Integer productsid;

	
	@ManyToOne(targetEntity = ProductBean.class)
	@JoinColumn(name = "productsid", nullable = false )
	private ProductBean product;
	
	public ProdspecBean() {
	}
	
	public ProdspecBean(ProductBean product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "ProudspecBean [product=" + product + ", id=" + id + ", spec=" + spec + "]";
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
//	public Integer getProductsid() {
//		return productsid;
//	}
//
//	public void setProductsid(Integer productsid) {
//		this.productsid = productsid;
//	}
	
}
