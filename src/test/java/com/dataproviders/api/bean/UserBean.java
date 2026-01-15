package com.dataproviders.api.bean;

import com.opencsv.bean.CsvBindByName;
import com.poiji.annotation.ExcelCellName;

public class UserBean {

	@ExcelCellName("username")
	@CsvBindByName(column = "username")
	private String username;
	
	@ExcelCellName("password")
	@CsvBindByName(column = "password")
	private String password;
	
	public UserBean() {
		
	}

	public UserBean(String username, String password) {
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
		return "UserBean [username=" + username + ", password=************]";
	}

}
