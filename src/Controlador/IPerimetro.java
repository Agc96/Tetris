/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Vista.Ventana.numColumnas;
import static Vista.Ventana.numFilas;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Godievski
 */
public interface IPerimetro extends Remote {
    
    public void imprimirDatos()throws RemoteException;
    
    public int obtenerValorCelda(int x, int y) throws RemoteException;
    
    public void modificarValorCelda(int x, int y, int valor)throws RemoteException;
}
