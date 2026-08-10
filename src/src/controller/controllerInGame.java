package controller;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Obstaculos;
import utils.NavegacionUtil;

public class controllerInGame {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ImageView bird;

    @FXML
    private ImageView tutorial;

    @FXML
    private Label contadorPuntos;
    
    AnimationTimer animacion;

    private int paneAncho = 326;
    
    private int paneLargo = 600;

    private long UltmActu = 0;

    private long UltmActuTuberia = 0;

    private double velocidad = 0.2;

    private double aceleracion = 0.5;

    private static int puntuacion = 0;

    ArrayList<ImageView> tuberias;

    ArrayList<ImageView> puntuadas;

    Obstaculos obstaculos;

    public void initialize(){
        tuberias = new ArrayList<>();
        puntuadas = new ArrayList<>();
        anchorpane.setFocusTraversable(true);

        obstaculos = new Obstaculos(bird,anchorpane, paneLargo, paneAncho);

        animacion = new AnimationTimer() {

            @Override
            public void handle(long tiempo) {
                
                if(tiempo - UltmActu >= 16_000_000){
                    
                    if(tiempo - UltmActuTuberia >= 2_000_000_000){
                        efectoDesvanecido();

                        tuberias.addAll(obstaculos.crearTuberia());

                        contadorPuntos.toFront();

                        UltmActuTuberia = tiempo;
                    }
                    if(obstaculos.verificarGameOver(tuberias))
                    {
                        GameOver();
                    }
                    else{
                        gravedad();
                            
                    }

                    obstaculos.simularTuberias(tuberias);
                    Puntiacion(tuberias);

                    UltmActu = tiempo;
                }
            }
            
        };

        animacion.start();
    }

    public static int getPuntuacion(){
        return puntuacion;
    }

    public static void setPuntuacion(int Puntuacion){
        
        if(Puntuacion >=0){
            puntuacion = Puntuacion;
        }
    }

    @FXML
    public void volar(KeyEvent event) {
        if(obstaculos.verificarGameOver(tuberias)){
            GameOver();
        }else if(event.getCode() == KeyCode.SPACE){
            moverBird(-40);
            aceleracion = 0;
        }
    }

    @FXML
    public void mVolar(MouseEvent event) {
        if(obstaculos.verificarGameOver(tuberias)){
            GameOver();
        }else{
            moverBird(-35);
            aceleracion = 0;
            
        }
    }

    @FXML
    public void moverBird(double posicion) {
        bird.setLayoutY(bird.getLayoutY() + posicion);
    }

    public void GameOver(){
        if(obstaculos.verificarGameOver(tuberias)){
            animacion.stop();
            NavegacionUtil nav = new NavegacionUtil();
            nav.Navegacion(anchorpane, "/view/GameOVer.fxml");
            System.out.println("Game over");
        }
        
    }

    public void gravedad(){
        aceleracion += velocidad;
        moverBird(aceleracion);
    }



    private void efectoDesvanecido(){
        FadeTransition fade = new FadeTransition(javafx.util.Duration.seconds(1.5), tutorial);

        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        fade.setOnFinished(e -> {
            tutorial.setVisible(false);
        });

     
        fade.play();
    }

    public void Puntiacion(ArrayList<ImageView> tuberias){
        if(tuberias == null || tuberias.isEmpty()){
            return;
        }

        if(!puntuadas.isEmpty()){
            for(ImageView puntuada : puntuadas){
                if(tuberias.get(0).equals(puntuada)){
                    return;
                }
            }
            
        }

        if(bird.getLayoutX() >= tuberias.get(0).getLayoutX() + tuberias.get(0).getFitWidth()){
            puntuacion++;
            contadorPuntos.setText(Integer.toString(puntuacion));
            contadorPuntos.toFront();
            System.out.print(puntuacion+"\n");

            puntuadas.add(tuberias.get(0));
        }   
    }
}   