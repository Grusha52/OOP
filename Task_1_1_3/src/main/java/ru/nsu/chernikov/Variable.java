package ru.nsu.chernikov;


class Variable extends Expression {

    private String varname;

    public Variable(String var) {
        this.varname = var;
    }

    @Override
    public void print() {
        System.out.print(varname);;
    }

    @Override
    public Expression derivative(String var) {
        if (this.varname.equals(var)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    @Override
    public int eval(String vars) {
        String[] var;
        String[] variab;

        vars = vars.replaceAll(" ", "");
        var = vars.split(";");

        for (String a : var){
            variab = a.split("=");
             if (variab[0].equals(varname)){
                 return Integer.parseInt(variab[1]);
             }
        }
        System.out.println("Error, there's no matchable variables");
        return 0;
    }

}