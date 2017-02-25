package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;

/**
 * Created by Ranga on 2/24/2017.
 */

public class Open implements Serializable {
    @JsonGetter("is_overnight")
    public boolean getIsOvernight() {
        return this.isOvernight;
    }
    public void setIsOvernight(boolean isOvernight) {
        this.isOvernight = isOvernight;
    }
    boolean isOvernight;

    @JsonGetter("end")
    public String getEnd() {
        return this.end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    String end;

    @JsonGetter("start")
    public String getStart() {
        return this.start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    String start;

    @JsonGetter("day")
    public int getDay() {
        return this.day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    int day;
}
