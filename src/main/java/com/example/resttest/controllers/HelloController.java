package com.example.resttest.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "hello !";
    }

    @RequestMapping("/hello2")
    public String text2() {
        return "hello2 !";
    }

    @RequestMapping("/name")
    public String nameHandler(@RequestParam String first, @RequestParam(required = false, defaultValue = "Johansson") String last) {
        return "Hello " + first + " " + last;
    }

    @GetMapping("/list")
    public String listTest(@RequestParam List<String> id) {
        return "values "+id;
    }

    @RequestMapping("/path/{id}")
    public String pathvartest(@PathVariable String id)
    {
return "id "+id;
    }

    @RequestMapping("/hellohtml")
    public String htmlpage()
    {return "<h1> Hello </h1>";}
////////////////////

}
