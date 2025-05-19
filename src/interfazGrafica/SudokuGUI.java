package interfazGrafica;

import consola.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SudokuGUI extends JFrame implements PanelReemplazable {
    private JPanel mainPanel; // Panel principal que contendrá los diferentes paneles
    private Sudoku sudokuUsuario;
    private SudokuResuelto sudokuSolucion;
    private puntoMatriz lastSelectedCell;

    // Mapa para guardar referencias a los paneles
    private Map<String, JPanel> paneles;

    // Identificadores de paneles
    public static final String PANEL_DIFICULTAD = "Difficulty";
    public static final String PANEL_JUEGO = "Game";

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

        // Inicializar el mapa de paneles
        paneles = new HashMap<>();

        // Inicializar juego - NO crear sudokuSolucion aquí para permitir
        // que se genere uno nuevo en cada partida
        sudokuUsuario = new Sudoku();

        // Crear panel de dificultad
        DifficultyPanel difficultyPanel = new DifficultyPanel(this);
        mainPanel.add(difficultyPanel, PANEL_DIFICULTAD);
        paneles.put(PANEL_DIFICULTAD, difficultyPanel);

        setVisible(true);
    }

    // Implementación del método de la interfaz PanelReemplazable
    @Override
    public boolean cambiarPanel(String nombrePanel) {
        if (paneles.containsKey(nombrePanel)) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, nombrePanel);
            return true;
        } else if (nombrePanel.equals(PANEL_JUEGO)) {
            // Si el panel de juego aún no existe pero se intenta cambiar a él,
            // asumimos que es porque estamos en el proceso de startGame()
            return true;
        }
        return false;
    }

    // Iniciar juego con la dificultad seleccionada
    public void startGame(String difficulty) {
        // Generar un nuevo sudoku resuelto cada vez para usar backtracking
        sudokuSolucion = new SudokuResuelto();

        // Inicializar el sudoku con la dificultad elegida
        sudokuUsuario = new Sudoku();
        sudokuUsuario.rellenarDesdeSolucion(difficulty, sudokuSolucion);

        // Verificar si ya existe un panel de juego
        GamePanel gamePanel;
        if (paneles.containsKey(PANEL_JUEGO)) {
            // Si ya existe, removerlo para que no guarde el estado anterior
            mainPanel.remove(paneles.get(PANEL_JUEGO));
            paneles.remove(PANEL_JUEGO);
        }

        // Crear un nuevo panel de juego
        gamePanel = new GamePanel(this, sudokuUsuario, sudokuSolucion);
        mainPanel.add(gamePanel, PANEL_JUEGO);
        paneles.put(PANEL_JUEGO, gamePanel);

        // Cambiar al panel de juego
        cambiarPanel(PANEL_JUEGO);
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
        if (paneles.containsKey(PANEL_JUEGO) && paneles.get(PANEL_JUEGO) instanceof GamePanel) {
            GamePanel gamePanel = (GamePanel) paneles.get(PANEL_JUEGO);
            gamePanel.processNumberSelection(lastSelectedCell, number);
        }
    }

    // Volver a mostrar el panel de dificultad
    public void showDifficultyPanel() {
        cambiarPanel(PANEL_DIFICULTAD);
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI());
    }
}