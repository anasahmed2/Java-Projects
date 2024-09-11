package model;

import org.json.JSONObject;
import persistence.Writable;

public class Chip implements Writable {

    final String name;
    final double value;

    //Effects: initializes the chipType with the name and value
    public Chip(String name, double value) {
        this.name = name;
        this.value = value;
    }


    //Effects: returns the name of the chip type
    public String getName() {
        return this.name;
    }

    //Effects: returns the value of the chip type
    public double getValue() {
        return this.value;
    }


    // EFFECTS: converts the chips into a jsonObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("value", value);
        return json;
    }
}
