
package com.mycompany.rpgproyect;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

/**
 *
 * @author kazukkuwo
 */
public class Sistema {
    private HashMap<String, Jugador > jugadores;
    
    private ArrayList<Mision> catalogo;
    
    private Jugador jugadorActual;
    private Personajes personajeActivo;
    private Persistencia persistencia;
    
    public Sistema(){
        jugadores = new HashMap<>();
        catalogo = new ArrayList<>();
        personajeActivo = null;
        persistencia = new Persistencia();
        persistencia.cargarTodo(jugadores,catalogo);
        
        if(jugadores.isEmpty()) cargarDatosPrecargados();
        
    }
    
    
    private void cargarDatosPrecargados(){
        Jugador j1 = new Jugador("darkblade", "Carlos", "Mendoza", "carlos@rpg.com","10/05/1998");
        Jugador j2 = new Jugador("anita","Ana","Rivera","Ana@rpg.com","22/03/1999");
        Jugador j3 = new Jugador("Troll","Froilan","muñoz","froilan@rpg.com","27/04/2000");
        jugadores.put(j1.getCorreo(), j1);
        jugadores.put(j2.getCorreo(), j2);
        jugadores.put(j3.getCorreo(), j3);
        
        //carlos
        Caballero g1 = new Caballero("Arthur");
        g1.ganarXP(250);
        j1.agregarPersonaje(g1);
        Arcanista m1 = new Arcanista ("Merlon");
        j1.agregarPersonaje(m1);
        
        //ana
        Explorador a1 = new Explorador("Chiz");
        a1.ganarXP(150);
        j2.agregarPersonaje(a1);
        Arcanista m2 = new Arcanista("Ranni");
        m2.ganarXP(100);
        j2.agregarPersonaje(m2);
        
        //froilan
        Caballero g2 = new Caballero("jhonrpg");
        j3.agregarPersonaje(g2);
        Explorador a2 = new Explorador("iphone4");
        a2.ganarXP(350);
        j3.agregarPersonaje(a2);
        
        
    
        
        
        
    }
    
    
}
