
package com.mycompany.rpgproyect.enums;

/**
 *
 * @author vpino
 */
public enum EstadoPersonaje {
    ACTIVO,
    REPOSO,
    ELIMINADO;
   @Override
    public String toString(){
        return switch (this) {
            case ACTIVO -> "Activo";
            case REPOSO -> "Reposo";
            case  ELIMINADO-> "Eliminado";
            default -> name();
        };
     }
}
