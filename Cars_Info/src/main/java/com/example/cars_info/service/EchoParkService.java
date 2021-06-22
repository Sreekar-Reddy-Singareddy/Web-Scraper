package com.example.cars_info.service;

import com.example.cars_info.model.echo_park_db.CarDetails;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EchoParkService {

    @Autowired private ObjectMapper jsonObjMapper;
    private static List<String> requiredKeys;

    static {
        requiredKeys = new ArrayList<>();
        requiredKeys.add("vin");
        requiredKeys.add("make");
        requiredKeys.add("model");
        requiredKeys.add("year");
        requiredKeys.add("trim");
        requiredKeys.add("echopark_price");
        requiredKeys.add("available_location");
        requiredKeys.add("horsepower");
        requiredKeys.add("torque");
        requiredKeys.add("fuel_economy_combined");
    }

    public boolean validateJSON (String jsonString){
        JsonNode jsonNode = getJsonNode(jsonString);
        if (jsonNode == null) return false;

        boolean hasAllReqFields = hasAllRequiredFields(jsonNode);
        if (!hasAllReqFields) return false;

        CarDetails carDetails = new CarDetails();
        mapJsonToCarObj(jsonNode, carDetails);

        return true;
    }

    private void mapJsonToCarObj(JsonNode jsonNode, CarDetails carDetails) {

    }

    private boolean hasAllRequiredFields(JsonNode jsonNode) {
        boolean flag = true;
        for (String key : requiredKeys) {
            flag = flag && jsonNode.has(key);
        }
        return flag;
    }

    private JsonNode getJsonNode(String jsonString) {
        try {
            JsonNode jsonNode = jsonObjMapper.readTree(jsonString);
            return jsonNode;
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<String> convertToList(String jsonString) {
        JsonNode jsonNode = getJsonNode(jsonString);
        if (jsonNode == null) return new ArrayList<String>();

        Iterator<JsonNode> iterator = jsonNode.elements();
        List<String> allUrls = new ArrayList<>();
        while (iterator.hasNext()) {
            allUrls.add(iterator.next().textValue());
        }
        return allUrls;
    }
}
