package controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class controllerInGame {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ImageView bird;

    @FXML
    private ImageView tutorial;
    
    @FXML
    private Label contadorMonedas;

    @FXML
    private Label contadorPuntos;
    
    @FXML
    private ImageView imgMoneda;
    
    AnimationTimer animacion;

    private int paneAncho = 326;
    
    private int paneLargo = 600;

    private long UltmActu = 0;

    private long UltmActuTuberia = 0;

    private double velocidad = 0.2;

    private double aceleracion = 0.5;

    private int puntuacion = 0;

    private int monedas = 0;

    ArrayList<ImageView> tuberias;

    ArrayList<ImageView> puntuadas;

    Random random = new Random();

    public void initialize(){

        tuberias = new ArrayList<>();

        puntuadas = new ArrayList<>();

        animacion = new AnimationTimer() {

            @Override
            public void handle(long tiempo) {
                
                if(tiempo - UltmActu >= 16_000_000){
                    
                    if(tiempo - UltmActuTuberia >= 2_000_000_000){
                        efectoDesvanecido();

                        tuberias.addAll(crearTuberia());

                        contadorPuntos.toFront();
                        contadorMonedas.toFront();
                        imgMoneda.toFront();

                        UltmActuTuberia = tiempo;
                    }
                    if(verificarGameOver(tuberias))
                    {
                        GameOver();
                    }
                    else{
                        gravedad();
                            
                    }

                    simularTuberias(tuberias);
                    Puntiacion(tuberias);

                    UltmActu = tiempo;
                }
            }
            
        };

        animacion.start();
    }

    @FXML
    public void volar(KeyEvent event) {
        if(verificarGameOver(tuberias)){
            GameOver();
        }else if(event.getCode() == KeyCode.SPACE){
            moverBird(-40);
            aceleracion = 0;
            
        }
    }

    @FXML
    public void mVolar(MouseEvent event) {
        if(verificarGameOver(tuberias)){
            GameOver();
        }else{
            moverBird(-35);
            aceleracion = 0;
            
        }
    }

    public Boolean verificarGameOver(ArrayList<ImageView> tuberias){
        if(tuberias == null || tuberias.isEmpty()){
            return false;
        } else{
            return verificarLimites() || verificarTuberias(tuberias);    
        }
        
    }

    public Boolean verificarLimites(){
        return bird.getBoundsInParent().getMaxY() >= (anchorpane.getHeight()-110) || bird.getBoundsInParent().getMinY() <= 20;
    }

    public Boolean verificarTuberias(ArrayList<ImageView> tuberias){
        return tuberias.get(0).getBoundsInParent().intersects(bird.getBoundsInParent()) || tuberias.get(1).getBoundsInParent().intersects(bird.getBoundsInParent());
    }

    @FXML
    public void moverBird(double posicion) {
        bird.setLayoutY(bird.getLayoutY() + posicion);
    }

    public void GameOver(){
        System.out.println("Game over");
        animacion.stop();
    }

    public void gravedad(){
        aceleracion += velocidad;
        moverBird(aceleracion);
    }

    public void simularTuberias(ArrayList<ImageView> tuberias){
        ArrayList<ImageView> descartados = new ArrayList<>();

        for(ImageView tuberia : tuberias){
            
            moverTuberia(tuberia, -2.75);

            if(tuberia.getLayoutX()+tuberia.getFitWidth()+50<= 0){
                
                descartados.add(tuberia);    

            }
        }

        tuberias.removeAll(descartados);
        anchorpane.getChildren().removeAll(descartados);
    }

    private ArrayList<ImageView> crearTuberia(){
        int espacio = 230;
        int altoSup =(int)(random.nextInt( 90, 260));
        int altoInf = (int)paneLargo - altoSup - espacio;
        int PosX = (int)paneAncho;

        Image imgTuberia = new Image("/img/pipe-green.png");

        ImageView tuberiaSup = new ImageView(imgTuberia);
        tuberiaSup.setLayoutX(PosX);
        tuberiaSup.setLayoutY(0);
        tuberiaSup.setFitHeight(altoSup);

        tuberiaSup.setRotate(180);

        ImageView tuberiaInf = new ImageView(imgTuberia);
        tuberiaInf.setLayoutX(PosX);
        tuberiaInf.setLayoutY(altoSup + espacio - 109);
        tuberiaInf.setFitHeight(altoInf);

        ArrayList<ImageView> tuberias = new ArrayList<>();
        tuberias.add(tuberiaSup);
        tuberias.add(tuberiaInf);
        
        anchorpane.getChildren().addAll(tuberiaSup,tuberiaInf);

        return tuberias;

    }

    private void moverTuberia(ImageView tuberia, double posicion){
        tuberia.setLayoutX(tuberia.getLayoutX()+posicion);
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
            contadorMonedas.setText(Integer.toString(puntuacion));
            contadorPuntos.toFront();
            contadorMonedas.toFront();
            imgMoneda.toFront();
            System.out.print(puntuacion+"\n");

            puntuadas.add(tuberias.get(0));
        }   
    }
}