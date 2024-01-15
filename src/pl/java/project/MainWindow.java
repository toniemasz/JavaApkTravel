package pl.java.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class MainWindow {
    private JButton newTravelButton;
    private JButton editButton;
    private JButton removeTravelButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton readButton;
    private JList<String> list1;
    private JButton settingsButton;
    private ShowDetails detailsDialog;
    JPanel panel1;
    DistanceMatrixAPIExample googleMaps = new DistanceMatrixAPIExample();
    TravelManage tManage = new TravelManage();

    public MainWindow() {

        removeTravelButton.addActionListener(e -> {
            removeFromList();
        });

        editButton.addActionListener(e -> { //przycisk do edycji
            editFromList();
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Właśnie wyłączyłeś program. Gratulacje!");
            System.exit(0);

        });

        saveButton.addActionListener(e -> {
            FilesManager.saveToFile(tManage.travelList, "travels.txt");
        });

        readButton.addActionListener(e -> {
            List<Travel> loadedTravels = FilesManager.loadFromFile("travels.txt");

            if (loadedTravels != null) {
                tManage.addToTravelListAll(loadedTravels);
                list1.setModel(tManage.displayTravelList().getModel());
                list1.setVisible(true);
                JOptionPane.showMessageDialog(null, "Wczytano podróże z pliku.");


            } else {
                JOptionPane.showMessageDialog(null, "Błąd podczas wczytywania z pliku.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });


        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = list1.getSelectedIndex();
                    if (selectedIndex != -1) {
                        try {
                            showDetailsDialog(selectedIndex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        settingsButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                SettingsDialog dialog = new SettingsDialog();
                dialog.setVisible(true);
            });
        });

        newTravelButton.addActionListener(e -> {

            Travel travel = new NewTravelPane().getTravel();
            tManage.addTravel(travel);

           updateList();
        });
    }

    private void editFromList() {
        new EditTravelDialog(tManage, list1);
    }



    private void showDetailsDialog(int selectedIndex) throws IOException {
        ListModel<String> model = list1.getModel();

        if (selectedIndex >= 0 && selectedIndex < model.getSize()) {
            String selectedTravelTitle = model.getElementAt(selectedIndex);

            // Pobieramy obiekt Travel na podstawie tytułu
            Travel selectedTravel = findTravelByTitle(selectedTravelTitle, model);

                detailsDialog = new ShowDetails(selectedTravel, panel1,tManage);
                detailsDialog.pack();
                detailsDialog.setVisible(true);


        } else {
            JOptionPane.showMessageDialog(null, "Błąd: Nieprawidłowy indeks elementu", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Metoda pomocnicza do znalezienia obiektu Travel po tytule
    private Travel findTravelByTitle(String title, ListModel<String> model) {
        for (int i = 0; i < model.getSize(); i++) {
            Travel travel = tManage.getTravelList().get(i);
            if (title.equals(travel.getTitle())) {
                return travel;
            }
        }
        return null;
    }

    private void removeFromList() {
        // Tutaj dodaj logikę usuwania podróży z listy
        int selectedIndex = list1.getSelectedIndex();

        if (selectedIndex >= 0 && selectedIndex < tManage.getTravelList().size()) {
            // Usuwamy podróż z listy na podstawie indeksu
            tManage.removeTravel(selectedIndex);

            // Aktualizujemy wyświetlanie listy
            updateList();
            JOptionPane.showMessageDialog(null, "Usunięto podróż");
        } else {
            JOptionPane.showMessageDialog(null, "Błąd: Zaznacz elementy do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateList() {
        list1.setModel(tManage.displayTravelList().getModel());
        list1.setVisible(true);
    }


}


