package com.example.springjwt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Analysis {
	

	private final RestTemplate restTemplate;

	public Analysis (RestTemplate restTemplate2) {
		this.restTemplate = restTemplate2;
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		this.restTemplate.setMessageConverters(messageConverters);
	}

	public Object getAnalysis() {
		 Object obj = this.restTemplate.getForObject("http://127.0.0.1:5000/books/", List.class);
		System.out.println(obj);
		return obj;
	}
	
	

}
