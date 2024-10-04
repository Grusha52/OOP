package ru.nsu.chernikov;

abstract class Expression {

    public abstract void print();

    public abstract Expression derivative(String var);

    public abstract int eval(String vars);
}