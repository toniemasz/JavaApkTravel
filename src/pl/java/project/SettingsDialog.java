package pl.java.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SettingsDialog extends JDialog {
    private JTextField fuelConsumptionField;
    private JTextField fuelPriceField;

    public SettingsDialog() {
        initComponents();
        loadSettings();
    }

    private void initComponents() {
        setTitle("Ustawienia");
        setLayout(new GridLayout(3, 2));

        JLabel fuelConsumptionLabel = new JLabel("Spalanie na 100 km (litry):");
        JLabel fuelPriceLabel = new JLabel("Cena paliwa za 1 litr:");

        fuelConsumptionField = new JTextField();
        fuelPriceField = new JTextField();
        JButton saveButton = new JButton("Zapisz");


        fuelPriceField.setToolTipText("Wpisuj wartości: np 5.4");
        fuelConsumptionField.setToolTipText("Wpisuj wartości: np 5.4");



        saveButton.addActionListener(e -> {
            saveSettings();
        });

        add(fuelConsumptionLabel);
        add(fuelConsumptionField);
        add(fuelPriceLabel);
        add(fuelPriceField);
        add(saveButton);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void loadSettings() {
        try {
            Path path = Paths.get("fuel.txt");

            if (Files.exists(path)) {
                // Wczytaj spalanie i cenę paliwa z pliku
                String[] lines = Files.readAllLines(path).toArray(new String[0]);
                if (lines.length >= 2) {
                    fuelConsumptionField.setText(lines[0]);
                    fuelPriceField.setText(lines[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSettings() {
        try {
            double fuelConsumption = Double.parseDouble(fuelConsumptionField.getText());
            double fuelPrice = Double.parseDouble(fuelPriceField.getText());

            // Zapisz wartości do pliku
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("fuel.txt"))) {
                writer.write(String.valueOf(fuelConsumption));
                writer.newLine();
                writer.write(String.valueOf(fuelPrice));
            }
            dispose();
            JOptionPane.showMessageDialog(this, "Ustawienia zostały zapisane.", "Zapisano", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Wprowadź poprawne wartości.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

}
