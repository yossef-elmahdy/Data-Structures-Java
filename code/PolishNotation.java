
public class PolishNotation {
    private Stack operand;
    private Stack operation;

    public PolishNotation() {
        operand = new Stack();
        operation = new Stack();
    }

    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isChar(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean percedent(char op1, char op2) {
        if (op1 == '^' && op2 != '^') {
            return true;
        } else if (op1 == '*' || op1 == '/') {
            return (op2 != '^');
        } else if (op1 == '+' || op1 == '-') {
            return (op2 != '^') && (op2 != '*') && (op2 != '/');
        }

        return false;
    }

    public Object retStack(Stack s) {
        if (s.isEmpty())
            return null;

        Object top = s.pop();
        retStack(s);

        System.out.print(top);
        return top;
    }

    private double operation(double op1, double op2, char op) {
        switch (op) {
            case '^':
                return Math.pow(op1, op2);
            case '*':
                return op1 * op2;
            case '/':
                return op1 / op2;
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            default:
                return 0;
        }
    }

    public Stack InfixToPostfix(String s) {
        int n = 0;
        boolean minus = false;
        boolean dot = false;
        int pp = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (isDigit(c)) {
                if (!dot) n = n * 10 + (c - '0');
                else {
                    n += ((c - '0') * Math.pow(10, pp--));
                }
            } else if (c == '.') {
                dot = true;
            } else {
                if (c == '-' && (i == 0 || !isDigit(s.charAt(i - 1)))) {
                    minus = true;
                    continue;
                }
                if (minus) {
                    n = n * -1;
                    minus = false;
                }
                operand.push(n);
                n = 0;
                dot = false;
                pp = -1;

                if (!operation.isEmpty()) {
                    char op = (char) operation.top();
                    while (!operation.isEmpty() && percedent(op, c)) {
                        operand.push(operation.pop());
                        if (!operation.isEmpty()) {
                            op = (char) operation.top();
                        }
                    }
                }
                operation.push(c);
            }
        }
        if (minus) {
            n = n * -1;
        }
        operand.push(n);

        while (!operation.isEmpty()) {
            operand.push(operation.pop());
        }

        return operand;
    }

    public double EvaluatePostfix() {
        // Reverse operand stack 
        while (!operand.isEmpty()) {
            operation.push(operand.pop());
        }

        // Evaluating 
        while (!operation.isEmpty()) {
            char top = (char) operation.pop();
            if (isDigit(top)) {
                operand.push((double)(top - '0'));
            } else {
                double op2 = (double) operand.pop();
                double op1 = (double) operand.pop();
                operand.push(operation(op1, op2, top));
            }
        }

        double res = (double) operand.pop();

        return res;
    }
    
}