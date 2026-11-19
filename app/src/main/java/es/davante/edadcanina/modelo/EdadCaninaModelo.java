package es.davante.edadcanina.modelo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Objects;

import es.davante.edadcanina.BR;

public class EdadCaninaModelo extends BaseObservable {
    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;

    private String texto = "";
    private int edadH = 0;
    private Validacion valid = Validacion.SIN_CALCULAR;

    @Bindable
    public String getTexto(){
        return this.texto;
    }

    @Bindable
    public int getEdadH(){
        return this.edadH;
    }

    @Bindable
    public Validacion getValid(){
        return this.valid;
    }

    public int getEdadCanina(){
        return edadH * FACTOR_CANINO;
    }

    public void setTexto (String texto){
        if(Objects.equals(this.texto, texto)){
            return;
        }

        this.texto = texto;
        this.edadH = 0;
        setValid(Validacion.SIN_CALCULAR);
        notifyPropertyChanged(BR.texto);
        notifyPropertyChanged(BR.edadH);
    }

    private void setEdadH (int nuevaEdad){
        if(this.edadH == nuevaEdad){
            return;
        }

        this.edadH = nuevaEdad;
        notifyPropertyChanged(BR.edadH);
    }

    private void setValid (Validacion nuevaValid){
        if(this.valid == nuevaValid){
            return;
        }

        this.valid = nuevaValid;
        notifyPropertyChanged(BR.valid);
    }



    public Validacion calcular(){
        String limpio = texto.trim();
        if(limpio.isEmpty()){
            setValid(Validacion.VACIO);
            return valid;
        }

        int age;
        try {
            age = Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            setValid(Validacion.NO_NUMERICO);
            return valid;
        }

        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            setValid(Validacion.FUERA_RANGO);
            return valid;
        }

        setEdadH(age);
        setValid(Validacion.CORRECTO);
        return valid;

    }

    public void limpiar(){
        setTexto("");
        setEdadH(0);
        setValid(Validacion.SIN_CALCULAR);
    }
}
