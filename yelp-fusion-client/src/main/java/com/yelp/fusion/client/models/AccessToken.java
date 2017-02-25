package com.yelp.fusion.client.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;

//@JsonDeserialize(builder = AccessToken.class)
public class AccessToken implements Serializable {
    @JsonGetter("access_token")
    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    String accessToken;

    @JsonGetter("token_type")
    public String getTokenType() {
        return this.tokenType;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    String tokenType;

    @JsonGetter("expires_in")
    public int getExpiresIn() {
        return this.expiresIn;
    }
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
    int expiresIn;
}
