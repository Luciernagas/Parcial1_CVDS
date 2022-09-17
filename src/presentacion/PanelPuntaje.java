package presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * clase de la parte grafica que maneja la parte del panel del juego
 */
public class PanelPuntaje extends JPanel {
    SnakeGUI gui;

    /**
     * contructor del panel del juego
     * @param gui
     */
    public PanelPuntaje(SnakeGUI gui) {
        this.gui = gui;
        setLayout(new FlowLayout(FlowLayout.CENTER, 155, 20));
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    }

    /**
     * metodo que dibuja los componentes dentro del panel
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(new Color(232, 46, 46));
        g.setColor(Color.WHITE);
        informacionJ1(g);
        if(gui.getCantidadJugadores() == 2){
            informacionJ2(g);
        }
    }

    private void informacionJ1(Graphics g){
        if(gui.getSnake_Game().getSnake_1().getPoder() != null){
            g.drawString("Puntaje " + gui.getSnake_Game().getSnake_1().getNombre() + ": " + gui.getSnake_Game().getSnake_1().getPuntaje() + " | "
                    + "Poder:" + " " + gui.getSnake_Game().getSnake_1().getPoder().toString(), 10, 25);
        }
        else{
            g.drawString("Puntaje " + gui.getSnake_Game().getSnake_1().getNombre() + ": " + gui.getSnake_Game().getSnake_1().getPuntaje() + " | "
                    + "Poder:" + " " + "null", 10, 25);
        }
    }

    private void informacionJ2(Graphics g){
        if(gui.getSnake_Game().getSnake_2().getPoder() != null){
            g.drawString("Puntaje " + gui.getSnake_Game().getSnake_2().getNombre() + ": " + gui.getSnake_Game().getSnake_2().getPuntaje() + " | "
                    + "Poder:" + " " + gui.getSnake_Game().getSnake_2().getPoder().toString(), 295, 25);
        }
        else{
            g.drawString("Puntaje " + gui.getSnake_Game().getSnake_2().getNombre() + ": " + gui.getSnake_Game().getSnake_2().getPuntaje() + " | "
                    + "Poder:" + " " + "null", 295, 25);
        }
    }
}
