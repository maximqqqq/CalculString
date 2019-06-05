public class App {
    public static void main(String[] args) {

        System.out.println(Calculator.ExpressionToRPN("(222+2)*2"));
        System.out.println(Calculator.RPNtoAnswer(Calculator.ExpressionToRPN("(2+2)*2")));

    }
}
