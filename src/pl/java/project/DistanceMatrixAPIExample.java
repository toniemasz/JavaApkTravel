package pl.java.project;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DistanceMatrixAPIExample {



    String ApiKey;

    private static String getApiKey() {
        String apiKey = null;
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/config.properties")) {
            prop.load(input);
            apiKey = prop.getProperty("api.key");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return apiKey;
    }

    public static class DistanceDurationResult {
        private double distance;
        private String duration;

        public DistanceDurationResult(double distance, String duration) {
            this.distance = distance;
            this.duration = duration;
        }

        public double getDistance() {
            return distance;
        }

        public String getDuration() {
            return duration;
        }
    }

    public static DistanceDurationResult runExample(String origin, String destination) {
        double distance = 0;
        String distanceText = null;
        String duration = null;
        try {
            // Tworzymy URL na podstawie zapytania do API
            String apiKey = getApiKey();

            distanceText = "";
            duration = "";

            // Kodujemy adresy URL
            String encodedOrigin = URLEncoder.encode(origin, "UTF-8");
            String encodedDestination = URLEncoder.encode(destination, "UTF-8");

            String urlString = "https://maps.googleapis.com/maps/api/distancematrix/xml"
                    + "?origins=" + encodedOrigin
                    + "&destinations=" + encodedDestination
                    + "&units=metric"
                    + "&key=" + apiKey;

            URL url = new URL(urlString);

            // Ustawiamy połączenie HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Odczytujemy odpowiedź
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println(response);

            // Parsujemy odpowiedź XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(url.openStream());

            // Pobieramy potrzebne informacje z XML
            String status = document.getElementsByTagName("status").item(0).getTextContent();
            if ("OK".equals(status)) {
                String originAddress = document.getElementsByTagName("origin_address").item(0).getTextContent();
                String destinationAddress = document.getElementsByTagName("destination_address").item(0).getTextContent();
                distanceText = document.getElementsByTagName("text").item(1).getTextContent();
                duration = document.getElementsByTagName("text").item(0).getTextContent();
                System.out.println(duration);
                System.out.println(distanceText);
                distanceText = extractNumber(distanceText);
                distance = Double.parseDouble(distanceText);


                System.out.println("Origin: " + originAddress);
                System.out.println("Destination: " + destinationAddress);
                System.out.println("Duration: " + duration);
                System.out.println("Distance: " + distanceText);
            } else {
                System.out.println("Error: " + status);
                JOptionPane.showMessageDialog(null,"brak połączenia z API wpisz api_key w odpowiedni plik","Błąd",JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            JOptionPane.showMessageDialog(null,"Nie znaleziono trasy","Błąd",JOptionPane.ERROR_MESSAGE);

        }
        return new DistanceDurationResult(distance, duration);
    }

    private static String extractNumber(String text) {
        // Usunięcie niechcianych znaków (poza cyframi i kropką)
        String cleanedText = text.replaceAll("[^\\d.]", "");

        // W przypadku, gdy mamy kropkę, oznaczającą dziesiętne miejsce, pozostawiamy tylko jedną
        if (cleanedText.contains(".")) {
            String[] parts = cleanedText.split("\\.");
            if (parts.length > 1) {
                cleanedText = parts[0] + "." + parts[1];
            }
        }

        return cleanedText;
    }
}
