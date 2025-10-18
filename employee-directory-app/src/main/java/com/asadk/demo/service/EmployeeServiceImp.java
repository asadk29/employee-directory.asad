package com.asadk.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.asadk.demo.dao.EmployeeRepository;
import com.asadk.demo.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImp implements EmployeeService{
	
	private EmployeeRepository repository;
	
	@Autowired
	public EmployeeServiceImp(EmployeeRepository repository) {
		this.repository = repository;
	}

	public List<Employee> findAll(){
		
		List<Employee> employees = repository.findAll();
		
		return employees;
	}
	
	public Employee findEmpById(int empId) {
		
		Optional<Employee> result = repository.findById(empId);
		Employee employee = null;
		
		if(result.isPresent()) {
			employee = result.get();
		}else {
			throw new RuntimeException("Employee Id was not found: " +empId);
		}
		
		return employee;
	}
	
	public Employee save(Employee employee) {
		
		Employee dbEmployee = repository.save(employee);
		
		return dbEmployee;
		
	}
	
	public void delete(int empId) {
		
		repository.deleteById(empId);
		
	}

	public List<Employee> sortByFirstName(){
		
		List<Employee> sortedEmployees = repository.findAllByOrderByFirstNameAsc();
		
		return sortedEmployees;
		
	}
	
}
