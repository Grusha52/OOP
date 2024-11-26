package ru.nsu.chernikov;


public class Parser {

    private static String expression;
    private static String token;
    private static int pos;
    private static int len;

    static Expression parse(String expr) {
        expression = expr;
        len = expr.length();
        pos = 0;
        return parseExpression();
    }

    private static String readToken() {

        while (pos < len && expression.charAt(pos) == ' ') {
            pos++;
        }
        if (pos == len) {
            return token = "";
        }
        if ("+-*/()".contains(String.valueOf(expression.charAt(pos)))) {
            return token = String.valueOf(expression.charAt(pos++));
        }
        int left = pos;
        while (pos < len && Character.isLetterOrDigit(expression.charAt(pos))) {
            pos++;
        }
        return token = expression.substring(left, pos);
    }

    private static String peekToken() {
        int oldPos = pos;
        while (oldPos < len && expression.charAt(oldPos) == ' ') {
            oldPos++;
        }
        if (oldPos == len) {
            return token = "";
        }
        if ("+-*/()".contains(String.valueOf(expression.charAt(oldPos)))) {
            return token = String.valueOf(expression.charAt(oldPos));
        }
        int left = oldPos;
        while (oldPos < len && Character.isLetterOrDigit(expression.charAt(oldPos))) {
            oldPos++;
        }
        return token = expression.substring(left, oldPos);
    }

    private static Expression parseExpression() {

        Expression leftExp = parseMonome();

        while (peekToken().equals("+") || peekToken().equals("-")) {
            String operation = readToken();
            Expression rightExp = parseMonome();

            if (operation.equals("+")) {
                leftExp = new Add(leftExp, rightExp);
            } else {
                leftExp = new Sub(leftExp, rightExp);
            }
        }
        return leftExp;
    }

    private static Expression parseAtom(){

        if (peekToken().equals("(")) {
            readToken();
            Expression e = parseExpression();
            readToken();
            return e;
        } else {
            readToken();
            if (token.charAt(0) >= '0' && token.charAt(0) <= '9') {
                return new Number(Integer.parseInt(token));
            } else {
                return new Variable(token);
            }
        }
    }

    private static Expression parseMonome(){

        Expression leftExp = parseAtom();

        while (peekToken().equals("*") || peekToken().equals("/")) {
            String operation = readToken();
            Expression rightExp = parseAtom();
            if (operation.equals("*")) {
                leftExp = new Mul(leftExp, rightExp);
            } else {
                leftExp = new Div(leftExp, rightExp);
            }
        }
        return leftExp;
    }
}
