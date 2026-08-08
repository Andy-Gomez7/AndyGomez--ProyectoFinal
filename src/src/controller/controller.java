package controller;

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

    public void initialize(){
        
        AnimationTimer animacion = new AnimationTimer() {

            @Override
            public void handle(long tiempo) {
                if(tiempo - UltmActu >= 16_000_000){
                    
                    if(!verificarLimites())
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
        if(verificarLimites()){
            GameOver();
        }
        else{
            if(event.getCode() == KeyCode.SPACE){
                moverBird(-50);
                aceleracion = 0;
            }
        }
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
}