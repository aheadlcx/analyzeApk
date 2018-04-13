package org.mozilla.javascript.ast;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

public abstract class AstNode extends Node implements Comparable<AstNode> {
    private static Map<Integer, String> operatorNames = new HashMap();
    protected int length;
    protected AstNode parent;
    protected int position;

    protected static class DebugPrintVisitor implements NodeVisitor {
        private static final int DEBUG_INDENT = 2;
        private StringBuilder buffer;

        public DebugPrintVisitor(StringBuilder stringBuilder) {
            this.buffer = stringBuilder;
        }

        public String toString() {
            return this.buffer.toString();
        }

        private String makeIndent(int i) {
            StringBuilder stringBuilder = new StringBuilder(i * 2);
            for (int i2 = 0; i2 < i * 2; i2++) {
                stringBuilder.append(" ");
            }
            return stringBuilder.toString();
        }

        public boolean visit(AstNode astNode) {
            int type = astNode.getType();
            String typeToName = Token.typeToName(type);
            this.buffer.append(astNode.getAbsolutePosition()).append("\t");
            this.buffer.append(makeIndent(astNode.depth()));
            this.buffer.append(typeToName).append(" ");
            this.buffer.append(astNode.getPosition()).append(" ");
            this.buffer.append(astNode.getLength());
            if (type == 39) {
                this.buffer.append(" ").append(((Name) astNode).getIdentifier());
            }
            this.buffer.append("\n");
            return true;
        }
    }

    public static class PositionComparator implements Serializable, Comparator<AstNode> {
        private static final long serialVersionUID = 1;

        public int compare(AstNode astNode, AstNode astNode2) {
            return astNode.position - astNode2.position;
        }
    }

    public abstract String toSource(int i);

    public abstract void visit(NodeVisitor nodeVisitor);

    static {
        operatorNames.put(Integer.valueOf(52), "in");
        operatorNames.put(Integer.valueOf(32), "typeof");
        operatorNames.put(Integer.valueOf(53), "instanceof");
        operatorNames.put(Integer.valueOf(31), RequestParameters.SUBRESOURCE_DELETE);
        operatorNames.put(Integer.valueOf(89), ",");
        operatorNames.put(Integer.valueOf(103), ":");
        operatorNames.put(Integer.valueOf(104), "||");
        operatorNames.put(Integer.valueOf(105), "&&");
        operatorNames.put(Integer.valueOf(106), "++");
        operatorNames.put(Integer.valueOf(107), "--");
        operatorNames.put(Integer.valueOf(9), "|");
        operatorNames.put(Integer.valueOf(10), "^");
        operatorNames.put(Integer.valueOf(11), "&");
        operatorNames.put(Integer.valueOf(12), "==");
        operatorNames.put(Integer.valueOf(13), "!=");
        operatorNames.put(Integer.valueOf(14), "<");
        operatorNames.put(Integer.valueOf(16), ">");
        operatorNames.put(Integer.valueOf(15), "<=");
        operatorNames.put(Integer.valueOf(17), ">=");
        operatorNames.put(Integer.valueOf(18), "<<");
        operatorNames.put(Integer.valueOf(19), ">>");
        operatorNames.put(Integer.valueOf(20), ">>>");
        operatorNames.put(Integer.valueOf(21), "+");
        operatorNames.put(Integer.valueOf(22), "-");
        operatorNames.put(Integer.valueOf(23), "*");
        operatorNames.put(Integer.valueOf(24), "/");
        operatorNames.put(Integer.valueOf(25), "%");
        operatorNames.put(Integer.valueOf(26), "!");
        operatorNames.put(Integer.valueOf(27), "~");
        operatorNames.put(Integer.valueOf(28), "+");
        operatorNames.put(Integer.valueOf(29), "-");
        operatorNames.put(Integer.valueOf(46), "===");
        operatorNames.put(Integer.valueOf(47), "!==");
        operatorNames.put(Integer.valueOf(90), "=");
        operatorNames.put(Integer.valueOf(91), "|=");
        operatorNames.put(Integer.valueOf(93), "&=");
        operatorNames.put(Integer.valueOf(94), "<<=");
        operatorNames.put(Integer.valueOf(95), ">>=");
        operatorNames.put(Integer.valueOf(96), ">>>=");
        operatorNames.put(Integer.valueOf(97), "+=");
        operatorNames.put(Integer.valueOf(98), "-=");
        operatorNames.put(Integer.valueOf(99), "*=");
        operatorNames.put(Integer.valueOf(100), "/=");
        operatorNames.put(Integer.valueOf(101), "%=");
        operatorNames.put(Integer.valueOf(92), "^=");
        operatorNames.put(Integer.valueOf(126), "void");
    }

