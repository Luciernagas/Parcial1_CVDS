package test;

import dominio.Alimento;
import dominio.Snake;
import dominio.SnakeGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import persistencia.SnakeIO;
import presentacion.SnakeGUI;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SnakeTest {

    @Test
    public void dado_laPosicion_Cuando_seIniciaElJuego_Entonces_retorna25coma25(){
        //Arrange
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        int[] posEsperada = {25,25};
        //Action
        LinkedList<int[]> posicionesSp = snakeT.getSerpiente();
        //Assertion
        Assertions.assertArrayEquals(posEsperada, posicionesSp.get(0));
    }

    @Test
    public void dado_LaPosicionDespues_Cuando_esElPrimerMovimiento_Entonces_retorna25coma24() {
        //Arrange
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        int[] posicionEsperada = {juego.getCantidad() / 2, juego.getCantidad() / 2 - 1};
        int[] posicion = {25, 24};
        //Action
        snakeT.avanzar();
        LinkedList<int[]> posicionesSp = snakeT.getSerpiente();
        //Assertion
        Assertions.assertArrayEquals(posicion, posicionesSp.get(0));
    }

    @Test
    public void dado_SerpienteEstaViva_Cuando_ChocaContraUnMuro_Entonces_retornaFalse(){
        //Arrange
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        boolean respuestaEsperada = false;
        //Action
        for (int i = 0; i < 26; i++) {
            snakeT.avanzar();
        }
        boolean vivo = snakeT.isVivo();
        //Assertion
        Assertions.assertEquals(respuestaEsperada,vivo);
    }

    @Test
    public void dado_unBoolean_Cuando_ElJuegoNoEstaEnPausa_Entonces_retornaFalse(){
        //Arrange
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        boolean respuestaEsperada = false;
        //Action
        juego.pausar(true);
        Assertions.assertTrue(SnakeGame.pausa);
        juego.pausar(false);
        //Assertion
        Assertions.assertEquals(respuestaEsperada, SnakeGame.pausa);
    }

    @Test
    public void dado_unBoolean_Cuando_ElJuegoEstaEnPausa_Entonces_retornaTrue(){
        //Arrange
        SnakeGame juego = new SnakeGame(1);
        Snake snakeT = juego.getSerpiente(0);
        boolean respuestaEsperada = true;
        //Action
        juego.pausar(true);
        //Assertion
        Assertions.assertEquals(respuestaEsperada, SnakeGame.pausa);
    }

}
