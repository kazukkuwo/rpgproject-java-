/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpgproyect;
import java.util.ArrayList;
/**
 *
 * @author kazukkuwo
 */
public class Inventario {
    private static final int  MAX_ITEMS = 15;
    private double MAX_PESO;
    private ArrayList<Item> items;

    public Inventario(double MAX_PESO) {
        this.MAX_PESO = MAX_PESO;
        items = new ArrayList<>();
    }
    public  String agregarItem(Item item){
        if(items.size() >= MAX_ITEMS){
            return "Inventario lleno";
           
        }
        double pesoActual = getPesoTotal();
        if(pesoActual + item.getPeso() > MAX_PESO){
            return String.format("demasiado pesado. Llevas %.1f/%.1f kg - el item pesa %.1f kg.", pesoActual, MAX_PESO,item.getPeso());
            
        }
        items.add(item);
        return "item " + item.getName() + " agregado al inventario.";
        
    }
    public double getPesoTotal(){
        double total = 0;
        for(Item i : items) total += i.getPeso();
        return total;
        
    }

    public int getMAX_ITEMS() {
        return MAX_ITEMS;
    }

    public double getMAX_PESO() {
        return MAX_PESO;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public  String resumen(){
        return String.format("items: %d/%d | Peso: %.1f/%.1f kg", items.size(),MAX_ITEMS, getPesoTotal(),MAX_PESO);
    }
    
            
    
          
            
            
    
}
