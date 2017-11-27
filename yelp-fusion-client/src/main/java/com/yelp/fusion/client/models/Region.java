package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;

public class Region implements Serializable
{
    @JsonGetter("center")
    public Center getCenter() {
        return this.center;
    }
    public void setCenter(Center center) {
        this.center = center;
    }
    Center center;
}
