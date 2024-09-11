package persistence;

import model.Chip;
import model.ChipCounter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ChipCounter cc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkroom.json");
        try {
            ChipCounter cc = reader.read();
            assertEquals(0, cc.getChips().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkroom.json");
        try {
            ChipCounter cc = reader.read();
            List<Chip> chips = cc.getChips();
            assertEquals(2, chips.size());
            checkThingy("red", 5, chips.get(0));
            checkThingy("blue", 10, chips.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
