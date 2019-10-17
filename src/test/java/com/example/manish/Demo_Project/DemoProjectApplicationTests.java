package com.example.manish.Demo_Project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)// By Using this annotation we can use all other mockito anotation such as @Mock 
@SpringBootTest             //InjectMock and all other different Mockito Annotation.
public class DemoProjectApplicationTests {
	

	
	
	  @Test public void contextLoads() { 
		  
	  }
	 
}    
	/*
	 * A mock object is an interface to hide a dependency which cannot be tested in
	 * test environment e.g. database, network locations etc. A method invoked using
	 * mocked reference does not execute method body defined in class file, rather
	 * the method behavior is configured using when-thenReturn methods combinations.
	 */
	  
	  
/*  Difference between @InjectMocks and @Mocks
 * 
 * 1. Use @InjectMocks to create class instances which needs to be tested in test class. 
 * 
 * 2. Use @InjectMocks when actual method body needs to be executed for a given class. 
 * 
 * 3. Use @InjectMocks when we need all internal dependencies initialized with mock objects to work
 *    method correctly.
 *  
 * 4. Use @Mock to create mocks which are needed to support testing of class to be tested.
 * 
 * 5. Annotated class (to be tested) dependencies with @Mock annotation.
 * 
 * 6. We must define the when-thenRetrun methods for mock objects which class methods will
 *    be invoking during actual test execution 
 */
