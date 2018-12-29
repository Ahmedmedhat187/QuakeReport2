package com.example.android.quakereport;

public class Earthquake  {


    private double mag  ;
    private String place ;
    private Long time  ;
    private String url;

    public Earthquake(double mag, String place, Long time , String url) {
        this.mag = mag;
        this.place = place;
        this.time = time;
        this.url = url;
    }

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public Long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }


}


