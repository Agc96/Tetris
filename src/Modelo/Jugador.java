/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Anthony
 */
public class Jugador {
    private int tetriminoRandom;
    private int centralX;
    private int centralY;
    private int rotaActual;
    private int rotaAntigua;
    private boolean bottom;

    public int getTetriminoRandom() {
        return tetriminoRandom;
    }
    public void setTetriminoRandom(int tetriminoRandom) {
        this.tetriminoRandom = tetriminoRandom;
    }
    
    public int getCentralX() {
        return centralX;
    }
    public void setCentralX(int centralX) {
        this.centralX = centralX;
    }
    
    public int getCentralY() {
        return centralY;
    }
    public void setCentralY(int centralY) {
        this.centralY = centralY;
    }
    
    public int getRotaActual() {
        return rotaActual;
    }
    public void setRotaActual(int rotaActual) {
        this.rotaActual = rotaActual;
    }
    
    public int getRotaAntigua() {
        return rotaAntigua;
    }
    public void setRotaAntigua(int rotaAntigua) {
        this.rotaAntigua = rotaAntigua;
    }
    
    public boolean isBottom() {
        return bottom;
    }
    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }
    
    public void generarTetriminoAleatorio() {
        this.rotaActual = 0;
        this.rotaAntigua = 1;
        this.tetriminoRandom = (int) (Math.floor(Math.random()*7));
        this.centralX = 4;
        this.centralY = 0;
        System.out.println("Se selecciono el tetrimino " + tetriminoRandom);
    }
    
}
