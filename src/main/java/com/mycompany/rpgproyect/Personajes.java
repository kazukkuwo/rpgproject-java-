
package com.mycompany.rpgproyect;
import com.mycompany.rpgproyect.inventario.Inventario;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author vicente p
 */
public abstract class Personajes extends Entidad{
    private int nivel;
    private int xp;
    private int nivelMaximo;
    
    private int vida;
    private int mana;
    private int fuerza;
    private int defensa;
    private int agilidad;
    private int oro;
    private int ataque;
    
    private EstadoPersonaje estado;
    private Inventario inventario;
    private ArrayList<RegistroMision> historialMisiones;
    
    private LocalDate fechaCreacion;
    private LocalDate fechaUltimaSesion;
    //constructores
    public Personajes(String nombre, int vida, int mana, int fuerza, int defensa,int agilidad,double MAX_PESO) {
        super(validarNombre(nombre));
        this.nivel = 1;
        this.xp = 0;
        this.nivelMaximo = 1;
        this.vida = vida;
        this.mana = mana;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.agilidad = agilidad;
        this.oro = 0;
        this.ataque = fuerza;
        this.estado = EstadoPersonaje.ACTIVO;
        this.inventario = new Inventario(MAX_PESO);
        this.historialMisiones = new ArrayList<>();
        this.fechaCreacion = LocalDate.now();
        this.fechaUltimaSesion = LocalDate.now();
    }
    //valida que el nombre cumpla con los requisitos
    private static String validarNombre(String nombre){
        if(nombre.contains(" ")){
            throw new IllegalArgumentException("El nombre no puede tener espacios.");
        }
        if(nombre.length() > 15){
            throw new IllegalArgumentException("el nombre no puede superar los 15 caracteres.");
        }
        return nombre;
            
    }
    //nombre de la clase
    public abstract String getNombreClase();
    //cada clase sube distintos stats al subir de nivel
    protected abstract void aplicarBonusNivel();
    //metodo para ganar xp
    public void ganarXP(int cantidad){
        xp +=cantidad;
        while(xp >= xpParaSiguienteNivel()){
            xp -= xpParaSiguienteNivel();
            subirNivel();
        }
    }
    //metodo que calcula la xp para subir de nivel de manera exponencial
    public int xpParaSiguienteNivel(){
        return (int)(100* Math.pow(nivel, 2.0));
    }
    //metodo que sube de nivel y las stat
    private void subirNivel(){
        nivel++;
        vida +=20;
        fuerza +=10;
        defensa += 10;
        agilidad += 5;
        ataque +=5;
        aplicarBonusNivel();
        if(nivel > nivelMaximo) {
            nivelMaximo = nivel;
        }
        System.out.println(getNombre()+ "subio de nivel" + nivel);       
    }
    // al historial de misisones agrega
    public void agregarRegistroMision(RegistroMision rm){
        historialMisiones.add(rm);
    }
    //busca en el historial de misiones misiones con el estado en progreso
    public RegistroMision buscarMisionEnProgreso(String nombreMision){
        for(RegistroMision rm : historialMisiones)
            if(rm.getNombreMision().equals(nombreMision) && rm.getEstado() == EstadoMision.EN_PROGRESO)
                return rm;
    
        return null;
    }
    //te dice si tienes mision activas
    public boolean tieneMisionActiva(String nombreMision){
        return buscarMisionEnProgreso(nombreMision) != null;
    }
    //getters
    public int getNivel() {
        return nivel;
    }
    public int getXp() {
        return xp;
    }
    public int getNivelMaximo() {
        return nivelMaximo;
    }
    public int getVida() {
        return vida;
    }
    public int getMana() {
        return mana;
    }
    public int getFuerza() {
        return fuerza;
    }
    public int getDefensa() {
        return defensa;
    }
    public int getAgilidad() {
        return agilidad;
    }
    public int getOro() {
        return oro;
    }
    public int getAtaque() {
        return ataque;
    }
    public EstadoPersonaje getEstado() {
        return estado;
    }
    public Inventario getInventario() {
        return inventario;
    }
    public ArrayList<RegistroMision> getHistorialMisiones() {
        return historialMisiones;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public LocalDate getFechaUltimaSesion() {
        return fechaUltimaSesion;
    }
    //setters
    public void setNivel(int n) {
        this.nivel = n;
    }
    public void setXp(int x) {
        this.xp = x;
    }
    public void setNivelMaximo(int n) {
        this.nivelMaximo = n;
    }
    public void setVida(int v) {
        this.vida = v;
    }
    public void setMana(int m) {
        this.mana = m;
    }
    public void setFuerza(int f) {
        this.fuerza = f;
    }
    public void setDefensa(int d) {
        this.defensa = d;
    }
    public void setAgilidad(int a) {
        this.agilidad = a;
    }
    public void setOro(int o) {
        this.oro = o;
    }
    public void setAtaque(int a) {
        this.ataque = a;
    }
    public void setEstado(EstadoPersonaje e) {
        this.estado = e;
    }
    public void setFechaCreacion(LocalDate f) {
        this.fechaCreacion = f;
    }
    public void setFechaUltimaSesion(LocalDate f) {
        this.fechaUltimaSesion = f;
    }
    
    @Override
    public String resumen(){
        return String.format("%s [%s] Nv.%d | %s", getNombre(),getNombreClase(),nivel,estado);
    }
    
    public String Ficha(){
       return String.format("""
                            --- FICHA: %s [%s] ---
                              Nivel: %d (max: %d) | XP: %d/%d
                              VID:%d MAN:%d FUE:%d DEF:%d AGI:%d ATQ:%d
                              Oro: %d | Estado: %s
                              Creado: %s | Ultima sesion: %s
                              Inventario: %s""",
            getNombre(), getNombreClase(), nivel, nivelMaximo,
            xp, xpParaSiguienteNivel(),
            vida, mana, fuerza, defensa, agilidad, ataque,
            oro, estado,
            fechaCreacion, fechaUltimaSesion,
            inventario.resumen());
    }

    

   
    
    
}
