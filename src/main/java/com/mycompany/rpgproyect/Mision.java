
package com.mycompany.rpgproyect;

/**
 *
 * @author vpino
 */
public class Mision extends Entidad {
    private String descripcion;
    private int nivelMinimo;
    private int recompensaXP;
    private int recompensaORO;
    //constructore
    public Mision(String nombre, String descripcion, int nivelMinimo, int recompensaXP, int recompensaORO){
        super(nombre);
        this.descripcion = descripcion;
        this.nivelMinimo = nivelMinimo;
        this.recompensaXP= recompensaXP;
        this.recompensaORO = recompensaORO;
    }
    //getters
    public String getDescripcion() {
        return descripcion;
    }
    public int getNivelMinimo() {
        return nivelMinimo;
    }
    public int getRecompensaXP() {
        return recompensaXP;
    }
    public int getRecompensaORO() {
        return recompensaORO;
    }
    
    @Override
    public String resumen(){
        return String.format("[Nv.%d+] %s - XP:%d Oro:%d", nivelMinimo,getNombre(),recompensaXP,recompensaORO);
        
    }
    @Override
    public String toString(){
        return String.format("[Nv.%d+] %s - XP:%d Oro:%d | %s",nivelMinimo,getNombre(),recompensaXP,recompensaORO,descripcion );
        
    }
}
