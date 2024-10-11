package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Variable class.
 */
class Variable extends Expression {

    private String varname;

    /**
     * Variable konstructor.
     *
     * @param var variable in String format.
     */
    public Variable(String var) {
        this.varname = var;
    }

    /**
     * printing our variable.
     */
    @Override
    public void print() {
        System.out.print(varname);
    }

    /**
     * Derivative of our variable.
     *
     * @param var our variable in String type
     * @return a derivative of expression
     */
    @Override
    public Expression derivative(String var) {
        if (this.varname.equals(var)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Evaluation.
     *
     * @param vars variables for our equation
     * @return evaluates the expression
     */
    @Override
    public double eval(String vars) {
        String[] var;
        String[] variab;

        vars = vars.replaceAll(" ", "");
        var = vars.split(";");

        for (String a : var) {
            variab = a.split("=");
            if (variab[0].equals(varname)) {
                return Integer.parseInt(variab[1]);
            }
        }
        System.out.println("Error, there's no matchable variables");
        return 0;
    }

}