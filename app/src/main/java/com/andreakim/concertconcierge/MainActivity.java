package com.andreakim.concertconcierge;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {




        String mLatitudeText;
        String mLongitudeText;
        Location mLastLocation;
        private LocationRequest mLocationRequest;

//        @Override
        public void onConnectionSuspended(int i) {

        }

        String lat, lon;

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

        Intent detailIntent;


        Intent mConcertDetailIntent;


        protected void onStart() {
            mGoogleApiClient.connect();
            super.onStart();
        }


        //
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            txt_search = (TextView) findViewById(R.id.editTxt_search);
            btn_search = (Button) findViewById(R.id.btn_search);

            buildGoogleApiClient();

            try {

                PlacePicker.IntentBuilder picker_builder = new PlacePicker.IntentBuilder();
                startActivityForResult(picker_builder.build(this), place_picker_request);
            } catch (Exception e) {
                e.printStackTrace();
            }


            txt_search = (TextView) findViewById(R.id.editTxt_search);
            btn_search = (Button) findViewById(R.id.btn_search);


            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list_concerts = new ArrayList<Concert>();
                    place = txt_search.getText().toString();
                    new DataAsync().execute();
                }
            });


        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == place_picker_request) {
                if (resultCode == RESULT_OK) {
                    Place place_picker = PlacePicker.getPlace(this, data);

                    LatLng latLng = PlacePicker.getPlace(this, data).getLatLng();
                    Double lat = latLng.latitude;
                    Double lng = latLng.longitude;

                    Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
                        if (addresses.size() > 0)
                            place = addresses.get(0).getLocality();
                        String toastMsg = String.format("Place: %s", addresses.get(0).getLocality());
                        Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                        list_concerts = new ArrayList<Concert>();
                        // place = txt_search.getText().toString();
                        new DataAsync().execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
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
                                for (int i = 0; i < length; i++) {
                                    JSONObject innerObject = jsonArray.getJSONObject(i);
                                    name = innerObject.getString("displayName");
                                    date = innerObject.getJSONObject("start").getString("date");
                                    time = innerObject.getJSONObject("start").getString("time");
                                    //artist=innerObject.getJSONObject("performance").getString("")
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
        protected void onDestroy() {
            super.onDestroy();
            mGoogleApiClient.disconnect();
        }


//        @Override
        public void onConnected(Bundle connectionHint) {
            //    super.
            try {
                Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }





    }
