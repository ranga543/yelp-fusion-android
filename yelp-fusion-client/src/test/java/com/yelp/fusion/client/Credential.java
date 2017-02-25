package com.yelp.fusion.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ranga on 2/25/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Credential.Builder.class)
public class Credential {

    private static final String CREDS_CONFIG_FILE_NAME = "credentials.yaml";

    private static Credential instance;

    private String appId;
    private String appSecret;

    private Credential(Builder builder) {
        appId = builder.appId;
        appSecret = builder.appSecret;
    }

    public static String appId() {
        return getCredential().appId;
    }

    public static String appSecret() {
        return getCredential().appSecret;
    }

    private static Credential getCredential() {
        if (instance == null) {
            InputStream inputStream = Credential.class.getClassLoader().getResourceAsStream(CREDS_CONFIG_FILE_NAME);
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            try {
                instance = objectMapper.readValue(inputStream, Credential.class);
            } catch (IOException e) {
                Assert.fail("Failed to get credentials from " + CREDS_CONFIG_FILE_NAME + ": " + e.toString());
            }
        }

        return instance;
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static final class Builder {
        private String appId;
        private String appSecret;

        public Builder() {
        }

        @JsonProperty("app_id")
        public Builder appId(String val) {
            appId = val;
            return this;
        }

        @JsonProperty("app_secret")
        public Builder appSecret(String val) {
            appSecret = val;
            return this;
        }

        public Credential build() {
            return new Credential(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
