package com.example.manish.Demo_Project.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.manish.Demo_Project.Model.EmployeeInfo;
import com.example.manish.Demo_Project.Repositpry.EmployeeRepository;
import com.example.manish.Demo_Project.Service.EmployeeService;

// Two Types of Frameworks-->
// Proxy(A Proxy is just an object which will be used instead of the original object.) based mock frameworks: Mockito, EasyMock, jMock.
// The mock frameworks based on bytecode manipulation: PowerMock


@RunWith(SpringRunner.class)
@SpringBootTest// Assumed identity for SpringJunit4ClassRunner which joins JUnit testing library with the Spring TestContext Framework.It provides ability to start a fully running web server listening on any defined or random port.
class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;//(Create proxy class for EmployeeRepository)
	
	// Mockito.mock(class name) is same as using Mock Annotation on Any class.
    // @Mock-->The @Mock annotation is used to create and inject mocked instances
	
	EmployeeInfo employeeInfo1 = new EmployeeInfo(1, "manish", "manish@gmail.com");
	EmployeeInfo employeeInfo2 = new EmployeeInfo(2, "priti", "priti@gmail.com");

	@Test
	void testAddEmployee() {

		Mockito.when(employeeRepository.save(employeeInfo1)).thenReturn(employeeInfo1);

		assertThat(employeeService.addEmployee(employeeInfo1)).isEqualTo(employeeInfo1);
	}

	@Test
	void testGetAllEmployee() {

		List<EmployeeInfo> employeeInfo = new ArrayList<>();
		employeeInfo.add(employeeInfo1);
		employeeInfo.add(employeeInfo2);

		Mockito.when(employeeRepository.findAll()).thenReturn(employeeInfo);

		assertThat(employeeService.getAllEmployee()).isEqualTo(employeeInfo);
	}

	@Test
	void testDelete() {

		Mockito.when(employeeRepository.getOne(1)).thenReturn(employeeInfo1);

		Mockito.when(employeeRepository.existsById(employeeInfo1.getEmployeeId())).thenReturn(false);

		assertFalse(employeeRepository.existsById(employeeInfo1.getEmployeeId()));
	}

	@Test
	void testUpdate() {
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(employeeInfo1));

		employeeInfo1.setEmployeeEmail("man@gmail.com");
		Mockito.when(employeeRepository.save(employeeInfo1)).thenReturn(employeeInfo1);

		assertThat(employeeService.update(employeeInfo1, 1)).isEqualTo(employeeInfo1);

	}

	
	  @Test void testFindById()
	  { 
		  Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(employeeInfo1));
		  
		  assertThat(employeeService.findEmployee(1)).isEqualTo(Optional.of(employeeInfo1));
		 }
	 

}
