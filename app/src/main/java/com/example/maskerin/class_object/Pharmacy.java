package com.example.maskerin.class_object;

public class Pharmacy {

    public String name, distance, address, stock, dateUpdate, timeUpdate, phone, jam;
    public Double bujur,lintang;

    public Pharmacy(String name, String distance, String address, String stock,
                    String dateUpdate, String timeUpdate, String phone){
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.stock = stock;
        this.dateUpdate = dateUpdate;
        this.timeUpdate = timeUpdate;
        this.phone = phone;
    }
}
