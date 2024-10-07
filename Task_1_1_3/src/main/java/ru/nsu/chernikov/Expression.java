package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Expression abstract class.
 */
abstract class Expression {

    /**
     * abstract print for printing expressions.
     * сделать с Output stream
     */
    public abstract void print(OutputStream stream) throws IOException;

    /**
     * Derivative of expr.
     *
     * @param var our variable in String type
     * @return Expression with ...
     */
    public abstract Expression derivative(String var);

    /**
     * Evaluation of expr.
     *
     * @param vars variables for our equation
     * @return integer type result
     */
    public abstract double eval(String vars);
}