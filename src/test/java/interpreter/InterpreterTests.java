package interpreter;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InterpreterTests {

    @Test
    public void TestInterpreterWithSingleDigitIntegerReturnsIntegerValue() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 5), new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new Interpreter(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(5, result);
    }
}
