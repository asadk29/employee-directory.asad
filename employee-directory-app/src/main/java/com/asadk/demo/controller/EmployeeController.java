package com.asadk.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asadk.demo.entity.Employee;
import com.asadk.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService service;
	
	@Autowired
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		
		//get list of employees from DB
		List<Employee> employees = service.findAll();
		
		//add list to model
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormToAdd")
	public String getForm(Model model) {
		
		Employee employee = new Employee();
		
		model.addAttribute("employee", employee);
		
		return"employees/employee-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee) {
		
		service.save(employee);
		
		//using redirect to call the /list method of this same controller
        return "redirect:/employees/list";
	}
	
	@GetMapping("/sortByFirstName")
	public String sortByFirstName(Model model){
		
		List<Employee> sortedEmployees = service.sortByFirstName();
		
		model.addAttribute("employees", sortedEmployees);
		
		return "employees/list-employees";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateForm(@RequestParam("employeeId") int empId, Model model) {
		Employee employee = service.findEmpById(empId);
		
		model.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int empId) {
		
		service.delete(empId);
		
		return "redirect:/employees/list";
	}
	
}
