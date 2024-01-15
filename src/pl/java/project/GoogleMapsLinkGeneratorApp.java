package pl.java.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleMapsLinkGeneratorApp {

    private static String fromPlace;
    private static String toPlace;

    private String link;

    public GoogleMapsLinkGeneratorApp(String fromPlace, String toPlace) {
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        generateGoogleMapsLink();
    }

    private String generateGoogleMapsLink() {
        try {
            String encodedStartLocation = URLEncoder.encode(fromPlace, "UTF-8");
            String encodedEndLocation = URLEncoder.encode(toPlace, "UTF-8");

            return link ="https://www.google.com/maps/dir/?api=1&origin=" + encodedStartLocation +
                    "&destination=" + encodedEndLocation;
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
        }
    }

    public URI openLink() {
        Desktop desktop = Desktop.getDesktop();
        URI uri = null;

        try {
            uri = new URI(link);

        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        try {
            desktop.browse(uri);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return uri;
    }
}