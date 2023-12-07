package pl.java.project;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainWindow {
    TravelManage tManage = new TravelManage();
    JPanel panel1;
    private JButton newTravelButton;
    private JButton seJebnijButton;
    private JButton editTravelButton;
    private JButton exitButton;
    private JButton saveButton;
    private JButton readButton;
    private JList<String> list1;

    public MainWindow() {


        editTravelButton.addActionListener(e -> {
            //tutaj dodać funkcjonalność która edytuje trasę
            JOptionPane.showMessageDialog(null, "EDYTUJ");//test
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
                tManage.setTravelList(loadedTravels);
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
            int km = 100; // Tutaj będzie funkcja, która oblicza kilometry z Api Google, ale to potem
            String fromPlace = JOptionPane.showInputDialog(title, "Wpisz skąd chcesz jechać");
            String toPlace = JOptionPane.showInputDialog(fromPlace, "Wpisz dokąd");
            String date = JOptionPane.showInputDialog(toPlace, "Wpisz datę");

            Travel travel = new Travel(title, km, fromPlace, toPlace, date);
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
                String detailsMessage = "Tytuł: " + selectedTravel.getTitle() + "\n"
                        + "Kilometry: " + selectedTravel.getKilometres() + "\n"
                        + "Skąd: " + selectedTravel.getFromPlace() + "\n"
                        + "Dokąd: " + selectedTravel.getToPlace() + "\n"
                        + "Data: " + selectedTravel.getDate();

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






}

