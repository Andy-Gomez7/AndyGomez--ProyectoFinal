package model;

public class Jugador {
    
    private String skin;
    private int monedas;
    private String poder;

    public Jugador(String skin, int monedas, String poder){
        setSkin(skin);
        setMonedas(monedas);
        setPoder(poder);
    }

    public void setSkin(String Skin){
        skin = Skin;
    }

    public String getSkin(){
        return skin;
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