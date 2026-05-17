
package com.mycompany.rpgproyect;

import com.mycompany.rpgproyect.inventario.Item;

/**
 *
 * @author vpino
 */
public class Armadura extends Item implements Equipable {
    private int bonusDefensa;
    private int Sagilidad;
    
    public Armadura(String nombre, String descripcion, Rareza rareza, double peso, int bonusDefensa,int Sagilidad){
        super(nombre,descripcion,TipoItem.ARMADURA, rareza, peso, bonusDefensa);
        this.bonusDefensa = bonusDefensa;
        this.Sagilidad = Sagilidad;
    }
    
    @Override
    public void equipar(Personajes p){
        p.setDefensa(p.getDefensa()+ bonusDefensa);
        p.setAgilidad(p.getAgilidad()- Sagilidad);
        System.out.println("Equipaste "+ getNombre()+ ". Defensa +"+ bonusDefensa+(Sagilidad > 0? " | Agilidad -" + Sagilidad : "."));
    }
    
    @Override
    public void desequipar(Personajes p){
        p.setDefensa(p.getDefensa()- bonusDefensa);
        p.setAgilidad(p.getAgilidad()+ Sagilidad);
        System.out.println("Desequipaste "+ getNombre()+ ".");
    }
    
    public int getBonusDefensa(){
        return bonusDefensa;
    }
    public int getSagilidad(){
        return Sagilidad;
    }
    
    @Override
    public String toString(){
        return String.format("[Armadura] %s (%s) - DEF:+%d SAGI:-%d | %.1fkg | %s", getNombre(),getRareza(),bonusDefensa,Sagilidad,getPeso(),getDrescripcion());
    }
    
}
