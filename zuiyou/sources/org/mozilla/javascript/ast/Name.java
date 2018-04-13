package org.mozilla.javascript.ast;

public class Name extends AstNode {
    private String identifier;
    private Scope scope;

    public Name() {
        this.type = 39;
    }

    public Name(int i) {
        super(i);
        this.type = 39;
    }

    public Name(int i, int i2) {
        super(i, i2);
        this.type = 39;
    }

    public Name(int i, int i2, String str) {
        super(i, i2);
        this.type = 39;
        setIdentifier(str);
    }

    public Name(int i, String str) {
        super(i);
        this.type = 39;
        setIdentifier(str);
        setLength(str.length());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        assertNotNull(str);
        this.identifier = str;
        setLength(str.length());
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return this.scope;
    }

    public Scope getDefiningScope() {
        Scope enclosingScope = getEnclosingScope();
        return enclosingScope == null ? null : enclosingScope.getDefiningScope(getIdentifier());
    }

    public boolean isLocalName() {
        Scope definingScope = getDefiningScope();
        return (definingScope == null || definingScope.getParentScope() == null) ? false : true;
    }

    public int length() {
        return this.identifier == null ? 0 : this.identifier.length();
    }

    public String toSource(int i) {
        return makeIndent(i) + (this.identifier == null ? "<null>" : this.identifier);
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
