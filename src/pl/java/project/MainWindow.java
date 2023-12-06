package pl.java.project;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

    JPanel panel1;
    private JButton nowaTrasaButton;
    private JButton seJebnijButton;
    private JButton edytujTrasęButton;
    private JButton wyjdźButton;
    private JButton zapiszButton;
    private JButton wczytajButton;
    private JList list1;
public MainWindow() {

    list1.addComponentListener(new ComponentAdapter() {
    });
    list1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    });
}
}
