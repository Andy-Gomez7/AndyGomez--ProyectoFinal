package controller;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class controller {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Rectangle bird;

    private long UltmActu = 0;

    private double velocidad = 0.2;

    private double aceleracion = 0.5;

    Random random = new Random();

    public void initialize(){
        crearTuberia();
        AnimationTimer animacion = new AnimationTimer() {

            @Override
            public void handle(long tiempo) {
                if(tiempo - UltmActu >= 16_000_000){
                    
                    if(!verificarGameOver())
                    {
                        gravedad();
                        
                    }

                    UltmActu = tiempo;
                }
            }
            
        };

        animacion.start();
    }

    @FXML
    public void volar(KeyEvent event) {
        
        if(event.getCode() == KeyCode.SPACE){
            moverBird(-50);
            aceleracion = 0;
            
        }
    }

    public Boolean verificarGameOver(){
        return verificarLimites(); 
    }

    public Boolean verificarLimites(){
        return bird.getBoundsInParent().getMaxY() >= (anchorpane.getHeight()-10) || bird.getBoundsInParent().getMinY() <= 10;
    }

    @FXML
    public void moverBird(double posicion) {
        bird.setLayoutY(bird.getLayoutY() + posicion);
    }

    public void GameOver(){
        System.out.println("Game over");
    }

    public void gravedad(){
        aceleracion += velocidad;
        moverBird(aceleracion);
    }

    private void crearTuberia(){
        int paneAncho = 600;
        int paneLargo = 400;

        int espacio = 100;
        int ancho = 30;
        int altoSup = (int)(random.nextInt( 50, 251));
        int altoInf = (int)paneLargo - altoSup - espacio;
        int PosX = (int)paneAncho-200; //el 200 pa que se vea

        Rectangle tuberiaSup = new Rectangle(PosX, 0, ancho, altoSup);
        Rectangle tuberiaInf = new Rectangle(PosX, altoSup+espacio, ancho, altoInf);

        anchorpane.getChildren().addAll(tuberiaSup,tuberiaInf);
    }

    private void moverTuberia(Rectangle tuberia, double posicion){
        tuberia.setLayoutX(tuberia.getLayoutX()+posicion);
    }

}