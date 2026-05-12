
package com.mycompany.rpgproyect;

/**
 *
 * @author vicente pino
 */
//enum para retornar nombre de  tipo de cada item
public enum TipoItem {
    ARMA,
    ARMADURA,
    POCION,
    MISCELANEA;
    
    @Override
    public String toString(){
        return switch (this) {
            case ARMA -> "Arma";
            case ARMADURA -> "Armadura";
            case POCION -> "Pocion";
            case MISCELANEA -> "Miscelanea";
            default -> name();
        };
     }
    
    
}
