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
 * @author christoftobias.weick
 */
public class Garage {
    private static ArrayList<Fahrzeug> fahrzeuge = new ArrayList<>();
    
    public static void main(String[] args){
        readData();
        int actionchoice;
        printActionMenu();
        while((actionchoice = getIntInput(0,4))!=0){
            newline();
            switch(actionchoice){
                case 1:
                    addNewVehicle();
                    break;
                case 2:
                    showVehicels();
                    break;
                case 3:
                    bearbeiten();
                    break;
                case 4:
                    removeVehicle();
                    break;
            }
            printActionMenu();
        }
    }
    private static void bearbeiten(){
        println("Wählen sie einen Index den sie bearbeiten wollen");
        int editchoice = getIntInput(0,fahrzeuge.size()-1);
        Fahrzeug f = fahrzeuge.get(editchoice);
        println("! eingeben, wenn nichts geänder werden soll:");
        println("Versicherungsnummer ("+f.getVersicherungsnummer()+")");
        Scanner sc = new Scanner(System.in);
        String v;
        if(!(v = sc.next()).equals("!")){
            f.setVersicherungsnummer(v);
        }
        
        println("Marke ("+f.getMarke()+")");
        String mark = "";
        if(!(mark = sc.next()).equals("!")){
            f.setMarke(mark);
        }
        
        println("Modell ("+f.getModell()+")");
        String mod = "";
        if(!(mod = sc.next()).equals("!")){
            f.setModell(mod);
        }
        if(f instanceof Lastwagen){
            println("Ladeflaeche ("+((Lastwagen)f).getLadeflaeche()+")");
            String ladeflaeche = "";
            if(!(ladeflaeche = sc.next()).equals("!")){
                ((Lastwagen)f).setLadeflaeche(new Double(ladeflaeche));
            }
            fahrzeuge.remove(editchoice);
            fahrzeuge.add(f);
            saveToFile();
        }else if(f instanceof Auto){
            println("Sitzplaetze ("+((Auto)f).getSitzplaetze()+")");
            String plaetze = "";
            if(!(plaetze = sc.next()).equals("!")){
                ((Auto)f).setSitzplaetze(new Integer(plaetze));
            }
            fahrzeuge.remove(editchoice);
            fahrzeuge.add(f);
            saveToFile();
        }else{
            fahrzeuge.remove(editchoice);
            fahrzeuge.add(f);
            saveToFile();
        }
        
        
    }
    private static int getIntInput(int min,int max){
        boolean hasfound = false;
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(!hasfound){
            try{
                choice = sc.nextInt();
                if(choice <= max && choice >= min){
                    hasfound = true;
                }else{
                    println("Dies ist keine gültige Eingabe");
                }
            }catch(Exception e){
            }
        }
        return choice;
    }
    private static int getIntInput(){
        boolean hasfound = false;
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(!hasfound){
            try{
                choice = sc.nextInt();
                hasfound = true;
            }catch(Exception e){
            }
        }
        return choice;
    }
    private static double getDoubleInput(){
        boolean hasfound = false;
        Scanner sc = new Scanner(System.in);
        double choice = 0;
        while(!hasfound){
            try{
                choice = sc.nextDouble();
                hasfound = true;
            }catch(Exception e){
            }
        }
        return choice;
    }
    private static void printVehicleTypes(){
        println("[0] Beenden");
        println("[1] Auto");
        println("[2] Lastwagen");
        println("[3] Motorrad");
        newline();
        println("Ihre Wahl:");
    }
    private static void printActionMenu(){
        println("[0] Beenden");
        println("[1] Neues Fahrzeug erfassen");
        println("[2] Liste anzeigen");
        println("[3] Fahrzeug bearbeiten");
        println("[4] Fahrzeug löschen");
        newline();
        println("Ihre Wahl:");
    }
    private static void newline(){
        System.out.println("");
    }
    private static void print(String message){
        System.out.print(message);
    }
    private static void println(String message){
        System.out.println(message);
    }
    private static void saveToFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("Vehicels.txt"));
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(fahrzeuge);
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
            if(f.exists()){
                fis = new FileInputStream(f);
                ObjectInputStream ois = null;
                ois = new ObjectInputStream(fis);
                fahrzeuge = (ArrayList<Fahrzeug>) ois.readObject();
                ois.close();
            }else{
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
    
    private static void showVehicels() {
        if(fahrzeuge.isEmpty()){
            println("Es wurden keine Fahrzeuge gefunden");
        }else{
            int index = 0;
            for(Fahrzeug f : fahrzeuge){
                println("["+index+"] "+f.toString());
                index++;
            }
        }
        newline();
    }
    
    private static void addNewVehicle() {
        printVehicleTypes();
        int choice = getIntInput(0, 3);
        if(choice > 0){
            Fahrzeug basicinfo = new Fahrzeug();
            Scanner sc = new Scanner(System.in);
            println("Marke des Fahrzeuges:");
            basicinfo.setMarke(sc.next());
            println("Modell des Fahrzeuges:");
            basicinfo.setModell(sc.next());
            println("Versicherungsnummer des Fahrzeuges:");
            basicinfo.setVersicherungsnummer(sc.next());
            switch(choice){
                case 1:
                    Auto auto = new Auto(basicinfo);
                    println("Sitzplaetze im Fahrzeug:");
                    auto.setSitzplaetze(getIntInput());
                    fahrzeuge.add(auto);
                    saveToFile();
                    break;
                case 2:
                    Lastwagen lw = new Lastwagen(basicinfo);
                    println("Ladeflaeche des Lastwagens:");
                    lw.setLadeflaeche(getDoubleInput());
                    fahrzeuge.add(lw);
                    saveToFile();
                    break;
                case 3:
                    Motorrad m = new Motorrad(basicinfo);
                    fahrzeuge.add(m);
                    saveToFile();
                    break;
            }
        }
        newline();
    }
    
    private static void removeVehicle() {
        if(!fahrzeuge.isEmpty()){
            println("Welches auto möchten Sie gerne entfernen:");
            int todelete = getIntInput(0, fahrzeuge.size()-1);
            fahrzeuge.remove(todelete);
            saveToFile();
        }
        newline();
    }
}
