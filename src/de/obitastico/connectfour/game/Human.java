package de.obitastico.connectfour.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Human extends Player {
    public Human(char symbol)
    {
        super(symbol, false);
    }

    @Override
    public int get_pos(Board board) {
        int position;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                position = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a NUMBER beween 0-9");
                continue;
            }
            if (board.is_empty(position)) {
                break;
            } else {
                System.out.println("Please enter a Number for an empty field");
            }
        }
        return position;
    }
}
