package pl.java.project;

import javax.swing.*;
import java.util.List;

public class Window {
    JFrame frame = new JFrame();
    public Window(List<Travel> travelList){
        frame.setVisible(true);
        frame.setContentPane(new MainWindow(travelList).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
    }
}
