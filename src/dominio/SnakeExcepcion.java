package dominio;

public class SnakeExcepcion extends Exception{

    public static final String COLOR_NULO = "No se ha seleccionado un color";

    public SnakeExcepcion(String message) {
        super(message);
    }
}
