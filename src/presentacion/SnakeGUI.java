package presentacion;

import dominio.Snake;
import dominio.SnakeGame;
import persistencia.SnakeIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase dedicada a la interfaz grafica del juego
 */
public class SnakeGUI extends JFrame {
    public static int sizeVentana, sizeCelda, cantidadCelda = 50;
    public int cantidadJugadores = 1;
    public static boolean j2Bot = false;
    public SnakeGame snake_Game;
    private boolean inicio = false;

    //Color tablero
    public Color colorP = Color.BLACK;
    public Color colorS = Color.GRAY;

    //Barra menu
    private JMenuBar barraMenu;
    private JMenu opciones;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem reiniciar;
    private JMenuItem pausa;
    private JMenuItem iniciar;

    //Pantalla de juego
    private ImageIcon icono;
    private JPanel panelPuntaje;
    private JPanel panelJuego;

    /**
     * Constructor de la interfaz del juego
     */
    public SnakeGUI(){
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Constructor de la interfaz del juego especificando la cantidad de jugadores
     * @param cantidadJugadores - cantidad de jugadores
     */
    public SnakeGUI(int cantidadJugadores){
        this.cantidadJugadores = cantidadJugadores;
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Prepara los diferentes elementos del JFrame y de la interfaz del juego
     */
    private void prepareElementos(){
        snake_Game = new SnakeGame(cantidadJugadores);
        //Configurar pantallla
        setTitle("SnOOPe");
        icono = new ImageIcon("imagenes/icono.png");
        setIconImage(icono.getImage());
        setSize(615, 705);
        sizeVentana = 605;
        sizeCelda = sizeVentana / cantidadCelda;
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        prepararMenu();
        setJMenuBar(barraMenu);
        preparareElementosPantalla();
    }

    /**
     * Prepara los elementos que componen el JMenuBar
     */
    private void prepararMenu(){
        barraMenu = new JMenuBar();
        opciones = new JMenu("Opciones");
        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        pausa = new JMenuItem("Pausa");
        iniciar = new JMenuItem("Iniciar");
        reiniciar = new JMenuItem("Reiniciar");
        opciones.add(abrir);
        opciones.add(salvar);
        barraMenu.add(opciones);
        barraMenu.add(iniciar);
        barraMenu.add(pausa);
        barraMenu.add(reiniciar);
        add(barraMenu);
    }

    /**
     * Prepara los elementos de la vista del juego
     */
    private void preparareElementosPantalla(){
        prepararPuntaje();
        preparePantallaJuego();
        panelJuego.repaint();
    }

    /**
     * Prepara el JPanel de puntaje
     */
    private void prepararPuntaje(){
        panelPuntaje = new PanelPuntaje(this);
        add(panelPuntaje, BorderLayout.NORTH);
    }

    /**
     * Prepara el JPanel donde se encuentra el tablero de juego
     */
    private void preparePantallaJuego(){
        panelJuego = new PanelJuego(this);
        add(panelJuego, BorderLayout.CENTER);
    }

    /**
     * Prepara las diferentes acciones y oyentes que usa el juego
     */
    private void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                snake_Game.pausar(true);
                salga();
            }
        });

        prepareAccionesMenu();
        prepareAccionesTeclas();
    }

    /**
     * Prepara las acciones y los oyentes de los elementos del JMenuBar
     */
    private void prepareAccionesMenu(){
        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.pausa = false;
                jugar();
                inicio = true;
            }
        });

        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.pausa = true;
                String[] args = {};
                Snake.contadorJugadores = 1;
                MenuInicio.main(args);
                setVisible(false);
                inicio = false;
                j2Bot = false;
            }
        });

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!SnakeGame.pausa){
                    opcionPausar();
                }
                opcionGuardar();
            }
        });

        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!SnakeGame.pausa){
                    opcionPausar();
                }
                opcionAbrir();
                jugar();
            }
        });

        pausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionPausar();
            }
        });

    }

    /**
     * Prepara las acciones y los oyentes que estan atentos al teclado
     */
    private void prepareAccionesTeclas(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                int codigoTecla = e.getKeyCode();
                switch (codigoTecla) {
                    case KeyEvent.VK_UP:
                        snake_Game.changeDireccion("arriba", snake_Game.getSnake_1());
                        break;
                    case KeyEvent.VK_DOWN:
                        snake_Game.changeDireccion("abajo", snake_Game.getSnake_1());
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake_Game.changeDireccion("derecha", snake_Game.getSnake_1());
                        break;
                    case KeyEvent.VK_LEFT:
                        snake_Game.changeDireccion("izquierda", snake_Game.getSnake_1());
                        break;
                    case KeyEvent.VK_ENTER:
                        snake_Game.getSnake_1().usarPoder();
                        break;
                }

                if(snake_Game.getCantidadJugadores() == 2){
                    switch (codigoTecla) {
                        case KeyEvent.VK_W:
                            snake_Game.changeDireccion("arriba", snake_Game.getSnake_2());
                            break;
                        case KeyEvent.VK_S:
                            snake_Game.changeDireccion("abajo", snake_Game.getSnake_2());
                            break;
                        case KeyEvent.VK_D:
                            snake_Game.changeDireccion("derecha", snake_Game.getSnake_2());
                            break;
                        case KeyEvent.VK_A:
                            snake_Game.changeDireccion("izquierda", snake_Game.getSnake_2());
                            break;
                        case KeyEvent.VK_SPACE:
                            snake_Game.getSnake_2().usarPoder();
                            break;
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * Confirma si el usuario quiere salir del juego
     */
    private void salga(){
        int deisicion = JOptionPane.showConfirmDialog(this, "Desea salir de este programa?", "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(deisicion == 0){
            System.exit(0);
        }
        else{
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            snake_Game.pausar(false);
            jugar();
        }
    }

    private void opcionAbrir(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Abrir Juego");
            int decision = fileChooser.showOpenDialog(this);
            if(decision == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                snake_Game = SnakeIO.abra(file);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Excepcion clase no encontrada",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Excepcion en la operacion de I/O", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Guarda el juego
     */
    private void opcionGuardar(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Juego");
            int decision = fileChooser.showSaveDialog(this);
            if(decision == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                SnakeIO.guardar(file, snake_Game);
            }
        } catch (IOException e) {
            System.out.println("xd");
        }
    }

    /**
     * Pausa el juego
     */
    private void opcionPausar(){
        if(inicio){
            if(SnakeGame.pausa){
                snake_Game.pausar(false);
                jugar();
            }
            else{
                snake_Game.pausar(true);
                JOptionPane.showMessageDialog(null, "En Pausa");
            }
        }
    }

    /**
     * Crea un Thread donde se va a correr y actualizar la parte grafica del juego en tablero
     */
    private void jugar(){
        Runnable tableroRunnable = new HiloTablero(snake_Game, panelJuego);
        Thread hilotablero = new Thread(tableroRunnable);
        if(cantidadJugadores == 1){
            Runnable snakeRunnable = new HiloSnake_1(snake_Game, panelJuego, panelPuntaje);
            Thread hiloMovimiento = new Thread(snakeRunnable);
            hiloMovimiento.start();
        }
        else{
            Runnable snakeRunnable = new HiloSnake_1(snake_Game, panelJuego, panelPuntaje);
            Thread hiloMovimiento = new Thread(snakeRunnable);
            Runnable snakeRunnable2 = new HiloSnake_2(snake_Game, panelJuego, panelPuntaje);
            Thread hiloMovimiento2 = new Thread(snakeRunnable2);
            hiloMovimiento.start();
            hiloMovimiento2.start();
        }
        hilotablero.start();
    }

    public SnakeGame getSnake_Game() {
        return snake_Game;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

}
