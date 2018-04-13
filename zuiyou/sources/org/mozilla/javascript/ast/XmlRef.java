package org.mozilla.javascript.ast;

public abstract class XmlRef extends AstNode {
    protected int atPos = -1;
    protected int colonPos = -1;
    protected Name namespace;

    public XmlRef(int i) {
        super(i);
    }

    public XmlRef(int i, int i2) {
        super(i, i2);
    }

    public Name getNamespace() {
        return this.namespace;
    }

    public void setNamespace(Name name) {
        this.namespace = name;
        if (name != null) {
            name.setParent(this);
        }
    }

    public boolean isAttributeAccess() {
        return this.atPos >= 0;
    }

    public int getAtPos() {
        return this.atPos;
    }

    public void setAtPos(int i) {
        this.atPos = i;
    }

    public int getColonPos() {
        return this.colonPos;
    }

    public void setColonPos(int i) {
        this.colonPos = i;
    }
}
