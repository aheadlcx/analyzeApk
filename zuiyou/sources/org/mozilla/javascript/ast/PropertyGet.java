package org.mozilla.javascript.ast;

public class PropertyGet extends InfixExpression {
    public PropertyGet() {
        this.type = 33;
    }

    public PropertyGet(int i) {
        super(i);
        this.type = 33;
    }

    public PropertyGet(int i, int i2) {
        super(i, i2);
        this.type = 33;
    }

    public PropertyGet(int i, int i2, AstNode astNode, Name name) {
        super(i, i2, astNode, (AstNode) name);
        this.type = 33;
    }

    public PropertyGet(AstNode astNode, Name name) {
        super(astNode, (AstNode) name);
        this.type = 33;
    }

    public PropertyGet(AstNode astNode, Name name, int i) {
        super(33, astNode, (AstNode) name, i);
        this.type = 33;
    }

    public AstNode getTarget() {
        return getLeft();
    }

    public void setTarget(AstNode astNode) {
        setLeft(astNode);
    }

    public Name getProperty() {
        return (Name) getRight();
    }

    public void setProperty(Name name) {
        setRight(name);
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append(getLeft().toSource(0));
        stringBuilder.append(".");
        stringBuilder.append(getRight().toSource(0));
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            getTarget().visit(nodeVisitor);
            getProperty().visit(nodeVisitor);
        }
    }
}
