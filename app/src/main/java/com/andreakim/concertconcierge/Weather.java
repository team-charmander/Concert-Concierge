package com.andreakim.concertconcierge;

/**
 * Created by ericaschulz on 7/26/16.
 */
public class Weather {


    public int currentTemp;
    public String mDescription;



    public Weather(String cityName, int currentTemp, long mLat, long mLon) {

        this.currentTemp = currentTemp;
        this.mDescription = mDescription;
    }


    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public Weather() {
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
