package interfazGrafica;

import consola.*;
import javax.swing.*;
import java.awt.*;

public class SudokuGUI extends JFrame {
    private JPanel mainPanel; // Panel principal que contendrá los diferentes paneles
    private Sudoku sudokuUsuario;
    private SudokuResuelto sudokuSolucion;
    private puntoMatriz lastSelectedCell;

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

        // Inicializar juego - NO crear sudokuSolucion aquí para permitir
        // que se genere uno nuevo en cada partida
        sudokuUsuario = new Sudoku();

        // Crear paneles
        DifficultyPanel difficultyPanel = new DifficultyPanel(this);
        mainPanel.add(difficultyPanel, "Difficulty");

        setVisible(true);
    }

    // Iniciar juego con la dificultad seleccionada
    public void startGame(String difficulty) {
        // Generar un nuevo sudoku resuelto cada vez para usar backtracking
        sudokuSolucion = new SudokuResuelto();

        // Inicializar el sudoku con la dificultad elegida
        sudokuUsuario = new Sudoku();
        sudokuUsuario.rellenarDesdeSolucion(difficulty, sudokuSolucion);

        // Crear y mostrar panel del juego
        // Primero eliminar el panel de juego anterior si existe
        Component[] components = mainPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof GamePanel) {
                mainPanel.remove(comp);
                break;
            }
        }

        GamePanel gamePanel = new GamePanel(this, sudokuUsuario, sudokuSolucion);
        mainPanel.add(gamePanel, "Game");

        showPanel("Game");
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
        GamePanel gamePanel = (GamePanel) getPanel("Game");
        gamePanel.processNumberSelection(lastSelectedCell, number);
    }

    // Volver a mostrar el panel de dificultad
    public void showDifficultyPanel() {
        showPanel("Difficulty");
    }

    // Cambiar de panel
    public void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }

    // Obtener un panel específico
    public JPanel getPanel(String panelName) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof JPanel) {
                if (((CardLayout) mainPanel.getLayout()).toString().contains(panelName)) {
                    return (JPanel) comp;
                }
            }
        }
        return null;
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI());
    }
}