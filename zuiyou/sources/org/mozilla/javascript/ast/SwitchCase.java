package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.List;

public class SwitchCase extends AstNode {
    private AstNode expression;
    private List<AstNode> statements;

    public SwitchCase() {
        this.type = 115;
    }

    public SwitchCase(int i) {
        super(i);
        this.type = 115;
    }

    public SwitchCase(int i, int i2) {
        super(i, i2);
        this.type = 115;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        this.expression = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public boolean isDefault() {
        return this.expression == null;
    }

    public List<AstNode> getStatements() {
        return this.statements;
    }

    public void setStatements(List<AstNode> list) {
        if (this.statements != null) {
            this.statements.clear();
        }
        for (AstNode addStatement : list) {
            addStatement(addStatement);
        }
    }

    public void addStatement(AstNode astNode) {
        assertNotNull(astNode);
        if (this.statements == null) {
            this.statements = new ArrayList();
        }
        setLength((astNode.getPosition() + astNode.getLength()) - getPosition());
        this.statements.add(astNode);
        astNode.setParent(this);
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        if (this.expression == null) {
            stringBuilder.append("default:\n");
        } else {
            stringBuilder.append("case ");
            stringBuilder.append(this.expression.toSource(0));
            stringBuilder.append(":\n");
        }
        if (this.statements != null) {
            for (AstNode toSource : this.statements) {
                stringBuilder.append(toSource.toSource(i + 1));
            }
        }
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            if (this.expression != null) {
                this.expression.visit(nodeVisitor);
            }
            if (this.statements != null) {
                for (AstNode visit : this.statements) {
                    visit.visit(nodeVisitor);
                }
            }
        }
    }
}
