package com.example.bola;

import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Server extends Task<Void> {
    private final static int MAX_BYTES = 1500;
    private final static String COD_TEXTO = "UTF-8";
    int puerto;
   private ArrayList<Cliente> clientes;
  private   Bola bola;
   private   DatagramSocket serverSocket;
    Server(int puerto, ImageView imageView){
        this.puerto=puerto;
        clientes=new ArrayList<Cliente>();
        bola=new Bola(imageView,this);
    }

    @Override
    protected Void call(){
        try {
            Thread hiloBola=new Thread(bola);
            hiloBola.start();
            serverSocket=new DatagramSocket(puerto);
            while (true){
            byte[] recibir=new byte[MAX_BYTES];
                DatagramPacket paketeR=new DatagramPacket(recibir, recibir.length);
                serverSocket.receive(paketeR);
                Cliente cliente=new Cliente(paketeR.getAddress(),paketeR.getPort());
                clientes.add(cliente);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Bola getBola() {
        return bola;
    }
   public void enviarBrodcast(Coordenadas coordenadas){
       for (Cliente cliente:clientes){
           enviarPakete(cliente.getIp(),cliente.getPuerto(),coordenadas);
       }
    }
    public void enviarPakete(InetAddress ip,int puerto,Coordenadas coordenadas){
        try {
            byte[] coor =new byte[MAX_BYTES];
            coor=(coordenadas.getX() +":"+ coordenadas.getY()).getBytes(COD_TEXTO);
            DatagramPacket envio=new DatagramPacket(coor,coor.length,ip,puerto);
            serverSocket.send(envio);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
