package com.example.memoryspil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MemorySpil extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane(), 1440, 1024);
        stage.setTitle("Memory Spil");
        stage.setScene(scene);
        stage.show();
    }
}
