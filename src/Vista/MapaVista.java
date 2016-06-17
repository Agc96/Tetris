/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

import static Vista.Tetris.*;

/**
 *
 * @author Anthony
 */
public class MapaVista {
    private JButton[][] mapa;
    private Color[][] aux;
    //Color para la "celda vacía"
    public static final Color vacio = Color.DARK_GRAY;
    
    public MapaVista(JFrame ventana) {
        //Generar mapa
        this.mapa = new JButton[numColumnas][numFilas];
        this.aux = new Color[numColumnas][numFilas];
        //Crear los casilleros (grid) y colocarlos en la ventana
        for (int i = 0; i < numColumnas; i++)
            for (int j = 0; j < numFilas; j++) {
                this.mapa[i][j] = new JButton(" ");
                this.mapa[i][j].setBackground(vacio);
                this.mapa[i][j].setEnabled(true);
                ventana.add(this.mapa[i][j]);
                this.aux[i][j] = vacio;
            }     
    }
    
    public Color obtenerColorAux(int x, int y) {
        return this.aux[x][y];
    } 
    public void modificarColorAux(int x, int y, Color color) {
        this.aux[x][y] = color;
    }
    
    public Color obtenerColor(int x, int y) {
        return this.mapa[x][y].getBackground();
    } 
    public void modificarColor(int x, int y, Color color) {
        this.mapa[x][y].setBackground(color);
    }
    
    public boolean celdaVacia(int x, int y) {
        return (this.mapa[x][y].getBackground() == vacio);
    }
}
