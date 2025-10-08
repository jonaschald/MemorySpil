package com.example.memoryspil;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Brik extends ImageView {
    private Image forside, bagside; // Billeder af forsiden og bagsiden af kortet
    public Brik par; // Det kort, dette kort er i par med
    private boolean klikkede = false; // Har spilleren klikket på kortet?
    private MemorySpil spil;

    /**
     * Brik - Skab en ny brik, der befinder sig ved x og y med navnet af et billede
     */
    public Brik(int x, int y, String filnavn, MemorySpil server) {
        forside = new Image(getClass().getResource(filnavn).toString());
        bagside = new Image(getClass().getResource("bagside.png").toString());
        setImage(bagside);

        setX(x);
        setY(y);

        spil = server;
    }

    /**
     * Brik - Skab en ny brik, der befinder sig ved x og y med navnet af et billede og den brik, det er parret med
     */
    public Brik(int x, int y, String filnavn, Brik par, MemorySpil server) {
        forside = new Image(getClass().getResource(filnavn).toString());
        bagside = new Image(getClass().getResource("bagside.png").toString());
        setImage(bagside);

        setX(x);
        setY(y);

        this.par = par;
        spil = server;
    }

    public void klik() {
        if (klikkede) return;
        klikkede = true;

        if (getImage() == bagside) {
            visForside().setOnFinished(e -> {
                spil.vælgKort(this);
            });
        } else {
            visBagside().setOnFinished(e -> {
                spil.vælgKort(this);
            });;
        }
    }

    private SequentialTransition klikAnimation(Image billede) {
        ScaleTransition skalerNed = new ScaleTransition(Duration.millis(250), this);
        skalerNed.setToX(0);
        skalerNed.setOnFinished(e -> {
            setImage(billede);
        });

        ScaleTransition skalerOp = new ScaleTransition(Duration.millis(250), this);
        skalerOp.setToX(1);
        skalerOp.setOnFinished(e -> {
            klikkede = false;
        });

        SequentialTransition animation = new SequentialTransition(skalerNed, skalerOp);
        animation.play();

        return animation;
    }

    public SequentialTransition visForside() {
        return klikAnimation(forside);
    }

    public SequentialTransition visBagside() {
        return klikAnimation(bagside);
    }

    public void skabPar() {

    }

    public boolean erParret(Brik b) {
        return par == b;
    }

    public void fjern() {
        klikkede = true; // Vi har fundet et par, så denne brik kan ikke klikkes mere

    }
}
