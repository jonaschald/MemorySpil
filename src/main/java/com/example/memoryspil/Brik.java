package com.example.memoryspil;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Brik extends ImageView {
    private Image forside, bagside;

    public Brik(int x, int y, String filnavn) {
        forside = new Image(getClass().getResource(filnavn).toString());
        bagside = new Image(getClass().getResource("bagside.png").toString());
        setImage(bagside);

        setX(x);
        setY(y);
    }

    public void klik() throws InterruptedException {
        ScaleTransition st = new ScaleTransition(Duration.millis(500), this);
        st.setToX(0);
        ScaleTransition st2 = new ScaleTransition(Duration.millis(500), this);
        st2.setToX(1);

        SequentialTransition st3 = new SequentialTransition(st, st2);
        st3.play();
    }
}
