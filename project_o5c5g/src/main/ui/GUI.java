package ui;

import javax.swing.*;

public class GUI {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartWindow startWindow = new StartWindow();
                startWindow.setVisible(true);

            }
        });

    }
}
