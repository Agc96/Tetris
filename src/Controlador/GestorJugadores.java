/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Jugador;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class GestorJugadores extends UnicastRemoteObject implements IGestorJugadores{
    private ArrayList<Jugador> jugadores;
    private int filasEliminadas;
    
    public GestorJugadores() throws RemoteException{
        this.jugadores = new ArrayList();
        this.filasEliminadas = 0;
    }
    
    public int agregarJugador() throws RemoteException{
        this.jugadores.add(new Jugador());
        return this.jugadores.size()-1;
    }

    public int getFilasEliminadas() throws RemoteException{
        return filasEliminadas;
    }
    public void addFilasEliminadas() throws RemoteException{
        this.filasEliminadas++;
    }
    
    public int cantidadJugadores() throws RemoteException{
        return this.jugadores.size();
    }
    
    public void generarTetriminoAleatorio(int index) throws RemoteException{
        if (index == 0)
            this.jugadores.get(index).generarTetriminoAleatorio(8);
        else if (index == 1)
            this.jugadores.get(index).generarTetriminoAleatorio(2);
    }
    
    /**************** GETS Y SETS ****************/
    
    public int getCentralX(int index) throws RemoteException{
        return this.jugadores.get(index).getCentralX();
    }
    public void setCentralX(int centralX, int index) throws RemoteException{
        this.jugadores.get(index).setCentralX(centralX);
    }
    
    public int getTetriminoRandom(int index) throws RemoteException{
        return this.jugadores.get(index).getTetriminoRandom();
    }
    public void setTetriminoRandom(int tetriminoRandom, int index) {
        this.jugadores.get(index).setTetriminoRandom(tetriminoRandom);
    }
    
    public int getCentralY(int index) throws RemoteException{
        return this.jugadores.get(index).getCentralY();
    }
    public void setCentralY(int centralY, int index) throws RemoteException{
        this.jugadores.get(index).setCentralY(centralY);
    }
    
    public int getRotaActual(int index) throws RemoteException{
        return this.jugadores.get(index).getRotaActual();
    }
    public void setRotaActual(int rotaActual, int index) throws RemoteException{
        this.jugadores.get(index).setRotaActual(rotaActual);
    }
    
    public int getRotaAntigua(int index) throws RemoteException{
        return this.jugadores.get(index).getRotaAntigua();
    }
    public void setRotaAntigua(int rotaAntigua, int index) throws RemoteException{
        this.jugadores.get(index).setRotaAntigua(rotaAntigua);
    }
    
    public boolean isBottom(int index) throws RemoteException{
        return this.jugadores.get(index).isBottom();
    }
    public void setBottom(boolean bottom, int index) throws RemoteException{
        this.jugadores.get(index).setBottom(bottom);
    }    
}
