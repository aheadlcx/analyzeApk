package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunctionCall extends AstNode {
    protected static final List<AstNode> NO_ARGS = Collections.unmodifiableList(new ArrayList());
    protected List<AstNode> arguments;
    protected int lp;
    protected int rp;
    protected AstNode target;

    public FunctionCall() {
        this.lp = -1;
        this.rp = -1;
        this.type = 38;
    }

    public FunctionCall(int i) {
        super(i);
        this.lp = -1;
        this.rp = -1;
        this.type = 38;
    }

    public FunctionCall(int i, int i2) {
        super(i, i2);
        this.lp = -1;
        this.rp = -1;
        this.type = 38;
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode astNode) {
        assertNotNull(astNode);
        this.target = astNode;
        astNode.setParent(this);
    }

    public List<AstNode> getArguments() {
        return this.arguments != null ? this.arguments : NO_ARGS;
    }

    public void setArguments(List<AstNode> list) {
        if (list == null) {
            this.arguments = null;
            return;
        }
        if (this.arguments != null) {
            this.arguments.clear();
        }
        for (AstNode addArgument : list) {
            addArgument(addArgument);
        }
    }

    public void addArgument(AstNode astNode) {
        assertNotNull(astNode);
        if (this.arguments == null) {
            this.arguments = new ArrayList();
        }
        this.arguments.add(astNode);
        astNode.setParent(this);
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append(this.target.toSource(0));
        stringBuilder.append("(");
        if (this.arguments != null) {
            printList(this.arguments, stringBuilder);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.target.visit(nodeVisitor);
            for (AstNode visit : getArguments()) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
