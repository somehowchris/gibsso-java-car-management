/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_management;

import java.io.Serializable;

/**
 *
 * @author christoftobias.weick
 */
public class Fahrzeug implements Serializable{
    private String versicherungsnummer;
    private String marke;
    private String modell;

    public Fahrzeug() {
    }
    public Fahrzeug(Fahrzeug f){
        setVersicherungsnummer(f.versicherungsnummer);
        setMarke(f.marke);
        setModell(f.modell);
    }
    public Fahrzeug(String versicherungsnummer, String marke, String modell) {
        this.versicherungsnummer = versicherungsnummer;
        this.marke = marke;
        this.modell = modell;
    }

    public void setVersicherungsnummer(String versicherungsnummer) {
        this.versicherungsnummer = versicherungsnummer;
    }

    public String getVersicherungsnummer() {
        return versicherungsnummer;
    }

    public String getMarke() {
        return marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }
    @Override
    public String toString() {
        return  getClass().getSimpleName()+": "+getVersicherungsnummer()+" - "+getMarke()+" - "+ getModell();
    }
    
}
