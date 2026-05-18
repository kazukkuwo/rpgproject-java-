
package com.mycompany.rpgproyect.enums;

/**
 *
 * @author Vicent pino
 */
public enum Rareza {
    COMUN,
    RARO,
    EPICO,
    LEGENDARIO;
/**metodo para retornar nombre de rarezas */
    @Override
    public String toString(){
        return switch (this) {
            case COMUN -> "Comun";
            case RARO -> "Raro";
            case EPICO -> "Epico";
            case LEGENDARIO -> "Legendario";
            default -> name();
        };
    }
    
    
    
    
}
