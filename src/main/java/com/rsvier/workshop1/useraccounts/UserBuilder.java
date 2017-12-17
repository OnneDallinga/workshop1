package com.rsvier.workshop1.useraccounts;

import com.rsvier.workshop1.model.RetrieveUserInfoModel;

public class UserBuilder {
	private String username;
	private int userID;
	private boolean admin;
	
	public UserBuilder setUsername(String username) {
		this.username = username;
		return this;
	}
	public User build() {
		userID = new RetrieveUserInfoModel().retrieveUserId(username);
		admin = new RetrieveUserInfoModel().retrieveAdminStatus(userID);
		return new User(username, userID, admin);
	}
}