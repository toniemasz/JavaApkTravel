package pl.java.project;

import javax.swing.*;
import java.util.List;

public class StartApp {
    private JButton ZACZYNAMYButton;
    private JPanel Panel1;
    private JFrame frame;

    public StartApp(List<Travel> travelList) {
        SwingUtilities.invokeLater(() -> initializeGUI(travelList));
    }

    private void initializeGUI(List<Travel> travelList) {
        frame = new JFrame("Aplikacja do planowania podróży");
        frame.setContentPane(new MainWindow(travelList).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ZACZYNAMYButton.addActionListener(e -> {
            frame.dispose();
            new Window(travelList);
        });
    }
}
