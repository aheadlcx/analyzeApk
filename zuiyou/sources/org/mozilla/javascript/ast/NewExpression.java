package org.mozilla.javascript.ast;

public class NewExpression extends FunctionCall {
    private ObjectLiteral initializer;

    public NewExpression() {
        this.type = 30;
    }

    public NewExpression(int i) {
        super(i);
        this.type = 30;
    }

    public NewExpression(int i, int i2) {
        super(i, i2);
        this.type = 30;
    }

    public ObjectLiteral getInitializer() {
        return this.initializer;
    }

    public void setInitializer(ObjectLiteral objectLiteral) {
        this.initializer = objectLiteral;
        if (objectLiteral != null) {
            objectLiteral.setParent(this);
        }
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append("new ");
        stringBuilder.append(this.target.toSource(0));
        stringBuilder.append("(");
        if (this.arguments != null) {
            printList(this.arguments, stringBuilder);
        }
        stringBuilder.append(")");
        if (this.initializer != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.initializer.toSource(0));
        }
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.target.visit(nodeVisitor);
            for (AstNode visit : getArguments()) {
                visit.visit(nodeVisitor);
            }
            if (this.initializer != null) {
                this.initializer.visit(nodeVisitor);
            }
        }
    }
}
