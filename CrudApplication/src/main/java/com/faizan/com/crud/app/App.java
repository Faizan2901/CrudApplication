package com.faizan.com.crud.app;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

	static Connection con = ConnectionUtil.getConnection();
	public static Scanner sc = new Scanner(System.in);
	static DatabaseService ds = new DatabaseService(con);
	static boolean isRun = true;

	public static void main(String[] args) {

		while (isRun) {
			System.out.println("\n1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit from System");
			String choice = null;
			boolean isTrue = false;
			while (!isTrue) {

				System.out.print("Enter the choice:- ");
				choice = sc.next();
				isTrue = ds.isChoiceCheck(choice);
				if (isTrue) {
					break;
				} else {
					System.out.println("Please enter choice from above description!!");
				}

			}
			switch (choice) {
			case "1":
				System.out.println("Please Give Admin Credentials: ");
				System.out.println("Enter Username:- ");
				String uname = sc.next();
				System.out.println("Enter Password:- ");
				String upass = sc.next();
				boolean isTrue1 = ds.isAdminCheck(new Admin(uname, upass));
				if (isTrue1) {
					boolean isRunning = false;
					while (!isRunning) {

						System.out.println("\nWelcome to Admin Page");
						System.out.println("1. Show all Customer");
						System.out.println("2. Show all Order");
						System.out.println("3. Delete Customer");
						System.out.println("4. Exit from Admin Page");

						String choice1 = null;
						boolean isTrue2 = false;
						while (!isTrue2) {

							System.out.print("Enter the choice:- ");
							choice1 = sc.next();
							isTrue2 = ds.isChoiceCheck1(choice1);
							if (isTrue2) {
								break;
							} else {
								System.out.println("Please enter choice from above description!!");
							}

						}

						switch (choice1) {
						case "1":
							ds.getAllCustomer();
							break;
						case "2":
							ds.getAllOrders();
							break;
						case "3":
							ds.getAllCustomer();
							String id = null;
							boolean isTrue3 = false;
							while (!isTrue3) {

								System.out.print("Which Customer do want to delete:- ");
								id = sc.next();
								isTrue3 = ds.isChoiceCheck3(id);
								if (isTrue3) {
									break;
								} else {
									System.out.println("Please enter only Integer Value!!");
								}

							}
							ds.deleteCustomer(Integer.parseInt(id));
							break;
						case "4":
							isRunning = true;
							isTrue = true;
							System.out.println("Thank you Admin");
							break;
						default:
							break;
						}
					}

				}

				break;
			case "2":
				System.out.println("\n======Welcome to Food Mania======");
				System.out.println("1. Sign Up");
				System.out.println("2. Sign In");
				System.out.println("3. Exit from Customer Page");
				String choice2 = null;
				boolean isTrue4 = false;
				while (!isTrue4) {

					System.out.print("Enter the choice:- ");
					choice2 = sc.next();
					isTrue4 = ds.isChoiceCheck4(choice2);
					if (isTrue4) {
						break;
					} else {
						System.out.println("Please enter choice from above description!!");
					}

				}
				switch (choice2) {
				case "1":
					System.out.println("===Enter Your Information:=== ");
					String name = null;
					boolean isCorrect = false;
					while (!isCorrect) {
						System.out.print("Enter Your Name:- ");
						name = sc.next();
						isCorrect = ds.isNameCheck(name);
					}
					String email = null;
					isCorrect = false;
					while (!isCorrect) {
						System.out.print("Enter Your Email:- ");
						email = sc.next();
						isCorrect = ds.isEmailCheck(email);
					}
					String phone = null;
					isCorrect = false;
					while (!isCorrect) {
						System.out.print("Enter Your Phone:- ");
						phone = sc.next();
						isCorrect = ds.isPhoneCheck(phone);
					}
					String password = null;
					isCorrect = false;
					while (!isCorrect) {
						System.out.print("Enter Password:- ");
						password = sc.next();
						isCorrect = ds.isPasswordCheck(password);
					}
					ds.insertCust(new Customer(name, email, phone, password));
					break;
				case "2":
					isCorrect = false;
					System.out.println("Please Provide Your Credentials: ");
					System.out.println("Enter Email:- ");
					String cname = sc.next();
					System.out.println("Enter Password:- ");
					String cpass = sc.next();
					isCorrect = ds.getCustomer(cname, cpass);

					if (isCorrect) {
						ds.getOrderMenu();
						boolean isRunning = false;
						while (!isRunning) {

							System.out.println("1. Place an Order");
							System.out.println("2. Cancel Order");
							System.out.println("3. Show Bill");
							System.out.println("4. Exit");
							String choice3 = null;
							boolean isTrue5 = false;
							while (!isTrue5) {

								System.out.print("Enter the choice:- ");
								choice3 = sc.next();
								isTrue5 = ds.isChoiceCheck5(choice3);
								if (isTrue5) {
									break;
								} else {
									System.out.println("Please enter choice from above description!!");
								}

							}
							switch (choice3) {
							case "1":
								isCorrect = false;
								String fid = null;
								boolean isTrue6 = false;
								while (!isTrue6) {
									System.out.print("Enter Food Id:- ");
									fid = sc.next();
									isCorrect = ds.isFidCheck(fid);
									if (isCorrect) {
										String quan = null;
										boolean isTrue7 = false;
										while (!isTrue7) {
											System.out.print("Enter Quantity:- ");
											quan = sc.next();
											isTrue7 = ds.checkQuantity(quan);
										}
										int id0 = ds.getCustomerId(cname);
										isTrue6 = ds.insertOrder(id0, Integer.parseInt(fid), Integer.parseInt(quan));
										if (isTrue6) {
											System.out.println("Do you want to add something(Y/N) ");
											String status = sc.next();
											if (status.equals("Y")) {
												isTrue6 = false;
											} else {
												isTrue6 = true;
											}

										}
									}

								}

								break;
							case "2":
								int id=ds.getCustomerId(cname);
								ds.showAllOrderForCustId(id);
								System.out.println("Which Food you want to cancel?");
								String like=null;
								isCorrect=false;
								while(!isCorrect){
									System.out.print("Enter FoodId from your above Orderlist:- ");
									like=sc.next();
									isCorrect=ds.isChoiceCheck3(like);
									if (isCorrect) {
										break;
									} else {
										System.out.println("Please enter only Integer Value!!");
									}
									
									
								}
								ds.deleOrder(id, like);
								
								break;
							case "3":
								id=ds.getCustomerId(cname);
								ds.showAllOrderForCustId1(id);
								ds.totalBill(id);
								break;
							case "4":
								isTrue = true;
								isRunning = true;
								System.out.println("Thank You visit again");
								break;
							default:
								break;
							}
						}
					}
					break;
				case "3":
					isTrue = true;

					break;
				default:
					break;
				}
				break;
			case "3":
				isRun = false;
				System.out.println("Thank you");
				break;
			default:
				break;
			}

		}

	}
}