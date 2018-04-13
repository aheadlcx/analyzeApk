package org.mozilla.javascript.ast;

public abstract class XmlFragment extends AstNode {
    public XmlFragment() {
        this.type = 145;
    }

    public XmlFragment(int i) {
        super(i);
        this.type = 145;
    }

    public XmlFragment(int i, int i2) {
        super(i, i2);
        this.type = 145;
    }
}
