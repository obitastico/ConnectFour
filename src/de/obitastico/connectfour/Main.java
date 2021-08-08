package de.obitastico.connectfour;

import de.obitastico.connectfour.game.*;

public class Main {
    public static void main(String[] args) {
        console();
    }

    public static void console() {
        Player player1 = new Computer('X');
        Player player2 = new Computer('O');
        Board board = new Board(player1, player2);
        Player active_player = player1;

        while (!board.is_game_over()) {
            System.out.printf("Current Player: %c%n", active_player.symbol);
            board.print();
            int pos = active_player.get_pos(board);
            System.out.println("pos = " + pos);
            board.place_symbol(pos, active_player.symbol);
            active_player = active_player.symbol == player1.symbol ? player2 : player1;
        }

        board.print();

        int winner = board.get_winner();
        if (winner % 2 != 0){
            System.out.printf("%c wins!%n", winner == 1 ? player1.symbol : player2.symbol);
        } else {
            System.out.println("Tie!");
        }
    }
}
