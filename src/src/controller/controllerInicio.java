package controller;

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
    private Button tienda;

    @FXML
    private Button logros;

    @FXML
    private Button salir;

    public void initialize(){
        btnDiseno();
    }   

    private void btnDiseno(){
        Image imgPlay = new Image("/img/botonPlay.png");
        ImageView imgVPlay = new ImageView(imgPlay);
        play.setGraphic(imgVPlay);
        
        Image imgTienda = new Image("/img/botonTienda.png");
        ImageView imgVTienda = new ImageView(imgTienda);
        tienda.setGraphic(imgVTienda);

        Image imgLogros = new Image("/img/botonLogros.png");
        ImageView imgVLogros = new ImageView(imgLogros);
        logros.setGraphic(imgVLogros);

        Image imgSalir = new Image("/img/botonSalir.png");
        ImageView imgVSalir = new ImageView(imgSalir);
        salir.setGraphic(imgVSalir);
    } 

    public void play(Event event){
        NavegacionUtil nav = new NavegacionUtil();
        nav.Navegacion(anchorpane);
    }
}