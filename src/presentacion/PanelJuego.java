package presentacion;

import dominio.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Clase para dibujar la parte grafica del juego
 */
public class PanelJuego extends JPanel {

    private final SnakeGUI gui;
    private final int sizeCelda;
    private Random rand = new Random();

    /**
     * Constructor del panel donde se va a mostrar el juego
     * @param gui - El frame donde se encuentra el panel
     */
    public PanelJuego(SnakeGUI gui) {
        this.gui = gui;
        sizeCelda = SnakeGUI.sizeCelda;
    }

    /**
     * Metodo para dibujar en el panel
     * @param g - Graphics que se va a usar para dibujar
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        SnakeGame juego = gui.getSnake_Game();
        setBackground(gui.colorP);
        g.setColor(gui.colorS);

        //Dibujar consumibles
        for (Alimento consumible: juego.getAlimentos()) {
            if(consumible instanceof Fruta fruta){
                g.setColor(fruta.getColor());
                g.fillRoundRect(fruta.getPosicion()[0]*sizeCelda, fruta.getPosicion()[1]*sizeCelda, sizeCelda,sizeCelda, 0, 0);
            }
            else if(consumible instanceof FrutaArcoiris frutaArc){
                float rC = rand.nextFloat();
                float gC = rand.nextFloat();
                float bC = rand.nextFloat();
                g.setColor(new Color(rC, gC, bC));
                g.fillRoundRect(frutaArc.getPosicion()[0]*sizeCelda, frutaArc.getPosicion()[1]*sizeCelda, sizeCelda,sizeCelda, 0, 0);
            }
            else if(consumible instanceof Veneno fruta){
                g.setColor(fruta.getColor());
                g.fillOval(fruta.getPosicion()[0]*sizeCelda, fruta.getPosicion()[1]*sizeCelda, sizeCelda,sizeCelda);
            }
            else if(consumible instanceof Dulce fruta){
                g.setColor(fruta.getColor());
                g.fillOval(fruta.getPosicion()[0]*sizeCelda, fruta.getPosicion()[1]*sizeCelda, sizeCelda,sizeCelda);
            }
            else if(consumible instanceof NuevoElemento nuevoElemento){
                g.setColor(nuevoElemento.getColor());
                g.drawOval(nuevoElemento.getPosicion()[0]*sizeCelda, nuevoElemento.getPosicion()[1]*sizeCelda, sizeCelda,sizeCelda);
            }
        }

        Sorpresa sorpresa = juego.getSorpresa();
        if(sorpresa instanceof FlechaVelocidad flecha){
            g.setColor(Color.WHITE);
            g.drawLine(flecha.getPosicion()[0]*sizeCelda + 6, flecha.getPosicion()[1]*sizeCelda,
                    flecha.getPosicion()[0]*sizeCelda + 6, flecha.getPosicion()[1]*sizeCelda + sizeCelda);
        }
        else if(sorpresa instanceof BloqueTrampa bloque){
            g.setColor(Color.RED);
            g.drawLine(bloque.getPosicion()[0]*sizeCelda, bloque.getPosicion()[1]*sizeCelda,
                    bloque.getPosicion()[0]*sizeCelda + 8, bloque.getPosicion()[1]*sizeCelda + sizeCelda);
            g.drawLine(bloque.getPosicion()[0]*sizeCelda + 8, bloque.getPosicion()[1]*sizeCelda,
                    bloque.getPosicion()[0]*sizeCelda, bloque.getPosicion()[1]*sizeCelda + sizeCelda);
        }
        else if(sorpresa instanceof Lupa lupa){
            g.setColor(Color.WHITE);
            g.drawOval(lupa.getPosicion()[0]*sizeCelda, lupa.getPosicion()[1]*sizeCelda, sizeCelda,sizeCelda);
        }

        //Dibujar spawn
        if(juego.getSpawn() != null){
            g.setColor(Color.WHITE);
            g.fillRoundRect(juego.getSpawn().getFila()*sizeCelda, juego.getSpawn().getColumna()*sizeCelda, sizeCelda,sizeCelda, 0, 0);
        }

        //Dibujar serpiente 1
        for (int[] posSerpiente: juego.getSnake_1().getSerpiente()) {
            if(juego.getSnake_1().getSerpiente().indexOf(posSerpiente) == 0){
                g.setColor(juego.getSnake_1().getColorCabeza());
            }
            else{
                g.setColor(juego.getSnake_1().getColorCuerpo());
            }
            g.fillRect(posSerpiente[0]*sizeCelda, posSerpiente[1]*sizeCelda, sizeCelda, sizeCelda);
        }

        if(juego.getCantidadJugadores() == 2){
            //Dibujar serpiente 2
            for (int[] posSerpiente: juego.getSnake_2().getSerpiente()) {
                if(juego.getSnake_2().getSerpiente().indexOf(posSerpiente) == 0){
                    g.setColor(juego.getSnake_2().getColorCabeza());
                }
                else{
                    g.setColor(juego.getSnake_2().getColorCuerpo());
                }
                g.fillRect(posSerpiente[0]*sizeCelda, posSerpiente[1]*sizeCelda, sizeCelda, sizeCelda);
            }
        }

    }


}
