package pl.java.project;

import javax.swing.*;
import java.awt.*;

public class NewTravelPane extends JPanel {

    private TravelManage tManage;
    private JTextField titleField;
    private JTextField fromPlaceField;
    private JTextField toPlaceField;
    private JList<String> list1;

    public NewTravelPane(JList<String> list1, TravelManage tManage) {
        this.tManage = tManage;
        this.list1 = list1;

        titleField = new JTextField(20);
        fromPlaceField = new JTextField(20);
        toPlaceField = new JTextField(20);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createLabelAndFieldPanel("Tytuł:", titleField));
        add(createLabelAndFieldPanel("Skąd:", fromPlaceField));
        add(createLabelAndFieldPanel("Dokąd:", toPlaceField));

        titleField.setFont(new Font(titleField.getFont().getName(), Font.PLAIN, 16));
        fromPlaceField.setFont(new Font(fromPlaceField.getFont().getName(), Font.PLAIN, 16));
        toPlaceField.setFont(new Font(toPlaceField.getFont().getName(), Font.PLAIN, 16));

        JButton addButton = new JButton("Dodaj trasę");
        addButton.addActionListener(e -> {
            addNewTravel();
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        add(addButton);

        list1.setModel(tManage.displayTravelList().getModel());
        list1.setVisible(true);
        setVisible(true);
    }

    private void addNewTravel() {
        String title = titleField.getText();
        String fromPlace = fromPlaceField.getText();
        String toPlace = toPlaceField.getText();

        if (title == null || title.trim().isEmpty()) {
            title = "Untitled";
        }

        DistanceMatrixAPIExample googleMaps = new DistanceMatrixAPIExample();
        DistanceMatrixAPIExample.DistanceDurationResult apiResult = googleMaps.generateApiResponseOriginDestination(fromPlace, toPlace);

        if (apiResult != null) {
            double km = apiResult.getDistance();
            String duration = apiResult.getDuration();
            JOptionPane.showMessageDialog(null, "Nowa trasa z " + fromPlace + " do " + toPlace + " została dodana poprawnie");

            Travel newTravel = new Travel(title, km, fromPlace, toPlace, duration);
            tManage.addTravel(newTravel);

            // Aktualizuj listę
            updateList();
        } else {
            JOptionPane.showMessageDialog(null, "Błąd podczas pobierania danych z API.");
        }
    }

    private JPanel createLabelAndFieldPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText);
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private void updateList() {
        list1.setModel(tManage.displayTravelList().getModel());
        list1.setVisible(true);
    }
}

