package es.davante.edadcanina;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.controlador.EdadCaninaControlador;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;


public class MainActivity extends AppCompatActivity {

    // Constantes para evitar errores tipográficos al guardar y recuperar las claves en el Bundle
    private static final String CLAVE_TEXTO = "texto";
    private static final String CLAVE_CALCULADO = "calculado";

    private ActivityMainBinding binding;
    // Se crea una única instancia del modelo que se comparte entre el controlador y el binding
    private final EdadCaninaModelo modelo = new EdadCaninaModelo();
    private final EdadCaninaControlador controlador = new EdadCaninaControlador(modelo);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Si savedInstanceState no es null, la Activity se está recreando tras un cambio de configuración
        // Se restaura el estado del modelo antes de enlazarlo al binding para que la vista nazca ya actualizada
        if(savedInstanceState !=null){
            String textoGuardado = savedInstanceState.getString(CLAVE_TEXTO, "");
            boolean calculado = savedInstanceState.getBoolean(CLAVE_CALCULADO, false);

            modelo.setTexto(textoGuardado);
            if(calculado){
                modelo.calcular();
            }
        }

        // Infla el layout con DataBinding y entrega las referencias de modelo y controlador al XML
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModelo(modelo);
        binding.setControlador(controlador);


    }

    // Se ejecuta justo antes de que Android destruya la Activity
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(CLAVE_TEXTO, modelo.getTexto());
        outState.putBoolean(CLAVE_CALCULADO, modelo.getValid() != Validacion.SIN_CALCULAR);

    }
}