package pl.java.project;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TravelManage {

    List<Travel> travelList = new ArrayList<>();

    public void addTravel(Travel travel){
        travelList.add(travel);
    }

    public void removeTravel(int travel){
        travelList.remove(travel);
    }

    public void editTravel(int number, String fromPlace, String toPlace, String duraction, double km, String title){
        if (number >= 0 && number < travelList.size()) {
            Travel travel = travelList.get(number);
            travel.setFromPlace(fromPlace);
            travel.setToPlace(toPlace);
            travel.setDuration(duraction);
            travel.setKilometres(km);
            travel.setTitle(title);
        } else {
            // Obsługa błędu indeksu podróży
            JOptionPane.showMessageDialog(null, "Podróż o podanym numerze nie istnieje", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JList displayTravelList(){ //do wyświetlenia listy w GUI
        DefaultListModel<String> listModel = new DefaultListModel<>();
        travelList.forEach(travel -> listModel.addElement(travel.getTitle()));

        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        return list;
    }

    public static void displayTravelInformation(Travel travel) {
        String information = "Podróż z " + travel.getFromPlace() + " do " + travel.getToPlace() +
                " na dystansie " + travel.getKilometres() + " km, planowana na " + travel.getDuration();
        JOptionPane.showMessageDialog(null, information, "Informacje o podróży", JOptionPane.INFORMATION_MESSAGE);
    }

    //Oblicza koszt podróży
    public static double calculateTravelCost(int kilometres, double fuelPricePerLitre, double fuelConsumptionPerKm) {
        double fuelNeeded = kilometres * fuelConsumptionPerKm;
        return fuelNeeded * fuelPricePerLitre;
    }

    public static void displayTravelCost(double travelCost) {
        // Ta metoda wyświetla koszty podróży w interfejsie graficznym
        JOptionPane.showMessageDialog(null, "Koszt podróży wynosi: " + travelCost + " zł", "Koszt podróży", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<Travel> getTravelList() {
        return travelList;
    }

    public void setTravelList(List<Travel> travelList) {
        this.travelList = travelList;
    }

    public void addToTravelList(Travel travel){
        this.travelList.add(travel);
    }

    public void addToTravelListAll(List<Travel> travelList){
        for (Travel t:travelList) {
            addToTravelList(t);
        }
    }



}
