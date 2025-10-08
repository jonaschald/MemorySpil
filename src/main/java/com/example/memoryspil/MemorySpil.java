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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class MemorySpil extends Application {
    private final String[] billeder = {
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
    private Brik valgtKort; // Det kort, spilleren har valgt
    private int antalTræk = 0; // Antal træk
    private boolean klik = false; // Har spilleren klikket på et kort?

    private ArrayList<Brik> alleBrikker = new ArrayList<>();

    private Text træk;

    private Stage stage;
    private Pane root;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        initGame();
    }

    private void initGame() {
        root = new Pane();

        Scene scene = new Scene(root, 1440, 750);
        stage.setTitle("Memory Spil");
        stage.setScene(scene);
        stage.show();

        Button button = new Button("Prøv igen");
        button.setLayoutX(30);
        button.setLayoutY(700);
        button.setScaleX(1.5);
        button.setScaleY(1.5);
        button.setOnMouseClicked(event -> {
            restart();
        });

        root.getChildren().add(button);

        // Vis spillerens antal træk
        træk = new Text(44, 66, "Antal træk: " + antalTræk);
        træk.setFont(new Font(36));
        root.getChildren().add(træk);

        // Tilføj kort til spillet
        int baneX = 10;
        int baneY = 5;

        tilføjKort(root, baneX, baneY);
    }

    /**
     * tilføjKort - Tilføjer x * y kort, f.eks. x = 6, y = 5 vil sige at der skabes en bane af 6 * 5 = 30 kort.
     */
    public void tilføjKort(Pane root, int x, int y) {
        int antalKort = x * y; // Antal kort der skal tilføjes

        // Er de antal kort ikke et lige tal?
        if (antalKort % 2 != 0) {
            System.out.println("x * y giver ikke et lige tal, og kan dermed ikke skabe en bane med nok par.");
            return; // Alle kort SKAL være i et par
        }
        // Er der flere antal kort end billeder?
        if (billeder.length * 2 < antalKort) return; // Man kan ikke spørge efter flere kort end man har billeder af

        //
        ArrayList<String> alleBilleder = new ArrayList<>();
        for (int i = 0; i < antalKort / 2; i++) {
            alleBilleder.add(billeder[i]);
            alleBilleder.add(billeder[i]);
        }

        Collections.shuffle(alleBilleder);

        int idx = 0;
        double i = 0.5;
        double j = 0.75;

        while (i < x) {
            while (j < y) {
                Brik b = nytKort((int)(i * 125), (int)(j * 125), alleBilleder.get(idx++));
                alleBrikker.add(b);

                root.getChildren().add(b);
                //b.visForside();

                ++j;
            }

            ++i;
            j = 0.75;
        }
    }

    /**
     * nytKort - Skab et nyt kort ved x og y med et billede som forside
     */
    public Brik nytKort(int x, int y, String filnavn) {
        Brik b = new Brik(x, y, filnavn, this);
        b.setOnMouseClicked(e -> {
            if (klik || b.parFundet) return;
            klik = true;

            b.klik();
        });

        return b;
    }

    /**
     * Vælgkort - Metoden kaldes hver gang et kort klikkes
     */
    public void vælgKort(Brik b) {
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
            if (Objects.equals(valgtKort.getBillednavn(), b.getBillednavn())) {
                valgtKort.fjern();  // Fjern dem fra spillet
                b.fjern();
            } else {
                valgtKort.visBagside(); // Vend dem om igen
                b.visBagside();
            }

            ++antalTræk;
            træk.setText("Antal træk: " + antalTræk);

            valgtKort = null;
        }

        klik = false;
    }

    public void restart() {
        antalTræk = 0;
        valgtKort = null;
        klik = false;

        root.getChildren().clear();
        initGame();
    }
}
