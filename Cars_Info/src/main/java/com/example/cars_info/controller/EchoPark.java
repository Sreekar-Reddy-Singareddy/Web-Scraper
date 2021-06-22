package com.example.cars_info.controller;

import com.example.cars_info.model.echo_park_db.VinNumber;
import com.example.cars_info.repository.allcars_db.AllCarsRepository;
import com.example.cars_info.model.allcars_db.Vehicle;
import com.example.cars_info.repository.echo_park_db.CarDetailsRepository;
import com.example.cars_info.repository.echo_park_db.StockLinkRepository;
import com.example.cars_info.repository.echo_park_db.VinNumbersRepository;
import com.example.cars_info.service.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/app")
public class EchoPark {

    @Autowired private AllCarsRepository allCarsRepository;
    @Autowired private VinNumbersRepository vinNumbersRepository;
    @Autowired private CarDetailsRepository carDetailsRepository;
    @Autowired private StockLinkRepository stockLinkRepository;
    @Autowired private Client client;

    @GetMapping(path = "/scrape")
    public @ResponseBody String callScraper() {
        System.out.println("Inside callscraper");
        String response = client.scrape();
        return response;
    }

    @GetMapping(path = "/demo")
    public @ResponseBody String demo () {
        String response = client.demo();
        return response+"\nDONE!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Vehicle> allCars () {
        Iterable<Vehicle> vehicles = allCarsRepository.findAll();
        return vehicles;
    }

    @GetMapping(path = "/vins")
    public @ResponseBody Iterable<VinNumber> allVins() {
        Iterable<VinNumber> allVins = vinNumbersRepository.findAll();
        return allVins;
    }
}
