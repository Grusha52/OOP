package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Add class.
 */
class Add extends Expression {

    Expression left, right;

    /**
     * Add constructor.
     *
     * @param left  expression
     * @param right expression
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * printing expression.
     */
    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("+");
        right.print();
        System.out.print(")");
    }

    /**
     * Derivative of expression.
     *
     * @param var our variable in String type
     * @return derivative(left) + derivative(right)
     */
    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    /**
     * Evaluation of sum.
     *
     * @param vars variables for our equation
     * @return left + right
     */
    @Override
    public double eval(String vars) {
        return left.eval(vars) + right.eval(vars);
    }

    @Override
    public Expression simplification() {
        Add simplificationAdd = new Add(this.left.simplification(), this.right.simplification());
        if (simplificationAdd.left instanceof Number leftNumber
        && simplificationAdd.right instanceof Number rightNumber) {

            return new Number(leftNumber.value + rightNumber.value);

        } else if (simplificationAdd.right instanceof Number rightNumber && rightNumber.value == 0) {
            return simplificationAdd.left;

        } else if (simplificationAdd.left instanceof Number leftNumber && leftNumber.value == 0) {
            return simplificationAdd.right;

        } else {
            return simplificationAdd;
        }
    }
}