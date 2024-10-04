package ru.nsu.chernikov;

/**
 * Div class.
 */
class Div extends Expression {
    private Expression left, right;

    /**
     * Division.
     *
     * @param left  expr
     * @param right expr
     */
    public Div(Expression left, Expression right) {
        this.right = right;
        this.left = left;
    }

    /**
     * printing.
     */
    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("/");
        right.print();
        System.out.print(")");
    }

    /**
     * Derivative of division.
     *
     * @param var our variable in String type
     * @return (f / g)' = (f' * g - f * g') / g*g
     */
    @Override
    public Expression derivative(String var) {
        return new Div(
                new Sub(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var))),
                new Mul(right, right)
        );
    }

    /**
     * Evaluation of division.
     *
     * @param vars variables for our equation
     * @return left / right in (Integer)
     */
    @Override
    public int eval(String vars) {
        return left.eval(vars) / right.eval(vars);
    }
}
