package com.selvaggi.google;

import java.util.ArrayList;

public class Intersection {
    public int id;
    public ArrayList<Street> streets;
    public boolean isTouch = false;

    public Intersection() {
        streets = new ArrayList<>();
    }

    public int getTotalGreenSeconds(){
        int totalGreenSeconds = 0;
        for(Street street: streets) {
            totalGreenSeconds = street.greenSeconds;
        }
        return totalGreenSeconds;
    }
}
