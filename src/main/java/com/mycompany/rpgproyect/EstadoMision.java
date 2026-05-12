
package com.mycompany.rpgproyect;

/**
 *
 * @author vpino
 */
//para ver como tiene las misiones el personaje
public enum EstadoMision {
    DISPONIBLE,
    EN_PROGRESO,
    COMPLETADA,
    FALLIDA;
    @Override
    public String toString(){
        return switch (this) {
            case DISPONIBLE -> "Disponible";
            case EN_PROGRESO -> "En progreso";
            case COMPLETADA -> "Completada";
            case FALLIDA -> "Fallida";
            default -> name();
        };
    }

    
}
