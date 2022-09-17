package persistencia;

import dominio.SnakeGame;

import java.io.*;

public abstract  class SnakeIO {

    /**
     * Guarda el juego como un objeto
     * @param archivo - archivo de tipo File
     * @throws IOException - La funcionalidad se encuentra en construccion
     */
    public static void guardar(File archivo, SnakeGame juego) throws IOException {
        ObjectOutputStream guardaAutomata = new ObjectOutputStream(new FileOutputStream(archivo));
        guardaAutomata.writeObject(juego);
        guardaAutomata.close();
    }

    /**
     * Abre un archivo .data donde se haya guardado un juego
     * @param archivo - Archivo que se quiere abrir
     * @return SnakeGame - Juego que se va a abrir
     * @throws IOException - Error al abrir el archivo
     * @throws ClassNotFoundException - Error con la clase del objeto que se va a abrir
     */
    public static SnakeGame abra(File archivo) throws IOException, ClassNotFoundException {
        ObjectInputStream abrirJuego = new ObjectInputStream(new FileInputStream(archivo));
        SnakeGame juegoGuardado = (SnakeGame) abrirJuego.readObject();
        abrirJuego.close();
        return juegoGuardado;
    }
}
