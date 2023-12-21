package pl.java.project;

import com.sun.tools.javac.Main;

import javax.swing.*;

//Uruchamia całą aplikację
public class Runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartApp();
            }
        });
    }
}