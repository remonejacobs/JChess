package com.chess.board;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board.toString());

        System.out.println("Choose your color: ");
        Scanner scanner = new Scanner(System.in);
        String color = scanner.nextLine();



    }
}