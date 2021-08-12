package de.obitastico.connectfour.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow implements ActionListener {
    static JFrame frame = new JFrame();
    static JButton playAgain = new JButton("Play Again");
    static JButton exit = new JButton("Exit");
    static JPanel textfield = new JPanel();
    static JLabel text = new JLabel();

    public GameOverWindow(char winner){
        playAgain.setBounds(40,70,95,20);
        playAgain.setFocusable(false);
        playAgain.addActionListener(this);
        playAgain.setHorizontalAlignment(JButton.CENTER);

        exit.setBounds(150,70,95,20);
        exit.setFocusable(false);
        exit.addActionListener(this);
        exit.setHorizontalAlignment(JButton.CENTER);

        if(winner != 't'){
            text.setText(winner + " wins!");
        } else {
            text.setText("Tie");
        }

        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font(null, Font.BOLD, 20));
        text.setOpaque(true);

        textfield.setLayout(new BorderLayout());
        textfield.setBounds(105,20, 80, 30);
        textfield.add(text);

        frame.add(playAgain);
        frame.add(exit);
        frame.setBackground(new Color(1,1,1));
        frame.add(textfield);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,150);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(0);
        } else {
            frame.setVisible(false);
            GUI.reset();
        }
    }
}
