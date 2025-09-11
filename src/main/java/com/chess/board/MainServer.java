package com.chess.board;

import io.javalin.Javalin;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.JSONObject;

import java.util.Scanner;

public class MainServer {
    private final Javalin server;
    Player player = new Player("white");

    public static void main(String[] args) {
        new MainServer();
    }

    public MainServer() {

        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        }).start(7000);

        server.post("/newGame", ctx -> {
            player = new Player("white");
        });

        server.post("/move", ctx -> {
            String move = ctx.body();

            JSONObject json = new JSONObject(move);
            move = json.getString("move");
            JSONObject moveSet = player.validMove(move);
            System.out.println(moveSet);
            ctx.json(moveSet.toMap());
        });
    }
}