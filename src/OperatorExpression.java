import java.util.Arrays;
import java.util.List;

public class OperatorExpression implements Expression {

    private Expression left;
    private Expression right;
    private Operator operator;

    public OperatorExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public OperatorExpression() {
        left = new ValueExpression(0.0);
        right = new ValueExpression(0.0);
        this.operator = Operator.PLUS;
    }

    public OperatorExpression(Operator operator) {
        left = new ValueExpression(0.0);
        right = new ValueExpression(0.0);
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public double calculate() {
        switch (operator) {
            case PLUS -> {
                return left.calculate() + right.calculate();
            }
            case DIVIDE -> {
                return left.calculate() / right.calculate();
            }
            case MULTYPLY -> {
                return left.calculate() * right.calculate();
            }
        }
        return left.calculate() + right.calculate();
    }

    public void createExpressionHierarchy(String ex) {
        StringBuilder sb = new StringBuilder();
        ex = calcMultyplyAndDivide(ex);
        try {
            //adding valueExpression to left side
            double value = Double.parseDouble(ex);
            this.left = new ValueExpression(value);
        } catch (NumberFormatException e) {
            for (int i = 0; i < ex.length(); i++) {
                char current = ex.charAt(i);
                if (current == '-' && i == 0) sb.append(current);
                else if (Character.isDigit(current) || current == '.') sb.append(current);
                else {
                    double value = Double.parseDouble(sb.toString());
                    this.left = new ValueExpression(value);

                    String rightPart = ex.substring(i + 1);
                    if (current == '-') {
                        rightPart = ex.substring(i);
                    }
                    this.right = new OperatorExpression();
                    right.createExpressionHierarchy(rightPart);
                    break;
                }
            }
        }
    }

    private String calcMultyplyAndDivide(String ex) {
        double firstOperant;
        double secondOperant;
        int index;
        while (ex.contains("*") || ex.contains("/")) {
            String[] numbs = ex.split("[*,/,-,+]+");
            List<String> operators = Arrays.asList(ex.split("[0-9,.]+"));
            int indexOfMultyply = operators.indexOf("*") - 1;
            int indexDivide = operators.indexOf("/") - 1;
            if (indexDivide >= -1 && indexOfMultyply >= -1) index = Math.min(indexDivide, indexOfMultyply);
            else if (indexDivide == -2) index = indexOfMultyply;
            else index = indexDivide;
            double replace;
            if (ex.charAt(0) == '-') {
                firstOperant = Double.parseDouble(numbs[index - 1]);
                secondOperant = Double.parseDouble(numbs[index]);

            } else {
                firstOperant = Double.parseDouble(numbs[index]);
                secondOperant = Double.parseDouble(numbs[index + 1]);
            }
            if (index == indexDivide) {
                replace = firstOperant / secondOperant;
                ex = ex.replaceFirst("[0-9.]+[\\/]+[0-9,.]+", String.valueOf(replace));
            } else {
                replace = firstOperant * secondOperant;
                ex = ex.replaceFirst("[0-9.]+[*]+[0-9,.]+", String.valueOf(replace));
            }
        }
        return ex;
    }
}
