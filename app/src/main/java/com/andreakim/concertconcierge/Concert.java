package com.andreakim.concertconcierge;

/**
 * Created by andreakim on 7/25/16.
 */
public class Concert {
    String name;
    String venue;
    String date;
    String time;

    public Concert(String name, String venue, String date, String time) {
        this.name = name;
        this.venue = venue;
        this.date = date;
        this.time = time;
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