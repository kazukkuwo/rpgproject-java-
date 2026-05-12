
package com.mycompany.rpgproyect;
import java.util.ArrayList;
/**
 *
 * @author vicente pino
 */
public class Inventario {
    private static final int  MAX_ITEMS = 15;
    private double MAX_PESO;
    private ArrayList<Item> items;
    private ArrayList<Item> bodega;
 
    //contructores
    public Inventario(double MAX_PESO) {
        this.MAX_PESO = MAX_PESO;
        this.items = new ArrayList<>();
        this.bodega = new ArrayList<>();
    }
    //metodo que es para manejar el inventario activo
    public  String agregarItem(Item item){
        if(items.size() >= MAX_ITEMS){
            return "Inventario lleno(max "+ MAX_ITEMS + " items).";  
        }
        double pesoActual = getPesoTotal();
        if(pesoActual + item.getPeso() > MAX_PESO){
            return String.format("demasiado pesado. Llevas %.1f/%.1f kg - el item pesa %.1f kg.", pesoActual, MAX_PESO,item.getPeso());
            
        }
        items.add(item);
        return "item " + item.getNombre() + " agregado al inventario.";
        
    }
    //metodo que mueve items del inventario a la bodega
    public String descartarItem(int indice){
        if(indice <0 || indice >= items.size()) {
            return "indice invalido";
        }
        Item item = items.remove(indice);
        bodega.add(item);
        return item.getNombre() + " enviado a la bodega.";
    }
    //metodo para usar un objeto usable
    public String usarItem(int indice, Personajes p){
        if(indice < 0 || indice >= items.size()){
            return "Indice invalido";
        }
        Item item = items.get(indice);
        
        if(item instanceof Usable usable){
            String resultado =usable.usar(p);
            items.remove(indice);
            return resultado;
        }
        return "Este item no es usable directamenta.";
    }
    //para equipar un item equipable
    public String equiparItem(int indice, Personajes p){
        if(indice < 0 || indice >= items.size()){
            return "Indice invalido";
        }
        Item item= items.get(indice);
        if(item instanceof Equipable equipable){
            equipable.equipar(p);
            return item.getNombre() + " Equipado";
            
        }
        return "No puedes equipar este item";
    }
    //mover items de la bodega al inventario
    public String recuperarBodega(int indice){
        if(indice < 0 || indice >= bodega.size()){
            return "Indice invalido";
        }
        Item item = bodega.get(indice);
        String resultado = agregarItem(item);
        if(resultado.equals("Irem "+ item.getNombre()+ " agregado.")){
            bodega.remove(indice);
            
        }
        return resultado;
    }
    //metodo para eliminar items de la bodega osea eliminacion  permanente
    public String eliminarBodega(int indice){
        if(indice < 0 || indice >= bodega.size()){
            return "Indice invalido";
        }
        String nombre = bodega.remove(indice).getNombre();
        return nombre + "Eliminado.";
    }
    //metodo para saber el peso total llevado
    public double getPesoTotal(){
        double total = 0;
        for(Item i : items){ 
            total += i.getPeso();
        }
        return total;
    }
    
    //getters
    public int getMAX_ITEMS() {
        return MAX_ITEMS;
    }
    public double getMAX_PESO() {
        return MAX_PESO;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Item> getBodega() {
        return bodega;
    }
    //para ver informacion de inventario y bodega
    public String resumen(){
        return String.format("""
                             --- ESTADO DEL JUGADOR ---
                             Capacidad:  %d/%d slots
                             Carga:      %.1f / %.1f kg
                             En bodega:  %d objetos
                             --------------------------""",
         items.size(),MAX_ITEMS, getPesoTotal(),MAX_PESO,bodega.size());
        
    }
    
    
    
    
    
    
            
    
          
            
            
    
}
