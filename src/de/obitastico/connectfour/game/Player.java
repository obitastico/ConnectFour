package de.obitastico.connectfour.game;

public class Player {
    public char symbol;
    public boolean com;

    public Player(char symbol, boolean com){
        this.symbol = symbol;
        this.com = com;
    }

    public int get_pos(Board board){
        return -1;
    }
}
