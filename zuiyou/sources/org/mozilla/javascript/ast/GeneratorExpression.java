package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.List;

public class GeneratorExpression extends Scope {
    private AstNode filter;
    private int ifPosition;
    private List<GeneratorExpressionLoop> loops;
    private int lp;
    private AstNode result;
    private int rp;

    public GeneratorExpression() {
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 162;
    }

    public GeneratorExpression(int i) {
        super(i);
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 162;
    }

    public GeneratorExpression(int i, int i2) {
        super(i, i2);
        this.loops = new ArrayList();
        this.ifPosition = -1;
        this.lp = -1;
        this.rp = -1;
        this.type = 162;
    }

    public AstNode getResult() {
        return this.result;
    }

    public void setResult(AstNode astNode) {
        assertNotNull(astNode);
        this.result = astNode;
        astNode.setParent(this);
    }

    public List<GeneratorExpressionLoop> getLoops() {
        return this.loops;
    }

    public void setLoops(List<GeneratorExpressionLoop> list) {
        assertNotNull(list);
        this.loops.clear();
        for (GeneratorExpressionLoop addLoop : list) {
            addLoop(addLoop);
        }
    }

    public void addLoop(GeneratorExpressionLoop generatorExpressionLoop) {
        assertNotNull(generatorExpressionLoop);
        this.loops.add(generatorExpressionLoop);
        generatorExpressionLoop.setParent(this);
    }

    public AstNode getFilter() {
        return this.filter;
    }

    public void setFilter(AstNode astNode) {
        this.filter = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getIfPosition() {
        return this.ifPosition;
    }

    public void setIfPosition(int i) {
        this.ifPosition = i;
    }

    public int getFilterLp() {
        return this.lp;
    }

    public void setFilterLp(int i) {
        this.lp = i;
    }

    public int getFilterRp() {
        return this.rp;
    }

    public void setFilterRp(int i) {
        this.rp = i;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder(250);
        stringBuilder.append("(");
        stringBuilder.append(this.result.toSource(0));
        for (GeneratorExpressionLoop toSource : this.loops) {
            stringBuilder.append(toSource.toSource(0));
        }
        if (this.filter != null) {
            stringBuilder.append(" if (");
            stringBuilder.append(this.filter.toSource(0));
            stringBuilder.append(")");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.result.visit(nodeVisitor);
            for (GeneratorExpressionLoop visit : this.loops) {
                visit.visit(nodeVisitor);
            }
            if (this.filter != null) {
                this.filter.visit(nodeVisitor);
            }
        }
    }
}
