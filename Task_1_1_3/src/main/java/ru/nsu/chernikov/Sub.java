package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Sub class.
 */
class Sub extends Expression {
    private Expression left;
    private Expression right;

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
    public void print(OutputStream stream) throws IOException {
        stream.write("(".getBytes(StandardCharsets.UTF_8));
        left.print(stream);
        stream.write("-".getBytes(StandardCharsets.UTF_8));
        right.print(stream);
        stream.write(")".getBytes(StandardCharsets.UTF_8));
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
    public double eval(String vars) {
        return left.eval(vars) - right.eval(vars);
    }
}
