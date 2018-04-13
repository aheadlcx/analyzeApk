package org.mozilla.javascript;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

public class Node implements Iterable<Node> {
    public static final int ATTRIBUTE_FLAG = 2;
    public static final int BOTH = 0;
    public static final int CASEARRAY_PROP = 5;
    public static final int CATCH_SCOPE_PROP = 14;
    public static final int CONTROL_BLOCK_PROP = 18;
    public static final int DECR_FLAG = 1;
    public static final int DESCENDANTS_FLAG = 4;
    public static final int DESTRUCTURING_ARRAY_LENGTH = 21;
    public static final int DESTRUCTURING_NAMES = 22;
    public static final int DESTRUCTURING_PARAMS = 23;
    public static final int DESTRUCTURING_SHORTHAND = 26;
    public static final int DIRECTCALL_PROP = 9;
    public static final int END_DROPS_OFF = 1;
    public static final int END_RETURNS = 2;
    public static final int END_RETURNS_VALUE = 4;
    public static final int END_UNREACHED = 0;
    public static final int END_YIELDS = 8;
    public static final int EXPRESSION_CLOSURE_PROP = 25;
    public static final int FUNCTION_PROP = 1;
    public static final int GENERATOR_END_PROP = 20;
    public static final int INCRDECR_PROP = 13;
    public static final int ISNUMBER_PROP = 8;
    public static final int JSDOC_PROP = 24;
    public static final int LABEL_ID_PROP = 15;
    public static final int LAST_PROP = 26;
    public static final int LEFT = 1;
    public static final int LOCAL_BLOCK_PROP = 3;
    public static final int LOCAL_PROP = 2;
    public static final int MEMBER_TYPE_PROP = 16;
    public static final int NAME_PROP = 17;
    public static final int NON_SPECIALCALL = 0;
    private static final Node NOT_SET = new Node(-1);
    public static final int OBJECT_IDS_PROP = 12;
    public static final int PARENTHESIZED_PROP = 19;
    public static final int POST_FLAG = 2;
    public static final int PROPERTY_FLAG = 1;
    public static final int REGEXP_PROP = 4;
    public static final int RIGHT = 2;
    public static final int SKIP_INDEXES_PROP = 11;
    public static final int SPECIALCALL_EVAL = 1;
    public static final int SPECIALCALL_PROP = 10;
    public static final int SPECIALCALL_WITH = 2;
    public static final int TARGETBLOCK_PROP = 6;
    public static final int VARIABLE_PROP = 7;
    protected Node first;
    protected Node last;
    protected int lineno;
    protected Node next;
    protected PropListItem propListHead;
    protected int type;

    public class NodeIterator implements Iterator<Node> {
        private Node cursor;
        private Node prev = Node.NOT_SET;
        private Node prev2;
        private boolean removed = false;

        public NodeIterator() {
            this.cursor = Node.this.first;
        }

        public boolean hasNext() {
            return this.cursor != null;
        }

        public Node next() {
            if (this.cursor == null) {
                throw new NoSuchElementException();
            }
            this.removed = false;
            this.prev2 = this.prev;
            this.prev = this.cursor;
            this.cursor = this.cursor.next;
            return this.prev;
        }

        public void remove() {
            if (this.prev == Node.NOT_SET) {
                throw new IllegalStateException("next() has not been called");
            } else if (this.removed) {
                throw new IllegalStateException("remove() already called for current element");
            } else if (this.prev == Node.this.first) {
                Node.this.first = this.prev.next;
            } else if (this.prev == Node.this.last) {
                this.prev2.next = null;
                Node.this.last = this.prev2;
            } else {
                this.prev2.next = this.cursor;
            }
        }
    }

    private static class PropListItem {
        int intValue;
        PropListItem next;
        Object objectValue;
        int type;

        private PropListItem() {
        }
    }

    public Node(int i) {
        this.type = -1;
        this.lineno = -1;
        this.type = i;
    }

