package ru.nsu.chernikov;

/**
 * Sub class.
 */
class Sub extends Expression {
    private Expression left, right;

    /**
     * Sub constructor.
     *
     * @param left  expr
     * @param right expr
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * printing expr.
     */
    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("-");
        right.print();
        System.out.print(")");
    }

    /**
     * derivative of expr.
     *
     * @param var our variable in String type
     * @return der(left) - der(right)
     */
    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    /**
     * Evaluation of sub.
     *
     * @param vars variables for our equation
     * @return left - right.
     */
    @Override
    public int eval(String vars) {
        return left.eval(vars) - right.eval(vars);
    }
}
