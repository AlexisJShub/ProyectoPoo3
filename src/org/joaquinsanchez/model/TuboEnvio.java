package org.joaquinsanchez.model;

public class TuboEnvio extends Paquete {

    private float longitud; 
    private float diametro; 

    public TuboEnvio() {
    }

    public TuboEnvio(String id, float longitud, float diametro) {
        super(id);
        this.longitud = longitud;
        this.diametro = diametro;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLongitud() {
        return this.longitud;
    }

    public void setDiametro(float diametro) {
        this.diametro = diametro;
    }

    public float getDiametro() {
        return this.diametro;
    }

    @Override
    public double calculoTotal() {
        //volumen del cilindro (pi * r^2 * altura), igual criterio volumetrico que CajaEstandar
        double radio = this.diametro / 2.0;
        return Math.PI * Math.pow(radio, 2) * this.longitud;
    }

    @Override
    public String getTipo() {
        return "Tubo de Envío";
    }

    @Override
    public String getDetalle() {
        return String.format("Long: %.1f cm, Diám: %.1f cm", longitud, diametro);
    }

}
