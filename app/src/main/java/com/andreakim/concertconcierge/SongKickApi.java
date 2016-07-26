package com.andreakim.concertconcierge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by spoorthi on 7/25/16.
 */
public interface SongKickApi {
    @GET("api/3.0/search/locations.json?query=Chicago&apikey=KTHRla0QWSbiVtF0")
    Call<SongKickConcerts> getJSON();
}
