package org.mozilla.javascript.ast;

public class EmptyExpression extends AstNode {
    public EmptyExpression() {
        this.type = 128;
    }

    public EmptyExpression(int i) {
        super(i);
        this.type = 128;
    }

    public EmptyExpression(int i, int i2) {
        super(i, i2);
        this.type = 128;
    }

    public String toSource(int i) {
        return makeIndent(i);
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
