package pl.java.project;

import javax.swing.*;

public class MainWindow {
    TravelManage tManage = new TravelManage();
    JPanel panel1;
    private JButton newTravelButton;
    private JButton seJebnijButton;
    private JButton editTravelButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton readButton;
    private JList list1 = tManage.displayTravelList();
public MainWindow() {


    newTravelButton.addActionListener(e -> {
        //tutaj wpisać kod aby działało dodawanie nowej trasy
        JOptionPane.showMessageDialog(null,"NOWE");//test
    });

    editTravelButton.addActionListener(e -> {
        //tutaj dodać funkcjonalność która edytuje trasę
        JOptionPane.showMessageDialog(null,"EDYTUJ");//test
    });

    seJebnijButton.addActionListener(e -> { //Jakiś przycisk ale wsm nie wiem co będzie robił taki zapasowy w razie czego to do kosza go
       JOptionPane.showInternalMessageDialog(null,"XD tak nudziło mi się ");
    });

    exitButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(null,"Właśnie wyłączyłeś program. Gratulacje!");
        System.exit(0);

    });
}
}
