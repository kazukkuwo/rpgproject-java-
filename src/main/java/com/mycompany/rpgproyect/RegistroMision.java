
package com.mycompany.rpgproyect;

import com.mycompany.rpgproyect.Sistemas.EstadoMision;

/**
 *
 * @author vpino
 */
public class RegistroMision {
    private int misionId;
    private String nombreMision;
    private EstadoMision estado;
    
    public RegistroMision(Mision mision){
        this.misionId = mision.getId();
        this.nombreMision = mision.getNombre();
        this.estado = EstadoMision.EN_PROGRESO;
    }
    
    public RegistroMision(int misionId, String nombreMision,EstadoMision estado){
        this.misionId = misionId;
        this.nombreMision = nombreMision;
        this.estado = estado;
    }

    public int getMisionId() {
        return misionId;
    }
    public String getNombreMision() {
        return nombreMision;
    }
    public EstadoMision getEstado() {
        return estado;
    }
    public void setEstado(EstadoMision e) {
        this.estado = e;
    }
    
    @Override
    public String toString(){
        return String.format("%-30s [%s]", nombreMision,estado);
    }
    
}
