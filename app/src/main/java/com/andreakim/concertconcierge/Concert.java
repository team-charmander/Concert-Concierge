package com.andreakim.concertconcierge;

import android.graphics.Bitmap;

/**
 * Created by andreakim on 7/25/16.
 */
public class Concert {
    String name;
    String venue;
    String date;
    String time;
    Bitmap image;


    int id;


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }










    public void setCity(String city) {
        this.city = city;
    }

    String city;

    public Concert(String name, String venue, String city, String time,Bitmap image,int id) {


        this.name = name;
        this.venue = venue;
        this.city = city;
        this.time = time;
        this.image = image;

        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}