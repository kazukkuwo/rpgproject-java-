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
public class Jugador {
    private static final int MAX_PERSONAJES =5;
    private String apodo;
    private String nombre;
    private String apellido;
    private String correo;
    private String fechanacimiento;
    private String clave;
    private ArrayList<Personajes> personajes;

    public Jugador(String apodo, String nombre, String apellido, String correo, String fechanacimiento, String clave, ArrayList<Personajes> personajes) {
        this.apodo = apodo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechanacimiento = fechanacimiento;
        this.clave = generarClave(nombre, apellido , apodo);
        this.personajes = new ArrayList<>();
    }
    private String generarClave(String nombre, String apellido, String apodo){
        String iN = String.valueOf(nombre.charAt(0)).toUpperCase();
        String iA = String.valueOf(apellido.charAt(0)).toUpperCase();
        return iN + iA + apodo;
    }
    public boolean agregarPersonaje(Personajes p){
        if(personajes.size() >= MAX_PERSONAJES) return false;
        personajes.add(p);
        return true;
                
    }

    public String getApodo() {
        return apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public String getClave() {
        return clave;
    }

    public ArrayList<Personajes> getPersonajes() {
        return personajes;
    }
    
    
    
    
    
}
