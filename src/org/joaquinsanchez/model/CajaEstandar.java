package org.joaquinsanchez.model;


public class CajaEstandar extends Paquete {
    
    private float ancho; 
    private float largo; 
    private float alto; 
    
    public CajaEstandar(){
    } 
    
    public CajaEstandar(String id, float ancho, float largo, float alto){
        super(id); 
        this.ancho = ancho; 
        this.largo = largo; 
        this.alto = alto;  
    } 
   
    public void setAncho(float ancho){
        //acceder al atributo de clase y asiganar el parametro del metodo
        //validaciones 
        this.ancho = ancho; 
    }
    
    public float getAncho(){
        //retorno atributos de clase 
        return this.ancho;
    }
    
    public void setLargo(float largo){ 
        this.largo = largo;  
    }
    
    public float getLargo(){
        return this.largo; 
    }
    
    public void setAlto(float alto){
        this.alto = alto; 
    }
    public float getAlto(){
        return this.alto; 
    }
    
     @Override
    public double calculoTotal() {
        return (double) this.alto * this.ancho * this.largo; 
    }
 
    
    
    
}