    public Node(int i, Node node) {
        this.type = -1;
        this.lineno = -1;
        this.type = i;
        this.last = node;
        this.first = node;
        node.next = null;
    }

    public Node(int i, Node node, Node node2) {
        this.type = -1;
        this.lineno = -1;
        this.type = i;
        this.first = node;
        this.last = node2;
        node.next = node2;
        node2.next = null;
    }

    public Node(int i, Node node, Node node2, Node node3) {
        this.type = -1;
        this.lineno = -1;
        this.type = i;
        this.first = node;
        this.last = node3;
        node.next = node2;
        node2.next = node3;
        node3.next = null;
    }

    public Node(int i, int i2) {
        this.type = -1;
        this.lineno = -1;
        this.type = i;
        this.lineno = i2;
    }

    public Node(int i, Node node, int i2) {
        this(i, node);
        this.lineno = i2;
    }

    public Node(int i, Node node, Node node2, int i2) {
        this(i, node, node2);
        this.lineno = i2;
    }

    public Node(int i, Node node, Node node2, Node node3, int i2) {
        this(i, node, node2, node3);
        this.lineno = i2;
    }

    public static Node newNumber(double d) {
        Node numberLiteral = new NumberLiteral();
        numberLiteral.setNumber(d);
        return numberLiteral;
    }

    public static Node newString(String str) {
        return newString(41, str);
    }

    public static Node newString(int i, String str) {
        Node name = new Name();
        name.setIdentifier(str);
        name.setType(i);
        return name;
    }

    public int getType() {
        return this.type;
    }

    public Node setType(int i) {
        this.type = i;
        return this;
    }

    public String getJsDoc() {
        Comment jsDocNode = getJsDocNode();
        if (jsDocNode != null) {
            return jsDocNode.getValue();
        }
        return null;
    }

    public Comment getJsDocNode() {
        return (Comment) getProp(24);
    }

    public void setJsDocNode(Comment comment) {
        putProp(24, comment);
    }

    public boolean hasChildren() {
        return this.first != null;
    }

    public Node getFirstChild() {
        return this.first;
    }

