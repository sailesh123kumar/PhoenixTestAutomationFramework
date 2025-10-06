package com.api.pojo;

public class CustomerProduct {

	private String dop;
	private String serial_numbe;
	private String imei1;
	private String imei2;
	private String popurl;
	private int product_id;
	private int mst_model_id;

	public CustomerProduct(String dop, String serial_numbe, String imei1, String imei2, String popurl, int product_id,
			int mst_model_id) {
		super();
		this.dop = dop;
		this.serial_numbe = serial_numbe;
		this.imei1 = imei1;
		this.imei2 = imei2;
		this.popurl = popurl;
		this.product_id = product_id;
		this.mst_model_id = mst_model_id;
	}

	public String getDop() {
		return dop;
	}

	public String getSerial_numbe() {
		return serial_numbe;
	}

	public String getImei1() {
		return imei1;
	}

	public String getImei2() {
		return imei2;
	}

	public String getPopurl() {
		return popurl;
	}

	public int getProduct_id() {
		return product_id;
	}

	public int getMst_model_id() {
		return mst_model_id;
	}

	public void setDop(String dop) {
		this.dop = dop;
	}

	public void setSerial_numbe(String serial_numbe) {
		this.serial_numbe = serial_numbe;
	}

	public void setImei1(String imei1) {
		this.imei1 = imei1;
	}

	public void setImei2(String imei2) {
		this.imei2 = imei2;
	}

	public void setPopurl(String popurl) {
		this.popurl = popurl;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public void setMst_model_id(int mst_model_id) {
		this.mst_model_id = mst_model_id;
	}

	@Override
	public String toString() {
		return "CustomerProduct [dop=" + dop + ", serial_numbe=" + serial_numbe + ", imei1=" + imei1 + ", imei2="
				+ imei2 + ", popurl=" + popurl + ", product_id=" + product_id + ", mst_model_id=" + mst_model_id + "]";
	}

}
