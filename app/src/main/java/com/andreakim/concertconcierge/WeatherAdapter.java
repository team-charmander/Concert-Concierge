package com.andreakim.concertconcierge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ericaschulz on 7/27/16.
 */
public abstract class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<Weather> weathers;

    public WeatherAdapter(ArrayList<Weather>weathers){
                          this.weathers=weathers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_concert_detail,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.weatherCity.setText(weathers.get(position).getCityName());
        holder.weatherTemp.setText(weathers.get(position).getCurrentTemp());
//        holder.weatherLat.setText(weathers.get(position).getMlat());
//        holder.weatherLon.setText(weathers.get(position).getmLon());


    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView weatherTemp, weatherCity, weatherLat, weatherLon;

        public ViewHolder(View view) {
            super(view);

            weatherTemp = (TextView)view.findViewById(R.id.weather_temp);
            weatherCity = (TextView)view.findViewById(R.id.weather_city);

        }
    }
}






