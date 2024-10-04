package ru.nsu.chernikov;

/**
 * Expression abstract class.
 */
abstract class Expression {

    /**
     * abstract print for printing expressions.
     */
    public abstract void print();

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
    public abstract int eval(String vars);
}