public class Main {
    public static void main(String[] args) {
        String expression = "1*5*5+2/1*2+1*1*5";
        Expression expression1 = new OperatorExpression();
        expression1.createExpressionHierarchy(expression);
        System.out.println(expression1.calculate());
    }
}
