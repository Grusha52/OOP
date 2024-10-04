package ru.nsu.chernikov;

/**
 * Number expr.
 */
class Number extends Expression {

    private final int value;

    public Number(int value) {
        this.value = value;
    }

    /**
     * printing constant.
     */
    @Override
    public void print() {
        System.out.print(value);
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
    public int eval(String vars) {
        return value;
    }

}