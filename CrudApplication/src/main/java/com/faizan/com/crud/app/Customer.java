package com.faizan.com.crud.app;

public class Customer {
	
	private int custId;
	private String custName;
	private String custEmail;
	private String custPhone;
	private String custPassword;
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	public Customer(int custId, String custName, String custEmail, String custPhone) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPhone = custPhone;
	}
	public Customer(String custName, String custEmail, String custPhone, String custPassword) {
		super();
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPhone = custPhone;
		this.custPassword = custPassword;
	}
	
	

}
