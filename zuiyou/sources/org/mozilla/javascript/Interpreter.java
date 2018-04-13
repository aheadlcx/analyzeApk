package org.mozilla.javascript;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebugFrame;

public final class Interpreter extends Icode implements Evaluator {
    static final int EXCEPTION_HANDLER_SLOT = 2;
    static final int EXCEPTION_LOCAL_SLOT = 4;
    static final int EXCEPTION_SCOPE_SLOT = 5;
    static final int EXCEPTION_SLOT_SIZE = 6;
    static final int EXCEPTION_TRY_END_SLOT = 1;
    static final int EXCEPTION_TRY_START_SLOT = 0;
    static final int EXCEPTION_TYPE_SLOT = 3;
    InterpreterData itsData;

    private static class CallFrame implements Serializable, Cloneable {
        static final long serialVersionUID = -2843792508994958978L;
        DebugFrame debuggerFrame;
        int emptyStackTop;
        InterpretedFunction fnOrScript;
        int frameIndex;
        boolean frozen;
        InterpreterData idata;
        boolean isContinuationsTopFrame;
        int localShift;
        CallFrame parentFrame;
        int pc;
        int pcPrevBranch;
        int pcSourceLineStart;
        Object result;
        double resultDbl;
        double[] sDbl;
        int savedCallOp;
        int savedStackTop;
        Scriptable scope;
        Object[] stack;
        int[] stackAttributes;
        Scriptable thisObj;
        Object throwable;
        boolean useActivation;
        CallFrame varSource;

        private CallFrame() {
        }

        CallFrame cloneFrozen() {
            if (!this.frozen) {
                Kit.codeBug();
            }
            try {
                CallFrame callFrame = (CallFrame) clone();
                callFrame.stack = (Object[]) this.stack.clone();
                callFrame.stackAttributes = (int[]) this.stackAttributes.clone();
                callFrame.sDbl = (double[]) this.sDbl.clone();
                callFrame.frozen = false;
                return callFrame;
            } catch (CloneNotSupportedException e) {
                throw new IllegalStateException();
            }
        }
    }

    private static final class ContinuationJump implements Serializable {
        static final long serialVersionUID = 7687739156004308247L;
        CallFrame branchFrame;
        CallFrame capturedFrame;
        Object result;
        double resultDbl;

        ContinuationJump(NativeContinuation nativeContinuation, CallFrame callFrame) {
            this.capturedFrame = (CallFrame) nativeContinuation.getImplementation();
            if (this.capturedFrame == null || callFrame == null) {
                this.branchFrame = null;
                return;
            }
            CallFrame callFrame2 = this.capturedFrame;
            int i = callFrame2.frameIndex - callFrame.frameIndex;
            if (i != 0) {
                if (i < 0) {
                    i = -i;
                    callFrame2 = callFrame;
                    callFrame = this.capturedFrame;
                }
                do {
                    callFrame2 = callFrame2.parentFrame;
                    i--;
                } while (i != 0);
                if (callFrame2.frameIndex != callFrame.frameIndex) {
                    Kit.codeBug();
                }
            }
            while (callFrame2 != callFrame && callFrame2 != null) {
                callFrame2 = callFrame2.parentFrame;
                callFrame = callFrame.parentFrame;
            }
            this.branchFrame = callFrame2;
            if (this.branchFrame != null && !this.branchFrame.frozen) {
                Kit.codeBug();
            }
        }
    }

    static class GeneratorState {
        int operation;
        RuntimeException returnedException;
        Object value;

        GeneratorState(int i, Object obj) {
            this.operation = i;
            this.value = obj;
        }
    }

    private static CallFrame captureFrameForGenerator(CallFrame callFrame) {
        callFrame.frozen = true;
        CallFrame cloneFrozen = callFrame.cloneFrozen();
        callFrame.frozen = false;
        cloneFrozen.parentFrame = null;
        cloneFrozen.frameIndex = 0;
        return cloneFrozen;
    }

    public Object compile(CompilerEnvirons compilerEnvirons, ScriptNode scriptNode, String str, boolean z) {
        this.itsData = new CodeGenerator().compile(compilerEnvirons, scriptNode, str, z);
        return this.itsData;
    }

    public Script createScriptObject(Object obj, Object obj2) {
        if (obj != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createScript(this.itsData, obj2);
    }

    public void setEvalScriptFlag(Script script) {
        ((InterpretedFunction) script).idata.evalScriptFlag = true;
    }

    public Function createFunctionObject(Context context, Scriptable scriptable, Object obj, Object obj2) {
        if (obj != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createFunction(context, scriptable, this.itsData, obj2);
    }

    private static int getShort(byte[] bArr, int i) {
        return (bArr[i] << 8) | (bArr[i + 1] & 255);
    }

    private static int getIndex(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    private static int getInt(byte[] bArr, int i) {
        return (((bArr[i] << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8)) | (bArr[i + 3] & 255);
    }

    private static int getExceptionHandler(CallFrame callFrame, boolean z) {
        int i = 0;
        int[] iArr = callFrame.idata.itsExceptionTable;
        if (iArr == null) {
            return -1;
        }
        int i2 = callFrame.pc - 1;
        int i3 = 0;
        int i4 = -1;
        int i5 = 0;
        while (i != iArr.length) {
            int i6 = iArr[i + 0];
            int i7 = iArr[i + 1];
            if (i6 <= i2 && i2 < i7 && (!z || iArr[i + 3] == 1)) {
                if (i4 >= 0) {
                    if (i5 >= i7) {
                        if (i3 > i6) {
                            Kit.codeBug();
                        }
                        if (i5 == i7) {
                            Kit.codeBug();
                        }
                    }
                }
                i5 = i7;
                i3 = i6;
                i4 = i;
            }
            i += 6;
        }
        return i4;
    }

    static void dumpICode(InterpreterData interpreterData) {
    }

    private static int bytecodeSpan(int i) {
        switch (i) {
            case -63:
            case -62:
            case -54:
            case -46:
            case -39:
            case -27:
            case -26:
            case -23:
            case -6:
            case 5:
            case 6:
            case 7:
            case 50:
            case 72:
                return 3;
            case -61:
            case -49:
            case -48:
                return 2;
            case -47:
                return 5;
            case -45:
                return 2;
            case -40:
                return 5;
            case -38:
                return 2;
            case -28:
                return 5;
            case -21:
                return 5;
            case -11:
            case -10:
            case -9:
            case -8:
            case -7:
                return 2;
            case 57:
                return 2;
            default:
                if (Icode.validBytecode(i)) {
                    return 1;
                }
                throw Kit.codeBug();
        }
    }

    static int[] getLineNumbers(InterpreterData interpreterData) {
        UintMap uintMap = new UintMap();
        byte[] bArr = interpreterData.itsICode;
        int length = bArr.length;
        int i = 0;
        while (i != length) {
            byte b = bArr[i];
            int bytecodeSpan = bytecodeSpan(b);
            if (b == (byte) -26) {
                if (bytecodeSpan != 3) {
                    Kit.codeBug();
                }
                uintMap.put(getIndex(bArr, i + 1), 0);
            }
            i += bytecodeSpan;
        }
        return uintMap.getKeys();
    }

    public void captureStackInfo(RhinoException rhinoException) {
        int i = 0;
        Context currentContext = Context.getCurrentContext();
        if (currentContext == null || currentContext.lastInterpreterFrame == null) {
            rhinoException.interpreterStackInfo = null;
            rhinoException.interpreterLineData = null;
            return;
        }
        Object obj;
        int size;
        if (currentContext.previousInterpreterInvocations == null || currentContext.previousInterpreterInvocations.size() == 0) {
            obj = new CallFrame[1];
        } else {
            size = currentContext.previousInterpreterInvocations.size();
            if (currentContext.previousInterpreterInvocations.peek() == currentContext.lastInterpreterFrame) {
                size--;
            }
            Object obj2 = new CallFrame[(size + 1)];
            currentContext.previousInterpreterInvocations.toArray(obj2);
            obj = obj2;
        }
        obj[obj.length - 1] = (CallFrame) currentContext.lastInterpreterFrame;
        for (size = 0; size != obj.length; size++) {
            i += obj[size].frameIndex + 1;
        }
        int[] iArr = new int[i];
        size = obj.length;
        while (size != 0) {
            int i2 = size - 1;
            for (CallFrame callFrame = obj[i2]; callFrame != null; callFrame = callFrame.parentFrame) {
                i--;
                iArr[i] = callFrame.pcSourceLineStart;
            }
            size = i2;
        }
        if (i != 0) {
            Kit.codeBug();
        }
        rhinoException.interpreterStackInfo = obj;
        rhinoException.interpreterLineData = iArr;
    }

    public String getSourcePositionFromStack(Context context, int[] iArr) {
        CallFrame callFrame = (CallFrame) context.lastInterpreterFrame;
        InterpreterData interpreterData = callFrame.idata;
        if (callFrame.pcSourceLineStart >= 0) {
            iArr[0] = getIndex(interpreterData.itsICode, callFrame.pcSourceLineStart);
        } else {
            iArr[0] = 0;
        }
        return interpreterData.itsSourceFile;
    }

    public String getPatchedStack(RhinoException rhinoException, String str) {
        String str2 = "org.mozilla.javascript.Interpreter.interpretLoop";
        StringBuilder stringBuilder = new StringBuilder(str.length() + 1000);
        String systemProperty = SecurityUtilities.getSystemProperty("line.separator");
        CallFrame[] callFrameArr = (CallFrame[]) rhinoException.interpreterStackInfo;
        int[] iArr = rhinoException.interpreterLineData;
        int length = callFrameArr.length;
        int length2 = iArr.length;
        int i = length;
        length = 0;
        while (i != 0) {
            int i2 = i - 1;
            i = str.indexOf(str2, length);
            if (i < 0) {
                break;
            }
            i += str2.length();
            while (i != str.length()) {
                char charAt = str.charAt(i);
                if (charAt == '\n' || charAt == '\r') {
                    break;
                }
                i++;
            }
            stringBuilder.append(str.substring(length, i));
            for (CallFrame callFrame = callFrameArr[i2]; callFrame != null; callFrame = callFrame.parentFrame) {
                if (length2 == 0) {
                    Kit.codeBug();
                }
                length2--;
                InterpreterData interpreterData = callFrame.idata;
                stringBuilder.append(systemProperty);
                stringBuilder.append("\tat script");
                if (!(interpreterData.itsName == null || interpreterData.itsName.length() == 0)) {
                    stringBuilder.append('.');
                    stringBuilder.append(interpreterData.itsName);
                }
                stringBuilder.append('(');
                stringBuilder.append(interpreterData.itsSourceFile);
                int i3 = iArr[length2];
                if (i3 >= 0) {
                    stringBuilder.append(':');
                    stringBuilder.append(getIndex(interpreterData.itsICode, i3));
                }
                stringBuilder.append(')');
            }
            length = i;
            i = i2;
        }
        stringBuilder.append(str.substring(length));
        return stringBuilder.toString();
    }

    public List<String> getScriptStack(RhinoException rhinoException) {
        ScriptStackElement[][] scriptStackElements = getScriptStackElements(rhinoException);
        List<String> arrayList = new ArrayList(scriptStackElements.length);
        String systemProperty = SecurityUtilities.getSystemProperty("line.separator");
        for (ScriptStackElement[] scriptStackElementArr : scriptStackElements) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ScriptStackElement renderJavaStyle : scriptStackElementArr) {
                renderJavaStyle.renderJavaStyle(stringBuilder);
                stringBuilder.append(systemProperty);
            }
            arrayList.add(stringBuilder.toString());
        }
        return arrayList;
    }

    public ScriptStackElement[][] getScriptStackElements(RhinoException rhinoException) {
        if (rhinoException.interpreterStackInfo == null) {
            return (ScriptStackElement[][]) null;
        }
        List arrayList = new ArrayList();
        CallFrame[] callFrameArr = (CallFrame[]) rhinoException.interpreterStackInfo;
        int[] iArr = rhinoException.interpreterLineData;
        int length = callFrameArr.length;
        int length2 = iArr.length;
        while (length != 0) {
            int i = length - 1;
            CallFrame callFrame = callFrameArr[i];
            List arrayList2 = new ArrayList();
            CallFrame callFrame2 = callFrame;
            length = length2;
            while (callFrame2 != null) {
                String str;
                if (length == 0) {
                    Kit.codeBug();
                }
                int i2 = length - 1;
                InterpreterData interpreterData = callFrame2.idata;
                String str2 = interpreterData.itsSourceFile;
                length = -1;
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    length = getIndex(interpreterData.itsICode, i3);
                }
                if (interpreterData.itsName == null || interpreterData.itsName.length() == 0) {
                    str = null;
                } else {
                    str = interpreterData.itsName;
                }
                callFrame2 = callFrame2.parentFrame;
                arrayList2.add(new ScriptStackElement(str2, str, length));
                length = i2;
            }
            arrayList.add(arrayList2.toArray(new ScriptStackElement[arrayList2.size()]));
            length2 = length;
            length = i;
        }
        return (ScriptStackElement[][]) arrayList.toArray(new ScriptStackElement[arrayList.size()][]);
    }

    static String getEncodedSource(InterpreterData interpreterData) {
        if (interpreterData.encodedSource == null) {
            return null;
        }
        return interpreterData.encodedSource.substring(interpreterData.encodedSourceStart, interpreterData.encodedSourceEnd);
    }

    private static void initFunction(Context context, Scriptable scriptable, InterpretedFunction interpretedFunction, int i) {
        NativeFunction createFunction = InterpretedFunction.createFunction(context, scriptable, interpretedFunction, i);
        ScriptRuntime.initFunction(context, scriptable, createFunction, createFunction.idata.itsFunctionType, interpretedFunction.idata.evalScriptFlag);
    }

    static Object interpret(InterpretedFunction interpretedFunction, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!ScriptRuntime.hasTopCall(context)) {
            Kit.codeBug();
        }
        if (context.interpreterSecurityDomain != interpretedFunction.securityDomain) {
            Object obj = context.interpreterSecurityDomain;
            context.interpreterSecurityDomain = interpretedFunction.securityDomain;
            try {
                Object callWithDomain = interpretedFunction.securityController.callWithDomain(interpretedFunction.securityDomain, context, interpretedFunction, scriptable, scriptable2, objArr);
                return callWithDomain;
            } finally {
                context.interpreterSecurityDomain = obj;
            }
        } else {
            CallFrame callFrame = new CallFrame();
            initFrame(context, scriptable, scriptable2, objArr, null, 0, objArr.length, interpretedFunction, null, callFrame);
            callFrame.isContinuationsTopFrame = context.isContinuationsTopCall;
            context.isContinuationsTopCall = false;
            return interpretLoop(context, callFrame, null);
        }
    }

