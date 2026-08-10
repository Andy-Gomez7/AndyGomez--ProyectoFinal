package model;


import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utils.ArchivoUtil;

public class Puntuacion {
    private ListView<String> lvRanking;

    private Label lbPuntajeObtenido;

    private int puntuacion;

    private ArchivoUtil archivo;

    public Puntuacion(ListView<String> LVRanking, Label LBPuntajeObtenido, ArchivoUtil Archivo){
        lvRanking = LVRanking;
        lbPuntajeObtenido = LBPuntajeObtenido;
        archivo = Archivo;
    }   

    public void setPuntuacion(int Puntuacion){
puntuacion = Puntuacion;
    }

    public void setRanking(){
        lbPuntajeObtenido.setText(lbPuntajeObtenido.getText()+Integer.toString(puntuacion));
        archivo.Escribir(Integer.toString(puntuacion));
        lvRanking.setItems(archivo.LeerPuntuacion());
    }
}