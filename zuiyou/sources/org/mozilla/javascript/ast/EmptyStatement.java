package org.mozilla.javascript.ast;

public class EmptyStatement extends AstNode {
    public EmptyStatement() {
        this.type = 128;
    }

    public EmptyStatement(int i) {
        super(i);
        this.type = 128;
    }

    public EmptyStatement(int i, int i2) {
        super(i, i2);
        this.type = 128;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i)).append(";\n");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
