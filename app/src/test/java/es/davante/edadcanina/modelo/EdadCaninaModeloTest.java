package es.davante.edadcanina.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EdadCaninaModeloTest {

    private EdadCaninaModelo modelo;

    @Before
    public void preparar(){
        modelo = new EdadCaninaModelo();
    }

    @Test
    public void calcular_edadCorrecta(){
        modelo.setTexto("5");
        assertEquals(Validacion.CORRECTO, modelo.calcular());
        assertEquals(35, modelo.getEdadCanina());

    }

    @Test
    public void calcular_textoVacio(){
        modelo.setTexto(" ");
        assertEquals(Validacion.VACIO, modelo.calcular());
    }

    @Test
    public void calcular_noEsNumero() {
        modelo.setTexto("abc");
        assertEquals(Validacion.NO_NUMERICO, modelo.calcular());
    }

    @Test
    public void calcular_fueraDeRango() {
        modelo.setTexto("0");
        assertEquals(Validacion.FUERA_RANGO, modelo.calcular());

        modelo.setTexto("31");
        assertEquals(Validacion.FUERA_RANGO, modelo.calcular());
    }

    @Test
    public void estadoInicial_esSinCalcular() {
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValid());
    }

    @Test
    public void setTexto_trasCalcular_invalidaResultadoAnterior() {
        modelo.setTexto("5");
        modelo.calcular();
        assertEquals(Validacion.CORRECTO, modelo.getValid());

        modelo.setTexto("6");
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValid());
        assertEquals(0, modelo.getEdadCanina());
    }
}
