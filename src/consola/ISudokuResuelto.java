package consola;

/**
 * Interfaz que define el comportamiento de un sudoku completamente resuelto.
 * Permite diferentes algoritmos de generación de sudokus válidos.
 */
public interface ISudokuResuelto {

    /**
     * Obtiene la matriz del sudoku completamente resuelto.
     */
    int[][] getSudoku();
}