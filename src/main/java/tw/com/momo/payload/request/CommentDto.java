package tw.com.momo.payload.request;

public class CommentDto {
	private String broad;

	public String getBroad() {
		return broad;
	}

	public void setBroad(String broad) {
		this.broad = broad;
	}

	@Override
	public String toString() {
		return "CommentDto [indexx=" + broad + "]";
	}
	
}
