package com.andreakim.concertconcierge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ericaschulz on 8/1/16.
 */
public class WeatherFragment extends Fragment{


    public WeatherFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // 1st - use inflater to inflate fragment layout
        View weatherFragment = inflater.inflate(R.layout.weather_card_layout, container, false);
        // 2nd - find views
        TextView currentTemp = (TextView) weatherFragment.findViewById(R.id.weather_temp);
        TextView mDescription = (TextView)weatherFragment.findViewById(R.id.weather_desc);
        // 3rd view - update views with .setText()
        //currentTemp.setText();

//        currentTemp.setText();
//        mDescription.setText();

        return weatherFragment;


    }


    }



