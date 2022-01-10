package tw.com.momo.payload.request;

public class CommentDto {
	private String indexx;

	public String getIndexx() {
		return indexx;
	}

	public void setIndexx(String indexx) {
		this.indexx = indexx;
	}

	@Override
	public String toString() {
		return "CommentDto [indexx=" + indexx + "]";
	}
	
}
