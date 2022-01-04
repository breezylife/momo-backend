package tw.com.momo.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
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
@Table(name="confirmationTokenBean")
public class ConfirmationTokenBean {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tokenid")
    private Integer tokenid;

    @Column(name="confirmationtoken")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate")
    private Date createdDate;

    @OneToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userid")
    private UserBean userBean;
    
    public ConfirmationTokenBean() {
    	
    }

    public ConfirmationTokenBean(UserBean userBean) {
        this.userBean = userBean;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

	public Integer getTokenid() {
		return tokenid;
	}

	public void setTokenid(Integer tokenid) {
		this.tokenid = tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
    
    // getters and setters
    
}
