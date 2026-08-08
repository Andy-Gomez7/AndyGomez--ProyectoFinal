package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Jugador;

public class ArchivoUtil {

    private File archivo;

    public ArchivoUtil(String nombreArchivo){
        archivo = new File(nombreArchivo);
        
    }

    public void Escribir(Jugador jugador) {
        try{
            
            FileWriter write = new FileWriter(archivo,true);
            BufferedWriter bufWrite = new BufferedWriter(write);

            bufWrite.write(jugador.getRecord()+";"+jugador.getMonedas()+";"+jugador.getPoder());
            bufWrite.newLine();

            bufWrite.close();
                 
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Jugador> LeerPaquete(){
        ArrayList<Jugador> Jugador = new ArrayList<>();
        
        try{
            FileReader read = new FileReader(archivo);
            BufferedReader bufread = new BufferedReader(read);
            
            String[] aux;

            String linea;

            while((linea =  bufread.readLine()) != null){
                aux = linea.split(";");
                if(aux.length == 4){
                    Jugador paquete = new Jugador(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),aux[2]);
                    Jugador.add(paquete);
                }
            }

            read.close();
            bufread.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return Jugador;
    }
}