package ru.nsu.chernikov;

/**
 * THA MAIN.
 */
public class Main {


    public static void main(String[] argc) {
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))

        e.print();
        System.out.println();
        Expression de = e.derivative("x");
        de.print();
        System.out.println();


        int result = e.eval("x = 10; y = 13");
        System.out.println(result);

        Expression f = new Add(new Variable("y"), new Mul(new Number(2),
                new Variable("x")));

        f.print();
        System.out.println();

        de = f.derivative("y");
        de.print();
        System.out.println();

        result = f.eval("x = 25; y = 30");
        System.out.println(result);
    }
}
