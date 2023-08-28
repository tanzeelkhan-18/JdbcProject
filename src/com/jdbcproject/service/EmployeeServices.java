package com.jdbcproject.service;

import java.sql.SQLException;

public interface EmployeeServices {
	public void  addEmp() throws SQLException;
	public void  updateEmp() throws SQLException;
	public void  deleteEmp() throws SQLException;
	public void findEmpById() throws SQLException;
	public void findAllEmp() throws SQLException;
}
