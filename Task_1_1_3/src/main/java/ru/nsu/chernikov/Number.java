package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Number expr.
 */
class Number extends Expression {

    int value;

    public Number(int value) {
        this.value = value;
    }

    /**
     * printing constant.
     * сделать с Output stream
     */
    @Override
    public void print() {
        System.out.print(this.value);
    }

    /**
     * Derivative of constant.
     *
     * @param variable our variable in String type
     * @return zero
     */
    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    /**
     * evaluation.
     *
     * @param vars variables for our equation
     * @return value of given number
     */
    @Override
    public double eval(String vars) {
        return value;
    }

    @Override
    public Expression simplification() {
        return new Number(this.value);
    }

}