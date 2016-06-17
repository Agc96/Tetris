/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Anthony
 */
public class GestorTetriminos {
    public Tetrimino[] tetriminos;
    
    public GestorTetriminos() {
        this.tetriminos = new Tetrimino[7];
        //Cargar tetriminos
        Scanner sc = null;
        try {
            sc = new Scanner(new File("tetriminos.csv"));
        } catch (FileNotFoundException ex) {
            System.err.println("Error: No se encontró el archivo tetriminos.csv");
            System.exit(1);
        }
        for (int i = 0; i < 7; i++) {
            sc.nextLine();
            Celda[][] datosTetrimino = new Celda[4][4];
            for (int rotacion = 0; rotacion < 4; rotacion++) {
                String[] palabras = sc.nextLine().split(",");
                for (int pieza = 0; pieza < 4; pieza++) {
                    Celda c = new Celda(Integer.parseInt(palabras[2*pieza]),
                            Integer.parseInt(palabras[2*pieza+1]));
                    datosTetrimino[rotacion][pieza] = c;
                }
            }
            this.tetriminos[i] = new Tetrimino(datosTetrimino);    
        }      
        //Asignar colores
        this.tetriminos[0].setColor(Color.red);
        this.tetriminos[1].setColor(Color.yellow);
        this.tetriminos[2].setColor(Color.cyan);
        this.tetriminos[3].setColor(Color.green);
        this.tetriminos[4].setColor(Color.white);
        this.tetriminos[5].setColor(Color.blue);
        this.tetriminos[6].setColor(Color.orange);
        
        this.imprimirDatos();
    }
    
    public void imprimirDatos() {
        for (int t = 0; t < 7; t++) {
            System.out.println("Tetrimino " + t);
            Tetrimino tetrimino = this.tetriminos[t];
            for (int r = 0; r < 4; r++) {
                for (int p = 0; p < 4; p++) {
                    Celda celda = tetrimino.obtenerCelda(r, p);
                    if (p != 0) System.out.print(",");
                    System.out.print(celda.getX() + "," + celda.getY());
                }
                System.out.println();
            }
        }
        System.out.println("OK");
    }
    
    //PLACEHOLDER
    public Tetrimino ObtenerTetrimino(int index) {
        return this.tetriminos[index];
    }
        
    //Obtener color de un tetrimino específico
    public Color ObtenerColor(int index) {
        return this.tetriminos[index].getColor();
    }
    
    //Obtener la posicion de una celda de un tetrimino específico
    public int getX(int index, int rotacion, int celda) {
        return this.tetriminos[index].getX(rotacion, celda);
    }
    public int getY(int index, int rotacion, int celda) {
        return this.tetriminos[index].getY(rotacion, celda);
    }
    
    //Modificar la posicion de una celda de un tetrimino específico
    public void setX(int index, int rotacion, int pieza, int valor) {
        this.tetriminos[index].setX(rotacion, pieza, valor);
    }
    public void setY(int index, int rotacion, int pieza, int valor) {
        this.tetriminos[index].setY(rotacion, pieza, valor);
    }
}
