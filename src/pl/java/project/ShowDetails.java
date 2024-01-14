package pl.java.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    public ShowDetails(Travel selectedTravel, JPanel mainWindow, TravelManage tManage) {
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
        fuelConsumption.setText("Paliwo: " + selectedTravel.fuelConsumption(5.7)+ "litry");
        fuelCost.setText("Koszt: " + selectedTravel.fuelCost("5.7",7.9));


        linkDoTrasyButton.addActionListener(e -> {
            GoogleMapsLinkGeneratorApp link = new GoogleMapsLinkGeneratorApp(selectedTravel.getFromPlace(),selectedTravel.getToPlace());
            link.getLink();
            Desktop desktop = Desktop.getDesktop();

            // Adres URL do otwarcia
            String link2 = link.getLink();

            URI uri = null;
            try {
                uri = new URI(link2);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            try {
                desktop.browse(uri);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
