package ru.nsu.chernikov;

class Div extends Expression{
    private Expression left, right;

    public Div(Expression left, Expression right){
        this.right = right;
        this.left = left;
    }

    @Override
    public void print(){
        System.out.print("(");
        left.print();
        System.out.print("/");
        right.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String var){
        //(f / g)' = (f' * g - f * g') / g*g
        return new Div(
                new Sub(new Mul(left.derivative(var),right),new Mul(left, right.derivative(var))),
                new Mul(right, right)
        );
    }

    @Override
    public int eval(String vars){
        return left.eval(vars) / right.eval(vars);
    }
}
