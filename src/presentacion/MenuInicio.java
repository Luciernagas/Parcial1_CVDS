package presentacion;

import dominio.SnakeExcepcion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Clase para mostrar la interfaz del menu inicial
 */
public class MenuInicio extends JFrame {

    private ImageIcon icono;
    private JPanel contenedor;
    private JPanel contenedorBotones;
    private JLabel letrero;
    private JLabel ekans;
    private JButton unJugador;
    private JButton dosJugadores;
    private SnakeGUI interfaz;

    /**
     * Constructor del menu de inicio que prepara los elementos y las acciones
     */
    public MenuInicio(){
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Prepara los distintos elementos del menu inicial
     */
    private void prepareElementos(){
        setTitle("SnOOPe");
        setSize(480, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        icono = new ImageIcon("imagenes/icono.png");
        setIconImage(icono.getImage());
        prepareLetrero();
        prepareBotones();
    }

    /**
     * Prepara el encabezado con el nombre del juego
     */
    private void prepareLetrero(){
        contenedor = new JPanel();
        contenedor.setLayout(new BorderLayout());
        contenedor.setBackground(Color.BLACK);
        add(contenedor);
        letrero = new JLabel();
        letrero.setIcon(new ImageIcon("imagenes/letrero.png"));
        ekans = new JLabel();
        ekans.setIcon(new ImageIcon("imagenes/ekans.png"));
        contenedor.add(letrero, BorderLayout.NORTH);
        contenedor.add(ekans, BorderLayout.CENTER);
    }

    /**
     * Prepara los botones de un jugador y dos jugadores
     */
    private void prepareBotones(){
        contenedorBotones = new JPanel();
        contenedorBotones.setBackground(Color.BLACK);
        unJugador = new JButton("Un jugador");
        unJugador.setBackground(new Color(247, 94, 85));
        dosJugadores = new JButton("Dos jugadores");
        dosJugadores.setBackground(new Color(247, 94, 85));
        contenedorBotones.add(unJugador);
        contenedorBotones.add(dosJugadores);
        contenedor.add(contenedorBotones,BorderLayout.SOUTH);
    }

    /**
     * Prepara las acciones del menu
     */
    private void prepareAcciones(){
        unJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurarJugador1(1);
                interfaz.setVisible(true);
                setVisible(false);
            }
        });

        dosJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] opciones = {"Bot", "Persona"};
                int deisicion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de jugador 2","Seleccion J2",
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, opciones, null);
                if(deisicion == JOptionPane.YES_OPTION){
                    SnakeGUI.j2Bot = true;
                }
                configurarJugador1(2);
                opcionJugador2();
                interfaz.setVisible(true);
                setVisible(false);
            }
        });
    }

    /**
     * Configura al primer jugador
     * @param cantidadJugadores - La cantidad de jugadores seleccionada por el usuario
     */
    private void configurarJugador1(int cantidadJugadores){
        interfaz = new SnakeGUI(cantidadJugadores);
        String nombre1 = JOptionPane.showInputDialog(null, "Ingrese su nombre J1");
        interfaz.getSnake_Game().setNombreSerpiente(0, nombre1);
        try {
            asignarColorS1();
        }catch (SnakeExcepcion exc){
            JOptionPane.showMessageDialog(null, exc.getMessage());
            interfaz.getSnake_Game().setColorCabeza(0, Color.BLUE);
            interfaz.getSnake_Game().setColorCuerpo(0, Color.GREEN);
        }
    }


    /**
     * Configura al segundo jugador
     */
    private void opcionJugador2(){
        Random rand = new Random();
        boolean bandera = true;
        while(bandera){
            String nombre2 = JOptionPane.showInputDialog(null, "Ingrese su nombre J2");
            interfaz.getSnake_Game().setNombreSerpiente(1, nombre2);
            bandera = validarNombre();
        }
        try {
            asignarColorS2();
        }catch (SnakeExcepcion exc){
            JOptionPane.showMessageDialog(null, exc.getMessage());
            interfaz.getSnake_Game().setColorCabeza(1, Color.RED);
            interfaz.getSnake_Game().setColorCuerpo(1, Color.orange);
            if(interfaz.getSnake_Game().getSerpiente(0).getColorCabeza() == interfaz.getSnake_Game().getSerpiente(1).getColorCabeza()
                    || interfaz.getSnake_Game().getSerpiente(0).getColorCuerpo() == interfaz.getSnake_Game().getSerpiente(1).getColorCuerpo()){
                float rC = rand.nextFloat();
                float gC = rand.nextFloat();
                float bC = rand.nextFloat();
                float rC2 = rand.nextFloat();
                float gC2 = rand.nextFloat();
                float bC2 = rand.nextFloat();
                interfaz.getSnake_Game().setColorCabeza(1, new Color(rC, gC, bC));
                interfaz.getSnake_Game().setColorCuerpo(1, new Color(rC2, gC2, bC2));
            }
        }
    }

    /**
     * Asigna el color a la serpiente 1
     * @throws SnakeExcepcion - Arroja excepcion cuando un jugador no escoge color
     */
    private void asignarColorS1() throws SnakeExcepcion {
        Color colorCabeza1 = JColorChooser.showDialog(null, "Seleccione un nuevo color principal", Color.BLUE);
        interfaz.getSnake_Game().setColorCabeza(0, colorCabeza1);
        Color colorCuerpo1 = JColorChooser.showDialog(null, "Seleccione un nuevo color secundario", Color.GREEN);
        if(colorCuerpo1 == null || colorCabeza1 == null){
            throw new SnakeExcepcion(SnakeExcepcion.COLOR_NULO);
        }
        else{
            while(colorCabeza1.equals(colorCuerpo1)){
                colorCuerpo1 = JColorChooser.showDialog(null, "Seleccione un nuevo color secundario", Color.GREEN);
            }
        }
        interfaz.getSnake_Game().setColorCuerpo(0, colorCuerpo1);
    }

    /**
     * Asigna el color a la serpiente 2
     * @throws SnakeExcepcion - Arroja excepcion cuando un jugador no escoge color
     */
    private void asignarColorS2() throws SnakeExcepcion{
        Color colorcabeza2 = JColorChooser.showDialog(null, "Seleccione un nuevo color principal", Color.BLUE);
        Color colorcuerpo2 = JColorChooser.showDialog(null, "Seleccione un nuevo color secundario", Color.GREEN);
        if(colorcabeza2 == null || colorcuerpo2 == null){
            throw new SnakeExcepcion(SnakeExcepcion.COLOR_NULO);
        }
        else{
            while(colorcabeza2.equals(interfaz.getSnake_Game().getSnake_1().getColorCabeza())){
                colorcabeza2 = JColorChooser.showDialog(null, "Seleccione un nuevo color principal", Color.GREEN);
            }

            while(colorcabeza2.equals(colorcuerpo2)){
                colorcuerpo2 = JColorChooser.showDialog(null, "Seleccione un nuevo color secundario", Color.GREEN);
            }
            while(colorcuerpo2.equals(interfaz.getSnake_Game().getSnake_1().getColorCuerpo())){
                colorcuerpo2 = JColorChooser.showDialog(null, "Seleccione un nuevo color secundario", Color.GREEN);
            }
            interfaz.getSnake_Game().setColorCabeza(1, colorcabeza2);
            interfaz.getSnake_Game().setColorCuerpo(1, colorcuerpo2);
        }
    }

    /**
     * Valida que los nombres de las dos serpientes no sean iguales
     * @return - booleano
     */
    private boolean validarNombre(){
        return interfaz.getSnake_Game().getSnake_1().getNombre().equals(interfaz.getSnake_Game().getSnake_2().getNombre());
    }

    public static void main(String[] args) {
        MenuInicio inicio = new MenuInicio();
        inicio.setVisible(true);
    }
}
