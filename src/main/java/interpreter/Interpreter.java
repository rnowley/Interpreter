package interpreter;

public class Interpreter implements NodeVisitor {
    private Parser parser;

    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    @Override
    public int visit(AST node) {

        if (node instanceof BinOp) {
            return visit((BinOp)node);
        } else if (node instanceof Num) {
            return visit((Num)node);
        } else {
            throw new RuntimeException("Invalid Syntax");
        }
    }

    @Override
    public int visit(BinOp node) {

        if (node.getTokenType() == TokenType.PLUS) {
            return visit(node.getLeft()) + visit(node.getRight());
        } else if (node.getTokenType() == TokenType.MINUS) {
            return visit(node.getLeft()) - visit(node.getRight());
        } else if (node.getTokenType() == TokenType.MUL) {
            return visit(node.getLeft()) * visit(node.getRight());
        } else if (node.getTokenType() == TokenType.DIV) {
            return visit(node.getLeft()) / visit(node.getRight());
        }

        throw new RuntimeException("Invalid Syntax");
    }

    public int interpret() {
        AST tree = parser.parse();
        return visit(tree);
    }

    @Override
    public int visit(Num num) {
        return num.getValue();
    }
}
