package com.api.request.model;

public record UserCredentials(String username, String password)
{

	
	//we cannot create instance variable inside the record
	//we can create static variable , static method ,non static method and static block inside the record
	//we are allowed to create simple constructor inside the record but we should call canonical constructor on the 1st line
	//record cannot extends other classes as it internally extends java.lang.Records by default
	//record can implement interfaces
	//we can create inner classses and enums in record
	
	static {
		int x=10;
		System.out.println("static block");
	}
	
	public UserCredentials() {
		this("iamfd", "password");  //constructor chaining
		System.out.println("Simple constructor");
	}
	
}
