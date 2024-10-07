package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Add class.
 */
class Add extends Expression {

    private Expression left, right;

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
    public void print(OutputStream stream) throws IOException {
        stream.write("(".getBytes(StandardCharsets.UTF_8));
        left.print(stream);
        stream.write("+".getBytes(StandardCharsets.UTF_8));
        right.print(stream);
        stream.write(")".getBytes(StandardCharsets.UTF_8));
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
}