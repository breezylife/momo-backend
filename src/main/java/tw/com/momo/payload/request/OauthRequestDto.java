package tw.com.momo.payload.request;

public class OauthRequestDto {
	
	//就是email
	private String email;
	
	//對應password
	private String uid;
	
	//對應username
	private String displayName;
	
	//對應userphoto
	private String photoURL;
	
	@Override
	public String toString() {
		return "OauthRequestDto [email=" + email + ", uid=" + uid + ", displayName=" + displayName + ", photoURL="
				+ photoURL + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
}