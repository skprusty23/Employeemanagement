package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.Employee;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/employeeManagement")
public class EmployeeManagementController {
	static List<Employee> tempEmployees = new ArrayList<Employee>();

	@RequestMapping(value="/employees",produces="application/json",method=RequestMethod.GET)
	public List<Employee> getemployee(){
		
		if(tempEmployees.size()==0)
			createEmployee();
		return tempEmployees;	
		
		
	}
	
	
	public List<Employee> createEmployee()
	{
		Employee emp1 = new Employee();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setEmpId("1");
		emp1.setSalary(3000);

		Employee emp2 = new Employee();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setEmpId("2");
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}
	
	@DeleteMapping(path = { "deleteEmployee/{id}" })
	public Employee delete(@PathVariable("id") String id) {
		Employee deletedEmp = null;
		System.out.println("TempEmployeeslength=="+tempEmployees.size());
		for (Employee emp : tempEmployees) {
			if (emp.getEmpId().equals(id)) {
				tempEmployees.remove(emp);
				deletedEmp = emp;
				System.out.println("TempEmployeeslength=="+tempEmployees.size());
				break;
			}
		}
		return deletedEmp;
	}

	@PostMapping(value="/createEmployee")
	public Employee create(@RequestBody Employee user) {
		tempEmployees.add(user);
		System.out.println(tempEmployees);
		return user;
	}
	
	
}
