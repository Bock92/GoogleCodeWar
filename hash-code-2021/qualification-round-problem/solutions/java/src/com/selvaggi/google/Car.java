package com.selvaggi.google;

import java.util.ArrayList;

public class Car {
    public int id;
    public ArrayList<Street> streets;
    public int tripTime;
    public boolean isArrived;
    public int streetsSize;

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
