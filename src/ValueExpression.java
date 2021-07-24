public class ValueExpression implements Expression {

    private double value;

    public ValueExpression(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double calculate() {
        return value;
    }

    public void createExpressionHierarchy(String s) {
        double d = Double.parseDouble(s);
        this.value = d;
    }
}
