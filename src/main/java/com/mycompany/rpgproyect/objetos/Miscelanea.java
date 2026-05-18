
package com.mycompany.rpgproyect.objetos;

import com.mycompany.rpgproyect.pjs.Personajes;
import com.mycompany.rpgproyect.interfaces.Usable;
import com.mycompany.rpgproyect.enums.TipoItem;
import com.mycompany.rpgproyect.enums.Rareza;
import com.mycompany.rpgproyect.inventario.Item;

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
