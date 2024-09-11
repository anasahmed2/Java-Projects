package persistence;

import model.Chip;
import model.ChipCounter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends persistence.JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ChipCounter cc = new ChipCounter();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ChipCounter cc = new ChipCounter();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(cc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            cc = reader.read();
            assertEquals(0, cc.getChips().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ChipCounter cc = new ChipCounter();
            cc.addChips(new Chip("red", 5));
            cc.addChips(new Chip("blue", 10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(cc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            cc = reader.read();
            List<Chip> chips = cc.getChips();
            assertEquals(2, chips.size());
            checkThingy("red", 5, chips.get(0));
            checkThingy("blue", 10, chips.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
