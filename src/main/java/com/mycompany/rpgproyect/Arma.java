
package com.mycompany.rpgproyect;

/**
 *
 * @author vpino
 */
public class Arma extends Item implements Equipable {
    private int daño;
    private int velocidad;
    private int Sagilidad;
    //constructor
    public Arma(String nombre, String drescripcion, Rareza rareza, double peso, int daño, int velocidad, int Sagilidad) {
        super(nombre, drescripcion,TipoItem.ARMA, rareza, peso, daño);
        this.daño = daño;
        this.velocidad = velocidad;
        this.Sagilidad = Sagilidad;
    }
    
    //equipa el arma y cambia los stat que tiene que cambiar 
    @Override
    public void equipar (Personajes p){
        p.setAtaque(p.getAtaque() + daño);
        p.setAgilidad(p.getAgilidad()- Sagilidad);
        System.out.println("Equipaste"+ getNombre()+ ". Ataque +" + daño + (Sagilidad > 0 ? " | agilidad -"+ Sagilidad: "")+ " | velocidad de ataque: "+ velocidad + "/10");    
    }
    //desequipa el arma
    @Override
    public void desequipar(Personajes p){
        p.setAtaque(p.getAtaque()- daño);
        p.setAgilidad(p.getAgilidad()+ Sagilidad);
        System.out.println("Desequipaste "+ getNombre()+".");
    }
    //GETTERS
    public int getDaño() {
        return daño;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public int getSagilidad() {
        return Sagilidad;
    }
    
    @Override
    public String toString(){
        return String.format("[Arma] %s (%s) - DMG:%d VEL:%d/10 Sagilidad:-%d | %.1fkg | %s", getNombre(),getRareza(),daño,velocidad,Sagilidad,getPeso(),getDrescripcion());
    }
}

