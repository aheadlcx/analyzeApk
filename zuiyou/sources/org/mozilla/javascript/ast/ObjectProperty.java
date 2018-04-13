package org.mozilla.javascript.ast;

public class ObjectProperty extends InfixExpression {
    public void setNodeType(int i) {
        if (i == 103 || i == 151 || i == 152) {
            setType(i);
            return;
        }
        throw new IllegalArgumentException("invalid node type: " + i);
    }

    public ObjectProperty() {
        this.type = 103;
    }

    public ObjectProperty(int i) {
        super(i);
        this.type = 103;
    }

    public ObjectProperty(int i, int i2) {
        super(i, i2);
        this.type = 103;
    }

    public void setIsGetter() {
        this.type = 151;
    }

    public boolean isGetter() {
        return this.type == 151;
    }

    public void setIsSetter() {
        this.type = 152;
    }

    public boolean isSetter() {
        return this.type == 152;
    }

    public String toSource(int i) {
        int i2;
        int i3 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(makeIndent(i + 1));
        if (isGetter()) {
            stringBuilder.append("get ");
        } else if (isSetter()) {
            stringBuilder.append("set ");
        }
        AstNode astNode = this.left;
        if (getType() == 103) {
            i2 = 0;
        } else {
            i2 = i;
        }
        stringBuilder.append(astNode.toSource(i2));
        if (this.type == 103) {
            stringBuilder.append(": ");
        }
        AstNode astNode2 = this.right;
        if (getType() != 103) {
            i3 = i + 1;
        }
        stringBuilder.append(astNode2.toSource(i3));
        return stringBuilder.toString();
    }
}
