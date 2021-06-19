package com.example.cars_info.controller;

import com.example.cars_info.model.echo_park_db.VinNumber;
import com.example.cars_info.repository.allcars_db.AllCarsRepository;
import com.example.cars_info.model.allcars_db.Vehicle;
import com.example.cars_info.repository.echo_park_db.VinNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class EchoPark {

    @Autowired private AllCarsRepository repo;
    @Autowired private VinNumbersRepository vinsRepo;


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Vehicle> allCars () {
        Iterable<Vehicle> vehicles = repo.findAll();
        return vehicles;
    }

    @GetMapping(path = "/vins")
    public @ResponseBody Iterable<VinNumber> allVins() {
        Iterable<VinNumber> allVins = vinsRepo.findAll();
        return allVins;
    }
}
