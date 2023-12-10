package pl.java.project;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainWindow {

    DistanceMatrixAPIExample googleMaps = new DistanceMatrixAPIExample();
    TravelManage tManage = new TravelManage();
    JPanel panel1;
    private JButton newTravelButton;
    private JButton seJebnijButton;
    private JButton removeTravelButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton readButton;
    private JList<String> list1;

    public MainWindow() {


        removeTravelButton.addActionListener(e -> {
            removeFromList();
        });

        seJebnijButton.addActionListener(e -> { //Jakiś przycisk ale wsm nie wiem co będzie robił taki zapasowy w razie czego to do kosza go
            JOptionPane.showInternalMessageDialog(null, "XD tak nudziło mi się ");
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Właśnie wyłączyłeś program. Gratulacje!");
            System.exit(0);

        });

        saveButton.addActionListener(e -> {
            FilesManager file = new FilesManager();
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
                        showDetailsDialog(selectedIndex);
                    }
                }
            }
        });

        // Użyj wyrażenia lambda dla ActionListenera przycisku newTravelButton
        newTravelButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(null, "Wpisz Tytuł");
            String fromPlace = JOptionPane.showInputDialog(null, "Wpisz skąd chcesz jechać");
            String toPlace = JOptionPane.showInputDialog(null, "Wpisz dokąd");
            String km = googleMaps.runExample(fromPlace,toPlace).getDistance();
            String duration = googleMaps.runExample(fromPlace,toPlace).getDuration();

            Travel travel = new Travel(title, km, fromPlace, toPlace, duration);
            System.out.println(travel);
            tManage.addTravel(travel);
            JOptionPane.showMessageDialog(null, "NOWE");// test
            System.out.println("Tytuły podróży: " + tManage.displayTravelList().getModel());
            list1.setModel(tManage.displayTravelList().getModel());
            list1.setVisible(true);
        });
    }

    private void showDetailsDialog(int selectedIndex) {
        ListModel<String> model = list1.getModel();

        if (selectedIndex >= 0 && selectedIndex < model.getSize()) {
            String selectedTravelTitle = model.getElementAt(selectedIndex);

            // Pobieramy obiekt Travel na podstawie tytułu
            Travel selectedTravel = findTravelByTitle(selectedTravelTitle, model);

            if (selectedTravel != null) {
                // Wyświetlamy szczegóły podróży
                String detailsMessage = selectedTravel.getTitle() + "\n"
                        + "Kilometry: " + selectedTravel.getKilometres() + "\n"
                        + "Skąd: " + selectedTravel.getFromPlace() + "\n"
                        + "Dokąd: " + selectedTravel.getToPlace() + "\n"
                        + "Czas: " + selectedTravel.getDuration();

                JOptionPane.showMessageDialog(null, detailsMessage, "Szczegóły podróży", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Błąd: Nie znaleziono podróży o tytule: " + selectedTravelTitle, "Błąd", JOptionPane.ERROR_MESSAGE);
            }
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
            list1.setModel(tManage.displayTravelList().getModel());
            list1.setVisible(true);
            JOptionPane.showMessageDialog(null, "Usunięto podróż");
        } else {
            JOptionPane.showMessageDialog(null, "Błąd: Zaznacz elementy do usunięcia", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }






}

