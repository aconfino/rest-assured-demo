package com.confino.demo;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

@SuppressWarnings("rawtypes")
public class BasicTest {
	
	private String latitude = "40.0344";
	private String longitude = "-75.5144";
	private String city = "Malvern";
	private String url = "http://api.openweathermap.org/data/2.5/weather?q=malvern,pa";
	
	@Test
	public void simpleGetTest(){
		given().
			param("lat", latitude).
			param("lon", longitude).
		when().
			get("http://api.openweathermap.org/data/2.1/find/city").
		then().
			body(containsString(city));
	}
	
	@Test
	public void simplePostTest(){
		given().
			queryParam("lat", latitude).
			queryParam("lon", longitude).
		when().
			post("http://api.openweathermap.org/data/2.1/find/city").
		then().
			body(containsString(city));
	}
	
	@Test
	public void contentTypeStatusCodeTest(){
		expect().
			contentType(ContentType.JSON).
		and().
			statusCode(200).
		when().
			get(url);
	}
	
	@Test
	public void headerTest(){	
		get(url).
	  	then().
	  		assertThat().header("Access-Control-Allow-Methods", "GET, POST").
	  	and().
	  		assertThat().header("Transfer-Encoding", "chunked");
	}
	
	@Test
	public void jsonPathTest(){
		String jsonPathUrl  = "http://api.openweathermap.org/data/2.1/find/city?lat=" + latitude + "&lon=" + longitude;
		JsonPath jsonPath = get(jsonPathUrl).body().jsonPath();
		
		assertEquals("5199600", jsonPath.getString("list.id[0]"));
		assertEquals("0.764", jsonPath.getString("list.distance[0]"));
	    assertEquals("-75.51381", jsonPath.getString("list.coord[0].lon"));
		assertEquals("40.03622", jsonPath.getString("list.coord[0].lat"));
	    assertEquals(city, jsonPath.getString("list.name[0]"));
	}
	
	@Test
	public void xpathPathTest(){
		XmlPath xmlPath = get(url + "&mode=xml").xmlPath();

		assertEquals("Malvern", xmlPath.getString("current.city.@name"));
		assertEquals("5199600", xmlPath.getString("current.city.@id"));
		assertEquals("United States of America", xmlPath.getString("current.city.country"));
		assertEquals("-75.51", xmlPath.getString("current.city.coord.@lon"));
		assertEquals("40.04", xmlPath.getString("current.city.coord.@lat"));
	}

}

