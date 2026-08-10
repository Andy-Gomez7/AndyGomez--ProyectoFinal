package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArchivoUtil {

    private File archivo;

    public ArchivoUtil(String nombreArchivo){
        archivo = new File(nombreArchivo);
        
    }

    public void Escribir(String texto) {
        try{
            
            FileWriter write = new FileWriter(archivo,true);
            BufferedWriter bufWrite = new BufferedWriter(write);

            bufWrite.write(texto);
            bufWrite.newLine();

            bufWrite.close();
                 
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> Leertexto(){
        ObservableList<String> texto = FXCollections.observableArrayList();
        
        try{
            FileReader read = new FileReader(archivo);
            BufferedReader bufread = new BufferedReader(read);

            String linea;

            while((linea =  bufread.readLine()) != null){
                texto.add(linea);
            }

            read.close();
            bufread.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return texto;
    }
}