package interpreter;

public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
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
     * @return the integer that the token represents.
     */
    public AST factor() {
        Token token = currentToken;

        if (token.getTokenType() == TokenType.INTEGER) {
            eat(TokenType.INTEGER);
            return new Num(token);
        } else {
            eat(TokenType.LPAREN);
            AST node = expr();
            eat(TokenType.RPAREN);
            return node;
        }

    }

    /**
     * term : factor ((MUL | DIV) factor)*
     * @return
     */
    public AST term() {
        AST node = factor();

        while(currentToken.getTokenType() == TokenType.MUL ||
                currentToken.getTokenType() == TokenType.DIV) {
            Token token = currentToken;

            if (token.getTokenType() == TokenType.MUL) {
                eat(TokenType.MUL);
            } else if (token.getTokenType() == TokenType.DIV) {
                eat(TokenType.DIV);
            }

            node = new BinOp(node, token, factor());

        }

        return node;
    }

    /**
     * Arithmetic expression parser / interpreter.
     *
     * expr   : term ((PLUS|MINUS) term)*
     * term   : factor ((MUL|DIV) factor)*
     * factor : INTEGER | LPAREN expr RPAREN
     * @return The result of interpreting the arithmetic expression.
     */
    public AST expr() {
        AST node = term();

        while (currentToken.getTokenType() == TokenType.PLUS ||
                currentToken.getTokenType() == TokenType.MINUS) {
            Token token = currentToken;

            if (token.getTokenType() == TokenType.PLUS) {
                eat(TokenType.PLUS);
            } else if (token.getTokenType() == TokenType.MINUS) {
                eat(TokenType.MINUS);
            }

            node = new BinOp(node, token, term());

        }

        return node;
    }

    /**
     * Starts of the parsing of the expression.
     * @return an abstract syntax tree representing the expression.
     */
    public AST parse() {
        return expr();
    }
}
