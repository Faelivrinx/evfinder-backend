package com.mypieceofcode.evfinder.command;

public class Coordinate {

    private double latitude;
    private double longitude;

    private int recommendationType;

    public Coordinate() {
    }

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getRecommendationType() {
        return recommendationType;
    }

    public void setRecommendationType(int recommendationType) {
        this.recommendationType = recommendationType;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
