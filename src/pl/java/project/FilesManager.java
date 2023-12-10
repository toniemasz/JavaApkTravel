package pl.java.project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesManager {

    TravelManage travelManage = new TravelManage();

    public static void saveToFile(List<Travel> travelList, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Travel travel : travelList) {
                writer.println(travel.getTitle() + "," + travel.getKilometres() + "," +
                        travel.getFromPlace() + "," + travel.getToPlace() + "," + travel.getDuration());
            }
            System.out.println("Zapisano do pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Travel> loadFromFile(String fileName) {
        List<Travel> loadedTravels = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String title = parts[0].trim();
                    String kilometres = parts[1].trim();
                    String fromPlace = parts[2].trim();
                    String toPlace = parts[3].trim();
                    String date = parts[4].trim();

                    Travel travel = new Travel(title, kilometres, fromPlace, toPlace, date);
                    loadedTravels.add(travel);
                } else {
                    System.out.println("Nieprawidłowy format linii: " + line);
                }
            }
            System.out.println("Wczytano z pliku: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedTravels;
    }
}
