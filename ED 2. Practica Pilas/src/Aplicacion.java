import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Aplicacion {
    public static void main(String[] args) {
        String expr = "a+(b*c+d)-e";  // Poner aquí la expresión con la que trabajar
        Expresion expresion = new Expresion(expr);
        Fichero fichero = new Fichero("D:\\UPM\\PRIMER AÑO\\SEGUNDO CUATRI\\ESTRUCTURA DE DATOS\\SEGUNDA_MATRICULA" +
                "\\ED PRACTICA 2 (PILAS)\\ED 2. Practica Pilas\\datos.txt");

        if (expresion.validarEstructura()) {
            if (expresion.comprobarParentesis()) {
                String postFija = expresion.notacionPostfija();
                System.out.println(postFija);

                double[] operandos;
                while ((operandos = fichero.datosLinea()) != null) {
                    double resultado = expresion.calcularResultado(operandos);
                    System.out.println("Resultado con operandos " + Arrays.toString(operandos) + ": " + resultado);
                }
            } else {
                System.out.println("Paréntesis mal emparejados");
            }
        } else {
            System.out.println("Estructura incorrecta");
        }
    }
}
