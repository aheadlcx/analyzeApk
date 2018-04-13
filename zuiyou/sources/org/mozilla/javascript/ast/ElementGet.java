package org.mozilla.javascript.ast;

public class ElementGet extends AstNode {
    private AstNode element;
    private int lb;
    private int rb;
    private AstNode target;

    public ElementGet() {
        this.lb = -1;
        this.rb = -1;
        this.type = 36;
    }

    public ElementGet(int i) {
        super(i);
        this.lb = -1;
        this.rb = -1;
        this.type = 36;
    }

    public ElementGet(int i, int i2) {
        super(i, i2);
        this.lb = -1;
        this.rb = -1;
        this.type = 36;
    }

    public ElementGet(AstNode astNode, AstNode astNode2) {
        this.lb = -1;
        this.rb = -1;
        this.type = 36;
        setTarget(astNode);
        setElement(astNode2);
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode astNode) {
        assertNotNull(astNode);
        this.target = astNode;
        astNode.setParent(this);
    }

    public AstNode getElement() {
        return this.element;
    }

    public void setElement(AstNode astNode) {
        assertNotNull(astNode);
        this.element = astNode;
        astNode.setParent(this);
    }

    public int getLb() {
        return this.lb;
    }

    public void setLb(int i) {
        this.lb = i;
    }

    public int getRb() {
        return this.rb;
    }

    public void setRb(int i) {
        this.rb = i;
    }

    public void setParens(int i, int i2) {
        this.lb = i;
        this.rb = i2;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append(this.target.toSource(0));
        stringBuilder.append("[");
        stringBuilder.append(this.element.toSource(0));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.target.visit(nodeVisitor);
            this.element.visit(nodeVisitor);
        }
    }
}
