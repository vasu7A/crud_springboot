package com.example.demo.Controller;

import java.net.http.HttpHeaders;

import javax.tools.JavaFileObject;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import com.example.demo.Service.GreetingService;
import com.fasterxml.jackson.core.JacksonException;

@RestController
public class Greeting {

    @GetMapping("/greet")
    public String greet() {
        return "/Hello";
    }

    @PostMapping("/test")
    public String test() {
        return "passed";
    }

    @PostMapping(path = "/user")
    public ResponseEntity<String> getGreetingService(@RequestBody String request) {
        try {
            // Assuming GreetingService.getGreetingService returns a JSON object
            JSONObject req = new JSONObject(request);
            JSONObject res = GreetingService.getGreetingService(req);

            // Return the response as a String
            return new ResponseEntity<>(res.toString(), HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            return new ResponseEntity<>("Error processing request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
