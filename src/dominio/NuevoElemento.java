package dominio;

import java.awt.*;

public class NuevoElemento extends Alimento{

    private final Color color;
    /**
     * constructor de alimentos  que asigna la fila la columna y el tiempo
     * @param fila
     * @param columna
     */
    public NuevoElemento(int fila, int columna) {
        super(fila, columna);
        this.color = Color.GREEN;
    }

    @Override
    public void sumarTiempo() {
        this.tiempo += 3;
    }

    @Override
    public boolean sumarPuntaje(Snake serpiente) {
        int numAleatorio = (int) (Math.random()*2);
        serpiente.sumarPuntaje(2);
        serpiente.setUnidadPendiente();
        if(numAleatorio == 0){
            serpiente.setPoder(new FlechaVelocidad(0, 0, serpiente.getJuego()));
        }
        else if(numAleatorio == 1){
            serpiente.setPoder(new BloqueTrampa(0, 0, serpiente.getJuego()));
        }
        return true;
    }

    public Color getColor() {
        return color;
    }
}
