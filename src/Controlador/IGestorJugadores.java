/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Jugador;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Godievski
 */
public interface IGestorJugadores extends Remote{
    
    public int agregarJugador()throws RemoteException;
    public int getFilasEliminadas() throws RemoteException;
    public void addFilasEliminadas() throws RemoteException;
    
    public int cantidadJugadores() throws RemoteException;
    public void generarTetriminoAleatorio(int index) throws RemoteException;
    /**************** GETS Y SETS ****************/
    
    public int getCentralX(int index) throws RemoteException;
    public void setCentralX(int centralX, int index) throws RemoteException;
    public int getTetriminoRandom(int index) throws RemoteException;
    public void setTetriminoRandom(int tetriminoRandom, int index) throws RemoteException;
    public int getCentralY(int index) throws RemoteException;
    public void setCentralY(int centralY, int index) throws RemoteException;
    public int getRotaActual(int index) throws RemoteException;
    public void setRotaActual(int rotaActual, int index) throws RemoteException;
    public int getRotaAntigua(int index) throws RemoteException;
    public void setRotaAntigua(int rotaAntigua, int index) throws RemoteException;
    public boolean isBottom(int index) throws RemoteException;
    public void setBottom(boolean bottom, int index) throws RemoteException;
}
