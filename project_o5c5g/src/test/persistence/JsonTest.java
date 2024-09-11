package persistence;

import model.Chip;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String name, double value, Chip chip) {
        assertEquals(name, chip.getName());
        assertEquals(value, chip.getValue());
    }
}