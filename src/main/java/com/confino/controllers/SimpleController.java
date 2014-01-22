package com.confino.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.confino.domain.Message;

@Controller
public class SimpleController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/message", method = GET)
    public @ResponseBody Message message(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Message(counter.incrementAndGet(), String.format(template, name));
    }
}
