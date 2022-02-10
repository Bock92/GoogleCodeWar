package com.selvaggi.google;

import java.util.*;

public class World {
    public HashMap<String, Street> streets;
    public ArrayList<Car> cars;
    public ArrayList<Intersection> intersections;
    public int t = 0;

    public World() {
        this.streets = new HashMap<>();
        this.intersections = new ArrayList<>();
    }

    public Street getStreetByName(String streetName) {
        return this.streets.get(streetName);
    }

    public void addStreet(Street street ) {
        this.streets.put(street.name, street);
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
                    if(c.currentStreet == c.streets.size() - 1) {
                        c.isArrived = true;
                    } else {
                        c.isWaiting = true;
                        currentStreet.queueLength++;
                    }
                }
            }

        }
    }

    public void updateTrafficLight() {
        for(Intersection i: intersections) {
            Collections.sort(i.streets, new Comparator<Street>() {
                @Override
                public int compare(Street s1, Street s2)
                {
                    return  s1.queueLength - s2.queueLength;
                }
            });
            boolean finish = false;
            for(int index = 0; index < i.streets.size(); index++) {
                if(!finish && i.streets.get(index).queueLength > 0) {
                    i.streets.get(index).isGreen = true;
                    i.streets.get(index).greenSeconds++;
                    i.isTouch = true;
                    finish = true;
                } else {
                    i.streets.get(index).isGreen = false;
                }
            }
        }
    }

    public int getNIntersection() {
        int count = 0;
        for(Intersection i: intersections) {
            //if(i.isTouch) {
            if(i.getTotalGreenSeconds() > 0){
                count++;
            }
        }

        return count;
    }

    public int getNStreets(Intersection inter) {
        int count = 0;
        for(Street s: inter.streets) {
            if(s.greenSeconds > 0) {
                count++;
            }
        }

        return count;
    }



}
