package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Div class.
 */
class Div extends Expression {
    private Expression left;
    private Expression right;

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
        return new Div(new Sub(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))), new Mul(right, right));
    }

    /**
     * Evaluation of division.
     *
     * @param vars variables for our equation
     * @return left / right in (Integer)
     */
    @Override
    public double eval(String vars) throws ArithmeticException {
        if (Double.isInfinite(left.eval(vars) / right.eval(vars))) {
            throw new ArithmeticException();
        }
        return left.eval(vars) / right.eval(vars);
    }
}
