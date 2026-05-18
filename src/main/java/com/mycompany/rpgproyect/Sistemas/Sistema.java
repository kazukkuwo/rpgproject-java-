
package com.mycompany.rpgproyect.Sistemas;
import com.mycompany.rpgproyect.objetos.Arma;
import com.mycompany.rpgproyect.objetos.Armadura;
import com.mycompany.rpgproyect.enums.EstadoPersonaje;
import com.mycompany.rpgproyect.Jugador;
import com.mycompany.rpgproyect.objetos.Miscelanea;
import com.mycompany.rpgproyect.Mision;
import com.mycompany.rpgproyect.pjs.Personajes;
import com.mycompany.rpgproyect.objetos.Pocion;
import com.mycompany.rpgproyect.enums.Rareza;
import com.mycompany.rpgproyect.RegistroMision;
import com.mycompany.rpgproyect.inventario.Item;
import com.mycompany.rpgproyect.pjs.Explorador;
import com.mycompany.rpgproyect.pjs.Caballero;
import com.mycompany.rpgproyect.pjs.Arcanista;
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
        //ARMA nombre descripcion rareza peso daño velodiad y Sagilidad(la agilidad que le reta por el peso
        Arma espada = new Arma("Espada sin luz", "Espada gastada que ha experimentado mcuchas batallas", Rareza.COMUN, 3.0, 15,5,3);
        Arma arco = new Arma("Arco elfico", "Arco echo con ramas del arbol sagrado", Rareza.EPICO, 1.0, 30,8,0);
        Arma baston = new Arma("Baston de madera", "Baston de madera comun", Rareza.COMUN,2.0,10,4,4);
        //armadura nombre descripcion rareza peso defensa y sagilidad
        Armadura peto = new Armadura("Peto de Acero",  "Proteccion solida para guerreros", Rareza.COMUN, 8.0, 12, 4);
        Armadura capa = new Armadura("Capa del Mago",  "Tejida con hilos magicos",         Rareza.RARO,  0.5,  5, 0);
        //pocion nombre descripcion rareza peso cantidad que hace tipo efecto
        Pocion vidaGrande = new Pocion("Pocion de Vida Grande", "Restaura 80 puntos de vida",  Rareza.RARO,   0.3, 80,  Pocion.TipoEfecto.VIDA);
        Pocion vidaBasica = new Pocion("Pocion de Vida",        "Restaura 30 puntos de vida",  Rareza.COMUN,  0.2, 30,  Pocion.TipoEfecto.VIDA);
        Pocion pocMana    = new Pocion("Pocion de Mana",        "Restaura 50 puntos de mana",  Rareza.COMUN,  0.2, 50,  Pocion.TipoEfecto.MANA);
        Pocion pocFuerza  = new Pocion("Elixir de Fuerza",      "Aumenta la fuerza del PJ",    Rareza.RARO,   0.3, 10,  Pocion.TipoEfecto.FUERZA);
        Miscelanea tomo    = new Miscelanea("Tomo Ancestral",   "Otorga 120 XP al usarlo",     Rareza.EPICO,      1.0, 120);
        Miscelanea reliquia= new Miscelanea("Reliquia Oscura",  "Otorga 200 XP al usarlo",     Rareza.LEGENDARIO, 0.8, 200);
        //
        g1.getInventario().agregarItem(espada);
        g1.getInventario().agregarItem(peto);
        g1.getInventario().agregarItem(vidaBasica);
        a1.getInventario().agregarItem(arco);
        a1.getInventario().agregarItem(vidaGrande);
        m1.getInventario().agregarItem(baston);
        m1.getInventario().agregarItem(capa);
        m1.getInventario().agregarItem(pocMana);
        g2.getInventario().agregarItem(tomo);
        a2.getInventario().agregarItem(reliquia);
        //
        catalogo.add(new Mision("El Lobo del Bosque",    "Elimina al lobo alfa del bosque norte",   1, 80,  30));
        catalogo.add(new Mision("Ruinas Malditas",       "Explora las ruinas al este del rio",      1, 100, 50));
        catalogo.add(new Mision("La Cueva del Dragon",   "Derrota al dragon que aterroriza la aldea",3, 300,150));
        catalogo.add(new Mision("Recuperar el Grimorio", "El grimorio fue robado por bandidos",      2, 150, 80));
        catalogo.add(new Mision("Torneo del Reino",      "Participa en el torneo anual del reino",   2, 200,100));
        catalogo.add(new Mision("La Torre Oscura",       "Infiltrate en la torre del señor oscuro",  4, 400,200));
        catalogo.add(new Mision("Rastro de Sangre",      "Investiga las desapariciones en el pueblo",1, 90,  40));
        catalogo.add(new Mision("El Portal Roto",        "Cierra el portal dimensional en el norte", 5, 500,300));

        persistencia.guardarTodo(jugadores, catalogo);
        System.out.println("[Sistema] Datos precargados inicializados.");
        
        
    }
    
    public String registrarJugador(String apodo, String nombre, String apellido,
                                   String correo, String fechaNac) {
        if (jugadores.containsKey(correo))
            return "ERROR: Ya existe una cuenta con ese correo.";
        Jugador j = new Jugador(apodo, nombre, apellido, correo, fechaNac);
        jugadores.put(correo, j);
        persistencia.guardarTodo(jugadores, catalogo);
        return "OK:" + j.getClave();
    }

    public boolean iniciarSesion(String correo, String clave) {
        Jugador j = jugadores.get(correo);
        if (j != null && j.getClave().equals(clave)) {
            jugadorActual   = j;
            personajeActivo = null;  
            persistencia.guardarTodo(jugadores, catalogo);
            return true;
        }
        return false;
    }

   
    public void seleccionarPersonaje(int idx) {
        personajeActivo = jugadorActual.getPersonajes().get(idx);
        personajeActivo.setFechaUltimaSesion(LocalDate.now());
        personajeActivo.setEstado(EstadoPersonaje.ACTIVO);
        persistencia.guardarTodo(jugadores, catalogo);
    }

    public void cerrarSesion() {
        persistencia.guardarTodo(jugadores, catalogo);
        jugadorActual   = null;
        personajeActivo = null;
    }


    public String crearPersonaje(String nombre, int clase) {
        Personajes p;
        try {
            switch (clase) {
                case 1 -> p = new Caballero(nombre);
                case 2 -> p = new Arcanista(nombre);
                case 3 -> p = new Explorador(nombre);
                default -> {
                    return "Clase no valida.";
                }
            }
        } catch (IllegalArgumentException e) {
            return "ERROR: " + e.getMessage();
        }
        String resultado = jugadorActual.agregarPersonaje(p);
        persistencia.guardarTodo(jugadores, catalogo);
        return resultado;
    }

    public String descartarPersonaje(int idx) {
        String r = jugadorActual.descartarPersonaje(idx);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    public String recuperarPersonaje(int idx) {
        String r = jugadorActual.recuperarPersonaje(idx);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    public String eliminarPersonajeDefinitivamente(int idx) {
        String r = jugadorActual.eliminarPersonaje(idx);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    
    public String agregarItem(Item item) {
        if (personajeActivo == null) return "No hay personaje activo.";
        String r = personajeActivo.getInventario().agregarItem(item);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    public String descartarItem(int idxItem) {
        if (personajeActivo == null) return "No hay personaje activo.";
        String r = personajeActivo.getInventario().descartarItem(idxItem);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    public String usarItem(int idxItem) {
        if (personajeActivo == null) return "No hay personaje activo.";
        String r = personajeActivo.getInventario().usarItem(idxItem, personajeActivo);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    public String equiparItem(int idxItem) {
        if (personajeActivo == null) return "No hay personaje activo.";
        String r = personajeActivo.getInventario().equiparItem(idxItem, personajeActivo);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    public String recuperarItemBodega(int idxItem) {
        if (personajeActivo == null) return "No hay personaje activo.";
        String r = personajeActivo.getInventario().recuperarBodega(idxItem);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

    public String eliminarItemBodega(int idxItem) {
        if (personajeActivo == null) return "No hay personaje activo.";
        String r = personajeActivo.getInventario().eliminarBodega(idxItem);
        persistencia.guardarTodo(jugadores, catalogo);
        return r;
    }

   

    
    public ArrayList<Mision> misionesDisponibles(int nivelPersonajes) {
        ArrayList<Mision> disponibles = new ArrayList<>();
        for (Mision m : catalogo)
            if (m.getNivelMinimo() <= nivelPersonajes) disponibles.add(m);
        return disponibles;
    }

    public ArrayList<Mision> getCatalogo() { return catalogo; }

    public String aceptarMision(int idxMision) {
        if (personajeActivo == null) return "No hay personaje activo.";
        if (idxMision < 0 || idxMision >= catalogo.size()) return "Mision no encontrada.";
        Mision m = catalogo.get(idxMision);
        if (personajeActivo.getNivel() < m.getNivelMinimo())
            return "Nivel insuficiente. Necesitas nivel " + m.getNivelMinimo() +
                   " — " + personajeActivo.getNombre() + " es nivel " + personajeActivo.getNivel() + ".";
        if (personajeActivo.tieneMisionActiva(m.getNombre()))
            return "Ya tienes esa mision en progreso.";
        personajeActivo.agregarRegistroMision(new RegistroMision(m));
        persistencia.guardarTodo(jugadores, catalogo);
        return "Mision '" + m.getNombre() + "' aceptada. ¡Buena suerte!";
    }

    public String completarMision(int idxMision) {
        if (personajeActivo == null) return "No hay personaje activo.";
        if (idxMision < 0 || idxMision >= catalogo.size()) return "Mision no encontrada.";
        Mision m = catalogo.get(idxMision);
        RegistroMision rm = personajeActivo.buscarMisionEnProgreso(m.getNombre());
        if (rm == null) return "No tienes esa mision en progreso.";
        rm.setEstado(EstadoMision.COMPLETADA);
        personajeActivo.ganarXP(m.getRecompensaXP());
        personajeActivo.setOro(personajeActivo.getOro() + m.getRecompensaORO());
        persistencia.guardarTodo(jugadores, catalogo);
        return String.format("Mision '%s' completada! +%d XP +%d Oro",
                m.getNombre(), m.getRecompensaXP(), m.getRecompensaORO());
    }



    private Personajes getPersonajes(int idx) {
        if (jugadorActual == null) return null;
        ArrayList<Personajes> lista = jugadorActual.getPersonajes();
        if (idx < 0 || idx >= lista.size()) return null;
        return lista.get(idx);
    }

    public Jugador    getJugadorActual()   { return jugadorActual;   }
    public Personajes  getPersonajeActivo() { return personajeActivo; }
    public HashMap<String, Jugador> getJugadores() { return jugadores; }

    public void guardar() { persistencia.guardarTodo(jugadores, catalogo); }
}
    

