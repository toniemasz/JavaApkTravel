package pl.java.project;

import javax.swing.*;

public class Window {
    JFrame frame = new JFrame();
    public Window(){
        frame.setVisible(true);
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
    }
}
