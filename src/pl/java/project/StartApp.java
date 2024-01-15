package pl.java.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartApp {
    private JButton ZACZYNAMYButton;
    private JPanel Panel1;
    private JFrame frame;

    public StartApp() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeGUI();
            }
        });
    }

    private void initializeGUI() {
        frame = new JFrame("Aplikacja do planowania podróży");
        frame.setContentPane(Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ZACZYNAMYButton.addActionListener(e -> {
            frame.dispose();
            new Window();
        });
    }
}
