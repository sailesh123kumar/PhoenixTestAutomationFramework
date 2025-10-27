package com.dataproviders.api.bean;

import com.opencsv.bean.CsvBindByName;

public class UserBean {

	@CsvBindByName(column = "username")
	private String username;
	
	@CsvBindByName(column = "password")
	private String password;
	
	public UserBean() {
		
	}

	public UserBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + "]";
	}

}
