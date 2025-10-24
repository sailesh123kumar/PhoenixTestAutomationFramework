package com.api.constants;

public enum Warranty_Status {
	
	
	IN_WARRANTY(1),
	OUT_WARRANTY(2);
	
	int code;

	private Warranty_Status(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
