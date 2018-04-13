package org.mozilla.javascript.ast;

public class XmlString extends XmlFragment {
    private String xml;

    public XmlString(int i) {
        super(i);
    }

    public XmlString(int i, String str) {
        super(i);
        setXml(str);
    }

    public void setXml(String str) {
        assertNotNull(str);
        this.xml = str;
        setLength(str.length());
    }

    public String getXml() {
        return this.xml;
    }

    public String toSource(int i) {
        return makeIndent(i) + this.xml;
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
