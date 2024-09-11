package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChipCounterTest {

    private ChipCounter chipCounter;
    private Chip chip1;
    private Chip chip2;


    @BeforeEach
    public void setUp() {
        chipCounter = new ChipCounter();
        chip1 = new Chip("Red", 5);
        chip2 = new Chip("Green", 25);



    }

    @Test
    public void testAddChips() {
        chipCounter.addChips(chip1);
        assertEquals(1, chipCounter.getChips().size());
        assertEquals(chip1, chipCounter.getChips().get(0));

    }

    @Test
    public void testRemoveChips() {
        assertEquals(0, chipCounter.getChips().size());
        chipCounter.addChips(chip1);
        assertEquals(1, chipCounter.getChips().size());
        assertEquals(chip1, chipCounter.getChips().get(0));
        chipCounter.removeChips(0);
        assertEquals(0, chipCounter.getChips().size());
        chipCounter.addChips(chip1);
        chipCounter.addChips(chip2);
        assertEquals(2, chipCounter.getChips().size());
        assertEquals(chip2, chipCounter.getChips().get(1));
        chipCounter.removeChips(1);
        assertEquals(1, chipCounter.getChips().size());
        chipCounter.addChips(chip2);
        assertEquals(2, chipCounter.getChips().size());
        chipCounter.removeChips(6);
        assertEquals(2, chipCounter.getChips().size());
        chipCounter.removeChips(-5);
        assertEquals(2, chipCounter.getChips().size());

    }

    @Test
    public void testCalculateTotalMoney() {
        chipCounter.addChips(chip1);
        assertEquals(5, chipCounter.calculateTotalMoney());

    }

    @Test
    public void testToJson() {
        Chip chip1 = new Chip("Red", 5);
        Chip chip2 = new Chip("Blue", 10);

        chipCounter.addChips(chip1);
        chipCounter.addChips(chip2);

        JSONObject jsonObject = new JSONObject();

        JSONArray chipsArray = new JSONArray();
        chipsArray.put(chip1.toJson());
        chipsArray.put(chip2.toJson());
        jsonObject.put("chips", chipsArray);

        assertEquals(jsonObject.toString(), chipCounter.toJson().toString());
    }

    @Test
    public void testChipsToJson() {
        Chip chip1 = new Chip("Red", 5);
        Chip chip2 = new Chip("Blue", 10);

        chipCounter.addChips(chip1);
        chipCounter.addChips(chip2);

        JSONArray jsonObject = new JSONArray();
        JSONObject chip1Json = new JSONObject();
        chip1Json.put("name", "Red");
        chip1Json.put("value", 5);
        JSONObject chip2Json = new JSONObject();
        chip2Json.put("name", "Blue");
        chip2Json.put("value", 10);
        jsonObject.put(chip1Json);
        jsonObject.put(chip2Json);

        JSONArray chipsJsonArray = chipCounter.chipsToJson();

        assertEquals(jsonObject.toString(), chipsJsonArray.toString());
    }



}
