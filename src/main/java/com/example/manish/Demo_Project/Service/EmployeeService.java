package com.example.manish.Demo_Project.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.manish.Demo_Project.Model.EmployeeInfo;
import com.example.manish.Demo_Project.Repositpry.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public EmployeeInfo addEmployee(EmployeeInfo employeeInfo) {

		return repository.save(employeeInfo);
	}

	public List<EmployeeInfo> getAllEmployee() {

		List<EmployeeInfo> employeeInfo = new ArrayList<>();
		repository.findAll().forEach(employeeInfo::add);

		return employeeInfo;
	}

	public String delete(Integer employeeId) {
		repository.deleteById(employeeId);
		return "DELETED";

	}

	public EmployeeInfo update(EmployeeInfo employeeInfo, int employeeId) {
		List<EmployeeInfo> employeeinfo = repository.findAll();

		for (EmployeeInfo emp : employeeinfo) {
			if (emp.getEmployeeId() == employeeId) {
				emp.setEmployeeId(employeeId);
				repository.save(employeeInfo);
			}
		}
		return employeeInfo;
		
	}

	public Optional<EmployeeInfo> findEmployee(int employeeId) {

		return repository.findById(employeeId);
	}
	
	
}
