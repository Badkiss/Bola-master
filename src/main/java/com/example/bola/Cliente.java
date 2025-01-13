package com.example.bola;

import java.net.InetAddress;

public class Cliente {
    InetAddress ip;
    int puerto;
    Cliente(InetAddress ip,int puerto){
        this.ip=ip;
        this.puerto=puerto;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPuerto() {
        return puerto;
    }
}
