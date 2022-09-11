package com.faizan.com.crud.app;

public class Validation {

	static boolean isTrue = false;

	public static boolean isChoiceCheck(String choice) {
		isTrue = false;
		if (choice.matches("[1-3]")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isNameCheck(String name) {
		isTrue = false;

		if (name.matches("[a-zA-Z]+")) {
			isTrue = true;
		} else {
			System.out.println("Name only contain Alphabets!");
		}

		return isTrue;
	}

	public static boolean isChoiceCheck1(String choice) {
		isTrue = false;
		if (choice.matches("[1-4]")) {
			isTrue = true;
		}
		return isTrue;
	}

	public static boolean isChoiceCheck2(String id) {
		isTrue = false;
		if (id.matches("[0-9]*")) {
			isTrue = true;
		}
		return isTrue;
	}
	
	public static boolean isChoiceCheck3(String choice) {
		isTrue = false;
		if (choice.matches("[1-2]")) {
			isTrue = true;
		}
		return isTrue;
	}


	public static boolean checkQuantity(String quan) {
		isTrue = false;
		if (quan.matches("[1-9]+")) {
			isTrue = true;
		} else {
			System.out.println("Please enter valid Qauntity(Number)!");
		}
		return isTrue;
	}

	public static boolean isPasswordCheck(String password) {
		isTrue = false;
		if (password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
			isTrue = true;
		} else {
			System.out.println(
					"Password should be atleast 8 character long and atleast one uppercase and lowercase and Special character");
		}
		return isTrue;

	}
	
}
