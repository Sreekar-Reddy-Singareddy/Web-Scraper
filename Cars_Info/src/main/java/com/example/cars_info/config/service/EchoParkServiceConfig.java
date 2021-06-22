package com.example.cars_info.config.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EchoParkServiceConfig {

    @Bean (name = "jsonObjectMapper")
    public ObjectMapper getObjectMapper () {
        ObjectMapper objectMapper = new ObjectMapper(getJsonFactory());
        return objectMapper;
    }

    @Bean (name = "jsonFactory")
    public JsonFactory getJsonFactory () {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        return jsonFactory;
    }

}
