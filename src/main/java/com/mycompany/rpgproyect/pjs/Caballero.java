
package com.mycompany.rpgproyect.pjs;

import com.mycompany.rpgproyect.Personajes;

/**
 *
 * @author vpino
 */
public class Caballero extends Personajes{
    private int fuerza_bonus= 0;
    public Caballero(String nombre){
        super(nombre, 150,50,20,15,8,50.0);
        
    }
    @Override public String getNombreClase(){ return"Caballero";}
    
    @Override
    protected void aplicarBonusNivel(){
        fuerza_bonus += 8;
        setFuerza(getFuerza()+8);
        System.out.println(" Fuerza +" + 8);
        
    }
    
    @Override
    public String Ficha(){
        return super.Ficha() + "\n Bonus fuerza acumulado: "+ fuerza_bonus;
    }
    
    
}
