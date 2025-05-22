
package consola;

/**
 * Interfaz que define las operaciones de validación para un juego de Sudoku.
 * Separa la lógica de validación permitiendo diferentes estrategias.
 */
public interface IValidadorSudoku {

    /**
     * Comprueba si un número es correcto según la solución dada.
     */
    boolean comprobarNumeroCorrecto(puntoMatriz xy, int valor, ISudokuResuelto solucion);

    /**
     * Valida si un número puede ser colocado en una posición específica
     * según las reglas del Sudoku (no repetido en fila, columna o bloque 3x3).
     */
    boolean validarNumSudoku(puntoMatriz xy, int valor, int[][] sudoku);
}