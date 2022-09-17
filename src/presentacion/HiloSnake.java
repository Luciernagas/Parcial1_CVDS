package presentacion;
import dominio.SnakeGame;
import javax.swing.*;

public abstract class HiloSnake implements Runnable{
    SnakeGame juegoSnakeGame;
    JPanel panelJ, panelP;
    /**
     * Construye el runnable
     * @param snakeGame - El juego
     * @param pantallaJuego - El panel donde se va a dibujar
     * @param panallaPuntaje - El panel donde se va a mostrar la informacion del juego
     */
    public HiloSnake(SnakeGame snakeGame, JPanel pantallaJuego, JPanel panallaPuntaje) {
        super();
        this.juegoSnakeGame = snakeGame;
        this.panelJ = pantallaJuego;
        this.panelP = panallaPuntaje;
    }

    /**
     * Metodo para hacer correr el runnable, dibuja cada serpiente
     */
    public abstract void run();
}
