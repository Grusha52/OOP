package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

class Mul extends Expression {
    private Expression left;
    private Expression right;

    /**
     * Multiplication of two expr.
     *
     * @param left  expr
     * @param right expr
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * printing expr.
     */
    @Override
    public void print() throws IOException {
        System.out.print("(");
        left.print();
        System.out.print("*");
        right.print();
        System.out.print(")");
    }

    /**
     * Derivative of multiplication.
     *
     * @param var our variable in String type
     * @return Derivative of expr
     */
    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    /**
     * Evaluation a multiplication.
     *
     * @param vars variables for our equation
     * @return Integer of left * return
     */
    @Override
    public double eval(String vars) {
        return left.eval(vars) * right.eval(vars);
    }

}