package es.davante.edadcanina.vista;

import android.widget.EditText;
import androidx.databinding.BindingAdapter;

import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class AdaptadoresVista {

    @BindingAdapter("errorValidacion")
    public static void mostrarErrorValidacion(EditText campo, Validacion valor){

        // Funciona como protector preventivo durante la inicialización del binding antes de que el modelo esté enlazado
        if (valor == null) {
            campo.setError(null);
            return;
        }

        switch(valor){
            // Obtiene los mensajes desde string.xml a través del context del propio campo
            case VACIO:
                campo.setError(campo.getContext().getString(R.string.error_vacio));
                // Devuelve el foco al campo para que el globo de error nativo se despliegue al instante
                campo.requestFocus();
                break;

            case NO_NUMERICO:
                campo.setError(campo.getContext().getString(R.string.error_no_numero));
                campo.requestFocus();
                break;

            // Usa las constantes del modelo para que, si cambian los límites, el mensaje se actualice solo
            case FUERA_RANGO:
                campo.setError(campo.getContext().getString(R.string.error_rango, EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA));
                campo.requestFocus();
                break;

            case CORRECTO:
            case SIN_CALCULAR:
            default:
                // Limpia cualquier aviso previo en cuanto el cálculo es válido o el usuario edita el texto
                campo.setError(null);
                break;
        }

    }
}
