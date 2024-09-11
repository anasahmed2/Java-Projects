package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.Iterator;

public class ChipCounter implements Writable {

    private final ArrayList<Chip> chips;

    private String name;

    final EventLog eventLog;


    //Effects: initializes a new chipCounter object with an empty list
    public ChipCounter() {
        chips = new ArrayList<>();
        eventLog = EventLog.getInstance();
    }


    //Modifies: this
    //Effects: adds the specified chip to the list of chips
    public void addChips(Chip chip) {

        chips.add(chip);

        eventLog.logEvent(new Event("Chip Added to List"));

    }


    //Modifies: this
    // Effects: removes the chip at the specified index from the list of chips
    public void removeChips(int index) {
        if (index >= 0 && index < chips.size()) {
            chips.remove(index);
        }

        eventLog.logEvent(new Event("Chip Removed from List"));

    }


    // Effects: calculates the total amount of money represented
    // by all the chips in the list
    public double calculateTotalMoney() {


        double totalMoney = 0.0;
        for (Chip chip : chips) {
            totalMoney += chip.getValue();
        }

        eventLog.logEvent(new Event("Total Money Calculated"));

        return totalMoney;


    }


    // Effects: returns the list of chips
    public ArrayList<Chip> getChips() {

        eventLog.logEvent(new Event("Chip List Viewed"));

        return chips;
    }

    // EFFECTS: converts the chips into a JsonObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("chips", chipsToJson());
        return json;
    }

    // EFFECTS: converts the list of chips into a json Array
    public JSONArray chipsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Chip chip : chips) {
            jsonArray.put(chip.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: prints log to the console
    public void printEventLog() {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Event Log:");
        Iterator<Event> iterator = eventLog.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            System.out.println(event);
        }
    }

}