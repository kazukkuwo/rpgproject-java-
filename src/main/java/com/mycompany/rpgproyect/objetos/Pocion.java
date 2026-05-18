
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
public class Pocion extends Item implements Usable {
    public enum TipoEfecto{
        VIDA,
        MANA,
        FUERZA,
        XP
    }
    private TipoEfecto tipoEfecto;
    
    public Pocion(String nombre, String drescripcion, Rareza rareza, double peso,int cantidad, TipoEfecto tipoEfecto){
        super(nombre,drescripcion,TipoItem.POCION,rareza,peso,cantidad);
        this.tipoEfecto = tipoEfecto;
        
    }
    //metodo que aplica el usar cada pocion
    @Override
    public String usar(Personajes p ){
        int cantidad = getEfecto();
        switch(tipoEfecto){
            case VIDA -> {
                p.setVida(p.getVida()+ cantidad);
                return getNombre()+" usada. Vida +"+ cantidad + " (total: "+ p.getVida() + ")";
            }
            case MANA -> {
                p.setMana(p.getMana()+cantidad);
                return getNombre() + "usada. Mana +"+cantidad + " (Total: " + p.getMana()+")";
                
            }
            case FUERZA -> {
                p.setFuerza(p.getFuerza()+ cantidad);
                p.setAtaque(p.getAtaque()+ cantidad);
                return getNombre()+ "usada. Fuerza +" + cantidad + " | Ataque +" + cantidad;
                
            }
            case XP -> {
                p.ganarXP(cantidad);
                return getNombre()+ "Usada. XP +" + cantidad;
            }
            default -> { 
                return "Efecto desconocido";
            }
      
             
        }
    }
    
    public TipoEfecto getTipoEfecto(){
        return tipoEfecto;
    }
    
    @Override
    public String toString() {
        String efecto;
        efecto = switch (tipoEfecto) {
            case VIDA -> "Vida +"   + getEfecto();
            case MANA -> "Mana +"   + getEfecto();
            case FUERZA -> "Fuerza +" + getEfecto();
            case XP -> "XP +"     + getEfecto();
            default -> "?";
        };
        return String.format("[Pocion] %s (%s) — %s | %.1fkg | %s",
                getNombre(), getRareza(), efecto, getPeso(), getDrescripcion());
    }
}
    

