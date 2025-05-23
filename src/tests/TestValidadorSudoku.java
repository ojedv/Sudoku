package tests;

import consola.ValidadorSudoku;
import consola.puntoMatriz;
import consola.ISudokuResuelto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase ValidadorSudoku.
 */
public class TestValidadorSudoku {

    private ValidadorSudoku validador;
    private ISudokuResuelto sudokuResueltoMock;

    @BeforeEach
    void setUp() {
        validador = new ValidadorSudoku();

        // Crear un mock de SudokuResuelto para pruebas simples
        sudokuResueltoMock = new ISudokuResuelto() {
            @Override
            public int[][] getSudoku() {
                return new int[][]{
                        {5, 3, 4, 6, 7, 8, 9, 1, 2},
                        {6, 7, 2, 1, 9, 5, 3, 4, 8},
                        {1, 9, 8, 3, 4, 2, 5, 6, 7},
                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
                        {7, 1, 3, 9, 2, 4, 8, 5, 6},
                        {9, 6, 1, 5, 3, 7, 2, 8, 4},
                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
                        {3, 4, 5, 2, 8, 6, 1, 7, 9}
                };
            }
        };
    }

    /**
     * Test para comprobar que un número es correcto según la solución.
     */
    @Test
    void testComprobarNumeroCorrecto() {
        puntoMatriz punto = new puntoMatriz(0, 0); // Celda (0, 0)
        int valorCorrecto = 5;

        assertTrue(validador.comprobarNumeroCorrecto(punto, valorCorrecto, sudokuResueltoMock),
                "El número debería ser correcto según la solución");

        int valorIncorrecto = 9;

        assertFalse(validador.comprobarNumeroCorrecto(punto, valorIncorrecto, sudokuResueltoMock),
                "El número no debería ser correcto según la solución");
    }

    /**
     * Test para validar que un número no está repetido en fila, columna ni bloque.
     */
    @Test
    void testValidarNumSudoku() {
        int[][] sudokuUsuario = {
                {0, 0, 4, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        // Validar celda vacía
        puntoMatriz punto = new puntoMatriz(0, 0);
        assertTrue(validador.validarNumSudoku(punto, 5, sudokuUsuario),
                "El número debería ser válido en una celda vacía");

        // Introducir un valor duplicado en la fila
        sudokuUsuario[0][1] = 5;
        assertFalse(validador.validarNumSudoku(punto, 5, sudokuUsuario),
                "El número no debería ser válido si ya existe en la fila");

        // Introducir un valor duplicado en la columna
        sudokuUsuario[1][0] = 5;
        assertFalse(validador.validarNumSudoku(punto, 5, sudokuUsuario),
                "El número no debería ser válido si ya existe en la columna");

        // Introducir un valor duplicado en el bloque
        sudokuUsuario[1][1] = 5;
        assertFalse(validador.validarNumSudoku(punto, 5, sudokuUsuario),
                "El número no debería ser válido si ya existe en el bloque 3x3");
    }

    /**
     * Test inválido: Validar un número fuera de rango.
     */
    @Test
    void testValidarNumeroFueraDeRango() {
        int[][] sudokuUsuario = new int[9][9];
        puntoMatriz punto = new puntoMatriz(0, 0);

        // Número menor a 1
        assertFalse(validador.validarNumSudoku(punto, 0, sudokuUsuario),
                "El número 0 no debería ser válido en un Sudoku");

        // Número mayor a 9
        assertFalse(validador.validarNumSudoku(punto, 10, sudokuUsuario),
                "El número 10 no debería ser válido en un Sudoku");
    }
}
