package com.andreakim.concertconcierge;

import java.util.List;

/**
 * Created by spoorthi on 7/25/16.
 */
public class SongKickConcerts {

    private List<ConcertBean.ResultsPageBean.ResultsBean.LocationBean> location;
    ConcertBean cb = new ConcertBean();

    public List<ConcertBean.ResultsPageBean.ResultsBean.LocationBean> getLocation() {
        return cb.getResultsPage().getResults().getLocation();
    }

    public void setLocation(List<ConcertBean.ResultsPageBean.ResultsBean.LocationBean> location) {
        this.location = location;
    }



}
