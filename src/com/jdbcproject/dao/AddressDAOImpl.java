package com.jdbcproject.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbcproject.entity.Address;
import com.jdbcproject.utility.ConnectionPool;

public class AddressDAOImpl implements AddressDAO{

	ConnectionPool connect = new ConnectionPool();
	PreparedStatement pst;
	ResultSet rs;
	
	@Override
	public boolean AddAddr(Address ab) throws SQLException{
		int x = 0;
		
		String sql = "Insert into address(empId,city,country,pin) values(?,?,?,?)";
		pst = connect.createPST(sql);
		pst.setInt(1, ab.getEmpId());
		pst.setString(2, ab.getCity());
		pst.setString(3, ab.getCountry());
		pst.setInt(4, ab.getPin());
		connect.update(pst);
		
		//Committing changes to the database
		try {
			connect.commitQuery();
			x++;
		}catch(SQLException e) {
			connect.rollbackQuery();
			e.printStackTrace();
		}
		
		if(x!=0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean EditAddr(Address ab) throws SQLException{
		int x = 0;
		
		String sql = "Update address set empId=?, city=?, country=?, pin=? where addrId=?";
		pst = connect.createPST(sql);
		pst.setInt(1, ab.getEmpId());
		pst.setString(2, ab.getCity());
		pst.setString(3, ab.getCountry());
		pst.setInt(4, ab.getPin());
		pst.setInt(5, ab.getAddrId());
		connect.update(pst);
		
		//Committing changes to the database
		try {
			connect.commitQuery();
			x++;
		}catch(SQLException e) {
			connect.rollbackQuery();
			e.printStackTrace();
		}
		
		if(x!=0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean DeleteAddr(int addrid) throws SQLException{
		int x = 0;
		
		String sql = "Delete from address where addrId=?";
		pst = connect.createPST(sql);
		pst.setInt(1, addrid);
		connect.update(pst);
		
		//Committing changes to the database
		try {
			connect.commitQuery();
			x++;
		}catch(SQLException e) {
			connect.rollbackQuery();
			e.printStackTrace();
		}
		
		if(x!=0) {
			return true;
		}
		else {
			return true;
		}
	}
	
	@Override
	public ArrayList<Address> findalladdr() throws SQLException{
		
		String sql = "select * from address";
		pst = connect.createPST(sql);
		rs = connect.query(pst);		
		ArrayList<Address> al = new ArrayList<Address>();
		while(rs.next()) {
			Address ab = new Address();
			ab.setAddrId(rs.getInt("addrId"));
			ab.setEmpId(rs.getInt("empId"));
			ab.setCity(rs.getString("city"));
			ab.setCountry(rs.getString("country"));
			ab.setPin(rs.getInt("pin"));
			al.add(ab);
		}
		return al;
	}
	
	@Override
	public Address findbyAddrId(int addrId) throws SQLException{
		
		String sql = "Select * from address where addrId=?";
		pst = connect.createPST(sql);
		pst.setInt(1, addrId);
		rs = connect.query(pst);
		
		if(rs.next()) {
			Address ab = new Address();
			ab.setAddrId(rs.getInt("addrId"));
			ab.setEmpId(rs.getInt("empId"));
			ab.setCity(rs.getString("city"));
			ab.setCountry(rs.getString("country"));
			ab.setPin(rs.getInt("pin"));
			return ab;
		}
		return null;
	}
	
	@Override
	public ArrayList<Address> findbyEmpId(int empId) throws SQLException{
		
		String sql = "Select * from address where empId=?";
		pst = connect.createPST(sql);
		pst.setInt(1, empId);
		rs = connect.query(pst);
		ArrayList<Address> al = new ArrayList<Address>();
		while(rs.next()) {
			Address ab = new Address();
			ab.setAddrId(rs.getInt("addrId"));
			ab.setEmpId(rs.getInt("empId"));
			ab.setCity(rs.getString("city"));
			ab.setCountry(rs.getString("country"));
			ab.setPin(rs.getInt("pin"));
			al.add(ab);
		}
		return al;
	}
	
	public static void main(String[] args) {
	}
}
