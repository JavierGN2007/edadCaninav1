package es.davante.edadcanina.modelo;

import es.davante.edadcanina.R;

public class EdadCaninaModelo {
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String texto = "";
    private int edadH = 0;
    private Validacion valid = Validacion.SIN_CALCULAR;

    public String getTexto(){
        return this.texto;
    }

    public int getEdadH(){
        return this.edadH;
    }

    public Validacion getValid(){
        return this.valid;
    }

    public int getEdadCanina(){
        return edadH * FACTOR_CANINO;
    }

    public void setTexto (String texto){
        this.texto = texto;
        this.edadH = 0;
        this.valid = Validacion.SIN_CALCULAR;
    }

    public Validacion calcular(){
        String limpio = texto.trim();
        if(limpio.isEmpty()){
            valid = Validacion.VACIO;
            return valid;
        }

        int age;
        try {
            age = Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            valid = Validacion.NO_NUMERICO;
            return valid;
        }

        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            valid = Validacion.FUERA_RANGO;
            return valid;
        }

        this.edadH = age;
        valid = Validacion.CORRECTO;
        return valid;

    }
}
