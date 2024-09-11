package ui;

import model.Chip;
import model.ChipCounter;

import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// THe second window for the GUI application that contains five buttons.
// Add chips, view chips, view total money, save, and select chips. Add Chips
// will add the selected chip type and number of chips to the list of chips.
// View chips button will allow the user to see the list of chips. The total money
// button will allow the user to see the total money contained within the list.
// The save button will save the current state of the application. The select
// chips button will allow the user to see the certain chip type within their list
// of chips.
public class Game extends JFrame implements ActionListener, WindowListener {

    final JsonWriter jsonWriter;
    private ChipCounter chipCounter;
    private static final String JSON_STORE = "./data/ChipCounter.json";
    private JComboBox<String> chipTypeComboBox;
    private JTextField chipCountField;
    private DefaultListModel<String> chipListModel;

    public Game() {

        jsonWriter = new JsonWriter(JSON_STORE);
        chipCounter = new ChipCounter();
        initializeUi();
        addWindowListener(this);

    }


    // MODIFIES: this
    // EFFECTS: initializes the components of the GUI
    private void initializeUi() {

        setTitle("Chip Counter App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon("./data/gold.png");
        setIconImage(image.getImage());
        getContentPane().setBackground(Color.green);

        JPanel addChipsPanel = addChipsPanel();
        add(addChipsPanel, BorderLayout.NORTH);

        chipListModel = new DefaultListModel<>();
        JList<String> chipList = new JList<>(chipListModel);
        JScrollPane listScrollPane = new JScrollPane(chipList);
        add(listScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = panel();

        add(buttonPanel, BorderLayout.SOUTH);

    }

    // EFFECTS: creates the bottom panel for the GUI which
    // contains all the buttons
    private JPanel panel() {

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = newButton();
        buttonPanel.add(addButton);

        JButton viewChipTypesButton = viewButton();
        buttonPanel.add(viewChipTypesButton);

        JButton selectedChipTypes = selectButton();
        buttonPanel.add(selectedChipTypes);

        JButton calculateTotalMoney = totalMoneyButton();
        buttonPanel.add(calculateTotalMoney);

        JButton saveButton = saveButton();
        buttonPanel.add(saveButton);

        return buttonPanel;

    }

    // EFFECTS: creates a button for addChips
    private JButton newButton() {

        JButton addButton = new JButton("Add Chips");
        addButton.addActionListener(this);

        return addButton;

    }

    // EFFECTS: creates a button for view chip types
    private JButton viewButton() {

        JButton viewChipTypesButton = new JButton("View Chips");
        viewChipTypesButton.addActionListener(this);

        return viewChipTypesButton;

    }

    // EFFECTS: creates a button to select different chip types
    private JButton selectButton() {

        JButton selectedChipTypes = new JButton("Select Chips");
        selectedChipTypes.addActionListener(this);

        return selectedChipTypes;

    }


    // EFFECTS: creates a button to see the total money the user has
    private JButton totalMoneyButton() {

        JButton calculateTotalMoney = new JButton("Total Money");
        calculateTotalMoney.addActionListener(this);

        return calculateTotalMoney;

    }

    // EFFECTS: creates the save button
    private JButton saveButton() {

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        return saveButton;
    }

    // create the panel at the top of the window for adding
    // the different type of chip and how many chips to add
    private JPanel addChipsPanel() {
        JPanel addChipsPanel = new JPanel();
        addChipsPanel.setLayout(new GridLayout(2, 2));

        JLabel chipTypeLabel = new JLabel("Add Chip Type:");
        chipTypeComboBox = new JComboBox<>(new String[]{"Red", "Blue", "Green", "Black", "White"});
        addChipsPanel.add(chipTypeLabel);
        addChipsPanel.add(chipTypeComboBox);

        JLabel chipCountLabel = new JLabel("Chip Count:");
        chipCountField = new JTextField();
        addChipsPanel.add(chipCountLabel);
        addChipsPanel.add(chipCountField);

        return addChipsPanel;
    }

    // EFFECTS: add chips to the list of chips
    private void addChipsActionPerformed() {
        String chipTypeName = (String) chipTypeComboBox.getSelectedItem();
        int chipCount = Integer.parseInt(chipCountField.getText());

        Map<String, Double> chipValues = new HashMap<>();
        chipValues.put("Red", 5.0);
        chipValues.put("Blue", 10.0);
        chipValues.put("Green", 25.0);
        chipValues.put("Black", 50.0);
        chipValues.put("White", 100.0);

        double value;
        value = chipValues.getOrDefault(chipTypeName, 0.0);

        for (int i = 0; i < chipCount; i++) {
            chipCounter.addChips(new Chip(chipTypeName, value));
        }

        chipCountField.setText("");
    }

    // EFFECTS: allows the user to see the list of chips
    // which displays the name of the chip and its value
    private void viewChipTypesActionPerformed() {
        ArrayList<Chip> chips = chipCounter.getChips();
        chipListModel.clear();

        if (chips.isEmpty()) {
            chipListModel.addElement("You Have An Empty List!");
        } else {
            for (Chip chip : chips) {
                chipListModel.addElement(chip.getName() + " - $" + chip.getValue());
            }
        }
    }

    // EFFECTS: allows the user to see the total amount of
    // money he has between all the chips
    private void totalMoneyActionPerformed() {
        double totalMoney = chipCounter.calculateTotalMoney();
        JOptionPane.showMessageDialog(this,
                "Total Money: $" + totalMoney, "Total Money", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: allows the user to select a specific chip type
    // and only view that chip type in the list of chips
    private void selectChipsActionPerformed() {
        String[] chipTypes = {"Red", "Blue", "Green", "Black", "White"};

        String selectedChipType = (String) JOptionPane.showInputDialog(
                this,
                "Choose a Chip Type To View:",
                "View Chip Type",
                JOptionPane.PLAIN_MESSAGE,
                null,
                chipTypes,
                null);

        if (selectedChipType != null) {
            ArrayList<Chip> chips = chipCounter.getChips();
            chipListModel.clear();

            boolean foundChips = false;
            for (Chip chip : chips) {
                if (chip.getName().equals(selectedChipType)) {
                    chipListModel.addElement(chip.getName() + " - $" + chip.getValue());
                    foundChips = true;
                }
            }

            if (!foundChips) {
                chipListModel.addElement("You Do Not Have This Type of Chip!");
            }
        }

    }


    // EFFECTS: allows the user to save the program
    private void saveActionPerformed() {
        try {
            jsonWriter.open();
            jsonWriter.write(chipCounter);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this,
                    "Chip Counter Saved Successfully!", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "Unable to save Chip Counter data!", "Save Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: button handling
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add Chips")) {
            addChipsActionPerformed();
        }
        if (e.getActionCommand().equals("View Chips")) {
            viewChipTypesActionPerformed();
        }

        if (e.getActionCommand().equals("Select Chips")) {
            selectChipsActionPerformed();
        }

        if (e.getActionCommand().equals("Total Money")) {
            totalMoneyActionPerformed();
        }

        if (e.getActionCommand().equals("Save")) {
            saveActionPerformed();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the chipCounter as chipCounter and
    // updates the list of chips
    public void setChipCounter(ChipCounter chipCounter) {
        this.chipCounter = chipCounter;
        viewChipTypesActionPerformed();
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        chipCounter.printEventLog();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
