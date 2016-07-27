package com.andreakim.concertconcierge;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Arrays;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConcertAdapter concertAdapter;
   static private ArrayList<Concert> list_concerts;
    private TextView txt_search;
    Button btn_search;
    String place ;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_search = (TextView)findViewById(R.id.editTxt_search);
        btn_search = (Button)findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_concerts = new ArrayList<Concert>();
                place = txt_search.getText().toString();
                new DataAsync().execute();
            }
        });



    }

    private class DataAsync extends AsyncTask<Void, Void, Void> {
       int metro_id =0;
        String name,date,venue,time,artist,venue_lat,venue_lng,city;

        @Override
        protected Void doInBackground(Void... params) {

            JSONObject jsonObject = JsonParser.getMetroID(place);
            if(jsonObject!=null){
                if(jsonObject.length()>0){
                    try {
                        JSONArray jsonArray = jsonObject.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("location");
                        metro_id = jsonArray.getJSONObject(0).getJSONObject("metroArea").getInt("id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            JSONObject concert_jsonObject = JsonParser.getConcertsFromApi(metro_id);
            if(concert_jsonObject!=null){
                if(concert_jsonObject.length()>0){
                    try{
                        JSONArray jsonArray = concert_jsonObject.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event");
                        int length = jsonArray.length();
                        if(length>0){
                            for(int i=0;i<length;i++){
                                JSONObject innerObject = jsonArray.getJSONObject(i);
                                name = innerObject.getString("displayName");
                                date= innerObject.getJSONObject("start").getString("date");
                                time = innerObject.getJSONObject("start").getString("time");
                               // artist=innerObject.getJSONObject("performance").getString("")
                                venue = innerObject.getJSONObject("venue").getString("displayName");
                                venue_lat=innerObject.getJSONObject("venue").getString("lat");
                                venue_lng=innerObject.getJSONObject("venue").getString("lng");
                                city = innerObject.getJSONObject("location").getString("city");

                                Concert concert = new Concert(name,venue,city,time);
                                list_concerts.add(concert);

                            }
                        }


                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
          //  Log.e("Hey",name);
            recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            concertAdapter = new ConcertAdapter(list_concerts);
            recyclerView.setAdapter(concertAdapter);
        }
    }
}
