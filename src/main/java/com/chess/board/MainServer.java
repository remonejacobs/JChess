package com.chess.board;

import io.javalin.Javalin;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.JSONObject;

import java.util.Scanner;

public class MainServer {
    private Javalin server;

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Player player = new Player("white");
//
//        while (true) {
//            System.out.println(player.getBoard().toString());
//
//            System.out.println("Enter your move: ");
//            String move = scanner.nextLine().strip();
//
//            if (move.equalsIgnoreCase("resign")) {
//                System.out.println("YOU LOSE!");
//                System.exit(0);
//            }
//            try {
//                player.validMove(move);
//            } catch (Exception e) {
//                System.out.println("INVALID MOVE!");
//            }
//
//        }
        new MainServer();
    }

    public MainServer() {
        Player player = new Player("white");

        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        }).start(7000);

        server.post("/move", ctx -> {
            String move = ctx.body();

            JSONObject json = new JSONObject(move);
            move = json.getString("move");
            try {
                System.out.println(move);
                player.validMove(move);
                ctx.json(new JSONObject().put("move", "valid").toString());
            } catch (Exception e) {
                ctx.status(400).json(new JSONObject().put("move", "Invalid").toString());

                return;
            }
//            System.out.println("Received move: " + move);
//            // Here you would process the move and update the game state
//            ctx.result("Move received: " + move);
        });
    }

}