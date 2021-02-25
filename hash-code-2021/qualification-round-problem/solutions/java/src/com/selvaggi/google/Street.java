package com.selvaggi.google;

public class Street {
    public int id;
    public String name;
    public int length;
    public int start_intersection;
    public int end_intersection;
    public boolean isGreen;
    public int queueLength = 0;

    public Street() {

    }

    public java.lang.String toString() {
        StringBuilder out = new StringBuilder();
        return out.append(this.start_intersection + " ")
                .append(this.end_intersection + " ")
                .append(this.name + " ")
                .append(this.length).toString();
    }
}
