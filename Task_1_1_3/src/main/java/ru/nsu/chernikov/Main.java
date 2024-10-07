package ru.nsu.chernikov;

import java.io.IOException;
import java.io.OutputStream;

/**
 * THA MAIN.
 */
public class Main {


    public static void main(String[] argc) {
        Expression exp = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))

        OutputStream out = System.out;
        try {
            exp.print(out);
        } catch (IOException e){
            e.printStackTrace();

        }
        System.out.println();
        Expression de = exp.derivative("x");
        try{
            de.print(out);
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println();

        try {
            double result = exp.eval("y = 13; z = 2351");
            System.out.println(result);
        } catch(ArithmeticException e){
            System.out.println("Arithmetic exception");
        }

        Expression f = new Sub(new Variable("y"), new Div(new Number(2),
                new Variable("x")));

        try {
            f.print(out);
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println();

        de = f.derivative("y");
        try {
            de.print(out);
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println();

        try {
            double result = f.eval("x = 25; y = 30");
            System.out.println(result);
        } catch(ArithmeticException e){
            System.out.println("Arithmetic exception");
        }

    }
}
