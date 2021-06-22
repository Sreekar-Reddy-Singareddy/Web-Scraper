package com.example.cars_info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class Client {

    private RestTemplate restTemplate;
    @Autowired private EchoParkService echoParkService;

    public Client (@Autowired RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public String scrape () {
        String url = "http://127.0.0.1:8000/scrape";

        HttpHeaders headers = new HttpHeaders();
        headers.add("echopark-url", "https://www.echopark.com/used/Toyota/2019-Toyota-Camry-d89774390a0e09a957961eee86975b44.htm");

        HttpEntity<String> httpEntity = new HttpEntity<>("",headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.GET,httpEntity,String.class);
        String body = responseEntity.getBody();

        echoParkService.validateJSON(body);

        return body;
    }

    public String demo () {
        String url = "http://127.0.0.1:8000/demo";
        HttpHeaders headers = new HttpHeaders();
        headers.add("sender", "java web application");
        headers.add("EchoparkURL", "https://www.geeksforgeeks.org/how-to-convert-python-dictionary-to-json/");
        HttpEntity<String> httpEntity = new HttpEntity<>("",headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        String body = responseEntity.getBody();
        System.out.println(body);
        return body;
    }

}
