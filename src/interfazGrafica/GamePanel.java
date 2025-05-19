package interfazGrafica;

import consola.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JPanel {
    private SudokuGUI mainFrame;
    private Sudoku sudokuUsuario;
    private SudokuResuelto sudokuSolucion;
    private JButton[][] sudokuButtons;

    public GamePanel(SudokuGUI mainFrame, Sudoku sudokuUsuario, SudokuResuelto sudokuSolucion) {
        this.mainFrame = mainFrame;
        this.sudokuUsuario = sudokuUsuario;
        this.sudokuSolucion = sudokuSolucion;
        this.sudokuButtons = new JButton[9][9];
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(SudokuGUI.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel para el tablero
        JPanel boardPanel = new JPanel(new GridLayout(9, 9, 1, 1));
        boardPanel.setBackground(SudokuGUI.GRID_COLOR);

        // Crear botones para el tablero
        int[][] initialBoard = sudokuUsuario.getSudokuUsuario();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuButtons[i][j] = new JButton();
                final int row = i;
                final int col = j;

                sudokuButtons[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                sudokuButtons[i][j].setFocusPainted(false);
                sudokuButtons[i][j].setBackground(Color.WHITE);

                // Si ya tiene un número inicial (de la dificultad), mostrarlo y deshabilitarlo
                if (initialBoard[i][j] != 0) {
                    sudokuButtons[i][j].setText(String.valueOf(initialBoard[i][j]));
                    sudokuButtons[i][j].setForeground(SudokuGUI.FIXED_NUMBER_COLOR);
                    sudokuButtons[i][j].setEnabled(false);
                } else {
                    sudokuButtons[i][j].addActionListener(e -> {
                        mainFrame.showNumberSelectionDialog(new puntoMatriz(row, col));
                    });
                }

                // Añadir bordes más gruesos para los bloques 3x3
                Border border = BorderFactory.createMatteBorder(
                        (i % 3 == 0) ? 2 : 1,
                        (j % 3 == 0) ? 2 : 1,
                        1, 1, SudokuGUI.GRID_COLOR);
                sudokuButtons[i][j].setBorder(border);

                boardPanel.add(sudokuButtons[i][j]);
            }
        }

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(SudokuGUI.BACKGROUND_COLOR);

        // Botón para nueva partida
        JButton newGameButton = new JButton("Nueva Partida");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 16));
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(e -> mainFrame.showDifficultyPanel());

        // Botón para ver solución
        JButton solutionButton = new JButton("Ver Solución");
        solutionButton.setFont(new Font("Arial", Font.PLAIN, 16));
        solutionButton.setFocusPainted(false);
        solutionButton.addActionListener(e -> showSolution());

        buttonPanel.add(newGameButton);
        buttonPanel.add(solutionButton);

        add(boardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Mostrar la solución completa
    private void showSolution() {
        int[][] solutionGrid = sudokuSolucion.getSudoku();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Si la celda no estaba ya completa, mostrar la solución
                if (sudokuButtons[i][j].isEnabled()) {
                    sudokuButtons[i][j].setText(String.valueOf(solutionGrid[i][j]));
                    sudokuButtons[i][j].setForeground(Color.RED);
                    sudokuButtons[i][j].setEnabled(false);
                }
            }
        }

        // Actualizar también el modelo de datos
        sudokuUsuario = new Sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuUsuario.rellenarNumSudoku(new puntoMatriz(i, j), solutionGrid[i][j]);
            }
        }
    }

    // Procesar la selección de un número
    public void processNumberSelection(puntoMatriz cell, int number) {
        int row = cell.getX();
        int col = cell.getY();

        // Validar que el número no esté repetido en fila, columna o bloque
        if (!sudokuUsuario.validarNumSudoku(cell, number)) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Número inválido: ya está en la fila, columna o bloque.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el número es correcto según la solución
        if (sudokuUsuario.comprobarNumeroCorrecto(cell, number, sudokuSolucion)) {
            // Actualizar el modelo de datos y la interfaz
            sudokuUsuario.rellenarNumSudoku(cell, number);
            sudokuButtons[row][col].setText(String.valueOf(number));
            sudokuButtons[row][col].setForeground(SudokuGUI.USER_NUMBER_COLOR);

            // Verificar si el juego está completo
            if (isSudokuComplete()) {
                JOptionPane.showMessageDialog(mainFrame,
                        "¡Felicidades! Has completado el Sudoku.",
                        "Victoria", JOptionPane.INFORMATION_MESSAGE);
                mainFrame.showDifficultyPanel();
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame,
                    "Número incorrecto. Inténtalo de nuevo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Verificar si el Sudoku está completo
    private boolean isSudokuComplete() {
        int[][] grid = sudokuUsuario.getSudokuUsuario();
        for (int[] fila : grid) {
            for (int celda : fila) {
                if (celda == 0) return false;
            }
        }
        return true;
    }
}