package model;

public class Jugador {
    
    private int record;
    private int monedas;
    private String poder;

    public Jugador(int record, int monedas, String poder){
        setRecord(record);
        setMonedas(monedas);
        setPoder(poder);
    }

    public void setRecord(int Record){
        record = Record;
    }

    public int getRecord(){
        return record;
    }

    public void setMonedas(int Monedas){
        monedas = Monedas;
    }

    public int getMonedas(){
        return monedas;
    }

    public void setPoder(String Poder){
        poder = Poder;
    }

    public String getPoder(){
        return poder;
    }
}