package com.yelp.fusion.client.connection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.fusion.client.connection.interceptors.AccessTokenInterceptor;
import com.yelp.fusion.client.exception.ErrorHandlingInterceptor;
import com.yelp.fusion.client.models.AccessToken;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Ranga on 2/22/2017.
 */

public class YelpFusionApiFactory {
    private static final String YELP_API_BASE_URL = "https://api.yelp.com";

    private OkHttpClient httpClient;
    private OkHttpClient authClient;
    private AccessToken accessToken;

    public YelpFusionApiFactory() {}

    public YelpFusionApi createAPI(String clientId, String clientSecret) throws IOException {
        getAccessToken(clientId, clientSecret);

        return getYelpFusionApi();
    }

    public YelpFusionApi createAPI(String accessToken) throws IOException {
        this.accessToken = new AccessToken();
        this.accessToken.setAccessToken(accessToken);
        this.accessToken.setTokenType("Bearer");
        return getYelpFusionApi();
    }

    private YelpFusionApi getYelpFusionApi() {
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AccessTokenInterceptor(accessToken))
                .addInterceptor(new ErrorHandlingInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAPIBaseUrl())
                .addConverterFactory(getJacksonFactory())
                .client(this.httpClient)
                .build();
        return retrofit.create(YelpFusionApi.class);
    }

    public AccessToken getAccessToken(String clientId, String clientSecret) throws IOException {
        authClient = new OkHttpClient.Builder()
                .addInterceptor(new ErrorHandlingInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAPIBaseUrl())
                .addConverterFactory(getJacksonFactory())
                .client(authClient)
                .build();

        YelpFusionAuthApi client = retrofit.create(YelpFusionAuthApi.class);
        Call<AccessToken> call = client.getToken("client_credentials", clientId, clientSecret);
        accessToken = call.execute().body();
        return accessToken;
    }

    private static JacksonConverterFactory getJacksonFactory(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return JacksonConverterFactory.create(mapper);
    }

    public String getAPIBaseUrl() {
        return YELP_API_BASE_URL;
    }

}

