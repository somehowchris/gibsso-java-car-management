/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test02_weickhardtchristof;

import java.io.Serializable;

/**
 *
 * @author christoftobias.weick
 */
public class Auto extends Fahrzeug implements Serializable{
    private int sitzplaetze;

    public Auto() {
    }
    public Auto(Fahrzeug f) {
        super(f);
    }

    public Auto(int sitzplaetze, String versicherungsnummer, String marke, String modell) {
        super(versicherungsnummer, marke, modell);
        this.sitzplaetze = sitzplaetze;
    }

    public void setSitzplaetze(int sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }

    public int getSitzplaetze() {
        return sitzplaetze;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+": "+getVersicherungsnummer()+" - "+getMarke()+" - "+ getModell()+" - "+ getSitzplaetze()+" Plätze";
    }
    
}