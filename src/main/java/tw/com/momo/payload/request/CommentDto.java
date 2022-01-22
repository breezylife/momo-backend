package tw.com.momo.payload.request;

public class CommentDto {
	private String broad;
	private Integer userid;
	
	public String getBroad() {
		return broad;
	}

	
	public void setBroad(String broad) {
		this.broad = broad;
	}

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	@Override
	public String toString() {
		return "CommentDto [indexx=" + broad +",userid="+userid+"]";
	}
	
}
