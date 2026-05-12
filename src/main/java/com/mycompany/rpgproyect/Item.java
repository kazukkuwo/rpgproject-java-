
package com.mycompany.rpgproyect;

/**
 *
 * @author vicente pino
 */
//clase que es la base para los items aca se define rareza tipo etc
public abstract class Item extends Entidad {
    
    private String drescripcion;
    private TipoItem tipo;
    private Rareza rareza;
    private double peso;
    private int efecto;
    
    //constructor
    public Item( String nombre, String drescripcion, TipoItem tipo, Rareza rareza, double peso, int efecto) {
        super(nombre);
        this.drescripcion = drescripcion;
        this.tipo = tipo;
        this.rareza = rareza;
        this.peso = peso;
        this.efecto = efecto;
    }
    
    //getters
    public String getDrescripcion() {
        return drescripcion;
    }
    public TipoItem getTipo() {
        return tipo;
    }
    public Rareza getRareza() {
        return rareza;
    }
    public double getPeso() {
        return peso;
    }
    public int getEfecto() {
        return efecto;
    }

  

   
    @Override
    public String resumen(){
        return String.format("[%s] %s (%s) %.1fkg", tipo, getNombre(),rareza,peso);
    }
    @Override
    public String toString(){
        return String.format("[%s] %s (%s) - Efecto:%d | %.1fkg | %s", tipo,getNombre(),rareza,efecto,peso,drescripcion);
    }
    

  
  
    

    
    
    
}
