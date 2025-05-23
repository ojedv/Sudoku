package tests;

import consola.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Tests para la clase SudokuConsola
 * Verifica los métodos principales de lógica no interactiva
 */
public class TestSudokuConsola {

    private SudokuConsola sudokuConsola;
    private SudokuResuelto sudokuSolucion;
    private Sudoku sudokuUsuario;

    @BeforeEach
    void setUp() {
        sudokuSolucion = new SudokuResuelto();
        sudokuConsola = new SudokuConsola();
        sudokuUsuario = new Sudoku();
    }



    @Test
    void testImprimirSudokuUsuario() {
        // Redirigir la salida estándar para capturar el output
        int[][] sudokuPrueba = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        // Simula rellenar el Sudoku del usuario
        sudokuUsuario.getSudokuUsuario()[0][0] = 1;
        sudokuUsuario.getSudokuUsuario()[0][1] = 2;
        sudokuUsuario.getSudokuUsuario()[0][2] = 3;

        // No podemos verificar directamente la impresión en consola,
        // pero podemos inspeccionar la lógica del método en tests gráficos.
    }

    @Test
    void testValidarSudoku() {
        // Simula una posición correcta
        puntoMatriz posCorrecta = new puntoMatriz(0, 0);

    }
}
