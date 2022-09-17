package dominio;

public class SnakeBot extends Snake{

    /**
     * constructor de la clase que crea la serpiente de la manera que se pide
     * @param juego - El juego al cual pertenece la serpiente
     */
    public SnakeBot(SnakeGame juego) {
        super(juego);
    }

    /**
     * Decide un nuevo movimiento al azar
     * @return - int[] la nueva posicion a la cual se va a mover el bot
     */
    @Override
    protected int[] decidirDireccion(){
        int numAleatorio = (int) (Math.random()*4);
        switch (numAleatorio){
            case 0:
                proxDireccion = "abajo";
                break;
            case 1:
                proxDireccion = "arriba";
                break;
            case 2:
                proxDireccion = "derecha";
                break;
            case 3:
                proxDireccion = "izquierda";
                break;
        }
        return super.decidirDireccion();
    }

}
