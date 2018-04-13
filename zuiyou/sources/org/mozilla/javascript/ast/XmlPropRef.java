package org.mozilla.javascript.ast;

public class XmlPropRef extends XmlRef {
    private Name propName;

    public XmlPropRef() {
        this.type = 79;
    }

    public XmlPropRef(int i) {
        super(i);
        this.type = 79;
    }

    public XmlPropRef(int i, int i2) {
        super(i, i2);
        this.type = 79;
    }

    public Name getPropName() {
        return this.propName;
    }

    public void setPropName(Name name) {
        assertNotNull(name);
        this.propName = name;
        name.setParent(this);
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        if (isAttributeAccess()) {
            stringBuilder.append("@");
        }
        if (this.namespace != null) {
            stringBuilder.append(this.namespace.toSource(0));
            stringBuilder.append("::");
        }
        stringBuilder.append(this.propName.toSource(0));
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            if (this.namespace != null) {
                this.namespace.visit(nodeVisitor);
            }
            this.propName.visit(nodeVisitor);
        }
    }
}
