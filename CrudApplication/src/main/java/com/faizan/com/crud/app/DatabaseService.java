package com.faizan.com.crud.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseService {
	static boolean isTrue = false;
	static String adminCheck = "SELECT * FROM compnay.admin where Username=? and Password=?";
	static String showCust = "SELECT custId,custName,custEmail,custPhone FROM compnay.customer";
	static String showAllOrder = "SELECT * FROM compnay.orders";
	static String deleteCustomer = "DELETE FROM compnay.customer WHERE custId=?";
	static String checkredundentemail = "SELECT * FROM compnay.customer WHERE custEmail=?";
	static String checkredundentphone = "SELECT * FROM compnay.customer WHERE custPhone=?";
	static String insertCustomer = "INSERT INTO compnay.customer (custName,custEmail,custPhone,custPassword) values (?,?,?,?)";
	static String checkCustomer = "SELECT custName FROM compnay.customer WHERE custEmail=? and custPassword=?";
	static String getMenu = "SELECT * FROM compnay.menu";
	static String getCustId = "SELECT custId FROM compnay.customer WHERE custEmail=?";
	static String getFoodId = "SELECT * FROM compnay.menu WHERE foodId=?";
	static String insertCustomerOrder = "INSERT INTO compnay.orders (custId,foodId,quantity,totalPrice,foodName) VALUES (?,?,?,?,?)";
	static String showOrderForParticularId = "SELECT foodId,quantity,foodName FROM compnay.orders WHERE custId=?";
	static String deleteOrder = "DELETE FROM compnay.orders WHERE custId=? and foodId=?";
	static String showOrderForParticularId1 = "SELECT foodId,quantity,totalPrice,foodName FROM compnay.orders WHERE custId=?";
	
	
	

	static PreparedStatement delorder=null;
	static PreparedStatement delOr = null;
	static PreparedStatement showOrder = null;
	static PreparedStatement inOrder = null;
	static PreparedStatement adCheck = null;
	static PreparedStatement showallCust = null;
	static PreparedStatement showallor = null;
	static PreparedStatement delcust = null;
	static PreparedStatement checkemail = null;
	static PreparedStatement checkPhone = null;
	static PreparedStatement insertClient = null;
	static PreparedStatement checkcust = null;
	static PreparedStatement getOrder = null;
	static PreparedStatement getCuid = null;
	static PreparedStatement getFoId = null;
	static PreparedStatement getFullOrder = null;

	public DatabaseService(Connection con) {

		try {

			delorder=con.prepareStatement(showOrderForParticularId1);
			delOr = con.prepareStatement(deleteOrder);
			showOrder = con.prepareStatement(showOrderForParticularId);
			inOrder = con.prepareStatement(insertCustomerOrder);
			getFullOrder = con.prepareStatement(getFoodId);
			adCheck = con.prepareStatement(adminCheck);
			showallCust = con.prepareStatement(showCust);
			showallor = con.prepareStatement(showAllOrder);
			delcust = con.prepareStatement(deleteCustomer);
			checkemail = con.prepareStatement(checkredundentemail);
			checkPhone = con.prepareStatement(checkredundentphone);
			insertClient = con.prepareStatement(insertCustomer);
			checkcust = con.prepareStatement(checkCustomer);
			getOrder = con.prepareStatement(getMenu);
			getCuid = con.prepareStatement(getCustId);
			getFoId = con.prepareStatement(getFoodId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isChoiceCheck4(String choice) {
		isTrue = false;
		if (choice.matches("[1-3]")) {
			isTrue = true;
		}
		return isTrue;
	}

	public boolean isChoiceCheck(String choice) {
		isTrue = false;
		if (choice.matches("[1-3]")) {
			isTrue = true;
		}
		return isTrue;
	}

	public boolean isChoiceCheck1(String choice) {
		isTrue = false;
		if (choice.matches("[1-4]")) {
			isTrue = true;
		}
		return isTrue;
	}

	public boolean isAdminCheck(Admin admin) {
		isTrue = false;
		try {
			adCheck.setString(1, admin.getUsername());
			adCheck.setString(2, admin.getPassword());
			ResultSet rs = adCheck.executeQuery();
			if (rs.next()) {
				isTrue = true;
			} else {
				System.out.println("You are not Authorized");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return isTrue;
	}

	public void getAllCustomer() {
		try {
			ResultSet rs = showallCust.executeQuery();
			System.out.println("CustomerId\tCustomerName\tCustomerEmail\tCustomerPhone");
			while (rs.next()) {
				printCustomer(new Customer(rs.getInt("custId"), rs.getString("custName"), rs.getString("custEmail"),
						rs.getString("custPhone")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void getAllOrders() {
		try {
			ResultSet rs = showallor.executeQuery();
			while (rs.next()) {
				System.out.println("CustomerId\tFoodId\tQuantity\tTotalPrice\tFoodName");
				printAllOrders(new Orders(rs.getInt("custId"), rs.getInt("foodId"), rs.getInt("quantity"),
						rs.getInt("totalPrice"), rs.getString("foodName")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void deleteCustomer(int id) {
		try {
			delcust.setInt(1, id);
			int row = delcust.executeUpdate();
			if (row > 0) {
				System.out.println("Customer is removed Successfully!");
			} else {
				System.out.println("Failed to delete Customer Data");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean isChoiceCheck3(String id) {
		isTrue = false;
		if (id.matches("[0-9]*")) {
			isTrue = true;
		}
		return isTrue;
	}

	private void printCustomer(Customer cust) {
		System.out.println(cust.getCustId() + "    " + cust.getCustName() + "    " + cust.getCustEmail() + "    "
				+ cust.getCustPhone());

	}

	private void printAllOrders(Orders order) {
		System.out.println(order.getCustId() + "    " + order.getFoodId() + "    " + order.getQuantity() + "    "
				+ order.getTotalPrice() + "    " + order.getFoodName());
	}

	public boolean isNameCheck(String name) {
		isTrue = false;

		if (name.matches("[a-zA-Z]+")) {
			isTrue = true;
		} else {
			System.out.println("Name only contain Alphabets!");
		}

		return isTrue;
	}

	public boolean isEmailCheck(String email) {
		isTrue = false;
		if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			try {

				checkemail.setString(1, email);
				ResultSet rs = checkemail.executeQuery();
				if (rs.next()) {
					System.out.println(email + " already present in the Database!");
				} else {
					isTrue = true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("Invalid Format");
		}
		return isTrue;
	}

	public boolean isPhoneCheck(String phone) {
		isTrue = false;

		if (phone.matches("[0-9]{10}")) {

			try {
				checkPhone.setString(1, phone);
				ResultSet rs = checkPhone.executeQuery();
				if (rs.next()) {
					System.out.println(phone + " number already present in the Database!");
				} else {
					isTrue = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Phone number must be length of 10 and only contain Integer Values");
		}
		return isTrue;
	}

	public boolean isPasswordCheck(String password) {
		isTrue = false;
		if (password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
			isTrue = true;
		} else {
			System.out.println(
					"Password should be atleast 8 character long and atleast one uppercase and lowercase and Special character");
		}
		return isTrue;

	}

	public boolean insertCust(Customer cust) {
		isTrue = false;
		try {

			insertClient.setString(1, cust.getCustName());
			insertClient.setString(2, cust.getCustEmail());
			insertClient.setString(3, cust.getCustPhone());
			insertClient.setString(4, cust.getCustPassword());
			int rows = insertClient.executeUpdate();
			if (rows > 0) {
				isTrue = true;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isTrue;
	}

	public boolean getCustomer(String email, String password) {
		isTrue = false;
		try {
			checkcust.setString(1, email);
			checkcust.setString(2, password);
			ResultSet rs = checkcust.executeQuery();
			if (rs.next()) {
				isTrue = true;
				String str = rs.getString("custName");
				System.out.println("Welcome " + str + " to Food Mania");

			} else {
				System.out.println("You are not authorized");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isTrue;
	}

	public void getOrderMenu() {
		try {
			ResultSet rs = getOrder.executeQuery();
			System.out.println("FoodId\tFoodName\tPrice");
			while (rs.next()) {

				System.out.println(rs.getInt("foodId") + "\t" + rs.getString("foodName") + "\t" + rs.getInt("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isChoiceCheck5(String choice) {
		isTrue = false;
		if (choice.matches("[1-4]")) {
			isTrue = true;
		}
		return isTrue;
	}

	public boolean isFidCheck(String fid) {
		isTrue = false;
		if (fid.matches("[0-9]+")) {
			try {
				getFoId.setInt(1, Integer.parseInt(fid));
				ResultSet rs = getFoId.executeQuery();
				if (rs.next()) {
					isTrue = true;

				} else {
					System.out.println("Please Enter available foodId");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Only Integer Value acceptable!");
		}
		return isTrue;

	}

	public int getCustomerId(String email) {
		int id = 0;
		try {
			getCuid.setString(1, email);
			ResultSet rs = getCuid.executeQuery();
			if (rs.next()) {
				id = rs.getInt("custId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean checkQuantity(String quan) {
		isTrue = false;
		if (quan.matches("[0-9]+")) {
			isTrue = true;
		}
		return isTrue;
	}

	public boolean insertOrder(int cId, int fId, int quan) {
		isTrue = false;
		int price = 0;
		int totalamount = 0;
		try {
			getFullOrder.setInt(1, fId);
			ResultSet rs = getFullOrder.executeQuery();
			if (rs.next()) {
				String fName = rs.getString("foodName");
				price = rs.getInt("price");
				totalamount = price * quan;
				inOrder.setInt(1, cId);
				inOrder.setInt(2, fId);
				inOrder.setInt(3, quan);
				inOrder.setInt(4, totalamount);
				inOrder.setString(5, fName);
				int rows = inOrder.executeUpdate();
				if (rows > 0) {
					System.out.println("Your order has been added successfully for " + fName);
					isTrue = true;
				} else {
					System.out.println("Something went wrong!!!");
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return isTrue;
	}

	public void showAllOrderForCustId(int id) {

		try {
			showOrder.setInt(1, id);
			ResultSet rs = showOrder.executeQuery();
			System.out.println("FoodId\tQuantity\tFoodName");
			while (rs.next()) {
				System.out.println(rs.getInt("foodId") + "\t" +  rs.getInt("quantity")
						+ "\t" + rs.getString("foodName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleOrder(int id, String fid) {

		try {
			delOr.setInt(1, id);
			delOr.setInt(2, Integer.parseInt(fid));
			int rows = delOr.executeUpdate();
			if (rows > 0) {
				System.out.println("Your Order Successfully cancel for " + fid);
			} else {
				System.out.println("You have not Place an order for this Food Id:- " + fid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showAllOrderForCustId1(int id) {

		try {
			showOrder.setInt(1, id);
			ResultSet rs = showOrder.executeQuery();
			System.out.println("FoodId\tQuantity\tFoodName");
			while (rs.next()) {
				System.out.println(rs.getInt("foodId") + "\t" + rs.getInt("quantity") +"\t" + rs.getString("foodName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void totalBill(int id){
		int totalPrice=0;
		try{
			delorder.setInt(1, id);
			ResultSet rs=delorder.executeQuery();
			while(rs.next()){
				totalPrice=totalPrice+rs.getInt("totalPrice");
			}
			System.out.println("Total Amount for your Order is:- "+totalPrice);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
