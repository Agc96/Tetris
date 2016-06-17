/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author David
 */
import Controlador.MapaVista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Modelo.*;
import Controlador.*;
 
public class Tetris extends JFrame implements KeyListener {
    //Datos no modificables que se usarán en todo el juego
    public static final int numFilas = 20;
    public static final int numColumnas = 10;    
    //Clases auxiliares controladoras
    private MapaVista mapaVista;
    private GestorTetriminos tetriminos;
    private Perimetro perimetro;
    //Datos del juego
    int tetrRand; //Numero que selecciona aleatoriamente un tetrimino
    int centralx;
    int centraly;
    int filasEliminadas;   
    //Datos de control
    int rotacionActual = 0;
    int rotacionAntigua = 1;
    boolean bottom; //Determina si se llego al fondo o no
    
    public Tetris() {
        //Configurar datos de juego
        this.filasEliminadas = 0;
        this.bottom = false;
        this.tetrRand = 0;
        this.centralx = 0;
        this.centraly = 0;
        //Configurar clases auxiliares
        this.tetriminos = new GestorTetriminos();
        this.perimetro = new Perimetro();
        this.mapaVista = new MapaVista(this);
        //Configurar datos de ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);        
        this.setLayout(new GridLayout(numFilas,numColumnas));     
        this.setFocusable(true);
        this.addKeyListener(this);
        this.pack();
        this.setVisible(true);
        this.blockgen();
    }
    
    public boolean continuaJuego() {
        boolean aux = true;
        for (int i = 0; i < 4; i++)
            aux = aux && mapaVista.celdaVacia(4 + tetriminos.getX(tetrRand, rotacionActual, i),
                tetriminos.getY(tetrRand, rotacionActual, i));
        return aux; 
    }
    
    public void blockgen(){
        this.rotacionActual = 0;
        this.rotacionAntigua = 1;
        this.tetrRand = (int) (Math.floor(Math.random()*7));
        this.centralx = 4;
        this.centraly = 0;
        System.out.println("Se selecciono el tetrimino " + tetrRand);
        
        //Queremos imprimir un tetrimino aleatorio, si todos esos casilleros
        //son grises (vacíos), entonces no hay problema, de lo contrario se
        //concluye que hemos perdido el juego.
        if (continuaJuego()) {
            for (int i = 0; i < 4; i++)
                mapaVista.modificarColor(4 + tetriminos.getX(tetrRand, rotacionActual, i),
                        tetriminos.getY(tetrRand, rotacionActual, i),
                        tetriminos.ObtenerColor(tetrRand));
            this.go();
        }
        else { //Fin del juego
            JOptionPane.showMessageDialog(null, "¡Fin del juego! ¡Eliminaste "
                    + filasEliminadas + " filas, bien hecho!");
            System.exit(0);
        }
    }
    
    public boolean validoRotar() {
        boolean valido = true;
        for (int i = 0; i < 4; i++) {
            int valor = perimetro.obtenerValorCelda(
                    centralx + 2 + tetriminos.getX(tetrRand, rotacionActual, i),
                    centraly + 2 + tetriminos.getY(tetrRand, rotacionActual, i));
            valido = valido && (valor == 0);
        }
        return valido;
    }
    
