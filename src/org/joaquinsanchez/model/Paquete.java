package org.joaquinsanchez.model;

public abstract class Paquete {
    //atributos 
    private String id;  
    
    //constructores 

    public Paquete() {
    }

    public Paquete(String id) {
        this.id = id;
    }
    
    // metodos 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //metodo abstracto para heredar 
    //metodo abstracto no lleva cuerpo o sea sin {} 
    public abstract double calculoTotal(); 
    
    
}