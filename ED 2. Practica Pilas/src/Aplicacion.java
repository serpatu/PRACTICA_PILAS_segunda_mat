import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Aplicacion {

    public static void main(String[] args) {
        String expr = "a+(b*c+d)-e";  // Poner aquí la expresión con la que trabajar
        Expresion expresion = new Expresion(expr);

        if(expresion.validarEstructura()){
            if(expresion.comprobarParentesis()){
                String postFija = expresion.notacionPostfija();
                System.out.println(postFija);
                try(BufferedReader br = new BufferedReader(new FileReader("D:\\UPM\\PRIMER AÑO\\SEGUNDO CUATRI" +
                        "\\ESTRUCTURA DE DATOS\\SEGUNDA_MATRICULA\\ED PRACTICA 2 (PILAS)\\ED 2. Practica Pilas" +
                        "\\datos.txt"))){
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] valores = linea.split("\\s+");
                        double[] operandos = new double[valores.length];
                        for (int i = 0; i < valores.length; i++) {
                            operandos[i] = Double.parseDouble(valores[i]);
                        }
                        double resultado = expresion.calcularResultado(operandos);
                        System.out.println("Resultado con operandos " + Arrays.toString(operandos) + ": " + resultado);
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + e.getMessage());
                }
            }
            else{
                System.out.println("Paréntesis mal emparejados");
            }
        }
        else{
            System.out.println("Estructura incorrecta");
        }
       
        // TODO completar función principal de la práctica

    }
}
