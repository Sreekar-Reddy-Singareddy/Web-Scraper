package com.example.cars_info.model.echo_park_db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table (name = "ALL_VIN")
public class VinNumber {
    @Id
    private String vin;

    @Column (name = "date_added")
    private Date dateAdded;

    public VinNumber() {
    }

    public VinNumber(String vin, Date dateAdded) {
        this.vin = vin;
        this.dateAdded = dateAdded;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "VinNumber{" +
                "vin='" + vin + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
