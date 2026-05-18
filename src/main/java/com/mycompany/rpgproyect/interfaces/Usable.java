
package com.mycompany.rpgproyect.interfaces;

import com.mycompany.rpgproyect.pjs.Personajes;

/**
 *
 * @author vicent pino
 */
//interfaz para objetos que se pueden usar y aplican un cambio en el personaje como las pociones o miscelaneasa
public interface Usable {
    String usar(Personajes p);
    
}
