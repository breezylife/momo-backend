package tw.com.momo.payload.request;

import java.util.Date;

public class SignUpDto {
	private String email;
	private String password;
	private String gender;
	private String birthday;
	private String phone;
	private String address;
	private String username;
	private String userphoto;
	@Override
	public String toString() {
		return "SignUpDto [email=" + email + ", password=" + password + ", gender=" + gender + ", birthday=" + birthday
				+ ", phone=" + phone + ", address=" + address + ", username=" + username + ", userphoto=" + userphoto
				+ "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
}