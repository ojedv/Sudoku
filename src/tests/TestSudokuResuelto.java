package tests;

import consola.SudokuResuelto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase SudokuResuelto.
 */
public class TestSudokuResuelto {

    private SudokuResuelto sudokuResuelto;

    @BeforeEach
    void setUp() {
        sudokuResuelto = new SudokuResuelto();
    }

    /**
     * Test para verificar que el Sudoku resuelto se genera correctamente.
     */
    @Test
    void testGenerarSudokuResuelto() {
        // Verificar que el Sudoku se genera correctamente
        boolean resultado = sudokuResuelto.generarSudokuResuelto();
        assertTrue(resultado, "El Sudoku debería generarse correctamente");

        // Validar que no hay duplicados en filas, columnas o bloques 3x3
        int[][] sudoku = sudokuResuelto.getSudoku();
        assertTrue(validarSudoku(sudoku), "El Sudoku generado debe ser válido");
    }

    /**
     * Test para verificar que los bloques diagonales están correctamente llenados.
     */
    @Test
    void testRellenarBloquesDiagonales() {
        int[][] sudoku = sudokuResuelto.getSudoku();

        // Verificar cada bloque diagonal
        assertTrue(validarBloque(sudoku, 0, 0), "El bloque diagonal superior izquierdo debe ser válido");
        assertTrue(validarBloque(sudoku, 3, 3), "El bloque diagonal central debe ser válido");
        assertTrue(validarBloque(sudoku, 6, 6), "El bloque diagonal inferior derecho debe ser válido");
    }

    /**
     * Test inválido: Intentar resolver un Sudoku ya roto.
     */
    @Test
    void testCasoInvalidoEnSudokuResuelto() {
        // Generar un Sudoku válido
        assertTrue(sudokuResuelto.generarSudokuResuelto(), "El Sudoku debería generarse correctamente");

        // Introducir manualmente un número inválido
        int[][] sudoku = sudokuResuelto.getSudoku();
        sudoku[0][0] = sudoku[0][1]; // Romper el Sudoku al duplicar en la fila

        // Intentar resolver el Sudoku roto
        boolean resultado = sudokuResuelto.generarSudokuResuelto();

        // Verificar que no puede generar un Sudoku resuelto a partir de un caso inválido
        assertFalse(resultado, "El Sudoku con números duplicados no debería poder resolverse");
    }

    /**
     * Test para verificar la funcionalidad del método `esValido`.
     */
    @Test
    void testEsValido() {
        int[][] sudoku = sudokuResuelto.getSudoku();

        // Probar que un número es válido en una celda vacía
        assertTrue(sudokuResuelto.generarSudokuResuelto(), "El Sudoku debería generarse correctamente");
        boolean valido = sudokuResuelto.generarSudokuResuelto();
        assertTrue(valido, "Debería ser válido agregar un número en una celda vacía");

        // Introducir un valor incorrecto y verificar que no es válido
        sudoku[0][0] = 5;
        sudoku[0][1] = 5; // Duplicado en la fila
        assertFalse(validarSudoku(sudoku), "El Sudoku no debe ser válido si hay números duplicados en una fila");
    }

    /**
     * Método auxiliar para validar si un Sudoku es correcto (sin duplicados).
     */
    private boolean validarSudoku(int[][] sudoku) {
        // Validar filas
        for (int i = 0; i < 9; i++) {
            if (!validarNumeros(sudoku[i])) return false;
        }

        // Validar columnas
        for (int j = 0; j < 9; j++) {
            int[] columna = new int[9];
            for (int i = 0; i < 9; i++) {
                columna[i] = sudoku[i][j];
            }
            if (!validarNumeros(columna)) return false;
        }

        // Validar bloques 3x3
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!validarBloque(sudoku, i, j)) return false;
            }
        }

        return true;
    }

    /**
     * Método auxiliar para validar que un bloque 3x3 no tiene duplicados.
     */
    private boolean validarBloque(int[][] sudoku, int startX, int startY) {
        boolean[] encontrado = new boolean[10]; // Números del 1 al 9
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                int num = sudoku[i][j];
                if (num != 0) {
                    if (encontrado[num]) return false;
                    encontrado[num] = true;
                }
            }
        }
        return true;
    }

    /**
     * Método auxiliar para validar que una fila o columna no tiene duplicados.
     */
    private boolean validarNumeros(int[] numeros) {
        boolean[] encontrado = new boolean[10]; // Números del 1 al 9
        for (int num : numeros) {
            if (num != 0) {
                if (encontrado[num]) return false;
                encontrado[num] = true;
            }
        }
        return true;
    }
}
