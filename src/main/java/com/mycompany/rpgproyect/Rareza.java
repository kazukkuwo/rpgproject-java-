/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpgproyect;

/**
 *
 * @author kazukkuwo
 */
public enum Rareza {
    COMUN,
    POCO_COMUN,
    RARO,
    EPICO,
    LEGENDARIO;

    @Override
    public String toString(){
        switch (this){
            case COMUN:  return "comun";
            case POCO_COMUN:  return "POCO COMUN";
            case RARO:  return "RARO";
            case EPICO: return "LEGENDARIO";
            default:  return name();
                    
        }
    }
    
    
    
    
}
