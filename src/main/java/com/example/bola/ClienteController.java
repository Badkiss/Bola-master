package com.example.bola;

import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ClienteController {
    @FXML
  private   Button start;
    @FXML
  private   ImageView imagenB;
    @FXML
  private   TextField textIP;
    @FXML
  private   TextField textPuerto;
  private   Cli cliente;
    @FXML
   void onStart(){
        cliente = new Cli(textIP.getText(),textPuerto.getText(),imagenB);
        Thread hiloCliente=new Thread(cliente);
        hiloCliente.start();
   }
}
