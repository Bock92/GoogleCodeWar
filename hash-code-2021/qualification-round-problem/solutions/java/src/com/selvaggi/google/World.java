package com.selvaggi.google;

import java.util.HashMap;

public class World {
    public HashMap<String, Street> streets;

    public World() {
        this.streets = new HashMap<>();
    }

    public Street getStreetByName(String streetName) {
        return this.streets.get(streetName);
    }

    public void addStreet(Street street ) {
        this.streets.put(street.name, street);
    }
}
