import java.util.ArrayList;
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
        return left.calculate() + right.calculate();
    }

    public void createExpressionHierarchy(String ex) {
        StringBuilder sb = new StringBuilder();

        try {
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

}
