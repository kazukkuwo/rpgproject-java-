

package com.mycompany.rpgproyect;

/**
 *
 * @author vicente pino
 */
public abstract class Entidad {
    private static int contadorID = 0;
    private final int id;
    private String nombre;
    
    //constructort
    public Entidad(String nombre){
        this.id = ++contadorID;
        this.nombre = nombre;
    }
    //getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    //setters
    public void setNombre(String n){
        this.nombre = n;
    }
    
    
    
    public abstract String resumen();
}
