package com.confino.domain;

import java.io.Serializable;  

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
@XmlRootElement  
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private Integer age;
	
	public Person(String firstName, String lastName, Integer age){
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	@XmlElement
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@XmlElement
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	@XmlElement
	public void setAge(Integer age) {
		this.age = age;
	}

}
