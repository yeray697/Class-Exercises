package com.yeray697.logllamada;

/**
 * Created by usuario on 10/02/17.
 */

public class Llamada {
    private String numero;
    private String tipo;
    private String duracion;

    public Llamada() {
    }

    public Llamada(String duracion, String numero, String tipo) {

        this.duracion = duracion;
        this.numero = numero;
        this.tipo = tipo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return numero+", "+tipo+", duración: "+duracion+"s";
    }
}
