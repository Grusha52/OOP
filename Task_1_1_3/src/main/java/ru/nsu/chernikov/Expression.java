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
     * можно короче вернуть принт на место и просто перехватить вывод с консоли:
     * PrintStream oldOutput = System.out;
     * ByteArrayOutputStream stream = new ByteArrayOutputStream;
     * System.setOut(new PrintStream(stream, false, "UTF-8");
     * try{
     * e.print();
     * } finally {
     * System.setOut(oldOutput);
     * }
     * string output = stream.toString("UTF-8");
     */
    public abstract void print() throws IOException;

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