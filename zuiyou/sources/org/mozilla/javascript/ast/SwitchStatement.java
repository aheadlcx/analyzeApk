package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwitchStatement extends Jump {
    private static final List<SwitchCase> NO_CASES = Collections.unmodifiableList(new ArrayList());
    private List<SwitchCase> cases;
    private AstNode expression;
    private int lp;
    private int rp;

    public SwitchStatement() {
        this.lp = -1;
        this.rp = -1;
        this.type = 114;
    }

    public SwitchStatement(int i) {
        this.lp = -1;
        this.rp = -1;
        this.type = 114;
        this.position = i;
    }

    public SwitchStatement(int i, int i2) {
        this.lp = -1;
        this.rp = -1;
        this.type = 114;
        this.position = i;
        this.length = i2;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public List<SwitchCase> getCases() {
        return this.cases != null ? this.cases : NO_CASES;
    }

    public void setCases(List<SwitchCase> list) {
        if (list == null) {
            this.cases = null;
            return;
        }
        if (this.cases != null) {
            this.cases.clear();
        }
        for (SwitchCase addCase : list) {
            addCase(addCase);
        }
    }

    public void addCase(SwitchCase switchCase) {
        assertNotNull(switchCase);
        if (this.cases == null) {
            this.cases = new ArrayList();
        }
        this.cases.add(switchCase);
        switchCase.setParent(this);
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent);
        stringBuilder.append("switch (");
        stringBuilder.append(this.expression.toSource(0));
        stringBuilder.append(") {\n");
        if (this.cases != null) {
            for (SwitchCase toSource : this.cases) {
                stringBuilder.append(toSource.toSource(i + 1));
            }
        }
        stringBuilder.append(makeIndent);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
            for (SwitchCase visit : getCases()) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
