package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;

/**
 * THA MAIN.
 */
public class Main {

    /**
     * main metod.
     *
     * @param argc argc)
     */
    public static void main(String[] argc) {
        Expression exp = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))

        try {
            exp.print();
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println();
        Expression de = exp.derivative("x");
        try {
            de.print();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        try {
            double result = exp.eval("y = 13; z = 2351");
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception");
        }

        Expression f = new Sub(new Variable("y"), new Div(new Number(2),
                new Variable("x")));

        try {
            f.print();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        de = f.derivative("y");
        try {
            de.print();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        try {
            double result = f.eval("x = 0; y = 30");
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception");
        }

    }
}
