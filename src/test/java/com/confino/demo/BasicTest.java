package com.confino.demo;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;

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
          	contentType("text/html").
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
	public void contentTypeStatusCodeTest(){
		expect().
			contentType(ContentType.HTML).
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
	public void jsonBodyTest(){
		 JsonPath jsonPath = get(url).body().jsonPath();
		 assertEquals(jsonPath.getString("list.id[0]"), "5210764");
		 assertEquals(jsonPath.getString("list.distance[0]"), "2.68");
	     assertEquals(jsonPath.getString("list.coord[0].lon"), "-75.59518");
		 assertEquals(jsonPath.getString("list.coord[0].lat"), "40.2451");
	     assertEquals(jsonPath.getString("list.name[0]"), "Sanatoga");
	}

}

