import java.util.Stack;

public class Calculator {

    public static String ExpressionToRPN(String Expr) {
        String curent = "";
        Stack<Character> stack = new Stack<>();

        int priorety;//Текущий преоритет символа

        for (int i = 0; i < Expr.length(); i++) {
            priorety = getP(Expr.charAt(i));//Проверяем преоритеты, создали эту перем чтобы пост не вызывать getP

            if (priorety == 0) curent += Expr.charAt(i);
            if (priorety == 1) stack.push(Expr.charAt(i));

            if (priorety > 1) {//роверим непустой ли стек, запускаем while
                curent += " ";
                while (!stack.empty()) {//!empty непустой
                    if (getP(stack.peek()) >= priorety)curent += stack.pop(); //peek берем верхний символ pop удоляем, берем из стека
                    else break;
                }
                stack.push(Expr.charAt(i));
            }

            if (priorety == -1) {
                curent += " ";
                while (getP(stack.peek()) != 1) curent += stack.pop();

                stack.pop();
            }
        }
        while (!stack.empty()) curent += stack.pop();
        return curent;
    }




    public static double RPNtoAnswer(String rpn) {
        String operand = new String();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;

            if (getP(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getP(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }

                stack.push(Double.parseDouble(operand));
                operand = new String();
            }

            if (getP(rpn.charAt(i)) > 1) {
                double a = stack.pop();
                double b = stack.pop();

                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                if (rpn.charAt(i) == '/') stack.push(b / a);
            }
        }
        return stack.pop();
    }

    private static int getP(char token) {                   //Определяем преоритеты символов
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;
    }


}
