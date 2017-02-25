package com.yelp.fusion.client;

import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.AccessToken;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Ranga on 2/24/2017.
 */

public class YelpFusionAuthApiUnitTest {
    YelpFusionApiFactory yelpFusionApiFactory;

    public YelpFusionAuthApiUnitTest(){
        yelpFusionApiFactory = new YelpFusionApiFactory();
    }

    @Test
    public void AccessTokenTest() throws IOException {
        AccessToken accessToken = yelpFusionApiFactory.getAccessToken(Credential.appId(), Credential.appSecret());
        assertNotNull(accessToken);
    }
}
