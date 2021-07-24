package com.example.cars_info.repository.echo_park_db;

import com.example.cars_info.model.echo_park_db.StockLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StockLinkRepository extends CrudRepository<StockLink, Integer> {

    @Query ("SELECT s FROM StockLink s WHERE s.stockLink = ?1")
    public StockLink findStockLinkBy (String link);

}
