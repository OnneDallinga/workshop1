package useraccounts;

public class User {
	private boolean isAdmin;
	private String username;
	
	public User (String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
