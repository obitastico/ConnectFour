package de.obitastico.connectfour.game;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static de.obitastico.connectfour.Helpers.copy_board;
import static de.obitastico.connectfour.Helpers.eval_board;

public class Computer extends Player {
    public static int starting_depth = 13;

    public Computer(char symbol) {
        super(symbol, true);
    }

    @Override
    public int get_pos(Board board){
        boolean max_player = board.player1.symbol == this.symbol;
        Map<String, Integer> best_move = this.minimax(copy_board(board), max_player, starting_depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return best_move.get("pos");
    }

    private Map<String, Integer> minimax(@NotNull Board board, boolean max_player, int depth, int alpha, int beta){
        if (board.is_game_over() || depth == 0){
            Map<String, Integer> move = new HashMap<>();
            move.put("eval", eval_board(board, depth, starting_depth));
            return move;
        }

        Map<String, Integer> best_move = new HashMap<>();
        best_move.put("eval", max_player ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        for (int i = 0; i < board.width; i++) {
            if (board.is_empty(i)) {
                board.place_symbol(i, (max_player ? board.player1 : board.player2).symbol);

                Map<String, Integer> move = this.minimax(copy_board(board), !max_player, depth - 1, alpha, beta);
                move.put("pos", i);
                board.undo();

                if (depth == starting_depth) {
                    System.out.println("move = " + move);
                }

                if (max_player) {
                    best_move = move.get("eval") > best_move.get("eval") ? move : best_move;
                    alpha = Math.max(alpha, move.get("eval"));
                } else {
                    best_move = move.get("eval") < best_move.get("eval") ? move : best_move;
                    beta = Math.min(beta, move.get("eval"));
                }

                if (beta <= alpha) {
                    break;
                }
            }
        }
        return best_move;
    }
}
