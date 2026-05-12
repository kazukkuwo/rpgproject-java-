
package com.mycompany.rpgproyect;

/**
 *
 * @author vpino
 */
public class Miscelanea extends Item implements Usable {
    public Miscelanea(String nombre, String drescripcion, Rareza rareza, double peso, int xpBonus){
        super(nombre,drescripcion,TipoItem.MISCELANEA, rareza,peso, xpBonus);
    }
    
    @Override
    public String usar(Personajes p){
        p.ganarXP(getEfecto());
        return getNombre()+"Usado. XP +" + getEfecto();
    }
    
    
}
