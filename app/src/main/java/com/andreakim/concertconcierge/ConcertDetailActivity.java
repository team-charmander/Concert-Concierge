package com.andreakim.concertconcierge;


import android.annotation.TargetApi;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.json.JSONObject;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import static android.text.format.DateFormat.getDateFormat;


public class ConcertDetailActivity extends AppCompatActivity implements OnMapReadyCallback{
    int event_id;
        String city, lat, lng, popularity, uri, event_name, id, time, date, ageRestriction, zip, venue_name, street, phone, venue_description;
        private TextView txt_name, txt_city, txt_popularity, txt_uri, txt_time, txt_date, txt_ageRestriction, txt_venue, txt_phone;
    Date formatted_date;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_concert_detail);

            FragmentManager fm = new FragmentManager() {
                @Override
                public FragmentTransaction beginTransaction() {
                    return null;
                }

                @Override
                public boolean executePendingTransactions() {
                    return false;
                }

                @Override
                public Fragment findFragmentById(@IdRes int id) {
                    return null;
                }

                @Override
                public Fragment findFragmentByTag(String tag) {
                    return null;
                }

                @Override
                public void popBackStack() {

                }

                @Override
                public boolean popBackStackImmediate() {
                    return false;
                }

                @Override
                public void popBackStack(String name, int flags) {

                }

                @Override
                public boolean popBackStackImmediate(String name, int flags) {
                    return false;
                }

                @Override
                public void popBackStack(int id, int flags) {

                }

                @Override
                public boolean popBackStackImmediate(int id, int flags) {
                    return false;
                }

                @Override
                public int getBackStackEntryCount() {
                    return 0;
                }

                @Override
                public BackStackEntry getBackStackEntryAt(int index) {
                    return null;
                }

                @Override
                public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {

                }

                @Override
                public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) {

                }

                @Override
                public void putFragment(Bundle bundle, String key, Fragment fragment) {

                }

                @Override
                public Fragment getFragment(Bundle bundle, String key) {
                    return null;
                }

                @Override
                public List<Fragment> getFragments() {
                    return null;
                }

                @Override
                public Fragment.SavedState saveFragmentInstanceState(Fragment f) {
                    return null;
                }

                @Override
                public boolean isDestroyed() {
                    return false;
                }

                @Override
                public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {

                }

            };


//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//            mapFragment.getMapAsync(this);
//            ft.add(R.id.map_card, mapFragment);
//            ft.commit();



// I removed these textViews ( - Andrea):
//            txt_city = (TextView) findViewById(R.id.concert_txtcity);
//            txt_popularity = (TextView) findViewById(R.id.concert_txtPopularity);
//            txt_phone = (TextView) findViewById(R.id.concert_txtPhone);
//            txt_date = (TextView) findViewById(R.id.concert_txtview_date);

            event_id = getIntent().getIntExtra("event_id", 0);
            txt_name = (TextView) findViewById(R.id.concert_txtview_name);
            txt_uri = (TextView) findViewById(R.id.concert_txtUri);
            txt_time = (TextView) findViewById(R.id.concert_txtview_time);
            txt_ageRestriction = (TextView) findViewById(R.id.concert_txtAge);
            txt_venue = (TextView) findViewById(R.id.concert_txtview_venue);

            new EventAsync().execute();
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private class EventAsync extends AsyncTask<Void, Void, Void> {



            @TargetApi(Build.VERSION_CODES.N)
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
                       // DateFormat sdf = new SimpleDateFormat("h:mm a");
                       // formatted_date = sdf.parse(date);
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
                    txt_ageRestriction.setText("No age restriction");
                } else {
                    txt_ageRestriction.setText(ageRestriction);
                }

                java.text.DateFormat dateFormat = getDateFormat(getApplicationContext());
 //               if (date!=null)
 //               txt_date.setText(date);
                txt_time.setText(time);
               // txt_popularity.setText(popularity);
                //  txt_uri.setText(uri);

                txt_uri.setClickable(true);
                txt_uri.setMovementMethod(LinkMovementMethod.getInstance());
                String text = "<a href='" + uri + "'>" + event_name + " </a>";
                txt_uri.setText(Html.fromHtml(text));
//                txt_city.setText(city);
 //               txt_phone.setText(phone);
                String venue = venue_name + "\n" + street + "\n" + zip;
                txt_venue.setText(venue);
            }
        }

}

