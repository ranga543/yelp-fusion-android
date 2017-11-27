package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ranga on 2/24/2017.
 */
public class Location implements Serializable
{
    @JsonGetter("state")
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    String state;

    @JsonGetter("address3")
    public String getAddress3() {
        return this.address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    String address3;

    @JsonGetter("cross_streets")
    public String getCrossStreets() {
        return this.crossStreets;
    }
    public void setCrossStreets(String crossStreets) {
        this.crossStreets = crossStreets;
    }
    String crossStreets;

    @JsonGetter("address2")
    public String getAddress2() {
        return this.address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    String address2;

    @JsonGetter("zip_code")
    public String getZipCode() {
        return this.zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    String zipCode;

    @JsonGetter("city")
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    String city;

    @JsonGetter("country")
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    String country;

    @JsonGetter("address1")
    public String getAddress1() {
        return this.address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    String address1;

    @JsonGetter("display_address")
    public ArrayList<String> getDisplayAddress() {
        return this.displayAddress;
    }
    public void setDisplayAddress(ArrayList<String> displayAddress) {
        this.displayAddress = displayAddress;
    }
    ArrayList<String> displayAddress;

}
