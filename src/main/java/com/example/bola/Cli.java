package com.example.bola;

import javafx.concurrent.Task;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Cli extends Task<Void> {
   private InetAddress serverIP;
   private int puertoServer;

   Cli(String serverIP, String  puerto) {
       try {
           this.serverIP= InetAddress.getByName(serverIP);
           this.puertoServer = Integer.parseInt(puerto);
       } catch (UnknownHostException e) {
           throw new RuntimeException(e);
       }

   }
    @Override
    protected Void call() {
       while(true) {

       }
    }
}
