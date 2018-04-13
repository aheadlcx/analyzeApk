package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayLiteral extends AstNode implements DestructuringForm {
    private static final List<AstNode> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
    private int destructuringLength;
    private List<AstNode> elements;
    private boolean isDestructuring;
    private int skipCount;

    public ArrayLiteral() {
        this.type = 65;
    }

    public ArrayLiteral(int i) {
        super(i);
        this.type = 65;
    }

    public ArrayLiteral(int i, int i2) {
        super(i, i2);
        this.type = 65;
    }

    public List<AstNode> getElements() {
        return this.elements != null ? this.elements : NO_ELEMS;
    }

    public void setElements(List<AstNode> list) {
        if (list == null) {
            this.elements = null;
            return;
        }
        if (this.elements != null) {
            this.elements.clear();
        }
        for (AstNode addElement : list) {
            addElement(addElement);
        }
    }

    public void addElement(AstNode astNode) {
        assertNotNull(astNode);
        if (this.elements == null) {
            this.elements = new ArrayList();
        }
        this.elements.add(astNode);
        astNode.setParent(this);
    }

    public int getSize() {
        return this.elements == null ? 0 : this.elements.size();
    }

    public AstNode getElement(int i) {
        if (this.elements != null) {
            return (AstNode) this.elements.get(i);
        }
        throw new IndexOutOfBoundsException("no elements");
    }

    public int getDestructuringLength() {
        return this.destructuringLength;
    }

    public void setDestructuringLength(int i) {
        this.destructuringLength = i;
    }

    public int getSkipCount() {
        return this.skipCount;
    }

    public void setSkipCount(int i) {
        this.skipCount = i;
    }

    public void setIsDestructuring(boolean z) {
        this.isDestructuring = z;
    }

    public boolean isDestructuring() {
        return this.isDestructuring;
    }

    public String toSource(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeIndent(i));
        stringBuilder.append("[");
        if (this.elements != null) {
            printList(this.elements, stringBuilder);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            for (AstNode visit : getElements()) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
