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

    private String apiKey;

    private Credential(Builder builder) {
        apiKey = builder.apiKey;
    }

    public static String apiKey() {
        return getCredential().apiKey;
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
        private String apiKey;

        public Builder() {
        }

        @JsonProperty("api_key")
        public Builder apiKey(String val) {
            apiKey = val;
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
