package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChipTest {

    private Chip chip;

    private JSONObject jsonObject;

    @BeforeEach
    public void setUp() {
        chip = new Chip("Red", 5);
        jsonObject = new JSONObject();
    }

    @Test
    public void testGetName() {
        assertEquals("Red", chip.getName());
    }

    @Test
    public void testGetValue() {
        assertEquals(5, chip.getValue());
    }

    @Test
    public void testToJson() {
        jsonObject.put("name", "Red");
        jsonObject.put("value", 5);

        assertEquals(jsonObject.toString(), chip.toJson().toString());
    }
}
