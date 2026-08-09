package controller;

import java.util.ArrayList;
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

    private int paneAncho = 600;
    
    private int paneLargo = 400;

    private long UltmActu = 0;

    private long UltmActuTuberia = 0;

    private double velocidad = 0.2;

    private double aceleracion = 0.5;

    ArrayList<Rectangle> tuberias;

    Random random = new Random();

    public void initialize(){
        
        tuberias = new ArrayList<>();

        AnimationTimer animacion = new AnimationTimer() {

            @Override
            public void handle(long tiempo) {
                if(tiempo - UltmActu >= 16_000_000){
                    if(tiempo - UltmActuTuberia >= 2_000_000_000){
                        
                        tuberias.addAll(crearTuberia());

                        UltmActuTuberia = tiempo;
                    }
                    if(!verificarGameOver())
                    {
                        gravedad();
                    }

                    simularTuberias(tuberias);

                    UltmActu = tiempo;
                }
            }
            
        };

        animacion.start();
    }

    @FXML
    public void volar(KeyEvent event) {
        
        if(event.getCode() == KeyCode.SPACE){
            moverBird(-40);
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

    public void simularTuberias(ArrayList<Rectangle> tuberias){
        ArrayList<Rectangle> descartados = new ArrayList<>();

        for(Rectangle rectangulo : tuberias){
            
            moverTuberia(rectangulo, -2.75);

            if(rectangulo.getLayoutX()+rectangulo.getWidth() <= 0){
                
                descartados.add(rectangulo);    

            }
        }

        tuberias.removeAll(descartados);
        anchorpane.getChildren().removeAll(descartados);
    }

    private ArrayList<Rectangle> crearTuberia(){
        int espacio = 150;
        int ancho = 30;
        int altoSup = (int)(random.nextInt( 50, 251));
        int altoInf = (int)paneLargo - altoSup - espacio;
        int PosX = (int)paneAncho;

        Rectangle tuberiaSup = new Rectangle(ancho, altoSup);
        Rectangle tuberiaInf = new Rectangle(ancho, altoInf);
            
        tuberiaSup.setLayoutX(PosX);
        tuberiaSup.setLayoutY(0);
            
        tuberiaInf.setLayoutX(PosX);
        tuberiaInf.setLayoutY(altoSup + espacio);

        ArrayList<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(tuberiaSup);
        rectangles.add(tuberiaInf);

        anchorpane.getChildren().addAll(tuberiaSup,tuberiaInf);

        return rectangles;
    }

    private void moverTuberia(Rectangle tuberia, double posicion){
        tuberia.setLayoutX(tuberia.getLayoutX()+posicion);
    }
}