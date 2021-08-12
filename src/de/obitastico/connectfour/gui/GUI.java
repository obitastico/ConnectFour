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
    static Player player1;
    static Player player2;
    static Board board = new Board(new Player('X', false), new Player('O', false));
    static Player active_player;

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
        text.setOpaque(true);

        title.setLayout(new BorderLayout());
        title.setBounds(0,0, 800, 100);

        button.setLayout(new GridLayout(6,7));
        button.setBackground(new Color(0,0,0));

        for(int i = 0;i < board.height;i++){
            for(int j = 0;j < board.width;j++){
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

        reset();

        if (active_player.com){
            do_turn();
        }
    }

    public static void do_turn(){
        assert active_player.com;
        do_turn(active_player.get_pos(board));
    }

    public static void do_turn(int row_num){
        board.place_symbol(row_num, active_player.symbol);

        for (int i = board.height - 1; i > -1; i--) {
            if (buttons[i][row_num].getText().equals("")){
                buttons[i][row_num].setText(String.valueOf(active_player.symbol));
                break;
            }
        }

        if (board.is_game_over()){
            text.setText(active_player.symbol + " wins!");
            new GameOverWindow(active_player.symbol);
            return;
        }

        switch_player();

        text.setText("Current Player: " + active_player.symbol);

        if (active_player.com){
            do_turn();
        }
    }

    public static void reset() {
        player1 = new Human('X');
        player2 = new Human('O');
        board = new Board(player1, player2);

        for(int i = 0;i < board.height;i++){
            for(int j = 0; j < board.width;j++){
                buttons[i][j].setText("");
            }
        }

        switch_player();

        text.setText("Current Player: " + active_player.symbol);
    }

    public static void switch_player(){
        if (active_player == null){
            active_player = player1;
            return;
        }

        active_player = active_player.symbol == player1.symbol ? player2 : player1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        for(int i = 0;i < board.height;i++){
            for(int j = 0;j < board.width;j++){
                if(e.getSource() == buttons[i][j] && board.is_empty(j)){
                    do_turn(j);
                }
            }
        }
    }
}
