package persistence;

import model.Chip;
import model.ChipCounter;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads chipCounter from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ChipCounter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChipCounter(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses chipCounter from JSON object and returns it
    private ChipCounter parseChipCounter(JSONObject jsonObject) {
        ChipCounter cc = new ChipCounter();
        addChips(cc, jsonObject);
        return cc;
    }

    // MODIFIES: cc
    // EFFECTS: parses chips from JSON object and adds them to chipCounter
    private void addChips(ChipCounter cc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("chips");
        for (Object json : jsonArray) {
            JSONObject nextChip = (JSONObject) json;
            addChip(cc, nextChip);
        }
    }

    // MODIFIES: cc
    // EFFECTS: parses Chip from JSON object and adds it to chipCounter
    private void addChip(ChipCounter cc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double value = jsonObject.getDouble("value");
        Chip chip = new Chip(name, value);
        cc.addChips(chip);
    }
}
