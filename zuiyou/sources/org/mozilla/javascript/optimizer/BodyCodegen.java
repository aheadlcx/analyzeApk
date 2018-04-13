package org.mozilla.javascript.optimizer;

import android.support.v4.app.NotificationCompat;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.tencent.bugly.BuglyStrategy$a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

class BodyCodegen {
    static final /* synthetic */ boolean $assertionsDisabled = (!BodyCodegen.class.desiredAssertionStatus());
    private static final int ECMAERROR_EXCEPTION = 2;
    private static final int EVALUATOR_EXCEPTION = 1;
    private static final int EXCEPTION_MAX = 5;
    private static final int FINALLY_EXCEPTION = 4;
    static final int GENERATOR_START = 0;
    static final int GENERATOR_TERMINATE = -1;
    static final int GENERATOR_YIELD_START = 1;
    private static final int JAVASCRIPT_EXCEPTION = 0;
    private static final int MAX_LOCALS = 1024;
    private static final int THROWABLE_EXCEPTION = 3;
    private short argsLocal;
    ClassFileWriter cfw;
    Codegen codegen;
    CompilerEnvirons compilerEnv;
    private short contextLocal;
    private int enterAreaStartLabel;
    private int epilogueLabel;
    private ExceptionManager exceptionManager = new ExceptionManager();
    private Map<Node, FinallyReturnPoint> finallys;
    private short firstFreeLocal;
    private OptFunctionNode fnCurrent;
    private short funObjLocal;
    private short generatorStateLocal;
    private int generatorSwitch;
    private boolean hasVarsInRegs;
    private boolean inDirectCallFunction;
    private boolean inLocalBlock;
    private boolean isGenerator;
    private boolean itsForcedObjectParameters;
    private int itsLineNumber;
    private short itsOneArgArray;
    private short itsZeroArgArray;
    private List<Node> literals;
    private int[] locals;
    private short localsMax;
    private int maxLocals = 0;
    private int maxStack = 0;
    private short operationLocal;
    private short popvLocal;
    private int savedCodeOffset;
    ScriptNode scriptOrFn;
    public int scriptOrFnIndex;
    private short thisObjLocal;
    private short[] varRegisters;
    private short variableObjectLocal;

    private class ExceptionManager {
        private LinkedList<ExceptionInfo> exceptionInfo = new LinkedList();

        private class ExceptionInfo {
            Node currentFinally = null;
            int[] exceptionStarts = new int[5];
            Node finallyBlock;
            int[] handlerLabels = new int[5];
            Jump node;

            ExceptionInfo(Jump jump, Node node) {
                this.node = jump;
                this.finallyBlock = node;
            }
        }

        ExceptionManager() {
        }

        void pushExceptionInfo(Jump jump) {
            this.exceptionInfo.add(new ExceptionInfo(jump, BodyCodegen.this.getFinallyAtTarget(jump.getFinally())));
        }

        void addHandler(int i, int i2, int i3) {
            ExceptionInfo top = getTop();
            top.handlerLabels[i] = i2;
            top.exceptionStarts[i] = i3;
        }

