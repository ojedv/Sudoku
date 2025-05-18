import java.util.Scanner;

public class SudokuConsola {
    private Sudoku sudokuUsuario;
    private SudokuResuelto sudokuSolucion;
    private Scanner scanner;

    public SudokuConsola() {
        this.sudokuUsuario = new Sudoku();
        this.sudokuSolucion = new SudokuResuelto();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar(String dificultad, SudokuResuelto sudokuSolucion) {
        this.sudokuSolucion = sudokuSolucion;
        sudokuUsuario.rellenarDesdeSolucion(dificultad, sudokuSolucion);

        System.out.println("Bienvenido al Sudoku. ¡Introduce números del 1 al 9!");
        boolean juegoActivo = true;

        while (juegoActivo) {
            System.out.println("\nSudoku actual:");
            imprimirSudokuUsuario();

            int fila = pedirNumero("Introduce la fila (0-8): ", 0, 8);
            int columna = pedirNumero("Introduce la columna (0-8): ", 0, 8);
            int valor = pedirNumero("Introduce un número del 1 al 9: ", 1, 9);

            puntoMatriz punto = new puntoMatriz(fila, columna);

            if (!sudokuUsuario.validarNumSudoku(punto, valor)) {
                System.out.println("❌ Número inválido: ya está en la fila, columna o bloque.");
                continue;
            }

            if (sudokuUsuario.comprobarNumeroCorrecto(punto, valor, sudokuSolucion)) {
                sudokuUsuario.rellenarNumSudoku(punto, valor);
                System.out.println("✅ ¡Correcto!");

                if (sudokuUsuarioCompleto()) {
                    System.out.println("🎉 ¡Felicidades! Has completado el Sudoku.");
                    imprimirSudokuUsuario();
                    juegoActivo = false;
                }

            } else {
                System.out.println("❌ Número incorrecto. Inténtalo de nuevo.");
            }
        }
    }

    private int pedirNumero(String mensaje, int min, int max) {
        int numero = -1;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(scanner.nextLine());
                if (numero >= min && numero <= max) {
                    valido = true;
                } else {
                    System.out.println("Número fuera de rango. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
            }
        }

        return numero;
    }

    private boolean sudokuUsuarioCompleto() {
        int[][] grid = sudokuUsuario.getSudokuUsuario();
        for (int[] fila : grid) {
            for (int celda : fila) {
                if (celda == 0) return false;
            }
        }
        return true;
    }

    private void imprimirSudokuUsuario() {
        int[][] grid = sudokuUsuario.getSudokuUsuario();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("+-------+-------+-------+");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print((grid[i][j] == 0 ? "." : grid[i][j]) + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
    }
}
