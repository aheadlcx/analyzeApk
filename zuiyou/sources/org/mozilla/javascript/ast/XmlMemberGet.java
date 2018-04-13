package org.mozilla.javascript.ast;

public class XmlMemberGet extends InfixExpression {
    public XmlMemberGet() {
        this.type = 143;
    }

    public XmlMemberGet(int i) {
        super(i);
        this.type = 143;
    }

    public XmlMemberGet(int i, int i2) {
        super(i, i2);
        this.type = 143;
    }

    public XmlMemberGet(int i, int i2, AstNode astNode, XmlRef xmlRef) {
        super(i, i2, astNode, (AstNode) xmlRef);
        this.type = 143;
    }

    public XmlMemberGet(AstNode astNode, XmlRef xmlRef) {
        super(astNode, (AstNode) xmlRef);
        this.type = 143;
    }

    public XmlMemberGet(AstNode astNode, XmlRef xmlRef, int i) {
        super(143, astNode, (AstNode) xmlRef, i);
        this.type = 143;
    }

    public AstNode getTarget() {
        return getLeft();
    }

    public void setTarget(AstNode astNode) {
        setLeft(astNode);
    }

    public XmlRef getMemberRef() {
        return (XmlRef) getRight();
    }

    public void setProperty(XmlRef xmlRef) {
        setRight(xmlRef);
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append(getLeft().toSource(0));
        stringBuilder.append(AstNode.operatorToString(getType()));
        stringBuilder.append(getRight().toSource(0));
        return stringBuilder.toString();
    }
}
