package com.corejavaexam.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPool {
	private static Connection conn;
    //Establishing connection with the database
	static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/corejavaexam", "root", "Tanzeelkhan@123");
                System.out.println("Connection Success.");
                conn.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Cannot Connect to Database.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection Failed.");
        }
    }
    
	//Handy Methods to create and run queries
    public PreparedStatement createPST(String sql) throws SQLException{
    	PreparedStatement pst = conn.prepareStatement(sql);
    	return pst;
    }
    
    public int update(PreparedStatement pst) throws SQLException{
    	return pst.executeUpdate();
    }
    public ResultSet query(PreparedStatement pst) throws SQLException{
    	return pst.executeQuery();
    }
    
    //Methods to commit to database and roll back changes in case of failures 
    public void commitQuery() throws SQLException {
		conn.commit();
    }
    public void rollbackQuery() throws SQLException {
    	conn.rollback();
    }
}

