package dominio;

public class Spawn {

    private final int fila;
    private final int columna;
    private int tiempoDuracion;

    public Spawn(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.tiempoDuracion = 0;
    }

    public int getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void sumarTiempo(){
        this.tiempoDuracion += 1;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
