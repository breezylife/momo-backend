package tw.com.momo.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderBean {
	
	@ManyToOne(targetEntity = UserBean.class)
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
	private UserBean userBean;

	public OrderBean() {
		setuptime = new Date();
	}
	
	public OrderBean(UserBean user) {
		this.userBean = user;
		setuptime = new Date();
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "TOTAL")
	private Integer total;
	
	@Column(name = "SHIPPING")
	private Integer shipping;
	
	@Column(name = "PAYMENT")
	private Integer payment;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "SHIPPINGADD")
	private Integer shippingadd;
	
	@Column(name = "SETUPTIME")
	private Date setuptime;

	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", total=" + total + ", shipping=" + shipping + ", payment=" + payment
				+ ", status=" + status + ", shippingadd=" + shippingadd + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShippingadd() {
		return shippingadd;
	}

	public void setShippingadd(Integer shippingadd) {
		this.shippingadd = shippingadd;
	}

	public Date getSetuptime() {
		return setuptime;
	}

	public void setSetuptime(Date setuptime) {
		this.setuptime = setuptime;
	}
}