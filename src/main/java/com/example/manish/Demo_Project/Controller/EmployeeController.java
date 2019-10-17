package com.example.manish.Demo_Project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.manish.Demo_Project.Model.EmployeeInfo;
import com.example.manish.Demo_Project.Service.EmployeeService;

@RestController // -->@Controller and @ResponseBody
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/addemployee")
	public EmployeeInfo addEmployee(@RequestBody EmployeeInfo employeeinfo) {
		
		return employeeService.addEmployee(employeeinfo);
	}

	@PutMapping(value = "/updateemployee/{employeeId}")
	void updateEmployee(@RequestBody EmployeeInfo employeeinfo, @PathVariable int employeeId) {

		employeeService.update(employeeinfo, employeeId);
	}

	@DeleteMapping(value = "/deleteemployee/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		return employeeService.delete(employeeId);
	}

	@GetMapping(value = "/employees")
	List<EmployeeInfo> getAllEmployeeInfo() {
		return employeeService.getAllEmployee();
	}

	@GetMapping(value = "/employee/{employeeId}")
	public Optional<EmployeeInfo> findEmployee(@PathVariable int employeeId) {
		
		
	    return employeeService.findEmployee(employeeId); 
	}


	
}
