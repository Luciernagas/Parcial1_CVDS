package dominio;

import java.io.Serializable;

/**
 *clase abstracta, siendo la super clase de cada una de los difernetes tipos de alimento dentro del juego
 */
public abstract class Alimento implements Serializable {
    private final int fila;
    private final int columna;
    protected int tiempo;

    /**
     * constructor de alimentos  que asigna la fila la columna y el tiempo
     * @param fila
     * @param columna
     */
    public Alimento(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.tiempo = 0;
    }


    /**
     * metodo que retorna la posicion del alimento
     * @return int[fila, columna]
     */
    public int[] getPosicion(){
        return new int[]{fila, columna};
    }

    /**
     * metodo que le suma tiempo al alimento
     */
    public abstract void sumarTiempo();

    /**
     * metodo que retorna el timepo del alimento
     * @return tiempo
     */
    public int getTiempo() { return tiempo; }

    /**
     * metodo abstracto de alimentos que suma el puntaje
     * @param serpiente
     * @return bool
     */
    public abstract boolean sumarPuntaje(Snake serpiente);
}
