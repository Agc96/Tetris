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
            aux &= mapaVista.celdaVacia(4 + tetriminos.getX(tetrRand, rotacionActual, i),
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
                        tetriminos.ObtenerColor(i));
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
            int valor = perimetro.obtenerValorCelda(centralx + 2 +
                    tetriminos.getX(tetrRand, rotacionActual, i), centraly + 2
                    + tetriminos.getY(tetrRand, rotacionActual, i));
            valido &= (valor != 4 && valor != 3 && valor != 2 && valor != 1);
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
            for (int i = 0; i < 4; i++) {
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionAntigua, i),
                        centraly + tetriminos.getY(tetrRand, rotacionAntigua, i),
                        MapaVista.vacio);
                mapaVista.modificarColor(centralx + tetriminos.getX(tetrRand, rotacionActual, i),
                        centraly + tetriminos.getY(tetrRand, rotacionActual, i),
                        tetriminos.ObtenerColor(tetrRand));
            }            
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
 
 
    public int getxs(){
        int xs = 0;
        int[] xf = {-1, -1, -1, -1};
        for (int d = 0;d<4;d++){
            if ((xf[0] != tetriminos.[rotacionActual][d][tetrRand].x) || (xf[1] != tetriminos.[rotacionActual][d][tetrRand].x) || (xf[2] != tetriminos.[rotacionActual][d][tetrRand].x) || (xf[3] != tetriminos.[rotacionActual][d][tetrRand].x)){
                xf[d] = tetriminos.[rotacionActual][d][tetrRand].x;
            }
        }
        for (int d = 0;d<4;d++){
            if (xf[d] != -1){
                xs++;
            }
        }
        return xs;
    }
 
 
    public void movedown(){
        int[] m2 = {-1, -1, -1, -1};
        int[] m1 = {-1, -1, -1, -1};
        int[] zero = {-1, -1, -1, -1};
        int[] one = {-1, -1, -1, -1};
        int[] two = {-1, -1, -1, -1};
        for (int d = 0;d<4;d++){
            if (tetriminos.[rotacionActual][d][tetrRand].x == -2){
                m2[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == -1){
                m1[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == 0){
                zero[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == 1){
                one[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == 2){
                two[d] = d;
            }
        }
        int tmpm2 = -5;
        int tmpm1 = -5;
        int tmpzero = -5;
        int tmpone = -5;
        int tmptwo = -5;
        for (int d = 0;d<4;d++){
            if (m2[d] != -1){
                if (tmpm2<tetriminos.[rotacionActual][m2[d]][tetrRand].y){
                    tmpm2 = tetriminos.[rotacionActual][m2[d]][tetrRand].y;
                }
            }
            if (m1[d] != -1){
                if (tmpm1<tetriminos.[rotacionActual][m1[d]][tetrRand].y){
                    tmpm1 = tetriminos.[rotacionActual][m1[d]][tetrRand].y;
                }
            }
            if (zero[d] != -1){
                if (tmpzero<tetriminos.[rotacionActual][zero[d]][tetrRand].y){
                    tmpzero = tetriminos.[rotacionActual][zero[d]][tetrRand].y;
                }
            }
            if (one[d] != -1){
                if (tmpone<tetriminos.[rotacionActual][one[d]][tetrRand].y){
                    tmpone = tetriminos.[rotacionActual][one[d]][tetrRand].y;
                }
            }
            if (two[d] != -1){
                if (tmptwo<tetriminos.[rotacionActual][two[d]][tetrRand].y){
                    tmptwo = tetriminos.[rotacionActual][two[d]][tetrRand].y;
                }
            }
        }
        int total = 0;
        for (int d = 0;d<4;d++){
            if (tetriminos.[rotacionActual][d][tetrRand].x == -2){
                if (perimetro[2+centralx+-2][2+centraly+tmpm2+1] != 4){
                    if(b[centralx+-2][centraly+tmpm2+1].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == -1){
                if (perimetro[2+centralx+-1][2+centraly+tmpm1+1] != 4){
                    if (b[centralx+-1][centraly+tmpm1+1].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == 0){
                if (perimetro[2+centralx][2+centraly+tmpzero+1] != 4){
                    if (b[centralx][centraly+tmpzero+1].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == 1){
                if (perimetro[2+centralx+1][2+centraly+tmpone+1] != 4){
                    if (b[centralx+1][centraly+tmpone+1].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].x == 2){
                if (perimetro[2+centralx+2][2+centraly+tmptwo+1] != 4){
                    if (b[centralx+2][centraly+tmptwo+1].getBackground() == MapaVista.vacio){
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
            centraly++;
            for (int i = 0; i < 4; i++)
                
            b[centralx+tetriminos.[rotacionActual][0][tetrRand].x][centraly+tetriminos.[rotacionActual][0][tetrRand].y].setBackground(rnd[tetrRand]);
            b[centralx+tetriminos.[rotacionActual][1][tetrRand].x][centraly+tetriminos.[rotacionActual][1][tetrRand].y].setBackground(rnd[tetrRand]);
            b[centralx+tetriminos.[rotacionActual][2][tetrRand].x][centraly+tetriminos.[rotacionActual][2][tetrRand].y].setBackground(rnd[tetrRand]);
            b[centralx+tetriminos.[rotacionActual][3][tetrRand].x][centraly+tetriminos.[rotacionActual][3][tetrRand].y].setBackground(rnd[tetrRand]);
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
        }
        while (bottom == false);
        bottom = false;
        blockgen();
    }
 
    public void rowcheck(){
        int row = 0;
        for (int y = 0;y<20;y++){
            for (int x = 0;x<10;x++){
                if (b[x][y].getBackground() != MapaVista.vacio){
                    row++;
                }
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
            b[x][y].setBackground(MapaVista.vacio);
        }
        for (int c = y-1;c>-1;c--){
            for (int x = 0;x<10;x++){
                tmp[x][y-inc] = b[x][c].getBackground();
            }
            inc++;
        }
        for (int c = y;c>-1;c--){
            for (int x = 0;x<10;x++){
                b[x][c].setBackground(tmp[x][c]);
            }
        }
    }
 
    public void mover(){
        int[] m2 = {-1, -1, -1, -1};
        int[] m1 = {-1, -1, -1, -1};
        int[] zero = {-1, -1, -1, -1};
        int[] one = {-1, -1, -1, -1};
        int[] two = {-1, -1, -1, -1};
        for (int d = 0;d<4;d++){
            if (tetriminos.[rotacionActual][d][tetrRand].y == -2){
                m2[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == -1){
                m1[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == 0){
                zero[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == 1){
                one[d] = d;
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == 2){
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
                    if (tmpm2<tetriminos.[rotacionActual][m2[d]][tetrRand].x){
                        tmpm2 = tetriminos.[rotacionActual][m2[d]][tetrRand].x;
                    }
                }
                if (m1[d] != -1){
                    if (tmpm1<tetriminos.[rotacionActual][m1[d]][tetrRand].x){
                        tmpm1 = tetriminos.[rotacionActual][m1[d]][tetrRand].x;
                    }
                }
                if (zero[d] != -1){
                    if (tmpzero<tetriminos.[rotacionActual][zero[d]][tetrRand].x){
                        tmpzero = tetriminos.[rotacionActual][zero[d]][tetrRand].x;
                    }
                }
                if (one[d] != -1){
                    if (tmpone<tetriminos.[rotacionActual][one[d]][tetrRand].x){
                        tmpone = tetriminos.[rotacionActual][one[d]][tetrRand].x;
                    }
                }
                if (two[d] != -1){
                    if (tmptwo<tetriminos.[rotacionActual][two[d]][tetrRand].x){
                        tmptwo = tetriminos.[rotacionActual][two[d]][tetrRand].x;
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
                    if (tmpm2>tetriminos.[rotacionActual][m2[d]][tetrRand].x){
                        tmpm2 = tetriminos.[rotacionActual][m2[d]][tetrRand].x;
                    }
                }
                if (m1[d] != -1){
                    if (tmpm1>tetriminos.[rotacionActual][m1[d]][tetrRand].x){
                        tmpm1 = tetriminos.[rotacionActual][m1[d]][tetrRand].x;
                    }
                }
                if (zero[d] != -1){
                    if (tmpzero>tetriminos.[rotacionActual][zero[d]][tetrRand].x){
                        tmpzero = tetriminos.[rotacionActual][zero[d]][tetrRand].x;
                    }
                }
                if (one[d] != -1){
                    if (tmpone>tetriminos.[rotacionActual][one[d]][tetrRand].x){
                        tmpone = tetriminos.[rotacionActual][one[d]][tetrRand].x;
                    }
                }
                if (two[d] != -1){
                    if (tmptwo>tetriminos.[rotacionActual][two[d]][tetrRand].x){
                        tmptwo = tetriminos.[rotacionActual][two[d]][tetrRand].x;
                    }
                }
            }
        }
        int total = 0;
        for (int d = 0;d<4;d++){
            if (tetriminos.[rotacionActual][d][tetrRand].y == -2){
                if (perimetro[2+centralx+deltax+tmpm2][2+centraly-2] != 2){
                    if(b[centralx+deltax+tmpm2][centraly-2].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == -1){
                if (perimetro[2+centralx+deltax+tmpm1][2+centraly-1] != 2){
                    if (b[centralx+deltax+tmpm1][centraly-1].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == 0){
                if (perimetro[2+centralx+deltax+tmpzero][2+centraly] != 2){
                    if (b[centralx+deltax+tmpzero][centraly].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == 1){
                if (perimetro[2+centralx+deltax+tmpone][2+centraly+1] != 2){
                    if (b[centralx+deltax+tmpone][centraly+1].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            } else if (tetriminos.[rotacionActual][d][tetrRand].y == 2){
                if (perimetro[2+centralx+deltax+tmptwo][2+centraly+2] != 2){
                    if (b[centralx+deltax+tmptwo][centraly+2].getBackground() == MapaVista.vacio){
                        total++;
                    }
                }
            }
        } if (total == 4){
            b[centralx+tetriminos.[rotacionActual][0][tetrRand].x][centraly+tetriminos.[rotacionActual][0][tetrRand].y].setBackground(MapaVista.vacio);
            b[centralx+tetriminos.[rotacionActual][1][tetrRand].x][centraly+tetriminos.[rotacionActual][1][tetrRand].y].setBackground(MapaVista.vacio);
            b[centralx+tetriminos.[rotacionActual][2][tetrRand].x][centraly+tetriminos.[rotacionActual][2][tetrRand].y].setBackground(MapaVista.vacio);
            b[centralx+tetriminos.[rotacionActual][3][tetrRand].x][centraly+tetriminos.[rotacionActual][3][tetrRand].y].setBackground(MapaVista.vacio);
            centralx = centralx+deltax;
            b[centralx+tetriminos.[rotacionActual][0][tetrRand].x][centraly+tetriminos.[rotacionActual][0][tetrRand].y].setBackground(rnd[tetrRand]);
            b[centralx+tetriminos.[rotacionActual][1][tetrRand].x][centraly+tetriminos.[rotacionActual][1][tetrRand].y].setBackground(rnd[tetrRand]);
            b[centralx+tetriminos.[rotacionActual][2][tetrRand].x][centraly+tetriminos.[rotacionActual][2][tetrRand].y].setBackground(rnd[tetrRand]);
            b[centralx+tetriminos.[rotacionActual][3][tetrRand].x][centraly+tetriminos.[rotacionActual][3][tetrRand].y].setBackground(rnd[tetrRand]);
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
        if (e.getKeyCode() == KeyEvent.VK_UP)
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