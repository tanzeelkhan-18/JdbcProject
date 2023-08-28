package com.corejavaexam.entity;

public class Employee {
	//Column names as member data from EMP Table
	private int empId;
	private String name;
	private long phone;
	
	//Parameterized Constructor
	public Employee(int empId, String name, long phone) {
		super();
		this.empId = empId;
		this.name = name;
		this.phone = phone;
	}
	
	//Default Constructor
	public Employee() {
	}
	
	//getters and setters
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	
}
