package org.mozilla.javascript.ast;

public class VariableInitializer extends AstNode {
    private AstNode initializer;
    private AstNode target;

    public void setNodeType(int i) {
        if (i == 122 || i == 154 || i == 153) {
            setType(i);
            return;
        }
        throw new IllegalArgumentException("invalid node type");
    }

    public VariableInitializer() {
        this.type = 122;
    }

    public VariableInitializer(int i) {
        super(i);
        this.type = 122;
    }

    public VariableInitializer(int i, int i2) {
        super(i, i2);
        this.type = 122;
    }

    public boolean isDestructuring() {
        return !(this.target instanceof Name);
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException("invalid target arg");
        }
        this.target = astNode;
        astNode.setParent(this);
    }

    public AstNode getInitializer() {
        return this.initializer;
    }

    public void setInitializer(AstNode astNode) {
        this.initializer = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append(this.target.toSource(0));
        if (this.initializer != null) {
            stringBuilder.append(" = ");
            stringBuilder.append(this.initializer.toSource(0));
        }
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.target.visit(nodeVisitor);
            if (this.initializer != null) {
                this.initializer.visit(nodeVisitor);
            }
        }
    }
}
