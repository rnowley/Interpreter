package interpreter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var input = new Scanner(System.in);

        while (true) {
            System.out.print("calc> ");
            String text = input.nextLine();

            var lexer = new LexerImpl(text);
            var interpreter = new Interpreter(lexer);
            int result = interpreter.expr();
            System.out.println(result);
        }
    }
}
