package com.techiebirendra.test;

//import static io.restassured.RestAssured.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.techiebirendra.demo.beans.Greetings;
import com.techiebirendra.demo.controller.StudentController;
import com.techiebirendra.demo.service.StudentService;

import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ExtractableResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentControllerTest {
	public static String ROOT_CONTEXT = "http://localhost:8080/RestAssured-Test";

	@Mock
	private StudentService service;
	@InjectMocks
	private StudentController studentController;
	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}

	// @Test
	public void testFindAll() {
		/**
		 * for non-springmvc
		 */
		// Response response = get(ROOT_CONTEXT + "/findAll");
		// response.then().body("name", equalTo(Arrays.asList("birendra", "shyam")));
		// response.then().body("id", hasItems(101, 102));
		//
		// JsonPath jp = new JsonPath(response.asString());
		// String names = jp.get("name").toString();
		// String id = jp.get("id").toString();
		// System.out.println("names : " + names + " id: " + id);

	}

	/**
	 * for non-springmvc
	 */
	// @Test
	// public void testFindById() {
	// given().param("id", "101").when().get(ROOT_CONTEXT +
	// "/findById").then().statusCode(200)
	// .body("name", equalTo("birendra")).body("address", equalTo("kol"));
	// }
	/**
	 * Spring MVC TEST
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGreets() throws Exception {
		when(service.getGreetings("birendra", 123)).thenReturn(new Greetings("birendra", 101));

		Map<String, Object> param = new HashMap<>();
		param.put("name", "birendra");
		param.put("id", 123);

		mockMvc.perform(get("/greeting").param("id", "10")).andExpect(status().isOk());

		// mockMvc.perform(get("/greeting").param("").andExpect(status().isOk());

		// verify(todoServiceMock, times(1)).deleteById(ID);
		// verifyNoMoreInteractions(todoServiceMock);
	}

	@Test
	public void testGreeting() {
		// when(service.getGreetings("Birendra", 001)).thenReturn(new
		// Greetings("abc",002));

		MockMvcRequestSpecification mrs = given().standaloneSetup(new StudentController(service)).param("name", "Raju")
				.param("id", 101);
		ValidatableMockMvcResponse resp = mrs.when().get("/greeting").then().statusCode(200);
		ExtractableResponse r = resp.extract();
		System.out.println(r.asString());
		// verifying
		verify(service).getGreetings("Raju", 101);

		// ArgumentCaptor<Greetings> formObjectArgument =
		// ArgumentCaptor.forClass(Greetings.class);
		//
		// verify(service).getGreetings(formObjectArgument.capture());
		// Greetings formObject = formObjectArgument.getValue();
		//
		// assertThat(formObject.getName(), is("Birendra"));
		// assertThat(formObject.getId(), is(01));
		// assertNull(formObject.getId());

		// resp.body("name", equalTo("Hello, Birendra"));
		//
		// given().standaloneSetup(new StudentController()).param("name",
		// "Birendra").when().get("/greeting").then()
		// .statusCode(200).body("name", equalTo("Hello, Birendra"));
	}
}
