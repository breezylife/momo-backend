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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comment")
public class CommentBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "board")
	private String board;
	@Column(name = "star")
	private Integer star;
	@Column(name = "productsid")
	private Integer productsid;
	
	@ManyToOne(targetEntity = UserBean.class)
	@JoinColumn(name = "userid", nullable = false)
	private UserBean user;

	@Temporal(TemporalType.TIMESTAMP)
    private Date setuptime;
	
	public CommentBean() {

    }
 
	public CommentBean(UserBean user) {
		this.user = user;
        setuptime = new Date();
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

	public String getboard() {
		return board;
	}

	public void setboard(String broad) {
		this.board = broad;
	}


	public Date getSetuptime() {
		return setuptime;
	}

	public void setSetuptime(Date setuptime) {
		this.setuptime = setuptime;
	}
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

}
