package tw.com.momo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class OrderDetailBean {
	
	@ManyToOne(targetEntity = ProductBean.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "productsid", nullable = false)
	private ProductBean productBean;
	
	@ManyToOne(targetEntity = OrderBean.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ordersid", nullable = false)
	private OrderBean orderBean;
	
	public OrderDetailBean() {
		setuptime = new Date();
	}
	
	public OrderDetailBean(OrderBean order, ProductBean product) {
		this.orderBean = order;
		this.productBean = product;
		setuptime = new Date();
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "SETUPTIME")
	private Date setuptime;
	
	@Column(name = "QUANTITY")
	private Integer quantity;

	@Override
	public String toString() {
		return "OrderDetailBean [id=" + id + ", setuptime=" + setuptime + ", quantity=" + quantity + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSetuptime() {
		return setuptime;
	}

	public void setSetuptime(Date setuptime) {
		this.setuptime = setuptime;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}