    public Node getLastChild() {
        return this.last;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getChildBefore(Node node) {
        if (node == this.first) {
            return null;
        }
        Node node2 = this.first;
        while (node2.next != node) {
            node2 = node2.next;
            if (node2 == null) {
                throw new RuntimeException("node is not a child");
            }
        }
        return node2;
    }

    public Node getLastSibling() {
        while (this.next != null) {
            this = this.next;
        }
        return this;
    }

    public void addChildToFront(Node node) {
        node.next = this.first;
        this.first = node;
        if (this.last == null) {
            this.last = node;
        }
    }

    public void addChildToBack(Node node) {
        node.next = null;
        if (this.last == null) {
            this.last = node;
            this.first = node;
            return;
        }
        this.last.next = node;
        this.last = node;
    }

    public void addChildrenToFront(Node node) {
        Node lastSibling = node.getLastSibling();
        lastSibling.next = this.first;
        this.first = node;
        if (this.last == null) {
            this.last = lastSibling;
        }
    }

    public void addChildrenToBack(Node node) {
        if (this.last != null) {
            this.last.next = node;
        }
        this.last = node.getLastSibling();
        if (this.first == null) {
            this.first = node;
        }
    }

    public void addChildBefore(Node node, Node node2) {
        if (node.next != null) {
            throw new RuntimeException("newChild had siblings in addChildBefore");
        } else if (this.first == node2) {
            node.next = this.first;
            this.first = node;
        } else {
            addChildAfter(node, getChildBefore(node2));
        }
    }

    public void addChildAfter(Node node, Node node2) {
        if (node.next != null) {
            throw new RuntimeException("newChild had siblings in addChildAfter");
        }
        node.next = node2.next;
        node2.next = node;
        if (this.last == node2) {
            this.last = node;
        }
    }

    public void removeChild(Node node) {
        Node childBefore = getChildBefore(node);
        if (childBefore == null) {
            this.first = this.first.next;
        } else {
            childBefore.next = node.next;
        }
        if (node == this.last) {
            this.last = childBefore;
        }
        node.next = null;
    }

    public void replaceChild(Node node, Node node2) {
        node2.next = node.next;
        if (node == this.first) {
            this.first = node2;
        } else {
            getChildBefore(node).next = node2;
        }
        if (node == this.last) {
            this.last = node2;
        }
        node.next = null;
    }

    public void replaceChildAfter(Node node, Node node2) {
        Node node3 = node.next;
        node2.next = node3.next;
        node.next = node2;
        if (node3 == this.last) {
            this.last = node2;
        }
        node3.next = null;
    }

    public void removeChildren() {
        this.last = null;
        this.first = null;
    }

    public Iterator<Node> iterator() {
        return new NodeIterator();
    }

    private static final String propToString(int i) {
        return null;
    }

    private PropListItem lookupProperty(int i) {
        PropListItem propListItem = this.propListHead;
        while (propListItem != null && i != propListItem.type) {
            propListItem = propListItem.next;
        }
        return propListItem;
    }

    private PropListItem ensureProperty(int i) {
        PropListItem lookupProperty = lookupProperty(i);
        if (lookupProperty != null) {
            return lookupProperty;
        }
        lookupProperty = new PropListItem();
        lookupProperty.type = i;
        lookupProperty.next = this.propListHead;
        this.propListHead = lookupProperty;
        return lookupProperty;
    }

    public void removeProp(int i) {
        PropListItem propListItem = this.propListHead;
        if (propListItem != null) {
            PropListItem propListItem2 = null;
            while (propListItem.type != i) {
                propListItem2 = propListItem.next;
                if (propListItem2 != null) {
                    PropListItem propListItem3 = propListItem;
                    propListItem = propListItem2;
                    propListItem2 = propListItem3;
                } else {
                    return;
                }
            }
            if (propListItem2 == null) {
                this.propListHead = propListItem.next;
            } else {
                propListItem2.next = propListItem.next;
            }
        }
    }

    public Object getProp(int i) {
        PropListItem lookupProperty = lookupProperty(i);
        if (lookupProperty == null) {
            return null;
        }
        return lookupProperty.objectValue;
    }

    public int getIntProp(int i, int i2) {
        PropListItem lookupProperty = lookupProperty(i);
        return lookupProperty == null ? i2 : lookupProperty.intValue;
    }

    public int getExistingIntProp(int i) {
        PropListItem lookupProperty = lookupProperty(i);
        if (lookupProperty == null) {
            Kit.codeBug();
        }
        return lookupProperty.intValue;
    }

    public void putProp(int i, Object obj) {
        if (obj == null) {
            removeProp(i);
        } else {
            ensureProperty(i).objectValue = obj;
        }
    }

    public void putIntProp(int i, int i2) {
        ensureProperty(i).intValue = i2;
    }

    public int getLineno() {
        return this.lineno;
    }

    public void setLineno(int i) {
        this.lineno = i;
    }

    public final double getDouble() {
        return ((NumberLiteral) this).getNumber();
    }

    public final void setDouble(double d) {
        ((NumberLiteral) this).setNumber(d);
    }

    public final String getString() {
        return ((Name) this).getIdentifier();
    }

    public final void setString(String str) {
        if (str == null) {
            Kit.codeBug();
        }
        ((Name) this).setIdentifier(str);
    }

    public Scope getScope() {
        return ((Name) this).getScope();
    }

    public void setScope(Scope scope) {
        if (scope == null) {
            Kit.codeBug();
        }
        if (this instanceof Name) {
            ((Name) this).setScope(scope);
            return;
        }
        throw Kit.codeBug();
    }

    public static Node newTarget() {
        return new Node(131);
    }

    public final int labelId() {
        if (!(this.type == 131 || this.type == 72)) {
            Kit.codeBug();
        }
        return getIntProp(15, -1);
    }

    public void labelId(int i) {
        if (!(this.type == 131 || this.type == 72)) {
            Kit.codeBug();
        }
        putIntProp(15, i);
    }

    public boolean hasConsistentReturnUsage() {
        int endCheck = endCheck();
        return (endCheck & 4) == 0 || (endCheck & 11) == 0;
    }

    private int endCheckIf() {
        Node node = this.next;
        Node node2 = ((Jump) this).target;
        int endCheck = node.endCheck();
        if (node2 != null) {
            return endCheck | node2.endCheck();
        }
        return endCheck | 1;
    }

    private int endCheckSwitch() {
        return 0;
    }

    private int endCheckTry() {
        return 0;
    }

    private int endCheckLoop() {
        Node node = this.first;
        while (node.next != this.last) {
            node = node.next;
        }
        if (node.type != 6) {
            return 1;
        }
        int endCheck = ((Jump) node).target.next.endCheck();
        if (node.first.type == 45) {
            endCheck &= -2;
        }
        return endCheck | getIntProp(18, 0);
    }

    private int endCheckBlock() {
        int i = 1;
        Node node = this.first;
        while ((i & 1) != 0 && node != null) {
            i = (i & -2) | node.endCheck();
            node = node.next;
        }
        return i;
    }

    private int endCheckLabel() {
        return this.next.endCheck() | getIntProp(18, 0);
    }

    private int endCheckBreak() {
        ((Jump) this).getJumpStatement().putIntProp(18, 1);
        return 0;
    }

    private int endCheck() {
        switch (this.type) {
            case 4:
                if (this.first != null) {
                    return 4;
                }
                return 2;
            case 50:
            case 121:
                return 0;
            case 72:
                return 8;
            case 120:
                return endCheckBreak();
            case 129:
            case 141:
                if (this.first == null) {
                    return 1;
                }
                switch (this.first.type) {
                    case 7:
                        return this.first.endCheckIf();
                    case 81:
                        return this.first.endCheckTry();
                    case 114:
                        return this.first.endCheckSwitch();
                    case 130:
                        return this.first.endCheckLabel();
                    default:
                        return endCheckBlock();
                }
            case 131:
                if (this.next != null) {
                    return this.next.endCheck();
                }
                return 1;
            case 132:
                return endCheckLoop();
            case 133:
                if (this.first != null) {
                    return this.first.endCheck();
                }
                return 1;
            default:
                return 1;
        }
    }

    public boolean hasSideEffects() {
        boolean z = false;
        switch (this.type) {
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
            case 89:
            case 133:
                if (this.last != null) {
                    return this.last.hasSideEffects();
                }
                return true;
            case 102:
                if (this.first == null || this.first.next == null || this.first.next.next == null) {
                    Kit.codeBug();
                }
                if (this.first.next.hasSideEffects() && this.first.next.next.hasSideEffects()) {
                    return true;
                }
                return false;
            case 104:
            case 105:
                if (this.first == null || this.last == null) {
                    Kit.codeBug();
                }
                if (this.first.hasSideEffects() || this.last.hasSideEffects()) {
                    z = true;
                }
                return z;
            default:
                return false;
        }
    }

    public void resetTargets() {
        if (this.type == 125) {
            resetTargets_r();
        } else {
            Kit.codeBug();
        }
    }

    private void resetTargets_r() {
        if (this.type == 131 || this.type == 72) {
            labelId(-1);
        }
        for (Node node = this.first; node != null; node = node.next) {
            node.resetTargets_r();
        }
    }

    public String toString() {
        return String.valueOf(this.type);
    }

    private void toString(ObjToIntMap objToIntMap, StringBuilder stringBuilder) {
    }

    public String toStringTree(ScriptNode scriptNode) {
        return null;
    }

    private static void toStringTreeHelper(ScriptNode scriptNode, Node node, ObjToIntMap objToIntMap, int i, StringBuilder stringBuilder) {
    }

    private static void generatePrintIds(Node node, ObjToIntMap objToIntMap) {
    }

    private static void appendPrintId(Node node, ObjToIntMap objToIntMap, StringBuilder stringBuilder) {
    }
}
