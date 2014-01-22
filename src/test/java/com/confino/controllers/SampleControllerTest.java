package com.confino.controllers;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.get;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.path.json.JsonPath;

public class SampleControllerTest {
	
	@Before
	public void setup(){
		RestAssuredMockMvc.standaloneSetup(new SampleController());
	}

	@Test
	public void messageTest() {
		given().
			param("name", "Mr. McNabb").
		when().
			get("/greeting").
		then().
			statusCode(200).body("id", equalTo(1)).
			body("message", equalTo("Good morning, Mr. McNabb!"));
	}
	
	@Test
	public void personTest(){
		JsonPath jsonPath = get("/person").body().jsonPath();

		assertEquals("Joe", jsonPath.getString("[0].firstName"));
		assertEquals("Smith", jsonPath.getString("[0].lastName"));
		assertEquals("40", jsonPath.getString("[0].age"));
		assertEquals("Ann", jsonPath.getString("[3].firstName"));
		assertEquals("Fields", jsonPath.getString("[3].lastName"));
		assertEquals("25", jsonPath.getString("[3].age"));
	}
	
	@Test
	public void personStatusCodeContentTypeTest(){
		get("/person").
		then().
			contentType(ContentType.JSON).
		and().
			statusCode(200);
	}

}
