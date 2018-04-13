package org.mozilla.javascript.ast;

public class XmlDotQuery extends InfixExpression {
    private int rp;

    public XmlDotQuery() {
        this.rp = -1;
        this.type = 146;
    }

    public XmlDotQuery(int i) {
        super(i);
        this.rp = -1;
        this.type = 146;
    }

    public XmlDotQuery(int i, int i2) {
        super(i, i2);
        this.rp = -1;
        this.type = 146;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int i) {
        this.rp = i;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append(getLeft().toSource(0));
        stringBuilder.append(".(");
        stringBuilder.append(getRight().toSource(0));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
