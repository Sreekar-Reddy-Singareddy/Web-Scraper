package com.example.cars_info.repository.allcars_db;

import com.example.cars_info.model.allcars_db.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface AllCarsRepository extends CrudRepository<Vehicle, Integer>{

}
