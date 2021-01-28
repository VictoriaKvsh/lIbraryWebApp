package by.grodno.vika.librarywebapp.exception;


public class UserNotFoundException extends RuntimeException {

	private String userInfo;

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	
	
	

	
}