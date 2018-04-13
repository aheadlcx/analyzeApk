package org.mozilla.javascript.ast;

public class KeywordLiteral extends AstNode {
    public KeywordLiteral(int i) {
        super(i);
    }

    public KeywordLiteral(int i, int i2) {
        super(i, i2);
    }

    public KeywordLiteral(int i, int i2, int i3) {
        super(i, i2);
        setType(i3);
    }

    public KeywordLiteral setType(int i) {
        if (i == 43 || i == 42 || i == 45 || i == 44 || i == 160) {
            this.type = i;
            return this;
        }
        throw new IllegalArgumentException("Invalid node type: " + i);
    }

    public boolean isBooleanLiteral() {
        return this.type == 45 || this.type == 44;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        switch (getType()) {
            case 42:
                stringBuilder.append("null");
                break;
            case 43:
                stringBuilder.append("this");
                break;
            case 44:
                stringBuilder.append("false");
                break;
            case 45:
                stringBuilder.append("true");
                break;
            case 160:
                stringBuilder.append("debugger;\n");
                break;
            default:
                throw new IllegalStateException("Invalid keyword literal type: " + getType());
        }
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
