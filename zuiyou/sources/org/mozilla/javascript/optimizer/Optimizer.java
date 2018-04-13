package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ast.ScriptNode;

class Optimizer {
    static final int AnyType = 3;
    static final int NoType = 0;
    static final int NumberType = 1;
    private boolean inDirectCallFunction;
    private boolean parameterUsedInNumberContext;
    OptFunctionNode theFunction;

    Optimizer() {
    }

    void optimize(ScriptNode scriptNode) {
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            optimizeFunction(OptFunctionNode.get(scriptNode, i));
        }
    }

    private void optimizeFunction(OptFunctionNode optFunctionNode) {
        int i = 0;
        if (!optFunctionNode.fnode.requiresActivation()) {
            this.inDirectCallFunction = optFunctionNode.isTargetOfDirectCall();
            this.theFunction = optFunctionNode;
            ObjArray objArray = new ObjArray();
            buildStatementList_r(optFunctionNode.fnode, objArray);
            Node[] nodeArr = new Node[objArray.size()];
            objArray.toArray(nodeArr);
            Block.runFlowAnalyzes(optFunctionNode, nodeArr);
            if (!optFunctionNode.fnode.requiresActivation()) {
                this.parameterUsedInNumberContext = false;
                int length = nodeArr.length;
                while (i < length) {
                    rewriteForNumberVariables(nodeArr[i], 1);
                    i++;
                }
                optFunctionNode.setParameterNumberContext(this.parameterUsedInNumberContext);
            }
        }
    }

    private void markDCPNumberContext(Node node) {
        if (this.inDirectCallFunction && node.getType() == 55) {
            if (this.theFunction.isParameter(this.theFunction.getVarIndex(node))) {
                this.parameterUsedInNumberContext = true;
            }
        }
    }

    private boolean convertParameter(Node node) {
        if (this.inDirectCallFunction && node.getType() == 55) {
            if (this.theFunction.isParameter(this.theFunction.getVarIndex(node))) {
                node.removeProp(8);
                return true;
            }
        }
        return false;
    }

    private int rewriteForNumberVariables(Node node, int i) {
        Node firstChild;
        Node next;
        int rewriteForNumberVariables;
        int rewriteForNumberVariables2;
        int varIndex;
        switch (node.getType()) {
            case 9:
            case 10:
            case 11:
            case 18:
            case 19:
            case 22:
            case 23:
            case 24:
            case 25:
                firstChild = node.getFirstChild();
                next = firstChild.getNext();
                rewriteForNumberVariables = rewriteForNumberVariables(firstChild, 1);
                rewriteForNumberVariables2 = rewriteForNumberVariables(next, 1);
                markDCPNumberContext(firstChild);
                markDCPNumberContext(next);
                if (rewriteForNumberVariables == 1) {
                    if (rewriteForNumberVariables2 == 1) {
                        node.putIntProp(8, 0);
                        return 1;
                    }
                    if (!convertParameter(next)) {
                        node.removeChild(next);
                        node.addChildToBack(new Node(150, next));
                        node.putIntProp(8, 0);
                    }
                    return 1;
                } else if (rewriteForNumberVariables2 == 1) {
                    if (!convertParameter(firstChild)) {
                        node.removeChild(firstChild);
                        node.addChildToFront(new Node(150, firstChild));
                        node.putIntProp(8, 0);
                    }
                    return 1;
                } else {
                    if (!convertParameter(firstChild)) {
                        node.removeChild(firstChild);
                        node.addChildToFront(new Node(150, firstChild));
                    }
                    if (!convertParameter(next)) {
                        node.removeChild(next);
                        node.addChildToBack(new Node(150, next));
                    }
                    node.putIntProp(8, 0);
                    return 1;
                }
            case 14:
            case 15:
            case 16:
            case 17:
                firstChild = node.getFirstChild();
                next = firstChild.getNext();
                rewriteForNumberVariables = rewriteForNumberVariables(firstChild, 1);
                rewriteForNumberVariables2 = rewriteForNumberVariables(next, 1);
                markDCPNumberContext(firstChild);
                markDCPNumberContext(next);
                if (convertParameter(firstChild)) {
                    if (convertParameter(next)) {
                        return 0;
                    }
                    if (rewriteForNumberVariables2 == 1) {
                        node.putIntProp(8, 2);
                    }
                } else if (convertParameter(next)) {
                    if (rewriteForNumberVariables == 1) {
                        node.putIntProp(8, 1);
                    }
                } else if (rewriteForNumberVariables == 1) {
                    if (rewriteForNumberVariables2 == 1) {
                        node.putIntProp(8, 0);
                    } else {
                        node.putIntProp(8, 1);
                    }
                } else if (rewriteForNumberVariables2 == 1) {
                    node.putIntProp(8, 2);
                }
                return 0;
            case 21:
                firstChild = node.getFirstChild();
                next = firstChild.getNext();
                rewriteForNumberVariables = rewriteForNumberVariables(firstChild, 1);
                rewriteForNumberVariables2 = rewriteForNumberVariables(next, 1);
                if (convertParameter(firstChild)) {
                    if (convertParameter(next)) {
                        return 0;
                    }
                    if (rewriteForNumberVariables2 == 1) {
                        node.putIntProp(8, 2);
                    }
                } else if (convertParameter(next)) {
                    if (rewriteForNumberVariables == 1) {
                        node.putIntProp(8, 1);
                    }
                } else if (rewriteForNumberVariables == 1) {
                    if (rewriteForNumberVariables2 == 1) {
                        node.putIntProp(8, 0);
                        return 1;
                    }
                    node.putIntProp(8, 1);
                } else if (rewriteForNumberVariables2 == 1) {
                    node.putIntProp(8, 2);
                }
                return 0;
            case 36:
                firstChild = node.getFirstChild();
                next = firstChild.getNext();
                if (rewriteForNumberVariables(firstChild, 1) == 1 && !convertParameter(firstChild)) {
                    node.removeChild(firstChild);
                    node.addChildToFront(new Node(149, firstChild));
                }
                if (rewriteForNumberVariables(next, 1) == 1 && !convertParameter(next)) {
                    node.putIntProp(8, 2);
                }
                return 0;
            case 37:
            case 140:
                firstChild = node.getFirstChild();
                next = firstChild.getNext();
                Node next2 = next.getNext();
                if (rewriteForNumberVariables(firstChild, 1) == 1 && !convertParameter(firstChild)) {
                    node.removeChild(firstChild);
                    node.addChildToFront(new Node(149, firstChild));
                }
                if (rewriteForNumberVariables(next, 1) == 1 && !convertParameter(next)) {
                    node.putIntProp(8, 1);
                }
                if (rewriteForNumberVariables(next2, 1) == 1 && !convertParameter(next2)) {
                    node.removeChild(next2);
                    node.addChildToBack(new Node(149, next2));
                }
                return 0;
            case 38:
                firstChild = node.getFirstChild();
                rewriteAsObjectChildren(firstChild, firstChild.getFirstChild());
                next = firstChild.getNext();
                if (((OptFunctionNode) node.getProp(9)) != null) {
                    for (firstChild = next; firstChild != null; firstChild = firstChild.getNext()) {
                        if (rewriteForNumberVariables(firstChild, 1) == 1) {
                            markDCPNumberContext(firstChild);
                        }
                    }
                } else {
                    rewriteAsObjectChildren(node, next);
                }
                return 0;
            case 40:
                node.putIntProp(8, 0);
                return 1;
            case 55:
                varIndex = this.theFunction.getVarIndex(node);
                if (this.inDirectCallFunction && this.theFunction.isParameter(varIndex) && i == 1) {
                    node.putIntProp(8, 0);
                    return 1;
                } else if (!this.theFunction.isNumberVar(varIndex)) {
                    return 0;
                } else {
                    node.putIntProp(8, 0);
                    return 1;
                }
            case 56:
            case 156:
                next = node.getFirstChild().getNext();
                varIndex = rewriteForNumberVariables(next, 1);
                rewriteForNumberVariables = this.theFunction.getVarIndex(node);
                if (this.inDirectCallFunction && this.theFunction.isParameter(rewriteForNumberVariables)) {
                    if (varIndex != 1) {
                        return varIndex;
                    }
                    if (convertParameter(next)) {
                        markDCPNumberContext(next);
                        return 0;
                    }
                    node.putIntProp(8, 0);
                    return 1;
                } else if (this.theFunction.isNumberVar(rewriteForNumberVariables)) {
                    if (varIndex != 1) {
                        node.removeChild(next);
                        node.addChildToBack(new Node(150, next));
                    }
                    node.putIntProp(8, 0);
                    markDCPNumberContext(next);
                    return 1;
                } else {
                    if (varIndex == 1 && !convertParameter(next)) {
                        node.removeChild(next);
                        node.addChildToBack(new Node(149, next));
                    }
                    return 0;
                }
            case 106:
            case 107:
                next = node.getFirstChild();
                varIndex = rewriteForNumberVariables(next, 1);
                if (next.getType() == 55) {
                    if (varIndex != 1 || convertParameter(next)) {
                        return 0;
                    }
                    node.putIntProp(8, 0);
                    markDCPNumberContext(next);
                    return 1;
                } else if (next.getType() == 36 || next.getType() == 33) {
                    return varIndex;
                } else {
                    return 0;
                }
            case 133:
                if (rewriteForNumberVariables(node.getFirstChild(), 1) == 1) {
                    node.putIntProp(8, 0);
                }
                return 0;
            default:
                rewriteAsObjectChildren(node, node.getFirstChild());
                return 0;
        }
    }

    private void rewriteAsObjectChildren(Node node, Node node2) {
        while (node2 != null) {
            Node next = node2.getNext();
            if (rewriteForNumberVariables(node2, 0) == 1 && !convertParameter(node2)) {
                node.removeChild(node2);
                Node node3 = new Node(149, node2);
                if (next == null) {
                    node.addChildToBack(node3);
                } else {
                    node.addChildBefore(node3, next);
                }
            }
            node2 = next;
        }
    }

    private static void buildStatementList_r(Node node, ObjArray objArray) {
        int type = node.getType();
        if (type == 129 || type == 141 || type == 132 || type == 109) {
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
                buildStatementList_r(firstChild, objArray);
            }
            return;
        }
        objArray.add(node);
    }
}
