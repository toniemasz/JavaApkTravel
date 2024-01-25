package pl.java.project;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

// Uruchamia całą aplikację
public class Runner {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingWorker<Void, Void> worker = new InitializationWorker();
            worker.execute();
        });
    }

    private static class InitializationWorker extends SwingWorker<Void, Void> {

        private List<Travel> loadedTravels;

        @Override
        protected Void doInBackground() throws Exception {
            // Tutaj umieść asynchroniczne zadania inicjalizacyjne

            // Na przykład wczytanie listy podróży z pliku
            loadedTravels = FilesManager.loadFromFile("travels.txt");

            return null;
        }

        @Override
        protected void done() {
            // Zadania po zakończeniu inicjalizacji, np. uruchomienie głównego okna
            if (loadedTravels != null) {
                new StartApp(loadedTravels);
            }
        }
    }
}

