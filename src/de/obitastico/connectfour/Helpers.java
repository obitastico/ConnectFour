package de.obitastico.connectfour;

import de.obitastico.connectfour.game.Board;

public class Helpers {
    public static Board cur_board;
    public static int cur_depth;
    public static int cur_starting_depth;

    public static boolean compare(char... chars){
        for (char chr : chars){
            if (chr != chars[0] || chr == ' '){
                return false;
            }
        }
        return true;
    }

    public static Board copy_board(Board board){
        Board new_board = new Board(board.player1, board.player2);

        for (int col = 0; col < board.width; col++) {
            for (int row = 0; row < board.height; row++) {
                char symbol = board.get(row, col);
                new_board.set(row, col, symbol);
            }
        }

        return new_board;
    }

    public static int eval_col(int start_row, int start_col){
        if (start_row > 0 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row - 1, start_col))){
            if (start_row > 1 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row - 2, start_col))){
                if (start_row > 2 && compare(cur_board.get(start_row - 3, start_col))){
                    return score_4(start_row, start_col);
                }
                return score_3(start_row, start_col);
            }
            return score_2(start_row, start_col);
        }

        return 0;
    }

    public static int eval_diagonal_left(int start_row, int start_col){
        if (start_col > 0 && start_row > 0 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row - 1, start_col - 1))){
            if (start_col > 1 && start_row > 1 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row - 2, start_col - 2))){
                if (start_col > 2 && start_row > 2 && compare(cur_board.get(start_row - 3, start_col - 3))){
                     return score_4(start_row, start_col);
                }
                return score_3(start_row, start_col);
            }
            return score_2(start_row, start_col);
        }

        return 0;
    }

    public static int eval_diagonal_right(int start_row, int start_col){
        if (start_row > 0 && start_col < cur_board.width - 5 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row - 1, start_col + 1))){
            if (start_row > 1 && start_col < cur_board.width - 4 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row - 2, start_col + 2))){
                if (start_row > 2 && start_col < cur_board.width - 3 && compare(cur_board.get(start_row - 3, start_col + 3))){
                    return score_4(start_row, start_col);
                }
                return score_3(start_row, start_col);
            }
            return score_2(start_row, start_col);
        }

        return 0;
    }

    public static int eval_row(int start_row, int start_col){
        if (start_col > 0 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row, start_col - 1))){
            if (start_col > 1 && compare(cur_board.get(start_row, start_col), cur_board.get(start_row, start_col - 2))){
                if (start_col > 2 && compare(cur_board.get(start_row, start_col - 3))){
                    return score_4(start_row, start_col);
                }
                return score_3(start_row, start_col);
            }
            return score_2(start_row, start_col);
        }

        return 0;
    }

    public static int eval_board(Board board, int depth, int starting_depth){
        cur_board = board;
        cur_depth = depth;
        cur_starting_depth = starting_depth;

        if (cur_board.is_full()){
            return 0;
        }

        int score = 0;

        for (int i = 0; i < cur_board.height; i++) {
            for (int j = 0; j < cur_board.width; j++) {
                return eval_col(i, j) + eval_row(i, j) + eval_diagonal_left(i, j) + eval_diagonal_right(i, j);
            }
        }

        return score;
    }

    public static int score_4(int start_row, int start_col){
        if (cur_depth == cur_starting_depth){
            return cur_board.get(start_row, start_col) == cur_board.player1.symbol ? 10000 * cur_depth : -10000 * (cur_starting_depth + cur_depth);
        }
        return cur_board.get(start_row, start_col) == cur_board.player1.symbol ? 100 * cur_depth : -100 * (cur_starting_depth + cur_depth);
    }

    public static int score_3(int start_row, int start_col){
        return cur_board.get(start_row, start_col) == cur_board.player1.symbol ? 10 : -10;
    }

    public static int score_2(int start_row, int start_col){
        return cur_board.get(start_row, start_col) == cur_board.player1.symbol ? 1 : -1;
    }
}
