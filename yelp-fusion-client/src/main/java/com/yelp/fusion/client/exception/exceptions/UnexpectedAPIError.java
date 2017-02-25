package com.yelp.fusion.client.exception.exceptions;

/**
 * Created by Ranga on 2/23/2017.
 */

public class UnexpectedAPIError extends YelpFusionError {
    public UnexpectedAPIError(int responseCode, String message) {
        this(responseCode, message, null, null);
    }

    public UnexpectedAPIError(int responseCode, String message, String code, String description) {
        super(responseCode, message, code, description);
    }
}
