module com.example.memoryspil {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.memoryspil to javafx.fxml;
    exports com.example.memoryspil;
}