package tw.com.momo.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "products")
public class ProductsBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private int price;
	private int stock;
	private String description;
	private String category;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

//    @OneToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "userid")
//    private UserBean userBean;
    
    public ProductsBean(UserBean userBean) {
//        this.userBean = userBean;
        createdDate = new Date();
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

//	public UserBean getUserBean() {
//		return userBean;
//	}
//
//	public void setUserBean(UserBean userBean) {
//		this.userBean = userBean;
//	}
}
