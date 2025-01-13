package com.example.bola;

import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

public class Bola extends Task<Coordenadas> {
  private ImageView imagenBola;
  private Coordenadas siguientePosicion;
  private boolean enMovimiento;
  private Server server;
   Bola(ImageView imagenBola,Server server){
       this.server=server;
       this.imagenBola=imagenBola;
       enMovimiento=false;
       siguientePosicion=new Coordenadas(imagenBola.getLayoutX(),imagenBola.getLayoutY());
       valueProperty().addListener((observableValue, coordenadas, newCoordenadas) -> {
           imagenBola.setLayoutX(newCoordenadas.getX());
           imagenBola.setLayoutY(newCoordenadas.getY());
           server.enviarBrodcast(newCoordenadas);
       });
   }
    @Override
    protected Coordenadas call() {
        Coordenadas nueva;
        int sumaX =5;
        int sumaY =5;
        int tamanyoBola=50;
        int pantalla=600;
        double valorX;
        double valorY;
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (enMovimiento){
                valorX=imagenBola.getLayoutX() +sumaX;
                valorY=imagenBola.getLayoutY() +sumaY;
                if (valorX > pantalla -tamanyoBola || valorX<0){
                    sumaX=sumaX*-1;
                }
                if (valorY > pantalla -tamanyoBola || valorY<0){
                    sumaY=sumaY*-1;
                }
                nueva=new Coordenadas(valorX,valorY);
                updateValue(nueva);
            }
        }
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }

}
