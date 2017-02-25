package com.yelp.fusion.client.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.fusion.client.exception.exceptions.UnexpectedAPIError;
import com.yelp.fusion.client.exception.exceptions.YelpFusionError;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ErrorHandlingInterceptor implements Interceptor {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.isSuccessful()) {
            throw parseError(
                    response.code(),
                    response.message(),
                    response.body() != null ? response.body().string() : null
            );
        }
        return response;
    }

    private YelpFusionError parseError(int code, String message, String responseBody) throws IOException {
        if (responseBody == null) {
            return new UnexpectedAPIError(code, message);
        }

        JsonNode errorJsonNode = objectMapper.readTree(responseBody).path("error");
        String errorCode = errorJsonNode.path("code").asText();
        String errorText = errorJsonNode.path("description").asText();
        return new UnexpectedAPIError(code, message, errorCode, errorText);
    }
}
