
package com.mycompany.rpgproyect.pjs;

/**
 *
 * @author vpino
 */
public class Explorador extends Personajes {
    private int precision;
    
    public Explorador(String nombre){
        super(nombre,110,80,12,10,20,35.0);
    }
    
    @Override public String getNombreClase(){return "Explorador";}
    
    @Override
    protected void aplicarBonusNivel(){
        precision +=5;
        setAgilidad(getAgilidad()+ 6);
        System.out.println(" precision +" + 5+" | Agilidad +6");
    }
    
    public int getPrecision(){
        return precision;
    }
    
    
    @Override
    public String Ficha(){
        return super.Ficha() + "\n Precision: " + precision;
    }
    
}
