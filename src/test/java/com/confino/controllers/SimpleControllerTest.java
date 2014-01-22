package com.confino.controllers;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

public class SimpleControllerTest {

	@Test
	public void fooTest() {
		RestAssuredMockMvc.standaloneSetup(new SimpleController());
		
		given().
			param("name", "Mr. McNabb").
		when().
			get("/message").
		then().
			statusCode(200).body("id", equalTo(1)).
			body("message", equalTo("Hello, Mr. McNabb!"));
	}

}
