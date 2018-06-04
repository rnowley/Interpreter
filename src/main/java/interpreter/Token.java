package interpreter;

public class Token {
    private TokenType tokenType;
    private int value;

    public Token(TokenType tokentype, int value) {
        this.tokenType = tokentype;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Token({%0}, {%1})", getTokenType(), getValue());
    }
}
