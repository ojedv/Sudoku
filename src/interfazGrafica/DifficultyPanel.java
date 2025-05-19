package interfazGrafica;

import javax.swing.*;
import java.awt.*;

public class DifficultyPanel extends JPanel {
    private SudokuGUI mainFrame;

    public DifficultyPanel(SudokuGUI mainFrame) {
        this.mainFrame = mainFrame;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(SudokuGUI.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titleLabel = new JLabel("Bienvenido al Sudoku");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Selecciona la dificultad:");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Usar los valores exactos esperados por el método rellenarDesdeSolucion
        JButton easyButton = createDifficultyButton("Fácil", "facil");
        JButton mediumButton = createDifficultyButton("Medio", "medio");
        JButton hardButton = createDifficultyButton("Difícil", "dificil");

        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(subtitleLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(easyButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(mediumButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(hardButton);
        add(Box.createVerticalGlue());
    }

    private JButton createDifficultyButton(String buttonText, String difficulty) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);

        button.addActionListener(e -> {
            // Iniciar el juego con la dificultad seleccionada
            mainFrame.startGame(difficulty);
        });

        return button;
    }
}