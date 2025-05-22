package interfazGrafica;

import consola.*;
import javax.swing.*;
import java.awt.*;

public class SudokuGUI extends JFrame implements PanelReemplazable, IControladorJuego {
    private JPanel mainPanel; // Panel principal que contendrá los diferentes paneles
    private Sudoku sudokuUsuario;
    private SudokuResuelto sudokuSolucion;
    private puntoMatriz lastSelectedCell;

    // Referencias directas a los paneles para acceder fácilmente
    private DifficultyPanel difficultyPanel;
    private GamePanel gamePanel;

    // Colores para el tablero
    public static final Color BACKGROUND_COLOR = new Color(250, 248, 239);
    public static final Color GRID_COLOR = new Color(187, 173, 160);
    public static final Color FIXED_NUMBER_COLOR = new Color(119, 110, 101);
    public static final Color USER_NUMBER_COLOR = new Color(0, 0, 205);

    public SudokuGUI() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new CardLayout());
        add(mainPanel);

        // Inicializar juego
        sudokuUsuario = new Sudoku();

        // Crear panel de dificultad
        difficultyPanel = new DifficultyPanel(this);
        mainPanel.add(difficultyPanel, "difficultyPanel");

        // Inicialmente mostrar el panel de dificultad
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "difficultyPanel");

        setVisible(true);
    }

    // Implementación del método de la interfaz PanelReemplazable
    @Override
    public boolean cambiarPanel(JPanel panel) {
        // Verificamos si el panel es uno de los nuestros conocidos
        if (panel instanceof DifficultyPanel || panel instanceof GamePanel) {
            // Si el panel no está en el contenedor principal, lo agregamos
            if (!containsPanel(panel)) {
                // Usamos un identificador único para cada panel
                String id = panel.getClass().getSimpleName();
                mainPanel.add(panel, id);
            }

            // Mostramos el panel
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            // Necesitamos obtener el nombre del constraint que se usó para agregar el panel
            String constraint = getPanelConstraint(panel);
            if (constraint != null) {
                cardLayout.show(mainPanel, constraint);
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para verificar si un panel ya está en el contenedor
    private boolean containsPanel(JPanel panel) {
        Component[] components = mainPanel.getComponents();
        for (Component comp : components) {
            if (comp == panel) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para obtener el constraint de un panel
    private String getPanelConstraint(JPanel panel) {
        // En este caso simplificado usamos el nombre de la clase como constraint
        return panel.getClass().getSimpleName();
    }

    // Iniciar juego con la dificultad seleccionada
    public void startGame(String difficulty) {
        // Generar un nuevo sudoku resuelto cada vez para usar backtracking
        sudokuSolucion = new SudokuResuelto();

        // Inicializar el sudoku con la dificultad elegida
        sudokuUsuario = new Sudoku();
        sudokuUsuario.rellenarDesdeSolucion(difficulty, sudokuSolucion);

        // Crear un nuevo panel de juego
        gamePanel = new GamePanel(this, sudokuUsuario, sudokuSolucion);

        // Cambiar al panel de juego - usamos el método de la interfaz
        cambiarPanel(gamePanel);
    }

    // Mostrar el panel de selección de número
    public void showNumberSelectionDialog(puntoMatriz cell) {
        lastSelectedCell = cell;
        NumberSelectionPanel numberPanel = new NumberSelectionPanel(this);

        JDialog dialog = new JDialog(this, "Selecciona un número", true);
        dialog.add(numberPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Procesar la selección de un número
    public void processNumberSelection(int number) {
        if (gamePanel != null) {
            gamePanel.processNumberSelection(lastSelectedCell, number);
        }
    }

    // Volver a mostrar el panel de dificultad
    public void showDifficultyPanel() {
        // Eliminamos el panel de juego actual del contenedor si existe
        if (gamePanel != null) {
            mainPanel.remove(gamePanel);
            gamePanel = null;
        }

        // Creamos un nuevo panel de dificultad para asegurar que es fresco
        difficultyPanel = new DifficultyPanel(this);
        mainPanel.add(difficultyPanel, "difficultyPanel");

        // Cambiamos al panel de dificultad
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "difficultyPanel");

        // Actualizamos la interfaz para reflejar los cambios
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI());
    }
}