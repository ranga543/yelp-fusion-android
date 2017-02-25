package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ranga on 2/24/2017.
 */

public class Reviews implements Serializable {
    @JsonGetter("total")
    public int getTotal() {
        return this.total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    int total;

    @JsonGetter("reviews")
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    ArrayList<Review> reviews;
}
