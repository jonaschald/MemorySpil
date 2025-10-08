package com.example.memoryspil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MemorySpil extends Application {
    private String[] billeder = {
            "abe.jpg",
            "bjørn.jpg",
            "bøffel.jpg",
            "dovendyr.jpg",
            "elefant.jpg",
            "flodhest.jpg",
            "giraf.jpg",
            "gorilla.jpg",
            "hund.jpg",
            "hund2.jpg",
            "hvidtiger.jpg",
            "jaguar.jpg",
            "kanin.jpg",
            "kat.jpg",
            "katmedbriller.jpg",
            "ko.jpg",
            "kænguru.jpg",
            "næsehorn.jpg",
            "panter.jpg",
            "penguin.jpg",
            "rådyr.jpg",
            "tyr.jpg",
            "ulv.jpg",
            "vaskebjørn.jpg",
            "vildsvin.jpg"
    }; // 25 forskellige kort
    private boolean klik = false;
    private Brik kort1, kort2;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        Scene scene = new Scene(root, 1440, 1024);
        stage.setTitle("Memory Spil");
        stage.setScene(scene);
        stage.show();

        int x = 0;
        while (x < 4) {
            root.getChildren().add(nytKort(x * 125, 0, "abe.jpg"));

            ++x;
        }
    }

    public Brik nytKort(int x, int y, String filnavn) {
        Brik b = new Brik(x, y, filnavn);
        b.setOnMouseClicked(e -> {
            if (klik) {return;}
            klik = true;

            b.klik();

        });

        return b;
    }
}
