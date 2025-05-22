package consola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuResuelto implements ISudokuResuelto{
    private int[][] sudoku = new int[9][9];

    public SudokuResuelto() {
        rellenarBloquesDiagonales();
        generarSudokuResuelto();
    }

    private void rellenarBloquesDiagonales() {
        rellenarBloque(0, 0);
        rellenarBloque(3, 3);
        rellenarBloque(6, 6);
    }

    private void rellenarBloque(int startX, int startY) {
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) numeros.add(i);
        Collections.shuffle(numeros);

        int idx = 0;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                sudoku[i][j] = numeros.get(idx++);
            }
        }
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    private boolean generarSudokuResuelto() {
        return backtracking(0, 0);
    }

    private boolean backtracking(int fila, int columna) {
        if (fila == 9) return true;

        int siguienteFila = (columna == 8) ? fila + 1 : fila;
        int siguienteColumna = (columna + 1) % 9;

        if (sudoku[fila][columna] != 0) {
            return backtracking(siguienteFila, siguienteColumna);
        }

        List<Integer> numeros = generarNumerosAleatorios();
        for (int num : numeros) {
            if (esValido(fila, columna, num)) {
                sudoku[fila][columna] = num;
                if (backtracking(siguienteFila, siguienteColumna)) return true;
                sudoku[fila][columna] = 0;
            }
        }
        return false;
    }

    private List<Integer> generarNumerosAleatorios() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);
        return nums;
    }

    private boolean esValido(int fila, int columna, int valor) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[fila][i] == valor || sudoku[i][columna] == valor) return false;
        }

        int startX = (fila / 3) * 3;
        int startY = (columna / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (sudoku[i][j] == valor) return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] fila : sudoku) {
            for (int num : fila) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
