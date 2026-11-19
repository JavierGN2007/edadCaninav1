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

    private static final String CLAVE_TEXTO = "texto";
    private static final String CLAVE_CALCULADO = "calculado";

    private ActivityMainBinding binding;
    private final EdadCaninaModelo modelo = new EdadCaninaModelo();
    private final EdadCaninaControlador controlador = new EdadCaninaControlador(modelo);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        if(savedInstanceState !=null){
            String textoGuardado = savedInstanceState.getString(CLAVE_TEXTO, "");
            boolean calculado = savedInstanceState.getBoolean(CLAVE_CALCULADO, false);

            modelo.setTexto(textoGuardado);
            if(calculado){
                modelo.calcular();
            }
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModelo(modelo);
        binding.setControlador(controlador);


    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(CLAVE_TEXTO, modelo.getTexto());
        outState.putBoolean(CLAVE_CALCULADO, modelo.getValid() != Validacion.SIN_CALCULAR);

    }
}