package test;

import dominio.Alimento;
import dominio.Snake;
import dominio.SnakeGame;
import org.junit.Assert;
import org.junit.Test;
import persistencia.SnakeIO;
import presentacion.SnakeGUI;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SnakeTest {

    @Test
    public void snakeTest(){
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        LinkedList<int[]> posicionesSp = snakeT.getSerpiente();
        //La primera posicion de la lista encadenada contiene
        //las coordenadas de la cabeza de la serpiente que por defecto siempre se crea en {cantidad/2, cantidad/2}
        int[] posEsperada = {juego.getCantidad()/2, juego.getCantidad()/2};
        //Assert
        Assert.assertArrayEquals(posEsperada, posicionesSp.get(0));
    }

    @Test
    public void avanzarTest(){
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        //Por defecto el primer movimiento siempre es hacia adelante
        snakeT.avanzar();
        LinkedList<int[]> posicionesSp = snakeT.getSerpiente();
        int[] posicionEsperada = {juego.getCantidad()/2, juego.getCantidad()/2 - 1};
        //Assert
        Assert.assertArrayEquals(posicionEsperada, posicionesSp.get(0));
        //Cambiar la direccion hacia donde va a avanzar la serpiente
        snakeT.cambiarDireccion("derecha");
        snakeT.avanzar();
        int[] posicionEsperada2 = {juego.getCantidad()/2 + 1, juego.getCantidad()/2 - 1};
        //Assert
        Assert.assertArrayEquals(posicionEsperada2, posicionesSp.get(0));
    }

    @Test
    public void verificarMovimiento(){
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        //Hacer avanzar hacia arriba a una serpiente hasta que se choque contra
        //un borde para verificar que murio
        for (int i = 0; i < 26; i++) {
            snakeT.avanzar();
        }
        boolean vivo = snakeT.isVivo();
        //Assert
        Assert.assertFalse(vivo);
    }

    @Test
    public void pausarTest(){
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        //Pausar el juego
        juego.pausar(true);
        //Assert
        Assert.assertTrue(SnakeGame.pausa);
        //Quitar la pausa del juego
        juego.pausar(false);
        Assert.assertFalse(SnakeGame.pausa);
    }

}
