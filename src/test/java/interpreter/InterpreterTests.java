package interpreter;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InterpreterTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestInterpreterWithSingleDigitIntegerReturnsIntegerValue() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 5), new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(5, result);
    }

    @Test
    public void TestInterpreterWithMultiDigitIntegerReturnsIntegerValue() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 12), new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(12, result);
    }

    @Test
    public void TestInterpreterSimpleSumReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 8),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(10, result);
    }

    @Test
    public void TestInterpreterSimpleSubtractionReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 10),
                new Token(TokenType.MINUS, '-'), new Token(TokenType.INTEGER, 8),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(2, result);
    }

    @Test
    public void TestInterpreterSimpleMultiplicationReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 12),
                new Token(TokenType.MUL, '*'), new Token(TokenType.INTEGER, 12),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(144, result);
    }

    @Test
    public void TestInterpreterSimpleDivisionReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 12),
                new Token(TokenType.DIV, '/'), new Token(TokenType.INTEGER, 12),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(1, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationMinusThenMulReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 4),
                new Token(TokenType.MINUS, '-'), new Token(TokenType.INTEGER, 2),
                new Token(TokenType.MUL, '*'), new Token(TokenType.INTEGER, 6),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(-8, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationMulThenMinusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.MUL, '*'), new Token(TokenType.INTEGER, 4),
                new Token(TokenType.MINUS, '-'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(3, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationPlusThenMulReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 4),
                new Token(TokenType.MUL, '*'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(22, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationMulThenPlusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.MUL, '*'), new Token(TokenType.INTEGER, 4),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(13, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationMinusTheDivReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 4),
                new Token(TokenType.MINUS, '-'), new Token(TokenType.INTEGER, 6),
                new Token(TokenType.DIV, '/'), new Token(TokenType.INTEGER, 2),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(1, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationDivThenMinusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 20),
                new Token(TokenType.DIV, '/'), new Token(TokenType.INTEGER, 2),
                new Token(TokenType.MINUS, '-'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(5, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationPlusThenDivReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 25),
                new Token(TokenType.DIV, '/'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(7, result);
    }

    @Test
    public void TestInterpreterOrderOfOperationDivThenPlusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 24),
                new Token(TokenType.DIV, '/'), new Token(TokenType.INTEGER, 4),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(11, result);
    }

    @Test
    public void TestInterpreterSimpleSumWithParenthesesReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 2),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 8),
                new Token(TokenType.RPAREN, ')'), new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(10, result);
    }

    @Test
    public void TestParserSimpleSumWithMultipleNestingOfParenthesesReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.LPAREN, '('), new Token(TokenType.INTEGER, 2),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 8),
                new Token(TokenType.RPAREN, ')'), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var parser = new Parser(mockLexer);
        AST result = parser.expr();

        Assert.assertTrue(result instanceof BinOp);
        BinOp binOpResult = (BinOp)result;
        Assert.assertTrue(binOpResult.getLeft() instanceof Num);
        Assert.assertTrue(binOpResult.getTokenType() == TokenType.PLUS);
        Assert.assertTrue(binOpResult.getRight() instanceof Num);
    }

    @Test
    public void TestInterpreterComplexSumWithMultipleNestingOfParenthesesReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 2),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 8), new Token(TokenType.MINUS, '-'),
                new Token(TokenType.INTEGER, 4), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.RPAREN, ')'), new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(6, result);
    }

    @Test
    public void TestInterpreterSimpleSubtractionWithParenthesesReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 10), new Token(TokenType.MINUS, '-'),
                new Token(TokenType.INTEGER, 8), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(2, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationMinusThenMulReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 4), new Token(TokenType.MINUS, '-'),
                new Token(TokenType.INTEGER, 2), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.MUL, '*'), new Token(TokenType.INTEGER, 6),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(12, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationMulThenMinusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.MUL, '*'), new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 4), new Token(TokenType.MINUS, '-'),
                new Token(TokenType.INTEGER, 5), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(-2, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationPlusThenMulReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 2), new Token(TokenType.PLUS, '+'),
                new Token(TokenType.INTEGER, 4), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.MUL, '*'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(30, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationMulThenPlusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.MUL, '*'), new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 4), new Token(TokenType.PLUS, '+'),
                new Token(TokenType.INTEGER, 5), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(18, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationMinusTheDivReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 4), new Token(TokenType.MINUS, '-'),
                new Token(TokenType.INTEGER, 6), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.DIV, '/'), new Token(TokenType.INTEGER, 2),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(-1, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationDivThenMinusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 30),
                new Token(TokenType.DIV, '/'), new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 2), new Token(TokenType.MINUS, '-'),
                new Token(TokenType.INTEGER, 5), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(-10, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationPlusThenDivReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 5), new Token(TokenType.PLUS, '+'),
                new Token(TokenType.INTEGER, 25), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.DIV, '/'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(6, result);
    }

    @Test
    public void TestInterpreterParenthesesOverrideOrderOfOperationDivThenPlusReturnsCorrectResult() {
        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 27),
                new Token(TokenType.DIV, '/'), new Token(TokenType.LPAREN, '('),
                new Token(TokenType.INTEGER, 4), new Token(TokenType.PLUS, '+'),
                new Token(TokenType.INTEGER, 5), new Token(TokenType.RPAREN, ')'),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        int result = interpreter.expr();

        Assert.assertEquals(3, result);
    }

    @Test
    public void TestInterpreterParenthesesEmptyBodyThrowsException() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Invalid syntax");

        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.RPAREN, ')'), new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        interpreter.expr();

        // Shouldn't get past here
    }

    @Test
    public void TestInterpreterParenthesesUnbalancedThrowsException() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Invalid syntax");

        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.LPAREN, '('),
                new Token(TokenType.LPAREN, '('), new Token(TokenType.INTEGER, 4),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.INTEGER, 5),
                new Token(TokenType.RPAREN, ')'), new Token(TokenType.EOF, Character.MIN_VALUE));

        var parser = new Parser(mockLexer);
        parser.parse();

        // Shouldn't get here
    }

    @Test
    public void TestInterpreterInvalidSumExpressionThrowsException() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Invalid syntax");

        Lexer mockLexer = mock(LexerImpl.class);
        when(mockLexer.getNextToken()).thenReturn(new Token(TokenType.INTEGER, 2),
                new Token(TokenType.PLUS, '+'), new Token(TokenType.PLUS, '+'),
                new Token(TokenType.EOF, Character.MIN_VALUE));

        var interpreter = new _old(mockLexer);
        interpreter.expr();

        // Shouldn't get here

    }

}
