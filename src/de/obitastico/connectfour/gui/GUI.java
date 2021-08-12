package de.obitastico.connectfour.gui;

import de.obitastico.connectfour.game.Board;
import de.obitastico.connectfour.game.Computer;
import de.obitastico.connectfour.game.Human;
import de.obitastico.connectfour.game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    static Player player1 = new Computer('X');
    static Player player2 = new Human('O');
    static Board board = new Board(player1, player2);
    static Player active_player = player1;

    static JFrame frame = new JFrame();
    static JPanel title = new JPanel();
    static JPanel button = new JPanel();
    static JLabel text = new JLabel();
    static JButton[][] buttons = new JButton[board.height][board.width];

    public GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,700);
        frame.getContentPane().setBackground(new Color(1,1,1));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        text.setBackground(new Color(1, 1, 1));
        text.setForeground(new Color(255,255,255));
        text.setFont(new Font("Consolas", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("Connect Four!");
        text.setOpaque(true);

        title.setLayout(new BorderLayout());
        title.setBounds(0,0, 800, 100);

        button.setLayout(new GridLayout(6,7));
        button.setBackground(new Color(0,0,0));

        for(int i = 0;i < 6;i++){
            for(int j = 0;j < 7;j++){
                buttons[i][j] = new JButton();
                button.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("Consolas", Font.BOLD, 75));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }

        title.add(text);
        frame.add(title, BorderLayout.NORTH);
        frame.add(button);
    }

    public static void reset() {
        player1 = new Computer('X');
        player2 = new Human('O');
        board = new Board(player1, player2);

        for(int i = 0;i < board.height;i++){
            for(int j = 0; j < board.width;j++){
                buttons[i][j].setText("");
            }
        }
        switch_player();

        text.setText("Current Player: ");
    }

    public static void switch_player(){
        active_player = active_player.symbol == player1.symbol ? player2 : player1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
