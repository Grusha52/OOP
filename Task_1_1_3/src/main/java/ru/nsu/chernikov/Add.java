package ru.nsu.chernikov;

class Add extends Expression {

    private Expression left, right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(){
        System.out.print("(");
        left.print();
        System.out.print("+");
        right.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var){
        return new Add(left.derivative(var),right.derivative(var));
    }

    @Override
    public int eval(String vars){
        return left.eval(vars) + right.eval(vars);
    }
}