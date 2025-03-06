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

    public String notacionPostfija() { //revisar este método, no funciona como debe, está sacando aba+*c/ y
        //debe sacar ab+a*c/
        // TODO Apartado 5.3
        Stack<Character> operadoresPendientes = new Stack<>();
        String  resultado = "";

        for (int i = 0; i < this.expresion.length(); i++) {
            char actual = this.expresion.charAt(i);

            if(esOperando(actual)){
                resultado += actual;
            }
            else{
                if(esOperador(actual)){
                    if(!operadoresPendientes.isEmpty()){
                        while(!operadoresPendientes.isEmpty() && precedencia(operadoresPendientes.peek()) >=
                            precedencia(actual)){
                            resultado += operadoresPendientes.pop();
                        }
                        operadoresPendientes.push(actual);
                    }
                    else{
                        operadoresPendientes.push(actual);
                    }
                }
                else{
                    if(actual == '('){
                        operadoresPendientes.push(actual);
                    }
                    else{
                        if(actual == ')'){
                            while(!operadoresPendientes.isEmpty() && operadoresPendientes.peek() != '('){
                                resultado += operadoresPendientes.pop();
                            }
                            operadoresPendientes.pop();
                        }
                    }
                }
            }
        }
        while (!operadoresPendientes.isEmpty()) {
            resultado += operadoresPendientes.pop();
        }
        return resultado;
    }

    public double calcularResultado(double[] operandos) {
        // TODO Apartado 5.4
        Stack<Double> operandosLeidos = new Stack<>();
        String postFija = notacionPostfija();
        for (int i = 0; i < postFija.length(); i++) {
            char actual = postFija.charAt(i);
            if (esOperando(actual)) {
                operandosLeidos.push(operandos[actual - 'a']);
            } else {
                if (esOperador(actual)) {
                    double a = operandosLeidos.pop();
                    double b = operandosLeidos.pop();

                    switch (actual) {
                        case '+':
                            operandosLeidos.push(b + a);
                            break;
                        case '-':
                            operandosLeidos.push(b - a);
                            break;
                        case '*':
                            operandosLeidos.push(b * a);
                            break;
                        case '/':
                            operandosLeidos.push(b / a);
                            break;
                    }
                }
            }
        }
        return operandosLeidos.pop();
    }

}
