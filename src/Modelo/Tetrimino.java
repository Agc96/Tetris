/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;

/**
 *
 * @author Anthony
 */
public class Tetrimino {
    public Celda[][] tetrimino;
    public Color color;
    
    public Tetrimino() {
        this.tetrimino = new Celda[4][4];
        for (int rotacion = 0; rotacion < 4; rotacion++)
            for (int pieza = 0; pieza < 4; pieza++)
                this.tetrimino[rotacion][pieza] = new Celda();
    }
    
    public Tetrimino(Celda[][] tetrimino) {
        this.tetrimino = tetrimino;
    }
    
    public Celda[] obtenerRotacion(int index) {
        return this.tetrimino[index];
    }
    
    public Celda obtenerCelda(int rotacion, int pieza) {
        return this.tetrimino[rotacion][pieza];
    }
    
    //Colocar el color del tetrimino
    public void setColor(Color color) {
        this.color = color;
    }
    
    //Obtener el color del tetrimino
    public Color getColor() {
        return this.color;
    }
    
    //Obtener la posicion de una celda de un tetrimino
    public int getX(int rotacion, int pieza) {
        return this.tetrimino[rotacion][pieza].getX();
    }
    public int getY(int rotacion, int pieza) {
        return this.tetrimino[rotacion][pieza].getY();
    }
    
    //Modificar la posicion de una celda de un tetrimino
    public void setX(int rotacion, int pieza, int valor) {
        this.tetrimino[rotacion][pieza].setX(valor);
    }
    public void setY(int rotacion, int pieza, int valor) {
        this.tetrimino[rotacion][pieza].setY(valor);
    }
}
