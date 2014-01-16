package com.confino.demo;

import org.junit.Test;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class BasicTest {
	
	private String url = "http://api.openweathermap.org/data/2.1/find/city?lat=40.2605&lon=-75.6155&cnt=3";
	
	@Test
	public void simpleTest(){
		assertTrue(get(url).asString().contains("Pottstown"));
	}
	
	@Test
	public void demo2Test(){
		assertTrue(get(url).body().jsonPath().getString("list.name").contains("Sanatoga"));
	}

}

