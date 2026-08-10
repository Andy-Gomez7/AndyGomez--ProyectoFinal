package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utils.ArchivoUtil;

public class controllerGameOver {
    
    @FXML
    private ListView<String> LVranking;

    @FXML
    private Label lbPuntajeObtenido;

    private ArchivoUtil archivo;

    private int puntuacion;

    public void initialize(){
        archivo = new ArchivoUtil("Puntaje.txt");
        setPuntuacion();
        setRanking();
    }

    public void setPuntuacion(){
        puntuacion = controllerInGame.getPuntuacion();
    }

    public void setRanking(){
        lbPuntajeObtenido.setText(lbPuntajeObtenido.getText()+puntuacion);
        archivo.Escribir(Integer.toString(puntuacion));
        ObservableList<String> puntuaciones = archivo.LeerPuntuacion().sorted();
        puntuaciones = convertir(puntuaciones);
        LVranking.setItems(puntuaciones);
        System.out.println(puntuacion);
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