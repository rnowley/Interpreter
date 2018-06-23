package interpreter;

public interface ASTElement {
    void accept(NodeVisitor visitor);
}