    public AstNode() {
        super(-1);
        this.position = -1;
        this.length = 1;
    }

    public AstNode(int i) {
        this();
        this.position = i;
    }

    public AstNode(int i, int i2) {
        this();
        this.position = i;
        this.length = i2;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int getAbsolutePosition() {
        int i = this.position;
        for (AstNode astNode = this.parent; astNode != null; astNode = astNode.getParent()) {
            i += astNode.getPosition();
        }
        return i;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public void setBounds(int i, int i2) {
        setPosition(i);
        setLength(i2 - i);
    }

    public void setRelative(int i) {
        this.position -= i;
    }

    public AstNode getParent() {
        return this.parent;
    }

    public void setParent(AstNode astNode) {
        if (astNode != this.parent) {
            if (this.parent != null) {
                setRelative(-this.parent.getPosition());
            }
            this.parent = astNode;
            if (astNode != null) {
                setRelative(astNode.getPosition());
            }
        }
    }

    public void addChild(AstNode astNode) {
        assertNotNull(astNode);
        setLength((astNode.getPosition() + astNode.getLength()) - getPosition());
        addChildToBack(astNode);
        astNode.setParent(this);
    }

    public AstRoot getAstRoot() {
        AstNode astNode = this;
        while (astNode != null && !(astNode instanceof AstRoot)) {
            astNode = astNode.getParent();
        }
        return (AstRoot) astNode;
    }

    public String toSource() {
        return toSource(0);
    }

    public String makeIndent(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }

    public String shortName() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public static String operatorToString(int i) {
        String str = (String) operatorNames.get(Integer.valueOf(i));
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Invalid operator: " + i);
    }

    public boolean hasSideEffects() {
        switch (getType()) {
            case -1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 30:
            case 31:
            case 35:
            case 37:
            case 38:
            case 50:
            case 51:
            case 56:
            case 57:
            case 64:
            case 68:
            case 69:
            case 70:
            case 72:
            case 81:
            case 82:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 106:
            case 107:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 129:
            case 130:
            case 131:
            case 132:
            case 134:
            case 135:
            case 139:
            case 140:
            case 141:
            case 142:
            case 153:
            case 154:
            case 158:
            case 159:
                return true;
            default:
                return false;
        }
    }

    protected void assertNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("arg cannot be null");
        }
    }

    protected <T extends AstNode> void printList(List<T> list, StringBuilder stringBuilder) {
        int size = list.size();
        int i = 0;
        for (T t : list) {
            stringBuilder.append(t.toSource(0));
            int i2 = i + 1;
            if (i < size - 1) {
                stringBuilder.append(", ");
            } else if (t instanceof EmptyExpression) {
                stringBuilder.append(",");
            }
            i = i2;
        }
    }

    public static RuntimeException codeBug() throws RuntimeException {
        throw Kit.codeBug();
    }

    public FunctionNode getEnclosingFunction() {
        AstNode parent = getParent();
        while (parent != null && !(parent instanceof FunctionNode)) {
            parent = parent.getParent();
        }
        return (FunctionNode) parent;
    }

    public Scope getEnclosingScope() {
        AstNode parent = getParent();
        while (parent != null && !(parent instanceof Scope)) {
            parent = parent.getParent();
        }
        return (Scope) parent;
    }

    public int compareTo(AstNode astNode) {
        if (equals(astNode)) {
            return 0;
        }
        int absolutePosition = getAbsolutePosition();
        int absolutePosition2 = astNode.getAbsolutePosition();
        if (absolutePosition < absolutePosition2) {
            return -1;
        }
        if (absolutePosition2 < absolutePosition) {
            return 1;
        }
        absolutePosition = getLength();
        absolutePosition2 = astNode.getLength();
        if (absolutePosition < absolutePosition2) {
            return -1;
        }
        if (absolutePosition2 < absolutePosition) {
            return 1;
        }
        return hashCode() - astNode.hashCode();
    }

    public int depth() {
        return this.parent == null ? 0 : this.parent.depth() + 1;
    }

    public int getLineno() {
        if (this.lineno != -1) {
            return this.lineno;
        }
        if (this.parent != null) {
            return this.parent.getLineno();
        }
        return -1;
    }

    public String debugPrint() {
        Object debugPrintVisitor = new DebugPrintVisitor(new StringBuilder(1000));
        visit(debugPrintVisitor);
        return debugPrintVisitor.toString();
    }
}
