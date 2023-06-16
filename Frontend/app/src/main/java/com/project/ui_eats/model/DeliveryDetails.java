package com.project.ui_eats.model;

public class DeliveryDetails {
    public String recepientname;
    public String phoneNumber;
    public String address;

    @Override
    public String toString() {
        return "DeliveryDetails{" +
                "recepient name='" + recepientname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String note;

}
