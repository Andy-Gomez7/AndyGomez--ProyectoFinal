package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import utils.ArchivoUtil;
import utils.NavegacionUtil;

public class controllerGameOver {
    
    @FXML
    private ListView<String> LVranking;

    @FXML
    private Label lbPuntajeObtenido;

    private ArchivoUtil archivo;

    private int puntuacion;

    @FXML
    private AnchorPane anchorpane;

    public void initialize(){
        archivo = new ArchivoUtil("Puntaje.txt");
        setPuntuacion();
        setRanking();
    }

    public void setPuntuacion(){
        puntuacion = controllerInGame.getPuntuacion();
    }

    private void setRanking(){
        lbPuntajeObtenido.setText(lbPuntajeObtenido.getText()+puntuacion);
        archivo.Escribir(Integer.toString(puntuacion));
        ObservableList<String> puntuaciones = archivo.Leertexto().sorted();
        puntuaciones = convertir(puntuaciones);
        LVranking.setItems(puntuaciones);
        System.out.println(puntuacion);
    }

    public void btnInicio(Event event){
        controllerInGame.setPuntuacion(0);
        NavegacionUtil nav = new NavegacionUtil();
        nav.Navegacion(anchorpane, "/view/inicio.fxml");
    }

    public void btnRestart(Event event){
        controllerInGame.setPuntuacion(0);
        NavegacionUtil nav = new NavegacionUtil();
        nav.Navegacion(anchorpane, "/view/inGame.fxml");
    }

    private ObservableList<String> convertir(ObservableList<String> puntuaciones){
        if(puntuaciones.isEmpty()){
            return null;
        }

        ObservableList<String> listaAux = FXCollections.observableArrayList();

        for(int i = puntuaciones.size()-1; i >= 0; i--){
            listaAux.add(puntuaciones.get(i));
        }

        return listaAux;
    }   
}