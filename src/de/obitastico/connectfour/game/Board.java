package de.obitastico.connectfour.game;

import static de.obitastico.connectfour.Helpers.compare;

public class Board {

    private final char[][] board;
    public Player player1;
    public Player player2;
    public int width;
    public int height;
    public int last_row_num;

    public Board(Player player1, Player player2) {
        this.board = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        for (char[] row : this.board) {
            assert row.length == this.board[0].length : "Board ist nicht richtig initialisiert";
        }
        
        this.player1 = player1;
        this.player2 = player2;
        this.width = board[0].length;
        this.height = board.length;
        this.last_row_num = -1;
    }

    public int get_winner(){
        if (this.is_full()){
            return 0;
        }

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (j > 2 && compare(this.board[i][j],this.board[i][j-1], this.board[i][j-2], this.board[i][j-3])){
                    return this.board[i][j] == player1.symbol ? 1 : -1;
                }

                if (i > 2 && compare(this.board[i][j],this.board[i-1][j], this.board[i-2][j], this.board[i-3][j])){
                    return this.board[i][j] == player1.symbol ? 1 : -1;
                }

                if (i > 2 && j > 2 && compare(this.board[i][j], this.board[i-1][j-1], this.board[i-2][j-2], this.board[i-3][j-3])){
                    return this.board[i][j] == player1.symbol ? 1 : -1;
                }

                if (i > 2 && j < this.width - 3 && compare(this.board[i][j], this.board[i-1][j+1], this.board[i-2][j+2], this.board[i-3][j+3])){
                    return this.board[i][j] == player1.symbol ? 1 : -1;
                }
            }
        }

        return 0;
    }

    public boolean is_game_over(){
        return this.get_winner() != 0 || this.is_full();
    }

    public boolean is_empty(int row_num){
        return this.board[0][row_num] == ' ';
    }

    public boolean is_full(){
        for (int i = 0; i < board.length; i++) {
            if (this.is_empty(i)){
                return false;
            }
        }
        return true;
    }

    public void place_symbol(int row_num, char symbol){
        for (int i = this.board.length - 1; i > -1; i--) {
            if (this.board[i][row_num] == ' '){
                this.board[i][row_num] = symbol;
                this.last_row_num = row_num;
                break;
            }
        }
    }

    public void undo() {
        if (this.last_row_num == -1){
            return;
        }

        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i][this.last_row_num] != ' '){
                this.board[i][this.last_row_num] = ' ';
                break;
            }
        }

        this.last_row_num = -1;
    }

    public char get(int row, int col){
        return this.board[row][col];
    }

    public void set(int row, int col, char symbol){
        this.board[row][col] = symbol;

    }

    public void print(){
        for (char[] chars : this.board) {
            for (int j = 0; j < this.board[0].length; j++) {
                System.out.print("  " + chars[j] + "  ");
                if (j < this.board.length) {
                    System.out.print("|");
                } else {
                    System.out.println();
                }
            }
            System.out.println(" --- + --- + --- + --- + --- + --- + --- ");
        }

        for (int i = 0; i < board[0].length; i++) {
            System.out.print("  " + i + "  ");
            if (i < this.board.length) {
                System.out.print("|");
            } else {
                System.out.println();
            }
        }
    }
}
