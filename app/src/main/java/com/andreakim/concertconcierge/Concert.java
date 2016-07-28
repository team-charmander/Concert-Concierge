package com.andreakim.concertconcierge;

import android.graphics.Bitmap;

/**
 * Created by andreakim on 7/25/16.
 */
public class Concert {
    String name;
    String venue;
    String date;
<<<<<<< HEAD
    String time;
    Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }
=======


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    String time;
    Bitmap image;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String city;


    public Concert(String name, String venue, String city, String time,Bitmap image) {


>>>>>>> a297e3f6686bad91711cc25a23297e0a30dc3048

        this.name = name;
        this.venue = venue;
        this.city = city;
        this.time = time;
        this.image = image;
    }

<<<<<<< HEAD
    String city;

    public Concert(String name, String venue, String city, String time) {
        this.name = name;
        this.venue = venue;
        this.city = city;
        this.time = time;
    }

=======
>>>>>>> a297e3f6686bad91711cc25a23297e0a30dc3048
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