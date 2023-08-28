package com.corejavaexam.controller;

import java.sql.SQLException;

import com.corejavaexam.service.EmployeeServicesImpl;

public class EmployeeController {
	public static void main(String[] args) throws SQLException {
		EmployeeServicesImpl service = new EmployeeServicesImpl();
		System.out.println("\n-----------------------------------\n");
		System.out.println("--------Java Core Exam------------\n");
		System.out.println("----------------------------------\n");
		service.driverCode();
	}
}
