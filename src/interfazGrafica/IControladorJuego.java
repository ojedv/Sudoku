package interfazGrafica;

import consola.puntoMatriz;

/**
 * Interfaz que define el comportamiento del controlador principal del juego.
 * Permite diferentes implementaciones de la lógica de control del juego.
 */
public interface IControladorJuego {

    /**
     * Inicia un nuevo juego con la dificultad especificada.
     */
    void startGame(String difficulty);

    /**
     * Muestra el diálogo de selección de números para una celda específica.
     */
    void showNumberSelectionDialog(puntoMatriz cell);

    /**
     * Procesa la selección de un número por parte del usuario.
     */
    void processNumberSelection(int number);

    /**
     * Muestra el panel de selección de dificultad.
     */
    void showDifficultyPanel();
}