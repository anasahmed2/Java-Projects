package ui;

import persistence.Writable;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new ChipCounterApp();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }


}
