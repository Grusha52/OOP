package ru.nsu.chernikov;

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
    }
}
