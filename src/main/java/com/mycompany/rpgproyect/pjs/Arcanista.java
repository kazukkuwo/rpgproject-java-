
package com.mycompany.rpgproyect.pjs;

import com.mycompany.rpgproyect.Personajes;

/**
 *
 * @author vpino
 */
public class Arcanista extends Personajes {
    private int magia;
    public Arcanista(String nombre){
        super(nombre,90,150,8,6,13,20.0);
        this.magia = 30;
    }
    
    @Override public String getNombreClase(){
        return "Arcanista";
    }
    
    @Override
    protected void aplicarBonusNivel(){
        magia += 12;
        setMana(getMana()+ 20);
        System.out.println(" Magia +" + 12 + " | Mana +20");
    }
    
    public int getMagia(){
        return magia;
        
    }
    
    @Override
    public String Ficha(){
        return super.Ficha()+ "\n Magia: " + magia;
    }
    
}
