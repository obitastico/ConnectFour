package de.obitastico.connectfour.gui;

import javax.swing.*;

public class ComputerThread implements Runnable {

    @Override
    public void run() {

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                GUI.do_turn();
            }
        });
    }
}
