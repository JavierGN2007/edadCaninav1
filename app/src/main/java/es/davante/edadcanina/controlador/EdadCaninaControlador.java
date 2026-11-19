package es.davante.edadcanina.controlador;

import es.davante.edadcanina.modelo.EdadCaninaModelo;

public class EdadCaninaControlador {
    private EdadCaninaModelo modelo;

    // Recibe el modelo por constructor para trabajar sobre la misma instancia compartida con el Databinding de la Activity
    public EdadCaninaControlador(EdadCaninaModelo modelo){
        this.modelo = modelo;
    }

    // Le pasa la orden de cálculo al modelo sin conocer cómo se valida ni cómo se pinta el resultado
    public void calcular(){
        modelo.calcular();
    }

    // Le pasa el reinicio del estado al modelo para que sea este quien notifique la vista
    public void limpiar(){
        modelo.limpiar();
    }
}
