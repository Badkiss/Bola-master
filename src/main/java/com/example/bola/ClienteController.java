package com.example.bola;

import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ClienteController {
    @FXML
    Button start;
    @FXML
    ImageView imagenB;
    @FXML
    TextField textIP;
    @FXML
    TextField textPuerto;
    Cli cliente;
    @FXML
   void onStart(){
        cliente = new Cli(textIP.getText(),textPuerto.getText());
   }
}
