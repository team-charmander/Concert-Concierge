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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConcertAdapter concertAdapter;
    private ArrayList<ConcertBean.ResultsPageBean.ResultsBean.LocationBean> list_concerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
        recyclerView.setAdapter(concertAdapter);
    }



    private void loadJSON() {
        Retrofit retrofit_metroID = new Retrofit.Builder()
                .baseUrl("http://api.songkick.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SongKickApi songKickApi_request = retrofit_metroID.create(SongKickApi.class);
        Call<SongKickConcerts> call = songKickApi_request.getJSON();
        call.enqueue(new Callback<SongKickConcerts>() {
            @Override
            public void onResponse(Call<SongKickConcerts> call, Response<SongKickConcerts> response) {
                SongKickConcerts json_response = response.body();
                if(json_response!=null)
                list_concerts = new ArrayList<ConcertBean.ResultsPageBean.ResultsBean.LocationBean>(json_response.getLocation());
                concertAdapter = new ConcertAdapter(list_concerts);

            }

            @Override
            public void onFailure(Call<SongKickConcerts> call, Throwable t) {
                //   Log.i("Error",t.getMessage());

            }
        });


    }
    }



//    private class getDataFromApi extends AsyncTask<String, String, String> {
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            Retrofit retrofit_metroID = new Retrofit.Builder()
//                    .baseUrl("http://api.songkick.com/api/3.0/search/locations.json?query=Chicago&apikey=KTHRla0QWSbiVtF0")
//                    .build();
//
//            Retrofit retrofit_concerts = new Retrofit.Builder()
//                    .baseUrl("http://api.songkick.com/api/3.0/metro_areas/{metro_area_id}/calendar.json?apikey=KTHRla0QWSbiVtF0")
//                    .build();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return null;
//        }
//    }
