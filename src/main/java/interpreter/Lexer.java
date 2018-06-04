package interpreter;

public interface Lexer {

    /**
     * Lexical analyser (also known as scanner and tokeniser)
     *
     * This method is responsible for breaking sentence apart into a token.
     * @return the next token from the input.
     */
    Token getNextToken();
}
