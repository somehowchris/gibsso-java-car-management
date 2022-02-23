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
public class Motorrad extends Fahrzeug implements Serializable{
    public Motorrad(Fahrzeug f) {
        super(f);
    }
    
}
