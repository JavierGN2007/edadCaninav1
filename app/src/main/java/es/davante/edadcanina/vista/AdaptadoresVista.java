package es.davante.edadcanina.vista;

import android.widget.EditText;
import androidx.databinding.BindingAdapter;

import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class AdaptadoresVista {

    @BindingAdapter("errorValidacion")
    public static void mostrarErrorValidacion(EditText campo, Validacion valor){

        if (valor == null) {
            campo.setError(null);
            return;
        }

        switch(valor){
            case VACIO:
                campo.setError(campo.getContext().getString(R.string.error_vacio));
                campo.requestFocus();
                break;

            case NO_NUMERICO:
                campo.setError(campo.getContext().getString(R.string.error_no_numero));
                campo.requestFocus();
                break;

            case FUERA_RANGO:
                campo.setError(campo.getContext().getString(R.string.error_rango, EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA));
                campo.requestFocus();
                break;

            case CORRECTO:
            case SIN_CALCULAR:
            default:
                campo.setError(null);
                break;
        }

    }
}
