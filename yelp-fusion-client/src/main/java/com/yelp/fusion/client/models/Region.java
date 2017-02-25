package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Region
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
