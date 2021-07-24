package com.example.cars_info.service;

import com.example.cars_info.model.echo_park_db.CarDetails;
import com.example.cars_info.model.echo_park_db.StockLink;
import com.example.cars_info.repository.echo_park_db.CarDetailsRepository;
import com.example.cars_info.repository.echo_park_db.StockLinkRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class EchoParkService {

    @Autowired private CarDetailsRepository carDetailsRepository;
    @Autowired private StockLinkRepository stockLinkRepository;
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
//        requiredKeys.add("horsepower");
//        requiredKeys.add("torque");
        requiredKeys.add("fuel_economy_combined");
    }

    public JsonNode validateJSON (String jsonString){
        JsonNode jsonNode = getJsonNode(jsonString);
        if (jsonNode == null) return null;

        boolean hasAllReqFields = hasAllRequiredFields(jsonNode);
        if (!hasAllReqFields) return null;

        return jsonNode;
    }

    private boolean hasAllRequiredFields(JsonNode jsonNode) {
        boolean flag = true;
        for (String key : requiredKeys) {
            flag = flag && jsonNode.has(key);
        }
        return flag;
    }

    public void mapJsonToCarObj(JsonNode jsonNode, CarDetails carDetails) {
        if(jsonNode.has("vin")) carDetails.setVin(jsonNode.get("vin").asText());
        if(jsonNode.has("make")) carDetails.setMake(jsonNode.get("make").asText());
        if(jsonNode.has("model")) carDetails.setModel(jsonNode.get("model").asText());
        if(jsonNode.has("trim")) carDetails.setTrim(jsonNode.get("trim").asText());
        if(jsonNode.has("year")) carDetails.setYear(jsonNode.get("year").asInt());
        if(jsonNode.has("stock_id"))carDetails.setStockId(jsonNode.get("stock_id").asText());
        if(jsonNode.has("available_location"))carDetails.setLocation(jsonNode.get("available_location").asText());
        if(jsonNode.has("exterior_color")) carDetails.setExteriorColor(jsonNode.get("exterior_color").asText());
        if(jsonNode.has("interior_color"))carDetails.setInteriorColor(jsonNode.get("interior_color").asText());
        if(jsonNode.has("transmission"))carDetails.setTransmission(jsonNode.get("transmission").asText());
        if(jsonNode.has("engine"))carDetails.setEngine(jsonNode.get("engine").asText());
        if(jsonNode.has("drivetrain")) carDetails.setDrivetrain(jsonNode.get("drivetrain").asText());
        if(jsonNode.has("body")) carDetails.setBodyType(jsonNode.get("body").asText());
        if(jsonNode.has("max_seating_capacity"))carDetails.setSeating(jsonNode.get("max_seating_capacity").asInt());
        if(jsonNode.has("detailed_specs"))carDetails.setOthers(jsonNode.get("detailed_specs").asText());

        if(jsonNode.has("fuel_economy_combined"))carDetails.setFuelEconomy(extractNumber(jsonNode.get("fuel_economy_combined").asText()));
        if(jsonNode.has("fuel_economy_city"))carDetails.setFuelEconomyCity(extractNumber(jsonNode.get("fuel_economy_city").asText()));
        if(jsonNode.has("fuel_economy_highway"))carDetails.setFuelEconomyHighway(extractNumber(jsonNode.get("fuel_economy_highway").asText()));

        if(jsonNode.has("echopark_price"))carDetails.setEchoParkPrice(extractNumber(jsonNode.get("echopark_price").asText()));
        if(jsonNode.has("kbb_price"))carDetails.setKbbPrice(extractNumber(jsonNode.get("kbb_price").asText()));

        if(jsonNode.has("mileage"))carDetails.setMileage(extractNumber(jsonNode.get("mileage").asText()));

        if(jsonNode.has("horsepower")) {
            String[] hpText = jsonNode.get("horsepower").asText().split("@");
            carDetails.setHorsePower(extractNumber(hpText[0]));
            carDetails.setHpRpm(extractNumber(hpText[1]));
        }
        if(jsonNode.has("torque")) {
            String[] tqText = jsonNode.get("torque").asText().split("@");;
            carDetails.setTorque(extractNumber(tqText[0]));
            carDetails.setTorqueRpm(extractNumber(tqText[1]));
        }

        if(jsonNode.has("engine_displacement"))carDetails.setEngineVolume(extractEngineDisplacement(jsonNode.get("engine_displacement").asText()));

        if(jsonNode.has("exterior_length"))carDetails.setLength(extractNumber(jsonNode.get("exterior_length").asText().split("\\(")[0]));
        if(jsonNode.has("exterior_body_width"))carDetails.setWidth(extractNumber(jsonNode.get("exterior_body_width").asText().split("\\(")[0]));
        if(jsonNode.has("exterior_height"))carDetails.setHeight(extractNumber(jsonNode.get("exterior_height").asText().split("\\(")[0]));
        if(jsonNode.has("front_legroom"))carDetails.setFrontLegRoom(extractNumber(jsonNode.get("front_legroom").asText().split("\\(")[0]));
        if(jsonNode.has("rear_legroom"))carDetails.setRearLegRoom(extractNumber(jsonNode.get("rear_legroom").asText().split("\\(")[0]));
        if(jsonNode.has("interior_cargo_volume"))carDetails.setCargoRoom(extractNumber(jsonNode.get("interior_cargo_volume").asText().split("\\(")[0]));
    }

    public Float extractEngineDisplacement (String text) {
        text = text.replaceAll("[^0-9.]", "");
        try {
            return Float.parseFloat(text);
        }
        catch (NumberFormatException ex) {
            return 0.0f;
        }
    }

    public Integer extractNumber (String text) {
        text = text.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
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

    public int saveUrlsInDatabase(List<String> allUrls) {
        Date defaultDate = new Date(0);

        int counter = 0;
        for (String url: allUrls) {
            StockLink stockLink = new StockLink();
            stockLink.setStockLink(url);
            stockLink.setLastSuccessExport(defaultDate);
            stockLink.setActive(true);
            if (stockLinkRepository.findStockLinkBy(stockLink.getStockLink()) != null) continue;
            stockLinkRepository.save(stockLink);
            counter++;
        }

        System.out.printf("[%d] URLs are saved out of [%d]. Rest already exist.\n",counter, allUrls.size());
        return counter;
    }

    public void saveCarDetailsInDatabase(CarDetails carDetails) {
        CarDetails c = carDetailsRepository.save(carDetails);
        System.out.println(c.getVin());
    }
}
