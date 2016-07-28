package com.andreakim.concertconcierge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class ConcertDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    static private ArrayList<Weather> list_weathers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_detail);


    }
}

//    private class WeatherTask extends AsyncTask<Void, Void, Void> {
//
////        int id = 0;
//
//        private TextView mTempView;
//
//
//        public WeatherTask(TextView mTempView) {
//
//            this.mTempView = mTempView;
//        }
//
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            JSONObject jsonObject = JsonParser.getWeatherTextFromApi();
//            if (jsonObject != null) {
//                if (jsonObject.length() > 0) {
//                    try {
//                        JSONArray jsonArray = jsonObject.getJSONObject("temp").getJSONObject("name").getJSONArray("weather");
//                        id = jsonArray.getJSONObject(0).getJSONObject("weather").getInt("id");
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//
//            return null;
//        }
//    }
//}