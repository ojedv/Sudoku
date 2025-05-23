package tests;

import consola.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Sudoku
 */
public class TestSudoku {

    private Sudoku sudoku;
    private SudokuResuelto sudokuSolucion;

    @BeforeEach
    void setUp() {
        sudoku = new Sudoku();
        sudokuSolucion = new SudokuResuelto();
        sudokuSolucion.generarSudokuResuelto(); // Aseguramos que la solución está generada
    }

    @Test
    void testRellenarNumSudoku() {
        // Verificar que el sudoku inicia vacío
        int[][] grid = sudoku.getSudokuUsuario();
        assertEquals(0, grid[4][4], "Sudoku debe iniciar vacío");

        // Rellenar una posición
        puntoMatriz posicion = new puntoMatriz(4, 4);
        int valor = 7;
        sudoku.rellenarNumSudoku(posicion, valor);

        // Verificar que se rellenó correctamente
        grid = sudoku.getSudokuUsuario();
        assertEquals(valor, grid[4][4], "Debe rellenar correctamente la posición [4][4] con valor " + valor);
    }

    @Test
    void testValidarNumSudoku() {
        // Rellenar un número en una posición
        puntoMatriz posicion = new puntoMatriz(0, 0);
        sudoku.rellenarNumSudoku(posicion, 5);

        // Validar un número válido
        puntoMatriz nuevaPosicion = new puntoMatriz(0, 1);
        assertTrue(sudoku.validarNumSudoku(nuevaPosicion, 3), "El número debe ser válido");

        // Validar un número que ya está en la misma fila
        assertFalse(sudoku.validarNumSudoku(nuevaPosicion, 5), "El número no debe ser válido (ya está en la fila)");

        // Validar un número que ya está en el mismo bloque
        puntoMatriz posicionEnBloque = new puntoMatriz(1, 1);
        assertFalse(sudoku.validarNumSudoku(posicionEnBloque, 5), "El número no debe ser válido (ya está en el bloque)");
    }

    @Test
    void testRellenarDesdeSolucion() {
        // Test con dificultad fácil
        sudoku.rellenarDesdeSolucion("facil", sudokuSolucion);
        int[][] gridFacil = sudoku.getSudokuUsuario();

        int celdasLlenasFacil = contarCeldasLlenas(gridFacil);
        assertEquals(51, celdasLlenasFacil, "Dificultad fácil debe llenar exactamente 51 celdas");

        // Verificar que los números llenados coinciden con la solución
        verificarCoincidenciaConSolucion(gridFacil, sudokuSolucion.getSudoku());

        // Test con dificultad medio
        Sudoku sudokuMedio = new Sudoku();
        sudokuMedio.rellenarDesdeSolucion("medio", sudokuSolucion);
        int[][] gridMedio = sudokuMedio.getSudokuUsuario();

        int celdasLlenasMedio = contarCeldasLlenas(gridMedio);
        assertEquals(41, celdasLlenasMedio, "Dificultad medio debe llenar exactamente 41 celdas");

        // Test con dificultad difícil
        Sudoku sudokuDificil = new Sudoku();
        sudokuDificil.rellenarDesdeSolucion("dificil", sudokuSolucion);
        int[][] gridDificil = sudokuDificil.getSudokuUsuario();

        int celdasLlenasDificil = contarCeldasLlenas(gridDificil);
        assertEquals(31, celdasLlenasDificil, "Dificultad difícil debe llenar exactamente 31 celdas");
    }

    private int contarCeldasLlenas(int[][] grid) {
        int contador = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0) {
                    contador++;
                }
            }
        }
        return contador;
    }

    private void verificarCoincidenciaConSolucion(int[][] gridUsuario, int[][] solucion) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gridUsuario[i][j] != 0) {
                    assertEquals(solucion[i][j], gridUsuario[i][j],
                            "Número en posición [" + i + "][" + j + "] debe coincidir con la solución");
                }
            }
        }
    }
}
