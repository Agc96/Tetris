/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Jugador;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class GestorJugadores {
    private ArrayList<Jugador> jugadores;
    private int filasEliminadas;
    
    public GestorJugadores() {
        this.jugadores = new ArrayList();
        this.filasEliminadas = 0;
    }
    
    public int agregarJugador() {
        this.jugadores.add(new Jugador());
        return this.jugadores.size()-1;
    }

    public int getFilasEliminadas() {
        return filasEliminadas;
    }
    public void addFilasEliminadas() {
        this.filasEliminadas++;
    }
    
    public int cantidadJugadores() {
        return this.jugadores.size();
    }
    
    public void generarTetriminoAleatorio(int index) {
        this.jugadores.get(index).generarTetriminoAleatorio();
    }
    
    /**************** GETS Y SETS ****************/
    
    public int getCentralX(int index) {
        return this.jugadores.get(index).getCentralX();
    }
    public void setCentralX(int centralX, int index) {
        this.jugadores.get(index).setCentralX(centralX);
    }
    
    public int getTetriminoRandom(int index) {
        return this.jugadores.get(index).getTetriminoRandom();
    }
    public void setTetriminoRandom(int tetriminoRandom, int index) {
        this.jugadores.get(index).setTetriminoRandom(tetriminoRandom);
    }
    
    public int getCentralY(int index) {
        return this.jugadores.get(index).getCentralY();
    }
    public void setCentralY(int centralY, int index) {
        this.jugadores.get(index).setCentralY(centralY);
    }
    
    public int getRotaActual(int index) {
        return this.jugadores.get(index).getRotaActual();
    }
    public void setRotaActual(int rotaActual, int index) {
        this.jugadores.get(index).setRotaActual(rotaActual);
    }
    
    public int getRotaAntigua(int index) {
        return this.jugadores.get(index).getRotaAntigua();
    }
    public void setRotaAntigua(int rotaAntigua, int index) {
        this.jugadores.get(index).setRotaAntigua(rotaAntigua);
    }
    
    public boolean isBottom(int index) {
        return this.jugadores.get(index).isBottom();
    }
    public void setBottom(boolean bottom, int index) {
        this.jugadores.get(index).setBottom(bottom);
    }    
}
