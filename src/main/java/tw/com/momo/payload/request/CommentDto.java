package tw.com.momo.payload.request;

public class CommentDto {
	private String broad;
	private Integer star;
	
	public String getBroad() {
		return broad;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public void setBroad(String broad) {
		this.broad = broad;
	}
}
