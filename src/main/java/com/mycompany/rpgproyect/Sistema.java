/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rpgproyect;
import java.util.ArrayList;

/**
 *
 * @author kazukkuwo
 */
public class Sistema {
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    public Sistema(){
        jugadores = new ArrayList<>();
        jugadorActual = null;
        
    }
    public Jugador registrarJugador(String apodo, String nombre,String apellido, String Correo, String fechaNacimiento){
        Jugador nuevo = new Jugador(apodo,nombre,apellido,correo,fechaNacimiento);
        jugadores.add(nuevo);
        return nuevo;
    }
}
