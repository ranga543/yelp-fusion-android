package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;

/**
 * Created by Ranga on 2/24/2017.
 */

public class User implements Serializable {
    @JsonGetter("image_url")
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    String imageUrl;

    @JsonGetter("name")
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    String name;
}
