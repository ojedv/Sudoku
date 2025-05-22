package consola;

/**
 * Implementación concreta de las validaciones para el juego de Sudoku.
 * Contiene toda la lógica de validación separada del modelo de datos.
 */
public class ValidadorSudoku implements IValidadorSudoku {

    @Override
    public boolean comprobarNumeroCorrecto(puntoMatriz xy, int valor, ISudokuResuelto solucion) {
        int[][] sudokuCorrecto = solucion.getSudoku();
        return sudokuCorrecto[xy.getX()][xy.getY()] == valor;
    }

    @Override
    public boolean validarNumSudoku(puntoMatriz xy, int valor, int[][] sudoku) {
        int x = xy.getX();
        int y = xy.getY();

        // Validar fila, ignorando la posición actual
        for (int i = 0; i < 9; i++) {
            if (i != y && sudoku[x][i] == valor) return false;
        }

        // Validar columna, ignorando la posición actual
        for (int i = 0; i < 9; i++) {
            if (i != x && sudoku[i][y] == valor) return false;
        }

        // Validar bloque 3x3, ignorando la posición actual
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if ((i != x || j != y) && sudoku[i][j] == valor) return false;
            }
        }

        return true;
    }
}