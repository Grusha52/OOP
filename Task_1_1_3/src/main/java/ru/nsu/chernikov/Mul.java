package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

class Mul extends Expression {
    Expression left;
    Expression right;

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
    public void print() {
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

    @Override
    public Expression simplification() {

        Mul simplificationMul = new Mul(this.left.simplification(), this.right.simplification());

        if (simplificationMul.left instanceof Number leftNumber && simplificationMul.right instanceof Number rightNumber) {
            return new Number(leftNumber.value * rightNumber.value);

        } else if (simplificationMul.right instanceof Number rightNumber && rightNumber.value == 0) {
            return new Number(0);

        } else if (simplificationMul.left instanceof Number leftNumber && leftNumber.value == 0) {
            return new Number(0);

        } else {
            return simplificationMul;
        }
    }
}