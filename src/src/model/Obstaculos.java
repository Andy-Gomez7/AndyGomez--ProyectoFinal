package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Obstaculos {

    private ImageView bird;

    private AnchorPane anchorpane;

    private Random random;

    private int paneLargo;

    private int paneAncho;

    public Obstaculos(ImageView Bird, AnchorPane AnchorPane, int PaneLargo, int PaneAncho){
        bird = Bird;
        anchorpane = AnchorPane;
        paneAncho = PaneAncho;
        paneLargo = PaneLargo;
        random = new Random();
    }    

    private Boolean verificarLimites(){
        return bird.getBoundsInParent().getMaxY() >= (anchorpane.getHeight()-110) || bird.getBoundsInParent().getMinY() <= 20;
    }

    private Boolean verificarTuberias(ArrayList<ImageView> tuberias){
        return tuberias.get(0).getBoundsInParent().intersects(bird.getBoundsInParent()) || tuberias.get(1).getBoundsInParent().intersects(bird.getBoundsInParent());
    }

    public Boolean verificarGameOver(ArrayList<ImageView> tuberias){
        if(tuberias == null || tuberias.isEmpty()){
            return false;
        } else{
            return verificarLimites() || verificarTuberias(tuberias);    
        }
        
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

    public ArrayList<ImageView> crearTuberia(){
        int espacio = 230;
        int altoSup =(int)(random.nextInt( 50, 351));
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
}