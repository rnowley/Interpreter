package interpreter;

public class _old {
    private Lexer lexer;
    private Token currentToken;

    public _old(Lexer lexer) {
        this.lexer = lexer;
        currentToken = lexer.getNextToken();
    }

    /**
     * Compare the current token type with the passed token type
     * and if they match then "eat' the current token and assign the
     * next token to the current token, otherwise raise an exception.
     * @param tokenType the token type to compare the current token
     *                  type to.
     */
    public void eat(TokenType tokenType) {

        if (currentToken.getTokenType() == tokenType) {
            currentToken = lexer.getNextToken();
        } else {
            throw new RuntimeException("Invalid syntax");
        }

    }

    /**
     * Return an INTEGER token value.
     *
     * factor : INTEGER | LPAREN expr RPAREN
     * @return the integer that the toke represents.
     */
    public int factor() {
        Token token = currentToken;

        if (token.getTokenType() == TokenType.INTEGER) {
            eat(TokenType.INTEGER);
            return token.getValue();
        } else {
            eat(TokenType.LPAREN);
            int result = expr();
            eat(TokenType.RPAREN);
            return result;
        }

    }

    /**
     * term : factor ((MUL | DIV) factor)*
     * @return
     */
    public int term() {
        int result = factor();

        while(currentToken.getTokenType() == TokenType.MUL ||
              currentToken.getTokenType() == TokenType.DIV) {
            Token token = currentToken;

            if (token.getTokenType() == TokenType.MUL) {
                eat(TokenType.MUL);
                result = result * factor();
            } else if (token.getTokenType() == TokenType.DIV) {
                eat(TokenType.DIV);
                result = result / factor();
            }

        }

        return result;
    }

    /**
     * Arithmetic expression parser / interpreter.
     *
     * expr   : term ((PLUS|MINUS) term)*
     * term   : factor ((MUL|DIV) factor)*
     * factor : INTEGER | LPAREN expr RPAREN
     * @return The result of interpreting the arithmetic expression.
     */
    public int expr() {
        int result = term();

        while (currentToken.getTokenType() == TokenType.PLUS ||
              currentToken.getTokenType() == TokenType.MINUS) {
            Token token = currentToken;

            if (token.getTokenType() == TokenType.PLUS) {
                eat(TokenType.PLUS);
                result += term();
            } else if (token.getTokenType() == TokenType.MINUS) {
                eat(TokenType.MINUS);
                result -= term();
            }

        }

        return result;
    }
}
