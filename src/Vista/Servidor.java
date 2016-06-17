/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GestorJugadores;
import Controlador.MapaVista;
import Controlador.Perimetro;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 *
 * @author Godievski
 */
public class Servidor {
    public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException{
        GestorJugadores gj = new GestorJugadores();
        MapaVista mv = new MapaVista();
        Perimetro p = new Perimetro();
        Naming.rebind("jugadores", gj);
        Naming.rebind("mapaVista", mv);
        Naming.rebind("perimetro", p);
        System.out.println("Server created");
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}
