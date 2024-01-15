package pl.java.project;

import javax.swing.*;

public class NewTravelPane extends JPanel{
    private JTextField titleField;
    private JTextField fromPlaceField;
    private JTextField toPlaceField;

    public NewTravelPane() {
        titleField = new JTextField(20);
        fromPlaceField = new JTextField(20);
        toPlaceField = new JTextField(20);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Tytuł:"));
        add(titleField);
        add(new JLabel("Skąd chcesz jechać:"));
        add(fromPlaceField);
        add(new JLabel("Dokąd:"));
        add(toPlaceField);
    }

    public Travel getTravel() {
        String title = titleField.getText();
        String fromPlace = fromPlaceField.getText();
        String toPlace = toPlaceField.getText();

        if (title == null || title.trim().isEmpty()) {
            title = "Untitled";
        }

        DistanceMatrixAPIExample googleMaps = new DistanceMatrixAPIExample();
        DistanceMatrixAPIExample.DistanceDurationResult apiResult = googleMaps.generateApiResponseOriginDestination(fromPlace, toPlace);
        double km = apiResult.getDistance();
        String duration = apiResult.getDuration();
        JOptionPane.showMessageDialog(null, "Nowa trasa z " + fromPlace + " do " + toPlace + " została dodana poprawnie");// test

        return new Travel(title, km, fromPlace, toPlace, duration);
    }
}
