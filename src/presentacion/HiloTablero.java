package presentacion;

import dominio.SnakeGame;

import javax.swing.*;

/**
 * Clase para correr el Thread que se encarga de animar los alimentos y sorpresas que hay en el juego
 */
public class HiloTablero implements Runnable{

    SnakeGame juegoSnakeGame;
    JPanel panelJ;

    /**
     * Constructor del runnable donde se van a dibujar los alimentos y sorpresas
     * @param snakeGame - El juego
     * @param pantallaJuego - El panel de juego donde se va a dibujar
     */
    public HiloTablero(SnakeGame snakeGame, JPanel pantallaJuego) {
        this.juegoSnakeGame = snakeGame;
        this.panelJ = pantallaJuego;
    }

    /**
     * Metodo para hacer correr el runnable, dibuja los alimentos y sorpresas
     */
    @Override
    public void run() {
        while(!(SnakeGame.pausa) && juegoSnakeGame.isEnJuego()){
            juegoSnakeGame.avanzarTiempo();
            panelJ.repaint();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
