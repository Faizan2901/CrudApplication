package com.faizan.com.crud.app;
import java.sql.*;
public class ConnectionUtil {
	
	static Connection con=null;
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/compnay ";
	private static final String USER_NAME = "root";
	private static final String USER_PASSWORD = "P@ssw0rd@123";
	
	
	public static Connection getConnection(){
		
		if(con==null){
			try{
				Class.forName(DRIVER_PATH);
				con=DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return con;
		
	}

}
