package com.example.cars_info.service;

import com.example.cars_info.model.echo_park_db.CarDetails;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class Client {

    private RestTemplate restTemplate;
    @Autowired private EchoParkService echoParkService;

    public Client (@Autowired RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public String scrapeCarInfo() {
        String url = "http://127.0.0.1:8000/scrape/car";

        HttpHeaders headers = new HttpHeaders();
        headers.add("echopark-url", "https://www.echopark.com/used/Volkswagen/2019-Volkswagen-Jetta-5f5221f10a0e09a934a7a3c791a08d42.htm");
        headers.add("make", "Volkswagen");
        headers.add("model", "Jetta");

        HttpEntity<String> httpEntity = new HttpEntity<>("",headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.GET,httpEntity,String.class);
        String body = responseEntity.getBody();

        JsonNode jsonNode = echoParkService.validateJSON(body);
        System.out.println(jsonNode);
        if (jsonNode != null) {
            CarDetails carDetails = new CarDetails();
            echoParkService.mapJsonToCarObj(jsonNode, carDetails);
            echoParkService.saveCarDetailsInDatabase(carDetails);
        }

        return body;
    }

    public String scrapeInventoryUrls() {
        String url = "http://127.0.0.1:8000/scrape/inventory";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();

        List<String> allUrls = echoParkService.convertToList(body);
        int urlSaved = echoParkService.saveUrlsInDatabase(allUrls);

        return String.format("[%d] URLs are saved out of [%d]. Rest already exist.\n",urlSaved , allUrls.size());
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
