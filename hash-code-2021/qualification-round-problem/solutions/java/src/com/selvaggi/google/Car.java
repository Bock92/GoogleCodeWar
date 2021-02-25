package com.selvaggi.google;

import java.util.ArrayList;

public class Car {
    public int id;
    public ArrayList<Street> streets;
    public int tripTime;
    public boolean isArrived = false;
    public int streetsSize;
    public int currentStreet;
    public int positionOnTheStreet;
    public boolean isWaiting = true;

    public Car() {

    }

    public String toString() {
        StringBuilder out = new StringBuilder().append(streetsSize + " ");

        for(int i = 0; i< this.streets.size(); i++) {
            out.append(streets.get(i).name + " ");
        }

        return out.toString();
    }

}
