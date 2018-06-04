package interpreter;

public class LexerImpl implements Lexer {
    private String text;
    private int position;
    private char currentChar;

    public LexerImpl(String text) {

        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("text is empty");
        }

        this.text = text;
        this.position = 0;
        currentChar = text.charAt(position);
    }

    /**
     * Advance, the 'pos' pointer and sets the 'currentChar variable.
     */
    private void advance() {
        ++position;

        if (position > text.length() - 1) {
            currentChar = Character.MIN_VALUE;
        } else {
            currentChar = text.charAt(position);
        }

    }


    private void skipWhiteSpace() {

        while (currentChar != Character.MIN_VALUE && Character.isSpaceChar(currentChar)) {
            advance();
        }

    }

    /**
     * Returns a (multi-digit) integer consumed from the input.
     * @return an integer representation of the input.
     */
    private int integer() {
        var result = new StringBuilder();

        while (currentChar != Character.MIN_VALUE && Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }

        return Integer.parseInt(result.toString());
    }

    /**
     * Lexical analyser (also known as scanner and tokeniser)
     *
     * This method is responsible for breaking sentence apart into a token.
     * @return the next token from the input.
     */
    public Token getNextToken() {

        while (currentChar != Character.MIN_VALUE) {

            if (Character.isSpaceChar(currentChar)) {
                skipWhiteSpace();
                continue;
            }

            if (Character.isDigit(currentChar)) {
                return new Token(TokenType.INTEGER, integer());
            }

            if (currentChar == '+') {
                advance();
                return new Token(TokenType.PLUS, '+');
            }

            if (currentChar == '-') {
                advance();
                return new Token(TokenType.MINUS, '-');
            }

            if (currentChar == '*') {
                advance();
                return new Token(TokenType.MUL, '*');
            }

            if (currentChar == '/') {
                advance();
                return new Token(TokenType.DIV, '/');
            }

            if (currentChar == '(') {
                advance();
                return new Token(TokenType.LPAREN, '(');
            }

            if (currentChar == ')') {
                advance();
                return new Token(TokenType.RPAREN, ')');
            }

            throw new RuntimeException("Error parsing input");
        }

        return new Token(TokenType.EOF, Character.MIN_VALUE);
    }

}
