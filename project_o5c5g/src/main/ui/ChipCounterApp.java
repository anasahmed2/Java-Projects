package ui;

import model.Chip;
import model.ChipCounter;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Chip Counter App which gives the user the option
// to add chips to a list and remove them. Along with
// it gives the user the functionality to view total money
// and take a look at all the chips in the list. The new features
// added to the program are safe and load file. These two functions
// allow the user to save the list of chips and load it back up again.
public class ChipCounterApp {

    private static final String JSON_STORE = "./data/ChipCounter.json";

    private Scanner scanner;
    private ChipCounter chipCounter;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;


    public ChipCounterApp() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        chipCounter = new ChipCounter();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runChipCounter();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runChipCounter() {
        boolean keepGoing = true;
        String command;
        scanner = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("7")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Ending Task...");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Chip Counter Menu:");
        System.out.println("1. Add Chips");
        System.out.println("2. Remove Chips");
        System.out.println("3. View Chip Types");
        System.out.println("4. View Total Money");
        System.out.println("5. Load List To File");
        System.out.println("6. Save List From File");
        System.out.println("7. Exit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            addChips();
        } else if (command.equals("2")) {
            removeChips();
        } else if (command.equals("3")) {
            viewChipTypes();
        } else if (command.equals("4")) {
            viewTotalMoney();
        } else if (command.equals("5")) {
            loadProgram();
        } else if (command.equals("6")) {
            saveProgram();
        } else {
            System.out.println("Selection Not Valid!");
        }
    }


    //Effects: Prompts the user to enter a choice and returns the integer entered.
    private int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid Input!");
            scanner.next();
        }

        return scanner.nextInt();
    }

    // Effects: adds the chips to the chip counter based on the user input
    private void addChips() {
        System.out.println("Available Chip Types:");
        System.out.println("1. Red Chips - $5");
        System.out.println("2. Blue Chips - $10");
        System.out.println("3. Green Chips - $25");
        System.out.println("4. Black Chips - $50");
        System.out.println("5. White Chips - $100");
        System.out.println("Enter Chip Type (1-5):");
        int chipTypeChoice = getChoice();
        double[] chipTypeValues = {5.0, 10.0, 25.0, 50.0, 100.0};
        String[] chipTypeNames = {"Red", "Blue", "Green", "Black", "White"};

        if (chipTypeChoice >= 1 && chipTypeChoice <= 5) {
            String name = chipTypeNames[chipTypeChoice - 1];
            double value = chipTypeValues[chipTypeChoice - 1];

            System.out.println("Enter Chip Count: ");
            int chipCount = getChoice();
            for (int i = 0; i < chipCount; i++) {
                Chip chip = new Chip(name, value);
                chipCounter.addChips(chip);
            }
        } else {
            System.out.println("Invalid Input!");
        }

    }


    // Effects: removes the specified chip from the chip counter based on the user input
    private void removeChips() {
        System.out.println("Available Chip Type To Remove:");
        ArrayList<Chip> chips = chipCounter.getChips();

        for (int i = 0; i < chips.size(); i++) {
            System.out.println(i + 1 + "." + chips.get(i).getName());
        }

        System.out.println("Enter Chip Type To Remove:");
        int chipIndex = getChoice();
        chipCounter.removeChips(chipIndex - 1);
    }

    // Effects: returns a list of all different types of chips added and their value
    public void viewChipTypes() {
        System.out.println("List of Chip Types:");
        ArrayList<Chip> chips = chipCounter.getChips();

        if (chips.isEmpty()) {
            System.out.println("You Have A Empty List!");
        }

        for (Chip chip : chips) {
            System.out.println(chip.getName() + " - $" + chip.getValue());
        }
    }


    // Effects: shows the total amount of money that
    // is represented by all the chips
    private void viewTotalMoney() {
        System.out.println("Total Money: $" + chipCounter.calculateTotalMoney());
    }

    // EFFECTS: saves program to file
    public void saveProgram() {
        try {
            jsonWriter.open();
            jsonWriter.write(chipCounter);
            jsonWriter.close();
            System.out.println("Saved Chip Counter To " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable To Write To File: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads program to file
    public void loadProgram() {
        try {
            chipCounter = jsonReader.read();
            System.out.println("Loaded Chip Counter From " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable To Read From File: " + JSON_STORE);
        }
    }


}
