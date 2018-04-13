package org.mozilla.javascript.ast;

public class RegExpLiteral extends AstNode {
    private String flags;
    private String value;

    public RegExpLiteral() {
        this.type = 48;
    }

    public RegExpLiteral(int i) {
        super(i);
        this.type = 48;
    }

    public RegExpLiteral(int i, int i2) {
        super(i, i2);
        this.type = 48;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        assertNotNull(str);
        this.value = str;
    }

    public String getFlags() {
        return this.flags;
    }

    public void setFlags(String str) {
        this.flags = str;
    }

    public String toSource(int i) {
        return makeIndent(i) + "/" + this.value + "/" + (this.flags == null ? "" : this.flags);
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