        void setHandlers(int[] iArr, int i) {
            getTop();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] != 0) {
                    addHandler(i2, iArr[i2], i);
                }
            }
        }

        int removeHandler(int i, int i2) {
            ExceptionInfo top = getTop();
            if (top.handlerLabels[i] == 0) {
                return 0;
            }
            int i3 = top.handlerLabels[i];
            endCatch(top, i, i2);
            top.handlerLabels[i] = 0;
            return i3;
        }

        void popExceptionInfo() {
            this.exceptionInfo.removeLast();
        }

        void markInlineFinallyStart(Node node, int i) {
            ListIterator listIterator = this.exceptionInfo.listIterator(this.exceptionInfo.size());
            while (listIterator.hasPrevious()) {
                ExceptionInfo exceptionInfo = (ExceptionInfo) listIterator.previous();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (exceptionInfo.handlerLabels[i2] != 0 && exceptionInfo.currentFinally == null) {
                        endCatch(exceptionInfo, i2, i);
                        exceptionInfo.exceptionStarts[i2] = 0;
                        exceptionInfo.currentFinally = node;
                    }
                }
                if (exceptionInfo.finallyBlock == node) {
                    return;
                }
            }
        }

        void markInlineFinallyEnd(Node node, int i) {
            ListIterator listIterator = this.exceptionInfo.listIterator(this.exceptionInfo.size());
            while (listIterator.hasPrevious()) {
                ExceptionInfo exceptionInfo = (ExceptionInfo) listIterator.previous();
                for (int i2 = 0; i2 < 5; i2++) {
                    if (exceptionInfo.handlerLabels[i2] != 0 && exceptionInfo.currentFinally == node) {
                        exceptionInfo.exceptionStarts[i2] = i;
                        exceptionInfo.currentFinally = null;
                    }
                }
                if (exceptionInfo.finallyBlock == node) {
                    return;
                }
            }
        }

        private void endCatch(ExceptionInfo exceptionInfo, int i, int i2) {
            if (exceptionInfo.exceptionStarts[i] == 0) {
                throw new IllegalStateException("bad exception start");
            }
            if (BodyCodegen.this.cfw.getLabelPC(exceptionInfo.exceptionStarts[i]) != BodyCodegen.this.cfw.getLabelPC(i2)) {
                BodyCodegen.this.cfw.addExceptionHandler(exceptionInfo.exceptionStarts[i], i2, exceptionInfo.handlerLabels[i], BodyCodegen.this.exceptionTypeToName(i));
            }
        }

        private ExceptionInfo getTop() {
            return (ExceptionInfo) this.exceptionInfo.getLast();
        }
    }

    static class FinallyReturnPoint {
        public List<Integer> jsrPoints = new ArrayList();
        public int tableLabel = 0;

        FinallyReturnPoint() {
        }
    }

    BodyCodegen() {
    }

    void generateBodyCode() {
        Node lastChild;
        this.isGenerator = Codegen.isGenerator(this.scriptOrFn);
        initBodyGeneration();
        if (this.isGenerator) {
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn) + "_gen", "(" + this.codegen.mainClassSignature + "Lorg/mozilla/javascript/Context;" + "Lorg/mozilla/javascript/Scriptable;" + "Ljava/lang/Object;" + "Ljava/lang/Object;I)Ljava/lang/Object;", (short) 10);
        } else {
            this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short) 10);
        }
        generatePrologue();
        if (this.fnCurrent != null) {
            lastChild = this.scriptOrFn.getLastChild();
        } else {
            lastChild = this.scriptOrFn;
        }
        generateStatement(lastChild);
        generateEpilogue();
        this.cfw.stopMethod((short) (this.localsMax + 1));
        if (this.isGenerator) {
            generateGenerator();
        }
        if (this.literals != null) {
            for (int i = 0; i < this.literals.size(); i++) {
                lastChild = (Node) this.literals.get(i);
                int type = lastChild.getType();
                switch (type) {
                    case 65:
                        generateArrayLiteralFactory(lastChild, i + 1);
                        break;
                    case 66:
                        generateObjectLiteralFactory(lastChild, i + 1);
                        break;
                    default:
                        Kit.codeBug(Token.typeToName(type));
                        break;
                }
            }
        }
    }

    private void generateGenerator() {
        this.cfw.startMethod(this.codegen.getBodyMethodName(this.scriptOrFn), this.codegen.getBodyMethodSignature(this.scriptOrFn), (short) 10);
        initBodyGeneration();
        short s = this.firstFreeLocal;
        this.firstFreeLocal = (short) (s + 1);
        this.argsLocal = s;
        this.localsMax = this.firstFreeLocal;
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.argsLocal);
        addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(this.scriptOrFnIndex);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        generateNestedFunctionInits();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addLoadConstant(this.maxLocals);
        this.cfw.addLoadConstant(this.maxStack);
        addOptRuntimeInvoke("createNativeGenerator", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;II)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateNestedFunctionInits() {
        int functionCount = this.scriptOrFn.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, i);
            if (optFunctionNode.fnode.getFunctionType() == 1) {
                visitFunction(optFunctionNode, 1);
            }
        }
    }

    private void initBodyGeneration() {
        this.varRegisters = null;
        if (this.scriptOrFn.getType() == 109) {
            this.fnCurrent = OptFunctionNode.get(this.scriptOrFn);
            this.hasVarsInRegs = !this.fnCurrent.fnode.requiresActivation();
            if (this.hasVarsInRegs) {
                int paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount();
                if (paramAndVarCount != 0) {
                    this.varRegisters = new short[paramAndVarCount];
                }
            }
            this.inDirectCallFunction = this.fnCurrent.isTargetOfDirectCall();
            if (this.inDirectCallFunction && !this.hasVarsInRegs) {
                Codegen.badTree();
            }
        } else {
            this.fnCurrent = null;
            this.hasVarsInRegs = false;
            this.inDirectCallFunction = false;
        }
        this.locals = new int[1024];
        this.funObjLocal = (short) 0;
        this.contextLocal = (short) 1;
        this.variableObjectLocal = (short) 2;
        this.thisObjLocal = (short) 3;
        this.localsMax = (short) 4;
        this.firstFreeLocal = (short) 4;
        this.popvLocal = (short) -1;
        this.argsLocal = (short) -1;
        this.itsZeroArgArray = (short) -1;
        this.itsOneArgArray = (short) -1;
        this.epilogueLabel = -1;
        this.enterAreaStartLabel = -1;
        this.generatorStateLocal = (short) -1;
    }

    private void generatePrologue() {
        int paramCount;
        int i;
        if (this.inDirectCallFunction) {
            paramCount = this.scriptOrFn.getParamCount();
            if (this.firstFreeLocal != (short) 4) {
                Kit.codeBug();
            }
            for (i = 0; i != paramCount; i++) {
                this.varRegisters[i] = this.firstFreeLocal;
                this.firstFreeLocal = (short) (this.firstFreeLocal + 3);
            }
            if (!this.fnCurrent.getParameterNumberContext()) {
                this.itsForcedObjectParameters = true;
                for (i = 0; i != paramCount; i++) {
                    short s = this.varRegisters[i];
                    this.cfw.addALoad(s);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int acquireLabel = this.cfw.acquireLabel();
                    this.cfw.add(166, acquireLabel);
                    this.cfw.addDLoad(s + 1);
                    addDoubleWrap();
                    this.cfw.addAStore(s);
                    this.cfw.markLabel(acquireLabel);
                }
            }
        }
        if (this.fnCurrent != null) {
            this.cfw.addALoad(this.funObjLocal);
            this.cfw.addInvoke(185, "org/mozilla/javascript/Scriptable", "getParentScope", "()Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.variableObjectLocal);
        }
        short s2 = this.firstFreeLocal;
        this.firstFreeLocal = (short) (s2 + 1);
        this.argsLocal = s2;
        this.localsMax = this.firstFreeLocal;
        if (this.isGenerator) {
            s2 = this.firstFreeLocal;
            this.firstFreeLocal = (short) (s2 + 1);
            this.operationLocal = s2;
            this.localsMax = this.firstFreeLocal;
            this.cfw.addALoad(this.thisObjLocal);
            s2 = this.firstFreeLocal;
            this.firstFreeLocal = (short) (s2 + 1);
            this.generatorStateLocal = s2;
            this.localsMax = this.firstFreeLocal;
            this.cfw.add(192, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState");
            this.cfw.add(89);
            this.cfw.addAStore(this.generatorStateLocal);
            this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "thisObj", "Lorg/mozilla/javascript/Scriptable;");
            this.cfw.addAStore(this.thisObjLocal);
            if (this.epilogueLabel == -1) {
                this.epilogueLabel = this.cfw.acquireLabel();
            }
            List resumptionPoints = ((FunctionNode) this.scriptOrFn).getResumptionPoints();
            if (resumptionPoints != null) {
                generateGetGeneratorResumptionPoint();
                this.generatorSwitch = this.cfw.addTableSwitch(0, resumptionPoints.size() + 0);
                generateCheckForThrowOrClose(-1, false, 0);
            }
        }
        if (this.fnCurrent == null && this.scriptOrFn.getRegexpCount() != 0) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addInvoke(184, this.codegen.mainClassName, "_reInit", "(Lorg/mozilla/javascript/Context;)V");
        }
        if (this.compilerEnv.isGenerateObserverCount()) {
            saveCurrentCodeOffset();
        }
        String str;
        if (this.hasVarsInRegs) {
            i = this.scriptOrFn.getParamCount();
            if (i > 0 && !this.inDirectCallFunction) {
                this.cfw.addALoad(this.argsLocal);
                this.cfw.add(190);
                this.cfw.addPush(i);
                paramCount = this.cfw.acquireLabel();
                this.cfw.add(162, paramCount);
                this.cfw.addALoad(this.argsLocal);
                this.cfw.addPush(i);
                addScriptRuntimeInvoke("padArguments", "([Ljava/lang/Object;I)[Ljava/lang/Object;");
                this.cfw.addAStore(this.argsLocal);
                this.cfw.markLabel(paramCount);
            }
            int paramCount2 = this.fnCurrent.fnode.getParamCount();
            int paramAndVarCount = this.fnCurrent.fnode.getParamAndVarCount();
            boolean[] paramAndVarConst = this.fnCurrent.fnode.getParamAndVarConst();
            acquireLabel = 0;
            i = -1;
            while (acquireLabel != paramAndVarCount) {
                int i2;
                if (acquireLabel < paramCount2) {
                    if (this.inDirectCallFunction) {
                        paramCount = -1;
                        i2 = i;
                    } else {
                        paramCount = getNewWordLocal();
                        this.cfw.addALoad(this.argsLocal);
                        this.cfw.addPush(acquireLabel);
                        this.cfw.add(50);
                        this.cfw.addAStore(paramCount);
                        i2 = i;
                    }
                } else if (this.fnCurrent.isNumberVar(acquireLabel)) {
                    paramCount = getNewWordPairLocal(paramAndVarConst[acquireLabel]);
                    this.cfw.addPush(0.0d);
                    this.cfw.addDStore(paramCount);
                    i2 = i;
                } else {
                    paramCount = getNewWordLocal(paramAndVarConst[acquireLabel]);
                    if (i == -1) {
                        Codegen.pushUndefined(this.cfw);
                        i = paramCount;
                    } else {
                        this.cfw.addALoad(i);
                    }
                    this.cfw.addAStore(paramCount);
                    i2 = i;
                }
                if (paramCount >= 0) {
                    if (paramAndVarConst[acquireLabel]) {
                        this.cfw.addPush(0);
                        ClassFileWriter classFileWriter = this.cfw;
                        if (this.fnCurrent.isNumberVar(acquireLabel)) {
                            i = 2;
                        } else {
                            i = 1;
                        }
                        classFileWriter.addIStore(i + paramCount);
                    }
                    this.varRegisters[acquireLabel] = paramCount;
                }
                if (this.compilerEnv.isGenerateDebugInfo()) {
                    String paramOrVarName = this.fnCurrent.fnode.getParamOrVarName(acquireLabel);
                    str = this.fnCurrent.isNumberVar(acquireLabel) ? "D" : "Ljava/lang/Object;";
                    int currentCodeOffset = this.cfw.getCurrentCodeOffset();
                    if (paramCount < 0) {
                        paramCount = this.varRegisters[acquireLabel];
                    }
                    this.cfw.addVariableDescriptor(paramOrVarName, str, currentCodeOffset, paramCount);
                }
                acquireLabel++;
                i = i2;
            }
        } else if (!this.isGenerator) {
            if (this.fnCurrent != null) {
                str = "activation";
                this.cfw.addALoad(this.funObjLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addALoad(this.argsLocal);
                addScriptRuntimeInvoke("createFunctionActivation", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(this.variableObjectLocal);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("enterActivationFunction", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V");
            } else {
                str = "global";
                this.cfw.addALoad(this.funObjLocal);
                this.cfw.addALoad(this.thisObjLocal);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(0);
                addScriptRuntimeInvoke("initScript", "(Lorg/mozilla/javascript/NativeFunction;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Z)V");
            }
            this.enterAreaStartLabel = this.cfw.acquireLabel();
            this.epilogueLabel = this.cfw.acquireLabel();
            this.cfw.markLabel(this.enterAreaStartLabel);
            generateNestedFunctionInits();
            if (this.compilerEnv.isGenerateDebugInfo()) {
                this.cfw.addVariableDescriptor(str, "Lorg/mozilla/javascript/Scriptable;", this.cfw.getCurrentCodeOffset(), this.variableObjectLocal);
            }
            if (this.fnCurrent == null) {
                this.popvLocal = getNewWordLocal();
                Codegen.pushUndefined(this.cfw);
                this.cfw.addAStore(this.popvLocal);
                i = this.scriptOrFn.getEndLineno();
                if (i != -1) {
                    this.cfw.addLineNumberEntry((short) i);
                    return;
                }
                return;
            }
            if (this.fnCurrent.itsContainsCalls0) {
                this.itsZeroArgArray = getNewWordLocal();
                this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
                this.cfw.addAStore(this.itsZeroArgArray);
            }
            if (this.fnCurrent.itsContainsCalls1) {
                this.itsOneArgArray = getNewWordLocal();
                this.cfw.addPush(1);
                this.cfw.add(189, "java/lang/Object");
                this.cfw.addAStore(this.itsOneArgArray);
            }
        }
    }

    private void generateGetGeneratorResumptionPoint() {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.add(180, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateSetGeneratorResumptionPoint(int i) {
        this.cfw.addALoad(this.generatorStateLocal);
        this.cfw.addLoadConstant(i);
        this.cfw.add(181, "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState", "resumptionPoint", "I");
    }

    private void generateGetGeneratorStackState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorStackState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateEpilogue() {
        if (this.compilerEnv.isGenerateObserverCount()) {
            addInstructionCount();
        }
        if (this.isGenerator) {
            int i;
            Node node;
            int i2;
            Map liveLocals = ((FunctionNode) this.scriptOrFn).getLiveLocals();
            if (liveLocals != null) {
                List resumptionPoints = ((FunctionNode) this.scriptOrFn).getResumptionPoints();
                for (i = 0; i < resumptionPoints.size(); i++) {
                    node = (Node) resumptionPoints.get(i);
                    int[] iArr = (int[]) liveLocals.get(node);
                    if (iArr != null) {
                        this.cfw.markTableSwitchCase(this.generatorSwitch, getNextGeneratorState(node));
                        generateGetGeneratorLocalsState();
                        for (i2 = 0; i2 < iArr.length; i2++) {
                            this.cfw.add(89);
                            this.cfw.addLoadConstant(i2);
                            this.cfw.add(50);
                            this.cfw.addAStore(iArr[i2]);
                        }
                        this.cfw.add(87);
                        this.cfw.add(167, getTargetLabel(node));
                    }
                }
            }
            if (this.finallys != null) {
                for (Node node2 : this.finallys.keySet()) {
                    if (node2.getType() == 125) {
                        FinallyReturnPoint finallyReturnPoint = (FinallyReturnPoint) this.finallys.get(node2);
                        this.cfw.markLabel(finallyReturnPoint.tableLabel, (short) 1);
                        int addTableSwitch = this.cfw.addTableSwitch(0, finallyReturnPoint.jsrPoints.size() - 1);
                        this.cfw.markTableSwitchDefault(addTableSwitch);
                        i2 = 0;
                        for (i = 0; i < finallyReturnPoint.jsrPoints.size(); i++) {
                            this.cfw.markTableSwitchCase(addTableSwitch, i2);
                            this.cfw.add(167, ((Integer) finallyReturnPoint.jsrPoints.get(i)).intValue());
                            i2++;
                        }
                    }
                }
            }
        }
        if (this.epilogueLabel != -1) {
            this.cfw.markLabel(this.epilogueLabel);
        }
        if (this.hasVarsInRegs) {
            this.cfw.add(176);
        } else if (this.isGenerator) {
            if (((FunctionNode) this.scriptOrFn).getResumptionPoints() != null) {
                this.cfw.markTableSwitchDefault(this.generatorSwitch);
            }
            generateSetGeneratorResumptionPoint(-1);
            this.cfw.addALoad(this.variableObjectLocal);
            addOptRuntimeInvoke("throwStopIteration", "(Ljava/lang/Object;)V");
            Codegen.pushUndefined(this.cfw);
            this.cfw.add(176);
        } else if (this.fnCurrent == null) {
            this.cfw.addALoad(this.popvLocal);
            this.cfw.add(176);
        } else {
            generateActivationExit();
            this.cfw.add(176);
            int acquireLabel = this.cfw.acquireLabel();
            this.cfw.markHandler(acquireLabel);
            short newWordLocal = getNewWordLocal();
            this.cfw.addAStore(newWordLocal);
            generateActivationExit();
            this.cfw.addALoad(newWordLocal);
            releaseWordLocal(newWordLocal);
            this.cfw.add(191);
            this.cfw.addExceptionHandler(this.enterAreaStartLabel, this.epilogueLabel, acquireLabel, null);
        }
    }

    private void generateGetGeneratorLocalsState() {
        this.cfw.addALoad(this.generatorStateLocal);
        addOptRuntimeInvoke("getGeneratorLocalsState", "(Ljava/lang/Object;)[Ljava/lang/Object;");
    }

    private void generateActivationExit() {
        if (this.fnCurrent == null || this.hasVarsInRegs) {
            throw Kit.codeBug();
        }
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("exitActivationFunction", "(Lorg/mozilla/javascript/Context;)V");
    }

    private void generateStatement(Node node) {
        int i = 0;
        updateLineNumber(node);
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        int existingIntProp;
        switch (type) {
            case 2:
                generateExpression(firstChild, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("enterWith", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(this.variableObjectLocal);
                incReferenceWordLocal(this.variableObjectLocal);
                return;
            case 3:
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("leaveWith", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(this.variableObjectLocal);
                decReferenceWordLocal(this.variableObjectLocal);
                return;
            case 4:
            case 64:
                if (!this.isGenerator) {
                    if (firstChild != null) {
                        generateExpression(firstChild, node);
                    } else if (type == 4) {
                        Codegen.pushUndefined(this.cfw);
                    } else if (this.popvLocal < (short) 0) {
                        throw Codegen.badTree();
                    } else {
                        this.cfw.addALoad(this.popvLocal);
                    }
                }
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount();
                }
                if (this.epilogueLabel == -1) {
                    if (this.hasVarsInRegs) {
                        this.epilogueLabel = this.cfw.acquireLabel();
                    } else {
                        throw Codegen.badTree();
                    }
                }
                this.cfw.add(167, this.epilogueLabel);
                return;
            case 5:
            case 6:
            case 7:
            case 135:
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount();
                }
                visitGoto((Jump) node, type, firstChild);
                return;
            case 50:
                generateExpression(firstChild, node);
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount();
                }
                generateThrowJavaScriptException();
                return;
            case 51:
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount();
                }
                this.cfw.addALoad(getLocalBlockRegister(node));
                this.cfw.add(191);
                return;
            case 57:
                this.cfw.setStackTop((short) 0);
                i = getLocalBlockRegister(node);
                existingIntProp = node.getExistingIntProp(14);
                String string = firstChild.getString();
                generateExpression(firstChild.getNext(), node);
                if (existingIntProp == 0) {
                    this.cfw.add(1);
                } else {
                    this.cfw.addALoad(i);
                }
                this.cfw.addPush(string);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("newCatchScope", "(Ljava/lang/Throwable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
                this.cfw.addAStore(i);
                return;
            case 58:
            case 59:
            case 60:
                generateExpression(firstChild, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                if (type != 58) {
                    i = type == 59 ? 1 : 2;
                }
                this.cfw.addPush(i);
                addScriptRuntimeInvoke("enumInit", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                this.cfw.addAStore(getLocalBlockRegister(node));
                return;
            case 81:
                visitTryCatchFinally((Jump) node, firstChild);
                return;
            case 109:
                OptFunctionNode optFunctionNode = OptFunctionNode.get(this.scriptOrFn, node.getExistingIntProp(1));
                existingIntProp = optFunctionNode.fnode.getFunctionType();
                if (existingIntProp == 3) {
                    visitFunction(optFunctionNode, existingIntProp);
                    return;
                } else if (existingIntProp != 1) {
                    throw Codegen.badTree();
                } else {
                    return;
                }
            case 114:
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount();
                }
                visitSwitch((Jump) node, firstChild);
                return;
            case 123:
            case 128:
            case 129:
            case 130:
            case 132:
            case 136:
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount(1);
                }
                while (firstChild != null) {
                    generateStatement(firstChild);
                    firstChild = firstChild.getNext();
                }
                return;
            case 125:
                if (this.isGenerator) {
                    if (this.compilerEnv.isGenerateObserverCount()) {
                        saveCurrentCodeOffset();
                    }
                    this.cfw.setStackTop((short) 1);
                    short newWordLocal = getNewWordLocal();
                    i = this.cfw.acquireLabel();
                    existingIntProp = this.cfw.acquireLabel();
                    this.cfw.markLabel(i);
                    generateIntegerWrap();
                    this.cfw.addAStore(newWordLocal);
                    for (Node node2 = firstChild; node2 != null; node2 = node2.getNext()) {
                        generateStatement(node2);
                    }
                    this.cfw.addALoad(newWordLocal);
                    this.cfw.add(192, "java/lang/Integer");
                    generateIntegerUnwrap();
                    FinallyReturnPoint finallyReturnPoint = (FinallyReturnPoint) this.finallys.get(node);
                    finallyReturnPoint.tableLabel = this.cfw.acquireLabel();
                    this.cfw.add(167, finallyReturnPoint.tableLabel);
                    releaseWordLocal((short) newWordLocal);
                    this.cfw.markLabel(existingIntProp);
                    return;
                }
                return;
            case 131:
                if (this.compilerEnv.isGenerateObserverCount()) {
                    addInstructionCount();
                }
                this.cfw.markLabel(getTargetLabel(node));
                if (this.compilerEnv.isGenerateObserverCount()) {
                    saveCurrentCodeOffset();
                    return;
                }
                return;
            case 133:
                if (firstChild.getType() == 56) {
                    visitSetVar(firstChild, firstChild.getFirstChild(), false);
                    return;
                } else if (firstChild.getType() == 156) {
                    visitSetConstVar(firstChild, firstChild.getFirstChild(), false);
                    return;
                } else if (firstChild.getType() == 72) {
                    generateYieldPoint(firstChild, false);
                    return;
                } else {
                    generateExpression(firstChild, node);
                    if (node.getIntProp(8, -1) != -1) {
                        this.cfw.add(88);
                        return;
                    } else {
                        this.cfw.add(87);
                        return;
                    }
                }
            case 134:
                generateExpression(firstChild, node);
                if (this.popvLocal < (short) 0) {
                    this.popvLocal = getNewWordLocal();
                }
                this.cfw.addAStore(this.popvLocal);
                return;
            case 141:
                boolean z = this.inLocalBlock;
                this.inLocalBlock = true;
                short newWordLocal2 = getNewWordLocal();
                if (this.isGenerator) {
                    this.cfw.add(1);
                    this.cfw.addAStore(newWordLocal2);
                }
                node.putIntProp(2, newWordLocal2);
                while (firstChild != null) {
                    generateStatement(firstChild);
                    firstChild = firstChild.getNext();
                }
                releaseWordLocal((short) newWordLocal2);
                node.removeProp(2);
                this.inLocalBlock = z;
                return;
            case 160:
                return;
            default:
                throw Codegen.badTree();
        }
    }

    private void generateIntegerWrap() {
        this.cfw.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
    }

    private void generateIntegerUnwrap() {
        this.cfw.addInvoke(182, "java/lang/Integer", "intValue", "()I");
    }

    private void generateThrowJavaScriptException() {
        this.cfw.add(187, "org/mozilla/javascript/JavaScriptException");
        this.cfw.add(90);
        this.cfw.add(95);
        this.cfw.addPush(this.scriptOrFn.getSourceName());
        this.cfw.addPush(this.itsLineNumber);
        this.cfw.addInvoke(183, "org/mozilla/javascript/JavaScriptException", "<init>", "(Ljava/lang/Object;Ljava/lang/String;I)V");
        this.cfw.add(191);
    }

    private int getNextGeneratorState(Node node) {
        return ((FunctionNode) this.scriptOrFn).getResumptionPoints().indexOf(node) + 1;
    }

    private void generateExpression(Node node, Node node2) {
        boolean z = true;
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        int acquireLabel;
        int acquireLabel2;
        OptFunctionNode optFunctionNode;
        Node next;
        String str;
        Node next2;
        switch (type) {
            case 8:
                visitSetName(node, firstChild);
                return;
            case 9:
            case 10:
            case 11:
            case 18:
            case 19:
            case 20:
                visitBitOp(node, type, firstChild);
                return;
            case 12:
            case 13:
            case 46:
            case 47:
                acquireLabel = this.cfw.acquireLabel();
                acquireLabel2 = this.cfw.acquireLabel();
                visitIfJumpEqOp(node, firstChild, acquireLabel, acquireLabel2);
                addJumpedBooleanWrap(acquireLabel, acquireLabel2);
                return;
            case 14:
            case 15:
            case 16:
            case 17:
            case 52:
            case 53:
                acquireLabel = this.cfw.acquireLabel();
                acquireLabel2 = this.cfw.acquireLabel();
                visitIfJumpRelOp(node, firstChild, acquireLabel, acquireLabel2);
                addJumpedBooleanWrap(acquireLabel, acquireLabel2);
                return;
            case 21:
                generateExpression(firstChild, node);
                generateExpression(firstChild.getNext(), node);
                switch (node.getIntProp(8, -1)) {
                    case 0:
                        this.cfw.add(99);
                        return;
                    case 1:
                        addOptRuntimeInvoke("add", "(DLjava/lang/Object;)Ljava/lang/Object;");
                        return;
                    case 2:
                        addOptRuntimeInvoke("add", "(Ljava/lang/Object;D)Ljava/lang/Object;");
                        return;
                    default:
                        if (firstChild.getType() == 41) {
                            addScriptRuntimeInvoke("add", "(Ljava/lang/CharSequence;Ljava/lang/Object;)Ljava/lang/CharSequence;");
                            return;
                        } else if (firstChild.getNext().getType() == 41) {
                            addScriptRuntimeInvoke("add", "(Ljava/lang/Object;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;");
                            return;
                        } else {
                            this.cfw.addALoad(this.contextLocal);
                            addScriptRuntimeInvoke("add", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                            return;
                        }
                }
            case 22:
                visitArithmetic(node, 103, firstChild, node2);
                return;
            case 23:
                visitArithmetic(node, 107, firstChild, node2);
                return;
            case 24:
            case 25:
                visitArithmetic(node, type == 24 ? 111 : 115, firstChild, node2);
                return;
            case 26:
                acquireLabel = this.cfw.acquireLabel();
                acquireLabel2 = this.cfw.acquireLabel();
                type = this.cfw.acquireLabel();
                generateIfJump(firstChild, node, acquireLabel, acquireLabel2);
                this.cfw.markLabel(acquireLabel);
                this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                this.cfw.add(167, type);
                this.cfw.markLabel(acquireLabel2);
                this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                this.cfw.markLabel(type);
                this.cfw.adjustStackTop(-1);
                return;
            case 27:
                generateExpression(firstChild, node);
                addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
                this.cfw.addPush(-1);
                this.cfw.add(130);
                this.cfw.add(135);
                addDoubleWrap();
                return;
            case 28:
            case 29:
                generateExpression(firstChild, node);
                addObjectToDouble();
                if (type == 29) {
                    this.cfw.add(119);
                }
                addDoubleWrap();
                return;
            case 30:
            case 38:
                acquireLabel = node.getIntProp(10, 0);
                if (acquireLabel == 0) {
                    optFunctionNode = (OptFunctionNode) node.getProp(9);
                    if (optFunctionNode != null) {
                        visitOptimizedCall(node, optFunctionNode, type, firstChild);
                        return;
                    } else if (type == 38) {
                        visitStandardCall(node, firstChild);
                        return;
                    } else {
                        visitStandardNew(node, firstChild);
                        return;
                    }
                }
                visitSpecialCall(node, type, acquireLabel, firstChild);
                return;
            case 31:
                if (firstChild.getType() != 49) {
                    z = false;
                }
                generateExpression(firstChild, node);
                generateExpression(firstChild.getNext(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addPush(z);
                addScriptRuntimeInvoke(RequestParameters.SUBRESOURCE_DELETE, "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Z)Ljava/lang/Object;");
                return;
            case 32:
                generateExpression(firstChild, node);
                addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                return;
            case 33:
            case 34:
                visitGetProp(node, firstChild);
                return;
            case 35:
            case 139:
                visitSetProp(type, node, firstChild);
                return;
            case 36:
                generateExpression(firstChild, node);
                generateExpression(firstChild.getNext(), node);
                this.cfw.addALoad(this.contextLocal);
                if (node.getIntProp(8, -1) != -1) {
                    addScriptRuntimeInvoke("getObjectIndex", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                    return;
                }
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                return;
            case 37:
            case 140:
                visitSetElem(type, node, firstChild);
                return;
            case 39:
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(node.getString());
                addScriptRuntimeInvoke("name", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
                return;
            case 40:
                double d = node.getDouble();
                if (node.getIntProp(8, -1) != -1) {
                    this.cfw.addPush(d);
                    return;
                } else {
                    this.codegen.pushNumberAsObject(this.cfw, d);
                    return;
                }
            case 41:
                this.cfw.addPush(node.getString());
                return;
            case 42:
                this.cfw.add(1);
                return;
            case 43:
                this.cfw.addALoad(this.thisObjLocal);
                return;
            case 44:
                this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
                return;
            case 45:
                this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
                return;
            case 48:
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.add(178, this.codegen.mainClassName, this.codegen.getCompiledRegexpName(this.scriptOrFn, node.getExistingIntProp(4)), "Ljava/lang/Object;");
                this.cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "wrapRegExp", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
                return;
            case 49:
                break;
            case 54:
                this.cfw.addALoad(getLocalBlockRegister(node));
                return;
            case 55:
                visitGetVar(node);
                return;
            case 56:
                visitSetVar(node, firstChild, true);
                return;
            case 61:
            case 62:
                this.cfw.addALoad(getLocalBlockRegister(node));
                if (type == 61) {
                    addScriptRuntimeInvoke("enumNext", "(Ljava/lang/Object;)Ljava/lang/Boolean;");
                    return;
                }
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("enumId", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                return;
            case 63:
                this.cfw.add(42);
                return;
            case 65:
                visitArrayLiteral(node, firstChild, false);
                return;
            case 66:
                visitObjectLiteral(node, firstChild, false);
                return;
            case 67:
                generateExpression(firstChild, node);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                return;
            case 68:
            case 142:
                generateExpression(firstChild, node);
                next = firstChild.getNext();
                if (type == 142) {
                    this.cfw.add(89);
                    this.cfw.addALoad(this.contextLocal);
                    addScriptRuntimeInvoke("refGet", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                }
                generateExpression(next, node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("refSet", "(Lorg/mozilla/javascript/Ref;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
                return;
            case 69:
                generateExpression(firstChild, node);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("refDel", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                return;
            case 70:
                generateFunctionAndThisObj(firstChild, node);
                generateCallArgArray(node, firstChild.getNext(), false);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("callRef", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Ref;");
                return;
            case 71:
                str = (String) node.getProp(17);
                generateExpression(firstChild, node);
                this.cfw.addPush(str);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("specialRef", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Ref;");
                return;
            case 72:
                generateYieldPoint(node, true);
                return;
            case 73:
                visitStrictSetName(node, firstChild);
                return;
            case 74:
                generateExpression(firstChild, node);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("setDefaultNamespace", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
                return;
            case 75:
                generateExpression(firstChild, node);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("escapeAttributeValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                return;
            case 76:
                generateExpression(firstChild, node);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("escapeTextValue", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;");
                return;
            case 77:
            case 78:
            case 79:
            case 80:
                String str2;
                int intProp = node.getIntProp(16, 0);
                next = firstChild;
                do {
                    generateExpression(next, node);
                    next = next.getNext();
                } while (next != null);
                this.cfw.addALoad(this.contextLocal);
                switch (type) {
                    case 77:
                        str2 = "memberRef";
                        str = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                        break;
                    case 78:
                        str2 = "memberRef";
                        str = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;I)Lorg/mozilla/javascript/Ref;";
                        break;
                    case 79:
                        str2 = "nameRef";
                        str = "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                        this.cfw.addALoad(this.variableObjectLocal);
                        break;
                    case 80:
                        str2 = "nameRef";
                        str = "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;";
                        this.cfw.addALoad(this.variableObjectLocal);
                        break;
                    default:
                        throw Kit.codeBug();
                }
                this.cfw.addPush(intProp);
                addScriptRuntimeInvoke(str2, str);
                return;
            case 89:
                for (next = firstChild.getNext(); next != null; next = next.getNext()) {
                    generateExpression(firstChild, node);
                    this.cfw.add(87);
                    firstChild = next;
                }
                generateExpression(firstChild, node);
                return;
            case 102:
                next = firstChild.getNext();
                next2 = next.getNext();
                generateExpression(firstChild, node);
                addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                int acquireLabel3 = this.cfw.acquireLabel();
                this.cfw.add(153, acquireLabel3);
                short stackTop = this.cfw.getStackTop();
                generateExpression(next, node);
                acquireLabel = this.cfw.acquireLabel();
                this.cfw.add(167, acquireLabel);
                this.cfw.markLabel(acquireLabel3, stackTop);
                generateExpression(next2, node);
                this.cfw.markLabel(acquireLabel);
                return;
            case 104:
            case 105:
                generateExpression(firstChild, node);
                this.cfw.add(89);
                addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                acquireLabel = this.cfw.acquireLabel();
                if (type == 105) {
                    this.cfw.add(153, acquireLabel);
                } else {
                    this.cfw.add(154, acquireLabel);
                }
                this.cfw.add(87);
                generateExpression(firstChild.getNext(), node);
                this.cfw.markLabel(acquireLabel);
                return;
            case 106:
            case 107:
                visitIncDec(node);
                return;
            case 109:
                if (this.fnCurrent != null || node2.getType() != 136) {
                    optFunctionNode = OptFunctionNode.get(this.scriptOrFn, node.getExistingIntProp(1));
                    acquireLabel2 = optFunctionNode.fnode.getFunctionType();
                    if (acquireLabel2 != 2) {
                        throw Codegen.badTree();
                    }
                    visitFunction(optFunctionNode, acquireLabel2);
                    return;
                }
                return;
            case 126:
                generateExpression(firstChild, node);
                this.cfw.add(87);
                Codegen.pushUndefined(this.cfw);
                return;
            case 137:
                visitTypeofname(node);
                return;
            case 138:
                return;
            case 146:
                visitDotQuery(node, firstChild);
                return;
            case 149:
                if (firstChild.getType() == 40) {
                    acquireLabel = firstChild.getIntProp(8, -1);
                } else {
                    acquireLabel = -1;
                }
                if (acquireLabel != -1) {
                    firstChild.removeProp(8);
                    generateExpression(firstChild, node);
                    firstChild.putIntProp(8, acquireLabel);
                    return;
                }
                generateExpression(firstChild, node);
                addDoubleWrap();
                return;
            case 150:
                generateExpression(firstChild, node);
                addObjectToDouble();
                return;
            case 155:
                visitSetConst(node, firstChild);
                return;
            case 156:
                visitSetConstVar(node, firstChild, true);
                return;
            case 157:
                next = firstChild.getNext();
                generateStatement(firstChild);
                generateExpression(next, node);
                return;
            case 159:
                next = firstChild.getNext();
                next2 = next.getNext();
                generateStatement(firstChild);
                generateExpression(next.getFirstChild(), next);
                generateStatement(next2);
                return;
            default:
                throw new RuntimeException("Unexpected node type " + type);
        }
        while (firstChild != null) {
            generateExpression(firstChild, node);
            firstChild = firstChild.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(node.getString());
        addScriptRuntimeInvoke("bind", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void generateYieldPoint(Node node, boolean z) {
        int i;
        short stackTop = this.cfw.getStackTop();
        if (this.maxStack > stackTop) {
            i = this.maxStack;
        } else {
            short s = stackTop;
        }
        this.maxStack = i;
        if (this.cfw.getStackTop() != (short) 0) {
            generateGetGeneratorStackState();
            for (i = 0; i < stackTop; i++) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addLoadConstant(i);
                this.cfw.add(95);
                this.cfw.add(83);
            }
            this.cfw.add(87);
        }
        Node firstChild = node.getFirstChild();
        if (firstChild != null) {
            generateExpression(firstChild, node);
        } else {
            Codegen.pushUndefined(this.cfw);
        }
        i = getNextGeneratorState(node);
        generateSetGeneratorResumptionPoint(i);
        boolean generateSaveLocals = generateSaveLocals(node);
        this.cfw.add(176);
        generateCheckForThrowOrClose(getTargetLabel(node), generateSaveLocals, i);
        if (stackTop != (short) 0) {
            generateGetGeneratorStackState();
            for (s = (short) 0; s < stackTop; s++) {
                this.cfw.add(89);
                this.cfw.addLoadConstant((stackTop - s) - 1);
                this.cfw.add(50);
                this.cfw.add(95);
            }
            this.cfw.add(87);
        }
        if (z) {
            this.cfw.addALoad(this.argsLocal);
        }
    }

    private void generateCheckForThrowOrClose(int i, boolean z, int i2) {
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        this.cfw.addALoad(this.argsLocal);
        generateThrowJavaScriptException();
        this.cfw.markLabel(acquireLabel2);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.add(192, "java/lang/Throwable");
        this.cfw.add(191);
        if (i != -1) {
            this.cfw.markLabel(i);
        }
        if (!z) {
            this.cfw.markTableSwitchCase(this.generatorSwitch, i2);
        }
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(2);
        this.cfw.add(159, acquireLabel2);
        this.cfw.addILoad(this.operationLocal);
        this.cfw.addLoadConstant(1);
        this.cfw.add(159, acquireLabel);
    }

    private void generateIfJump(Node node, Node node2, int i, int i2) {
        int type = node.getType();
        Node firstChild = node.getFirstChild();
        switch (type) {
            case 12:
            case 13:
            case 46:
            case 47:
                visitIfJumpEqOp(node, firstChild, i, i2);
                return;
            case 14:
            case 15:
            case 16:
            case 17:
            case 52:
            case 53:
                visitIfJumpRelOp(node, firstChild, i, i2);
                return;
            case 26:
                generateIfJump(firstChild, node, i2, i);
                return;
            case 104:
            case 105:
                int acquireLabel = this.cfw.acquireLabel();
                if (type == 105) {
                    generateIfJump(firstChild, node, acquireLabel, i2);
                } else {
                    generateIfJump(firstChild, node, i, acquireLabel);
                }
                this.cfw.markLabel(acquireLabel);
                generateIfJump(firstChild.getNext(), node, i, i2);
                return;
            default:
                generateExpression(node, node2);
                addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
                this.cfw.add(154, i);
                this.cfw.add(167, i2);
                return;
        }
    }

    private void visitFunction(OptFunctionNode optFunctionNode, int i) {
        int index = this.codegen.getIndex(optFunctionNode.fnode);
        this.cfw.add(187, this.codegen.mainClassName);
        this.cfw.add(89);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(index);
        this.cfw.addInvoke(183, this.codegen.mainClassName, "<init>", "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V");
        if (i != 2) {
            this.cfw.addPush(i);
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.contextLocal);
            addOptRuntimeInvoke("initFunction", "(Lorg/mozilla/javascript/NativeFunction;ILorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;)V");
        }
    }

    private int getTargetLabel(Node node) {
        int labelId = node.labelId();
        if (labelId != -1) {
            return labelId;
        }
        labelId = this.cfw.acquireLabel();
        node.labelId(labelId);
        return labelId;
    }

    private void visitGoto(Jump jump, int i, Node node) {
        Node node2 = jump.target;
        if (i == 6 || i == 7) {
            if (node == null) {
                throw Codegen.badTree();
            }
            int targetLabel = getTargetLabel(node2);
            int acquireLabel = this.cfw.acquireLabel();
            if (i == 6) {
                generateIfJump(node, jump, targetLabel, acquireLabel);
            } else {
                generateIfJump(node, jump, acquireLabel, targetLabel);
            }
            this.cfw.markLabel(acquireLabel);
        } else if (i != 135) {
            addGoto(node2, 167);
        } else if (this.isGenerator) {
            addGotoWithReturn(node2);
        } else {
            inlineFinally(node2);
        }
    }

    private void addGotoWithReturn(Node node) {
        FinallyReturnPoint finallyReturnPoint = (FinallyReturnPoint) this.finallys.get(node);
        this.cfw.addLoadConstant(finallyReturnPoint.jsrPoints.size());
        addGoto(node, 167);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        finallyReturnPoint.jsrPoints.add(Integer.valueOf(acquireLabel));
    }

    private void generateArrayLiteralFactory(Node node, int i) {
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i;
        initBodyGeneration();
        short s = this.firstFreeLocal;
        this.firstFreeLocal = (short) (s + 1);
        this.argsLocal = s;
        this.localsMax = this.firstFreeLocal;
        this.cfw.startMethod(str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short) 2);
        visitArrayLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void generateObjectLiteralFactory(Node node, int i) {
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + i;
        initBodyGeneration();
        short s = this.firstFreeLocal;
        this.firstFreeLocal = (short) (s + 1);
        this.argsLocal = s;
        this.localsMax = this.firstFreeLocal;
        this.cfw.startMethod(str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;", (short) 2);
        visitObjectLiteral(node, node.getFirstChild(), true);
        this.cfw.add(176);
        this.cfw.stopMethod((short) (this.localsMax + 1));
    }

    private void visitArrayLiteral(Node node, Node node2, boolean z) {
        int i = 0;
        int i2 = 0;
        for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
            i2++;
        }
        if (z || ((i2 <= 10 && this.cfw.getCurrentCodeOffset() <= BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH) || this.hasVarsInRegs || this.isGenerator || this.inLocalBlock)) {
            if (this.isGenerator) {
                for (int i3 = 0; i3 != i2; i3++) {
                    generateExpression(node2, node);
                    node2 = node2.getNext();
                }
                addNewObjectArray(i2);
                while (i != i2) {
                    this.cfw.add(90);
                    this.cfw.add(95);
                    this.cfw.addPush((i2 - i) - 1);
                    this.cfw.add(95);
                    this.cfw.add(83);
                    i++;
                }
            } else {
                addNewObjectArray(i2);
                while (i != i2) {
                    this.cfw.add(89);
                    this.cfw.addPush(i);
                    generateExpression(node2, node);
                    this.cfw.add(83);
                    node2 = node2.getNext();
                    i++;
                }
            }
            int[] iArr = (int[]) node.getProp(11);
            if (iArr == null) {
                this.cfw.add(1);
                this.cfw.add(3);
            } else {
                this.cfw.addPush(OptRuntime.encodeIntArray(iArr));
                this.cfw.addPush(iArr.length);
            }
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addOptRuntimeInvoke("newArrayLiteral", "([Ljava/lang/Object;Ljava/lang/String;ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.literals == null) {
            this.literals = new LinkedList();
        }
        this.literals.add(node);
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.addInvoke(182, this.codegen.mainClassName, str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void addLoadPropertyIds(Object[] objArr, int i) {
        addNewObjectArray(i);
        for (int i2 = 0; i2 != i; i2++) {
            this.cfw.add(89);
            this.cfw.addPush(i2);
            Object obj = objArr[i2];
            if (obj instanceof String) {
                this.cfw.addPush((String) obj);
            } else {
                this.cfw.addPush(((Integer) obj).intValue());
                addScriptRuntimeInvoke("wrapInt", "(I)Ljava/lang/Integer;");
            }
            this.cfw.add(83);
        }
    }

    private void addLoadPropertyValues(Node node, Node node2, int i) {
        int i2 = 0;
        int i3;
        if (this.isGenerator) {
            for (i3 = 0; i3 != i; i3++) {
                int type = node2.getType();
                if (type == 151 || type == 152) {
                    generateExpression(node2.getFirstChild(), node);
                } else {
                    generateExpression(node2, node);
                }
                node2 = node2.getNext();
            }
            addNewObjectArray(i);
            while (i2 != i) {
                this.cfw.add(90);
                this.cfw.add(95);
                this.cfw.addPush((i - i2) - 1);
                this.cfw.add(95);
                this.cfw.add(83);
                i2++;
            }
            return;
        }
        addNewObjectArray(i);
        while (i2 != i) {
            this.cfw.add(89);
            this.cfw.addPush(i2);
            i3 = node2.getType();
            if (i3 == 151 || i3 == 152) {
                generateExpression(node2.getFirstChild(), node);
            } else {
                generateExpression(node2, node);
            }
            this.cfw.add(83);
            node2 = node2.getNext();
            i2++;
        }
    }

    private void visitObjectLiteral(Node node, Node node2, boolean z) {
        Object[] objArr = (Object[]) node.getProp(12);
        int length = objArr.length;
        if (z || ((length <= 10 && this.cfw.getCurrentCodeOffset() <= BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH) || this.hasVarsInRegs || this.isGenerator || this.inLocalBlock)) {
            if (this.isGenerator) {
                addLoadPropertyValues(node, node2, length);
                addLoadPropertyIds(objArr, length);
                this.cfw.add(95);
            } else {
                addLoadPropertyIds(objArr, length);
                addLoadPropertyValues(node, node2, length);
            }
            int i = 0;
            Node node3 = node2;
            while (i != length) {
                int type = node3.getType();
                if (type == 151 || type == 152) {
                    i = 1;
                    break;
                } else {
                    node3 = node3.getNext();
                    i++;
                }
            }
            i = 0;
            if (i != 0) {
                this.cfw.addPush(length);
                this.cfw.add(188, 10);
                for (i = 0; i != length; i++) {
                    this.cfw.add(89);
                    this.cfw.addPush(i);
                    int type2 = node2.getType();
                    if (type2 == 151) {
                        this.cfw.add(2);
                    } else if (type2 == 152) {
                        this.cfw.add(4);
                    } else {
                        this.cfw.add(3);
                    }
                    this.cfw.add(79);
                    node2 = node2.getNext();
                }
            } else {
                this.cfw.add(1);
            }
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("newObjectLiteral", "([Ljava/lang/Object;[Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
            return;
        }
        if (this.literals == null) {
            this.literals = new LinkedList();
        }
        this.literals.add(node);
        String str = this.codegen.getBodyMethodName(this.scriptOrFn) + "_literal" + this.literals.size();
        this.cfw.addALoad(this.funObjLocal);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addALoad(this.thisObjLocal);
        this.cfw.addALoad(this.argsLocal);
        this.cfw.addInvoke(182, this.codegen.mainClassName, str, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitSpecialCall(Node node, int i, int i2, Node node2) {
        String str;
        String str2;
        this.cfw.addALoad(this.contextLocal);
        if (i == 30) {
            generateExpression(node2, node);
        } else {
            generateFunctionAndThisObj(node2, node);
        }
        generateCallArgArray(node, node2.getNext(), false);
        if (i == 30) {
            str = "newObjectSpecial";
            str2 = "(Lorg/mozilla/javascript/Context;Ljava/lang/Object;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;";
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
        } else {
            String str3 = "callSpecial";
            str = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;ILjava/lang/String;I)Ljava/lang/Object;";
            this.cfw.addALoad(this.variableObjectLocal);
            this.cfw.addALoad(this.thisObjLocal);
            this.cfw.addPush(i2);
            str2 = this.scriptOrFn.getSourceName();
            ClassFileWriter classFileWriter = this.cfw;
            if (str2 == null) {
                str2 = "";
            }
            classFileWriter.addPush(str2);
            this.cfw.addPush(this.itsLineNumber);
            str2 = str;
            str = str3;
        }
        addOptRuntimeInvoke(str, str2);
    }

    private void visitStandardCall(Node node, Node node2) {
        if (node.getType() != 38) {
            throw Codegen.badTree();
        }
        String str;
        String str2;
        Node next = node2.getNext();
        int type = node2.getType();
        Node firstChild;
        if (next == null) {
            if (type == 39) {
                this.cfw.addPush(node2.getString());
                str = "callName0";
                str2 = "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else if (type == 33) {
                firstChild = node2.getFirstChild();
                generateExpression(firstChild, node);
                this.cfw.addPush(firstChild.getNext().getString());
                str = "callProp0";
                str2 = "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else if (type == 34) {
                throw Kit.codeBug();
            } else {
                generateFunctionAndThisObj(node2, node);
                str = "call0";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            }
        } else if (type == 39) {
            str2 = node2.getString();
            generateCallArgArray(node, next, false);
            this.cfw.addPush(str2);
            str = "callName";
            str2 = "([Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
        } else {
            int i = 0;
            for (firstChild = next; firstChild != null; firstChild = firstChild.getNext()) {
                i++;
            }
            generateFunctionAndThisObj(node2, node);
            if (i == 1) {
                generateExpression(next, node);
                str = "call1";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else if (i == 2) {
                generateExpression(next, node);
                generateExpression(next.getNext(), node);
                str = "call2";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            } else {
                generateCallArgArray(node, next, false);
                str = "callN";
                str2 = "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;";
            }
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addOptRuntimeInvoke(str, str2);
    }

    private void visitStandardNew(Node node, Node node2) {
        if (node.getType() != 30) {
            throw Codegen.badTree();
        }
        Node next = node2.getNext();
        generateExpression(node2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        generateCallArgArray(node, next, false);
        addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void visitOptimizedCall(Node node, OptFunctionNode optFunctionNode, int i, Node node2) {
        Node next = node2.getNext();
        String str = this.codegen.mainClassName;
        short s = (short) 0;
        if (i == 30) {
            generateExpression(node2, node);
        } else {
            generateFunctionAndThisObj(node2, node);
            s = getNewWordLocal();
            this.cfw.addAStore(s);
        }
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(89);
        this.cfw.add(193, str);
        this.cfw.add(153, acquireLabel2);
        this.cfw.add(192, str);
        this.cfw.add(89);
        this.cfw.add(180, str, "_id", "I");
        this.cfw.addPush(this.codegen.getIndex(optFunctionNode.fnode));
        this.cfw.add(160, acquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (i == 30) {
            this.cfw.add(1);
        } else {
            this.cfw.addALoad(s);
        }
        for (Node node3 = next; node3 != null; node3 = node3.getNext()) {
            int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node3);
            if (nodeIsDirectCallParameter >= 0) {
                this.cfw.addALoad(nodeIsDirectCallParameter);
                this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
            } else if (node3.getIntProp(8, -1) == 0) {
                this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                generateExpression(node3, node);
            } else {
                generateExpression(node3, node);
                this.cfw.addPush(0.0d);
            }
        }
        this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
        ClassFileWriter classFileWriter = this.cfw;
        String str2 = this.codegen.mainClassName;
        if (i == 30) {
            str = this.codegen.getDirectCtorName(optFunctionNode.fnode);
        } else {
            str = this.codegen.getBodyMethodName(optFunctionNode.fnode);
        }
        classFileWriter.addInvoke(184, str2, str, this.codegen.getBodyMethodSignature(optFunctionNode.fnode));
        this.cfw.add(167, acquireLabel);
        this.cfw.markLabel(acquireLabel2);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (i != 30) {
            this.cfw.addALoad(s);
            releaseWordLocal(s);
        }
        generateCallArgArray(node, next, true);
        if (i == 30) {
            addScriptRuntimeInvoke("newObject", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        } else {
            this.cfw.addInvoke(185, "org/mozilla/javascript/Callable", NotificationCompat.CATEGORY_CALL, "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        }
        this.cfw.markLabel(acquireLabel);
    }

    private void generateCallArgArray(Node node, Node node2, boolean z) {
        int i = 0;
        int i2 = 0;
        for (Node node3 = node2; node3 != null; node3 = node3.getNext()) {
            i2++;
        }
        if (i2 != 1 || this.itsOneArgArray < (short) 0) {
            addNewObjectArray(i2);
        } else {
            this.cfw.addALoad(this.itsOneArgArray);
        }
        while (i != i2) {
            if (!this.isGenerator) {
                this.cfw.add(89);
                this.cfw.addPush(i);
            }
            if (z) {
                int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
                if (nodeIsDirectCallParameter >= 0) {
                    dcpLoadAsObject(nodeIsDirectCallParameter);
                } else {
                    generateExpression(node2, node);
                    if (node2.getIntProp(8, -1) == 0) {
                        addDoubleWrap();
                    }
                }
            } else {
                generateExpression(node2, node);
            }
            if (this.isGenerator) {
                short newWordLocal = getNewWordLocal();
                this.cfw.addAStore(newWordLocal);
                this.cfw.add(192, "[Ljava/lang/Object;");
                this.cfw.add(89);
                this.cfw.addPush(i);
                this.cfw.addALoad(newWordLocal);
                releaseWordLocal(newWordLocal);
            }
            this.cfw.add(83);
            node2 = node2.getNext();
            i++;
        }
    }

    private void generateFunctionAndThisObj(Node node, Node node2) {
        int type = node.getType();
        switch (node.getType()) {
            case 33:
            case 36:
                Node firstChild = node.getFirstChild();
                generateExpression(firstChild, node);
                firstChild = firstChild.getNext();
                if (type != 33) {
                    generateExpression(firstChild, node);
                    if (node.getIntProp(8, -1) != -1) {
                        addDoubleWrap();
                    }
                    this.cfw.addALoad(this.contextLocal);
                    this.cfw.addALoad(this.variableObjectLocal);
                    addScriptRuntimeInvoke("getElemFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                    break;
                }
                this.cfw.addPush(firstChild.getString());
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getPropFunctionAndThis", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                break;
            case 34:
                throw Kit.codeBug();
            case 39:
                this.cfw.addPush(node.getString());
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getNameFunctionAndThis", "(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;");
                break;
            default:
                generateExpression(node, node2);
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("getValueFunctionAndThis", "(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;");
                break;
        }
        this.cfw.addALoad(this.contextLocal);
        addScriptRuntimeInvoke("lastStoredScriptable", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;");
    }

    private void updateLineNumber(Node node) {
        this.itsLineNumber = node.getLineno();
        if (this.itsLineNumber != -1) {
            this.cfw.addLineNumberEntry((short) this.itsLineNumber);
        }
    }

    private void visitTryCatchFinally(Jump jump, Node node) {
        int labelId;
        short newWordLocal = getNewWordLocal();
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addAStore(newWordLocal);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel, (short) 0);
        Node node2 = jump.target;
        Node node3 = jump.getFinally();
        int[] iArr = new int[5];
        this.exceptionManager.pushExceptionInfo(jump);
        if (node2 != null) {
            iArr[0] = this.cfw.acquireLabel();
            iArr[1] = this.cfw.acquireLabel();
            iArr[2] = this.cfw.acquireLabel();
            Context currentContext = Context.getCurrentContext();
            if (currentContext != null && currentContext.hasFeature(13)) {
                iArr[3] = this.cfw.acquireLabel();
            }
        }
        if (node3 != null) {
            iArr[4] = this.cfw.acquireLabel();
        }
        this.exceptionManager.setHandlers(iArr, acquireLabel);
        if (this.isGenerator && node3 != null) {
            FinallyReturnPoint finallyReturnPoint = new FinallyReturnPoint();
            if (this.finallys == null) {
                this.finallys = new HashMap();
            }
            this.finallys.put(node3, finallyReturnPoint);
            this.finallys.put(node3.getNext(), finallyReturnPoint);
        }
        while (node != null) {
            if (node == node2) {
                int targetLabel = getTargetLabel(node2);
                this.exceptionManager.removeHandler(0, targetLabel);
                this.exceptionManager.removeHandler(1, targetLabel);
                this.exceptionManager.removeHandler(2, targetLabel);
                this.exceptionManager.removeHandler(3, targetLabel);
            }
            generateStatement(node);
            node = node.getNext();
        }
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        int localBlockRegister = getLocalBlockRegister(jump);
        if (node2 != null) {
            labelId = node2.labelId();
            generateCatchBlock(0, newWordLocal, labelId, localBlockRegister, iArr[0]);
            generateCatchBlock(1, newWordLocal, labelId, localBlockRegister, iArr[1]);
            generateCatchBlock(2, newWordLocal, labelId, localBlockRegister, iArr[2]);
            Context currentContext2 = Context.getCurrentContext();
            if (currentContext2 != null && currentContext2.hasFeature(13)) {
                generateCatchBlock(3, newWordLocal, labelId, localBlockRegister, iArr[3]);
            }
        }
        if (node3 != null) {
            int acquireLabel3 = this.cfw.acquireLabel();
            targetLabel = this.cfw.acquireLabel();
            this.cfw.markHandler(acquireLabel3);
            if (!this.isGenerator) {
                this.cfw.markLabel(iArr[4]);
            }
            this.cfw.addAStore(localBlockRegister);
            this.cfw.addALoad(newWordLocal);
            this.cfw.addAStore(this.variableObjectLocal);
            labelId = node3.labelId();
            if (this.isGenerator) {
                addGotoWithReturn(node3);
            } else {
                inlineFinally(node3, iArr[4], targetLabel);
            }
            this.cfw.addALoad(localBlockRegister);
            if (this.isGenerator) {
                this.cfw.add(192, "java/lang/Throwable");
            }
            this.cfw.add(191);
            this.cfw.markLabel(targetLabel);
            if (this.isGenerator) {
                this.cfw.addExceptionHandler(acquireLabel, labelId, acquireLabel3, null);
            }
        }
        releaseWordLocal(newWordLocal);
        this.cfw.markLabel(acquireLabel2);
        if (!this.isGenerator) {
            this.exceptionManager.popExceptionInfo();
        }
    }

    private void generateCatchBlock(int i, short s, int i2, int i3, int i4) {
        if (i4 == 0) {
            i4 = this.cfw.acquireLabel();
        }
        this.cfw.markHandler(i4);
        this.cfw.addAStore(i3);
        this.cfw.addALoad(s);
        this.cfw.addAStore(this.variableObjectLocal);
        exceptionTypeToName(i);
        this.cfw.add(167, i2);
    }

    private String exceptionTypeToName(int i) {
        if (i == 0) {
            return "org/mozilla/javascript/JavaScriptException";
        }
        if (i == 1) {
            return "org/mozilla/javascript/EvaluatorException";
        }
        if (i == 2) {
            return "org/mozilla/javascript/EcmaError";
        }
        if (i == 3) {
            return "java/lang/Throwable";
        }
        if (i == 4) {
            return null;
        }
        throw Kit.codeBug();
    }

    private void inlineFinally(Node node, int i, int i2) {
        Node finallyAtTarget = getFinallyAtTarget(node);
        finallyAtTarget.resetTargets();
        this.exceptionManager.markInlineFinallyStart(finallyAtTarget, i);
        for (Node firstChild = finallyAtTarget.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            generateStatement(firstChild);
        }
        this.exceptionManager.markInlineFinallyEnd(finallyAtTarget, i2);
    }

    private void inlineFinally(Node node) {
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        inlineFinally(node, acquireLabel, acquireLabel2);
        this.cfw.markLabel(acquireLabel2);
    }

    private Node getFinallyAtTarget(Node node) {
        if (node == null) {
            return null;
        }
        if (node.getType() == 125) {
            return node;
        }
        if (node != null && node.getType() == 131) {
            node = node.getNext();
            if (node != null && node.getType() == 125) {
                return node;
            }
        }
        throw Kit.codeBug("bad finally target");
    }

    private boolean generateSaveLocals(Node node) {
        short s;
        int i = 0;
        int i2 = 0;
        for (s = (short) 0; s < this.firstFreeLocal; s++) {
            if (this.locals[s] != 0) {
                i2++;
            }
        }
        if (i2 == 0) {
            ((FunctionNode) this.scriptOrFn).addLiveLocals(node, null);
            return false;
        }
        this.maxLocals = this.maxLocals > i2 ? this.maxLocals : i2;
        int[] iArr = new int[i2];
        int i3 = 0;
        for (s = (short) 0; s < this.firstFreeLocal; s++) {
            if (this.locals[s] != 0) {
                iArr[i3] = s;
                i3++;
            }
        }
        ((FunctionNode) this.scriptOrFn).addLiveLocals(node, iArr);
        generateGetGeneratorLocalsState();
        while (i < i2) {
            this.cfw.add(89);
            this.cfw.addLoadConstant(i);
            this.cfw.addALoad(iArr[i]);
            this.cfw.add(83);
            i++;
        }
        this.cfw.add(87);
        return true;
    }

    private void visitSwitch(Jump jump, Node node) {
        generateExpression(node, jump);
        short newWordLocal = getNewWordLocal();
        this.cfw.addAStore(newWordLocal);
        for (Node node2 = (Jump) node.getNext(); node2 != null; Jump jump2 = (Jump) node2.getNext()) {
            if (node2.getType() != 115) {
                throw Codegen.badTree();
            }
            generateExpression(node2.getFirstChild(), node2);
            this.cfw.addALoad(newWordLocal);
            addScriptRuntimeInvoke("shallowEq", "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            addGoto(node2.target, 154);
        }
        releaseWordLocal(newWordLocal);
    }

    private void visitTypeofname(Node node) {
        if (this.hasVarsInRegs) {
            int indexForNameNode = this.fnCurrent.fnode.getIndexForNameNode(node);
            if (indexForNameNode >= 0) {
                if (this.fnCurrent.isNumberVar(indexForNameNode)) {
                    this.cfw.addPush("number");
                    return;
                } else if (varIsDirectCallParameter(indexForNameNode)) {
                    short s = this.varRegisters[indexForNameNode];
                    this.cfw.addALoad(s);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int acquireLabel = this.cfw.acquireLabel();
                    this.cfw.add(165, acquireLabel);
                    short stackTop = this.cfw.getStackTop();
                    this.cfw.addALoad(s);
                    addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                    indexForNameNode = this.cfw.acquireLabel();
                    this.cfw.add(167, indexForNameNode);
                    this.cfw.markLabel(acquireLabel, stackTop);
                    this.cfw.addPush("number");
                    this.cfw.markLabel(indexForNameNode);
                    return;
                } else {
                    this.cfw.addALoad(this.varRegisters[indexForNameNode]);
                    addScriptRuntimeInvoke("typeof", "(Ljava/lang/Object;)Ljava/lang/String;");
                    return;
                }
            }
        }
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(node.getString());
        addScriptRuntimeInvoke("typeofName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/String;");
    }

    private void saveCurrentCodeOffset() {
        this.savedCodeOffset = this.cfw.getCurrentCodeOffset();
    }

    private void addInstructionCount() {
        addInstructionCount(Math.max(this.cfw.getCurrentCodeOffset() - this.savedCodeOffset, 1));
    }

    private void addInstructionCount(int i) {
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(i);
        addScriptRuntimeInvoke("addInstructionCount", "(Lorg/mozilla/javascript/Context;I)V");
    }

    private void visitIncDec(Node node) {
        int i = 0;
        int existingIntProp = node.getExistingIntProp(13);
        Node firstChild = node.getFirstChild();
        Node firstChild2;
        switch (firstChild.getType()) {
            case 33:
                firstChild2 = firstChild.getFirstChild();
                generateExpression(firstChild2, node);
                generateExpression(firstChild2.getNext(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(existingIntProp);
                addScriptRuntimeInvoke("propIncrDecr", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            case 34:
                throw Kit.codeBug();
            case 36:
                firstChild2 = firstChild.getFirstChild();
                generateExpression(firstChild2, node);
                generateExpression(firstChild2.getNext(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(existingIntProp);
                if (firstChild2.getNext().getIntProp(8, -1) != -1) {
                    addOptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                    return;
                } else {
                    addScriptRuntimeInvoke("elemIncrDecr", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                    return;
                }
            case 39:
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(firstChild.getString());
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addPush(existingIntProp);
                addScriptRuntimeInvoke("nameIncrDecr", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;");
                return;
            case 55:
                if (!this.hasVarsInRegs) {
                    Kit.codeBug();
                }
                int i2 = (existingIntProp & 2) != 0 ? 1 : 0;
                int varIndex = this.fnCurrent.getVarIndex(firstChild);
                short s = this.varRegisters[varIndex];
                if (this.fnCurrent.fnode.getParamAndVarConst()[varIndex]) {
                    if (node.getIntProp(8, -1) != -1) {
                        if (varIsDirectCallParameter(varIndex)) {
                            i = 1;
                        }
                        this.cfw.addDLoad(i + s);
                        if (i2 == 0) {
                            this.cfw.addPush(1.0d);
                            if ((existingIntProp & 1) == 0) {
                                this.cfw.add(99);
                                return;
                            } else {
                                this.cfw.add(103);
                                return;
                            }
                        }
                        return;
                    }
                    if (varIsDirectCallParameter(varIndex)) {
                        dcpLoadAsObject(s);
                    } else {
                        this.cfw.addALoad(s);
                    }
                    if (i2 != 0) {
                        this.cfw.add(89);
                        addObjectToDouble();
                        this.cfw.add(88);
                        return;
                    }
                    addObjectToDouble();
                    this.cfw.addPush(1.0d);
                    if ((existingIntProp & 1) == 0) {
                        this.cfw.add(99);
                    } else {
                        this.cfw.add(103);
                    }
                    addDoubleWrap();
                    return;
                } else if (node.getIntProp(8, -1) != -1) {
                    if (varIsDirectCallParameter(varIndex)) {
                        i = 1;
                    }
                    this.cfw.addDLoad(s + i);
                    if (i2 != 0) {
                        this.cfw.add(92);
                    }
                    this.cfw.addPush(1.0d);
                    if ((existingIntProp & 1) == 0) {
                        this.cfw.add(99);
                    } else {
                        this.cfw.add(103);
                    }
                    if (i2 == 0) {
                        this.cfw.add(92);
                    }
                    this.cfw.addDStore(i + s);
                    return;
                } else {
                    if (varIsDirectCallParameter(varIndex)) {
                        dcpLoadAsObject(s);
                    } else {
                        this.cfw.addALoad(s);
                    }
                    if (i2 != 0) {
                        this.cfw.add(89);
                    }
                    addObjectToDouble();
                    this.cfw.addPush(1.0d);
                    if ((existingIntProp & 1) == 0) {
                        this.cfw.add(99);
                    } else {
                        this.cfw.add(103);
                    }
                    addDoubleWrap();
                    if (i2 == 0) {
                        this.cfw.add(89);
                    }
                    this.cfw.addAStore(s);
                    return;
                }
            case 67:
                generateExpression(firstChild.getFirstChild(), node);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                this.cfw.addPush(existingIntProp);
                addScriptRuntimeInvoke("refIncrDecr", "(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;");
                return;
            default:
                Codegen.badTree();
                return;
        }
    }

    private static boolean isArithmeticNode(Node node) {
        int type = node.getType();
        return type == 22 || type == 25 || type == 24 || type == 23;
    }

    private void visitArithmetic(Node node, int i, Node node2, Node node3) {
        if (node.getIntProp(8, -1) != -1) {
            generateExpression(node2, node);
            generateExpression(node2.getNext(), node);
            this.cfw.add(i);
            return;
        }
        boolean isArithmeticNode = isArithmeticNode(node3);
        generateExpression(node2, node);
        if (!isArithmeticNode(node2)) {
            addObjectToDouble();
        }
        generateExpression(node2.getNext(), node);
        if (!isArithmeticNode(node2.getNext())) {
            addObjectToDouble();
        }
        this.cfw.add(i);
        if (!isArithmeticNode) {
            addDoubleWrap();
        }
    }

    private void visitBitOp(Node node, int i, Node node2) {
        int intProp = node.getIntProp(8, -1);
        generateExpression(node2, node);
        if (i == 20) {
            addScriptRuntimeInvoke("toUint32", "(Ljava/lang/Object;)J");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            this.cfw.addPush(31);
            this.cfw.add(126);
            this.cfw.add(125);
            this.cfw.add(138);
            addDoubleWrap();
            return;
        }
        if (intProp == -1) {
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(Ljava/lang/Object;)I");
        } else {
            addScriptRuntimeInvoke("toInt32", "(D)I");
            generateExpression(node2.getNext(), node);
            addScriptRuntimeInvoke("toInt32", "(D)I");
        }
        switch (i) {
            case 9:
                this.cfw.add(128);
                break;
            case 10:
                this.cfw.add(130);
                break;
            case 11:
                this.cfw.add(126);
                break;
            case 18:
                this.cfw.add(120);
                break;
            case 19:
                this.cfw.add(122);
                break;
            default:
                throw Codegen.badTree();
        }
        this.cfw.add(135);
        if (intProp == -1) {
            addDoubleWrap();
        }
    }

    private int nodeIsDirectCallParameter(Node node) {
        if (node.getType() == 55 && this.inDirectCallFunction && !this.itsForcedObjectParameters) {
            int varIndex = this.fnCurrent.getVarIndex(node);
            if (this.fnCurrent.isParameter(varIndex)) {
                return this.varRegisters[varIndex];
            }
        }
        return -1;
    }

    private boolean varIsDirectCallParameter(int i) {
        return this.fnCurrent.isParameter(i) && this.inDirectCallFunction && !this.itsForcedObjectParameters;
    }

    private void genSimpleCompare(int i, int i2, int i3) {
        if (i2 == -1) {
            throw Codegen.badTree();
        }
        switch (i) {
            case 14:
                this.cfw.add(152);
                this.cfw.add(155, i2);
                break;
            case 15:
                this.cfw.add(152);
                this.cfw.add(158, i2);
                break;
            case 16:
                this.cfw.add(151);
                this.cfw.add(157, i2);
                break;
            case 17:
                this.cfw.add(151);
                this.cfw.add(156, i2);
                break;
            default:
                throw Codegen.badTree();
        }
        if (i3 != -1) {
            this.cfw.add(167, i3);
        }
    }

    private void visitIfJumpRelOp(Node node, Node node2, int i, int i2) {
        if (i == -1 || i2 == -1) {
            throw Codegen.badTree();
        }
        int type = node.getType();
        Node next = node2.getNext();
        if (type == 53 || type == 52) {
            generateExpression(node2, node);
            generateExpression(next, node);
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke(type == 53 ? "instanceOf" : "in", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Z");
            this.cfw.add(154, i);
            this.cfw.add(167, i2);
            return;
        }
        int intProp = node.getIntProp(8, -1);
        int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
        int nodeIsDirectCallParameter2 = nodeIsDirectCallParameter(next);
        if (intProp != -1) {
            if (intProp != 2) {
                generateExpression(node2, node);
            } else if (nodeIsDirectCallParameter != -1) {
                dcpLoadAsNumber(nodeIsDirectCallParameter);
            } else {
                generateExpression(node2, node);
                addObjectToDouble();
            }
            if (intProp != 1) {
                generateExpression(next, node);
            } else if (nodeIsDirectCallParameter2 != -1) {
                dcpLoadAsNumber(nodeIsDirectCallParameter2);
            } else {
                generateExpression(next, node);
                addObjectToDouble();
            }
            genSimpleCompare(type, i, i2);
            return;
        }
        if (nodeIsDirectCallParameter == -1 || nodeIsDirectCallParameter2 == -1) {
            generateExpression(node2, node);
            generateExpression(next, node);
        } else {
            short stackTop = this.cfw.getStackTop();
            intProp = this.cfw.acquireLabel();
            this.cfw.addALoad(nodeIsDirectCallParameter);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            this.cfw.add(166, intProp);
            this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
            dcpLoadAsNumber(nodeIsDirectCallParameter2);
            genSimpleCompare(type, i, i2);
            if (stackTop != this.cfw.getStackTop()) {
                throw Codegen.badTree();
            }
            this.cfw.markLabel(intProp);
            intProp = this.cfw.acquireLabel();
            this.cfw.addALoad(nodeIsDirectCallParameter2);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            this.cfw.add(166, intProp);
            this.cfw.addALoad(nodeIsDirectCallParameter);
            addObjectToDouble();
            this.cfw.addDLoad(nodeIsDirectCallParameter2 + 1);
            genSimpleCompare(type, i, i2);
            if (stackTop != this.cfw.getStackTop()) {
                throw Codegen.badTree();
            }
            this.cfw.markLabel(intProp);
            this.cfw.addALoad(nodeIsDirectCallParameter);
            this.cfw.addALoad(nodeIsDirectCallParameter2);
        }
        if (type == 17 || type == 16) {
            this.cfw.add(95);
        }
        String str = (type == 14 || type == 16) ? "cmp_LT" : "cmp_LE";
        addScriptRuntimeInvoke(str, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
        this.cfw.add(154, i);
        this.cfw.add(167, i2);
    }

    private void visitIfJumpEqOp(Node node, Node node2, int i, int i2) {
        if (i == -1 || i2 == -1) {
            throw Codegen.badTree();
        }
        short stackTop = this.cfw.getStackTop();
        int type = node.getType();
        Node next = node2.getNext();
        int acquireLabel;
        if (node2.getType() == 42 || next.getType() == 42) {
            if (node2.getType() == 42) {
                node2 = next;
            }
            generateExpression(node2, node);
            if (type == 46 || type == 47) {
                this.cfw.add(type == 46 ? 198 : 199, i);
                i = i2;
            } else {
                if (type == 12) {
                    int i3 = i2;
                    i2 = i;
                    i = i3;
                } else if (type != 13) {
                    throw Codegen.badTree();
                }
                this.cfw.add(89);
                acquireLabel = this.cfw.acquireLabel();
                this.cfw.add(199, acquireLabel);
                short stackTop2 = this.cfw.getStackTop();
                this.cfw.add(87);
                this.cfw.add(167, i2);
                this.cfw.markLabel(acquireLabel, stackTop2);
                Codegen.pushUndefined(this.cfw);
                this.cfw.add(165, i2);
            }
            this.cfw.add(167, i);
        } else {
            String str;
            int nodeIsDirectCallParameter = nodeIsDirectCallParameter(node2);
            if (nodeIsDirectCallParameter != -1 && next.getType() == 149) {
                Node firstChild = next.getFirstChild();
                if (firstChild.getType() == 40) {
                    this.cfw.addALoad(nodeIsDirectCallParameter);
                    this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
                    int acquireLabel2 = this.cfw.acquireLabel();
                    this.cfw.add(166, acquireLabel2);
                    this.cfw.addDLoad(nodeIsDirectCallParameter + 1);
                    this.cfw.addPush(firstChild.getDouble());
                    this.cfw.add(151);
                    if (type == 12) {
                        this.cfw.add(153, i);
                    } else {
                        this.cfw.add(154, i);
                    }
                    this.cfw.add(167, i2);
                    this.cfw.markLabel(acquireLabel2);
                }
            }
            generateExpression(node2, node);
            generateExpression(next, node);
            switch (type) {
                case 12:
                    str = "eq";
                    acquireLabel = 154;
                    break;
                case 13:
                    str = "eq";
                    acquireLabel = 153;
                    break;
                case 46:
                    str = "shallowEq";
                    acquireLabel = 154;
                    break;
                case 47:
                    str = "shallowEq";
                    acquireLabel = 153;
                    break;
                default:
                    throw Codegen.badTree();
            }
            addScriptRuntimeInvoke(str, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
            this.cfw.add(acquireLabel, i);
            this.cfw.add(167, i2);
        }
        if (stackTop != this.cfw.getStackTop()) {
            throw Codegen.badTree();
        }
    }

    private void visitSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("setName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitStrictSetName(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("strictSetName", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitSetConst(Node node, Node node2) {
        String string = node.getFirstChild().getString();
        while (node2 != null) {
            generateExpression(node2, node);
            node2 = node2.getNext();
        }
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addPush(string);
        addScriptRuntimeInvoke("setConst", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Ljava/lang/String;)Ljava/lang/Object;");
    }

    private void visitGetVar(Node node) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        short s = this.varRegisters[varIndex];
        if (varIsDirectCallParameter(varIndex)) {
            if (node.getIntProp(8, -1) != -1) {
                dcpLoadAsNumber(s);
            } else {
                dcpLoadAsObject(s);
            }
        } else if (this.fnCurrent.isNumberVar(varIndex)) {
            this.cfw.addDLoad(s);
        } else {
            this.cfw.addALoad(s);
        }
    }

    private void visitSetVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        Object obj = node.getIntProp(8, -1) != -1 ? 1 : null;
        short s = this.varRegisters[varIndex];
        if (this.fnCurrent.fnode.getParamAndVarConst()[varIndex]) {
            if (!z) {
                if (obj != null) {
                    this.cfw.add(88);
                } else {
                    this.cfw.add(87);
                }
            }
        } else if (!varIsDirectCallParameter(varIndex)) {
            boolean isNumberVar = this.fnCurrent.isNumberVar(varIndex);
            if (obj == null) {
                if (isNumberVar) {
                    Kit.codeBug();
                }
                this.cfw.addAStore(s);
                if (z) {
                    this.cfw.addALoad(s);
                }
            } else if (isNumberVar) {
                this.cfw.addDStore(s);
                if (z) {
                    this.cfw.addDLoad(s);
                }
            } else {
                if (z) {
                    this.cfw.add(92);
                }
                addDoubleWrap();
                this.cfw.addAStore(s);
            }
        } else if (obj != null) {
            if (z) {
                this.cfw.add(92);
            }
            this.cfw.addALoad(s);
            this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
            int acquireLabel = this.cfw.acquireLabel();
            varIndex = this.cfw.acquireLabel();
            this.cfw.add(165, acquireLabel);
            short stackTop = this.cfw.getStackTop();
            addDoubleWrap();
            this.cfw.addAStore(s);
            this.cfw.add(167, varIndex);
            this.cfw.markLabel(acquireLabel, stackTop);
            this.cfw.addDStore(s + 1);
            this.cfw.markLabel(varIndex);
        } else {
            if (z) {
                this.cfw.add(89);
            }
            this.cfw.addAStore(s);
        }
    }

    private void visitSetConstVar(Node node, Node node2, boolean z) {
        if (!this.hasVarsInRegs) {
            Kit.codeBug();
        }
        int varIndex = this.fnCurrent.getVarIndex(node);
        generateExpression(node2.getNext(), node);
        int i = node.getIntProp(8, -1) != -1 ? 1 : 0;
        short s = this.varRegisters[varIndex];
        int acquireLabel = this.cfw.acquireLabel();
        int acquireLabel2 = this.cfw.acquireLabel();
        short stackTop;
        if (i != 0) {
            this.cfw.addILoad(s + 2);
            this.cfw.add(154, acquireLabel2);
            stackTop = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(s + 2);
            this.cfw.addDStore(s);
            if (z) {
                this.cfw.addDLoad(s);
                this.cfw.markLabel(acquireLabel2, stackTop);
            } else {
                this.cfw.add(167, acquireLabel);
                this.cfw.markLabel(acquireLabel2, stackTop);
                this.cfw.add(88);
            }
        } else {
            this.cfw.addILoad(s + 1);
            this.cfw.add(154, acquireLabel2);
            stackTop = this.cfw.getStackTop();
            this.cfw.addPush(1);
            this.cfw.addIStore(s + 1);
            this.cfw.addAStore(s);
            if (z) {
                this.cfw.addALoad(s);
                this.cfw.markLabel(acquireLabel2, stackTop);
            } else {
                this.cfw.add(167, acquireLabel);
                this.cfw.markLabel(acquireLabel2, stackTop);
                this.cfw.add(87);
            }
        }
        this.cfw.markLabel(acquireLabel);
    }

    private void visitGetProp(Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        generateExpression(next, node);
        if (node.getType() == 34) {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getObjectPropNoWarn", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        } else if (node2.getType() == 43 && next.getType() == 41) {
            this.cfw.addALoad(this.contextLocal);
            addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
        } else {
            this.cfw.addALoad(this.contextLocal);
            this.cfw.addALoad(this.variableObjectLocal);
            addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void visitSetProp(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 139) {
            this.cfw.add(89);
        }
        generateExpression(next, node);
        Node next2 = next.getNext();
        if (i == 139) {
            this.cfw.add(90);
            if (node2.getType() == 43 && next.getType() == 41) {
                this.cfw.addALoad(this.contextLocal);
                addScriptRuntimeInvoke("getObjectProp", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;");
            } else {
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            }
        }
        generateExpression(next2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("setObjectProp", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
    }

    private void visitSetElem(int i, Node node, Node node2) {
        generateExpression(node2, node);
        Node next = node2.getNext();
        if (i == 140) {
            this.cfw.add(89);
        }
        generateExpression(next, node);
        Node next2 = next.getNext();
        Object obj = node.getIntProp(8, -1) != -1 ? 1 : null;
        if (i == 140) {
            if (obj != null) {
                this.cfw.add(93);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getObjectIndex", "(Ljava/lang/Object;DLorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            } else {
                this.cfw.add(90);
                this.cfw.addALoad(this.contextLocal);
                this.cfw.addALoad(this.variableObjectLocal);
                addScriptRuntimeInvoke("getObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
            }
        }
        generateExpression(next2, node);
        this.cfw.addALoad(this.contextLocal);
        this.cfw.addALoad(this.variableObjectLocal);
        if (obj != null) {
            addScriptRuntimeInvoke("setObjectIndex", "(Ljava/lang/Object;DLjava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        } else {
            addScriptRuntimeInvoke("setObjectElem", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        }
    }

    private void visitDotQuery(Node node, Node node2) {
        updateLineNumber(node);
        generateExpression(node2, node);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("enterDotQuery", "(Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
        this.cfw.add(1);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.markLabel(acquireLabel);
        this.cfw.add(87);
        generateExpression(node2.getNext(), node);
        addScriptRuntimeInvoke("toBoolean", "(Ljava/lang/Object;)Z");
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("updateDotQuery", "(ZLorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;");
        this.cfw.add(89);
        this.cfw.add(198, acquireLabel);
        this.cfw.addALoad(this.variableObjectLocal);
        addScriptRuntimeInvoke("leaveDotQuery", "(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        this.cfw.addAStore(this.variableObjectLocal);
    }

    private int getLocalBlockRegister(Node node) {
        return ((Node) node.getProp(3)).getExistingIntProp(2);
    }

    private void dcpLoadAsNumber(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, acquireLabel);
        short stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        addObjectToDouble();
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        this.cfw.markLabel(acquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        this.cfw.markLabel(acquireLabel2);
    }

    private void dcpLoadAsObject(int i) {
        this.cfw.addALoad(i);
        this.cfw.add(178, "java/lang/Void", "TYPE", "Ljava/lang/Class;");
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(165, acquireLabel);
        short stackTop = this.cfw.getStackTop();
        this.cfw.addALoad(i);
        int acquireLabel2 = this.cfw.acquireLabel();
        this.cfw.add(167, acquireLabel2);
        this.cfw.markLabel(acquireLabel, stackTop);
        this.cfw.addDLoad(i + 1);
        addDoubleWrap();
        this.cfw.markLabel(acquireLabel2);
    }

    private void addGoto(Node node, int i) {
        this.cfw.add(i, getTargetLabel(node));
    }

    private void addObjectToDouble() {
        addScriptRuntimeInvoke("toNumber", "(Ljava/lang/Object;)D");
    }

    private void addNewObjectArray(int i) {
        if (i != 0) {
            this.cfw.addPush(i);
            this.cfw.add(189, "java/lang/Object");
        } else if (this.itsZeroArgArray >= (short) 0) {
            this.cfw.addALoad(this.itsZeroArgArray);
        } else {
            this.cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "emptyArgs", "[Ljava/lang/Object;");
        }
    }

    private void addScriptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org.mozilla.javascript.ScriptRuntime", str, str2);
    }

    private void addOptRuntimeInvoke(String str, String str2) {
        this.cfw.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", str, str2);
    }

    private void addJumpedBooleanWrap(int i, int i2) {
        this.cfw.markLabel(i2);
        int acquireLabel = this.cfw.acquireLabel();
        this.cfw.add(178, "java/lang/Boolean", "FALSE", "Ljava/lang/Boolean;");
        this.cfw.add(167, acquireLabel);
        this.cfw.markLabel(i);
        this.cfw.add(178, "java/lang/Boolean", "TRUE", "Ljava/lang/Boolean;");
        this.cfw.markLabel(acquireLabel);
        this.cfw.adjustStackTop(-1);
    }

    private void addDoubleWrap() {
        addOptRuntimeInvoke("wrapDouble", "(D)Ljava/lang/Double;");
    }

    private short getNewWordPairLocal(boolean z) {
        return getNewWordIntern(z ? 3 : 2);
    }

    private short getNewWordLocal(boolean z) {
        return getNewWordIntern(z ? 2 : 1);
    }

    private short getNewWordLocal() {
        return getNewWordIntern(1);
    }

    private short getNewWordIntern(int i) {
        if ($assertionsDisabled || (i >= 1 && i <= 3)) {
            short s;
            int[] iArr = this.locals;
            if (i > 1) {
                short s2 = this.firstFreeLocal;
                loop0:
                while (s2 + i <= 1024) {
                    int i2 = 0;
                    while (i2 < i) {
                        if (iArr[s2 + i2] != 0) {
                            s2 += i2 + 1;
                        } else {
                            i2++;
                        }
                    }
                }
                s2 = (short) -1;
                s = s2;
            } else {
                s = this.firstFreeLocal;
            }
            if (s != (short) -1) {
                iArr[s] = 1;
                if (i > 1) {
                    iArr[s + 1] = 1;
                }
                if (i > 2) {
                    iArr[s + 2] = 1;
                }
                if (s != this.firstFreeLocal) {
                    return (short) s;
                }
                for (int i3 = s + i; i3 < 1024; i3++) {
                    if (iArr[i3] == 0) {
                        this.firstFreeLocal = (short) i3;
                        if (this.localsMax < this.firstFreeLocal) {
                            this.localsMax = this.firstFreeLocal;
                        }
                        return (short) s;
                    }
                }
            }
            throw Context.reportRuntimeError("Program too complex (out of locals)");
        }
        throw new AssertionError();
    }

    private void incReferenceWordLocal(short s) {
        int[] iArr = this.locals;
        iArr[s] = iArr[s] + 1;
    }

    private void decReferenceWordLocal(short s) {
        int[] iArr = this.locals;
        iArr[s] = iArr[s] - 1;
    }

    private void releaseWordLocal(short s) {
        if (s < this.firstFreeLocal) {
            this.firstFreeLocal = s;
        }
        this.locals[s] = 0;
    }
}
