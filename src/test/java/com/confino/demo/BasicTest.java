package com.confino.demo;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.restassured.response.ResponseBody;

@SuppressWarnings("rawtypes")
public class BasicTest {
	
	private String url = "http://api.openweathermap.org/data/2.1/find/city?lat=40.2605&lon=-75.6155&cnt=3";
	
	@Test
	public void simpleGetTest(){
		given().
        	param("lat", "40.2605").
        	param("lon", "-75.6155").
        	param("cnt", "3").
        when().
        	get("http://api.openweathermap.org/data/2.1/find/city").
        then().
        	body(containsString("Sanatoga"));
	}
	
	@Test
	public void simplePostTest(){
		given().
        	queryParam("lat", "40.2605").
        	queryParam("lon", "-75.6155").
        	queryParam("cnt", "3").
        when().
        	post("http://api.openweathermap.org/data/2.1/find/city").
        then().
        	body(containsString("Sanatoga"));
	}
	
	@Test
	public void basicTest(){
		ResponseBody body = get(url).body();
		assertTrue(body.jsonPath().getString("list.id[0]").equals("5210764"));
		assertTrue(body.jsonPath().getString("list.distance[0]").equals("2.68"));
		assertTrue(body.jsonPath().getString("list.coord[0].lon").equals("-75.59518"));
		assertTrue(body.jsonPath().getString("list.coord[0].lat").equals("40.2451"));
		assertTrue(body.jsonPath().getString("list.name[0]").equals("Sanatoga"));
	}

}

