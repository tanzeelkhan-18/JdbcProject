package com.jdbcproject.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbcproject.entity.Address;
import com.jdbcproject.entity.Employee;
import com.jdbcproject.exceptions.DuplicatePhoneNumberException;

public interface EmployeeDAO {
	public boolean addEmp(Employee eb, Address localAb, Address permAb) throws SQLException, DuplicatePhoneNumberException;
	public boolean editEmp(Employee eb, Address localAb, Address permAb) throws SQLException;
	public boolean deleteEmp(int empid) throws SQLException;
	public ArrayList<Employee> findAlleb() throws SQLException;
	public Employee findbyEmpId(int empId) throws SQLException;
}