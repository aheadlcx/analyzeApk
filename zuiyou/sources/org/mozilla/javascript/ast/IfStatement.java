package org.mozilla.javascript.ast;

public class IfStatement extends AstNode {
    private AstNode condition;
    private AstNode elsePart;
    private int elsePosition;
    private int lp;
    private int rp;
    private AstNode thenPart;

    public IfStatement() {
        this.elsePosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 112;
    }

    public IfStatement(int i) {
        super(i);
        this.elsePosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 112;
    }

    public IfStatement(int i, int i2) {
        super(i, i2);
        this.elsePosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 112;
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode astNode) {
        assertNotNull(astNode);
        this.condition = astNode;
        astNode.setParent(this);
    }

    public AstNode getThenPart() {
        return this.thenPart;
    }

    public void setThenPart(AstNode astNode) {
        assertNotNull(astNode);
        this.thenPart = astNode;
        astNode.setParent(this);
    }

    public AstNode getElsePart() {
        return this.elsePart;
    }

    public void setElsePart(AstNode astNode) {
        this.elsePart = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getElsePosition() {
        return this.elsePosition;
    }

    public void setElsePosition(int i) {
        this.elsePosition = i;
    }

    public int getLp() {
        return this.lp;
    }

    public void setLp(int i) {
        this.lp = i;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int i) {
        this.rp = i;
    }

    public void setParens(int i, int i2) {
        this.lp = i;
        this.rp = i2;
    }

    public String toSource(int i) {
        String makeIndent = makeIndent(i);
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(makeIndent);
        stringBuilder.append("if (");
        stringBuilder.append(this.condition.toSource(0));
        stringBuilder.append(") ");
        if (this.thenPart.getType() != 129) {
            stringBuilder.append("\n").append(makeIndent(i + 1));
        }
        stringBuilder.append(this.thenPart.toSource(i).trim());
        if (this.elsePart != null) {
            if (this.thenPart.getType() != 129) {
                stringBuilder.append("\n").append(makeIndent).append("else ");
            } else {
                stringBuilder.append(" else ");
            }
            if (!(this.elsePart.getType() == 129 || this.elsePart.getType() == 112)) {
                stringBuilder.append("\n").append(makeIndent(i + 1));
            }
            stringBuilder.append(this.elsePart.toSource(i).trim());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.condition.visit(nodeVisitor);
            this.thenPart.visit(nodeVisitor);
            if (this.elsePart != null) {
                this.elsePart.visit(nodeVisitor);
            }
        }
    }
}
