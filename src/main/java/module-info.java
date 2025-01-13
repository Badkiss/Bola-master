module com.example.bola {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.bola to javafx.fxml;
    exports com.example.bola;
}