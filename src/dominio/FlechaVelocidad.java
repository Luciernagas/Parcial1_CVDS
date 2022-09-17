package dominio;

/**
 * clase que hereda de Sorpresa, siendo la sorpresa de aumentar y disminuir la velocidad de los jugadores.
 */
public class FlechaVelocidad extends Sorpresa{

    String tipo;

    /**
     * constructor de la flecha de velocidad que se encarga de asignar todos los valores necesario para el funcionamiento de la clase
     * @param fila
     * @param columna
     * @param juego
     */
    public FlechaVelocidad(int fila, int columna, SnakeGame juego) {
        super(fila, columna, juego);
        if(juego.getCantidadJugadores() == 1){
            this.tipo = "Flecha aumento";
        }
        else{
            int numAleatorio = (int) (Math.random()*2);
            if(numAleatorio == 0){
                this.tipo = "Flecha aumento";
            }
            else{
                this.tipo = "Flecha disminucion";
            }
        }
    }

    /**
     * metodo abstracto para accionar la accion o de subir la velocida o de bajarla dependiendo de los diferentes casos
     * @param serpiente
     */
    @Override
    public void afectar(Snake serpiente) {
        if(this.tipo.equals("Flecha aumento")){
            serpiente.modificarTempo(-40);
        }
        else{
            if(serpiente.equals(juego.getSnake_1())){
                juego.getSnake_2().modificarTempo(30);
            }
            else{
                juego.getSnake_1().modificarTempo(30);
            }
        }
    }

    /**
     * metodo abstracto para devolver el jugador afecta al estado inicial en el momento que se acaba el tiempo de fuuncionamiento
     * @param serpiente
     */
    @Override
    public void quitarEfecto(Snake serpiente) {
        if(this.tipo.equals("Flecha aumento")){
            serpiente.modificarTempo(40);
        }
        else{
            if(serpiente.equals(juego.getSnake_1())){
                juego.getSnake_2().modificarTempo(-30);
            }
            else{
                juego.getSnake_1().modificarTempo(-30);
            }
        }
    }

    /**
     * metodo abstracto para impimir el objeto
     * @return tipo
     */
    @Override
    public String toString() {
        return  tipo;
    }

    /**
     * retorna el tipo de la flecha de volicdad
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }
}
