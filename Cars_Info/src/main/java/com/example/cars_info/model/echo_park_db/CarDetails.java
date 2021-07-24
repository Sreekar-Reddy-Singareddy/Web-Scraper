package com.example.cars_info.model.echo_park_db;

import javax.persistence.*;

@Entity
@Table (name = "CAR_DETAILS")
public class CarDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String vin;
    private String make;
    private String model;
    private String trim;
    private int year;
    @Column (name = "stock_id")
    private String stockId;
    @Column (name = "kbb_price")
    private int kbbPrice;
    @Column (name = "echo_park_price")
    private int echoParkPrice;
    private String location;
    private int mileage;
    @Column (name = "fuel_economy")
    private int fuelEconomy;
    @Column (name = "exterior_color")
    private String exteriorColor;
    @Column (name = "interior_color")
    private String interiorColor;
    private String transmission;
    private String engine;
    @Column (name = "horse_power")
    private int horsePower;
    private int torque;
    private String drivetrain;
    @Column (name = "body_type")
    private String bodyType;
    private int seating;
    @Column (name = "hp_rpm")
    private int hpRpm;
    @Column (name = "torque_rpm")
    private int torqueRpm;
    @Column (name = "fuel_economy_city")
    private int fuelEconomyCity;
    @Column (name = "fuel_economy_highway")
    private int fuelEconomyHighway;
    @Column (name = "engine_volume")
    private float engineVolume;
    private int length;
    private int width;
    private int height;
    @Column (name = "front_leg_room")
    private int frontLegRoom;
    @Column (name = "rear_leg_room")
    private int rearLegRoom;
    @Column (name = "cargo_room")
    private int cargoRoom;
    private String others;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getKbbPrice() {
        return kbbPrice;
    }

    public void setKbbPrice(int kbbPrice) {
        this.kbbPrice = kbbPrice;
    }

    public int getEchoParkPrice() {
        return echoParkPrice;
    }

    public void setEchoParkPrice(int echoParkPrice) {
        this.echoParkPrice = echoParkPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getFuelEconomy() {
        return fuelEconomy;
    }

    public void setFuelEconomy(int fuelEconomy) {
        this.fuelEconomy = fuelEconomy;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getSeating() {
        return seating;
    }

    public void setSeating(int seating) {
        this.seating = seating;
    }

    public int getHpRpm() {
        return hpRpm;
    }

    public void setHpRpm(int hpRpm) {
        this.hpRpm = hpRpm;
    }

    public int getTorqueRpm() {
        return torqueRpm;
    }

    public void setTorqueRpm(int torqueRpm) {
        this.torqueRpm = torqueRpm;
    }

    public int getFuelEconomyCity() {
        return fuelEconomyCity;
    }

    public void setFuelEconomyCity(int fuelEconomyCity) {
        this.fuelEconomyCity = fuelEconomyCity;
    }

    public int getFuelEconomyHighway() {
        return fuelEconomyHighway;
    }

    public void setFuelEconomyHighway(int fuelEconomyHighway) {
        this.fuelEconomyHighway = fuelEconomyHighway;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFrontLegRoom() {
        return frontLegRoom;
    }

    public void setFrontLegRoom(int frontLegRoom) {
        this.frontLegRoom = frontLegRoom;
    }

    public int getRearLegRoom() {
        return rearLegRoom;
    }

    public void setRearLegRoom(int rearLegRoom) {
        this.rearLegRoom = rearLegRoom;
    }

    public int getCargoRoom() {
        return cargoRoom;
    }

    public void setCargoRoom(int cargoRoom) {
        this.cargoRoom = cargoRoom;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
