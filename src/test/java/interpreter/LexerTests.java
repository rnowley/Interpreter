package interpreter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LexerTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestLexerWithSingleDigitInteger() {

        var lexer = new LexerImpl("5");

        var token1 = lexer.getNextToken();
        var token2 = lexer.getNextToken();

        assertEquals(TokenType.INTEGER, token1.getTokenType());
        assertEquals(5, token1.getValue());

        assertEquals(TokenType.EOF, token2.getTokenType());
    }

    @Test
    public void TestLexerWithMultiDigitInteger() {
        var lexer = new LexerImpl("115");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(115, tokenList.get(0).getValue());
        assertEquals(TokenType.EOF, tokenList.get(1).getTokenType());
    }

    @Test
    public void TestLexerWithSimpleAdditionExpressionWithoutSpaces() {
        var lexer = new LexerImpl("2+4");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(2, tokenList.get(0).getValue());
        assertEquals(TokenType.PLUS, tokenList.get(1).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(2).getTokenType());
        assertEquals(4, tokenList.get(2).getValue());
        assertEquals(TokenType.EOF, tokenList.get(3).getTokenType());
    }

    @Test
    public void TestLexerWithExpressionWithMultipleSpaces() {
        var lexer = new LexerImpl("  2  +   4  ");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(2, tokenList.get(0).getValue());
        assertEquals(TokenType.PLUS, tokenList.get(1).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(2).getTokenType());
        assertEquals(4, tokenList.get(2).getValue());
        assertEquals(TokenType.EOF, tokenList.get(3).getTokenType());
    }

    @Test
    public void TestLexerWithSimpleSubtractionExpression() {
        var lexer = new LexerImpl("5 - 4");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(5, tokenList.get(0).getValue());
        assertEquals(TokenType.MINUS, tokenList.get(1).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(2).getTokenType());
        assertEquals(4, tokenList.get(2).getValue());
        assertEquals(TokenType.EOF, tokenList.get(3).getTokenType());
    }

    @Test
    public void TestLexerWithSimpleMultiplicationExpression() {
        var lexer = new LexerImpl("6 * 2");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(6, tokenList.get(0).getValue());
        assertEquals(TokenType.MUL, tokenList.get(1).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(2).getTokenType());
        assertEquals(2, tokenList.get(2).getValue());
        assertEquals(TokenType.EOF, tokenList.get(3).getTokenType());
    }

    @Test
    public void TestLexerWithSimpleDivisionExpression() {
        var lexer = new LexerImpl("14 / 2");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(14, tokenList.get(0).getValue());
        assertEquals(TokenType.DIV, tokenList.get(1).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(2).getTokenType());
        assertEquals(2, tokenList.get(2).getValue());
        assertEquals(TokenType.EOF, tokenList.get(3).getTokenType());
    }

    @Test
    public void TestLexerWithComplexAdditionAndSubtractionExpression() {
        var lexer = new LexerImpl("50 + 12 - 2");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(50, tokenList.get(0).getValue());
        assertEquals(TokenType.PLUS, tokenList.get(1).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(2).getTokenType());
        assertEquals(12, tokenList.get(2).getValue());
        assertEquals(TokenType.MINUS, tokenList.get(3).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(4).getTokenType());
        assertEquals(2, tokenList.get(4).getValue());
        assertEquals(TokenType.EOF, tokenList.get(5).getTokenType());
    }

    @Test
    public void TestLexerWithComplexMultiplicationAndDivisionExpression() {
        var lexer = new LexerImpl("50 * 12 / 2");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(50, tokenList.get(0).getValue());
        assertEquals(TokenType.MUL, tokenList.get(1).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(2).getTokenType());
        assertEquals(12, tokenList.get(2).getValue());
        assertEquals(TokenType.DIV, tokenList.get(3).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(4).getTokenType());
        assertEquals(2, tokenList.get(4).getValue());
        assertEquals(TokenType.EOF, tokenList.get(5).getTokenType());
    }

    @Test
    public void TestLexerWithParenthesesExpression() {
        var lexer = new LexerImpl("50 * (12 - 2)");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.INTEGER, tokenList.get(0).getTokenType());
        assertEquals(50, tokenList.get(0).getValue());
        assertEquals(TokenType.MUL, tokenList.get(1).getTokenType());
        assertEquals(TokenType.LPAREN, tokenList.get(2).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(3).getTokenType());
        assertEquals(12, tokenList.get(3).getValue());
        assertEquals(TokenType.MINUS, tokenList.get(4).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(5).getTokenType());
        assertEquals(2, tokenList.get(5).getValue());
        assertEquals(TokenType.RPAREN, tokenList.get(6).getTokenType());
        assertEquals(TokenType.EOF, tokenList.get(7).getTokenType());
    }

    @Test
    public void TestLexerWithMultipleParenthesisedExpressions() {
        var lexer = new LexerImpl("(12 - 2) / (2 + 1)");

        var tokenList = new ArrayList<Token>();

        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());
        tokenList.add(lexer.getNextToken());

        assertEquals(TokenType.LPAREN, tokenList.get(0).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(1).getTokenType());
        assertEquals(12, tokenList.get(1).getValue());
        assertEquals(TokenType.MINUS, tokenList.get(2).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(3).getTokenType());
        assertEquals(2, tokenList.get(3).getValue());
        assertEquals(TokenType.RPAREN, tokenList.get(4).getTokenType());
        assertEquals(TokenType.DIV, tokenList.get(5).getTokenType());
        assertEquals(TokenType.LPAREN, tokenList.get(6).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(7).getTokenType());
        assertEquals(2, tokenList.get(7).getValue());
        assertEquals(TokenType.PLUS, tokenList.get(8).getTokenType());
        assertEquals(TokenType.INTEGER, tokenList.get(9).getTokenType());
        assertEquals(1, tokenList.get(9).getValue());
        assertEquals(TokenType.RPAREN, tokenList.get(10).getTokenType());
        assertEquals(TokenType.EOF, tokenList.get(11).getTokenType());
    }

    @Test
    public void TestLexerWithStringOfSpacesThrowsException() {
        var lexer = new LexerImpl("     ");

        Token token = lexer.getNextToken();
        assertEquals(TokenType.EOF, token.getTokenType());
    }

    @Test
    public void TestLexerWithIllegalOperatorThrowsException() {
        var lexer = new LexerImpl("A");

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Error parsing input");
        lexer.getNextToken();

        // Shouldn't get past here
    }

    @Test
    public void TestLexerWithNullInputThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("text is empty");

        new LexerImpl(null);

        // Shouldn't get past here
    }

    @Test
    public void TestLexerWithEmptyStringInputThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("text is empty");

        new LexerImpl("");

        // Shouldn't get past here
    }
}
