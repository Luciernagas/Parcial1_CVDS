package dominio;

import java.awt.*;
import java.io.Serializable;

/**
 * clase que hereda de alimento que representa el alimento fruta que hace agrandar a la serpiente si llega a ser consumida
 */
public class Fruta extends Alimento implements Serializable {

    private Color color;

    /**
     * constructor de la clase fruta que le asigna un color a la fruta junto con su fila y columna
     * @param fila
     * @param columna
     */
    public Fruta(int fila, int columna) {
        super(fila, columna);
        int numAleatorio = (int) (Math.random()*6);
        switch(numAleatorio){
            case 0:
                this.color = Color.BLUE;
                break;
            case 1:
                this.color = Color.RED;
                break;
            case 2:
                this.color = Color.GREEN;
                break;
            case 3:
                this.color = Color.ORANGE;
                break;
            case 4:
                this.color = Color.CYAN;
                break;
            case 5:
                this.color = Color.YELLOW;
                break;
        }
    }

    @Override
    public void sumarTiempo() {
        this.tiempo += 1;
    }

    /**
     * metodo abstracto que suma el puntaje del jugador dependiendo de las condiciones anteriormente definidas
     * @param serpiente
     * @return
     */
    @Override
    public boolean sumarPuntaje(Snake serpiente) {
        if(this.color == serpiente.getColorCabeza() || this.color == serpiente.getColorCuerpo()){
            serpiente.sumarPuntaje(2);
        }
        else{
            serpiente.sumarPuntaje(1);
        }
        return true;
    }

    /**
     * metodo que retorna el color de la fruta
     * @return color
     */
    public Color getColor() {
        return color;
    }
}
