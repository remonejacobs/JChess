package com.chess.board;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player("white");
//        System.out.println("Playing as white.");

        while (true) {
            System.out.println(player.getBoard().toString());

            System.out.println("Enter your move: ");
            String move = scanner.nextLine().strip();
            try {
                player.validMove(move);
            } catch (Exception e) {
                System.out.println("INVALID MOVE!");
            }

        }
    }
}