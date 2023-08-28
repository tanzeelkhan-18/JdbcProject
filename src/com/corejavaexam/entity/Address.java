package com.corejavaexam.entity;

public class Address {
	//Column names as member data from Address Table
	private int addrId;
	private int EmpId;
	private String city;
	private String country;
	private int pin;
	
	//Parameterized Constructor
	public Address(int addrId, int empId, String city, String country, int pin){
		super();
		this.addrId = addrId;
		EmpId = empId;
		this.city = city;
		this.country = country;
		this.pin = pin;
	}

	//Default Constructor
	public Address() {
		super();
	}
	
	//getters and setters
	public int getAddrId() {
		return addrId;
	}

	public void setAddrId(int addrId) {
		this.addrId = addrId;
	}

	public int getEmpId() {
		return EmpId;
	}

	public void setEmpId(int empId) {
		EmpId = empId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
}
