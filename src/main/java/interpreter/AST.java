package interpreter;

public class AST implements ASTElement {

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }
}
