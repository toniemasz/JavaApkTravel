package pl.java.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTravelDialog extends JDialog {
    private TravelManage tManage;
    private JList<String> list1;

    public EditTravelDialog(TravelManage tManage, JList<String> list1) {
        super();
        this.tManage = tManage;
        this.list1 = list1;

        initUI();
    }

    private void initUI() {
        int selectedIndex = list1.getSelectedIndex();

        if (selectedIndex >= 0 && selectedIndex < tManage.getTravelList().size()) {
            Travel selectedTravel = tManage.getTravelList().get(selectedIndex);

            // Zapisz pierwotne wartości FromPlace i ToPlace
            String originalFromPlace = selectedTravel.getFromPlace();
            String originalToPlace = selectedTravel.getToPlace();

            // Utwórz okno dialogowe z polami do edycji
            JPanel editPanel = new JPanel(new GridLayout(4, 2));
            JTextField titleField = new JTextField(selectedTravel.getTitle());
            JTextField fromPlaceField = new JTextField(originalFromPlace);
            JTextField toPlaceField = new JTextField(originalToPlace);

            editPanel.add(new JLabel("Title:"));
            editPanel.add(titleField);
            editPanel.add(new JLabel("From Place:"));
            editPanel.add(fromPlaceField);
            editPanel.add(new JLabel("To Place:"));
            editPanel.add(toPlaceField);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleOkButton(selectedIndex, originalFromPlace, originalToPlace, fromPlaceField.getText(), toPlaceField.getText(), titleField.getText());
                }
            });

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            editPanel.add(okButton);
            editPanel.add(cancelButton);

            setLayout(new BorderLayout());
            add(editPanel, BorderLayout.CENTER);

            pack();
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Błąd: Zaznacz podróż do edycji", "Błąd", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void handleOkButton(int selectedIndex, String originalFromPlace, String originalToPlace, String newFromPlace, String newToPlace, String newTitle) {
        if (!originalFromPlace.equals(newFromPlace) || !originalToPlace.equals(newToPlace)) {
            DistanceMatrixAPIExample googleMaps = new DistanceMatrixAPIExample();
            DistanceMatrixAPIExample.DistanceDurationResult apiResult = googleMaps.generateApiResponseOriginDestination(newFromPlace, newToPlace);
            double km = apiResult.getDistance();
            String duration = apiResult.getDuration();

            tManage.editTravel(selectedIndex, newFromPlace, newToPlace, duration, km, newTitle);
            updateList();
            JOptionPane.showMessageDialog(null, "Podróż edytowana pomyślnie");
        } else {
            tManage.editTravel(selectedIndex, newFromPlace, newToPlace, tManage.getTravelList().get(selectedIndex).getDuration(), tManage.getTravelList().get(selectedIndex).getKilometres(), newTitle);
            updateList();
            JOptionPane.showMessageDialog(null, "Podróż edytowana pomyślnie");
        }

        dispose();
    }

    private void updateList() {
        list1.setModel(tManage.displayTravelList().getModel());
        list1.setVisible(true);
    }
}
