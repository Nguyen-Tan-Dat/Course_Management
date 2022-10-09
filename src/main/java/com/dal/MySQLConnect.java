package com.dal;

import java.sql.*;

public class MySQLConnect {
    String Host = "localhost";
    String UserName = "root";
    String Password = "";
    String Database = "School";
	
    Connection conn = null;
    Statement statement = null;
    ResultSet result = null;
	
    public MySQLConnect(String Host, String UserName, String Password, String Database) {
		this.Host = Host;
		this.UserName = UserName;
		this.Password = Password;
		this.Database = Database;
	}
	
	protected void testDriver () throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception ex){
			throw new Exception("Driver not found");
		}
	}
	
	protected Connection getConnection() throws Exception{
		//neu chua khoi tao 'conn'
		if(this.conn == null) {
			//kiem tra driver
			testDriver();
			var database = "School";
			var user = "root";
			var password = "";
			try {
				this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
			} catch (Exception ex) {
				throw new Exception("Cannot connect to database");
			}
		}
		//tra lai connection
		return this.conn;
	}
	
	protected Statement getStatement() throws Exception{
		//kiem tra statement
		if(this.statement == null? true:this.statement.isClosed()) {
			this.statement = this.getConnection().createStatement();
		}
		return this.statement;
	}
	
	public ResultSet executeQuery(String Query) throws Exception{
		
		try {
			this.result = getStatement().executeQuery(Query);
		} catch(Exception ex){
			throw new Exception("Cannot execute query: " + Query);
		}
		
		return this.result;
	}
	
	public int executeUpdate(String Query) throws Exception {
		//luu trang thai khi thuc thi query
		int res = Integer.MIN_VALUE;
		try {
			res = getStatement().executeUpdate(Query);
		} catch (Exception ex) {
			throw new Exception("Cannot excute query: " + Query);
		} finally {this.Close();}
		
		return res;
	}
	
	public void Close() throws SQLException {
		if(this.result != null && !this.result.isClosed()) {
			this.result.close();
			this.result = null;
		}
		
		if(this.statement != null && !this.statement.isClosed()) {
			statement.close();
			statement = null;
		}
		
		if(this.conn != null && !this.conn.isClosed()) {
			this.conn.close();
			this.conn = null;
		}
	}
}
