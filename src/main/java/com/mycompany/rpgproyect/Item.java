/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpgproyect;

/**
 *
 * @author kazukkuwo
 */
public class Item {
    private String name;
    private double peso;
    private  Rareza rareza;

    public Item(String name, double peso, Rareza rareza) {
        this.name = name;
        this.peso = peso;
        this.rareza = rareza;
    }

    public String getName() {
        return name;
    }

    public double getPeso() {
        return peso;
    }

    public Rareza getRareza() {
        return rareza;
    }
    @Override
    public String toString() {
        return String.format("%s [%s] — %.1f kg", name, rareza, peso);
    }
    

  
  
    

    
    
    
}
