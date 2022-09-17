package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * clase en la parte logica con la que se maneja los movimientos de la serpiente y sus metodos asociados
 */
public class Snake implements Serializable {

    public static int contadorJugadores = 1;
    private final SnakeGame juego;
    private Sorpresa poder;
    private Sorpresa poderActivo;
    private int id;
    private int tiempoPoderActivo;
    private int puntaje;
    private Color colorCabeza;
    private Color colorCuerpo;
    private String nombre;
    protected final LinkedList<int[]> serpiente = new LinkedList<>();
    protected String direccion;
    protected String proxDireccion;
    private int tempo = 80;
    private boolean vivo;
    private boolean unidadPendiente = false;
    private boolean proxUnidadPendiente = false;
    private boolean lupaActiva = false;

    /**
     * constructor de la clase que crea la serpiente de la manera que se pide
     * @param juego
     */
    public Snake(SnakeGame juego){
        this.juego = juego;
        this.puntaje = 0;
        this.vivo = true;
        this.direccion = "arriba";
        this.proxDireccion = "arriba";
        this.poder = null;
        this.poderActivo = null;
        this.tiempoPoderActivo = 0;
        int cantidad = 50;
        if(contadorJugadores == 1){
            this.id = 1;
            int[] p1 = {cantidad /2, (cantidad /2)};
            int[] p2 = {cantidad /2, (cantidad /2) +1};
            int[] p3 = {cantidad /2, (cantidad /2) +2};
            serpiente.add(p1);
            serpiente.add(p2);
            serpiente.add(p3);
            contadorJugadores ++;
        }
        else if(contadorJugadores == 2){
            this.id = 2;
            int[] p1 = {cantidad /2 + 5, (cantidad /2)};
            int[] p2 = {cantidad /2 + 5, (cantidad /2) +1};
            int[] p3 = {cantidad /2 + 5, (cantidad /2) +2};
            serpiente.add(p1);
            serpiente.add(p2);
            serpiente.add(p3);
        }

    }

    /**
     * Hace que la serpiente avance en el tablero
     */
    public void avanzar(){
        int[] nueva = decidirDireccion();

        if(!(verificarMovimiento(nueva))){
            if(verificarPuntos(nueva)){
                this.agregar(nueva);
                if(unidadPendiente){
                    proxUnidadPendiente = true;
                }
                unidadPendiente = false;
            }
            else if(proxUnidadPendiente){
                this.agregar(nueva);
                proxUnidadPendiente = false;
            }
            else{
                moverSerpiente(nueva);
                verificarTiempoPoder();
                juego.verificarTiempoConsumibles();
                juego.verificarTiempoSpawn();
            }
        }
        else{
            morir();
        }
    }

    /**
     * Hace que la serpiente se mueva hacia la siguiente posicion
     * @param nuevaPos - La siguiente posicion hacia la cual se va a mover la serpiente
     */
    private void moverSerpiente(int[] nuevaPos){
        for (int i = serpiente.size()-1; i > 0; i--) {
            serpiente.set(i, serpiente.get(i-1));
        }
        serpiente.set(0, nuevaPos);
    }

    /**
     * Decide la nueva direccion de la serpiente
     * @return int[] - La nueva posicion de la cabeza de la serpiente
     */
    protected int[] decidirDireccion(){
        int dx = 0;
        int dy = 0;
        switch (proxDireccion){
            case "arriba":
                if(direccion.equals("abajo")){
                    dy = 1;
                    proxDireccion = "abajo";
                }
                else {
                    dy = -1;
                }
                break;
            case "abajo":
                if(direccion.equals("arriba")){
                    dy = -1;
                    proxDireccion = "arriba";
                }
                else{
                    dy = 1;
                }
                break;
            case "derecha":
                if(direccion.equals("izquierda")){
                    dx = -1;
                    proxDireccion = "izquierda";
                }
                else{
                    dx = 1;
                }
                break;
            case "izquierda":
                if(direccion.equals("derecha")){
                    dx = 1;
                    proxDireccion = "derecha";
                }
                else{
                    dx = -1;
                }
                break;
        }
        direccion = proxDireccion;
        return new int[]{serpiente.get(0)[0] + dx, serpiente.get(0)[1] + dy};
    }

    /**
     * Verifica si en el siguiente movimiento la serpiente va a morir
     * @param nuevaDir - La siguiente posicion hacia la cual se va a mover la serpiente
     * @return boolean - true si la serpiente va a morir, false si la serpiente va a segui con vida
     */
    private boolean verificarMovimiento(int[] nuevaDir){
        boolean bandera = false;
        if(juego.getCantidadJugadores() == 1){
            bandera = nuevaDir[0] < 0 || nuevaDir[0] >= 50 || nuevaDir[1] < 0 || nuevaDir[1] >= 50 || suicidio(nuevaDir);
        }
        else{
            bandera = nuevaDir[0] < 0 || nuevaDir[0] >= 50 || nuevaDir[1] < 0 || nuevaDir[1] >= 50 || suicidio(nuevaDir) || choqueSerpiente(nuevaDir);
        }
        return bandera;
    }

    private boolean choqueSerpiente(int[] nDireccion){
        boolean bandera = false;
        if(this.id == 1){
            for (int[] pos: juego.getSnake_2().getSerpiente()) {
                if(nDireccion[0] == pos[0] && nDireccion[1] == pos[1]){
                    bandera = true;
                    break;
                }
            }
        }
        else{
            for (int[] pos: juego.getSnake_1().getSerpiente()) {
                if(nDireccion[0] == pos[0] && nDireccion[1] == pos[1]){
                    bandera = true;
                    break;
                }
            }
        }
        return bandera;
    }

