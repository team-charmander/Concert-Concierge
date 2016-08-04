package com.andreakim.concertconcierge;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class ConcertDetailActivity extends AppCompatActivity {

    private Weather mWeatherModel;
    int event_id;
    private String temp;
    private String description;

    String city, lat, lng, popularity, uri, event_name, id, time, date, ageRestriction, zip, venue_name, street, phone, venue_description;
    private TextView txt_name, txt_city, txt_popularity, txt_uri, txt_time, txt_date, txt_ageRestriction, txt_venue, txt_phone, currentTemp, mDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_detail);

         currentTemp = (TextView)findViewById(R.id.weather_temp);
         mDescription = (TextView)findViewById(R.id.weather_desc);

            event_id = getIntent().getIntExtra("event_id", 0);
            txt_name = (TextView) findViewById(R.id.concert_txtview_name);
            txt_city = (TextView) findViewById(R.id.concert_txtcity);
            txt_popularity = (TextView) findViewById(R.id.concert_txtPopularity);
            txt_uri = (TextView) findViewById(R.id.concert_txtUri);
            txt_time = (TextView) findViewById(R.id.concert_txtview_time);
            txt_date = (TextView) findViewById(R.id.concert_txtview_date);
            txt_ageRestriction = (TextView) findViewById(R.id.concert_txtAge);
            txt_venue = (TextView) findViewById(R.id.concert_txtview_venue);
            txt_phone = (TextView) findViewById(R.id.concert_txtPhone);
            new EventAsync().execute();
        }


        private class WeatherTask extends  AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... voids) {
                JSONObject weatherJson = JsonParser.getWeather(zip);
                // fetch weather
                // get json
                // put in mWeatherModel
                //mWeatherModel.mDescription = ...
                if (weatherJson != null && weatherJson.length() > 0) {

                    try {
                        temp = weatherJson.getJSONObject("main").getString("temp");
                        JSONArray weatherArray = weatherJson.getJSONArray("weather");

                        for (int i = 0; i < weatherArray.length(); i++) {

                            JSONObject innerObject = weatherArray.getJSONObject(i);
                            description = innerObject.getString("description");


                        }





                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void empty) {

                currentTemp.setText(temp + "°F");
                mDescription.setText(description);



                    }

                // 1. create the fragment & place it somewhere
                // 2. grab values from model
                // 3. create bundle w/values from model
                // 4. setArguments(bundle) to fragment
                // 5. inflate the fragment


            }


        private class EventAsync extends AsyncTask<Void, Void, Void> {
                @Override
                protected Void doInBackground(Void... voids) {
                    String key = getResources().getString(R.string.songkick_api);

                    JSONObject event_responseObject = JsonParser.getEventDetails(event_id,key);

                    if (event_responseObject != null && event_responseObject.length() > 0) {

                        try {
                            JSONObject eventObject = event_responseObject.getJSONObject("resultsPage").getJSONObject("results").getJSONObject("event");

                            city = eventObject.getJSONObject("location").getString("city");
                            lat = eventObject.getJSONObject("location").getString("lat");
                            lng = eventObject.getJSONObject("location").getString("lng");
                            popularity = eventObject.getString("popularity");
                            uri = eventObject.getString("uri");
                            event_name = eventObject.getString("displayName");
                            date = eventObject.getJSONObject("start").getString("date");
                            time = eventObject.getJSONObject("start").getString("time");
                            ageRestriction = eventObject.getString("ageRestriction");
                            JSONObject venue_object = eventObject.getJSONObject("venue");
                            venue_name = venue_object.getString("displayName");
                            street = venue_object.getString("street");
                            phone = venue_object.getString("phone");
                            zip = venue_object.getString("zip");
                            //     venue_description =venue_object.getString("venue_description");


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    txt_name.setText(event_name);
                    if (ageRestriction == "null") {
                        txt_ageRestriction.setText("None");
                    } else {
                        txt_ageRestriction.setText(ageRestriction);
                    }
                    txt_date.setText(date);
                    txt_time.setText(time);
                    txt_popularity.setText(popularity);
                    //  txt_uri.setText(uri);
                    txt_uri.setClickable(true);
                    txt_uri.setMovementMethod(LinkMovementMethod.getInstance());
                    String text = "<a href='" + uri + "'>" + event_name + " </a>";
                    txt_uri.setText(Html.fromHtml(text));
                    txt_city.setText(city);
                    txt_phone.setText(phone);
                    String venue = venue_name + "\n" + street + "\n" + zip;
                    txt_venue.setText(venue);

                    // when done, new async task for weather
                    new WeatherTask().execute();
                }


            }

        }

