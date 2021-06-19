package com.example.cars_info.repository.echo_park_db;

import com.example.cars_info.model.echo_park_db.CarDetails;
import org.springframework.data.repository.CrudRepository;

public interface CarDetailsRepository extends CrudRepository<CarDetails, Integer> {

}