    public static Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        CallFrame callFrame = (CallFrame) obj;
        GeneratorState generatorState = new GeneratorState(i, obj2);
        if (i == 2) {
            try {
                return interpretLoop(context, callFrame, generatorState);
            } catch (RuntimeException e) {
                if (e == obj2) {
                    return Undefined.instance;
                }
                throw e;
            }
        }
        Object interpretLoop = interpretLoop(context, callFrame, generatorState);
        if (generatorState.returnedException == null) {
            return interpretLoop;
        }
        throw generatorState.returnedException;
    }

    public static Object restartContinuation(NativeContinuation nativeContinuation, Context context, Scriptable scriptable, Object[] objArr) {
        if (!ScriptRuntime.hasTopCall(context)) {
            return ScriptRuntime.doTopCall(nativeContinuation, context, scriptable, null, objArr);
        }
        Object obj;
        if (objArr.length == 0) {
            obj = Undefined.instance;
        } else {
            obj = objArr[0];
        }
        if (((CallFrame) nativeContinuation.getImplementation()) == null) {
            return obj;
        }
        ContinuationJump continuationJump = new ContinuationJump(nativeContinuation, null);
        continuationJump.result = obj;
        return interpretLoop(context, null, continuationJump);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object interpretLoop(org.mozilla.javascript.Context r40, org.mozilla.javascript.Interpreter.CallFrame r41, java.lang.Object r42) {
        /*
        r32 = org.mozilla.javascript.UniqueTag.DOUBLE_MARK;
        r33 = org.mozilla.javascript.Undefined.instance;
        r0 = r40;
        r4 = r0.instructionThreshold;
        if (r4 == 0) goto L_0x00dc;
    L_0x000a:
        r4 = 1;
        r26 = r4;
    L_0x000d:
        r31 = 0;
        r16 = -1;
        r0 = r40;
        r4 = r0.lastInterpreterFrame;
        if (r4 == 0) goto L_0x0031;
    L_0x0017:
        r0 = r40;
        r4 = r0.previousInterpreterInvocations;
        if (r4 != 0) goto L_0x0026;
    L_0x001d:
        r4 = new org.mozilla.javascript.ObjArray;
        r4.<init>();
        r0 = r40;
        r0.previousInterpreterInvocations = r4;
    L_0x0026:
        r0 = r40;
        r4 = r0.previousInterpreterInvocations;
        r0 = r40;
        r5 = r0.lastInterpreterFrame;
        r4.push(r5);
    L_0x0031:
        r4 = 0;
        if (r42 == 0) goto L_0x00ea;
    L_0x0034:
        r0 = r42;
        r5 = r0 instanceof org.mozilla.javascript.Interpreter.GeneratorState;
        if (r5 == 0) goto L_0x00e1;
    L_0x003a:
        r42 = (org.mozilla.javascript.Interpreter.GeneratorState) r42;
        r4 = org.mozilla.javascript.ScriptRuntime.emptyArgs;
        r5 = 1;
        r0 = r40;
        r1 = r41;
        enterFrame(r0, r1, r4, r5);
        r4 = 0;
    L_0x0047:
        r30 = 0;
        r28 = 0;
        r6 = r31;
        r27 = r4;
        r5 = r41;
    L_0x0051:
        if (r27 == 0) goto L_0x00f2;
    L_0x0053:
        r0 = r40;
        r1 = r27;
        r2 = r16;
        r3 = r26;
        r5 = processThrowable(r0, r1, r5, r2, r3);	 Catch:{ Throwable -> 0x00fd }
        r0 = r5.throwable;	 Catch:{ Throwable -> 0x0d50 }
        r27 = r0;
        r4 = 0;
        r5.throwable = r4;	 Catch:{ Throwable -> 0x0d50 }
    L_0x0066:
        r7 = r5.stack;	 Catch:{ Throwable -> 0x00fd }
        r8 = r5.sDbl;	 Catch:{ Throwable -> 0x00fd }
        r4 = r5.varSource;	 Catch:{ Throwable -> 0x00fd }
        r0 = r4.stack;	 Catch:{ Throwable -> 0x00fd }
        r34 = r0;
        r4 = r5.varSource;	 Catch:{ Throwable -> 0x00fd }
        r0 = r4.sDbl;	 Catch:{ Throwable -> 0x00fd }
        r35 = r0;
        r4 = r5.varSource;	 Catch:{ Throwable -> 0x00fd }
        r0 = r4.stackAttributes;	 Catch:{ Throwable -> 0x00fd }
        r36 = r0;
        r4 = r5.idata;	 Catch:{ Throwable -> 0x00fd }
        r15 = r4.itsICode;	 Catch:{ Throwable -> 0x00fd }
        r4 = r5.idata;	 Catch:{ Throwable -> 0x00fd }
        r0 = r4.itsStringTable;	 Catch:{ Throwable -> 0x00fd }
        r37 = r0;
        r9 = r5.savedStackTop;	 Catch:{ Throwable -> 0x00fd }
        r0 = r40;
        r0.lastInterpreterFrame = r5;	 Catch:{ Throwable -> 0x00fd }
        r31 = r6;
    L_0x008e:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4 + 1;
        r5.pc = r6;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r15[r4];	 Catch:{ Throwable -> 0x00c7 }
        switch(r6) {
            case -64: goto L_0x0b45;
            case -63: goto L_0x01c0;
            case -62: goto L_0x0105;
            case -61: goto L_0x083a;
            case -60: goto L_0x0099;
            case -59: goto L_0x0405;
            case -58: goto L_0x0a73;
            case -57: goto L_0x0a52;
            case -56: goto L_0x052b;
            case -55: goto L_0x05d5;
            case -54: goto L_0x0aed;
            case -53: goto L_0x0ad5;
            case -52: goto L_0x089f;
            case -51: goto L_0x0895;
            case -50: goto L_0x08d0;
            case -49: goto L_0x0851;
            case -48: goto L_0x0868;
            case -47: goto L_0x0beb;
            case -46: goto L_0x0bd9;
            case -45: goto L_0x0bc7;
            case -44: goto L_0x0bc2;
            case -43: goto L_0x0bbd;
            case -42: goto L_0x0bb8;
            case -41: goto L_0x0bb3;
            case -40: goto L_0x0ba5;
            case -39: goto L_0x0b97;
            case -38: goto L_0x0b87;
            case -37: goto L_0x0b83;
            case -36: goto L_0x0b7f;
            case -35: goto L_0x0b7b;
            case -34: goto L_0x0b77;
            case -33: goto L_0x0b73;
            case -32: goto L_0x0b6f;
            case -31: goto L_0x0a94;
            case -30: goto L_0x0a31;
            case -29: goto L_0x0a1b;
            case -28: goto L_0x07ed;
            case -27: goto L_0x07d8;
            case -26: goto L_0x0b52;
            case -25: goto L_0x02cf;
            case -24: goto L_0x02b0;
            case -23: goto L_0x02a4;
            case -22: goto L_0x0363;
            case -21: goto L_0x05bd;
            case -20: goto L_0x09fa;
            case -19: goto L_0x09e8;
            case -18: goto L_0x059f;
            case -17: goto L_0x056e;
            case -16: goto L_0x054c;
            case -15: goto L_0x0534;
            case -14: goto L_0x07c4;
            case -13: goto L_0x09de;
            case -12: goto L_0x09d2;
            case -11: goto L_0x0501;
            case -10: goto L_0x04bd;
            case -9: goto L_0x0489;
            case -8: goto L_0x0820;
            case -7: goto L_0x087d;
            case -6: goto L_0x026f;
            case -5: goto L_0x02fe;
            case -4: goto L_0x02f7;
            case -3: goto L_0x033d;
            case -2: goto L_0x031d;
            case -1: goto L_0x030d;
            case 0: goto L_0x0424;
            case 1: goto L_0x0099;
            case 2: goto L_0x08d6;
            case 3: goto L_0x08f0;
            case 4: goto L_0x0357;
            case 5: goto L_0x0285;
            case 6: goto L_0x025e;
            case 7: goto L_0x024d;
            case 8: goto L_0x03d5;
            case 9: goto L_0x0376;
            case 10: goto L_0x0376;
            case 11: goto L_0x0376;
            case 12: goto L_0x0221;
            case 13: goto L_0x0221;
            case 14: goto L_0x0213;
            case 15: goto L_0x0213;
            case 16: goto L_0x0213;
            case 17: goto L_0x0213;
            case 18: goto L_0x0376;
            case 19: goto L_0x0376;
            case 20: goto L_0x037c;
            case 21: goto L_0x03a5;
            case 22: goto L_0x03ae;
            case 23: goto L_0x03ae;
            case 24: goto L_0x03ae;
            case 25: goto L_0x03ae;
            case 26: goto L_0x03b4;
            case 27: goto L_0x0369;
            case 28: goto L_0x0396;
            case 29: goto L_0x0396;
            case 30: goto L_0x071c;
            case 31: goto L_0x0424;
            case 32: goto L_0x07b0;
            case 33: goto L_0x0446;
            case 34: goto L_0x042c;
            case 35: goto L_0x0460;
            case 36: goto L_0x04ad;
            case 37: goto L_0x04b5;
            case 38: goto L_0x05d5;
            case 39: goto L_0x0810;
            case 40: goto L_0x0802;
            case 41: goto L_0x07d2;
            case 42: goto L_0x08a9;
            case 43: goto L_0x08b0;
            case 44: goto L_0x08c0;
            case 45: goto L_0x08c8;
            case 46: goto L_0x0237;
            case 47: goto L_0x0237;
            case 48: goto L_0x0a07;
            case 49: goto L_0x03c5;
            case 50: goto L_0x01de;
            case 51: goto L_0x0204;
            case 52: goto L_0x0219;
            case 53: goto L_0x0219;
            case 54: goto L_0x051b;
            case 55: goto L_0x0d87;
            case 56: goto L_0x0d8b;
            case 57: goto L_0x08fa;
            case 58: goto L_0x0931;
            case 59: goto L_0x0931;
            case 60: goto L_0x0931;
            case 61: goto L_0x095d;
            case 62: goto L_0x095d;
            case 63: goto L_0x08b8;
            case 64: goto L_0x0121;
            case 65: goto L_0x0a94;
            case 66: goto L_0x0a94;
            case 67: goto L_0x04c6;
            case 68: goto L_0x04d4;
            case 69: goto L_0x04f3;
            case 70: goto L_0x05d5;
            case 71: goto L_0x0978;
            case 72: goto L_0x0145;
            case 73: goto L_0x03d5;
            case 74: goto L_0x0b0f;
            case 75: goto L_0x0b25;
            case 76: goto L_0x0b35;
            case 77: goto L_0x0992;
            case 78: goto L_0x099c;
            case 79: goto L_0x09a6;
            case 80: goto L_0x09c0;
            case 81: goto L_0x0099;
            case 82: goto L_0x0099;
            case 83: goto L_0x0099;
            case 84: goto L_0x0099;
            case 85: goto L_0x0099;
            case 86: goto L_0x0099;
            case 87: goto L_0x0099;
            case 88: goto L_0x0099;
            case 89: goto L_0x0099;
            case 90: goto L_0x0099;
            case 91: goto L_0x0099;
            case 92: goto L_0x0099;
            case 93: goto L_0x0099;
            case 94: goto L_0x0099;
            case 95: goto L_0x0099;
            case 96: goto L_0x0099;
            case 97: goto L_0x0099;
            case 98: goto L_0x0099;
            case 99: goto L_0x0099;
            case 100: goto L_0x0099;
            case 101: goto L_0x0099;
            case 102: goto L_0x0099;
            case 103: goto L_0x0099;
            case 104: goto L_0x0099;
            case 105: goto L_0x0099;
            case 106: goto L_0x0099;
            case 107: goto L_0x0099;
            case 108: goto L_0x0099;
            case 109: goto L_0x0099;
            case 110: goto L_0x0099;
            case 111: goto L_0x0099;
            case 112: goto L_0x0099;
            case 113: goto L_0x0099;
            case 114: goto L_0x0099;
            case 115: goto L_0x0099;
            case 116: goto L_0x0099;
            case 117: goto L_0x0099;
            case 118: goto L_0x0099;
            case 119: goto L_0x0099;
            case 120: goto L_0x0099;
            case 121: goto L_0x0099;
            case 122: goto L_0x0099;
            case 123: goto L_0x0099;
            case 124: goto L_0x0099;
            case 125: goto L_0x0099;
            case 126: goto L_0x0099;
            case 127: goto L_0x0099;
            case 128: goto L_0x0099;
            case 129: goto L_0x0099;
            case 130: goto L_0x0099;
            case 131: goto L_0x0099;
            case 132: goto L_0x0099;
            case 133: goto L_0x0099;
            case 134: goto L_0x0099;
            case 135: goto L_0x0099;
            case 136: goto L_0x0099;
            case 137: goto L_0x0099;
            case 138: goto L_0x0099;
            case 139: goto L_0x0099;
            case 140: goto L_0x0099;
            case 141: goto L_0x0099;
            case 142: goto L_0x0099;
            case 143: goto L_0x0099;
            case 144: goto L_0x0099;
            case 145: goto L_0x0099;
            case 146: goto L_0x0099;
            case 147: goto L_0x0099;
            case 148: goto L_0x0099;
            case 149: goto L_0x0099;
            case 150: goto L_0x0099;
            case 151: goto L_0x0099;
            case 152: goto L_0x0099;
            case 153: goto L_0x0099;
            case 154: goto L_0x0099;
            case 155: goto L_0x0099;
            case 156: goto L_0x0d8f;
            default: goto L_0x0099;
        };	 Catch:{ Throwable -> 0x00c7 }
    L_0x0099:
        r4 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        dumpICode(r4);	 Catch:{ Throwable -> 0x00c7 }
        r4 = new java.lang.RuntimeException;	 Catch:{ Throwable -> 0x00c7 }
        r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00c7 }
        r7.<init>();	 Catch:{ Throwable -> 0x00c7 }
        r8 = "Unknown icode : ";
        r7 = r7.append(r8);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r7.append(r6);	 Catch:{ Throwable -> 0x00c7 }
        r7 = " @ pc : ";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00c7 }
        r7 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r7 = r7 + -1;
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x00c7 }
        r4.<init>(r6);	 Catch:{ Throwable -> 0x00c7 }
        throw r4;	 Catch:{ Throwable -> 0x00c7 }
    L_0x00c7:
        r4 = move-exception;
        r6 = r28;
        r8 = r31;
        r9 = r5;
        r5 = r30;
    L_0x00cf:
        if (r27 == 0) goto L_0x0c33;
    L_0x00d1:
        r5 = java.lang.System.err;
        r4.printStackTrace(r5);
        r4 = new java.lang.IllegalStateException;
        r4.<init>();
        throw r4;
    L_0x00dc:
        r4 = 0;
        r26 = r4;
        goto L_0x000d;
    L_0x00e1:
        r0 = r42;
        r5 = r0 instanceof org.mozilla.javascript.Interpreter.ContinuationJump;
        if (r5 != 0) goto L_0x00ea;
    L_0x00e7:
        org.mozilla.javascript.Kit.codeBug();
    L_0x00ea:
        r38 = r4;
        r4 = r42;
        r42 = r38;
        goto L_0x0047;
    L_0x00f2:
        if (r42 != 0) goto L_0x0066;
    L_0x00f4:
        r4 = r5.frozen;	 Catch:{ Throwable -> 0x00fd }
        if (r4 == 0) goto L_0x0066;
    L_0x00f8:
        org.mozilla.javascript.Kit.codeBug();	 Catch:{ Throwable -> 0x00fd }
        goto L_0x0066;
    L_0x00fd:
        r4 = move-exception;
        r8 = r6;
        r9 = r5;
        r6 = r28;
        r5 = r30;
        goto L_0x00cf;
    L_0x0105:
        r4 = r5.frozen;	 Catch:{ Throwable -> 0x00c7 }
        if (r4 != 0) goto L_0x0145;
    L_0x0109:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + -1;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = captureFrameForGenerator(r5);	 Catch:{ Throwable -> 0x00c7 }
        r6 = 1;
        r4.frozen = r6;	 Catch:{ Throwable -> 0x00c7 }
        r6 = new org.mozilla.javascript.NativeGenerator;	 Catch:{ Throwable -> 0x00c7 }
        r7 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r8 = r4.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r6.<init>(r7, r8, r4);	 Catch:{ Throwable -> 0x00c7 }
        r5.result = r6;	 Catch:{ Throwable -> 0x00c7 }
    L_0x0121:
        r4 = 0;
        r0 = r40;
        exitFrame(r0, r5, r4);	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.result;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.resultDbl;	 Catch:{ Throwable -> 0x0d59 }
        r8 = r5.parentFrame;	 Catch:{ Throwable -> 0x0d3d }
        if (r8 == 0) goto L_0x0c0b;
    L_0x012f:
        r8 = r5.parentFrame;	 Catch:{ Throwable -> 0x0d3d }
        r5 = r8.frozen;	 Catch:{ Throwable -> 0x0d45 }
        if (r5 == 0) goto L_0x0139;
    L_0x0135:
        r8 = r8.cloneFrozen();	 Catch:{ Throwable -> 0x0d45 }
    L_0x0139:
        setCallResult(r8, r4, r6);	 Catch:{ Throwable -> 0x0d45 }
        r30 = 0;
        r28 = r6;
        r5 = r8;
        r6 = r31;
        goto L_0x0051;
    L_0x0145:
        r4 = r5.frozen;	 Catch:{ Throwable -> 0x00c7 }
        if (r4 != 0) goto L_0x0152;
    L_0x0149:
        r0 = r40;
        r1 = r42;
        r5 = freezeGenerator(r0, r5, r9, r1);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0151:
        return r5;
    L_0x0152:
        r0 = r42;
        r4 = thawGenerator(r5, r9, r0, r6);	 Catch:{ Throwable -> 0x00c7 }
        r6 = org.mozilla.javascript.Scriptable.NOT_FOUND;	 Catch:{ Throwable -> 0x00c7 }
        if (r4 == r6) goto L_0x008e;
    L_0x015c:
        r8 = r28;
        r10 = r30;
        r19 = r5;
        r5 = r4;
    L_0x0163:
        if (r5 != 0) goto L_0x0168;
    L_0x0165:
        org.mozilla.javascript.Kit.codeBug();
    L_0x0168:
        r6 = 0;
        if (r42 == 0) goto L_0x0c3c;
    L_0x016b:
        r0 = r42;
        r4 = r0.operation;
        r7 = 2;
        if (r4 != r7) goto L_0x0c3c;
    L_0x0172:
        r0 = r42;
        r4 = r0.value;
        if (r5 != r4) goto L_0x0c3c;
    L_0x0178:
        r4 = 1;
        r38 = r6;
        r6 = r4;
        r4 = r38;
    L_0x017e:
        if (r26 == 0) goto L_0x0d9a;
    L_0x0180:
        r7 = 100;
        r0 = r40;
        r1 = r19;
        addInstructionCount(r0, r1, r7);	 Catch:{ RuntimeException -> 0x0cba, Error -> 0x0cc1 }
        r7 = r6;
        r6 = r4;
    L_0x018b:
        r0 = r19;
        r4 = r0.debuggerFrame;
        if (r4 == 0) goto L_0x0d93;
    L_0x0191:
        r4 = r5 instanceof java.lang.RuntimeException;
        if (r4 == 0) goto L_0x0d93;
    L_0x0195:
        r4 = r5;
        r4 = (java.lang.RuntimeException) r4;
        r0 = r19;
        r11 = r0.debuggerFrame;	 Catch:{ Throwable -> 0x0cc9 }
        r0 = r40;
        r11.onExceptionThrown(r0, r4);	 Catch:{ Throwable -> 0x0cc9 }
        r38 = r6;
        r6 = r7;
        r7 = r38;
    L_0x01a6:
        if (r6 == 0) goto L_0x0cd7;
    L_0x01a8:
        r4 = 2;
        if (r6 == r4) goto L_0x0cd4;
    L_0x01ab:
        r4 = 1;
    L_0x01ac:
        r0 = r19;
        r16 = getExceptionHandler(r0, r4);
        if (r16 < 0) goto L_0x0cd7;
    L_0x01b4:
        r28 = r8;
        r30 = r10;
        r6 = r31;
        r27 = r5;
        r5 = r19;
        goto L_0x0051;
    L_0x01c0:
        r4 = 1;
        r5.frozen = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = getIndex(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r6 = new org.mozilla.javascript.JavaScriptException;	 Catch:{ Throwable -> 0x00c7 }
        r7 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r7 = org.mozilla.javascript.NativeIterator.getStopIterationObject(r7);	 Catch:{ Throwable -> 0x00c7 }
        r8 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r8 = r8.itsSourceFile;	 Catch:{ Throwable -> 0x00c7 }
        r6.<init>(r7, r8, r4);	 Catch:{ Throwable -> 0x00c7 }
        r0 = r42;
        r0.returnedException = r6;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x0121;
    L_0x01de:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x01ea;
    L_0x01e4:
        r6 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r6);	 Catch:{ Throwable -> 0x00c7 }
    L_0x01ea:
        r6 = r9 + -1;
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r7 = getIndex(r15, r6);	 Catch:{ Throwable -> 0x00c7 }
        r6 = new org.mozilla.javascript.JavaScriptException;	 Catch:{ Throwable -> 0x00c7 }
        r8 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r8 = r8.itsSourceFile;	 Catch:{ Throwable -> 0x00c7 }
        r6.<init>(r4, r8, r7);	 Catch:{ Throwable -> 0x00c7 }
        r8 = r28;
        r10 = r30;
        r19 = r5;
        r5 = r6;
        goto L_0x0163;
    L_0x0204:
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + r16;
        r4 = r7[r4];	 Catch:{ Throwable -> 0x00c7 }
        r8 = r28;
        r10 = r30;
        r19 = r5;
        r5 = r4;
        goto L_0x0163;
    L_0x0213:
        r9 = doCompare(r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0219:
        r0 = r40;
        r9 = doInOrInstanceof(r0, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0221:
        r9 = r9 + -1;
        r10 = doEquals(r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        r4 = 13;
        if (r6 != r4) goto L_0x0235;
    L_0x022b:
        r4 = 1;
    L_0x022c:
        r4 = r4 ^ r10;
        r4 = org.mozilla.javascript.ScriptRuntime.wrapBoolean(r4);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0235:
        r4 = 0;
        goto L_0x022c;
    L_0x0237:
        r9 = r9 + -1;
        r10 = doShallowEquals(r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        r4 = 47;
        if (r6 != r4) goto L_0x024b;
    L_0x0241:
        r4 = 1;
    L_0x0242:
        r4 = r4 ^ r10;
        r4 = org.mozilla.javascript.ScriptRuntime.wrapBoolean(r4);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x024b:
        r4 = 0;
        goto L_0x0242;
    L_0x024d:
        r4 = r9 + -1;
        r6 = stack_boolean(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        if (r6 == 0) goto L_0x0d84;
    L_0x0255:
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6 + 2;
        r5.pc = r6;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r4;
        goto L_0x008e;
    L_0x025e:
        r4 = r9 + -1;
        r6 = stack_boolean(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        if (r6 != 0) goto L_0x0d84;
    L_0x0266:
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6 + 2;
        r5.pc = r6;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r4;
        goto L_0x008e;
    L_0x026f:
        r4 = r9 + -1;
        r6 = stack_boolean(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        if (r6 != 0) goto L_0x0280;
    L_0x0277:
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6 + 2;
        r5.pc = r6;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r4;
        goto L_0x008e;
    L_0x0280:
        r9 = r4 + -1;
        r6 = 0;
        r7[r4] = r6;	 Catch:{ Throwable -> 0x00c7 }
    L_0x0285:
        if (r26 == 0) goto L_0x028d;
    L_0x0287:
        r4 = 2;
        r0 = r40;
        addInstructionCount(r0, r5, r4);	 Catch:{ Throwable -> 0x00c7 }
    L_0x028d:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = getShort(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        if (r4 == 0) goto L_0x0bfd;
    L_0x0295:
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + -1;
        r4 = r4 + r6;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
    L_0x029c:
        if (r26 == 0) goto L_0x008e;
    L_0x029e:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r5.pcPrevBranch = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x02a4:
        r9 = r9 + 1;
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 2;
        r10 = (double) r4;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x0285;
    L_0x02b0:
        r4 = r5.emptyStackTop;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 1;
        if (r9 != r4) goto L_0x02c6;
    L_0x02b6:
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r7[r16] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r8[r16] = r10;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + -1;
        goto L_0x008e;
    L_0x02c6:
        r4 = r5.emptyStackTop;	 Catch:{ Throwable -> 0x00c7 }
        if (r9 == r4) goto L_0x008e;
    L_0x02ca:
        org.mozilla.javascript.Kit.codeBug();	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x02cf:
        if (r26 == 0) goto L_0x02d7;
    L_0x02d1:
        r4 = 0;
        r0 = r40;
        addInstructionCount(r0, r5, r4);	 Catch:{ Throwable -> 0x00c7 }
    L_0x02d7:
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = r7[r16];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 == r0) goto L_0x02ea;
    L_0x02e1:
        r8 = r28;
        r10 = r30;
        r19 = r5;
        r5 = r4;
        goto L_0x0163;
    L_0x02ea:
        r10 = r8[r16];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (int) r10;	 Catch:{ Throwable -> 0x00c7 }
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        if (r26 == 0) goto L_0x008e;
    L_0x02f1:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r5.pcPrevBranch = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x02f7:
        r4 = 0;
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + -1;
        goto L_0x008e;
    L_0x02fe:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r5.result = r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r5.resultDbl = r10;	 Catch:{ Throwable -> 0x00c7 }
        r4 = 0;
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + -1;
        goto L_0x008e;
    L_0x030d:
        r4 = r9 + 1;
        r6 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r7[r4] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + 1;
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r8[r4] = r10;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + 1;
        goto L_0x008e;
    L_0x031d:
        r4 = r9 + 1;
        r6 = r9 + -1;
        r6 = r7[r6];	 Catch:{ Throwable -> 0x00c7 }
        r7[r4] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + 1;
        r6 = r9 + -1;
        r10 = r8[r6];	 Catch:{ Throwable -> 0x00c7 }
        r8[r4] = r10;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + 2;
        r6 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r7[r4] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + 2;
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r8[r4] = r10;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + 2;
        goto L_0x008e;
    L_0x033d:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r6 = r9 + -1;
        r6 = r7[r6];	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r9 + -1;
        r7[r6] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + -1;
        r12 = r8[r4];	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r12;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + -1;
        r8[r4] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0357:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r5.result = r4;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r5.resultDbl = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + -1;
        goto L_0x0121;
    L_0x0363:
        r0 = r33;
        r5.result = r0;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x0121;
    L_0x0369:
        r4 = stack_int32(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 ^ -1;
        r10 = (double) r4;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0376:
        r9 = doBitOp(r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x037c:
        r4 = r9 + -1;
        r10 = stack_double(r5, r4);	 Catch:{ Throwable -> 0x00c7 }
        r4 = stack_int32(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 & 31;
        r9 = r9 + -1;
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r10 = org.mozilla.javascript.ScriptRuntime.toUint32(r10);	 Catch:{ Throwable -> 0x00c7 }
        r10 = r10 >>> r4;
        r10 = (double) r10;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0396:
        r10 = stack_double(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r4 = 29;
        if (r6 != r4) goto L_0x03a1;
    L_0x03a0:
        r10 = -r10;
    L_0x03a1:
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x03a5:
        r9 = r9 + -1;
        r0 = r40;
        doAdd(r7, r8, r9, r0);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x03ae:
        r9 = doArithmetic(r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x03b4:
        r4 = stack_boolean(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        if (r4 != 0) goto L_0x03c3;
    L_0x03ba:
        r4 = 1;
    L_0x03bb:
        r4 = org.mozilla.javascript.ScriptRuntime.wrapBoolean(r4);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x03c3:
        r4 = 0;
        goto L_0x03bb;
    L_0x03c5:
        r9 = r9 + 1;
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r31;
        r4 = org.mozilla.javascript.ScriptRuntime.bind(r0, r4, r1);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x03d5:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0d81;
    L_0x03db:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
        r10 = r4;
    L_0x03e2:
        r9 = r9 + -1;
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Scriptable) r4;	 Catch:{ Throwable -> 0x00c7 }
        r11 = 8;
        if (r6 != r11) goto L_0x03fa;
    L_0x03ec:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r31;
        r4 = org.mozilla.javascript.ScriptRuntime.setName(r4, r10, r0, r6, r1);	 Catch:{ Throwable -> 0x00c7 }
    L_0x03f6:
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x03fa:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r31;
        r4 = org.mozilla.javascript.ScriptRuntime.strictSetName(r4, r10, r0, r6, r1);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x03f6;
    L_0x0405:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0d7e;
    L_0x040b:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4;
    L_0x0412:
        r9 = r9 + -1;
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Scriptable) r4;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r31;
        r4 = org.mozilla.javascript.ScriptRuntime.setConst(r4, r6, r0, r1);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0424:
        r4 = r40;
        r9 = doDelName(r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x042c:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0438;
    L_0x0432:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0438:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.getObjectPropNoWarn(r4, r0, r1, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0446:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0452;
    L_0x044c:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0452:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.getObjectProp(r4, r0, r1, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0460:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0d7b;
    L_0x0466:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4;
    L_0x046d:
        r9 = r9 + -1;
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x047b;
    L_0x0475:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x047b:
        r10 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.setObjectProp(r4, r0, r6, r1, r10);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0489:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0495;
    L_0x048f:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0495:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r15[r10];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.propIncrDecr(r4, r0, r1, r6, r10);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 1;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x04ad:
        r0 = r40;
        r9 = doGetElem(r0, r5, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x04b5:
        r0 = r40;
        r9 = doSetElem(r0, r5, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x04bd:
        r4 = r40;
        r6 = r15;
        r9 = doElemIncDec(r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x04c6:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Ref) r4;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.refGet(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x04d4:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0d78;
    L_0x04da:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4;
    L_0x04e1:
        r9 = r9 + -1;
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Ref) r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.refSet(r4, r6, r0, r10);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x04f3:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Ref) r4;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.refDel(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0501:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Ref) r4;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r15[r10];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.refIncrDecr(r4, r0, r6, r10);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 1;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x051b:
        r9 = r9 + 1;
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = r7[r16];	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r8[r16];	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x052b:
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = 0;
        r7[r16] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0534:
        r4 = r9 + 1;
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r6 = org.mozilla.javascript.ScriptRuntime.getNameFunctionAndThis(r0, r1, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r4] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r4 + 1;
        r4 = org.mozilla.javascript.ScriptRuntime.lastStoredScriptable(r40);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x054c:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0558;
    L_0x0552:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0558:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.getPropFunctionAndThis(r4, r0, r1, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + 1;
        r4 = org.mozilla.javascript.ScriptRuntime.lastStoredScriptable(r40);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x056e:
        r4 = r9 + -1;
        r4 = r7[r4];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0d75;
    L_0x0576:
        r4 = r9 + -1;
        r10 = r8[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4;
    L_0x057f:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x058b;
    L_0x0585:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x058b:
        r10 = r9 + -1;
        r11 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.getElemFunctionAndThis(r6, r4, r0, r11);	 Catch:{ Throwable -> 0x00c7 }
        r7[r10] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.lastStoredScriptable(r40);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x059f:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x05ab;
    L_0x05a5:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x05ab:
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.getValueFunctionAndThis(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + 1;
        r4 = org.mozilla.javascript.ScriptRuntime.lastStoredScriptable(r40);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x05bd:
        if (r26 == 0) goto L_0x05c9;
    L_0x05bf:
        r0 = r40;
        r4 = r0.instructionCount;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 100;
        r0 = r40;
        r0.instructionCount = r4;	 Catch:{ Throwable -> 0x00c7 }
    L_0x05c9:
        r10 = r40;
        r11 = r5;
        r12 = r7;
        r13 = r8;
        r14 = r9;
        r9 = doCallSpecial(r10, r11, r12, r13, r14, r15, r16);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x05d5:
        if (r26 == 0) goto L_0x05e1;
    L_0x05d7:
        r0 = r40;
        r4 = r0.instructionCount;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 100;
        r0 = r40;
        r0.instructionCount = r4;	 Catch:{ Throwable -> 0x00c7 }
    L_0x05e1:
        r4 = r16 + 1;
        r9 = r9 - r4;
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Callable) r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r9 + 1;
        r12 = r7[r10];	 Catch:{ Throwable -> 0x00c7 }
        r12 = (org.mozilla.javascript.Scriptable) r12;	 Catch:{ Throwable -> 0x00c7 }
        r10 = 70;
        if (r6 != r10) goto L_0x0604;
    L_0x05f2:
        r6 = r9 + 2;
        r0 = r16;
        r6 = getArgsArray(r7, r8, r6, r0);	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.callRef(r4, r12, r6, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0604:
        r11 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r5.useActivation;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x0610;
    L_0x060a:
        r10 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r11 = org.mozilla.javascript.ScriptableObject.getTopLevelScope(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0610:
        r10 = r4 instanceof org.mozilla.javascript.InterpretedFunction;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x0650;
    L_0x0614:
        r0 = r4;
        r0 = (org.mozilla.javascript.InterpretedFunction) r0;	 Catch:{ Throwable -> 0x00c7 }
        r17 = r0;
        r10 = r5.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r10.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r17;
        r13 = r0.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 != r13) goto L_0x0650;
    L_0x0623:
        r19 = new org.mozilla.javascript.Interpreter$CallFrame;	 Catch:{ Throwable -> 0x00c7 }
        r4 = 0;
        r0 = r19;
        r0.<init>();	 Catch:{ Throwable -> 0x00c7 }
        r4 = -55;
        if (r6 != r4) goto L_0x0d71;
    L_0x062f:
        r0 = r5.parentFrame;	 Catch:{ Throwable -> 0x00c7 }
        r18 = r0;
        r4 = 0;
        r0 = r40;
        exitFrame(r0, r5, r4);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0639:
        r15 = r9 + 2;
        r10 = r40;
        r13 = r7;
        r14 = r8;
        initFrame(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);	 Catch:{ Throwable -> 0x00c7 }
        r4 = -55;
        if (r6 == r4) goto L_0x064a;
    L_0x0646:
        r5.savedStackTop = r9;	 Catch:{ Throwable -> 0x00c7 }
        r5.savedCallOp = r6;	 Catch:{ Throwable -> 0x00c7 }
    L_0x064a:
        r6 = r31;
        r5 = r19;
        goto L_0x0051;
    L_0x0650:
        r10 = r4 instanceof org.mozilla.javascript.NativeContinuation;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x0677;
    L_0x0654:
        r6 = new org.mozilla.javascript.Interpreter$ContinuationJump;	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.NativeContinuation) r4;	 Catch:{ Throwable -> 0x00c7 }
        r6.<init>(r4, r5);	 Catch:{ Throwable -> 0x00c7 }
        if (r16 != 0) goto L_0x066a;
    L_0x065d:
        r0 = r33;
        r6.result = r0;	 Catch:{ Throwable -> 0x00c7 }
    L_0x0661:
        r8 = r28;
        r10 = r30;
        r19 = r5;
        r5 = r6;
        goto L_0x0163;
    L_0x066a:
        r4 = r9 + 2;
        r4 = r7[r4];	 Catch:{ Throwable -> 0x00c7 }
        r6.result = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + 2;
        r8 = r8[r4];	 Catch:{ Throwable -> 0x00c7 }
        r6.resultDbl = r8;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x0661;
    L_0x0677:
        r10 = r4 instanceof org.mozilla.javascript.IdFunctionObject;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x06c8;
    L_0x067b:
        r0 = r4;
        r0 = (org.mozilla.javascript.IdFunctionObject) r0;	 Catch:{ Throwable -> 0x00c7 }
        r22 = r0;
        r10 = org.mozilla.javascript.NativeContinuation.isContinuationConstructor(r22);	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x0695;
    L_0x0686:
        r4 = r5.stack;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.parentFrame;	 Catch:{ Throwable -> 0x00c7 }
        r10 = 0;
        r0 = r40;
        r6 = captureContinuation(r0, r6, r10);	 Catch:{ Throwable -> 0x00c7 }
        r4[r9] = r6;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0695:
        r10 = org.mozilla.javascript.BaseFunction.isApplyOrCall(r22);	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x06c8;
    L_0x069b:
        r23 = org.mozilla.javascript.ScriptRuntime.getCallable(r12);	 Catch:{ Throwable -> 0x00c7 }
        r0 = r23;
        r10 = r0 instanceof org.mozilla.javascript.InterpretedFunction;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x06c8;
    L_0x06a5:
        r23 = (org.mozilla.javascript.InterpretedFunction) r23;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r5.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r10.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r23;
        r13 = r0.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 != r13) goto L_0x06c8;
    L_0x06b1:
        r14 = r40;
        r15 = r5;
        r17 = r7;
        r18 = r8;
        r19 = r9;
        r20 = r6;
        r21 = r11;
        r19 = initFrameForApplyOrCall(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r31;
        r5 = r19;
        goto L_0x0051;
    L_0x06c8:
        r10 = r4 instanceof org.mozilla.javascript.ScriptRuntime$NoSuchMethodShim;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x0702;
    L_0x06cc:
        r0 = r4;
        r0 = (org.mozilla.javascript.ScriptRuntime$NoSuchMethodShim) r0;	 Catch:{ Throwable -> 0x00c7 }
        r23 = r0;
        r0 = r23;
        r0 = r0.noSuchMethodMethod;	 Catch:{ Throwable -> 0x00c7 }
        r24 = r0;
        r0 = r24;
        r10 = r0 instanceof org.mozilla.javascript.InterpretedFunction;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x0702;
    L_0x06dd:
        r24 = (org.mozilla.javascript.InterpretedFunction) r24;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r5.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r10.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r24;
        r13 = r0.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 != r13) goto L_0x0702;
    L_0x06e9:
        r14 = r40;
        r15 = r5;
        r17 = r7;
        r18 = r8;
        r19 = r9;
        r20 = r6;
        r21 = r12;
        r22 = r11;
        r19 = initFrameForNoSuchMethod(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r31;
        r5 = r19;
        goto L_0x0051;
    L_0x0702:
        r0 = r40;
        r0.lastInterpreterFrame = r5;	 Catch:{ Throwable -> 0x00c7 }
        r5.savedCallOp = r6;	 Catch:{ Throwable -> 0x00c7 }
        r5.savedStackTop = r9;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r9 + 2;
        r0 = r16;
        r6 = getArgsArray(r7, r8, r6, r0);	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = r4.call(r0, r11, r12, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x071c:
        if (r26 == 0) goto L_0x0728;
    L_0x071e:
        r0 = r40;
        r4 = r0.instructionCount;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 100;
        r0 = r40;
        r0.instructionCount = r4;	 Catch:{ Throwable -> 0x00c7 }
    L_0x0728:
        r9 = r9 - r16;
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r10 = r4 instanceof org.mozilla.javascript.InterpretedFunction;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 == 0) goto L_0x076a;
    L_0x0730:
        r0 = r4;
        r0 = (org.mozilla.javascript.InterpretedFunction) r0;	 Catch:{ Throwable -> 0x00c7 }
        r17 = r0;
        r10 = r5.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r10.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r17;
        r11 = r0.securityDomain;	 Catch:{ Throwable -> 0x00c7 }
        if (r10 != r11) goto L_0x076a;
    L_0x073f:
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r17;
        r1 = r40;
        r12 = r0.createObject(r1, r4);	 Catch:{ Throwable -> 0x00c7 }
        r19 = new org.mozilla.javascript.Interpreter$CallFrame;	 Catch:{ Throwable -> 0x00c7 }
        r4 = 0;
        r0 = r19;
        r0.<init>();	 Catch:{ Throwable -> 0x00c7 }
        r11 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r15 = r9 + 1;
        r10 = r40;
        r13 = r7;
        r14 = r8;
        r18 = r5;
        initFrame(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r12;	 Catch:{ Throwable -> 0x00c7 }
        r5.savedStackTop = r9;	 Catch:{ Throwable -> 0x00c7 }
        r5.savedCallOp = r6;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r31;
        r5 = r19;
        goto L_0x0051;
    L_0x076a:
        r6 = r4 instanceof org.mozilla.javascript.Function;	 Catch:{ Throwable -> 0x00c7 }
        if (r6 != 0) goto L_0x077d;
    L_0x076e:
        r0 = r32;
        if (r4 != r0) goto L_0x0778;
    L_0x0772:
        r6 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r6);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0778:
        r4 = org.mozilla.javascript.ScriptRuntime.notFunctionError(r4);	 Catch:{ Throwable -> 0x00c7 }
        throw r4;	 Catch:{ Throwable -> 0x00c7 }
    L_0x077d:
        r4 = (org.mozilla.javascript.Function) r4;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4 instanceof org.mozilla.javascript.IdFunctionObject;	 Catch:{ Throwable -> 0x00c7 }
        if (r6 == 0) goto L_0x079c;
    L_0x0783:
        r0 = r4;
        r0 = (org.mozilla.javascript.IdFunctionObject) r0;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r0;
        r6 = org.mozilla.javascript.NativeContinuation.isContinuationConstructor(r6);	 Catch:{ Throwable -> 0x00c7 }
        if (r6 == 0) goto L_0x079c;
    L_0x078d:
        r4 = r5.stack;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.parentFrame;	 Catch:{ Throwable -> 0x00c7 }
        r10 = 0;
        r0 = r40;
        r6 = captureContinuation(r0, r6, r10);	 Catch:{ Throwable -> 0x00c7 }
        r4[r9] = r6;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x079c:
        r6 = r9 + 1;
        r0 = r16;
        r6 = getArgsArray(r7, r8, r6, r0);	 Catch:{ Throwable -> 0x00c7 }
        r10 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = r4.construct(r0, r10, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x07b0:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x07bc;
    L_0x07b6:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x07bc:
        r4 = org.mozilla.javascript.ScriptRuntime.typeof(r4);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x07c4:
        r9 = r9 + 1;
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r4 = org.mozilla.javascript.ScriptRuntime.typeofName(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x07d2:
        r9 = r9 + 1;
        r7[r9] = r31;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x07d8:
        r9 = r9 + 1;
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = getShort(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r10 = (double) r4;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 2;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x07ed:
        r9 = r9 + 1;
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = getInt(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r10 = (double) r4;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 4;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0802:
        r9 = r9 + 1;
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4.itsDoubleTable;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r4[r16];	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0810:
        r9 = r9 + 1;
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r31;
        r4 = org.mozilla.javascript.ScriptRuntime.name(r0, r4, r1);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0820:
        r9 = r9 + 1;
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r15[r6];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.nameIncrDecr(r4, r0, r1, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 1;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x083a:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4 + 1;
        r5.pc = r6;	 Catch:{ Throwable -> 0x00c7 }
        r13 = r15[r4];	 Catch:{ Throwable -> 0x00c7 }
    L_0x0842:
        r6 = r5;
        r10 = r34;
        r11 = r35;
        r12 = r36;
        r9 = doSetConstVar(r6, r7, r8, r9, r10, r11, r12, r13);	 Catch:{ Throwable -> 0x00c7 }
        r16 = r13;
        goto L_0x008e;
    L_0x0851:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4 + 1;
        r5.pc = r6;	 Catch:{ Throwable -> 0x00c7 }
        r13 = r15[r4];	 Catch:{ Throwable -> 0x00c7 }
    L_0x0859:
        r6 = r5;
        r10 = r34;
        r11 = r35;
        r12 = r36;
        r9 = doSetVar(r6, r7, r8, r9, r10, r11, r12, r13);	 Catch:{ Throwable -> 0x00c7 }
        r16 = r13;
        goto L_0x008e;
    L_0x0868:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4 + 1;
        r5.pc = r6;	 Catch:{ Throwable -> 0x00c7 }
        r12 = r15[r4];	 Catch:{ Throwable -> 0x00c7 }
    L_0x0870:
        r6 = r5;
        r10 = r34;
        r11 = r35;
        r9 = doGetVar(r6, r7, r8, r9, r10, r11, r12);	 Catch:{ Throwable -> 0x00c7 }
        r16 = r12;
        goto L_0x008e;
    L_0x087d:
        r17 = r40;
        r18 = r5;
        r19 = r7;
        r20 = r8;
        r21 = r9;
        r22 = r34;
        r23 = r35;
        r24 = r36;
        r25 = r16;
        r9 = doVarIncDec(r17, r18, r19, r20, r21, r22, r23, r24, r25);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0895:
        r9 = r9 + 1;
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r10 = 0;
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x089f:
        r9 = r9 + 1;
        r7[r9] = r32;	 Catch:{ Throwable -> 0x00c7 }
        r10 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08a9:
        r9 = r9 + 1;
        r4 = 0;
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08b0:
        r9 = r9 + 1;
        r4 = r5.thisObj;	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08b8:
        r9 = r9 + 1;
        r4 = r5.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08c0:
        r9 = r9 + 1;
        r4 = java.lang.Boolean.FALSE;	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08c8:
        r9 = r9 + 1;
        r4 = java.lang.Boolean.TRUE;	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08d0:
        r9 = r9 + 1;
        r7[r9] = r33;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08d6:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x08e2;
    L_0x08dc:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x08e2:
        r9 = r9 + -1;
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.enterWith(r4, r0, r6);	 Catch:{ Throwable -> 0x00c7 }
        r5.scope = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08f0:
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.leaveWith(r4);	 Catch:{ Throwable -> 0x00c7 }
        r5.scope = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x08fa:
        r9 = r9 + -1;
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4.itsICode;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4[r6];	 Catch:{ Throwable -> 0x00c7 }
        if (r4 == 0) goto L_0x0929;
    L_0x090a:
        r4 = 1;
        r6 = r4;
    L_0x090c:
        r4 = r9 + 1;
        r4 = r7[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Throwable) r4;	 Catch:{ Throwable -> 0x00c7 }
        if (r6 != 0) goto L_0x092c;
    L_0x0914:
        r6 = 0;
    L_0x0915:
        r10 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.newCatchScope(r4, r6, r0, r1, r10);	 Catch:{ Throwable -> 0x00c7 }
        r7[r16] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 1;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0929:
        r4 = 0;
        r6 = r4;
        goto L_0x090c;
    L_0x092c:
        r6 = r7[r16];	 Catch:{ Throwable -> 0x00c7 }
        r6 = (org.mozilla.javascript.Scriptable) r6;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x0915;
    L_0x0931:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0d6e;
    L_0x0937:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
        r10 = r4;
    L_0x093e:
        r9 = r9 + -1;
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = 58;
        if (r6 != r4) goto L_0x0955;
    L_0x0948:
        r4 = 0;
    L_0x0949:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.enumInit(r10, r0, r6, r4);	 Catch:{ Throwable -> 0x00c7 }
        r7[r16] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0955:
        r4 = 59;
        if (r6 != r4) goto L_0x095b;
    L_0x0959:
        r4 = 1;
        goto L_0x0949;
    L_0x095b:
        r4 = 2;
        goto L_0x0949;
    L_0x095d:
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = r7[r16];	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + 1;
        r10 = 61;
        if (r6 != r10) goto L_0x0971;
    L_0x0969:
        r4 = org.mozilla.javascript.ScriptRuntime.enumNext(r4);	 Catch:{ Throwable -> 0x00c7 }
    L_0x096d:
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0971:
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.enumId(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x096d;
    L_0x0978:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0984;
    L_0x097e:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0984:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r31;
        r1 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.specialRef(r4, r0, r1, r6);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0992:
        r0 = r40;
        r1 = r16;
        r9 = doRefMember(r0, r7, r8, r9, r1);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x099c:
        r0 = r40;
        r1 = r16;
        r9 = doRefNsMember(r0, r7, r8, r9, r1);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x09a6:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x09b2;
    L_0x09ac:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x09b2:
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r16;
        r4 = org.mozilla.javascript.ScriptRuntime.nameRef(r4, r0, r6, r1);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x09c0:
        r17 = r40;
        r18 = r5;
        r19 = r7;
        r20 = r8;
        r21 = r9;
        r22 = r16;
        r9 = doRefNsName(r17, r18, r19, r20, r21, r22);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x09d2:
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = r7[r16];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (org.mozilla.javascript.Scriptable) r4;	 Catch:{ Throwable -> 0x00c7 }
        r5.scope = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x09de:
        r4 = r5.localShift;	 Catch:{ Throwable -> 0x00c7 }
        r16 = r16 + r4;
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r7[r16] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x09e8:
        r9 = r9 + 1;
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r16;
        r4 = org.mozilla.javascript.InterpretedFunction.createFunction(r0, r4, r6, r1);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x09fa:
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.fnOrScript;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r1 = r16;
        initFunction(r0, r4, r6, r1);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0a07:
        r4 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4.itsRegExpLiterals;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4[r16];	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + 1;
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.wrapRegExp(r0, r6, r4);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0a1b:
        r4 = r9 + 1;
        r0 = r16;
        r6 = new int[r0];	 Catch:{ Throwable -> 0x00c7 }
        r7[r4] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r4 + 1;
        r0 = r16;
        r4 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = 0;
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0a31:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0d6b;
    L_0x0a37:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r4;
    L_0x0a3e:
        r9 = r9 + -1;
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r10 = (int) r10;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4[r10] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r10 + 1;
        r10 = (double) r4;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0a52:
        r6 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + -1;
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r10 = (int) r10;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4[r10] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + -1;
        r4 = r7[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (int[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = (int[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r6 = -1;
        r4[r10] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r10 + 1;
        r10 = (double) r4;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0a73:
        r6 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r9 = r9 + -1;
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r10 = (int) r10;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4[r10] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r9 + -1;
        r4 = r7[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (int[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = (int[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r6 = 1;
        r4[r10] = r6;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r10 + 1;
        r10 = (double) r4;	 Catch:{ Throwable -> 0x00c7 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0a94:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = (java.lang.Object[]) r4;	 Catch:{ Throwable -> 0x00c7 }
        r10 = r9 + -1;
        r9 = r7[r10];	 Catch:{ Throwable -> 0x00c7 }
        r9 = (int[]) r9;	 Catch:{ Throwable -> 0x00c7 }
        r9 = (int[]) r9;	 Catch:{ Throwable -> 0x00c7 }
        r11 = 66;
        if (r6 != r11) goto L_0x0abd;
    L_0x0aa6:
        r6 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6.literalIds;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6[r16];	 Catch:{ Throwable -> 0x00c7 }
        r6 = (java.lang.Object[]) r6;	 Catch:{ Throwable -> 0x00c7 }
        r6 = (java.lang.Object[]) r6;	 Catch:{ Throwable -> 0x00c7 }
        r11 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.newObjectLiteral(r6, r4, r9, r0, r11);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0ab8:
        r7[r10] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r9 = r10;
        goto L_0x008e;
    L_0x0abd:
        r9 = 0;
        r11 = -31;
        if (r6 != r11) goto L_0x0d68;
    L_0x0ac2:
        r6 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6.literalIds;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r6[r16];	 Catch:{ Throwable -> 0x00c7 }
        r6 = (int[]) r6;	 Catch:{ Throwable -> 0x00c7 }
        r6 = (int[]) r6;	 Catch:{ Throwable -> 0x00c7 }
    L_0x0acc:
        r9 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.newArrayLiteral(r4, r6, r0, r9);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x0ab8;
    L_0x0ad5:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0ae1;
    L_0x0adb:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0ae1:
        r9 = r9 + -1;
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.enterDotQuery(r4, r6);	 Catch:{ Throwable -> 0x00c7 }
        r5.scope = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0aed:
        r4 = stack_boolean(r5, r9);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.updateDotQuery(r4, r6);	 Catch:{ Throwable -> 0x00c7 }
        if (r4 == 0) goto L_0x0b0b;
    L_0x0af9:
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.scope;	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.leaveDotQuery(r4);	 Catch:{ Throwable -> 0x00c7 }
        r5.scope = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 2;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0b0b:
        r9 = r9 + -1;
        goto L_0x0285;
    L_0x0b0f:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 != r0) goto L_0x0b1b;
    L_0x0b15:
        r10 = r8[r9];	 Catch:{ Throwable -> 0x00c7 }
        r4 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0b1b:
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.setDefaultNamespace(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0b25:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 == r0) goto L_0x008e;
    L_0x0b2b:
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.escapeAttributeValue(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0b35:
        r4 = r7[r9];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r32;
        if (r4 == r0) goto L_0x008e;
    L_0x0b3b:
        r0 = r40;
        r4 = org.mozilla.javascript.ScriptRuntime.escapeTextValue(r4, r0);	 Catch:{ Throwable -> 0x00c7 }
        r7[r9] = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0b45:
        r4 = r5.debuggerFrame;	 Catch:{ Throwable -> 0x00c7 }
        if (r4 == 0) goto L_0x008e;
    L_0x0b49:
        r4 = r5.debuggerFrame;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r4.onDebuggerStatement(r0);	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0b52:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r5.pcSourceLineStart = r4;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.debuggerFrame;	 Catch:{ Throwable -> 0x00c7 }
        if (r4 == 0) goto L_0x0b67;
    L_0x0b5a:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = getIndex(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.debuggerFrame;	 Catch:{ Throwable -> 0x00c7 }
        r0 = r40;
        r6.onLineChange(r0, r4);	 Catch:{ Throwable -> 0x00c7 }
    L_0x0b67:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 2;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0b6f:
        r16 = 0;
        goto L_0x008e;
    L_0x0b73:
        r16 = 1;
        goto L_0x008e;
    L_0x0b77:
        r16 = 2;
        goto L_0x008e;
    L_0x0b7b:
        r16 = 3;
        goto L_0x008e;
    L_0x0b7f:
        r16 = 4;
        goto L_0x008e;
    L_0x0b83:
        r16 = 5;
        goto L_0x008e;
    L_0x0b87:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r15[r4];	 Catch:{ Throwable -> 0x00c7 }
        r0 = r4 & 255;
        r16 = r0;
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 1;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0b97:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r16 = getIndex(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 2;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0ba5:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r16 = getInt(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 + 4;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0bb3:
        r4 = 0;
        r31 = r37[r4];	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0bb8:
        r4 = 1;
        r31 = r37[r4];	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0bbd:
        r4 = 2;
        r31 = r37[r4];	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0bc2:
        r4 = 3;
        r31 = r37[r4];	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x008e;
    L_0x0bc7:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r15[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4 & 255;
        r6 = r37[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00fd }
        r4 = r4 + 1;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00fd }
        r31 = r6;
        goto L_0x008e;
    L_0x0bd9:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = getIndex(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r37[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00fd }
        r4 = r4 + 2;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00fd }
        r31 = r6;
        goto L_0x008e;
    L_0x0beb:
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = getInt(r15, r4);	 Catch:{ Throwable -> 0x00c7 }
        r6 = r37[r4];	 Catch:{ Throwable -> 0x00c7 }
        r4 = r5.pc;	 Catch:{ Throwable -> 0x00fd }
        r4 = r4 + 4;
        r5.pc = r4;	 Catch:{ Throwable -> 0x00fd }
        r31 = r6;
        goto L_0x008e;
    L_0x0bfd:
        r4 = r5.idata;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4.longJumps;	 Catch:{ Throwable -> 0x00c7 }
        r6 = r5.pc;	 Catch:{ Throwable -> 0x00c7 }
        r4 = r4.getExistingInt(r6);	 Catch:{ Throwable -> 0x00c7 }
        r5.pc = r4;	 Catch:{ Throwable -> 0x00c7 }
        goto L_0x029c;
    L_0x0c0b:
        r5 = r4;
        r4 = r27;
    L_0x0c0e:
        r0 = r40;
        r8 = r0.previousInterpreterInvocations;
        if (r8 == 0) goto L_0x0d24;
    L_0x0c14:
        r0 = r40;
        r8 = r0.previousInterpreterInvocations;
        r8 = r8.size();
        if (r8 == 0) goto L_0x0d24;
    L_0x0c1e:
        r0 = r40;
        r8 = r0.previousInterpreterInvocations;
        r8 = r8.pop();
        r0 = r40;
        r0.lastInterpreterFrame = r8;
    L_0x0c2a:
        if (r4 == 0) goto L_0x0d33;
    L_0x0c2c:
        r5 = r4 instanceof java.lang.RuntimeException;
        if (r5 == 0) goto L_0x0d30;
    L_0x0c30:
        r4 = (java.lang.RuntimeException) r4;
        throw r4;
    L_0x0c33:
        r10 = r5;
        r31 = r8;
        r19 = r9;
        r5 = r4;
        r8 = r6;
        goto L_0x0163;
    L_0x0c3c:
        r4 = r5 instanceof org.mozilla.javascript.JavaScriptException;
        if (r4 == 0) goto L_0x0c48;
    L_0x0c40:
        r4 = 2;
        r38 = r6;
        r6 = r4;
        r4 = r38;
        goto L_0x017e;
    L_0x0c48:
        r4 = r5 instanceof org.mozilla.javascript.EcmaError;
        if (r4 == 0) goto L_0x0c54;
    L_0x0c4c:
        r4 = 2;
        r38 = r6;
        r6 = r4;
        r4 = r38;
        goto L_0x017e;
    L_0x0c54:
        r4 = r5 instanceof org.mozilla.javascript.EvaluatorException;
        if (r4 == 0) goto L_0x0c60;
    L_0x0c58:
        r4 = 2;
        r38 = r6;
        r6 = r4;
        r4 = r38;
        goto L_0x017e;
    L_0x0c60:
        r4 = r5 instanceof org.mozilla.javascript.ContinuationPending;
        if (r4 == 0) goto L_0x0c6c;
    L_0x0c64:
        r4 = 0;
        r38 = r6;
        r6 = r4;
        r4 = r38;
        goto L_0x017e;
    L_0x0c6c:
        r4 = r5 instanceof java.lang.RuntimeException;
        if (r4 == 0) goto L_0x0c84;
    L_0x0c70:
        r4 = 13;
        r0 = r40;
        r4 = r0.hasFeature(r4);
        if (r4 == 0) goto L_0x0c82;
    L_0x0c7a:
        r4 = 2;
    L_0x0c7b:
        r38 = r6;
        r6 = r4;
        r4 = r38;
        goto L_0x017e;
    L_0x0c82:
        r4 = 1;
        goto L_0x0c7b;
    L_0x0c84:
        r4 = r5 instanceof java.lang.Error;
        if (r4 == 0) goto L_0x0c9c;
    L_0x0c88:
        r4 = 13;
        r0 = r40;
        r4 = r0.hasFeature(r4);
        if (r4 == 0) goto L_0x0c9a;
    L_0x0c92:
        r4 = 2;
    L_0x0c93:
        r38 = r6;
        r6 = r4;
        r4 = r38;
        goto L_0x017e;
    L_0x0c9a:
        r4 = 0;
        goto L_0x0c93;
    L_0x0c9c:
        r4 = r5 instanceof org.mozilla.javascript.Interpreter.ContinuationJump;
        if (r4 == 0) goto L_0x0ca6;
    L_0x0ca0:
        r6 = 1;
        r4 = r5;
        r4 = (org.mozilla.javascript.Interpreter.ContinuationJump) r4;
        goto L_0x017e;
    L_0x0ca6:
        r4 = 13;
        r0 = r40;
        r4 = r0.hasFeature(r4);
        if (r4 == 0) goto L_0x0cb8;
    L_0x0cb0:
        r4 = 2;
    L_0x0cb1:
        r38 = r6;
        r6 = r4;
        r4 = r38;
        goto L_0x017e;
    L_0x0cb8:
        r4 = 1;
        goto L_0x0cb1;
    L_0x0cba:
        r6 = move-exception;
        r5 = 1;
        r7 = r5;
        r5 = r6;
        r6 = r4;
        goto L_0x018b;
    L_0x0cc1:
        r6 = move-exception;
        r4 = 0;
        r5 = 0;
        r7 = r5;
        r5 = r6;
        r6 = r4;
        goto L_0x018b;
    L_0x0cc9:
        r4 = move-exception;
        r6 = 0;
        r7 = 0;
        r5 = r4;
        r38 = r7;
        r7 = r6;
        r6 = r38;
        goto L_0x01a6;
    L_0x0cd4:
        r4 = 0;
        goto L_0x01ac;
    L_0x0cd7:
        r0 = r40;
        r1 = r19;
        exitFrame(r0, r1, r5);
        r0 = r19;
        r0 = r0.parentFrame;
        r19 = r0;
        if (r19 != 0) goto L_0x0d01;
    L_0x0ce6:
        if (r7 == 0) goto L_0x0d63;
    L_0x0ce8:
        r4 = r7.branchFrame;
        if (r4 == 0) goto L_0x0cef;
    L_0x0cec:
        org.mozilla.javascript.Kit.codeBug();
    L_0x0cef:
        r4 = r7.capturedFrame;
        if (r4 == 0) goto L_0x0d17;
    L_0x0cf3:
        r16 = -1;
        r28 = r8;
        r30 = r10;
        r6 = r31;
        r27 = r5;
        r5 = r19;
        goto L_0x0051;
    L_0x0d01:
        if (r7 == 0) goto L_0x01a6;
    L_0x0d03:
        r4 = r7.branchFrame;
        r0 = r19;
        if (r4 != r0) goto L_0x01a6;
    L_0x0d09:
        r16 = -1;
        r28 = r8;
        r30 = r10;
        r6 = r31;
        r27 = r5;
        r5 = r19;
        goto L_0x0051;
    L_0x0d17:
        r6 = r7.result;
        r4 = r7.resultDbl;
        r7 = 0;
        r38 = r4;
        r5 = r6;
        r4 = r7;
        r6 = r38;
        goto L_0x0c0e;
    L_0x0d24:
        r8 = 0;
        r0 = r40;
        r0.lastInterpreterFrame = r8;
        r8 = 0;
        r0 = r40;
        r0.previousInterpreterInvocations = r8;
        goto L_0x0c2a;
    L_0x0d30:
        r4 = (java.lang.Error) r4;
        throw r4;
    L_0x0d33:
        r0 = r32;
        if (r5 != r0) goto L_0x0151;
    L_0x0d37:
        r5 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r6);
        goto L_0x0151;
    L_0x0d3d:
        r8 = move-exception;
        r9 = r5;
        r5 = r4;
        r4 = r8;
        r8 = r31;
        goto L_0x00cf;
    L_0x0d45:
        r5 = move-exception;
        r9 = r8;
        r8 = r31;
        r38 = r4;
        r4 = r5;
        r5 = r38;
        goto L_0x00cf;
    L_0x0d50:
        r4 = move-exception;
        r8 = r6;
        r9 = r5;
        r6 = r28;
        r5 = r30;
        goto L_0x00cf;
    L_0x0d59:
        r6 = move-exception;
        r8 = r31;
        r9 = r5;
        r5 = r4;
        r4 = r6;
        r6 = r28;
        goto L_0x00cf;
    L_0x0d63:
        r6 = r8;
        r4 = r5;
        r5 = r10;
        goto L_0x0c0e;
    L_0x0d68:
        r6 = r9;
        goto L_0x0acc;
    L_0x0d6b:
        r6 = r4;
        goto L_0x0a3e;
    L_0x0d6e:
        r10 = r4;
        goto L_0x093e;
    L_0x0d71:
        r18 = r5;
        goto L_0x0639;
    L_0x0d75:
        r6 = r4;
        goto L_0x057f;
    L_0x0d78:
        r6 = r4;
        goto L_0x04e1;
    L_0x0d7b:
        r6 = r4;
        goto L_0x046d;
    L_0x0d7e:
        r6 = r4;
        goto L_0x0412;
    L_0x0d81:
        r10 = r4;
        goto L_0x03e2;
    L_0x0d84:
        r9 = r4;
        goto L_0x0285;
    L_0x0d87:
        r12 = r16;
        goto L_0x0870;
    L_0x0d8b:
        r13 = r16;
        goto L_0x0859;
    L_0x0d8f:
        r13 = r16;
        goto L_0x0842;
    L_0x0d93:
        r38 = r6;
        r6 = r7;
        r7 = r38;
        goto L_0x01a6;
    L_0x0d9a:
        r7 = r6;
        r6 = r4;
        goto L_0x018b;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Interpreter.interpretLoop(org.mozilla.javascript.Context, org.mozilla.javascript.Interpreter$CallFrame, java.lang.Object):java.lang.Object");
    }

    private static int doInOrInstanceof(Context context, int i, Object[] objArr, double[] dArr, int i2) {
        boolean in;
        Object obj = objArr[i2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        int i3 = i2 - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        if (i == 52) {
            in = ScriptRuntime.in(obj2, obj, context);
        } else {
            in = ScriptRuntime.instanceOf(obj2, obj, context);
        }
        objArr[i3] = ScriptRuntime.wrapBoolean(in);
        return i3;
    }

    private static int doCompare(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        double d;
        double stack_double;
        boolean z = true;
        int i3 = i2 - 1;
        UniqueTag uniqueTag = objArr[i3 + 1];
        UniqueTag uniqueTag2 = objArr[i3];
        if (uniqueTag == UniqueTag.DOUBLE_MARK) {
            d = dArr[i3 + 1];
            stack_double = stack_double(callFrame, i3);
        } else if (uniqueTag2 == UniqueTag.DOUBLE_MARK) {
            d = ScriptRuntime.toNumber(uniqueTag);
            stack_double = dArr[i3];
        } else {
            switch (i) {
                case 14:
                    z = ScriptRuntime.cmp_LT(uniqueTag2, uniqueTag);
                    break;
                case 15:
                    z = ScriptRuntime.cmp_LE(uniqueTag2, uniqueTag);
                    break;
                case 16:
                    z = ScriptRuntime.cmp_LT(uniqueTag, uniqueTag2);
                    break;
                case 17:
                    z = ScriptRuntime.cmp_LE(uniqueTag, uniqueTag2);
                    break;
                default:
                    throw Kit.codeBug();
            }
            objArr[i3] = ScriptRuntime.wrapBoolean(z);
            return i3;
        }
        switch (i) {
            case 14:
                if (stack_double >= d) {
                    z = false;
                    break;
                }
                break;
            case 15:
                if (stack_double > d) {
                    z = false;
                    break;
                }
                break;
            case 16:
                if (stack_double <= d) {
                    z = false;
                    break;
                }
                break;
            case 17:
                if (stack_double < d) {
                    z = false;
                    break;
                }
                break;
            default:
                throw Kit.codeBug();
        }
        objArr[i3] = ScriptRuntime.wrapBoolean(z);
        return i3;
    }

    private static int doBitOp(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        int stack_int32 = stack_int32(callFrame, i2 - 1);
        int stack_int322 = stack_int32(callFrame, i2);
        int i3 = i2 - 1;
        objArr[i3] = UniqueTag.DOUBLE_MARK;
        switch (i) {
            case 9:
                stack_int32 |= stack_int322;
                break;
            case 10:
                stack_int32 ^= stack_int322;
                break;
            case 11:
                stack_int32 &= stack_int322;
                break;
            case 18:
                stack_int32 <<= stack_int322;
                break;
            case 19:
                stack_int32 >>= stack_int322;
                break;
        }
        dArr[i3] = (double) stack_int32;
        return i3;
    }

    private static int doDelName(Context context, CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        Object obj = objArr[i2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        int i3 = i2 - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.delete(obj2, obj, context, callFrame.scope, i == 0);
        return i3;
    }

    private static int doGetElem(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        int i2 = i - 1;
        Object obj = objArr[i2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        UniqueTag uniqueTag = objArr[i2 + 1];
        if (uniqueTag != UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.getObjectElem(obj, uniqueTag, context, callFrame.scope);
        } else {
            obj = ScriptRuntime.getObjectIndex(obj, dArr[i2 + 1], context, callFrame.scope);
        }
        objArr[i2] = obj;
        return i2;
    }

    private static int doSetElem(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i) {
        Object objectElem;
        int i2 = i - 2;
        Object obj = objArr[i2 + 2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i2 + 2]);
        }
        Object obj2 = objArr[i2];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        UniqueTag uniqueTag = objArr[i2 + 1];
        if (uniqueTag != UniqueTag.DOUBLE_MARK) {
            objectElem = ScriptRuntime.setObjectElem(obj2, uniqueTag, obj, context, callFrame.scope);
        } else {
            objectElem = ScriptRuntime.setObjectIndex(obj2, dArr[i2 + 1], obj, context, callFrame.scope);
        }
        objArr[i2] = objectElem;
        return i2;
    }

    private static int doElemIncDec(Context context, CallFrame callFrame, byte[] bArr, Object[] objArr, double[] dArr, int i) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i2 = i - 1;
        Object obj2 = objArr[i2];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i2]);
        }
        objArr[i2] = ScriptRuntime.elemIncrDecr(obj2, obj, context, callFrame.scope, bArr[callFrame.pc]);
        callFrame.pc++;
        return i2;
    }

    private static int doCallSpecial(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, byte[] bArr, int i2) {
        int i3;
        int i4 = bArr[callFrame.pc] & 255;
        Object obj = bArr[callFrame.pc + 1] != (byte) 0 ? 1 : null;
        int index = getIndex(bArr, callFrame.pc + 2);
        if (obj != null) {
            int i5 = i - i2;
            obj = objArr[i5];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i5]);
            }
            objArr[i5] = ScriptRuntime.newSpecial(context, obj, getArgsArray(objArr, dArr, i5 + 1, i2), callFrame.scope, i4);
            i3 = i5;
        } else {
            int i6 = i - (i2 + 1);
            objArr[i6] = ScriptRuntime.callSpecial(context, (Callable) objArr[i6], (Scriptable) objArr[i6 + 1], getArgsArray(objArr, dArr, i6 + 2, i2), callFrame.scope, callFrame.thisObj, i4, callFrame.idata.itsSourceFile, index);
            i3 = i6;
        }
        callFrame.pc += 4;
        return i3;
    }

    private static int doSetConstVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        if (callFrame.useActivation) {
            Object wrapNumber;
            UniqueTag uniqueTag = objArr[i];
            if (uniqueTag == UniqueTag.DOUBLE_MARK) {
                wrapNumber = ScriptRuntime.wrapNumber(dArr[i]);
            } else {
                UniqueTag uniqueTag2 = uniqueTag;
            }
            String str = callFrame.idata.argNames[i2];
            if (callFrame.scope instanceof ConstProperties) {
                ((ConstProperties) callFrame.scope).putConst(str, callFrame.scope, wrapNumber);
            } else {
                throw Kit.codeBug();
            }
        } else if ((iArr[i2] & 1) == 0) {
            throw Context.reportRuntimeError1("msg.var.redecl", callFrame.idata.argNames[i2]);
        } else if ((iArr[i2] & 8) != 0) {
            objArr2[i2] = objArr[i];
            iArr[i2] = iArr[i2] & -9;
            dArr2[i2] = dArr[i];
        }
        return i;
    }

    private static int doSetVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        if (callFrame.useActivation) {
            Object obj = objArr[i];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i]);
            }
            callFrame.scope.put(callFrame.idata.argNames[i2], callFrame.scope, obj);
        } else if ((iArr[i2] & 1) == 0) {
            objArr2[i2] = objArr[i];
            dArr2[i2] = dArr[i];
        }
        return i;
    }

    private static int doGetVar(CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int i2) {
        int i3 = i + 1;
        if (callFrame.useActivation) {
            objArr[i3] = callFrame.scope.get(callFrame.idata.argNames[i2], callFrame.scope);
        } else {
            objArr[i3] = objArr2[i2];
            dArr[i3] = dArr2[i2];
        }
        return i3;
    }

    private static int doVarIncDec(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, Object[] objArr2, double[] dArr2, int[] iArr, int i2) {
        int i3 = i + 1;
        byte b = callFrame.idata.itsICode[callFrame.pc];
        if (callFrame.useActivation) {
            objArr[i3] = ScriptRuntime.nameIncrDecr(callFrame.scope, callFrame.idata.argNames[i2], context, b);
        } else {
            double d;
            UniqueTag uniqueTag = objArr2[i2];
            if (uniqueTag == UniqueTag.DOUBLE_MARK) {
                d = dArr2[i2];
            } else {
                d = ScriptRuntime.toNumber(uniqueTag);
            }
            double d2 = (b & 1) == 0 ? 1.0d + d : d - 1.0d;
            Object obj = (b & 2) != 0 ? 1 : null;
            if ((iArr[i2] & 1) == 0) {
                double d3;
                if (uniqueTag != UniqueTag.DOUBLE_MARK) {
                    objArr2[i2] = UniqueTag.DOUBLE_MARK;
                }
                dArr2[i2] = d2;
                objArr[i3] = UniqueTag.DOUBLE_MARK;
                if (obj != null) {
                    d3 = d;
                } else {
                    d3 = d2;
                }
                dArr[i3] = d3;
            } else if (obj == null || uniqueTag == UniqueTag.DOUBLE_MARK) {
                objArr[i3] = UniqueTag.DOUBLE_MARK;
                if (obj == null) {
                    d = d2;
                }
                dArr[i3] = d;
            } else {
                objArr[i3] = uniqueTag;
            }
        }
        callFrame.pc++;
        return i3;
    }

    private static int doRefMember(Context context, Object[] objArr, double[] dArr, int i, int i2) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.memberRef(obj2, obj, context, i2);
        return i3;
    }

    private static int doRefNsMember(Context context, Object[] objArr, double[] dArr, int i, int i2) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        int i4 = i3 - 1;
        Object obj3 = objArr[i4];
        if (obj3 == UniqueTag.DOUBLE_MARK) {
            obj3 = ScriptRuntime.wrapNumber(dArr[i4]);
        }
        objArr[i4] = ScriptRuntime.memberRef(obj3, obj2, obj, context, i2);
        return i4;
    }

    private static int doRefNsName(Context context, CallFrame callFrame, Object[] objArr, double[] dArr, int i, int i2) {
        Object obj = objArr[i];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(dArr[i]);
        }
        int i3 = i - 1;
        Object obj2 = objArr[i3];
        if (obj2 == UniqueTag.DOUBLE_MARK) {
            obj2 = ScriptRuntime.wrapNumber(dArr[i3]);
        }
        objArr[i3] = ScriptRuntime.nameRef(obj2, obj, context, callFrame.scope, i2);
        return i3;
    }

    private static CallFrame initFrameForNoSuchMethod(Context context, CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2, int i3, Scriptable scriptable, Scriptable scriptable2, ScriptRuntime$NoSuchMethodShim scriptRuntime$NoSuchMethodShim, InterpretedFunction interpretedFunction) {
        CallFrame callFrame2;
        Object[] objArr2 = new Object[i];
        int i4 = i2 + 2;
        for (int i5 = 0; i5 < i; i5++) {
            Number number = objArr[i4];
            if (number == UniqueTag.DOUBLE_MARK) {
                number = ScriptRuntime.wrapNumber(dArr[i4]);
            }
            objArr2[i5] = number;
            i4++;
        }
        Object[] objArr3 = new Object[]{scriptRuntime$NoSuchMethodShim.methodName, context.newArray(scriptable2, objArr2)};
        CallFrame callFrame3 = new CallFrame();
        if (i3 == -55) {
            callFrame2 = callFrame.parentFrame;
            exitFrame(context, callFrame, null);
        } else {
            callFrame2 = callFrame;
        }
        initFrame(context, scriptable2, scriptable, objArr3, null, 0, 2, interpretedFunction, callFrame2, callFrame3);
        if (i3 != -55) {
            callFrame.savedStackTop = i2;
            callFrame.savedCallOp = i3;
        }
        return callFrame3;
    }

    private static boolean doEquals(Object[] objArr, double[] dArr, int i) {
        UniqueTag uniqueTag = objArr[i + 1];
        UniqueTag uniqueTag2 = objArr[i];
        if (uniqueTag == UniqueTag.DOUBLE_MARK) {
            if (uniqueTag2 == UniqueTag.DOUBLE_MARK) {
                return dArr[i] == dArr[i + 1];
            } else {
                return ScriptRuntime.eqNumber(dArr[i + 1], uniqueTag2);
            }
        } else if (uniqueTag2 == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.eqNumber(dArr[i], uniqueTag);
        } else {
            return ScriptRuntime.eq(uniqueTag2, uniqueTag);
        }
    }

    private static boolean doShallowEquals(Object[] objArr, double[] dArr, int i) {
        double d;
        double d2;
        boolean z;
        UniqueTag uniqueTag = objArr[i + 1];
        UniqueTag uniqueTag2 = objArr[i];
        UniqueTag uniqueTag3 = UniqueTag.DOUBLE_MARK;
        if (uniqueTag == uniqueTag3) {
            d = dArr[i + 1];
            if (uniqueTag2 == uniqueTag3) {
                d2 = dArr[i];
            } else if (!(uniqueTag2 instanceof Number)) {
                return false;
            } else {
                d2 = ((Number) uniqueTag2).doubleValue();
            }
        } else if (uniqueTag2 != uniqueTag3) {
            return ScriptRuntime.shallowEq(uniqueTag2, uniqueTag);
        } else {
            d = dArr[i];
            if (!(uniqueTag instanceof Number)) {
                return false;
            }
            double d3 = d;
            d = ((Number) uniqueTag).doubleValue();
            d2 = d3;
        }
        if (d2 == d) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private static CallFrame processThrowable(Context context, Object obj, CallFrame callFrame, int i, boolean z) {
        int i2;
        if (i >= 0) {
            if (callFrame.frozen) {
                callFrame = callFrame.cloneFrozen();
            }
            int[] iArr = callFrame.idata.itsExceptionTable;
            callFrame.pc = iArr[i + 2];
            if (z) {
                callFrame.pcPrevBranch = callFrame.pc;
            }
            callFrame.savedStackTop = callFrame.emptyStackTop;
            i2 = callFrame.localShift + iArr[i + 4];
            callFrame.scope = (Scriptable) callFrame.stack[callFrame.localShift + iArr[i + 5]];
            callFrame.stack[i2] = obj;
        } else {
            ContinuationJump continuationJump = (ContinuationJump) obj;
            if (continuationJump.branchFrame != callFrame) {
                Kit.codeBug();
            }
            if (continuationJump.capturedFrame == null) {
                Kit.codeBug();
            }
            int i3 = continuationJump.capturedFrame.frameIndex + 1;
            if (continuationJump.branchFrame != null) {
                i3 -= continuationJump.branchFrame.frameIndex;
            }
            CallFrame callFrame2 = continuationJump.capturedFrame;
            i2 = 0;
            CallFrame[] callFrameArr = null;
            for (int i4 = 0; i4 != i3; i4++) {
                if (!callFrame2.frozen) {
                    Kit.codeBug();
                }
                if (isFrameEnterExitRequired(callFrame2)) {
                    if (callFrameArr == null) {
                        callFrameArr = new CallFrame[(i3 - i4)];
                    }
                    callFrameArr[i2] = callFrame2;
                    i2++;
                }
                callFrame2 = callFrame2.parentFrame;
            }
            while (i2 != 0) {
                i2--;
                enterFrame(context, callFrameArr[i2], ScriptRuntime.emptyArgs, true);
            }
            callFrame = continuationJump.capturedFrame.cloneFrozen();
            setCallResult(callFrame, continuationJump.result, continuationJump.resultDbl);
        }
        callFrame.throwable = null;
        return callFrame;
    }

    private static Object freezeGenerator(Context context, CallFrame callFrame, int i, GeneratorState generatorState) {
        if (generatorState.operation == 2) {
            throw ScriptRuntime.typeError0("msg.yield.closing");
        }
        callFrame.frozen = true;
        callFrame.result = callFrame.stack[i];
        callFrame.resultDbl = callFrame.sDbl[i];
        callFrame.savedStackTop = i;
        callFrame.pc--;
        ScriptRuntime.exitActivationFunction(context);
        return callFrame.result != UniqueTag.DOUBLE_MARK ? callFrame.result : ScriptRuntime.wrapNumber(callFrame.resultDbl);
    }

    private static Object thawGenerator(CallFrame callFrame, int i, GeneratorState generatorState, int i2) {
        callFrame.frozen = false;
        int index = getIndex(callFrame.idata.itsICode, callFrame.pc);
        callFrame.pc += 2;
        if (generatorState.operation == 1) {
            return new JavaScriptException(generatorState.value, callFrame.idata.itsSourceFile, index);
        }
        if (generatorState.operation == 2) {
            return generatorState.value;
        }
        if (generatorState.operation != 0) {
            throw Kit.codeBug();
        }
        if (i2 == 72) {
            callFrame.stack[i] = generatorState.value;
        }
        return Scriptable.NOT_FOUND;
    }

    private static CallFrame initFrameForApplyOrCall(Context context, CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2, int i3, Scriptable scriptable, IdFunctionObject idFunctionObject, InterpretedFunction interpretedFunction) {
        Scriptable toObjectOrNull;
        CallFrame callFrame2;
        if (i != 0) {
            Object obj = objArr[i2 + 2];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(dArr[i2 + 2]);
            }
            toObjectOrNull = ScriptRuntime.toObjectOrNull(context, obj, callFrame.scope);
        } else {
            toObjectOrNull = null;
        }
        if (toObjectOrNull == null) {
            toObjectOrNull = ScriptRuntime.getTopCallScope(context);
        }
        if (i3 == -55) {
            exitFrame(context, callFrame, null);
            callFrame2 = callFrame.parentFrame;
        } else {
            callFrame.savedStackTop = i2;
            callFrame.savedCallOp = i3;
            callFrame2 = callFrame;
        }
        CallFrame callFrame3 = new CallFrame();
        if (BaseFunction.isApply(idFunctionObject)) {
            Object[] applyArguments = i < 2 ? ScriptRuntime.emptyArgs : ScriptRuntime.getApplyArguments(context, objArr[i2 + 3]);
            initFrame(context, scriptable, toObjectOrNull, applyArguments, null, 0, applyArguments.length, interpretedFunction, callFrame2, callFrame3);
        } else {
            for (int i4 = 1; i4 < i; i4++) {
                objArr[(i2 + 1) + i4] = objArr[(i2 + 2) + i4];
                dArr[(i2 + 1) + i4] = dArr[(i2 + 2) + i4];
            }
            initFrame(context, scriptable, toObjectOrNull, objArr, dArr, i2 + 2, i < 2 ? 0 : i - 1, interpretedFunction, callFrame2, callFrame3);
        }
        return callFrame3;
    }

    private static void initFrame(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, double[] dArr, int i, int i2, InterpretedFunction interpretedFunction, CallFrame callFrame, CallFrame callFrame2) {
        DebugFrame debugFrame;
        boolean z;
        int i3;
        Object obj;
        Object obj2;
        int[] iArr;
        Object obj3;
        int i4;
        InterpreterData interpreterData = interpretedFunction.idata;
        boolean z2 = interpreterData.itsNeedsActivation;
        if (context.debugger != null) {
            DebugFrame frame = context.debugger.getFrame(context, interpreterData);
            if (frame != null) {
                debugFrame = frame;
                z = true;
            } else {
                debugFrame = frame;
                z = z2;
            }
        } else {
            debugFrame = null;
            z = z2;
        }
        if (z) {
            if (dArr != null) {
                Object argsArray = getArgsArray(objArr, dArr, i, i2);
            }
            i = 0;
            Object obj4 = null;
        }
        if (interpreterData.itsFunctionType != 0) {
            scriptable = interpretedFunction.getParentScope();
            if (z) {
                scriptable = ScriptRuntime.createFunctionActivation(interpretedFunction, scriptable, argsArray);
            }
        } else {
            ScriptRuntime.initScript(interpretedFunction, scriptable2, context, scriptable, interpretedFunction.idata.evalScriptFlag);
        }
        if (interpreterData.itsNestedFunctions != null) {
            if (!(interpreterData.itsFunctionType == 0 || interpreterData.itsNeedsActivation)) {
                Kit.codeBug();
            }
            for (i3 = 0; i3 < interpreterData.itsNestedFunctions.length; i3++) {
                if (interpreterData.itsNestedFunctions[i3].itsFunctionType == 1) {
                    initFunction(context, scriptable, interpretedFunction, i3);
                }
            }
        }
        int i5 = (interpreterData.itsMaxVars + interpreterData.itsMaxLocals) - 1;
        int i6 = interpreterData.itsMaxFrameArray;
        if (i6 != (interpreterData.itsMaxStack + i5) + 1) {
            Kit.codeBug();
        }
        if (callFrame2.stack == null || i6 > callFrame2.stack.length) {
            obj = null;
            obj2 = new Object[i6];
            iArr = new int[i6];
            obj3 = new double[i6];
        } else {
            obj = 1;
            obj2 = callFrame2.stack;
            iArr = callFrame2.stackAttributes;
            obj3 = callFrame2.sDbl;
        }
        int paramAndVarCount = interpreterData.getParamAndVarCount();
        for (i4 = 0; i4 < paramAndVarCount; i4++) {
            if (interpreterData.getParamOrVarConst(i4)) {
                iArr[i4] = 13;
            }
        }
        i4 = interpreterData.argCount;
        if (i4 <= i2) {
            i2 = i4;
        }
        callFrame2.parentFrame = callFrame;
        callFrame2.frameIndex = callFrame == null ? 0 : callFrame.frameIndex + 1;
        if (callFrame2.frameIndex > context.getMaximumInterpreterStackDepth()) {
            throw Context.reportRuntimeError("Exceeded maximum stack depth");
        }
        callFrame2.frozen = false;
        callFrame2.fnOrScript = interpretedFunction;
        callFrame2.idata = interpreterData;
        callFrame2.stack = obj2;
        callFrame2.stackAttributes = iArr;
        callFrame2.sDbl = obj3;
        callFrame2.varSource = callFrame2;
        callFrame2.localShift = interpreterData.itsMaxVars;
        callFrame2.emptyStackTop = i5;
        callFrame2.debuggerFrame = debugFrame;
        callFrame2.useActivation = z;
        callFrame2.thisObj = scriptable2;
        callFrame2.result = Undefined.instance;
        callFrame2.pc = 0;
        callFrame2.pcPrevBranch = 0;
        callFrame2.pcSourceLineStart = interpreterData.firstLinePC;
        callFrame2.scope = scriptable;
        callFrame2.savedStackTop = i5;
        callFrame2.savedCallOp = 0;
        System.arraycopy(argsArray, i, obj2, 0, i2);
        if (obj4 != null) {
            System.arraycopy(obj4, i, obj3, 0, i2);
        }
        while (i2 != interpreterData.itsMaxVars) {
            obj2[i2] = Undefined.instance;
            i2++;
        }
        if (obj != null) {
            for (i3 = i5 + 1; i3 != obj2.length; i3++) {
                obj2[i3] = null;
            }
        }
        enterFrame(context, callFrame2, argsArray, false);
    }

    private static boolean isFrameEnterExitRequired(CallFrame callFrame) {
        return callFrame.debuggerFrame != null || callFrame.idata.itsNeedsActivation;
    }

    private static void enterFrame(Context context, CallFrame callFrame, Object[] objArr, boolean z) {
        boolean z2 = callFrame.idata.itsNeedsActivation;
        Object obj = callFrame.debuggerFrame != null ? 1 : null;
        if (z2 || obj != null) {
            Scriptable scriptable = callFrame.scope;
            if (scriptable == null) {
                Kit.codeBug();
            } else if (z) {
                while (scriptable instanceof NativeWith) {
                    scriptable = scriptable.getParentScope();
                    if (scriptable == null || (callFrame.parentFrame != null && callFrame.parentFrame.scope == scriptable)) {
                        Kit.codeBug();
                        break;
                    }
                }
            }
            if (obj != null) {
                callFrame.debuggerFrame.onEnter(context, scriptable, callFrame.thisObj, objArr);
            }
            if (z2) {
                ScriptRuntime.enterActivationFunction(context, scriptable);
            }
        }
    }

    private static void exitFrame(Context context, CallFrame callFrame, Object obj) {
        if (callFrame.idata.itsNeedsActivation) {
            ScriptRuntime.exitActivationFunction(context);
        }
        if (callFrame.debuggerFrame != null) {
            try {
                if (obj instanceof Throwable) {
                    callFrame.debuggerFrame.onExit(context, true, obj);
                    return;
                }
                Object obj2;
                ContinuationJump continuationJump = (ContinuationJump) obj;
                if (continuationJump == null) {
                    obj2 = callFrame.result;
                } else {
                    obj2 = continuationJump.result;
                }
                if (obj2 == UniqueTag.DOUBLE_MARK) {
                    double d;
                    if (continuationJump == null) {
                        d = callFrame.resultDbl;
                    } else {
                        d = continuationJump.resultDbl;
                    }
                    obj2 = ScriptRuntime.wrapNumber(d);
                }
                callFrame.debuggerFrame.onExit(context, false, obj2);
            } catch (Throwable th) {
                System.err.println("RHINO USAGE WARNING: onExit terminated with exception");
                th.printStackTrace(System.err);
            }
        }
    }

    private static void setCallResult(CallFrame callFrame, Object obj, double d) {
        if (callFrame.savedCallOp == 38) {
            callFrame.stack[callFrame.savedStackTop] = obj;
            callFrame.sDbl[callFrame.savedStackTop] = d;
        } else if (callFrame.savedCallOp != 30) {
            Kit.codeBug();
        } else if (obj instanceof Scriptable) {
            callFrame.stack[callFrame.savedStackTop] = obj;
        }
        callFrame.savedCallOp = 0;
    }

    public static NativeContinuation captureContinuation(Context context) {
        if (context.lastInterpreterFrame != null && (context.lastInterpreterFrame instanceof CallFrame)) {
            return captureContinuation(context, (CallFrame) context.lastInterpreterFrame, true);
        }
        throw new IllegalStateException("Interpreter frames not found");
    }

    private static NativeContinuation captureContinuation(Context context, CallFrame callFrame, boolean z) {
        ScriptableObject nativeContinuation = new NativeContinuation();
        ScriptRuntime.setObjectProtoAndParent(nativeContinuation, ScriptRuntime.getTopCallScope(context));
        CallFrame callFrame2 = callFrame;
        CallFrame callFrame3 = callFrame;
        while (callFrame3 != null && !callFrame3.frozen) {
            callFrame3.frozen = true;
            for (int i = callFrame3.savedStackTop + 1; i != callFrame3.stack.length; i++) {
                callFrame3.stack[i] = null;
                callFrame3.stackAttributes[i] = 0;
            }
            if (callFrame3.savedCallOp == 38) {
                callFrame3.stack[callFrame3.savedStackTop] = null;
            } else if (callFrame3.savedCallOp != 30) {
                Kit.codeBug();
            }
            CallFrame callFrame4 = callFrame3;
            callFrame3 = callFrame3.parentFrame;
            callFrame2 = callFrame4;
        }
        if (z) {
            while (callFrame2.parentFrame != null) {
                callFrame2 = callFrame2.parentFrame;
            }
            if (!callFrame2.isContinuationsTopFrame) {
                throw new IllegalStateException("Cannot capture continuation from JavaScript code not called directly by executeScriptWithContinuations or callFunctionWithContinuations");
            }
        }
        nativeContinuation.initImplementation(callFrame);
        return nativeContinuation;
    }

    private static int stack_int32(CallFrame callFrame, int i) {
        UniqueTag uniqueTag = callFrame.stack[i];
        if (uniqueTag == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toInt32(callFrame.sDbl[i]);
        }
        return ScriptRuntime.toInt32(uniqueTag);
    }

    private static double stack_double(CallFrame callFrame, int i) {
        UniqueTag uniqueTag = callFrame.stack[i];
        if (uniqueTag != UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toNumber(uniqueTag);
        }
        return callFrame.sDbl[i];
    }

    private static boolean stack_boolean(CallFrame callFrame, int i) {
        Boolean bool = callFrame.stack[i];
        if (bool == Boolean.TRUE) {
            return true;
        }
        if (bool == Boolean.FALSE) {
            return false;
        }
        double d;
        if (bool == UniqueTag.DOUBLE_MARK) {
            d = callFrame.sDbl[i];
            boolean z = d == d && d != 0.0d;
            return z;
        } else if (bool == null || bool == Undefined.instance) {
            return false;
        } else {
            if (bool instanceof Number) {
                d = ((Number) bool).doubleValue();
                if (d != d || d == 0.0d) {
                    return false;
                }
                return true;
            } else if (bool instanceof Boolean) {
                return bool.booleanValue();
            } else {
                return ScriptRuntime.toBoolean(bool);
            }
        }
    }

    private static void doAdd(Object[] objArr, double[] dArr, int i, Context context) {
        double d;
        Object obj;
        UniqueTag uniqueTag = objArr[i + 1];
        UniqueTag uniqueTag2 = objArr[i];
        if (uniqueTag == UniqueTag.DOUBLE_MARK) {
            d = dArr[i + 1];
            if (uniqueTag2 == UniqueTag.DOUBLE_MARK) {
                dArr[i] = dArr[i] + d;
                return;
            }
            obj = 1;
        } else if (uniqueTag2 == UniqueTag.DOUBLE_MARK) {
            d = dArr[i];
            uniqueTag2 = uniqueTag;
            obj = null;
        } else if ((uniqueTag2 instanceof Scriptable) || (uniqueTag instanceof Scriptable)) {
            objArr[i] = ScriptRuntime.add(uniqueTag2, uniqueTag, context);
            return;
        } else if ((uniqueTag2 instanceof CharSequence) || (uniqueTag instanceof CharSequence)) {
            objArr[i] = new ConsString(ScriptRuntime.toCharSequence(uniqueTag2), ScriptRuntime.toCharSequence(uniqueTag));
            return;
        } else {
            d = uniqueTag2 instanceof Number ? ((Number) uniqueTag2).doubleValue() : ScriptRuntime.toNumber(uniqueTag2);
            double doubleValue = uniqueTag instanceof Number ? ((Number) uniqueTag).doubleValue() : ScriptRuntime.toNumber(uniqueTag);
            objArr[i] = UniqueTag.DOUBLE_MARK;
            dArr[i] = doubleValue + d;
            return;
        }
        Object obj2;
        if (obj2 instanceof Scriptable) {
            Object wrapNumber = ScriptRuntime.wrapNumber(d);
            if (obj == null) {
                Object obj3 = wrapNumber;
                wrapNumber = obj2;
                obj2 = obj3;
            }
            objArr[i] = ScriptRuntime.add(obj2, wrapNumber, context);
        } else if (obj2 instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj2;
            CharSequence toCharSequence = ScriptRuntime.toCharSequence(Double.valueOf(d));
            if (obj != null) {
                objArr[i] = new ConsString(charSequence, toCharSequence);
            } else {
                objArr[i] = new ConsString(toCharSequence, charSequence);
            }
        } else {
            doubleValue = obj2 instanceof Number ? ((Number) obj2).doubleValue() : ScriptRuntime.toNumber(obj2);
            objArr[i] = UniqueTag.DOUBLE_MARK;
            dArr[i] = doubleValue + d;
        }
    }

    private static int doArithmetic(CallFrame callFrame, int i, Object[] objArr, double[] dArr, int i2) {
        double stack_double = stack_double(callFrame, i2);
        int i3 = i2 - 1;
        double stack_double2 = stack_double(callFrame, i3);
        objArr[i3] = UniqueTag.DOUBLE_MARK;
        switch (i) {
            case 22:
                stack_double2 -= stack_double;
                break;
            case 23:
                stack_double2 *= stack_double;
                break;
            case 24:
                stack_double2 /= stack_double;
                break;
            case 25:
                stack_double2 %= stack_double;
                break;
        }
        dArr[i3] = stack_double2;
        return i3;
    }

    private static Object[] getArgsArray(Object[] objArr, double[] dArr, int i, int i2) {
        if (i2 == 0) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] objArr2 = new Object[i2];
        for (int i3 = 0; i3 != i2; i3++) {
            Number number = objArr[i];
            if (number == UniqueTag.DOUBLE_MARK) {
                number = ScriptRuntime.wrapNumber(dArr[i]);
            }
            objArr2[i3] = number;
            i++;
        }
        return objArr2;
    }

    private static void addInstructionCount(Context context, CallFrame callFrame, int i) {
        context.instructionCount += (callFrame.pc - callFrame.pcPrevBranch) + i;
        if (context.instructionCount > context.instructionThreshold) {
            context.observeInstructionCount(context.instructionCount);
            context.instructionCount = 0;
        }
    }
}
