/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test02_weickhardtchristof;

/**
 *
 * @author christoftobias.weick
 */
public class Lastwagen extends Fahrzeug{
    private double ladeflaeche;

    public Lastwagen(double ladeflaeche, String versicherungsnummer, String marke, String modell) {
        super(versicherungsnummer, marke, modell);
        this.ladeflaeche = ladeflaeche;
    }

    public Lastwagen() {
    }
    
    public Lastwagen(Fahrzeug f) {
        super(f);
    }
    
    public void setLadeflaeche(double ladeflaeche) {
        this.ladeflaeche = ladeflaeche;
    }

    public double getLadeflaeche() {
        return ladeflaeche;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+": "+getVersicherungsnummer()+" - "+getMarke()+" - "+ getModell()+" - "+ getLadeflaeche()+" m2";
    }
    
    
}
