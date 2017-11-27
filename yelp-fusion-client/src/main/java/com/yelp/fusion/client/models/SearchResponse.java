package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchResponse implements Serializable
{
    @JsonGetter("region")
    public Region getRegion() {
        return this.region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }
    Region region;

    @JsonGetter("businesses")
    public ArrayList<Business> getBusinesses() {
        return this.businesses;
    }
    public void setBusinesses(ArrayList<Business> businesses) {
        this.businesses = businesses;
    }
    ArrayList<Business> businesses;

    @JsonGetter("total")
    public int getTotal() {
        return this.total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    int total;

}