package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Jugador;

public class ArchivoUtil {

    private File archivo;

    public ArchivoUtil(String nombreArchivo){
        archivo = new File(nombreArchivo);
        
    }

    public void Escribir(String puntuacion) {
        try{
            
            FileWriter write = new FileWriter(archivo,true);
            BufferedWriter bufWrite = new BufferedWriter(write);

            bufWrite.write(puntuacion);
            bufWrite.newLine();

            bufWrite.close();
                 
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void Escribir(Jugador jugador) {
        try{
            
            FileWriter write = new FileWriter(archivo);
            BufferedWriter bufWrite = new BufferedWriter(write);

            bufWrite.write(jugador.getSkin()+";"+jugador.getMonedas()+";"+jugador.getPoder());
            bufWrite.newLine();

            bufWrite.close();
                 
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> LeerPuntuacion(){
        ObservableList<String> puntuacion = FXCollections.observableArrayList();
        
        try{
            FileReader read = new FileReader(archivo);
            BufferedReader bufread = new BufferedReader(read);

            String linea;

            while((linea =  bufread.readLine()) != null){
                puntuacion.add(linea);
            }

            read.close();
            bufread.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return puntuacion;
    }

    public ObservableList<Jugador> Leer(){
        ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
        
        try{
            FileReader read = new FileReader(archivo);
            BufferedReader bufread = new BufferedReader(read);
            
            String[] aux;

            String linea;

            while((linea =  bufread.readLine()) != null){
                aux = linea.split(";");
                if(aux.length == 3){
                    Jugador paquete = new Jugador(aux[0],Integer.parseInt(aux[1]),aux[2]);
                    jugadores.add(paquete);
                }
            }

            read.close();
            bufread.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return jugadores;
    }
}