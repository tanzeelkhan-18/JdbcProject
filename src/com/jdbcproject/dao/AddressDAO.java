package com.jdbcproject.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbcproject.entity.Address;

public interface AddressDAO {
	public boolean AddAddr(Address ab) throws SQLException;
	public boolean EditAddr(Address ab) throws SQLException;
	public boolean DeleteAddr(int addrid) throws SQLException;
	public ArrayList<Address> findalladdr() throws SQLException;
	public Address findbyAddrId(int addrId) throws SQLException;
	public ArrayList<Address> findbyEmpId(int empId) throws SQLException;
}
