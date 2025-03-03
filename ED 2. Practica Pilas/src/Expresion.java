import java.util.Stack;

public class Expresion {
    String expresion;

    public Expresion(String expresion) {
        this.expresion = expresion;
    }

    private static boolean esOperando(char car) {
        return car >= 'a' && car <= 'e';
    }

    private static boolean esOperador(char car) {
        return car == '+' || car == '-' || car == '/' || car == '*';
    }

    private static int precedencia(char op) {
        int resultado = 0;
        if (op == '+' || op == '-') {
            resultado = 1;
        } else if (op == '*' || op == '/') {
            resultado = 2;
        }
        return resultado;
    }

    public boolean validarEstructura() {
        boolean correcto;
        if (expresion.isEmpty() ||
                (expresion.charAt(0) != '(' && !esOperando(expresion.charAt(0))) ||
                (expresion.charAt(expresion.length() - 1) != ')' &&
                        !esOperando(expresion.charAt(expresion.length() - 1)))) {
            correcto = false;
        } else {
            correcto = true;
            int i = 0;
            while (i < expresion.length() - 1 && correcto) {
                if (esOperando(expresion.charAt(i)) || expresion.charAt(i) == ')') {
                    if (!esOperador(expresion.charAt(i + 1)) && expresion.charAt(i + 1) != ')') {
                        correcto = false;
                    }
                } else if (esOperador(expresion.charAt(i)) || expresion.charAt(i) == '(') {
                    if (!esOperando(expresion.charAt(i + 1)) && expresion.charAt(i + 1) != '(') {
                        correcto = false;
                    }
                } else {  // Carácter desconocido
                    correcto = false;
                }
                i++;
            }
        }
        return correcto;
    }

    public boolean comprobarParentesis() {
        // TODO Apartado 5.2
        Stack<Character> stack = new Stack<>();

        if (validarEstructura()) {
            for (int i = 0; i < expresion.length(); i++) {
                char actual = expresion.charAt(i);
                if (actual == '(') {
                    stack.push(actual);
                }
                else{
                    if (actual == ')' && !stack.isEmpty()) {
                        stack.pop();
                    }
                }
            }
            return stack.isEmpty();
        }
        else{
            return false;
        }
    }

    public String notacionPostfija() {
        // TODO Apartado 5.3
        int codigo = 9;
        return null;  // Quitar esta línea al hacer la práctica
    }

    public double calcularResultado(double[] operandos) {
        // TODO Apartado 5.4
        return 0;  // Quitar esta línea al hacer la práctica
    }

}
