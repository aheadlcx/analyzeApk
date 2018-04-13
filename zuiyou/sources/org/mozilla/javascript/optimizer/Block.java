package org.mozilla.javascript.optimizer;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.ObjToIntMap.Iterator;
import org.mozilla.javascript.ast.Jump;

class Block {
    static final boolean DEBUG = false;
    private static int debug_blockCount;
    private int itsBlockID;
    private int itsEndNodeIndex;
    private BitSet itsLiveOnEntrySet;
    private BitSet itsLiveOnExitSet;
    private BitSet itsNotDefSet;
    private Block[] itsPredecessors;
    private int itsStartNodeIndex;
    private Block[] itsSuccessors;
    private BitSet itsUseBeforeDefSet;

    private static class FatBlock {
        private ObjToIntMap predecessors;
        Block realBlock;
        private ObjToIntMap successors;

        private FatBlock() {
            this.successors = new ObjToIntMap();
            this.predecessors = new ObjToIntMap();
        }

        private static Block[] reduceToArray(ObjToIntMap objToIntMap) {
            if (objToIntMap.isEmpty()) {
                return null;
            }
            Block[] blockArr = new Block[objToIntMap.size()];
            Iterator newIterator = objToIntMap.newIterator();
            newIterator.start();
            int i = 0;
            while (!newIterator.done()) {
                int i2 = i + 1;
                blockArr[i] = ((FatBlock) newIterator.getKey()).realBlock;
                newIterator.next();
                i = i2;
            }
            return blockArr;
        }

        void addSuccessor(FatBlock fatBlock) {
            this.successors.put(fatBlock, 0);
        }

        void addPredecessor(FatBlock fatBlock) {
            this.predecessors.put(fatBlock, 0);
        }

        Block[] getSuccessors() {
            return reduceToArray(this.successors);
        }

        Block[] getPredecessors() {
            return reduceToArray(this.predecessors);
        }
    }

    Block(int i, int i2) {
        this.itsStartNodeIndex = i;
        this.itsEndNodeIndex = i2;
    }

    static void runFlowAnalyzes(OptFunctionNode optFunctionNode, Node[] nodeArr) {
        int i;
        int paramCount = optFunctionNode.fnode.getParamCount();
        int paramAndVarCount = optFunctionNode.fnode.getParamAndVarCount();
        int[] iArr = new int[paramAndVarCount];
        for (i = 0; i != paramCount; i++) {
            iArr[i] = 3;
        }
        for (i = paramCount; i != paramAndVarCount; i++) {
            iArr[i] = 0;
        }
        Block[] buildBlocks = buildBlocks(nodeArr);
        reachingDefDataFlow(optFunctionNode, nodeArr, buildBlocks, iArr);
        typeFlow(optFunctionNode, nodeArr, buildBlocks, iArr);
        while (paramCount != paramAndVarCount) {
            if (iArr[paramCount] == 1) {
                optFunctionNode.setIsNumberVar(paramCount);
            }
            paramCount++;
        }
    }

    private static Block[] buildBlocks(Node[] nodeArr) {
        FatBlock newFatBlock;
        int i = 0;
        Map hashMap = new HashMap();
        ObjArray objArray = new ObjArray();
        int i2 = 0;
        for (int i3 = 0; i3 < nodeArr.length; i3++) {
            FatBlock newFatBlock2;
            switch (nodeArr[i3].getType()) {
                case 5:
                case 6:
                case 7:
                    newFatBlock2 = newFatBlock(i2, i3);
                    if (nodeArr[i2].getType() == 131) {
                        hashMap.put(nodeArr[i2], newFatBlock2);
                    }
                    objArray.add(newFatBlock2);
                    i2 = i3 + 1;
                    break;
                case 131:
                    if (i3 == i2) {
                        break;
                    }
                    newFatBlock2 = newFatBlock(i2, i3 - 1);
                    if (nodeArr[i2].getType() == 131) {
                        hashMap.put(nodeArr[i2], newFatBlock2);
                    }
                    objArray.add(newFatBlock2);
                    i2 = i3;
                    break;
                default:
                    break;
            }
        }
        if (i2 != nodeArr.length) {
            newFatBlock = newFatBlock(i2, nodeArr.length - 1);
            if (nodeArr[i2].getType() == 131) {
                hashMap.put(nodeArr[i2], newFatBlock);
            }
            objArray.add(newFatBlock);
        }
        int i4 = 0;
        while (i4 < objArray.size()) {
            newFatBlock = (FatBlock) objArray.get(i4);
            Node node = nodeArr[newFatBlock.realBlock.itsEndNodeIndex];
            int type = node.getType();
            if (type != 5 && i4 < objArray.size() - 1) {
                FatBlock fatBlock = (FatBlock) objArray.get(i4 + 1);
                newFatBlock.addSuccessor(fatBlock);
                fatBlock.addPredecessor(newFatBlock);
            }
            if (type == 7 || type == 6 || type == 5) {
                node = ((Jump) node).target;
                fatBlock = (FatBlock) hashMap.get(node);
                node.putProp(6, fatBlock.realBlock);
                newFatBlock.addSuccessor(fatBlock);
                fatBlock.addPredecessor(newFatBlock);
            }
            i4++;
        }
        Block[] blockArr = new Block[objArray.size()];
        while (i < objArray.size()) {
            newFatBlock = (FatBlock) objArray.get(i);
            Block block = newFatBlock.realBlock;
            block.itsSuccessors = newFatBlock.getSuccessors();
            block.itsPredecessors = newFatBlock.getPredecessors();
            block.itsBlockID = i;
            blockArr[i] = block;
            i++;
        }
        return blockArr;
    }

