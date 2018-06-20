package interpreter;

public class Num extends AST {
    private Token token;
    private int value;

    public Num(Token token) {
        this.setToken(token);
        this.setValue(token.getValue());
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
