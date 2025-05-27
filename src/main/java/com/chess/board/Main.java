package com.chess.board;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        System.out.println(board.toString());

        Player player = new Player(board.getHand("white"));
        System.out.println(player.boardValue());

    }
}