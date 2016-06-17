/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

import static Vista.Ventana.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 *
 * @author Anthony
 */
public class MapaVista extends UnicastRemoteObject implements IMapaVista{
    private Color[][] mapaColor;
    private Color[][] aux;
    //Color para la "celda vacía"
    public static final Color vacio = Color.DARK_GRAY;
    
    public MapaVista() throws RemoteException {
        //Generar mapa
        this.mapaColor = new Color[numColumnas][numFilas];
        this.aux = new Color[numColumnas][numFilas];
        //Crear los casilleros (grid) y colocarlos en la ventana
        for (int y = 0; y < numFilas; y++)
            for (int x = 0; x < numColumnas; x++) {
                this.mapaColor[x][y] = vacio;
                this.aux[x][y] = vacio;
            }     
    }
    
    public Color obtenerColorAux(int x, int y) throws RemoteException {
        return this.aux[x][y];
    } 
    public void modificarColorAux(int x, int y, Color color)throws RemoteException  {
        System.out.println("X: "+x+" Y: "+y);
        this.aux[x][y] = color;
    }
    
    public Color obtenerColor(int x, int y) throws RemoteException {
        return this.mapaColor[x][y];
    } 
    public void modificarColor(int x, int y, Color color) throws RemoteException {
        this.mapaColor[x][y] = color;
    }
    
    public boolean celdaVacia(int x, int y) {
        return (this.mapaColor[x][y] == vacio);
    }
}
