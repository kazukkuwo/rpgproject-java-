
package com.mycompany.rpgproyect.interfaces;

import com.mycompany.rpgproyect.pjs.Personajes;

/**
 *
 * @author vicente pino
 */
// interfaz para los objetos equipables como arma y armadura que dan un bono de stats
public interface Equipable {
    //equipa y da stats
    void equipar(Personajes p);
    //desequipa y quita sttats
    void desequipar(Personajes p);
    
}
