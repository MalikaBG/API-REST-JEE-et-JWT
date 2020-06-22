package com.example.springjwt.controllers;

import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import com.example.springjwt.Analysis;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jdk.nashorn.internal.parser.JSONParser;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	
	@PostMapping("/example")
	public JSONObject example(@RequestBody JSONObject string) {
	    return string;
	}

	@PostMapping("/analysis")
	public String postAnalysis(@RequestBody JSONObject string){
		

		JSONObject requestJson = string;

		RestTemplate restTemplate = new RestTemplate();

		String url = "http://127.0.0.1:5000/getAnalysis";
		//String requestJson = "{\"word\":\"tolerance\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		


		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(requestJson,headers);
		String answer = restTemplate.postForObject(url, entity, String.class);
		System.out.println(answer);
		//String answerup = "[" + answer + "]";
		//System.out.println("[" + answer + "]");
		return answer;
		
	}
	
	

	
	
	/*

    @Autowired
	private RestTemplate restTemplate;

	@GetMapping("/analysis")
	public List<Object> getAnalysis(){
		
		String url = "http://127.0.0.1:5000/analysis/";
		Object[] objects = restTemplate.getForObject(url, Object[].class);
        
		
		
		//String result = objects.toString();
		//System.out.println(result);
		//System.out.println(' '+)
		return java.util.Arrays.asList(objects);
		

		
	}*/
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}