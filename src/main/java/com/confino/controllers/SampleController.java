package com.confino.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import com.confino.domain.Message;
import com.confino.domain.Person;

@Controller
public class SampleController {

    private static final String template = "Good morning, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting", method = GET)
    public @ResponseBody Message message(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Message(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping(value = "/person", method = GET)
    public @ResponseBody List<Person> getPerson(){
    	return generatePeople();
    }
    
    public List<Person> generatePeople(){
    	List<Person> people = new ArrayList<Person>();
    	people.add(new Person("Joe", "Smith", 40));
    	people.add(new Person("Sally", "Jones", 32));
    	people.add(new Person("Frank", "Matthews", 62));
    	people.add(new Person("Ann", "Fields", 25));
    	return people;
    }
}
