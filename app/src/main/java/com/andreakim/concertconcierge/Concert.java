package com.andreakim.concertconcierge;

// class made by Tony

public class Concert {

    //identification
    private int id;
    private String displayName;
    private Double popularity;

    //location
    private String city;
    private Double latitude;
    private Double longitude;

    //time and date
    private Double startTime;
    private Double startDate;

    public Concert(int id, String displayName, Double popularity, String city, Double latitude, Double longitude, Double startTime, Double startDate) {
        this.id = id;
        this.displayName = displayName;
        this.popularity = popularity;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startTime = startTime;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getStartDate() {
        return startDate;
    }

    public void setStartDate(Double startDate) {
        this.startDate = startDate;
    }
}
