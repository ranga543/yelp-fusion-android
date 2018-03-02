package com.yelp.fusion.client.models;

import java.io.Serializable;

public class ApiKey implements Serializable {
    public ApiKey(String apiKey, String tokenType) {
        this.apiKey = apiKey;
        this.tokenType = tokenType;
    }

    public String getApiKey() {
        return this.apiKey;
    }
    private String apiKey;

    public String getTokenType() {
        return this.tokenType;
    }
    private String tokenType;
}
