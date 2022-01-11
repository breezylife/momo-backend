package tw.com.momo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "productpic")
public class PictureBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String picname;
    @ManyToOne(targetEntity = ProductBean.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "productsid")
	private ProductBean productBean;
	
	
	public Integer getId() {
		return id;
	}
	
	public PictureBean(ProductBean product , String picname) {
        this.productBean = product;
        this.picname = picname;
    }
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPicname() {
		return picname;
	}
	public void setPicname(String picname) {
		this.picname = picname;
	}
	public ProductBean getProductBean() {
		return productBean;
	}
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	

	
}
