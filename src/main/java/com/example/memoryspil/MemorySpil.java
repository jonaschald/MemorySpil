package com.example.memoryspil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MemorySpil extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Pane root = new Pane();

        Scene scene = new Scene(root, 1440, 1024);
        stage.setTitle("Memory Spil");
        stage.setScene(scene);
        stage.show();

        Brik b = new Brik(50, 50, "abe.jpg");
        root.getChildren().add(b);
        b.klik();
    }
}
