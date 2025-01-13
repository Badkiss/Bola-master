package com.example.bola;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HelloController {

   private Server server;

    @FXML
    private Button start;

    @FXML
   private ImageView imagenBola;

    @FXML
    public void initialize() {
        server = new Server(12345,imagenBola);
    }
    @FXML
    protected void onStart() {
        if (start.getText().equals("Start")){
            server.getBola().setEnMovimiento(true);
            start.setText("Stop");
            Thread hiloServer=new Thread(server);
            hiloServer.start();
        }else{
            start.setText("Start");
            server.getBola().setEnMovimiento(false);
        }
    }
}