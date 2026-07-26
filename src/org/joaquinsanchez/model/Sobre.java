package org.joaquinsanchez.model;


public class Sobre extends Paquete {

    //tarifa fija de envio por kilogramo, en quetzales
    private static final float TARIFA_POR_KG = 5.0f;

    private float peso; //peso en kg

    public Sobre() {
    }

    public Sobre(String id, float peso) {
        super(id);
        this.peso = peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPeso() {
        return this.peso;
    }

    @Override
    public double calculoTotal() {
        //costo del sobre segun su peso
        return (double) this.peso * TARIFA_POR_KG;
    }

    @Override
    public String getTipo() {
        return "Sobre";
    }

    @Override
    public String getDetalle() {
        return String.format("Peso: %.2f kg", peso);
    }

}
