package pl.java.project;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DistanceMatrixAPIExample {

    String ApiKey;

    private static String getApiKey(){
        String apiKey = null;
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/pl/java/project/config.properties")) {
            prop.load(input);
            apiKey = prop.getProperty("api.key");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return apiKey;
    }

    public static void runExample(String origin,String destination) {
        try {
            // Tworzymy URL na podstawie zapytania do API
            String apiKey = getApiKey();


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
                String durationText = document.getElementsByTagName("text").item(0).getTextContent();
                String distanceText = document.getElementsByTagName("text").item(1).getTextContent();

                System.out.println("Origin: " + originAddress);
                System.out.println("Destination: " + destinationAddress);
                System.out.println("Duration: " + durationText);
                System.out.println("Distance: " + distanceText);
            } else {
                System.out.println("Error: " + status);
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        runExample("Poznań","Wrocław");
    }

}
