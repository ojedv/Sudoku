package consola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sudoku implements ISudoku{
    private int[][] sudoku = new int[9][9];
    private IValidadorSudoku validador;


    public Sudoku() {
        this.validador = new ValidadorSudoku();
        // Inicializar todo a 0 (vacío)
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
            }
        }
    }

    public void rellenarNumSudoku(puntoMatriz xy, int valor) {
        sudoku[xy.getX()][xy.getY()] = valor;
    }

    // Método para comprobar si el número es correcto según la solución dada
    public boolean comprobarNumeroCorrecto(puntoMatriz xy, int valor, ISudokuResuelto solucion) {
        return validador.comprobarNumeroCorrecto(xy, valor, solucion);
    }

    // Devuelve la matriz con el estado actual del sudoku del usuario
    public int[][] getSudokuUsuario() {
        return sudoku;
    }



    // Validar que el valor no esté repetido en fila, columna ni bloque 3x3
    public boolean validarNumSudoku(puntoMatriz xy, int valor) {
        return validador.validarNumSudoku(xy, valor, this.sudoku);
    }

    // Rellena un número de celdas según dificultad con números de la solución
    @Override
    public void rellenarDesdeSolucion(String dificultad, ISudokuResuelto solucion) {
        int celdasARellenar;

        // Normalizar la dificultad: quitar acentos y pasar a minúsculas
        String dificultadNormalizada = dificultad.toLowerCase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u");

        switch (dificultadNormalizada) {
            case "facil":
                celdasARellenar = 51;
                break;
            case "medio":
                celdasARellenar = 41;
                break;
            case "dificil":
                celdasARellenar = 31;
                break;
            default:
                System.out.println("Dificultad no válida: '" + dificultad + "'. Se usará dificultad media.");
                celdasARellenar = 41;
                break;
        }

        int[][] sudokuSolucion = solucion.getSudoku();
        List<puntoMatriz> posiciones = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                posiciones.add(new puntoMatriz(i, j));
            }
        }

        Collections.shuffle(posiciones);

        for (int i = 0; i < celdasARellenar; i++) {
            puntoMatriz p = posiciones.get(i);
            sudoku[p.getX()][p.getY()] = sudokuSolucion[p.getX()][p.getY()];
        }
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