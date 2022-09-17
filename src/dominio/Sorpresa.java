package dominio;

import java.io.Serializable;

/**
 * clase abstracta de las sorpresas con diferentes poderes dentro del juego
 */
public abstract class Sorpresa implements Serializable, Cloneable {

    private final int fila;
    private final int columna;
    protected final SnakeGame juego;
    protected int tiempo;

    /**
     * constructor que asigna los valores de la fila columna el juego y el tiempo
     * @param fila
     * @param columna
     * @param juego
     */
    public Sorpresa(int fila, int columna, SnakeGame juego) {
        this.fila = fila;
        this.columna = columna;
        this.juego = juego;
        this.tiempo = 0;
    }

    /**
     * metodo para clonar el objeto de la manera deepcopy
     * @return obj
     */
    public Object clone(){
        Object obj = null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("No se puede duplicar");
        }
        return obj;
    }

    /**
     * metodo abstracta que afecta al jugador cuando consume una sorpresa
     * @param serpiente
     */
    public abstract void afectar(Snake serpiente);

    /**
     * metodo abstracto que remueve el efecto de la sorpresa cuando se debe
     * @param serpiente
     */
    public abstract void quitarEfecto(Snake serpiente);

    public int[] getPosicion(){
        return new int[]{fila, columna};
    }

    /**
     * metodo abstracto para impimir el objeto
     * @return string
     */
    public abstract String toString();
}
