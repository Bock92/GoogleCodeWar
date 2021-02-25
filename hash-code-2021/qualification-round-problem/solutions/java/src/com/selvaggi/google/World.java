package com.selvaggi.google;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    public HashMap<String, Street> streets;
    public ArrayList<Car> cars;
    public HashMap<Integer, Intersection> intersections;
    public int t = 0;

    public World() {
        this.streets = new HashMap<>();
        this.intersections = new HashMap<>();
    }

    public Street getStreetByName(String streetName) {
        return this.streets.get(streetName);
    }

    public void addStreet(Street street ) {
        this.streets.put(street.name, street);
    }

    public Intersection getIntersectionById(int id) {
        return this.intersections.get(id);
    }

    public void addIntersection(Intersection intersection) {
        this.intersections.put(intersection.id, intersection);
    }

    public void update() {
        for(Car c: cars) {
            Street currentStreet = c.streets.get(c.currentStreet);
            if(c.isWaiting && currentStreet.isGreen) {
                // attraverso l'incrocio
                c.positionOnTheStreet = 0;
                c.isWaiting = false;
                currentStreet.queueLength--;
                c.currentStreet++;

                if(c.streets.get(c.currentStreet).length == 1){
                    c.isArrived = true;

                }

            }

            if(!c.isWaiting && !c.isArrived) {
                c.positionOnTheStreet++;

                if(c.positionOnTheStreet == currentStreet.length - 1) {
                    c.isWaiting = true;
                    currentStreet.queueLength++;
                }
            }

        }
    }

    public void updateTrafficLight() {

        
    }
}
