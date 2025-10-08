package com.example.memoryspil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private Brik valgtKort;
    private int antalTræk = 0;
    private boolean klik = false;
    private Button prøvIgen;
    private Stage stage;
    private Pane root = new Pane();

    @Override
    public void start(Stage stage) {
       this.stage = stage;
       startingStage();
    }
    private void startingStage(){
        prøvIgen = new Button();
        prøvIgen.setText("Prøv Igen");
        prøvIgen.setOnAction(e -> restart());


        Scene scene = new Scene(root, 1440, 750);

        stage.setTitle("Memory Spil");
        stage.setScene(scene);
        stage.show();

        Button button = new Button("Prøv igen");
        button.setLayoutX(30);
        button.setLayoutY(700);
        button.setScaleX(1.5);
        button.setScaleY(1.5);
        root.getChildren().add(button);

        Text træk = new Text(44, 88, "Antal træk: " + antalTræk);
        træk.setFont(new Font(36));
        root.getChildren().add(træk);

        int x = 1;
        int y = 1;
        while (x < 10 + 1) {
            while (y < 5 + 1) {
                root.getChildren().add(nytKort(x * 125, y * 125, "abe.jpg"));
                ++y;
            }

            ++x;
            y = 1;
        }
    }

    public Brik nytKort(int x, int y, String filnavn) {
        Brik b = new Brik(x, y, filnavn, this);
        b.setOnMouseClicked(e -> {
            if (klik) return;
            klik = true;

            b.klik();
        });

        return b;
    }

    public void vælgKort(Brik b) {
        ++antalTræk;

        // Er dette kort allerede blevet valgt?
        if (valgtKort == b) {
            valgtKort = null; // Så fjern det
            klik = false;
            return; // Vi gør ikke mere
        }

        // Har vi ikke valgt et kort?
        if (valgtKort == null) {
            valgtKort = b;
        } else {
            // Er disse kort et par?
            if (valgtKort.erParret(b)) {
                valgtKort.fjern();  // Fjern dem fra spillet
                b.fjern();
            } else {
                valgtKort.visBagside(); // Vend dem om igen
                b.visBagside();
            }
            valgtKort = null;
        }

        klik = false;
    }

    public void restart() {
        antalTræk = 0;

        root.getChildren().clear();
        startingStage();
    }
}
