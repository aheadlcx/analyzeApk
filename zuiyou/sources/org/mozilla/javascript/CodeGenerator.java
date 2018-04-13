package org.mozilla.javascript;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import org.mozilla.javascript.ObjToIntMap.Iterator;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.ScriptNode;

class CodeGenerator extends Icode {
    private static final int ECF_TAIL = 1;
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
    private static final int MIN_LABEL_TABLE_SIZE = 32;
    private CompilerEnvirons compilerEnv;
    private int doubleTableTop;
    private int exceptionTableTop;
    private long[] fixupTable;
    private int fixupTableTop;
    private int iCodeTop;
    private InterpreterData itsData;
    private boolean itsInFunctionFlag;
    private boolean itsInTryFlag;
    private int[] labelTable;
    private int labelTableTop;
    private int lineNumber;
    private ObjArray literalIds = new ObjArray();
    private int localTop;
    private ScriptNode scriptOrFn;
    private int stackDepth;
    private ObjToIntMap strings = new ObjToIntMap(20);

    CodeGenerator() {
    }

    public InterpreterData compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        this.compilerEnv = compilerEnvirons;
        new NodeTransformer().transform(scriptNode);
        if (z) {
            this.scriptOrFn = scriptNode.getFunctionNode(0);
        } else {
            this.scriptOrFn = scriptNode;
        }
        this.itsData = new InterpreterData(compilerEnvirons.getLanguageVersion(), this.scriptOrFn.getSourceName(), str, ((AstRoot) scriptNode).isInStrictMode());
        this.itsData.topLevel = true;
        if (z) {
            generateFunctionICode();
        } else {
            generateICodeFromTree(this.scriptOrFn);
        }
        return this.itsData;
    }

    private void generateFunctionICode() {
        this.itsInFunctionFlag = true;
        FunctionNode functionNode = (FunctionNode) this.scriptOrFn;
        this.itsData.itsFunctionType = functionNode.getFunctionType();
        this.itsData.itsNeedsActivation = functionNode.requiresActivation();
        if (functionNode.getFunctionName() != null) {
            this.itsData.itsName = functionNode.getName();
        }
        if (functionNode.isGenerator()) {
            addIcode(-62);
            addUint16(functionNode.getBaseLineno() & 65535);
        }
        generateICodeFromTree(functionNode.getLastChild());
    }

    private void generateICodeFromTree(Node node) {
        generateNestedFunctions();
        generateRegExpLiterals();
        visitStatement(node, 0);
        fixLabelGotos();
        if (this.itsData.itsFunctionType == 0) {
            addToken(64);
        }
        if (this.itsData.itsICode.length != this.iCodeTop) {
            Object obj = new byte[this.iCodeTop];
            System.arraycopy(this.itsData.itsICode, 0, obj, 0, this.iCodeTop);
            this.itsData.itsICode = obj;
        }
        if (this.strings.size() == 0) {
            this.itsData.itsStringTable = null;
        } else {
            this.itsData.itsStringTable = new String[this.strings.size()];
            Iterator newIterator = this.strings.newIterator();
            newIterator.start();
            while (!newIterator.done()) {
                String str = (String) newIterator.getKey();
                int value = newIterator.getValue();
                if (this.itsData.itsStringTable[value] != null) {
                    Kit.codeBug();
                }
                this.itsData.itsStringTable[value] = str;
                newIterator.next();
            }
        }
        if (this.doubleTableTop == 0) {
            this.itsData.itsDoubleTable = null;
        } else if (this.itsData.itsDoubleTable.length != this.doubleTableTop) {
            obj = new double[this.doubleTableTop];
            System.arraycopy(this.itsData.itsDoubleTable, 0, obj, 0, this.doubleTableTop);
            this.itsData.itsDoubleTable = obj;
        }
        if (!(this.exceptionTableTop == 0 || this.itsData.itsExceptionTable.length == this.exceptionTableTop)) {
            obj = new int[this.exceptionTableTop];
            System.arraycopy(this.itsData.itsExceptionTable, 0, obj, 0, this.exceptionTableTop);
            this.itsData.itsExceptionTable = obj;
        }
        this.itsData.itsMaxVars = this.scriptOrFn.getParamAndVarCount();
        this.itsData.itsMaxFrameArray = (this.itsData.itsMaxVars + this.itsData.itsMaxLocals) + this.itsData.itsMaxStack;
        this.itsData.argNames = this.scriptOrFn.getParamAndVarNames();
        this.itsData.argIsConst = this.scriptOrFn.getParamAndVarConst();
        this.itsData.argCount = this.scriptOrFn.getParamCount();
        this.itsData.encodedSourceStart = this.scriptOrFn.getEncodedSourceStart();
        this.itsData.encodedSourceEnd = this.scriptOrFn.getEncodedSourceEnd();
        if (this.literalIds.size() != 0) {
            this.itsData.literalIds = this.literalIds.toArray();
        }
    }

    private void generateNestedFunctions() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        if (functionCount != 0) {
            InterpreterData[] interpreterDataArr = new InterpreterData[functionCount];
            for (int i = 0; i != functionCount; i++) {
                ScriptNode functionNode = this.scriptOrFn.getFunctionNode(i);
                CodeGenerator codeGenerator = new CodeGenerator();
                codeGenerator.compilerEnv = this.compilerEnv;
                codeGenerator.scriptOrFn = functionNode;
                codeGenerator.itsData = new InterpreterData(this.itsData);
                codeGenerator.generateFunctionICode();
                interpreterDataArr[i] = codeGenerator.itsData;
            }
            this.itsData.itsNestedFunctions = interpreterDataArr;
        }
    }

    private void generateRegExpLiterals() {
        int regexpCount = this.scriptOrFn.getRegexpCount();
        if (regexpCount != 0) {
            Context context = Context.getContext();
            RegExpProxy checkRegExpProxy = ScriptRuntime.checkRegExpProxy(context);
            Object[] objArr = new Object[regexpCount];
            for (int i = 0; i != regexpCount; i++) {
                objArr[i] = checkRegExpProxy.compileRegExp(context, this.scriptOrFn.getRegexpString(i), this.scriptOrFn.getRegexpFlags(i));
            }
            this.itsData.itsRegExpLiterals = objArr;
        }
    }

    private void updateLineNumber(Node node) {
        int lineno = node.getLineno();
        if (lineno != this.lineNumber && lineno >= 0) {
            if (this.itsData.firstLinePC < 0) {
                this.itsData.firstLinePC = lineno;
            }
            this.lineNumber = lineno;
            addIcode(-26);
            addUint16(lineno & 65535);
        }
    }

    private RuntimeException badTree(Node node) {
        throw new RuntimeException(node.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void visitStatement(org.mozilla.javascript.Node r10, int r11) {
        /*
        r9 = this;
        r0 = -5;
        r8 = -56;
        r7 = 1;
        r4 = 0;
        r5 = -1;
        r1 = r10.getType();
        r2 = r10.getFirstChild();
        switch(r1) {
            case -62: goto L_0x003e;
            case 2: goto L_0x005b;
            case 3: goto L_0x0066;
            case 4: goto L_0x01d2;
            case 5: goto L_0x00ea;
            case 6: goto L_0x00db;
            case 7: goto L_0x00db;
            case 50: goto L_0x01ab;
            case 51: goto L_0x01c4;
            case 57: goto L_0x0180;
            case 58: goto L_0x020c;
            case 59: goto L_0x020c;
            case 60: goto L_0x020c;
            case 64: goto L_0x0202;
            case 81: goto L_0x0131;
            case 109: goto L_0x0016;
            case 114: goto L_0x008d;
            case 123: goto L_0x004e;
            case 125: goto L_0x00fe;
            case 128: goto L_0x004e;
            case 129: goto L_0x004e;
            case 130: goto L_0x004e;
            case 131: goto L_0x00d6;
            case 132: goto L_0x004e;
            case 133: goto L_0x011e;
            case 134: goto L_0x011e;
            case 135: goto L_0x00f3;
            case 136: goto L_0x0051;
            case 141: goto L_0x006b;
            case 160: goto L_0x0087;
            default: goto L_0x0011;
        };
    L_0x0011:
        r0 = r9.badTree(r10);
        throw r0;
    L_0x0016:
        r1 = r10.getExistingIntProp(r7);
        r2 = r9.scriptOrFn;
        r2 = r2.getFunctionNode(r1);
        r2 = r2.getFunctionType();
        r3 = 3;
        if (r2 != r3) goto L_0x0047;
    L_0x0027:
        r2 = -20;
        r9.addIndexOp(r2, r1);
    L_0x002c:
        r2 = r9.itsInFunctionFlag;
        if (r2 != 0) goto L_0x003e;
    L_0x0030:
        r2 = -19;
        r9.addIndexOp(r2, r1);
        r9.stackChange(r7);
        r9.addIcode(r0);
        r9.stackChange(r5);
    L_0x003e:
        r0 = r9.stackDepth;
        if (r0 == r11) goto L_0x021b;
    L_0x0042:
        r0 = org.mozilla.javascript.Kit.codeBug();
        throw r0;
    L_0x0047:
        if (r2 == r7) goto L_0x002c;
    L_0x0049:
        r0 = org.mozilla.javascript.Kit.codeBug();
        throw r0;
    L_0x004e:
        r9.updateLineNumber(r10);
    L_0x0051:
        if (r2 == 0) goto L_0x003e;
    L_0x0053:
        r9.visitStatement(r2, r11);
        r2 = r2.getNext();
        goto L_0x0051;
    L_0x005b:
        r9.visitExpression(r2, r4);
        r0 = 2;
        r9.addToken(r0);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x0066:
        r0 = 3;
        r9.addToken(r0);
        goto L_0x003e;
    L_0x006b:
        r0 = r9.allocLocal();
        r1 = 2;
        r10.putIntProp(r1, r0);
        r9.updateLineNumber(r10);
    L_0x0076:
        if (r2 == 0) goto L_0x0080;
    L_0x0078:
        r9.visitStatement(r2, r11);
        r2 = r2.getNext();
        goto L_0x0076;
    L_0x0080:
        r9.addIndexOp(r8, r0);
        r9.releaseLocal(r0);
        goto L_0x003e;
    L_0x0087:
        r0 = -64;
        r9.addIcode(r0);
        goto L_0x003e;
    L_0x008d:
        r9.updateLineNumber(r10);
        r9.visitExpression(r2, r4);
        r0 = r2.getNext();
        r0 = (org.mozilla.javascript.ast.Jump) r0;
    L_0x0099:
        if (r0 == 0) goto L_0x00cd;
    L_0x009b:
        r1 = r0.getType();
        r2 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r1 == r2) goto L_0x00a8;
    L_0x00a3:
        r0 = r9.badTree(r0);
        throw r0;
    L_0x00a8:
        r1 = r0.getFirstChild();
        r9.addIcode(r5);
        r9.stackChange(r7);
        r9.visitExpression(r1, r4);
        r1 = 46;
        r9.addToken(r1);
        r9.stackChange(r5);
        r1 = r0.target;
        r2 = -6;
        r9.addGoto(r1, r2);
        r9.stackChange(r5);
        r0 = r0.getNext();
        r0 = (org.mozilla.javascript.ast.Jump) r0;
        goto L_0x0099;
    L_0x00cd:
        r0 = -4;
        r9.addIcode(r0);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x00d6:
        r9.markTargetLabel(r10);
        goto L_0x003e;
    L_0x00db:
        r10 = (org.mozilla.javascript.ast.Jump) r10;
        r0 = r10.target;
        r9.visitExpression(r2, r4);
        r9.addGoto(r0, r1);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x00ea:
        r10 = (org.mozilla.javascript.ast.Jump) r10;
        r0 = r10.target;
        r9.addGoto(r0, r1);
        goto L_0x003e;
    L_0x00f3:
        r10 = (org.mozilla.javascript.ast.Jump) r10;
        r0 = r10.target;
        r1 = -23;
        r9.addGoto(r0, r1);
        goto L_0x003e;
    L_0x00fe:
        r9.stackChange(r7);
        r0 = r9.getLocalBlockRef(r10);
        r1 = -24;
        r9.addIndexOp(r1, r0);
        r9.stackChange(r5);
    L_0x010d:
        if (r2 == 0) goto L_0x0117;
    L_0x010f:
        r9.visitStatement(r2, r11);
        r2 = r2.getNext();
        goto L_0x010d;
    L_0x0117:
        r1 = -25;
        r9.addIndexOp(r1, r0);
        goto L_0x003e;
    L_0x011e:
        r9.updateLineNumber(r10);
        r9.visitExpression(r2, r4);
        r2 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        if (r1 != r2) goto L_0x0129;
    L_0x0128:
        r0 = -4;
    L_0x0129:
        r9.addIcode(r0);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x0131:
        r10 = (org.mozilla.javascript.ast.Jump) r10;
        r5 = r9.getLocalBlockRef(r10);
        r6 = r9.allocLocal();
        r0 = -13;
        r9.addIndexOp(r0, r6);
        r1 = r9.iCodeTop;
        r3 = r9.itsInTryFlag;
        r9.itsInTryFlag = r7;
        r0 = r2;
    L_0x0147:
        if (r0 == 0) goto L_0x0151;
    L_0x0149:
        r9.visitStatement(r0, r11);
        r0 = r0.getNext();
        goto L_0x0147;
    L_0x0151:
        r9.itsInTryFlag = r3;
        r0 = r10.target;
        if (r0 == 0) goto L_0x0164;
    L_0x0157:
        r2 = r9.labelTable;
        r0 = r9.getTargetLabel(r0);
        r2 = r2[r0];
        r0 = r9;
        r3 = r2;
        r0.addExceptionHandler(r1, r2, r3, r4, r5, r6);
    L_0x0164:
        r0 = r10.getFinally();
        if (r0 == 0) goto L_0x0178;
    L_0x016a:
        r2 = r9.labelTable;
        r0 = r9.getTargetLabel(r0);
        r2 = r2[r0];
        r0 = r9;
        r3 = r2;
        r4 = r7;
        r0.addExceptionHandler(r1, r2, r3, r4, r5, r6);
    L_0x0178:
        r9.addIndexOp(r8, r6);
        r9.releaseLocal(r6);
        goto L_0x003e;
    L_0x0180:
        r0 = r9.getLocalBlockRef(r10);
        r1 = 14;
        r1 = r10.getExistingIntProp(r1);
        r3 = r2.getString();
        r2 = r2.getNext();
        r9.visitExpression(r2, r4);
        r9.addStringPrefix(r3);
        r9.addIndexPrefix(r0);
        r0 = 57;
        r9.addToken(r0);
        if (r1 == 0) goto L_0x01a3;
    L_0x01a2:
        r4 = r7;
    L_0x01a3:
        r9.addUint8(r4);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x01ab:
        r9.updateLineNumber(r10);
        r9.visitExpression(r2, r4);
        r0 = 50;
        r9.addToken(r0);
        r0 = r9.lineNumber;
        r1 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r0 = r0 & r1;
        r9.addUint16(r0);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x01c4:
        r9.updateLineNumber(r10);
        r0 = 51;
        r1 = r9.getLocalBlockRef(r10);
        r9.addIndexOp(r0, r1);
        goto L_0x003e;
    L_0x01d2:
        r9.updateLineNumber(r10);
        r0 = 20;
        r0 = r10.getIntProp(r0, r4);
        if (r0 == 0) goto L_0x01ed;
    L_0x01dd:
        r0 = -63;
        r9.addIcode(r0);
        r0 = r9.lineNumber;
        r1 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r0 = r0 & r1;
        r9.addUint16(r0);
        goto L_0x003e;
    L_0x01ed:
        if (r2 == 0) goto L_0x01fb;
    L_0x01ef:
        r9.visitExpression(r2, r7);
        r0 = 4;
        r9.addToken(r0);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x01fb:
        r0 = -22;
        r9.addIcode(r0);
        goto L_0x003e;
    L_0x0202:
        r9.updateLineNumber(r10);
        r0 = 64;
        r9.addToken(r0);
        goto L_0x003e;
    L_0x020c:
        r9.visitExpression(r2, r4);
        r0 = r9.getLocalBlockRef(r10);
        r9.addIndexOp(r1, r0);
        r9.stackChange(r5);
        goto L_0x003e;
    L_0x021b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.CodeGenerator.visitStatement(org.mozilla.javascript.Node, int):void");
    }

    private void visitExpression(Node node, int i) {
        int i2 = 1;
        int i3 = 0;
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        int i4 = this.stackDepth;
        String string;
        Node node2;
        int i5;
        int intProp;
        Node lastChild;
        switch (type) {
            case 8:
            case 73:
                string = firstChild.getString();
                visitExpression(firstChild, 0);
                visitExpression(firstChild.getNext(), 0);
                addStringOp(type, string);
                stackChange(-1);
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 36:
            case 46:
            case 47:
            case 52:
            case 53:
                visitExpression(firstChild, 0);
                visitExpression(firstChild.getNext(), 0);
                addToken(type);
                stackChange(-1);
                break;
            case 26:
            case 27:
            case 28:
            case 29:
            case 32:
            case 126:
                visitExpression(firstChild, 0);
                if (type != 126) {
                    addToken(type);
                    break;
                }
                addIcode(-4);
                addIcode(-50);
                break;
            case 30:
            case 38:
            case 70:
                if (type == 30) {
                    visitExpression(firstChild, 0);
                } else {
                    generateCallFunAndThis(firstChild);
                }
                node2 = firstChild;
                i5 = 0;
                while (true) {
                    node2 = node2.getNext();
                    if (node2 == null) {
                        intProp = node.getIntProp(10, 0);
                        if (type == 70 || intProp == 0) {
                            if (type != 38 || (i & 1) == 0 || this.compilerEnv.isGenerateDebugInfo() || this.itsInTryFlag) {
                                i3 = type;
                            } else {
                                i3 = -55;
                            }
                            addIndexOp(i3, i5);
                            type = i3;
                        } else {
                            addIndexOp(-21, i5);
                            addUint8(intProp);
                            if (type == 30) {
                                i3 = 1;
                            }
                            addUint8(i3);
                            addUint16(this.lineNumber & 65535);
                        }
                        if (type == 30) {
                            stackChange(-i5);
                        } else {
                            stackChange(-1 - i5);
                        }
                        if (i5 > this.itsData.itsMaxCalleeArgs) {
                            this.itsData.itsMaxCalleeArgs = i5;
                            break;
                        }
                    }
                    visitExpression(node2, 0);
                    i5++;
                }
                break;
            case 31:
                if (firstChild.getType() != 49) {
                    i2 = 0;
                }
                visitExpression(firstChild, 0);
                visitExpression(firstChild.getNext(), 0);
                if (i2 != 0) {
                    addIcode(0);
                } else {
                    addToken(31);
                }
                stackChange(-1);
                break;
            case 33:
            case 34:
                visitExpression(firstChild, 0);
                addStringOp(type, firstChild.getNext().getString());
                break;
            case 35:
            case 139:
                visitExpression(firstChild, 0);
                firstChild = firstChild.getNext();
                String string2 = firstChild.getString();
                firstChild = firstChild.getNext();
                if (type == 139) {
                    addIcode(-1);
                    stackChange(1);
                    addStringOp(33, string2);
                    stackChange(-1);
                }
                visitExpression(firstChild, 0);
                addStringOp(35, string2);
                stackChange(-1);
                break;
            case 37:
            case 140:
                visitExpression(firstChild, 0);
                firstChild = firstChild.getNext();
                visitExpression(firstChild, 0);
                firstChild = firstChild.getNext();
                if (type == 140) {
                    addIcode(-2);
                    stackChange(2);
                    addToken(36);
                    stackChange(-1);
                    stackChange(-1);
                }
                visitExpression(firstChild, 0);
                addToken(37);
                stackChange(-2);
                break;
            case 39:
            case 41:
            case 49:
                addStringOp(type, node.getString());
                stackChange(1);
                break;
            case 40:
                double d = node.getDouble();
                short s = (int) d;
                if (((double) s) != d) {
                    addIndexOp(40, getDoubleIndex(d));
                } else if (s == (short) 0) {
                    addIcode(-51);
                    if (1.0d / d < 0.0d) {
                        addToken(29);
                    }
                } else if (s == (short) 1) {
                    addIcode(-52);
                } else if (((short) s) == s) {
                    addIcode(-27);
                    addUint16(65535 & s);
                } else {
                    addIcode(-28);
                    addInt(s);
                }
                stackChange(1);
                break;
            case 42:
            case 43:
            case 44:
            case 45:
            case 63:
                addToken(type);
                stackChange(1);
                break;
            case 48:
                addIndexOp(48, node.getExistingIntProp(4));
                stackChange(1);
                break;
            case 54:
                addIndexOp(54, getLocalBlockRef(node));
                stackChange(1);
                break;
            case 55:
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                addVarOp(55, this.scriptOrFn.getIndexForNameNode(node));
                stackChange(1);
                break;
            case 56:
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                intProp = this.scriptOrFn.getIndexForNameNode(firstChild);
                visitExpression(firstChild.getNext(), 0);
                addVarOp(56, intProp);
                break;
            case 61:
            case 62:
                addIndexOp(type, getLocalBlockRef(node));
                stackChange(1);
                break;
            case 65:
            case 66:
                visitLiteral(node, firstChild);
                break;
            case 67:
            case 69:
                visitExpression(firstChild, 0);
                addToken(type);
                break;
            case 68:
            case 142:
                visitExpression(firstChild, 0);
                firstChild = firstChild.getNext();
                if (type == 142) {
                    addIcode(-1);
                    stackChange(1);
                    addToken(67);
                    stackChange(-1);
                }
                visitExpression(firstChild, 0);
                addToken(68);
                stackChange(-1);
                break;
            case 71:
                visitExpression(firstChild, 0);
                addStringOp(type, (String) node.getProp(17));
                break;
            case 72:
                if (firstChild != null) {
                    visitExpression(firstChild, 0);
                } else {
                    addIcode(-50);
                    stackChange(1);
                }
                addToken(72);
                addUint16(node.getLineno() & 65535);
                break;
            case 74:
            case 75:
            case 76:
                visitExpression(firstChild, 0);
                addToken(type);
                break;
            case 77:
            case 78:
            case 79:
            case 80:
                i2 = node.getIntProp(16, 0);
                node2 = firstChild;
                i5 = 0;
                do {
                    visitExpression(node2, 0);
                    i5++;
                    node2 = node2.getNext();
                } while (node2 != null);
                addIndexOp(type, i2);
                stackChange(1 - i5);
                break;
            case 89:
                lastChild = node.getLastChild();
                while (firstChild != lastChild) {
                    visitExpression(firstChild, 0);
                    addIcode(-4);
                    stackChange(-1);
                    firstChild = firstChild.getNext();
                }
                visitExpression(firstChild, i & 1);
                break;
            case 102:
                lastChild = firstChild.getNext();
                Node next = lastChild.getNext();
                visitExpression(firstChild, 0);
                i5 = this.iCodeTop;
                addGotoOp(7);
                stackChange(-1);
                visitExpression(lastChild, i & 1);
                i3 = this.iCodeTop;
                addGotoOp(5);
                resolveForwardGoto(i5);
                this.stackDepth = i4;
                visitExpression(next, i & 1);
                resolveForwardGoto(i3);
                break;
            case 104:
            case 105:
                visitExpression(firstChild, 0);
                addIcode(-1);
                stackChange(1);
                i2 = this.iCodeTop;
                addGotoOp(type == 105 ? 7 : 6);
                stackChange(-1);
                addIcode(-4);
                stackChange(-1);
                visitExpression(firstChild.getNext(), i & 1);
                resolveForwardGoto(i2);
                break;
            case 106:
            case 107:
                visitIncDec(node, firstChild);
                break;
            case 109:
                i5 = node.getExistingIntProp(1);
                if (this.scriptOrFn.getFunctionNode(i5).getFunctionType() == 2) {
                    addIndexOp(-19, i5);
                    stackChange(1);
                    break;
                }
                throw Kit.codeBug();
            case 137:
                if (!this.itsInFunctionFlag || this.itsData.itsNeedsActivation) {
                    i5 = -1;
                } else {
                    i5 = this.scriptOrFn.getIndexForNameNode(node);
                }
                if (i5 != -1) {
                    addVarOp(55, i5);
                    stackChange(1);
                    addToken(32);
                    break;
                }
                addStringOp(-14, node.getString());
                stackChange(1);
                break;
                break;
            case 138:
                stackChange(1);
                break;
            case 146:
                updateLineNumber(node);
                visitExpression(firstChild, 0);
                addIcode(-53);
                stackChange(-1);
                intProp = this.iCodeTop;
                visitExpression(firstChild.getNext(), 0);
                addBackwardGoto(-54, intProp);
                break;
            case 155:
                string = firstChild.getString();
                visitExpression(firstChild, 0);
                visitExpression(firstChild.getNext(), 0);
                addStringOp(-59, string);
                stackChange(-1);
                break;
            case 156:
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                intProp = this.scriptOrFn.getIndexForNameNode(firstChild);
                visitExpression(firstChild.getNext(), 0);
                addVarOp(156, intProp);
                break;
            case 157:
                visitArrayComprehension(node, firstChild, firstChild.getNext());
                break;
            case 159:
                firstChild = node.getFirstChild();
                lastChild = firstChild.getNext();
                visitExpression(firstChild.getFirstChild(), 0);
                addToken(2);
                stackChange(-1);
                visitExpression(lastChild.getFirstChild(), 0);
                addToken(3);
                break;
            default:
                throw badTree(node);
        }
        if (i4 + 1 != this.stackDepth) {
            Kit.codeBug();
        }
    }

    private void generateCallFunAndThis(Node node) {
        int type = node.getType();
        switch (type) {
            case 33:
            case 36:
                Node firstChild = node.getFirstChild();
                visitExpression(firstChild, 0);
                firstChild = firstChild.getNext();
                if (type == 33) {
                    addStringOp(-16, firstChild.getString());
                    stackChange(1);
                    return;
                }
                visitExpression(firstChild, 0);
                addIcode(-17);
                return;
            case 39:
                addStringOp(-15, node.getString());
                stackChange(2);
                return;
            default:
                visitExpression(node, 0);
                addIcode(-18);
                stackChange(1);
                return;
        }
    }

    private void visitIncDec(Node node, Node node2) {
        int existingIntProp = node.getExistingIntProp(13);
        Node firstChild;
        switch (node2.getType()) {
            case 33:
                firstChild = node2.getFirstChild();
                visitExpression(firstChild, 0);
                addStringOp(-9, firstChild.getNext().getString());
                addUint8(existingIntProp);
                return;
            case 36:
                firstChild = node2.getFirstChild();
                visitExpression(firstChild, 0);
                visitExpression(firstChild.getNext(), 0);
                addIcode(-10);
                addUint8(existingIntProp);
                stackChange(-1);
                return;
            case 39:
                addStringOp(-8, node2.getString());
                addUint8(existingIntProp);
                stackChange(1);
                return;
            case 55:
                if (this.itsData.itsNeedsActivation) {
                    Kit.codeBug();
                }
                addVarOp(-7, this.scriptOrFn.getIndexForNameNode(node2));
                addUint8(existingIntProp);
                stackChange(1);
                return;
            case 67:
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-11);
                addUint8(existingIntProp);
                return;
            default:
                throw badTree(node);
        }
    }

    private void visitLiteral(Node node, Node node2) {
        int i;
        int type = node.getType();
        Object obj = null;
        if (type == 65) {
            Node node3 = node2;
            i = 0;
            while (node3 != null) {
                int i2 = i + 1;
                node3 = node3.getNext();
                i = i2;
            }
        } else if (type == 66) {
            obj = (Object[]) node.getProp(12);
            i = obj.length;
        } else {
            throw badTree(node);
        }
        addIndexOp(-29, i);
        stackChange(2);
        while (node2 != null) {
            i = node2.getType();
            if (i == 151) {
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-57);
            } else if (i == 152) {
                visitExpression(node2.getFirstChild(), 0);
                addIcode(-58);
            } else {
                visitExpression(node2, 0);
                addIcode(-30);
            }
            stackChange(-1);
            node2 = node2.getNext();
        }
        if (type == 65) {
            int[] iArr = (int[]) node.getProp(11);
            if (iArr == null) {
                addToken(65);
            } else {
                i = this.literalIds.size();
                this.literalIds.add(iArr);
                addIndexOp(-31, i);
            }
        } else {
            i = this.literalIds.size();
            this.literalIds.add(obj);
            addIndexOp(66, i);
        }
        stackChange(-1);
    }

    private void visitArrayComprehension(Node node, Node node2, Node node3) {
        visitStatement(node2, this.stackDepth);
        visitExpression(node3, 0);
    }

    private int getLocalBlockRef(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private int getTargetLabel(Node node) {
        int labelId = node.labelId();
        if (labelId == -1) {
            labelId = this.labelTableTop;
            if (this.labelTable == null || labelId == this.labelTable.length) {
                if (this.labelTable == null) {
                    this.labelTable = new int[32];
                } else {
                    Object obj = new int[(this.labelTable.length * 2)];
                    System.arraycopy(this.labelTable, 0, obj, 0, labelId);
                    this.labelTable = obj;
                }
            }
            this.labelTableTop = labelId + 1;
            this.labelTable[labelId] = -1;
            node.labelId(labelId);
        }
        return labelId;
    }

    private void markTargetLabel(Node node) {
        int targetLabel = getTargetLabel(node);
        if (this.labelTable[targetLabel] != -1) {
            Kit.codeBug();
        }
        this.labelTable[targetLabel] = this.iCodeTop;
    }

    private void addGoto(Node node, int i) {
        int targetLabel = getTargetLabel(node);
        if (targetLabel >= this.labelTableTop) {
            Kit.codeBug();
        }
        int i2 = this.labelTable[targetLabel];
        if (i2 != -1) {
            addBackwardGoto(i, i2);
            return;
        }
        i2 = this.iCodeTop;
        addGotoOp(i);
        int i3 = this.fixupTableTop;
        if (this.fixupTable == null || i3 == this.fixupTable.length) {
            if (this.fixupTable == null) {
                this.fixupTable = new long[40];
            } else {
                Object obj = new long[(this.fixupTable.length * 2)];
                System.arraycopy(this.fixupTable, 0, obj, 0, i3);
                this.fixupTable = obj;
            }
        }
        this.fixupTableTop = i3 + 1;
        this.fixupTable[i3] = ((long) i2) | (((long) targetLabel) << 32);
    }

    private void fixLabelGotos() {
        for (int i = 0; i < this.fixupTableTop; i++) {
            long j = this.fixupTable[i];
            int i2 = (int) (j >> 32);
            int i3 = (int) j;
            int i4 = this.labelTable[i2];
            if (i4 == -1) {
                throw Kit.codeBug();
            }
            resolveGoto(i3, i4);
        }
        this.fixupTableTop = 0;
    }

    private void addBackwardGoto(int i, int i2) {
        int i3 = this.iCodeTop;
        if (i3 <= i2) {
            throw Kit.codeBug();
        }
        addGotoOp(i);
        resolveGoto(i3, i2);
    }

    private void resolveForwardGoto(int i) {
        if (this.iCodeTop < i + 3) {
            throw Kit.codeBug();
        }
        resolveGoto(i, this.iCodeTop);
    }

    private void resolveGoto(int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 0 || i3 > 2) {
            int i4 = i + 1;
            if (i3 != ((short) i3)) {
                if (this.itsData.longJumps == null) {
                    this.itsData.longJumps = new UintMap();
                }
                this.itsData.longJumps.put(i4, i2);
                i3 = 0;
            }
            byte[] bArr = this.itsData.itsICode;
            bArr[i4] = (byte) (i3 >> 8);
            bArr[i4 + 1] = (byte) i3;
            return;
        }
        throw Kit.codeBug();
    }

    private void addToken(int i) {
        if (Icode.validTokenCode(i)) {
            addUint8(i);
            return;
        }
        throw Kit.codeBug();
    }

    private void addIcode(int i) {
        if (Icode.validIcode(i)) {
            addUint8(i & 255);
            return;
        }
        throw Kit.codeBug();
    }

    private void addUint8(int i) {
        if ((i & InputDeviceCompat.SOURCE_ANY) != 0) {
            throw Kit.codeBug();
        }
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 == bArr.length) {
            bArr = increaseICodeCapacity(1);
        }
        bArr[i2] = (byte) i;
        this.iCodeTop = i2 + 1;
    }

    private void addUint16(int i) {
        if ((SupportMenu.CATEGORY_MASK & i) != 0) {
            throw Kit.codeBug();
        }
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 + 2 > bArr.length) {
            bArr = increaseICodeCapacity(2);
        }
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
        this.iCodeTop = i2 + 2;
    }

    private void addInt(int i) {
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 + 4 > bArr.length) {
            bArr = increaseICodeCapacity(4);
        }
        bArr[i2] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
        this.iCodeTop = i2 + 4;
    }

    private int getDoubleIndex(double d) {
        int i = this.doubleTableTop;
        if (i == 0) {
            this.itsData.itsDoubleTable = new double[64];
        } else if (this.itsData.itsDoubleTable.length == i) {
            Object obj = new double[(i * 2)];
            System.arraycopy(this.itsData.itsDoubleTable, 0, obj, 0, i);
            this.itsData.itsDoubleTable = obj;
        }
        this.itsData.itsDoubleTable[i] = d;
        this.doubleTableTop = i + 1;
        return i;
    }

    private void addGotoOp(int i) {
        byte[] bArr = this.itsData.itsICode;
        int i2 = this.iCodeTop;
        if (i2 + 3 > bArr.length) {
            bArr = increaseICodeCapacity(3);
        }
        bArr[i2] = (byte) i;
        this.iCodeTop = (i2 + 1) + 2;
    }

    private void addVarOp(int i, int i2) {
        switch (i) {
            case -7:
                break;
            case 55:
            case 56:
                if (i2 < 128) {
                    addIcode(i == 55 ? -48 : -49);
                    addUint8(i2);
                    return;
                }
                break;
            case 156:
                if (i2 < 128) {
                    addIcode(-61);
                    addUint8(i2);
                    return;
                }
                addIndexOp(-60, i2);
                return;
            default:
                throw Kit.codeBug();
        }
        addIndexOp(i, i2);
    }

    private void addStringOp(int i, String str) {
        addStringPrefix(str);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addIndexOp(int i, int i2) {
        addIndexPrefix(i2);
        if (Icode.validIcode(i)) {
            addIcode(i);
        } else {
            addToken(i);
        }
    }

    private void addStringPrefix(String str) {
        int i = this.strings.get(str, -1);
        if (i == -1) {
            i = this.strings.size();
            this.strings.put(str, i);
        }
        if (i < 4) {
            addIcode(-41 - i);
        } else if (i <= 255) {
            addIcode(-45);
            addUint8(i);
        } else if (i <= 65535) {
            addIcode(-46);
            addUint16(i);
        } else {
            addIcode(-47);
            addInt(i);
        }
    }

    private void addIndexPrefix(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        if (i < 6) {
            addIcode(-32 - i);
        } else if (i <= 255) {
            addIcode(-38);
            addUint8(i);
        } else if (i <= 65535) {
            addIcode(-39);
            addUint16(i);
        } else {
            addIcode(-40);
            addInt(i);
        }
    }

    private void addExceptionHandler(int i, int i2, int i3, boolean z, int i4, int i5) {
        int i6 = 0;
        int i7 = this.exceptionTableTop;
        int[] iArr = this.itsData.itsExceptionTable;
        if (iArr == null) {
            if (i7 != 0) {
                Kit.codeBug();
            }
            iArr = new int[12];
            this.itsData.itsExceptionTable = iArr;
        } else if (iArr.length == i7) {
            iArr = new int[(iArr.length * 2)];
            System.arraycopy(this.itsData.itsExceptionTable, 0, iArr, 0, i7);
            this.itsData.itsExceptionTable = iArr;
        }
        iArr[i7 + 0] = i;
        iArr[i7 + 1] = i2;
        iArr[i7 + 2] = i3;
        int i8 = i7 + 3;
        if (z) {
            i6 = 1;
        }
        iArr[i8] = i6;
        iArr[i7 + 4] = i4;
        iArr[i7 + 5] = i5;
        this.exceptionTableTop = i7 + 6;
    }

    private byte[] increaseICodeCapacity(int i) {
        int length = this.itsData.itsICode.length;
        int i2 = this.iCodeTop;
        if (i2 + i <= length) {
            throw Kit.codeBug();
        }
        length *= 2;
        if (i2 + i > length) {
            length = i2 + i;
        }
        Object obj = new byte[length];
        System.arraycopy(this.itsData.itsICode, 0, obj, 0, i2);
        this.itsData.itsICode = obj;
        return obj;
    }

    private void stackChange(int i) {
        if (i <= 0) {
            this.stackDepth += i;
            return;
        }
        int i2 = this.stackDepth + i;
        if (i2 > this.itsData.itsMaxStack) {
            this.itsData.itsMaxStack = i2;
        }
        this.stackDepth = i2;
    }

    private int allocLocal() {
        int i = this.localTop;
        this.localTop++;
        if (this.localTop > this.itsData.itsMaxLocals) {
            this.itsData.itsMaxLocals = this.localTop;
        }
        return i;
    }

    private void releaseLocal(int i) {
        this.localTop--;
        if (i != this.localTop) {
            Kit.codeBug();
        }
    }
}
