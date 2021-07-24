public class Main {
    public static void main(String[] args) {
        String expression = "-1-1+3+58+47-58-47";
        Expression expression1 = new OperatorExpression();
        expression1.createExpressionHierarchy(expression);
        System.out.println(expression1.calculate());
    }

}
