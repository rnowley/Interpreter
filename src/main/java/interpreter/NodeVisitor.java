package interpreter;

public interface NodeVisitor {
    int visit(AST ast);

    int visit(BinOp binOp);

    int visit(Num num);
}
