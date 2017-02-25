package com.yelp.fusion.client.connection;

import com.yelp.fusion.client.models.AutoComplete;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.Reviews;
import com.yelp.fusion.client.models.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Ranga on 2/22/2017.
 */

public interface YelpFusionApi {
    @GET("/v3/businesses/search")
    Call<SearchResponse> getBusinessSearch(@QueryMap Map<String, String> params);

    @GET("/v3/businesses/search/phone")
    Call<SearchResponse> getPhoneSearch(@Query("phone") String phone);

    @GET("/v3/transactions/{transaction_type}/search")
    Call<SearchResponse> getTransactionSearch(@Path("transaction_type") String transactionType, @QueryMap Map<String, String> params);

    @GET("/v3/businesses/{id}")
    Call<Business> getBusiness(@Path("id") String id);

    @GET("/v3/businesses/{id}/reviews")
    Call<Reviews> getBusinessReviews(@Path("id") String id, @Query("locale") String locale);

    @GET("/v3/autocomplete")
    Call<AutoComplete> getAutocomplete(@QueryMap Map<String, String> params);

}

