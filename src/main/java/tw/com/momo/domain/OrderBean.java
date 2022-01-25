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
@Table(name = "orders")
public class OrderBean {
	
	@ManyToOne(targetEntity = UserBean.class)
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
	private UserBean userBean;

//	private Set<OrderDetailBean> orderdetail= new HashSet<OrderDetailBean>(0);  
//	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "orderdetail")
//	public Set<OrderDetailBean> getOrderdetail() {
//		return this.orderdetail ;
//	}
//
//	public void setOrderdetail(Set<OrderDetailBean> orderdetail) {
//		this.orderdetail = orderdetail;
//	}

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
	private String shippingadd;
	
	@Column(name = "SETUPTIME")
	private Date setuptime;
	
	@Column(name = "TEL")
	private String tel ;
	
	@Column(name = "CONSIGNEE")
	private String consignee;
	
//	@Column(name = "USERPHOTO")
//	private String userphoto;

	@Override
	public String toString() {
		return "OrderBean [id=" + id + ", total=" + total + ", shipping=" + shipping + ", payment=" + payment
				+ ", status=" + status + ", shippingadd=" + shippingadd + ", tel=" + tel + ", consignee=" + consignee + "]";
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

	public String getShippingadd() {
		return shippingadd;
	}

	public void setShippingadd(String shippingadd) {
		this.shippingadd = shippingadd;
	}

	public Date getSetuptime() {
		return setuptime;
	}

	public void setSetuptime(Date setuptime) {
		this.setuptime = setuptime;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	

//	public String getUserphoto() {
//		return userphoto;
//	}
//
//	public void setUserphoto(String userphoto) {
//		this.userphoto = userphoto;
//	}
}