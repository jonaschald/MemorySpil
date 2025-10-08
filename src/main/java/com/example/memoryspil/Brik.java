package com.example.memoryspil;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Brik extends ImageView {
    private Image forside, bagside;
    private Brik par;

    public Brik(int x, int y, String filnavn) {
        forside = new Image(getClass().getResource(filnavn).toString());
        bagside = new Image(getClass().getResource("bagside.png").toString());
        setImage(bagside);

        setX(x);
        setY(y);
    }

    public Brik(int x, int y, String filnavn, Brik par) {
        forside = new Image(getClass().getResource(filnavn).toString());
        bagside = new Image(getClass().getResource("bagside.png").toString());
        setImage(bagside);

        setX(x);
        setY(y);

        this.par = par;
    }

    public void klik() {
        visForside();
    }

    private void klikAnimation(Image billede) {
        ScaleTransition skalerNed = new ScaleTransition(Duration.millis(500), this);
        skalerNed.setToX(0);
        skalerNed.setOnFinished(e -> {
            setImage(billede);
        });

        ScaleTransition skalerOp = new ScaleTransition(Duration.millis(500), this);
        skalerOp.setToX(1);

        SequentialTransition animation = new SequentialTransition(skalerNed, skalerOp);
        animation.play();
    }

    public void visForside() {
        klikAnimation(forside);
    }

    public void visBagside() {
        klikAnimation(bagside);
    }
}
