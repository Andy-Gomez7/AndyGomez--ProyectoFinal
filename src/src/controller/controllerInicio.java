package controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.NavegacionUtil;

public class controllerInicio {
    
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button play;

    @FXML
    private Button salir;

    public void initialize(){
        btnDiseno();
    }   

    private void btnDiseno(){
        Image imgPlay = new Image("/img/botonPlay.png");
        ImageView imgVPlay = new ImageView(imgPlay);
        play.setGraphic(imgVPlay);

        Image imgSalir = new Image("/img/botonSalir.png");
        ImageView imgVSalir = new ImageView(imgSalir);
        salir.setGraphic(imgVSalir);
    } 

    public void play(Event event){
        NavegacionUtil nav = new NavegacionUtil();
        nav.Navegacion(anchorpane, "/view/inGame.fxml");
    }

    public void volver(Event event){
        NavegacionUtil nav = new NavegacionUtil();
        nav.Navegacion(anchorpane, "/view/inicio.fxml");
    }

    public void salir(Event event){
        Platform.exit();
    }
}