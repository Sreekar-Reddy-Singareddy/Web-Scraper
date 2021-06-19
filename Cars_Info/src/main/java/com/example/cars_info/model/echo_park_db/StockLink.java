package com.example.cars_info.model.echo_park_db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "INVENTORY_LINKS")
public class StockLink {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Column (name = "stock_link")
    private String stockLink;
    @Column (name = "last_successful_export")
    private Date lastSuccessExport;
    @Column (name = "is_active")
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockLink() {
        return stockLink;
    }

    public void setStockLink(String stockLink) {
        this.stockLink = stockLink;
    }

    public Date getLastSuccessExport() {
        return lastSuccessExport;
    }

    public void setLastSuccessExport(Date lastSuccessExport) {
        this.lastSuccessExport = lastSuccessExport;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
