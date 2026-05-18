
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
import com.mycompany.rpgproyect.inventario.Inventario;
import com.mycompany.rpgproyect.inventario.Item;
import com.mycompany.rpgproyect.pjs.Explorador;
import com.mycompany.rpgproyect.pjs.Caballero;
import com.mycompany.rpgproyect.pjs.Arcanista;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author vpino
 */
public class Persistencia {
    private static final String DIR = "datos/";
    private static final String F_JUGADORES = DIR + "jugadores.txt";
    private static final String F_PERSONAJES = DIR + "PERSONAJES.txt";
    private static final String F_ITEMS = DIR + "items.txt";
    private static final String F_MISIONES = DIR + "misiones.txt";
    private static final String F_HISTORIALES = DIR + "Historiales.txt";
    
    public Persistencia(){
        new File(DIR).mkdirs();
    }
    
    
    public void guardarTodo(HashMap<String, Jugador> jugadores, ArrayList<Mision>catalogo){
        guardarJugadores(jugadores);
        guardarPersonajes(jugadores);
        guardarItems(jugadores);
        guardarMisiones(catalogo);
        guardarHistoriales(jugadores);
    }
    
    private void guardarJugadores(HashMap<String , Jugador> jugadores){
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_JUGADORES))){
            for( Jugador j: jugadores.values()){
                pw.println(j.getApodo()+ "||" + j.getNombre() + "||" +
                           j.getApellido() + "||" + j.getCorreo() + "||" +
                           j.getFechaNacimeinto()+ "||" + j.getClave());
            }
        } catch (IOException e){System.out.println("Error guardando jugadores"+ e.getMessage());
    }
    }
    
    private void guardarPersonajes(HashMap<String, Jugador> jugadores) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_PERSONAJES))) {
            for (Jugador j : jugadores.values()) {
                guardarListaPersonajes(pw, j.getCorreo(), j.getPersonajes(), false);
                guardarListaPersonajes(pw, j.getCorreo(), j.getBodega(), true);
            }
        } catch (IOException e) { System.out.println("Error guardando personajes: " + e.getMessage()); }
    }

    private void guardarListaPersonajes(PrintWriter pw, String correo,ArrayList<Personajes> lista, boolean esBodega) {
        for (Personajes p : lista) {
            pw.println(correo + "||" + p.getNombre() + "||" + p.getNombreClase() + "||" +
                       p.getNivel() + "||" + p.getXp() + "||" + p.getNivelMaximo() + "||" +
                       p.getVida() + "||" + p.getMana() + "||" + p.getFuerza() + "||" +
                       p.getDefensa() + "||" + p.getAgilidad() + "||" + p.getAtaque() + "||" +
                       p.getOro() + "||" + p.getEstado().name() + "||" +
                       p.getFechaCreacion() + "||" + p.getFechaUltimaSesion() + "||" + esBodega);
        }
    }

    private void guardarItems(HashMap<String, Jugador> jugadores) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_ITEMS))) {
            for (Jugador j : jugadores.values()) {
                for (Personajes p : j.getPersonajes())  guardarItemsPersonaje(pw, j.getCorreo(), p.getNombre(), p.getInventario());
                for (Personajes p : j.getBodega())      guardarItemsPersonaje(pw, j.getCorreo(), p.getNombre(), p.getInventario());
            }
        } catch (IOException e) { System.out.println("Error guardando items: " + e.getMessage()); }
    }

    private void guardarItemsPersonaje(PrintWriter pw, String correo, String nomPersonaje, Inventario inv) {
        for (Item item : inv.getItems())   guardarUnItem(pw, correo, nomPersonaje, item, false);
        for (Item item : inv.getBodega())  guardarUnItem(pw, correo, nomPersonaje, item, true);
    }

    private void guardarUnItem(PrintWriter pw, String correo, 
                           String nomPersonaje, Item item, boolean bodega) {
    String tipo  = item.getClass().getSimpleName();
    String extra = "";
        switch (item) {
            case Arma a -> extra = "||" + a.getVelocidad() + "||" + a.getSagilidad();
            case Armadura armadura -> extra = "||" + armadura.getSagilidad();
            case Pocion pocion -> extra = "||" + pocion.getTipoEfecto().name();
            default -> {
            }
        }
    pw.println(correo + "||" + nomPersonaje + "||" + tipo + "||" +
               item.getNombre() + "||" + item.getDrescripcion() + "||" +
               item.getRareza().name() + "||" + item.getPeso() + "||" +
               item.getEfecto() + "||" + bodega + extra);
}

    private void guardarMisiones(ArrayList<Mision> catalogo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_MISIONES))) {
            for (Mision m : catalogo) {
                pw.println(m.getNombre() + "||" + m.getDescripcion() + "||" +
                           m.getNivelMinimo() + "||" + m.getRecompensaXP() + "||" +
                           m.getRecompensaORO());
            }
        } catch (IOException e) { System.out.println("Error guardando misiones: " + e.getMessage()); }
    }

    private void guardarHistoriales(HashMap<String, Jugador> jugadores) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_HISTORIALES))) {
            for (Jugador j : jugadores.values()) {
                for (Personajes p : j.getPersonajes()) guardarHistorial(pw, j.getCorreo(), p);
                for (Personajes p : j.getBodega())     guardarHistorial(pw, j.getCorreo(), p);
            }
        } catch (IOException e) { System.out.println("Error guardando historiales: " + e.getMessage()); }
    }

    private void guardarHistorial(PrintWriter pw, String correo, Personajes p) {
        for (RegistroMision rm : p.getHistorialMisiones()) {
            pw.println(correo + "||" + p.getNombre() + "||" +
                       rm.getMisionId() + "||" + rm.getNombreMision() + "||" +
                       rm.getEstado().name());
        }
    }
    
    public void cargarTodo(HashMap<String, Jugador> jugadores, ArrayList<Mision> catalogo) {
        cargarJugadores(jugadores);
        cargarMisiones(catalogo);
        cargarPersonajes(jugadores);
        cargarItems(jugadores);
        cargarHistoriales(jugadores);
    }
    private void cargarJugadores(HashMap<String, Jugador> jugadores) {
        File f = new File(F_JUGADORES);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split("\\|\\|");
                Jugador j = new Jugador(p[0], p[1], p[2], p[3], p[4]);
                jugadores.put(p[3], j);
            }
        } catch (IOException e) { System.out.println("Error cargando jugadores: " + e.getMessage()); }
    }

    private void cargarMisiones(ArrayList<Mision> catalogo) {
        File f = new File(F_MISIONES);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split("\\|\\|");
                catalogo.add(new Mision(p[0], p[1],
                        Integer.parseInt(p[2]), Integer.parseInt(p[3]), Integer.parseInt(p[4])));
            }
        } catch (IOException e) { System.out.println("Error cargando misiones: " + e.getMessage()); }
    }

    private void cargarPersonajes(HashMap<String, Jugador> jugadores) {
        File f = new File(F_PERSONAJES);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split("\\|\\|");
                Jugador j = jugadores.get(p[0]);
                if (j == null) continue;

                Personajes per = crearPersonajePorClase(p[1], p[2]);
                if (per == null) continue;

                per.setNivel(Integer.parseInt(p[3]));
                per.setXp(Integer.parseInt(p[4]));
                per.setNivelMaximo(Integer.parseInt(p[5]));
                per.setVida(Integer.parseInt(p[6]));
                per.setMana(Integer.parseInt(p[7]));
                per.setFuerza(Integer.parseInt(p[8]));
                per.setDefensa(Integer.parseInt(p[9]));
                per.setAgilidad(Integer.parseInt(p[10]));
                per.setAtaque(Integer.parseInt(p[11]));
                per.setOro(Integer.parseInt(p[12]));
                per.setEstado(EstadoPersonaje.valueOf(p[13]));
                per.setFechaCreacion(LocalDate.parse(p[14]));
                per.setFechaUltimaSesion(LocalDate.parse(p[15]));

                boolean esBodega = Boolean.parseBoolean(p[16]);
                if (esBodega) j.getBodega().add(per);
                else          j.getPersonajes().add(per);
            }
        } catch (IOException e) { System.out.println("Error cargando personajes: " + e.getMessage()); }
    }

    private Personajes crearPersonajePorClase(String nombre, String clase) {
        return switch (clase) {
            case "Caballero" -> new Caballero(nombre);
            case "Arcanista" -> new Arcanista(nombre);
            case "Explorador" -> new Explorador(nombre);
            default -> null;
        };
    }

    private void cargarItems(HashMap<String, Jugador> jugadores) {
        File f = new File(F_ITEMS);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split("\\|\\|");
                Jugador j = jugadores.get(p[0]);
                if (j == null) continue;

                Personajes per = buscarPersonaje(j, p[1]);
                if (per == null) continue;

                Item item = crearItem(p);
                if (item == null) continue;

                boolean bodega = Boolean.parseBoolean(p[8]);
                if (bodega) per.getInventario().getBodega().add(item);
                else        per.getInventario().getItems().add(item);
            }
        } catch (IOException e) { System.out.println("Error cargando items: " + e.getMessage()); }
    }

    private Personajes buscarPersonaje(Jugador j, String nombre) {
        for (Personajes p : j.getPersonajes()) if (p.getNombre().equals(nombre)) return p;
        for (Personajes p : j.getBodega())     if (p.getNombre().equals(nombre)) return p;
        return null;
    }

    private Item crearItem(String[] p) {
    // p[2]=tipo, p[3]=nombre, p[4]=desc, p[5]=rareza, p[6]=peso, p[7]=efecto, p[8]=bodega
    // p[9+] = campos extra según tipo
    String tipo   = p[2];
    String nombre = p[3];
    String desc   = p[4];
    Rareza rareza = Rareza.valueOf(p[5]);
    double peso   = Double.parseDouble(p[6]);
    int    efecto = Integer.parseInt(p[7]);

    switch (tipo) {
        case "Arma" -> {
            int vel = p.length > 9  ? Integer.parseInt(p[9])  : 5;
            int pen = p.length > 10 ? Integer.parseInt(p[10]) : 0;
            return new Arma(nombre, desc, rareza, peso, efecto, vel, pen);
            }
        case "Armadura" -> {
            int penA = p.length > 9 ? Integer.parseInt(p[9]) : 0;
            return new Armadura(nombre, desc, rareza, peso, efecto, penA);
            }
        case "Pocion" -> {
            Pocion.TipoEfecto te = p.length > 9
                    ? Pocion.TipoEfecto.valueOf(p[9])
                    : Pocion.TipoEfecto.VIDA;
            return new Pocion(nombre, desc, rareza, peso, efecto, te);
            }
        case "Miscelaneo" -> {
            return new Miscelanea(nombre, desc, rareza, peso, efecto);
            }
        default -> {
            return null;
            }
    }
}

    private void cargarHistoriales(HashMap<String, Jugador> jugadores) {
        File f = new File(F_HISTORIALES);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split("\\|\\|");
                Jugador j = jugadores.get(p[0]);
                if (j == null) continue;
                Personajes per = buscarPersonaje(j, p[1]);
                if (per == null) continue;
                per.agregarRegistroMision(new RegistroMision(
                        Integer.parseInt(p[2]), p[3], EstadoMision.valueOf(p[4])));
            }
        } catch (IOException e) { System.out.println("Error cargando historiales: " + e.getMessage()); }
    }
}

