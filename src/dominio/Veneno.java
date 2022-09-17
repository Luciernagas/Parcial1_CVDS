package dominio;

import java.awt.*;
import java.io.Serializable;

/**
 * clase que hereda de alimentos, diseñada para matar al jugador en el momento que es consumida
 */
public class Veneno extends Alimento implements Serializable {
    private final Color color;

    /**
     * constructor del alimento veneno que asigna los valores nesecarios
     * @param fila
     * @param columna
     */
    public Veneno(int fila, int columna) {
        super(fila, columna);
        this.color = new Color(145, 255, 149);
    }

    @Override
    public void sumarTiempo() {
        this.tiempo += 1;
    }

    /**
     * metodo abstracto que se utiliza para sumar el puntaje, pero en este caso lo que ocasiona es la muerte del jugador
     * @param serpiente
     * @return
     */
    @Override
    public boolean sumarPuntaje(Snake serpiente) {
        serpiente.morir();
        return false;
    }

    /**
     * retorna el color del veneno
     * @return color
     */
    public Color getColor() {
        return color;
    }
}