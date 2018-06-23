package interpreter;

public class BinOp extends AST implements ASTElement {
    private AST left;
    private Token op;
    private AST right;

    public BinOp(AST left, Token op, AST right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        getLeft().accept(visitor);
        getRight().accept(visitor);

        visitor.visit(this);
    }

    public TokenType getTokenType() {
        return op.getTokenType();
    }

    public AST getLeft() {
        return left;
    }

    public AST getRight() {
        return right;
    }
}
