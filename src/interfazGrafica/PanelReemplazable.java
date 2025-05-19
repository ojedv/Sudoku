package interfazGrafica;

import javax.swing.JPanel;


// Interfaz para los paneles que pueden ser reemplazados dentro de la aplicación Sudoku.

public interface PanelReemplazable {

    // Cambia el panel actual por otro.
    boolean cambiarPanel(JPanel panel);
}