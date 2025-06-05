package com.chess.board;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        while (true) {
            System.out.println(board.toString());

            System.out.println("Enter your move: ");
            String move = scanner.nextLine();
        }
    }
}