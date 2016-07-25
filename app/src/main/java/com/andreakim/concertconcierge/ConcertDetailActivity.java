package com.andreakim.concertconcierge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.net.URL;
import java.net.URLEncoder;

public class ConcertDetailActivity extends AppCompatActivity {

    TextView mWeathertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_detail);


    }
    private URL createURL(String city) {
        String apiKey = getString(R.string.openweather_api);
        String baseUrl = getString(R.string.openweather_url);
        try {
            String url = baseUrl + URLEncoder.encode(city, "UTF-8") + "&units=imperial&cnt=16&APPID=" + apiKey;
            return new URL(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
