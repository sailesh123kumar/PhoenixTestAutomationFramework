package com.api.utils;

import com.api.constants.Role;

public class AuthTokenDemoRunner {
	
	public static void main(String[] args) {
		
		
		for(int i=1; i<=100 ; i++) {
			String token = AuthTokenProvider.getToken(Role.FD);
			System.out.println(token);
		}
		
		
	}

}
