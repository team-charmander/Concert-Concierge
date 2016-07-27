package com.andreakim.concertconcierge;

/**
 * Created by andreakim on 7/25/16.
 */
public class Concert {

    //identification
    private int id;
    private String displayName;



    //location
    private String city;
    private Double latitude;
    private Double longitude;

    //time and date
    private Double startTime;
    private Double startDate;

    public Concert(int id, String displayName, String city, Double latitude, Double longitude, Double startTime, Double startDate) {
        this.id = id;
        this.displayName = displayName;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startTime = startTime;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", startTime=" + startTime +
                ", startDate=" + startDate +
                '}';
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
