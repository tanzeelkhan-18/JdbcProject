package com.jdbcproject.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.jdbcproject.dao.AddressDAO;
import com.jdbcproject.dao.AddressDAOImpl;
import com.jdbcproject.dao.EmployeeDAO;
import com.jdbcproject.dao.EmployeeDAOImpl;
import com.jdbcproject.entity.Address;
import com.jdbcproject.entity.Employee;
import com.jdbcproject.exceptions.DuplicatePhoneNumberException;

public class EmployeeServicesImpl implements EmployeeServices {
	
	EmployeeDAO ed = new EmployeeDAOImpl();
	Scanner scObj = new Scanner(System.in);
	
	@Override
	public void addEmp(){
		System.out.println("Insert in Emp");
		scObj.nextLine();
		System.out.print("Enter Employee Name: ");
		String eName = scObj.nextLine();
		System.out.print("Enter Employee PhoneNo.: ");
		long ePhone = scObj.nextLong();
		scObj.nextLine();
		System.out.println("Enter Local Address");
		System.out.print("Enter City: ");
		String lCity = scObj.nextLine();
		System.out.print("Enter Country: ");
		String lCountry = scObj.nextLine();
		System.out.println("Enter Pin: ");
		int lPin = scObj.nextInt();
		scObj.nextLine();
		System.out.println("Enter Permanent Address");
		System.out.print("Enter City: ");
		String pCity = scObj.nextLine();
		System.out.print("Enter Country: ");
		String pCountry = scObj.nextLine();
		System.out.println("Enter Pin: ");
		int pPin = scObj.nextInt();
		
		Employee eb = new Employee();
		
		eb.setName(eName);
		eb.setPhone(ePhone);
		
		Address lAddr = new Address();
		lAddr.setCity(lCity);
		lAddr.setCountry(lCountry);
		lAddr.setPin(lPin);
		
		Address pAddr = new Address();
		pAddr.setCity(pCity);
		pAddr.setCountry(pCountry);
		pAddr.setPin(pPin);
		
		try {
			boolean flag=false;
			try {
				flag = ed.addEmp(eb, lAddr, pAddr);
			} catch(DuplicatePhoneNumberException e) {
				e.printStackTrace();
			}
			if(flag) {
				System.out.println("Insertion Successful");
			}
			else {
				System.out.println("Insertion Failed: Check for Errors.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateEmp() {
		System.out.println("Edit in Emp");
		System.out.print("Enter Employee Id to Update: ");
		int empId = scObj.nextInt();
		scObj.nextLine();
		System.out.print("Enter Updated Employee Name: ");
		String eName = scObj.nextLine();
		System.out.print("Enter Updated Employee PhoneNo.: ");
		long ePhone = scObj.nextLong();
		scObj.nextLine();
		System.out.println("Enter Updated Local Address");
		System.out.print("Enter City: ");
		String lCity = scObj.nextLine();
		System.out.print("Enter Country: ");
		String lCountry = scObj.nextLine();
		System.out.println("Enter Pin: ");
		int lPin = scObj.nextInt();
		scObj.nextLine();
		System.out.println("Enter Updated Permanent Address");
		System.out.print("Enter City: ");
		String pCity = scObj.nextLine();
		System.out.print("Enter Country: ");
		String pCountry = scObj.nextLine();
		System.out.println("Enter Pin: ");
		int pPin = scObj.nextInt();
		
		Employee eb = new Employee();
		eb.setEmpId(empId);
		eb.setName(eName);
		eb.setPhone(ePhone);
		
		Address lAddr = new Address();
		lAddr.setCity(lCity);
		lAddr.setCountry(lCountry);
		lAddr.setPin(lPin);
		
		Address pAddr = new Address();
		pAddr.setCity(pCity);
		pAddr.setCountry(pCountry);
		pAddr.setPin(pPin);
		
		try {
			boolean flag = ed.editEmp(eb, lAddr, pAddr);
			if(flag) {
				System.out.println("Edited Succesfully");
			}
			else {
				System.out.println("Failed: Check for Errors.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmp() {
		System.out.println("Delete from Emp");
		System.out.print("Enter EmpId to Delete: ");
		int empId = scObj.nextInt();
		
		try {
			boolean flag = ed.deleteEmp(empId);
			if(flag) {
				System.out.println("Deleted Successfully.");
			}
			else {
				System.out.println("Failed: Check for Errors.");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void findEmpById() {
		System.out.println("Find By ID");
		System.out.print("Enter Employee ID: ");
		int id = scObj.nextInt();
		try {
			Employee eb = ed.findbyEmpId(id);
			if(eb!=null) {
				System.out.println("Employee Details");
				System.out.println("EmpId: " + eb.getEmpId());
				System.out.println("Name: " + eb.getName());
				System.out.println("Phone No.: "+ eb.getPhone());
				AddressDAO ad = new AddressDAOImpl();
				//This will print the corresponding city,country and pin from the Address table using EmpId
				ArrayList<Address> listaddr = ad.findbyEmpId(id);
				for(Address abx : listaddr) {
					System.out.println("\nCity: " + abx.getCity());
					System.out.println("Country: " + abx.getCountry());
					System.out.println("Pin: " + abx.getPin());
				}
			}
			else {
				System.out.println("Employee Not Found.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void findAllEmp() {
		System.out.println("\nEmp");
		
		try {
			ArrayList<Employee> listemp = ed.findAlleb();
			for(Employee ebx : listemp) {
				System.out.println("EmpId: " + ebx.getEmpId());
				System.out.println("Name: " + ebx.getName());
				System.out.println("Phone No.: "+ ebx.getPhone());
				AddressDAO ad = new AddressDAOImpl();
				//This will print the corresponding city,country and pin from the Address table using EmpId
				ArrayList<Address> listaddr = ad.findbyEmpId(ebx.getEmpId());
				for(Address abx : listaddr) {
					System.out.println("City: " + abx.getCity());
					System.out.println("Country: " + abx.getCountry());
					System.out.println("Pin: " + abx.getPin());
				}
				System.out.println();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void driverCode() {
		
		boolean flag = true;
		while(flag){
			System.out.println("----------Employee Data-----------");
			System.out.println("Enter 1 to view Emp Table");
			System.out.println("Enter 2 to Insert Row in Emp Table");
			System.out.println("Enter 3 to Edit Row in Emp Table");
			System.out.println("Enter 4 to Delete Row from Emp Table");
			System.out.println("Enter 0 to Exit.");
			System.out.print("Enter your Choice: ");
			int choice = scObj.nextInt();
			switch(choice) {
			case 1:
				this.findAllEmp();
				break;
			case 2:
				this.addEmp();
				break;
			case 3:
				this.updateEmp();
				break;
			case 4:
				this.deleteEmp();
				break;
			case 0:
				flag = false;
				System.exit(0);
				break;
			default:
				System.out.println("Please Enter a Valid Choice.");
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
