package pl.java.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GoogleMapsLinkGeneratorApp {

    public String generateMapsLink;
    private static String fromPlace;
    private static String toPlace;

    private String link;

    public GoogleMapsLinkGeneratorApp(String fromPlace, String toPlace) {
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        generateGoogleMapsLink();
    }

    public String generateGoogleMapsLink() {
        try {
            String encodedStartLocation = URLEncoder.encode(fromPlace, "UTF-8");
            String encodedEndLocation = URLEncoder.encode(toPlace, "UTF-8");

            return this.link ="https://www.google.com/maps/dir/?api=1&origin=" + encodedStartLocation +
                    "&destination=" + encodedEndLocation;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLink() {
        return link;
    }
}