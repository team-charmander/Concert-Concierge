package com.andreakim.concertconcierge;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private RecyclerView recyclerView;
    private ConcertAdapter concertAdapter;
    static private ArrayList<Concert> list_concerts;
    private TextView txt_search;
    String image_url;
    Button btn_search;
    String place;
    String name, date, venue, time, artist, venue_lat, venue_lng, city;
    private GoogleApiClient mGoogleApiClient;
    int place_picker_request = 1;
    Button btn_placepicker;
    Double curr_lat,curr_lng;
    //

    @Override
    public void onConnected(Bundle connectionHint) {
        try {
            Location mLastLocation =
                    LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

                curr_lat = mLastLocation.getLatitude();
                curr_lng = mLastLocation.getLongitude();
                Toast.makeText(getApplicationContext(),String.valueOf(curr_lat),Toast.LENGTH_LONG).show();
            Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> addresses = gcd.getFromLocation(curr_lat, curr_lng, 1);
              //  place = addresses.get(0).getLocality();
                String toastMsg = String.format("Place: %s", addresses.get(0).getLocality());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                list_concerts = new ArrayList<Concert>();
                // place = txt_search.getText().toString();
                new DataAsync().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_placepicker = (Button) findViewById(R.id.btn_placePicker);

        buildGoogleApiClient();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("error", "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }


    private class DataAsync extends AsyncTask<Void, Void, Void> {
        int metro_id = 0;


        @Override
        protected Void doInBackground(Void... params) {

            JSONObject jsonObject = JsonParser.getMetroID(place);
            if (jsonObject != null) {
                if (jsonObject.length() > 0) {
                    try {
                        JSONArray jsonArray = jsonObject.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("location");
                        metro_id = jsonArray.getJSONObject(0).getJSONObject("metroArea").getInt("id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            JSONObject concert_jsonObject = JsonParser.getConcertsFromApi(metro_id);
            if (concert_jsonObject != null) {
                if (concert_jsonObject.length() > 0) {
                    try {
                        JSONArray jsonArray = concert_jsonObject.getJSONObject("resultsPage").getJSONObject("results").getJSONArray("event");
                        int length = jsonArray.length();
                        if (length > 0) {
                            for (int i = 1; i < length; i++) {
                                JSONObject innerObject = jsonArray.getJSONObject(i);
                                name = innerObject.getString("displayName");
                                date = innerObject.getJSONObject("start").getString("date");
                                time = innerObject.getJSONObject("start").getString("time");
                                venue = innerObject.getJSONObject("venue").getString("displayName");
                                venue_lat = innerObject.getJSONObject("venue").getString("lat");
                                venue_lng = innerObject.getJSONObject("venue").getString("lng");
                                city = innerObject.getJSONObject("location").getString("city");


                                Concert concert = new Concert(name, venue, city, time, null);
                                list_concerts.add(concert);


                            }
                        }


                    } catch (Exception e) {
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


    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


}
