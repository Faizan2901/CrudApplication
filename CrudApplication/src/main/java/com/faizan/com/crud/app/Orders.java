package com.faizan.com.crud.app;

public class Orders {
	
	private int custId;
	private int foodId;
	private int quantity;
	private int totalPrice;
	private String foodName;
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Orders(int custId, int foodId, int quantity, int totalPrice, String foodName) {
		super();
		this.custId = custId;
		this.foodId = foodId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.foodName = foodName;
	}
	
	
	
	
	
	
	

}
