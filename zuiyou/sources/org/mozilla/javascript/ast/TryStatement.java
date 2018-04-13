package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TryStatement extends AstNode {
    private static final List<CatchClause> NO_CATCHES = Collections.unmodifiableList(new ArrayList());
    private List<CatchClause> catchClauses;
    private AstNode finallyBlock;
    private int finallyPosition;
    private AstNode tryBlock;

    public TryStatement() {
        this.finallyPosition = -1;
        this.type = 81;
    }

    public TryStatement(int i) {
        super(i);
        this.finallyPosition = -1;
        this.type = 81;
    }

    public TryStatement(int i, int i2) {
        super(i, i2);
        this.finallyPosition = -1;
        this.type = 81;
    }

    public AstNode getTryBlock() {
        return this.tryBlock;
    }

    public void setTryBlock(AstNode astNode) {
        assertNotNull(astNode);
        this.tryBlock = astNode;
        astNode.setParent(this);
    }

    public List<CatchClause> getCatchClauses() {
        return this.catchClauses != null ? this.catchClauses : NO_CATCHES;
    }

    public void setCatchClauses(List<CatchClause> list) {
        if (list == null) {
            this.catchClauses = null;
            return;
        }
        if (this.catchClauses != null) {
            this.catchClauses.clear();
        }
        for (CatchClause addCatchClause : list) {
            addCatchClause(addCatchClause);
        }
    }

    public void addCatchClause(CatchClause catchClause) {
        assertNotNull(catchClause);
        if (this.catchClauses == null) {
            this.catchClauses = new ArrayList();
        }
        this.catchClauses.add(catchClause);
        catchClause.setParent(this);
    }

    public AstNode getFinallyBlock() {
        return this.finallyBlock;
    }

    public void setFinallyBlock(AstNode astNode) {
        this.finallyBlock = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getFinallyPosition() {
        return this.finallyPosition;
    }

    public void setFinallyPosition(int i) {
        this.finallyPosition = i;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder(250);
        stringBuilder.append(makeIndent(i));
        stringBuilder.append("try ");
        stringBuilder.append(this.tryBlock.toSource(i).trim());
        for (CatchClause toSource : getCatchClauses()) {
            stringBuilder.append(toSource.toSource(i));
        }
        if (this.finallyBlock != null) {
            stringBuilder.append(" finally ");
            stringBuilder.append(this.finallyBlock.toSource(i));
        }
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.tryBlock.visit(nodeVisitor);
            for (CatchClause visit : getCatchClauses()) {
                visit.visit(nodeVisitor);
            }
            if (this.finallyBlock != null) {
                this.finallyBlock.visit(nodeVisitor);
            }
        }
    }
}
