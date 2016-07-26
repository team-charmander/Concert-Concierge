package com.andreakim.concertconcierge;

import java.util.List;

/**
 * Created by spoorthi on 7/25/16.
 */
public class ConcertBean {

    /**
     * status : ok
     * results : {"location":[{"city":{"lat":41.8407,"lng":-87.6794,"country":{"displayName":"US"},"state":{"displayName":"IL"},"displayName":"Chicago"},"metroArea":{"lat":41.8407,"lng":-87.6794,"country":{"displayName":"US"},"uri":"http://www.songkick.com/metro_areas/9426-us-chicago?utm_source=41626&utm_medium=partner","state":{"displayName":"IL"},"displayName":"Chicago","id":9426}},{"metroArea":{"displayName":"Chicago","lat":41.8407,"country":{"displayName":"US"},"lng":-87.6794,"uri":"http://www.songkick.com/metro_areas/9426-us-chicago?utm_source=41626&utm_medium=partner","state":{"displayName":"IL"},"id":9426},"city":{"displayName":"East Chicago","lat":41.6392024,"country":{"displayName":"US"},"lng":-87.4547635,"state":{"displayName":"IN"}}},{"city":{"lat":null,"lng":null,"country":{"displayName":"US"},"state":{"displayName":"IL"},"displayName":"West Chicago"},"metroArea":{"lat":41.8407,"lng":-87.6794,"country":{"displayName":"US"},"state":{"displayName":"IL"},"uri":"http://www.songkick.com/metro_areas/9426-us-chicago?utm_source=41626&utm_medium=partner","displayName":"Chicago","id":9426}},{"city":{"lat":41.5061,"lng":-87.6356,"country":{"displayName":"US"},"state":{"displayName":"IL"},"displayName":"Chicago Heights"},"metroArea":{"lat":41.8407,"lng":-87.6794,"country":{"displayName":"US"},"uri":"http://www.songkick.com/metro_areas/9426-us-chicago?utm_source=41626&utm_medium=partner","state":{"displayName":"IL"},"displayName":"Chicago","id":9426}},{"metroArea":{"state":{"displayName":"MN"},"country":{"displayName":"US"},"lat":46.44231,"lng":-93.36586,"displayName":"Chicago","uri":"http://www.songkick.com/metro_areas/80131-us-chicago?utm_source=41626&utm_medium=partner","id":80131},"city":{"state":{"displayName":"MN"},"country":{"displayName":"US"},"lat":46.44231,"lng":-93.36586,"displayName":"Chicago"}}]}
     * perPage : 50
     * page : 1
     * totalEntries : 5
     */

    private ResultsPageBean resultsPage;

    public ResultsPageBean getResultsPage() {
        return resultsPage;
    }

    public void setResultsPage(ResultsPageBean resultsPage) {
        this.resultsPage = resultsPage;
    }

    public static class ResultsPageBean {
        private String status;
        private ResultsBean results;
        private int perPage;
        private int page;
        private int totalEntries;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ResultsBean getResults() {
            return results;
        }

        public void setResults(ResultsBean results) {
            this.results = results;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalEntries() {
            return totalEntries;
        }

        public void setTotalEntries(int totalEntries) {
            this.totalEntries = totalEntries;
        }

        public static class ResultsBean {
            /**
             * city : {"lat":41.8407,"lng":-87.6794,"country":{"displayName":"US"},"state":{"displayName":"IL"},"displayName":"Chicago"}
             * metroArea : {"lat":41.8407,"lng":-87.6794,"country":{"displayName":"US"},"uri":"http://www.songkick.com/metro_areas/9426-us-chicago?utm_source=41626&utm_medium=partner","state":{"displayName":"IL"},"displayName":"Chicago","id":9426}
             */

            private List<LocationBean> location;

            public List<LocationBean> getLocation() {
                return location;
            }

            public void setLocation(List<LocationBean> location) {
                this.location = location;
            }

            public static class LocationBean {
                /**
                 * lat : 41.8407
                 * lng : -87.6794
                 * country : {"displayName":"US"}
                 * state : {"displayName":"IL"}
                 * displayName : Chicago
                 */

                private CityBean city;
                /**
                 * lat : 41.8407
                 * lng : -87.6794
                 * country : {"displayName":"US"}
                 * uri : http://www.songkick.com/metro_areas/9426-us-chicago?utm_source=41626&utm_medium=partner
                 * state : {"displayName":"IL"}
                 * displayName : Chicago
                 * id : 9426
                 */

                private MetroAreaBean metroArea;

                public CityBean getCity() {
                    return city;
                }

                public void setCity(CityBean city) {
                    this.city = city;
                }

                public MetroAreaBean getMetroArea() {
                    return metroArea;
                }

                public void setMetroArea(MetroAreaBean metroArea) {
                    this.metroArea = metroArea;
                }

                public static class CityBean {
                    private double lat;
                    private double lng;
                    /**
                     * displayName : US
                     */

                    private CountryBean country;
                    /**
                     * displayName : IL
                     */

                    private StateBean state;
                    private String displayName;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }

                    public CountryBean getCountry() {
                        return country;
                    }

                    public void setCountry(CountryBean country) {
                        this.country = country;
                    }

                    public StateBean getState() {
                        return state;
                    }

                    public void setState(StateBean state) {
                        this.state = state;
                    }

                    public String getDisplayName() {
                        return displayName;
                    }

                    public void setDisplayName(String displayName) {
                        this.displayName = displayName;
                    }

                    public static class CountryBean {
                        private String displayName;

                        public String getDisplayName() {
                            return displayName;
                        }

                        public void setDisplayName(String displayName) {
                            this.displayName = displayName;
                        }
                    }

                    public static class StateBean {
                        private String displayName;

                        public String getDisplayName() {
                            return displayName;
                        }

                        public void setDisplayName(String displayName) {
                            this.displayName = displayName;
                        }
                    }
                }

                public static class MetroAreaBean {
                    private double lat;
                    private double lng;
                    /**
                     * displayName : US
                     */

                    private CountryBean country;
                    private String uri;
                    /**
                     * displayName : IL
                     */

                    private StateBean state;
                    private String displayName;
                    private int id;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }

                    public CountryBean getCountry() {
                        return country;
                    }

                    public void setCountry(CountryBean country) {
                        this.country = country;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public StateBean getState() {
                        return state;
                    }

                    public void setState(StateBean state) {
                        this.state = state;
                    }

                    public String getDisplayName() {
                        return displayName;
                    }

                    public void setDisplayName(String displayName) {
                        this.displayName = displayName;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public static class CountryBean {
                        private String displayName;

                        public String getDisplayName() {
                            return displayName;
                        }

                        public void setDisplayName(String displayName) {
                            this.displayName = displayName;
                        }
                    }

                    public static class StateBean {
                        private String displayName;

                        public String getDisplayName() {
                            return displayName;
                        }

                        public void setDisplayName(String displayName) {
                            this.displayName = displayName;
                        }
                    }
                }
            }
        }
    }
}
