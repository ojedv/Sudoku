package interfazGrafica;

import javax.swing.*;
import java.awt.*;

public class NumberSelectionPanel extends JPanel {
    private SudokuGUI mainFrame;

    public NumberSelectionPanel(SudokuGUI mainFrame) {
        this.mainFrame = mainFrame;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new GridLayout(3, 3, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int n = 1; n <= 9; n++) {
            final int num = n;
            JButton numberButton = new JButton(String.valueOf(n));
            numberButton.setFont(new Font("Arial", Font.BOLD, 20));
            numberButton.setFocusPainted(false);

            numberButton.addActionListener(e -> {
                mainFrame.processNumberSelection(num);
                Window dialog = SwingUtilities.getWindowAncestor(numberButton);
                dialog.dispose();
            });
            numberButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    numberButton.setBackground(Color.LIGHT_GRAY);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    numberButton.setBackground(null);
                }
            });


            add(numberButton);
        }
    }
}