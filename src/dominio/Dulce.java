package dominio;

import java.awt.*;
import java.io.Serializable;

/**
 * clase que hereda de alimento que representa el alimento dulce que hace encoger a la serpiente si llega a ser consumida
 */
public class Dulce extends Alimento implements Serializable {
    private final Color color;

    /**
     * constructor del alimento dulce que asigna en super la fila y la columna y le asigna el color desea
     * @param fila
     * @param columna
     */
    public Dulce(int fila, int columna) {
        super(fila, columna);
        this.color = new Color(255, 101, 101);
    }

    @Override
    public void sumarTiempo() {
        this.tiempo += 1;
    }

    /**
     * metodo abstracto heredado de alimento que remueve de la serpiente dependiendo de las condiciones propuestas
     * @param serpiente
     * @return false
     */
    @Override
    public boolean sumarPuntaje(Snake serpiente) {
        if(this.color == serpiente.getColorCabeza() || this.color == serpiente.getColorCuerpo()){
            if(serpiente.size() > 3){
                serpiente.remover();
                serpiente.remover();
                serpiente.sumarPuntaje(-2);
            }
            else{
                serpiente.morir();
            }
        }
        else{
            if(serpiente.size() > 2){
                serpiente.remover();
                serpiente.sumarPuntaje(-1);
            }
            else{
                serpiente.morir();
            }
        }
        return false;
    }

    /**
     * metodo que retorna el color del dulce
     * @return color
     */
    public Color getColor() {
        return color;
    }
}