/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package car_management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Christof Weickhardt
 */
public class Garage {
    private static ArrayList<Fahrzeug> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        readData();
        printActionMenu();

        int actionChoice;
        while ((actionChoice = getIntInput(0, 4)) != 0) {
            newline();
            switch (actionChoice) {
                case 1:
                    addNewVehicle();
                    break;
                case 2:
                    showVehicles();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    removeVehicle();
                    break;
            }
            printActionMenu();
        }
    }

    private static void edit() {
        println("Wählen sie einen Index den sie bearbeiten wollen");
        int choice = getIntInput(0, vehicles.size() - 1);
        Fahrzeug f = vehicles.get(choice);
        println("! eingeben, wenn nichts geänder werden soll:");
        println("Versicherungsnummer (" + f.getVersicherungsnummer() + ")");
        Scanner scanner = new Scanner(System.in);
        String v;
        if (!(v = scanner.next()).equals("!")) {
            f.setVersicherungsnummer(v);
        }

        println("Marke (" + f.getMarke() + ")");
        String mark = "";
        if (!(mark = scanner.next()).equals("!")) {
            f.setMarke(mark);
        }

        println("Modell (" + f.getModell() + ")");
        String mod = "";
        if (!(mod = scanner.next()).equals("!")) {
            f.setModell(mod);
        }
        if (f instanceof Lastwagen) {
            println("Ladeflaeche (" + ((Lastwagen) f).getLadeflaeche() + ")");
            String ladeflaeche = "";
            if (!(ladeflaeche = scanner.next()).equals("!")) {
                ((Lastwagen) f).setLadeflaeche(Double.valueOf(ladeflaeche));
            }
            vehicles.remove(choice);
            vehicles.add(f);
            saveToFile();
        } else if (f instanceof Auto) {
            println("Sitzplaetze (" + ((Auto) f).getSitzplaetze() + ")");
            String seats = "";
            if (!(seats = scanner.next()).equals("!")) {
                ((Auto) f).setSitzplaetze(Integer.valueOf(seats));
            }
            vehicles.remove(choice);
            vehicles.add(f);
            saveToFile();
        } else {
            vehicles.remove(choice);
            vehicles.add(f);
            saveToFile();
        }

    }

    private static int getIntInput(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice <= max && choice >= min) {
                    scanner.close();
                    break;
                } else {
                    println("Dies ist keine gültige Eingabe");
                }
            } catch (Exception e) {
            }
        }
        return choice;
    }

    private static int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.close();
                break;
            } catch (Exception e) {
            }
        }
        return choice;
    }

    private static double getDoubleInput() {
        Scanner scanner = new Scanner(System.in);
        double choice = 0;
        while (true) {
            try {
                choice = scanner.nextDouble();
                scanner.close();
                break;
            } catch (Exception e) {
            }
        }
        return choice;
    }

    private static void printVehicleTypes() {
        println("[0] Beenden");
        println("[1] Auto");
        println("[2] Lastwagen");
        println("[3] Motorrad");
        newline();
        println("Ihre Wahl:");
    }

    private static void printActionMenu() {
        println("[0] Beenden");
        println("[1] Neues Fahrzeug erfassen");
        println("[2] Liste anzeigen");
        println("[3] Fahrzeug bearbeiten");
        println("[4] Fahrzeug löschen");
        newline();
        println("Ihre Wahl:");
    }

    private static void newline() {
        System.out.println("");
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private static void saveToFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("Vehicels.txt"));
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(vehicles);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private static void readData() {
        FileInputStream fis;
        try {
            File f = new File("Vehicels.txt");
            if (f.exists()) {
                fis = new FileInputStream(f);
                ObjectInputStream ois = null;
                ois = new ObjectInputStream(fis);
                vehicles = (ArrayList<Fahrzeug>) ois.readObject();
                ois.close();
            } else {
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    private static void showVehicles() {
        if (vehicles.isEmpty()) {
            println("Es wurden keine Fahrzeuge gefunden");
        } else {
            int index = 0;
            for (Fahrzeug f : vehicles) {
                println("[" + index + "] " + f.toString());
                index++;
            }
        }
        newline();
    }

    private static void addNewVehicle() {
        printVehicleTypes();
        int choice = getIntInput(0, 3);
        if (choice > 0) {
            Fahrzeug basicinfo = new Fahrzeug();
            Scanner scanner = new Scanner(System.in);
            println("Marke des Fahrzeuges:");
            basicinfo.setMarke(scanner.next());
            println("Modell des Fahrzeuges:");
            basicinfo.setModell(scanner.next());
            println("Versicherungsnummer des Fahrzeuges:");
            basicinfo.setVersicherungsnummer(scanner.next());
            scanner.close();
            switch (choice) {
                case 1:
                    Auto auto = new Auto(basicinfo);
                    println("Sitzplaetze im Fahrzeug:");
                    auto.setSitzplaetze(getIntInput());
                    vehicles.add(auto);
                    saveToFile();
                    break;
                case 2:
                    Lastwagen lw = new Lastwagen(basicinfo);
                    println("Ladeflaeche des Lastwagens:");
                    lw.setLadeflaeche(getDoubleInput());
                    vehicles.add(lw);
                    saveToFile();
                    break;
                case 3:
                    Motorrad m = new Motorrad(basicinfo);
                    vehicles.add(m);
                    saveToFile();
                    break;
            }
        }
        newline();
    }

    private static void removeVehicle() {
        if (!vehicles.isEmpty()) {
            println("Welches auto möchten Sie gerne entfernen:");
            int vehicleToDelete = getIntInput(0, vehicles.size() - 1);
            vehicles.remove(vehicleToDelete);
            saveToFile();
        }
        newline();
    }
}
