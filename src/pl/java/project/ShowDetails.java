package pl.java.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ShowDetails extends JDialog {
    public JPanel contentPane;
    private JButton buttonOK;
    private JLabel titleLabel;
    private JLabel kilometersLabel;
    private JLabel fromPlaceLabel;
    private JLabel toPlaceLabel;
    private JLabel durationLabel;
    private JButton linkDoTrasyButton;
    private JLabel fuelConsumption;
    private JLabel fuelCost;

    private Travel selectedTravel;
    private int selectedIndex;
    private TravelManage tManage;

    public void transformIcons() {
        transformLabelIcon(kilometersLabel);
        transformLabelIcon(fromPlaceLabel);
        transformLabelIcon(toPlaceLabel);
        transformLabelIcon(durationLabel);
        transformLabelIcon(fuelConsumption);
        transformLabelIcon(fuelCost);
    }

    private void transformLabelIcon(JLabel label) {
        // Pobierz ikonę z JLabel
        Icon labelIcon = label.getIcon();

        // Sprawdź, czy ikona jest obiektem ImageIcon
        if (labelIcon instanceof ImageIcon) {
            // Przekształć ikonę
            ImageIcon originalIcon = (ImageIcon) labelIcon;
            Image originalImage = originalIcon.getImage();

            // Tutaj możesz przekształcać obraz, na przykład zmieniając rozmiar
            Image transformedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            // Utwórz nową ikonę na podstawie przekształconego obrazu
            Icon transformedIcon = new ImageIcon(transformedImage);

            // Ustaw przekształconą ikonę z powrotem na JLabel
            label.setIcon(transformedIcon);
        } else {
            // Obsłuż przypadki, gdy ikona nie jest obiektem ImageIcon
            System.out.println("The icon is not an instance of ImageIcon.");
        }
    }

    public ShowDetails(Travel selectedTravel, JPanel mainWindow, TravelManage tManage) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("fuel.txt"));
        double firstValue = Double.parseDouble(reader.readLine());// fuelConsumtion
        // Wczytaj drugą wartość
        double secondValue = Double.parseDouble(reader.readLine());// fuelCost

        this.selectedTravel = selectedTravel;
        this.tManage = tManage;
        transformIcons();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        if (mainWindow != null) {
            Point mainWindowLocation = mainWindow.getLocationOnScreen();
            setLocation(mainWindowLocation.x+200, mainWindowLocation.y);
        } else {
            setLocationRelativeTo(null);
            // Ustawienie położenia na środku ekranu, jeśli brak referencji do MainWindow
        }


        titleLabel.setText("Tytuł: " + selectedTravel.getTitle());
        kilometersLabel.setText("Kilometry: " + selectedTravel.getKilometres());
        fromPlaceLabel.setText("Skąd: " + selectedTravel.getFromPlace());
        toPlaceLabel.setText("Dokąd: " + selectedTravel.getToPlace());
        durationLabel.setText("Czas: " + selectedTravel.getDuration());
        fuelConsumption.setText("Paliwo: " + selectedTravel.fuelConsumption(firstValue) + " litry");
        fuelCost.setText("Koszt: " + selectedTravel.fuelCost(selectedTravel.fuelConsumption(firstValue), secondValue));

        reader.close();

        linkDoTrasyButton.addActionListener(e -> {
                GoogleMapsLinkGeneratorApp link = new GoogleMapsLinkGeneratorApp(selectedTravel.getFromPlace(),selectedTravel.getToPlace());
            link.openLink();

        });
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    private void onOK() {
        dispose();
    }

}
