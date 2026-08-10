package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import utils.NavegacionUtil;

public class controllerTienda {
    @FXML
    private AnchorPane anchorpane;

    public void volver(Event event){
        NavegacionUtil nav = new NavegacionUtil();
        nav.Navegacion(anchorpane, "/view/inicio.fxml");
    }
}