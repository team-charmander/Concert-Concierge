package com.andreakim.concertconcierge;

/**
 * Created by ericaschulz on 7/26/16.
 */
public class Weather {

    public String cityName;
    public int currentTemp;
    public long mLat;
    public long mLon;





    @Override
    public String toString() {
        return "Weather{" +
                "cityName='" + cityName + '\'' +
                ", currentTemp=" + currentTemp +
                ", mLat=" + mLat +
                ", mLon=" + mLon +
                '}';
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public long getMlat() {
        return mLat;
    }

    public void setMlat(long mlat) {
        this.mLat = mlat;
    }

    public long getmLon() {
        return mLon;
    }

    public void setmLon(long mLon) {
        this.mLon = mLon;
    }
}
