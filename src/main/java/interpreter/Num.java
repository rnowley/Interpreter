package interpreter;

public class Num extends AST implements ASTElement {
    private Token token;
    private int value;

    public Num(Token token) {
        this.setToken(token);
        this.setValue(token.getValue());
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
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