    private static FatBlock newFatBlock(int i, int i2) {
        FatBlock fatBlock = new FatBlock();
        fatBlock.realBlock = new Block(i, i2);
        return fatBlock;
    }

    private static String toString(Block[] blockArr, Node[] nodeArr) {
        return null;
    }

    private static void reachingDefDataFlow(OptFunctionNode optFunctionNode, Node[] nodeArr, Block[] blockArr, int[] iArr) {
        for (Block initLiveOnEntrySets : blockArr) {
            initLiveOnEntrySets.initLiveOnEntrySets(optFunctionNode, nodeArr);
        }
        boolean[] zArr = new boolean[blockArr.length];
        boolean[] zArr2 = new boolean[blockArr.length];
        int length = blockArr.length - 1;
        zArr[length] = true;
        int i = length;
        boolean z = false;
        while (true) {
            if (zArr[i] || !zArr2[i]) {
                zArr2[i] = true;
                zArr[i] = false;
                if (blockArr[i].doReachedUseDataFlow()) {
                    Block[] blockArr2 = blockArr[i].itsPredecessors;
                    if (blockArr2 != null) {
                        boolean z2 = z;
                        for (Block block : blockArr2) {
                            int i2 = block.itsBlockID;
                            zArr[i2] = true;
                            if (i2 > i) {
                                i2 = 1;
                            } else {
                                i2 = 0;
                            }
                            z2 |= i2;
                        }
                        z = z2;
                    }
                }
            }
            if (i != 0) {
                i--;
            } else if (z) {
                i = blockArr.length - 1;
                z = false;
            } else {
                blockArr[0].markAnyTypeVariables(iArr);
                return;
            }
        }
    }

    private static void typeFlow(OptFunctionNode optFunctionNode, Node[] nodeArr, Block[] blockArr, int[] iArr) {
        boolean[] zArr = new boolean[blockArr.length];
        boolean[] zArr2 = new boolean[blockArr.length];
        zArr[0] = true;
        boolean z = false;
        int i = 0;
        while (true) {
            if (zArr[i] || !zArr2[i]) {
                zArr2[i] = true;
                zArr[i] = false;
                if (blockArr[i].doTypeFlow(optFunctionNode, nodeArr, iArr)) {
                    Block[] blockArr2 = blockArr[i].itsSuccessors;
                    if (blockArr2 != null) {
                        boolean z2 = z;
                        for (Block block : blockArr2) {
                            int i2 = block.itsBlockID;
                            zArr[i2] = true;
                            if (i2 < i) {
                                i2 = 1;
                            } else {
                                i2 = 0;
                            }
                            z2 |= i2;
                        }
                        z = z2;
                    }
                }
            }
            if (i != blockArr.length - 1) {
                i++;
            } else if (z) {
                z = false;
                i = 0;
            } else {
                return;
            }
        }
    }