    /**
     * Verifica si la serpiente va a sumar puntos
     * @param nuevaPos - La siguiente posicion hacia la cual se va a mover la serpiente
     * @return boolean - true si la serpiente va a sumar puntos, false si la serpiente no va a sumar puntos
     */
    private boolean verificarPuntos(int[] nuevaPos){
        boolean resultado = false;
        int posConsumible = verificarComida(nuevaPos);
        Alimento alm;
        if(posConsumible >= 0){
            if(juego.getAlimentos().get(posConsumible) != null){
                alm = juego.getAlimentos().get(posConsumible);
                if(!lupaActiva){
                    resultado = alm.sumarPuntaje(this);
                }
                else{
                    setLupaActiva(false);
                }
                juego.generarUnAlimento(posConsumible);
            }
        }
        else{
            if(verificarSopresas(nuevaPos)){
                if(juego.getSorpresa() != null){
                    poder = juego.getSorpresa();
                    juego.generarUnaSorpresa();
                }
            }
            else if(juego.getSpawn() != null){
                if(nuevaPos[0] == juego.getSpawn().getFila() && nuevaPos[1] == juego.getSpawn().getColumna()){
                    morir();
                }
            }
        }
        return resultado;
    }

    /**
     * Verifica si la serpiente va a comer algun consumible (Alimentos)
     * @param nuevaPos - La siguiente posicion hacia la cual se va a mover la serpiente
     * @return int - 0 para fruta, 1 para fruta arcoiris
     */
    private int verificarComida(int[] nuevaPos){
        int posComida = -1;
        for (Alimento cble: juego.getAlimentos()) {
            if(cble.getPosicion()[0] == nuevaPos[0] && cble.getPosicion()[1] == nuevaPos[1]){
                posComida = juego.getAlimentos().indexOf(cble);
            }
        }
        return posComida;
    }

    /**
     * Verifica si la serpiente va a comer algun consumible (Sorpresa)
     * @param nuevaPos - La siguiente posicion hacia la cual se va a mover la serpiente
     * @return int - posicion de las sorpresa en la coleccion
     */
    private boolean verificarSopresas(int[] nuevaPos){
        Sorpresa cble = juego.getSorpresa();
        return cble.getPosicion()[0] == nuevaPos[0] && cble.getPosicion()[1] == nuevaPos[1];
    }

    /**
     * Verifica si la serpiente choco contra ella misma
     * @param nuevaPos - La siguiente posicion hacia la cual se va a mover la serpiente
     * @return boolean - true si la serpiente choco contra ella misma, de lo contrario false
     */
    private boolean suicidio(int[] nuevaPos){
        boolean bandera = false;
        for (int[] posSp: serpiente) {
            if(posSp[0] == nuevaPos[0] && posSp[1] == nuevaPos[1]){
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    /**
     * metodo que verifica el tiempo de duracion de los poderes y llega a ser nesesario
     */
    private void verificarTiempoPoder(){
        if(tiempoPoderActivo > 0 && tiempoPoderActivo < 100){
            tiempoPoderActivo += 1;
        }
        else if(tiempoPoderActivo == 100){
            tiempoPoderActivo = 0;
            poderActivo.quitarEfecto(this);
            poderActivo = null;
        }
    }

    /**
     * metodo que hace que la sorpresa se utilize el poder al momento de espichar la tecla enter
     */
    public void usarPoder(){
        if(poder != null && poderActivo == null){
            poder.afectar(this);
            poderActivo = (Sorpresa) poder.clone();
            poder = null;
            tiempoPoderActivo = 1;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColorCabeza(Color colorCabeza) {
        this.colorCabeza = colorCabeza;
    }

    public void setColorCuerpo(Color colorCuerpo) {
        this.colorCuerpo = colorCuerpo;
    }

    public SnakeGame getJuego() {
        return juego;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getTempo() {
        return tempo;
    }

    public Sorpresa getPoder() {
        return poder;
    }

    public Sorpresa getPoderActivo() {
        return poderActivo;
    }

    /** le suma al tempo el valor deseado
     * @param modificacion
     */
    public void modificarTempo(int modificacion){
        this.tempo += modificacion;
    }


    public LinkedList<int[]> getSerpiente() {
        return serpiente;
    }

    public Color getColorCabeza() {
        return colorCabeza;
    }

    public Color getColorCuerpo() {
        return colorCuerpo;
    }

    public int getId() {
        return id;
    }

    public void setUnidadPendiente() {
        this.unidadPendiente = true;
    }

    public void setPoder(Sorpresa poder) {
        this.poder = poder;
    }

    public void setLupaActiva(boolean estado) {
        this.lupaActiva = estado;
    }

    /**
     *metodo que asigna xima direccion de la serpiente
     * @param nuevaD
     */
    public void cambiarDireccion(String nuevaD){
        proxDireccion = nuevaD;
    }

    /**
     * agrega una nueva peica a la serpiente sdeseada
     * @param nueva
     */
    public void agregar(int[] nueva){
        serpiente.addFirst(nueva);
    }

    /**
     * remueva la ultima pieca de la serpiente deseada
     */
    public void remover(){
        serpiente.removeLast();
    }

    public boolean isVivo() {
        return vivo;
    }

    /**
     *metodo creado para colocar a la serpiente del jugador en el estado muerto y termina el juego enviandole mensaje al jugador
     */
    public void morir(){
        this.vivo = false;
        juego.setEnJuego(false);
        JOptionPane.showMessageDialog(null, "Game over :(");
    }

    /**
     * retorna el tamaño de la serpiente deseada
     * @returne  .size()
     */
    public int size(){
        return serpiente.size();
    }

    /**
     * suma el puntaje deseado
     * @param suma
     */
    public void sumarPuntaje(int suma){
        this.puntaje += suma;
    }
}
