package tw.com.momo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class OrderDetailBean {
	
	@ManyToOne(targetEntity = ProductBean.class)
	@JoinColumn(name = "productsid", nullable = false)
	private ProductBean product;
	
	@ManyToOne(targetEntity = OrderBean.class)
	@JoinColumn(name = "ordersid", nullable = false)
	private OrderBean order;
	
	public OrderDetailBean() {
	}
	
	public OrderDetailBean(OrderBean order, ProductBean product) {
		this.order = order;
		this.product = product;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NUM")
	private Integer num;

	@Override
	public String toString() {
		return "OrderDetailBean [id=" + id + ", quantity=" + num + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public OrderBean getOrder() {
		return order;
	}

	public void setOrder(OrderBean order) {
		this.order = order;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}