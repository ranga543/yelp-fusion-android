package com.fusion.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    YelpFusionApiFactory yelpFusionApiFactory;
    YelpFusionApi yelpFusionApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread.start();
    }

    Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {
            try {
                yelpFusionApiFactory = new YelpFusionApiFactory();
                yelpFusionApi = yelpFusionApiFactory.createAPI("Your Key here", "Your Secret here");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        Call<Business> call = yelpFusionApi.getBusiness(message);
        call.enqueue(callback);
    }

    Callback<Business> callback = new Callback<Business>() {
        @Override
        public void onResponse(Call<Business> call, Response<Business> response) {
            Business business = response.body();
            TextView txt = (TextView) findViewById(R.id.show_name);
            ArrayList<String> d = business.getLocation().getDisplayAddress();
            StringBuilder sbStr = new StringBuilder();
            for (int i = 0, il = d.size(); i < il; i++) {
                if (i > 0)
                    sbStr.append(',');
                sbStr.append(d.get(i));
            }
            txt.setText(sbStr.toString());

            // Update UI text with the Business object.
        }
        @Override
        public void onFailure(Call<Business> call, Throwable t) {
            TextView txt = (TextView) findViewById(R.id.show_name);
            txt.setText(t.getMessage());
            // HTTP error happened, do something to handle it.
        }
    };
}
