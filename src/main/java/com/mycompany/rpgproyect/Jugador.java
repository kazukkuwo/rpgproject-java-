
package com.mycompany.rpgproyect;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author kazukkuwo
 */
public class Jugador {
    private static final int MAX_PERSONAJES = 5;
    
    private String apodo;
    private String nombre;
    private String apellido;
    private String correo;
    private String fechaNacimeinto;
    private String clave;
    private ArrayList<Personajes> personajes;
    private ArrayList<Personajes> bodega;
    //constructores
    public Jugador(String apodo, String nombre, String apellido, String correo, String fechaNacimiento){
        this.apodo = apodo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = generarClave(nombre,apellido,apodo);
        this.personajes = new ArrayList<>();
        this.bodega = new ArrayList<>();
    }
    //metodo que genera la clave
    private String generarClave(String nombre, String apellido, String apodo){
        String iN = String.valueOf(nombre.charAt(0)).toUpperCase();
        String iA = String.valueOf(apellido.charAt(0)).toUpperCase();
        return iN + iA + apodo;
    }
    //metodo que agrega personajes
    public String agregarPersonaje(Personajes p){
        if(getTotalPersonajes() >= MAX_PERSONAJES){
            return " Limite de 5 personajes alcanzados";
            
        }
        personajes.add(p);
        return"Personaje " + p.getNombre()+ " Creado.";
    }
    
    public int getTotalPersonajes(){
        return personajes.size() + bodega.size();
    }
    
    public String descartarPersonaje(int indice){
        if (indice < 0 || indice >= personajes.size()){
            return "Indice invalido";
            
        }
        Personajes p = personajes.remove(indice);
        p.setEstado(EstadoPersonaje.REPOSO);
        bodega.add(p);
        return p.getNombre() + " Enviado a la bodega";
        
    }
    
    public String recuperarPersonaje(int indice){
        if (indice < 0 || indice >= bodega.size()){
            return "Indice invalido";
        }
        Personajes p = bodega.remove(indice);
        p.setEstado(EstadoPersonaje.ACTIVO);
        personajes.add(p);
        return p.getNombre()+" Recuperado.";
    }
    
    public String eliminarPersonaje(int indice){
        if (indice < 0 || indice >= bodega.size()){
            return "Indice invalido";
        }
        String nombre = bodega.remove(indice).getNombre();
        return nombre + " eliminado para siempre";
    }
    
    public void ordenarPorNivel() {
        personajes.sort(Comparator.comparingInt(Personajes::getNivel).reversed());
    }
    public void ordenarPorXP() {
        personajes.sort(Comparator.comparingInt(Personajes::getXp).reversed());
    }
    public void ordenarPorFechaCreacion() {
        personajes.sort(Comparator.comparing(Personajes::getFechaCreacion));
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

    public String getFechaNacimeinto() {
        return fechaNacimeinto;
    }

    public String getClave() {
        return clave;
    }

    public ArrayList<Personajes> getPersonajes() {
        return personajes;
    }

    public ArrayList<Personajes> getBodega() {
        return bodega;
    }

}
