package com.example.bola;

import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.*;

public class Cli extends Task<Void> {
    private final static int MAX_BYTES = 1024;
    private final static String COD_TEXTO = "UTF-8";
   private InetAddress serverIP;
   private int puertoServer;

   private ImageView imagenB;

   private DatagramSocket socketCliente;

   Cli(String serverIP, String  puerto, ImageView imagenB){
       try {
           this.serverIP= InetAddress.getByName(serverIP);
           this.puertoServer = Integer.parseInt(puerto);
           this.imagenB=imagenB;
           socketCliente=new DatagramSocket();
       } catch (UnknownHostException e) {
           throw new RuntimeException(e);
       } catch (SocketException e) {
           throw new RuntimeException(e);
       }

   }
    @Override
    protected Void call() {
       try {
           byte[] envio=new byte[MAX_BYTES];
           DatagramPacket enviar= new DatagramPacket(envio, envio.length,serverIP,puertoServer);
           socketCliente.send(enviar);
           while(true) {
               byte[] recibo=new byte[MAX_BYTES];
               DatagramPacket paketeRecibo=new DatagramPacket(recibo, recibo.length);
               socketCliente.receive(paketeRecibo);
               String coordenadaBruta=new String(paketeRecibo.getData(),0,paketeRecibo.getLength(),COD_TEXTO);
               String[] separado=coordenadaBruta.split(":");
               Coordenadas coordenadas=new Coordenadas(Double.parseDouble(separado[0]),Double.parseDouble(separado[1]));
               imagenB.setLayoutX(coordenadas.getX());
               imagenB.setLayoutY(coordenadas.getY());
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }


    }
}