    public void rotate(){
        if (this.rotacionActual < 3){
            this.rotacionAntigua = this.rotacionActual;
            this.rotacionActual++;
        } else if (this.rotacionActual == 3){
            this.rotacionActual = 0;
            this.rotacionAntigua = 3;
        } else {
            System.err.println("Error en la rotación");
            System.exit(1);
        }
        if (validoRotar()) {
            for (int i = 0; i < 4; i++)
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionAntigua, i),
                        centraly + tetriminos.getY(tetrRand, rotacionAntigua, i),
                        MapaVista.vacio);
            for (int i = 0; i < 4; i++)
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionActual, i),
                        centraly + tetriminos.getY(tetrRand, rotacionActual, i),
                        tetriminos.ObtenerColor(tetrRand));
        } else { //Volver al estado actual
            if (rotacionAntigua > 0){
                this.rotacionActual = this.rotacionAntigua;
                this.rotacionAntigua--;
            } else if (rotacionAntigua == 0){
                rotacionActual = 0;
                rotacionAntigua = 3;
            }
        }
    }
 
    public void movedown(){
        int[] m2 = {-1, -1, -1, -1};
        int[] m1 = {-1, -1, -1, -1};
        int[] zero = {-1, -1, -1, -1};
        int[] one = {-1, -1, -1, -1};
        int[] two = {-1, -1, -1, -1};
        
        for (int d = 0;d<4;d++){
            if (tetriminos.getX(tetrRand, rotacionActual, d) == -2)
                m2[d] = d;
            else if (tetriminos.getX(tetrRand, rotacionActual, d) == -1)
                m1[d] = d;
            else if (tetriminos.getX(tetrRand, rotacionActual, d) == 0)
                zero[d] = d;
            else if (tetriminos.getX(tetrRand, rotacionActual, d) == 1)
                one[d] = d;
            else if (tetriminos.getX(tetrRand, rotacionActual, d) == 2)
                two[d] = d;
        }
        int tmpm2 = -5;
        int tmpm1 = -5;
        int tmpzero = -5;
        int tmpone = -5;
        int tmptwo = -5;
        
        for (int d = 0;d<4;d++) {
            if (m2[d] != -1){
                if (tmpm2 < tetriminos.getY(tetrRand, rotacionActual, m2[d])){
                    tmpm2 = tetriminos.getY(tetrRand, rotacionActual, m2[d]);
                }
            }
            if (m1[d] != -1){
                if (tmpm1 < tetriminos.getY(tetrRand, rotacionActual, m1[d])){
                    tmpm1 = tetriminos.getY(tetrRand, rotacionActual, m1[d]);
                }
            }
            if (zero[d] != -1){
                if (tmpzero < tetriminos.getY(tetrRand, rotacionActual, zero[d])){
                    tmpzero = tetriminos.getY(tetrRand, rotacionActual, zero[d]);
                }
            }
            if (one[d] != -1){
                if (tmpone < tetriminos.getY(tetrRand, rotacionActual, one[d])){
                    tmpone = tetriminos.getY(tetrRand, rotacionActual, one[d]);
                }
            }
            if (two[d] != -1){
                if (tmptwo < tetriminos.getY(tetrRand, rotacionActual, two[d])){
                    tmptwo = tetriminos.getY(tetrRand, rotacionActual, two[d]);
                }
            }
        }
        int total = 0;
        for (int d = 0;d<4;d++){
            if (tetriminos.getX(tetrRand, rotacionActual, d) == -2){
                if (perimetro.obtenerValorCelda(centralx, 3 + centraly + tmpm2) != 4 &&
                        mapaVista.celdaVacia(centralx - 2, centraly + tmpm2 + 1)){
                        total++;
                }
            } else if (tetriminos.getX(tetrRand, rotacionActual, d) == -1){
                if (perimetro.obtenerValorCelda(centralx + 1, 3 + centraly + tmpm1) != 4 &&
                        mapaVista.celdaVacia(centralx - 1, centraly + tmpm1 + 1)){
                        total++;
                }
            } else if (tetriminos.getX(tetrRand, rotacionActual, d) == 0){
                if (perimetro.obtenerValorCelda(centralx + 2, 3 + centraly + tmpzero) != 4 &&
                        mapaVista.celdaVacia(centralx, centraly + tmpzero + 1)){
                        total++;
                }
            } else if (tetriminos.getX(tetrRand, rotacionActual, d) == 1){
                if (perimetro.obtenerValorCelda(centralx + 3, 3 + centraly + tmpone) != 4 &&
                        mapaVista.celdaVacia(centralx + 1, centraly + tmpone + 1)){
                        total++;
                }
            } else if (tetriminos.getX(tetrRand, rotacionActual, d) == 2){
                if (perimetro.obtenerValorCelda(centralx + 4, 3 + centraly + tmptwo) != 4 &&
                        mapaVista.celdaVacia(centralx + 2, centraly + tmptwo + 1)){
                        total++;
                }
            }
        }
        if (total == 4){
            for (int i = 0; i < 4; i++)
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionActual, i),
                        centraly + tetriminos.getY(tetrRand, rotacionActual, i),
                        MapaVista.vacio);
            centraly++;
            for (int i = 0; i < 4; i++)
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionActual, i),
                        centraly + tetriminos.getY(tetrRand, rotacionActual, i),
                        tetriminos.ObtenerColor(tetrRand));
        } else {
            bottom = true;
        }
    }
 
 
    public void go(){
        do{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {}
            movedown();
            rowcheck();
        } while (bottom == false);
        bottom = false;
        blockgen();
    }
 
    public void rowcheck(){
        int row = 0;
        for (int y = 0; y < 20; y++){
            for (int x = 0; x < 10; x++){
                if (!mapaVista.celdaVacia(x, y)) row++;
                if (row == 10){
                    filasEliminadas++;
                    rowclear(y);
                }
            }
            row = 0;
        }
    }
 
    public void rowclear(int y){
        int inc = 0;
        for (int x = 0;x<10;x++){
            mapaVista.modificarColor(x, y, MapaVista.vacio);
        }
        for (int c = y-1;c>-1;c--){
            for (int x = 0;x<10;x++){
                mapaVista.modificarColorAux(x, y-inc, mapaVista.obtenerColor(x, c));
            }
            inc++;
        }
        for (int c = y;c>-1;c--){
            for (int x = 0;x<10;x++){
                mapaVista.modificarColor(x, c, mapaVista.obtenerColorAux(x, c));
            }
        }
    }
 
    public void mover(int deltax){
        int[] m2 = {-1, -1, -1, -1};
        int[] m1 = {-1, -1, -1, -1};
        int[] zero = {-1, -1, -1, -1};
        int[] one = {-1, -1, -1, -1};
        int[] two = {-1, -1, -1, -1};
        for (int d = 0;d<4;d++){
            if (tetriminos.getY(tetrRand, rotacionActual, d) == -2){
                m2[d] = d;
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == -1){
                m1[d] = d;
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == 0){
                zero[d] = d;
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == 1){
                one[d] = d;
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == 2){
                two[d] = d;
            }
        }
        int tmpm2 = -5;
        int tmpm1 = -5;
        int tmpzero = -5;
        int tmpone = -5;
        int tmptwo = -5;
        if (deltax == 1){
            for (int d = 0;d<4;d++){
                if (m2[d] != -1){
                    if (tmpm2 < tetriminos.getX(tetrRand, rotacionActual, m2[d])){
                        tmpm2 = tetriminos.getX(tetrRand, rotacionActual, m2[d]);
                    }
                }
                if (m1[d] != -1){
                    if (tmpm1 < tetriminos.getX(tetrRand, rotacionActual, m1[d])){
                        tmpm1 = tetriminos.getX(tetrRand, rotacionActual, m1[d]);
                    }
                }
                if (zero[d] != -1){
                    if (tmpzero < tetriminos.getX(tetrRand, rotacionActual, zero[d])){
                        tmpzero = tetriminos.getX(tetrRand, rotacionActual, zero[d]);
                    }
                }
                if (one[d] != -1){
                    if (tmpone < tetriminos.getX(tetrRand, rotacionActual, one[d])){
                        tmpone = tetriminos.getX(tetrRand, rotacionActual, one[d]);
                    }
                }
                if (two[d] != -1){
                    if (tmptwo < tetriminos.getX(tetrRand, rotacionActual, two[d])){
                        tmptwo = tetriminos.getX(tetrRand, rotacionActual, two[d]);
                    }
                }
            }
        } else if (deltax == -1){
            tmpm2 = 5;
            tmpm1 = 5;
            tmpzero = 5;
            tmpone = 5;
            tmptwo = 5;
            for (int d = 0;d<4;d++){
                if (m2[d] != -1){
                    if (tmpm2 > tetriminos.getX(tetrRand, rotacionActual, m2[d])){
                        tmpm2 = tetriminos.getX(tetrRand, rotacionActual, m2[d]);
                    }
                }
                if (m1[d] != -1){
                    if (tmpm1 > tetriminos.getX(tetrRand, rotacionActual, m1[d])){
                        tmpm1 = tetriminos.getX(tetrRand, rotacionActual, m1[d]);
                    }
                }
                if (zero[d] != -1){
                    if (tmpzero > tetriminos.getX(tetrRand, rotacionActual, zero[d])){
                        tmpzero = tetriminos.getX(tetrRand, rotacionActual, zero[d]);
                    }
                }
                if (one[d] != -1){
                    if (tmpone > tetriminos.getX(tetrRand, rotacionActual, one[d])){
                        tmpone = tetriminos.getX(tetrRand, rotacionActual, one[d]);
                    }
                }
                if (two[d] != -1){
                    if (tmptwo > tetriminos.getX(tetrRand, rotacionActual, two[d])){
                        tmptwo = tetriminos.getX(tetrRand, rotacionActual, two[d]);
                    }
                }
            }
        }
        int total = 0;
        for (int d = 0;d<4;d++){
            if (tetriminos.getY(tetrRand, rotacionActual, d) == -2){
                if (perimetro.obtenerValorCelda(2+centralx+deltax+tmpm2, 2+centraly-2) != 2){
                    if(mapaVista.celdaVacia(centralx+deltax+tmpm2, centraly-2)){
                        total++;
                    }
                }
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == -1){
                if (perimetro.obtenerValorCelda(2+centralx+deltax+tmpm1, 2+centraly-1) != 2){
                    if (mapaVista.celdaVacia(centralx+deltax+tmpm1, centraly-1)){
                        total++;
                    }
                }
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == 0){
                if (perimetro.obtenerValorCelda(2+centralx+deltax+tmpzero, 2+centraly) != 2){
                    if (mapaVista.celdaVacia(centralx+deltax+tmpzero, centraly)){
                        total++;
                    }
                }
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == 1){
                if (perimetro.obtenerValorCelda(2+centralx+deltax+tmpone, 2+centraly+1) != 2){
                    if (mapaVista.celdaVacia(centralx+deltax+tmpone, centraly+1)){
                        total++;
                    }
                }
            } else if (tetriminos.getY(tetrRand, rotacionActual, d) == 2){
                if (perimetro.obtenerValorCelda(2+centralx+deltax+tmptwo, 2+centraly+2) != 2){
                    if (mapaVista.celdaVacia(centralx+deltax+tmptwo, centraly+2)){
                        total++;
                    }
                }
            }
        }
        
        if (total == 4){
            for (int i = 0; i < 4; i++)
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionActual, i),
                        centraly + tetriminos.getY(tetrRand, rotacionActual, i),
                        MapaVista.vacio);
            centralx += deltax;
            for (int i = 0; i < 4; i++)
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionActual, i),
                        centraly + tetriminos.getY(tetrRand, rotacionActual, i), tetriminos.ObtenerColor(tetrRand));
        }
    }
 
    public static void main (String[] args){
        new Tetris();
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            mover(1);
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            mover(-1);
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) //VK_UP
            rotate();
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            movedown();
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
        //Nada
    }
 
    @Override
    public void keyTyped(KeyEvent e) {        
        //Nada
    }
}