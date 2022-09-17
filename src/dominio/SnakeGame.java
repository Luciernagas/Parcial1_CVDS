package dominio;

import presentacion.SnakeGUI;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que se encarga de manejar el tablero del juego y los alimentos y sorpresas que hay en el juego
 */
public class SnakeGame implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private final int cantidad = 50;
    private int cantidadJugadores;
    private ArrayList<Snake> serpientes = new ArrayList<>();
    private boolean enJuego = true;
    public static boolean pausa = false;
    public Sorpresa sorpresa;
    public Spawn spawn;
    public ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

    /**
     * Constructor de la clase Snake
     */
    public SnakeGame(int cantidadJugadores) {
        Snake snake_2;
        Snake.contadorJugadores = 1;
        this.cantidadJugadores = cantidadJugadores;
        Snake snake_1 = new Snake(this);
        serpientes.add(snake_1);
        if(cantidadJugadores == 2){
            if(!SnakeGUI.j2Bot){
                snake_2 = new Snake(this);
            }
            else{
                snake_2 = new SnakeBot(this);
            }
            serpientes.add(snake_2);
        }
        generarAlimento();
        generarSorpresa();
    }

    /**
     * Verifica el tiempo que llevan los consumibles en el juego para volver a crearlos en otras posiciones
     */
    public void verificarTiempoConsumibles(){
        int pos;
        for (Alimento cons: alimentos) {
            if(cons.getTiempo() >= 80){
                pos = alimentos.indexOf(cons);
                generarUnAlimento(pos);
            }
        }
    }

    public void verificarTiempoSpawn(){
        if(spawn != null){
            if(spawn.getTiempoDuracion() == 100){
                spawn = null;
            }
            else {
                spawn.sumarTiempo();
            }
        }
    }

    public void avanzarTiempo(){
        for (Alimento alimento: alimentos) {
            alimento.sumarTiempo();
        }
    }

    /**
     * Genera los alimentos al empezar la partida
     */
    private void generarAlimento(){


        for (int i = 0; i < 6; i++) {
            int[] ayb = comprobarayb2generaralimento();

            if(i == 0){
                alimentos.add(new Fruta(ayb[0], ayb[1]));
            }
            else if(i == 1){
                alimentos.add(new FrutaArcoiris(ayb[0], ayb[1]));
            }
            else if(i == 2){
                alimentos.add(new Veneno(ayb[0], ayb[1]));
            }
            else if(i == 3){
                alimentos.add(new Dulce(ayb[0], ayb[1]));
            }
            else if(i == 4){
                alimentos.add(new NuevoElemento(ayb[0], ayb[1]));
            }
        }
    }

    public int[] comprobarayb2generaralimento(){
        int a,b;
        a = (int) (Math.random() * cantidad);
        b = (int) (Math.random()*cantidad);
        if(cantidadJugadores == 1){
            while(revisarSerpiente(a, b, serpientes.get(0))){
                a = (int) (Math.random()*cantidad);
                b = (int) (Math.random()*cantidad);
            }
        }
        else{
            while(revisarSerpiente(a, b, serpientes.get(0)) && revisarSerpiente(a, b, serpientes.get(1))){
                a = (int) (Math.random()*cantidad);
                b = (int) (Math.random()*cantidad);
            }
        }
        return new int[]{a,b};
    }

    /**
     * Genera las sorpresas al empezar la partida
     */
    private void generarSorpresa(){
        int c;
        int[] ayb = comprobarayb2generarsorpresa();

        c = (int) (Math.random()*3);

        if(c == 0) {
            sorpresa = new FlechaVelocidad(ayb[0], ayb[1], this);
        }
        else if(c == 1){
            sorpresa = new BloqueTrampa(ayb[0], ayb[1], this);
        }
        else if(c == 2){
            if(cantidadJugadores > 1){
                sorpresa = new Lupa(ayb[0], ayb[1], this);
            }
            else{
                generarSorpresa();
            }
        }

    }

    public void generarUnAlimento(int tipo){
        int[] ayb = comprobarAyb();

        if(tipo == 0){
            alimentos.set(tipo, new Fruta(ayb[0], ayb[1]));
        }
        else if(tipo == 1){
            alimentos.set(tipo, new FrutaArcoiris(ayb[0], ayb[1]));
        }
        else if (tipo == 2){
            alimentos.set(tipo, new Veneno(ayb[0], ayb[1]));
        }
        else if (tipo == 3){
            alimentos.set(tipo, new Dulce(ayb[0], ayb[1]));
        }
        else if(tipo == 4){
            alimentos.set(tipo,new NuevoElemento(ayb[0], ayb[1]));
        }
    }

    public int[] comprobarAyb(){
        int a = (int) (Math.random() * cantidad);
        int b = (int) (Math.random()*cantidad);
        while(revisarSerpiente(a, b, serpientes.get(0))){
            a = (int) (Math.random()*cantidad);
            b = (int) (Math.random()*cantidad);
        }
        return new int[]{a, b};
    }

    /**
     * Genera una sorpresa en especifico para que sea agregado a la coleccion de consumibles
     */
    public void generarUnaSorpresa(){
        int tipo = (int) (Math.random()*3);
        int[] ayb = comprobarAyb();
        if(tipo == 0){
            sorpresa =  new FlechaVelocidad(ayb[0], ayb[1], this);
        }
        else if(tipo == 1){
            sorpresa =  new BloqueTrampa(ayb[0], ayb[1], this);
        }
        else if(tipo == 2){
            if(cantidadJugadores > 1){
                sorpresa = new Lupa(ayb[0], ayb[1], this);
            }
            else{
                generarUnaSorpresa();
            }
        }
    }

    public int[] comprobarayb2generarsorpresa(){
        int a,b;
        a = (int) (Math.random() * cantidad);
        b = (int) (Math.random()*cantidad);
        if(cantidadJugadores == 1){
            while(revisarSerpiente(a, b, serpientes.get(0))){
                a = (int) (Math.random()*cantidad);
                b = (int) (Math.random()*cantidad);
            }
        }
        else{
            while(revisarSerpiente(a, b, serpientes.get(0)) && revisarSerpiente(a, b, serpientes.get(1))){
                a = (int) (Math.random()*cantidad);
                b = (int) (Math.random()*cantidad);
            }
        }
        return new int[]{a,b};
    }


    /**
     * Revisa que la coordenada x e y no este encima de una posicion en la cual ya se encuentra la serpiente
     * @param x - coordenada i
     * @param y - coordenada j
     * @return boolean - true si la posicion es valida, de lo contrario false
     */
    public boolean revisarSerpiente(int x, int y, Snake serpiente){
        boolean valido = true;
        for (int[] posSerp: serpiente.getSerpiente()) {
            if(x == posSerp[0] && y == posSerp[1]){
                valido = false;
                break;
            }
        }
        return !valido;
    }

    /**
     * Cambia la direccion en la que va la serpiente
     * @param nuevaDir - La nueva direccion
     */
    public void changeDireccion(String nuevaDir, Snake serpiente){
        serpiente.cambiarDireccion(nuevaDir);
    }

    public void hacerAvanzar(int numeroSerpiente){
        Snake serpiente = serpientes.get(numeroSerpiente);
        serpiente.avanzar();
    }

    /**
     * Pausa el juego
     * @param accion - true si se quiere pausar, false si se quiere quitar la pausa
     */
    public void pausar(boolean accion){
        pausa = accion;
    }

    /**
     * Hace que se inicie el juego
     * @param enJuego - Valor booleano
     */
    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
    }

    public void setSpawn(int fila, int columna){
        spawn = new Spawn(fila, columna);
    }

    public void setNombreSerpiente(int numeroSerpiente, String nombre){
        Snake serpiente = serpientes.get(numeroSerpiente);
        serpiente.setNombre(nombre);
    }

    public void setColorCabeza(int numeroSerpiente, Color color){
        Snake serpiente = serpientes.get(numeroSerpiente);
        serpiente.setColorCabeza(color);
    }

    public void setColorCuerpo(int numeroSerpiente, Color color){
        Snake serpiente = serpientes.get(numeroSerpiente);
        serpiente.setColorCuerpo(color);
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public Sorpresa getSorpresa() {
        return sorpresa;
    }

    public Spawn getSpawn() {
        return spawn;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public Snake getSerpiente(int numeroSerpiente){
        return serpientes.get(numeroSerpiente);
    }

    public Snake getSnake_1() {
        return serpientes.get(0);
    }

    public Snake getSnake_2() {
        return serpientes.get(1);
    }

    public void configurarAlimento(int x, int y){
        alimentos.add(new Fruta(x, y));
    }

    public void configurarSorpresa(int x, int y){
        sorpresa = new FlechaVelocidad(x, y, this);
    }

    public boolean isEnJuego() {
        return enJuego;
    }
}
