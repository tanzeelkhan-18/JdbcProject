package com.corejavaexam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.corejavaexam.entity.Address;
import com.corejavaexam.entity.Employee;
import com.corejavaexam.exceptions.DuplicatePhoneNumberException;
import com.corejavaexam.utility.ConnectionPool;

public class EmployeeDAOImpl implements EmployeeDAO {

	ConnectionPool connect = new ConnectionPool();
	PreparedStatement pst;
	PreparedStatement pst1;
	PreparedStatement pst2;
	ResultSet rs;

	@Override
	public boolean addEmp(Employee eb, Address localAb, Address permAb) throws SQLException, DuplicatePhoneNumberException {
		int x=0;
		String query1 = "select count(*) from emp where phone=?";
		pst = connect.createPST(query1);
		pst.setLong(1, eb.getPhone());
		rs = pst.executeQuery();
		Integer dup = 0;
		rs.next();
		dup = rs.getInt(1);
		// dup will be updated with the matching column's Id so it will make the if condition false
		if(dup==0) {
			String sql = "Insert into emp(name,phone) values(?,?)";
			pst = connect.createPST(sql);
			pst.setString(1, eb.getName());
			pst.setLong(2, eb.getPhone());
			connect.update(pst);

			PreparedStatement ps = connect.createPST("select max(empId) from emp");
			ResultSet rsx = ps.executeQuery();
			int maxId = 0;
			if(rsx.next()) {
				maxId = rsx.getInt(1);
			}

			// Inserting local Address in the table Address
			String sql1 = "insert into address(city,country,pin,empId) values(?,?,?,?)";
			pst1 = connect.createPST(sql1);
			pst1.setString(1, localAb.getCity());
			pst1.setString(2, localAb.getCountry());
			pst1.setInt(3, localAb.getPin());
			pst1.setInt(4,  maxId);
			connect.update(pst1);

			// Inserting Permanent Address in the table Address
			String sql2 = "insert into address(city,country,pin,empId) values(?,?,?,?)";
			pst2 = connect.createPST(sql2);
			pst2.setString(1, permAb.getCity());
			pst2.setString(2, permAb.getCountry());
			pst2.setInt(3, permAb.getPin());
			pst2.setInt(4, maxId);
			connect.update(pst2);

			//Committing changes to the database
			try {
				connect.commitQuery();
				x++;
			}catch(SQLException e) {
				connect.rollbackQuery();
				e.printStackTrace();
			}
		}
		else {
			//InCase of duplicate found, this exception would be raised.
			throw new DuplicatePhoneNumberException("Duplicate Phone Number.");
		}
		if(x!=0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean editEmp(Employee eb, Address localAb, Address permAb) throws SQLException {
		int x = 0;

		String sql = "Update emp set name=?, phone=? where empId=?";
		pst = connect.createPST(sql);
		pst.setString(1, eb.getName());
		pst.setLong(2, eb.getPhone());
		pst.setInt(3, eb.getEmpId());
		connect.update(pst);

		//This will find the second occurrence of same empId in address table which will give us the Local Address
		PreparedStatement ps = connect.createPST("select * from address where empId=6 and addrId=(select min(addrId) from address)");
		ResultSet rsx = ps.executeQuery();
		int addrId = 0;
		if(rsx.next()) {
			addrId = rsx.getInt("addrId");
		}

		String sql1 = "Update address set empId=?,city=?,country=?,pin=? where empId=? and addrId=?";
		pst1 = connect.createPST(sql1);
		pst1.setInt(1, eb.getEmpId());
		pst1.setString(2, localAb.getCity());
		pst1.setString(3, localAb.getCountry());
		pst1.setInt(4, localAb.getPin());
		pst1.setInt(5, eb.getEmpId());
		pst1.setInt(6, addrId);
		connect.update(pst1);

		//This will find the second occurrence of same empId in address table which will give us the Permanent Address
		PreparedStatement ps1 = connect.createPST("select * from address where empId=6 and addrId=(select max(addrId) from address)");
		ResultSet rsp = ps1.executeQuery();
		if(rsp.next()) {
			addrId = rsp.getInt("addrId");
		}

		String sql2 = "Update address set empId=?,city=?,country=?,pin=? where empId=? and addrId=?";
		pst2 = connect.createPST(sql2);
		pst2.setInt(1, eb.getEmpId());
		pst2.setString(2, permAb.getCity());
		pst2.setString(3, permAb.getCountry());
		pst2.setInt(4, permAb.getPin());
		pst2.setInt(5, eb.getEmpId());
		pst2.setInt(6, addrId);
		connect.update(pst2);

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
	public boolean deleteEmp(int empid) throws SQLException {
		int x = 0;

		// We have to delete from the Address Table first as deleting from Emp table will raise an error
		String sql1 = "delete from address where empId=?";
		pst1 = connect.createPST(sql1);
		pst1.setInt(1, empid);
		connect.update(pst1);

		// After reference of EmpId has been deleted we can delete from Emp table
		String sql = "Delete from emp where empId=?";
		pst = connect.createPST(sql);
		pst.setInt(1, empid);
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
	public ArrayList<Employee> findAlleb() throws SQLException{

		String sql = "Select * from emp";
		pst = connect.createPST(sql);
		rs = connect.query(pst);
		ArrayList<Employee> el = new ArrayList<Employee>();
		while(rs.next()) {
			Employee eb = new Employee();
			eb.setEmpId(rs.getInt("empId"));
			eb.setName(rs.getString("name"));
			eb.setPhone(rs.getLong("phone"));
			el.add(eb);
		}
		return el;
	}

	@Override
	public Employee findbyEmpId(int empId) throws SQLException {

		String sql = "Select * from emp where empId=?";
		pst = connect.createPST(sql);
		pst.setInt(1, empId);
		rs = connect.query(pst);

		if(rs.next()) {
			Employee eb = new Employee();
			eb.setEmpId(rs.getInt("empId"));
			eb.setName(rs.getString("name"));
			eb.setPhone(rs.getLong("phone"));
			return eb;
		}
		return null;
	}

	public static void main(String[] args) {
		Employee emp1 = new Employee();
		emp1.setName("Abdul Sami KHan");
		emp1.setPhone(Long.parseLong("7440739384"));

		System.out.println(emp1.getName());
	}
}
