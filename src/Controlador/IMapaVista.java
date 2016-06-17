/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.MapaVista.vacio;
import static Vista.Ventana.numColumnas;
import static Vista.Ventana.numFilas;
import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JFrame;

/**
 *
 * @author Godievski
 */
public interface IMapaVista extends Remote{
    public Color obtenerColorAux(int x, int y) throws RemoteException;
    public void modificarColorAux(int x, int y, Color color)throws RemoteException;
    public Color obtenerColor(int x, int y) throws RemoteException;
    public void modificarColor(int x, int y, Color color)throws RemoteException;
    public boolean celdaVacia(int x, int y) throws RemoteException;
}
