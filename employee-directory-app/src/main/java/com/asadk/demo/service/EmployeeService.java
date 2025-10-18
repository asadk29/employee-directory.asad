package com.asadk.demo.service;

import java.util.List;

import com.asadk.demo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findEmpById(int empId);
	
	public Employee save(Employee employee);
	
	public void delete(int empId);
	
	public List<Employee> sortByFirstName();
}
