import java.util.Stack;

public class Calculator {

    public static String ExpressionToRPN(String Expr) {
        String curent = "";
        Stack<Character> stack = new Stack<>();

        int priorety;//Текущий преоритет символа

        for (int i = 0; i< Expr.length();i++){
         priorety = getP(Expr.charAt(i));//Проверяем преоритеты, создали эту перем чтобы пост не вызывать getP

            if (priorety == 0) curent += Expr.charAt(i);
            if (priorety == 1) stack.push(Expr.charAt(i));

            if (priorety >1){//роверим непустой ли стек, запускаем while
                curent +=" ";
                while (!stack.empty()){//!empty непустой
                    if (getP(stack.peek()) >= priorety)curent += stack.pop(); //peek берем верхний символ pop удоляем
                    else break;
                }
                stack.push(Expr.charAt(i));
            }

            if (priorety == -1){
                curent = " ";
                while (getP(stack.peek()) != 1) curent += stack.pop();

                stack.pop();
            }
        }
        while (!stack.empty()) curent+= stack.pop();
        return curent;
    }

    public static double RPNtoAnswer(String rpn) {
        return 0;
    }

    private static int getP(char token) {                   //Определяем преоритеты символов
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;
    }


}
