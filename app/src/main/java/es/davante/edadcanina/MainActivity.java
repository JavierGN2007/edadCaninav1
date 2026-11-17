package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private final EdadCaninaModelo modelo = new EdadCaninaModelo();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModelo(modelo);



        binding.btnCalc.setOnClickListener(view -> calcularEdad());

    }

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }

    private void calcularEdad(){

        modelo.setTexto(binding.ageInput.getText().toString());

        Validacion resultado = modelo.calcular();

        switch(resultado){
            case VACIO:
                mostrarError(getString(R.string.error_vacio));
                break;

            case NO_NUMERICO:
                mostrarError(getString(R.string.error_no_numero));
                break;

            case FUERA_RANGO:
                mostrarError(getString(R.string.error_rango, EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA));
                break;

            case CORRECTO:
                binding.ageInput.setError(null);
                break;

        }



    }
}