package com.confino.demo;

import org.junit.Test;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@SuppressWarnings("rawtypes")
public class BasicTest {
	
	private String url = "http://api.openweathermap.org/data/2.1/find/city?lat=40.2605&lon=-75.6155&cnt=3";

	@Test
	public void demoTest(){
		ResponseBody body = get(url).body();
		assertTrue(body.jsonPath().getString("list.id[0]").equals("5210764"));
		assertTrue(body.jsonPath().getString("list.distance[0]").equals("2.68"));
		assertTrue(body.jsonPath().getString("list.coord[0].lon").equals("-75.59518"));
		assertTrue(body.jsonPath().getString("list.coord[0].lat").equals("40.2451"));
		assertTrue(body.jsonPath().getString("list.name[0]").equals("Sanatoga"));
	}

}

