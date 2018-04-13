package org.mozilla.javascript.ast;

public class ArrayComprehensionLoop extends ForInLoop {
    public ArrayComprehensionLoop(int i) {
        super(i);
    }

    public ArrayComprehensionLoop(int i, int i2) {
        super(i, i2);
    }

    public AstNode getBody() {
        return null;
    }

    public void setBody(AstNode astNode) {
        throw new UnsupportedOperationException("this node type has no body");
    }

    public String toSource(int i) {
        return makeIndent(i) + " for " + (isForEach() ? "each " : "") + "(" + this.iterator.toSource(0) + " in " + this.iteratedObject.toSource(0) + ")";
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.iterator.visit(nodeVisitor);
            this.iteratedObject.visit(nodeVisitor);
        }
    }
}
