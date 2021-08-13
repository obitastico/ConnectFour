package de.obitastico.connectfour.gui;

import javax.swing.*;

public class ComputerThread implements Runnable {

    @Override
    public void run() {
        SwingUtilities.invokeLater(GUI::do_turn);
    }
}
