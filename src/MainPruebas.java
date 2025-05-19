import consola.SudokuConsola;
import consola.SudokuResuelto;

public class MainPruebas {
    public static void main(String[] args) {
        SudokuConsola sudokuConsola = new SudokuConsola();
        sudokuConsola.iniciar("facil", new SudokuResuelto());
    }
}
