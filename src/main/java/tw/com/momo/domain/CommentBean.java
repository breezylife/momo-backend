package tw.com.momo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comment")
public class CommentBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "broad")
	private String broad;
	@Column(name = "productsid")
	private Integer productsid;
	@Column(name = "userid")
	private Integer userid;

	@Temporal(TemporalType.TIMESTAMP)
    private Date setuptime;
	
 
	public CommentBean() {
        setuptime = new Date();
    }	
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getProductsid() {
		return productsid;
	}

	public void setProductsid(Integer productsid) {
		this.productsid = productsid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getbroad() {
		return broad;
	}

	public void setbroad(String broad) {
		this.broad = broad;
	}


	public Date getSetuptime() {
		return setuptime;
	}

	public void setSetuptime(Date setuptime) {
		this.setuptime = setuptime;
	}


	@Override
	public String toString() {
		return "CommentBean [id=" + id + ", indexx=" + broad + ", userid="
				+ ", setuptime=" + setuptime + "]";
	}
	
}
