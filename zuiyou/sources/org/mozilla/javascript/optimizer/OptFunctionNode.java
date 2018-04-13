package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.ScriptNode;

public final class OptFunctionNode {
    private int directTargetIndex = -1;
    public final FunctionNode fnode;
    boolean itsContainsCalls0;
    boolean itsContainsCalls1;
    private boolean itsParameterNumberContext;
    private boolean[] numberVarFlags;

    OptFunctionNode(FunctionNode functionNode) {
        this.fnode = functionNode;
        functionNode.setCompilerData(this);
    }

    public static OptFunctionNode get(ScriptNode scriptNode, int i) {
        return (OptFunctionNode) scriptNode.getFunctionNode(i).getCompilerData();
    }

    public static OptFunctionNode get(ScriptNode scriptNode) {
        return (OptFunctionNode) scriptNode.getCompilerData();
    }

    public boolean isTargetOfDirectCall() {
        return this.directTargetIndex >= 0;
    }

    public int getDirectTargetIndex() {
        return this.directTargetIndex;
    }

    void setDirectTargetIndex(int i) {
        if (i < 0 || this.directTargetIndex >= 0) {
            Kit.codeBug();
        }
        this.directTargetIndex = i;
    }

    void setParameterNumberContext(boolean z) {
        this.itsParameterNumberContext = z;
    }

    public boolean getParameterNumberContext() {
        return this.itsParameterNumberContext;
    }

    public int getVarCount() {
        return this.fnode.getParamAndVarCount();
    }

    public boolean isParameter(int i) {
        return i < this.fnode.getParamCount();
    }

    public boolean isNumberVar(int i) {
        int paramCount = i - this.fnode.getParamCount();
        if (paramCount < 0 || this.numberVarFlags == null) {
            return false;
        }
        return this.numberVarFlags[paramCount];
    }

    void setIsNumberVar(int i) {
        int paramCount = i - this.fnode.getParamCount();
        if (paramCount < 0) {
            Kit.codeBug();
        }
        if (this.numberVarFlags == null) {
            this.numberVarFlags = new boolean[(this.fnode.getParamAndVarCount() - this.fnode.getParamCount())];
        }
        this.numberVarFlags[paramCount] = true;
    }

    public int getVarIndex(Node node) {
        int intProp = node.getIntProp(7, -1);
        if (intProp == -1) {
            Node node2;
            intProp = node.getType();
            if (intProp == 55) {
                node2 = node;
            } else if (intProp == 56 || intProp == 156) {
                node2 = node.getFirstChild();
            } else {
                throw Kit.codeBug();
            }
            intProp = this.fnode.getIndexForNameNode(node2);
            if (intProp < 0) {
                throw Kit.codeBug();
            }
            node.putIntProp(7, intProp);
        }
        return intProp;
    }
}
