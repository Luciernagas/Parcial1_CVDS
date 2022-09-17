package dominio;

public class Lupa extends Sorpresa{

    /**
     * constructor que asigna los valores de la fila columna el juego y el tiempo
     * @param fila
     * @param columna
     * @param juego
     */
    public Lupa(int fila, int columna, SnakeGame juego) {
        super(fila, columna, juego);
    }

    /**
     * Afecta a las serpientes bajo los efectos de la lupa
     * @param serpiente - La serpiente que toma la sorpresa
     */
    @Override
    public void afectar(Snake serpiente) {
        if(serpiente.getId() == 1){
            juego.getSerpiente(1).setLupaActiva(true);
        }
        else{
            juego.getSerpiente(0).setLupaActiva(true);
        }
    }

    /**
     * Metodo abstracto para devolver el jugador afecta al estado inicial en el momento que se acaba el tiempo de fuuncionamiento
     * @param serpiente - La serpiente que tenia la sorpresa
     */
    @Override
    public void quitarEfecto(Snake serpiente) {
    }

    @Override
    public String toString() {
        return "Lupa";
    }
}
