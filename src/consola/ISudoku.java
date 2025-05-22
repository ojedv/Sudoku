package consola;

/**
 * Interfaz que define las operaciones básicas de un juego de Sudoku.
 * Permite diferentes implementaciones del juego manteniendo un contrato común.
 */
public interface ISudoku {

    /**
     * Rellena una celda del sudoku con un valor específico.
     */
    void rellenarNumSudoku(puntoMatriz xy, int valor);

    /**
     * Valida si un número puede ser colocado en una posición específica
     * según las reglas del Sudoku.
     */
    boolean validarNumSudoku(puntoMatriz xy, int valor);

    /**
     * Obtiene el estado actual del sudoku del usuario.
     */
    int[][] getSudokuUsuario();

    /**
     * Rellena el sudoku desde una solución existente según la dificultad.
     */
    void rellenarDesdeSolucion(String dificultad, ISudokuResuelto solucion);

}