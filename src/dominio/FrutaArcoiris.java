package dominio;

import java.io.Serializable;

/**
 * clase que hereda de alimento que representa el alimento fruta que hace agrandar a la serpiente si llega a ser consumida
 */
public class FrutaArcoiris extends Alimento implements Serializable {
    /**
     * constructor de fruta arcoiris que asigna su fila y columna
     * @param fila, Fila en el tablero
     * @param columna, Columna en el tablero
     */
    public FrutaArcoiris(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public void sumarTiempo() {
        this.tiempo += 1;
    }

    /**
     * metodo abstracto que suma 3 puntos al momento de ser consumida
     * @param serpiente
     * @return true
     */
    @Override
    public boolean sumarPuntaje(Snake serpiente) {
        serpiente.sumarPuntaje(3);
        return true;
    }
}