    private static boolean assignType(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i] | i2;
        iArr[i] = i4;
        return i3 != i4;
    }

    private void markAnyTypeVariables(int[] iArr) {
        for (int i = 0; i != iArr.length; i++) {
            if (this.itsLiveOnEntrySet.get(i)) {
                assignType(iArr, i, 3);
            }
        }
    }

    private void lookForVariableAccess(OptFunctionNode optFunctionNode, Node node) {
        int varIndex;
        Node firstChild;
        switch (node.getType()) {
            case 55:
                varIndex = optFunctionNode.getVarIndex(node);
                if (!this.itsNotDefSet.get(varIndex)) {
                    this.itsUseBeforeDefSet.set(varIndex);
                    return;
                }
                return;
            case 56:
            case 156:
                lookForVariableAccess(optFunctionNode, node.getFirstChild().getNext());
                this.itsNotDefSet.set(optFunctionNode.getVarIndex(node));
                return;
            case 106:
            case 107:
                firstChild = node.getFirstChild();
                if (firstChild.getType() == 55) {
                    varIndex = optFunctionNode.getVarIndex(firstChild);
                    if (!this.itsNotDefSet.get(varIndex)) {
                        this.itsUseBeforeDefSet.set(varIndex);
                    }
                    this.itsNotDefSet.set(varIndex);
                    return;
                }
                lookForVariableAccess(optFunctionNode, firstChild);
                return;
            case 137:
                varIndex = optFunctionNode.fnode.getIndexForNameNode(node);
                if (varIndex > -1 && !this.itsNotDefSet.get(varIndex)) {
                    this.itsUseBeforeDefSet.set(varIndex);
                    return;
                }
                return;
            default:
                for (firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
                    lookForVariableAccess(optFunctionNode, firstChild);
                }
                return;
        }
    }

    private void initLiveOnEntrySets(OptFunctionNode optFunctionNode, Node[] nodeArr) {
        int varCount = optFunctionNode.getVarCount();
        this.itsUseBeforeDefSet = new BitSet(varCount);
        this.itsNotDefSet = new BitSet(varCount);
        this.itsLiveOnEntrySet = new BitSet(varCount);
        this.itsLiveOnExitSet = new BitSet(varCount);
        for (int i = this.itsStartNodeIndex; i <= this.itsEndNodeIndex; i++) {
            lookForVariableAccess(optFunctionNode, nodeArr[i]);
        }
        this.itsNotDefSet.flip(0, varCount);
    }

    private boolean doReachedUseDataFlow() {
        this.itsLiveOnExitSet.clear();
        if (this.itsSuccessors != null) {
            for (Block block : this.itsSuccessors) {
                this.itsLiveOnExitSet.or(block.itsLiveOnEntrySet);
            }
        }
        return updateEntrySet(this.itsLiveOnEntrySet, this.itsLiveOnExitSet, this.itsUseBeforeDefSet, this.itsNotDefSet);
    }

    private boolean updateEntrySet(BitSet bitSet, BitSet bitSet2, BitSet bitSet3, BitSet bitSet4) {
        int cardinality = bitSet.cardinality();
        bitSet.or(bitSet2);
        bitSet.and(bitSet4);
        bitSet.or(bitSet3);
        return bitSet.cardinality() != cardinality;
    }

    private static int findExpressionType(OptFunctionNode optFunctionNode, Node node, int[] iArr) {
        Node firstChild;
        switch (node.getType()) {
            case 8:
            case 35:
            case 37:
            case 56:
            case 89:
            case 156:
                return findExpressionType(optFunctionNode, node.getLastChild(), iArr);
            case 9:
            case 10:
            case 11:
            case 18:
            case 19:
            case 20:
            case 22:
            case 23:
            case 24:
            case 25:
            case 27:
            case 28:
            case 29:
            case 40:
            case 106:
            case 107:
                return 1;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 26:
            case 31:
            case 44:
            case 45:
            case 46:
            case 47:
            case 52:
            case 53:
            case 69:
                return 3;
            case 21:
                firstChild = node.getFirstChild();
                return findExpressionType(optFunctionNode, firstChild.getNext(), iArr) | findExpressionType(optFunctionNode, firstChild, iArr);
            case 30:
            case 38:
            case 70:
                return 3;
            case 32:
            case 41:
            case 137:
                return 3;
            case 33:
            case 36:
            case 39:
            case 43:
                return 3;
            case 42:
            case 48:
            case 65:
            case 66:
            case 157:
                return 3;
            case 55:
                return iArr[optFunctionNode.getVarIndex(node)];
            case 102:
                firstChild = node.getFirstChild().getNext();
                return findExpressionType(optFunctionNode, firstChild, iArr) | findExpressionType(optFunctionNode, firstChild.getNext(), iArr);
            case 104:
            case 105:
                firstChild = node.getFirstChild();
                return findExpressionType(optFunctionNode, firstChild.getNext(), iArr) | findExpressionType(optFunctionNode, firstChild, iArr);
            case 126:
                return 3;
            default:
                return 3;
        }
    }

    private static boolean findDefPoints(OptFunctionNode optFunctionNode, Node node, int[] iArr) {
        boolean z = false;
        Node firstChild = node.getFirstChild();
        Node node2 = firstChild;
        while (node2 != null) {
            boolean findDefPoints = findDefPoints(optFunctionNode, node2, iArr) | z;
            node2 = node2.getNext();
            z = findDefPoints;
        }
        int findExpressionType;
        switch (node.getType()) {
            case 56:
            case 156:
                findExpressionType = findExpressionType(optFunctionNode, firstChild.getNext(), iArr);
                int varIndex = optFunctionNode.getVarIndex(node);
                if (node.getType() == 56 && optFunctionNode.fnode.getParamAndVarConst()[varIndex]) {
                    return z;
                }
                return z | assignType(iArr, varIndex, findExpressionType);
            case 106:
            case 107:
                if (firstChild.getType() != 55) {
                    return z;
                }
                findExpressionType = optFunctionNode.getVarIndex(firstChild);
                if (optFunctionNode.fnode.getParamAndVarConst()[findExpressionType]) {
                    return z;
                }
                return z | assignType(iArr, findExpressionType, 1);
            default:
                return z;
        }
    }

    private boolean doTypeFlow(OptFunctionNode optFunctionNode, Node[] nodeArr, int[] iArr) {
        boolean z = false;
        for (int i = this.itsStartNodeIndex; i <= this.itsEndNodeIndex; i++) {
            Node node = nodeArr[i];
            if (node != null) {
                z |= findDefPoints(optFunctionNode, node, iArr);
            }
        }
        return z;
    }

    private void printLiveOnEntrySet(OptFunctionNode optFunctionNode) {
    }
}
