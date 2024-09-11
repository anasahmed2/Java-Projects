package ui;

import model.ChipCounter;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// Start window for the Chip Counter GUI which contains the visual component
// along with the start app button and load button. The load button loads the
// saved file using json reader and will open up the main window displaying the
// saved chips inside the list of chips. The start app button will allow the user
// to load into the second window which will contain all the other components of the
// application.
public class StartWindow extends JFrame implements ActionListener {

    private final JsonReader jsonReader;

    private ChipCounter chipCounter;
    private static final String JSON_STORE = "./data/ChipCounter.json";

    public StartWindow() {

        chipCounter = new ChipCounter();
        jsonReader = new JsonReader(JSON_STORE);
        initialize();


    }

    // MODIFIES: this
    // EFFECTS: initializes the components of the GUI
    private void initialize() {

        setTitle("Chip Counter Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        ImageIcon imageIcon = new ImageIcon("./data/poker image.png");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.CENTER);

        Button startButton = startButton();
        buttonPanel.add(startButton);

        Button loadButton = loadButton();
        buttonPanel.add(loadButton);

        ImageIcon image = new ImageIcon("./data/gold.png");
        setIconImage(image.getImage());
    }

    // EFFECTS: creates the load button
    private Button loadButton() {

        Button loadButton = new Button("Load Game");
        loadButton.setBackground(Color.BLACK);
        loadButton.setForeground(Color.white);
        loadButton.addActionListener(this);
        loadButton.setPreferredSize(new Dimension(100, 50));

        return loadButton;

    }

    // EFFECTS: creates the start button
    private Button startButton() {

        Button startButton = new Button("New Game");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.white);
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(100, 50));

        return startButton;

    }

    // MODIFIES: this
    // EFFECTS: handles clicking the button
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("New Game")) {
            dispose();
            Game game = new Game();
            game.setVisible(true);
        }

        if (e.getActionCommand().equals("Load Game")) {
            loadGame();
        }

    }


    // EFFECTS: loads the game using json Reader,
    // and also loads into the saved chip types
    private void loadGame() {
        try {
            chipCounter = jsonReader.read();
            JOptionPane.showMessageDialog(this,
                    "Game Data Loaded Successfully!", "Load Successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            Game game = new Game();
            game.setChipCounter(chipCounter);
            game.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Unable to load game data!", "Load Failed", JOptionPane.ERROR_MESSAGE);
        }
    }



}
