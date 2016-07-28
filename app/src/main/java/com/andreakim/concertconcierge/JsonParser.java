package com.andreakim.concertconcierge;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by spoorthi on 7/25/16.
 */
public class JsonParser {

    private static Response response;

    public static JSONObject getMetroID(String place){
        try {
            OkHttpClient client = new OkHttpClient();
            String metro_id_url = "http://api.songkick.com/api/3.0/search/locations.json?query="+place+"&apikey=KTHRla0QWSbiVtF0";
            Request request = new Request.Builder()
                    .url(metro_id_url).build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getConcertsFromApi(int metro_id){
        try {
            OkHttpClient client = new OkHttpClient();
            String Main_Url = "http://api.songkick.com/api/3.0/metro_areas/"+metro_id+"/calendar.json?apikey=KTHRla0QWSbiVtF0";
            Request request = new Request.Builder()
                    .url(Main_Url).build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static JSONObject getImage(String artist){
        try{
            OkHttpClient client = new OkHttpClient();
            String url = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="+artist+"&api_key=01c216d809a51da0b30105d35eb76ac8&format=json";

            Request request = new Request.Builder()
                    .url(url).build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static JSONObject getWeatherTextFromApi(int temp) {



        try {
            OkHttpClient client = new OkHttpClient();
            String weather_url = "http://api.openweathermap.org/data/2.5/weather?zip=60601,us"+"&APPID=1908e7a47dd061ed22e0faacfd678ba8";
            Request request = new Request.Builder()
                    .url(weather_url).build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
        }


        }

