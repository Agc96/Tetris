/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Anthony
 */

import static Vista.Ventana.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Perimetro extends UnicastRemoteObject implements IPerimetro{
    private final int[][] mapa;
    
    public Perimetro() throws RemoteException{
        //Generar mapa inicial
        this.mapa = new int[numColumnas + 4][numFilas + 4];
        //Llenar los campos
        for (int y = 0; y < 2; y++){
            for (int x = 0; x < numColumnas+4; x++){
                this.mapa[x][y]= 1;
            }
        }
        for (int y = numFilas + 2; y < numFilas + 4; y++)
            for (int x = 0; x < numColumnas+4; x++)
                this.mapa[x][y] = 4;
        
        for (int y = 2; y < numFilas + 2; y++)
            for (int x = 0; x < 2; x++)
                this.mapa[x][y]= 2;
        
        for (int y = 2; y < numFilas + 2; y++){
            for (int x = numColumnas + 2; x < numColumnas + 4; x++){
                this.mapa[x][y]= 2;
            }
        }
        
        this.imprimirDatos();
    }
    
    public void imprimirDatos() throws RemoteException{
        for (int y = 0; y < numFilas + 4; y++){
            for (int x = 0; x < numColumnas + 4; x++){
                System.out.print(this.mapa[x][y]);
            }
            System.out.println("");
        }
    }
    
    public int obtenerValorCelda(int x, int y) throws RemoteException{
        return this.mapa[x][y];
    }
    
    public void modificarValorCelda(int x, int y, int valor) throws RemoteException{
        this.mapa[x][y] = valor;
    }
}
