package com.api.constants;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

public enum Product {
	
	NEXUS_2(1),PIXEL(2);
	
	int code;
	
	//bydefault constructor will be private in enum we dont need to declare
	
	Product(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
