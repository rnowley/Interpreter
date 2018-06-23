package interpreter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var input = new Scanner(System.in);

        while (true) {
            System.out.print("calc> ");
            String text = input.nextLine();

            var lexer = new LexerImpl(text);
            var parser = new Parser(lexer);
            var interpreter = new Interpreter(parser);
            int result = interpreter.interpret();
            System.out.println(result);
        }
    }
}
