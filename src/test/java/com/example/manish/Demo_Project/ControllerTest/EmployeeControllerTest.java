package com.example.manish.Demo_Project.ControllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.manish.Demo_Project.Controller.EmployeeController;
import com.example.manish.Demo_Project.Model.EmployeeInfo;
import com.example.manish.Demo_Project.Service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)//SpringRunner is the new name for SpringJUnit4ClassRunner. It enables full support of spring context loading and dependency injection of the beans in the tests.
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	EmployeeInfo employeeInfo1 = new EmployeeInfo(1, "manish", "manish@gmail.com");

	EmployeeInfo employeeInfo2 = new EmployeeInfo(2, "rishav", "rishav@gmail.com");

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	void testAddEmployee() throws Exception {

		String inputInJson = this.mapToJson(employeeInfo1);

		String URI = "/addemployee";

		Mockito.when(employeeService.addEmployee(Mockito.any(EmployeeInfo.class))).thenReturn(employeeInfo1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void testUpdateEmployee() throws Exception {

		String inputJson = this.mapToJson(employeeInfo1);


		Mockito.when(employeeService.update(Mockito.any(EmployeeInfo.class), Mockito.anyInt()))
				.thenReturn(employeeInfo1);

		MvcResult result = this.mockMvc.perform(put("/updateemployee/{employeeId}", new Integer(12))
				.accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void testGetAllEmployeeInfo() throws Exception {

		List<EmployeeInfo> employeeList = new ArrayList<>();
		employeeList.add(employeeInfo1);
		employeeList.add(employeeInfo2);

		Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeList);

		String URI = "/employees";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(employeeList);

		String outputInJson = result.getResponse().getContentAsString();

		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test
	void testfindEmployee() throws Exception {

		String inputInJson = this.mapToJson(employeeInfo1);

		Mockito.when(employeeService.findEmployee(Mockito.anyInt())).thenReturn(Optional.of(employeeInfo1));

		MvcResult result = mockMvc
				.perform(get("/employee/{employeeId}", new Integer(1)).accept(MediaType.APPLICATION_JSON)).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testdeleteEmployee() throws Exception {

		Mockito.when(employeeService.delete(Mockito.anyInt())).thenReturn("DELETED");

		MvcResult result = this.mockMvc.perform(delete("/deleteemployee/{employeeId}", new Integer(1))).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputStr = response.getContentAsString();

		assertThat(outputStr).isEqualTo("DELETED");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
