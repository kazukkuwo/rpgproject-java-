/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpgproyect;

/**
 *
 * @author vicente 
 */
public abstract class Personajes {
    private String nombre;
    private int nivel;
    private int xp;
    private int vida;
    private int ataque;
    private int defensa;
    private Inventario inventario;

    public Personajes(String nombre, int nivel, int xp, int vida, int ataque, int defensa, double MAX_PESO1) {
        this.nombre = nombre;
        this.nivel = 1;
        this.xp = 0;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.inventario = new Inventario(MAX_PESO);
        
    }
    public abstract String getNonmbreClase();
    protected abstract void aplicarBonusNivel();
    public void ganarXP(int cantidad){
        xp += cantidad;
        System.out.println(nombre + " gana " + cantidad + " XP ");
        while (xp >= xpParaSiguienteNivel()){
            xp -= xpParaSiguienteNivel();
            subirNivel();
        }
    }
    
    public int xpParaSiguienteNivel(){
        return (int)(100 * Math.pow(nivel, 1.5));
    }
    private void subirNivel(){
        nivel++;
        vida += 20;
        ataque += 5;
        defensa += 3;
        aplicarBonusNivel();
        System.out.println("¡" + nombre + " subió al nivel " + nivel + "!");
        
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getXp() {
        return xp;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setVida(int v) {
        this.vida = v;
    }

    public void setAtaque(int a) {
        this.ataque = a;
    }

    public void setDefensa(int d) {
        this.defensa = d;
        
    }
    @Override
    public String toString() {
        return String.format("%s [%s] — Nv.%d | XP: %d/%d | VID:%d ATQ:%d DEF:%d",
                nombre, getNonmbreClase(), nivel,
                xp, xpParaSiguienteNivel(),
                vida, ataque, defensa);
    }
    
    
    
}
