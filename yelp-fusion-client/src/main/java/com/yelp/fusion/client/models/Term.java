package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;

/**
 * Created by Ranga on 2/24/2017.
 */

public class Term implements Serializable {
    @JsonGetter("text")
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    String text;
}
