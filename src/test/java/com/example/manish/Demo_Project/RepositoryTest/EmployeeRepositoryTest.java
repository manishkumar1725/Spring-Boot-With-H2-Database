package com.example.manish.Demo_Project.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.manish.Demo_Project.Model.EmployeeInfo;
import com.example.manish.Demo_Project.Repositpry.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	
	EmployeeInfo employeeInfo1 = new EmployeeInfo(1, "manish", "manish@gmail.com");
	EmployeeInfo employeeInfo2 = new EmployeeInfo(2, "priti", "priti@gmail.com");
	
	
	
	
	 

	
	

}
