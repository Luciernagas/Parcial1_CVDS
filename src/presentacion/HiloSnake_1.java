package presentacion;

import dominio.SnakeGame;

import javax.swing.*;

/**
 * Clase para correr el Thread que se encarga de animar a la serpiente 1
 */
public class HiloSnake_1 implements Runnable {

    SnakeGame juegoSnakeGame;
    JPanel panelJ, panelP;

    /**
     * Construye el runnable
     * @param snakeGame - El juego
     * @param pantallaJuego - El panel donde se va a dibujar
     * @param panallaPuntaje - El panel donde se va a mostrar la informacion del juego
     */
    public HiloSnake_1(SnakeGame snakeGame, JPanel pantallaJuego, JPanel panallaPuntaje) {
        this.juegoSnakeGame = snakeGame;
        this.panelJ = pantallaJuego;
        this.panelP = panallaPuntaje;
    }

    /**
     * Metodo para hacer correr el runnable, dibuja la serpiente 1
     */
    @Override
    public void run() {
        while(!(SnakeGame.pausa) && juegoSnakeGame.isEnJuego()){
            juegoSnakeGame.hacerAvanzar(0);
            panelJ.repaint();
            panelP.repaint();
            try {
                Thread.sleep(juegoSnakeGame.getSnake_1().getTempo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
