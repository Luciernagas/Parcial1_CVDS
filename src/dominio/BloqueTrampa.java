package dominio;

public class BloqueTrampa extends Sorpresa{

    /**
     * constructor que asigna los valores de la fila columna el juego y el tiempo
     * @param fila
     * @param columna
     * @param juego
     */
    public BloqueTrampa(int fila, int columna, SnakeGame juego) {
        super(fila, columna, juego);
    }

    @Override
    public void afectar(Snake serpiente) {
        int a,b;
        a = (int) (Math.random() * juego.getCantidad());
        b = (int) (Math.random() * juego.getCantidad());
        while(juego.revisarSerpiente(a, b, serpiente)){
            a = (int) (Math.random() * juego.getCantidad());
            b = (int) (Math.random() * juego.getCantidad());
        }
        if(juego.getSpawn() == null){
            juego.setSpawn(a, b);
        }

    }

    @Override
    public void quitarEfecto(Snake serpiente) {

    }

    @Override
    public String toString() {
        return "Bloque trampa";
    }
}
