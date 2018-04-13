package org.mozilla.javascript;

public class NativeCallSite extends IdScriptableObject {
    private static final String CALLSITE_TAG = "CallSite";
    private static final int Id_constructor = 1;
    private static final int Id_getColumnNumber = 9;
    private static final int Id_getEvalOrigin = 10;
    private static final int Id_getFileName = 7;
    private static final int Id_getFunction = 4;
    private static final int Id_getFunctionName = 5;
    private static final int Id_getLineNumber = 8;
    private static final int Id_getMethodName = 6;
    private static final int Id_getThis = 2;
    private static final int Id_getTypeName = 3;
    private static final int Id_isConstructor = 14;
    private static final int Id_isEval = 12;
    private static final int Id_isNative = 13;
    private static final int Id_isToplevel = 11;
    private static final int Id_toString = 15;
    private static final int MAX_PROTOTYPE_ID = 15;
    private ScriptStackElement element;

    static void init(Scriptable scriptable, boolean z) {
        new NativeCallSite().exportAsJSClass(15, scriptable, z);
    }

    static NativeCallSite make(Scriptable scriptable, Scriptable scriptable2) {
        NativeCallSite nativeCallSite = new NativeCallSite();
        Scriptable scriptable3 = (Scriptable) scriptable2.get("prototype", scriptable2);
        nativeCallSite.setParentScope(scriptable);
        nativeCallSite.setPrototype(scriptable3);
        return nativeCallSite;
    }

    private NativeCallSite() {
    }

    void setElement(ScriptStackElement scriptStackElement) {
        this.element = scriptStackElement;
    }

    public String getClassName() {
        return CALLSITE_TAG;
    }

    protected void initPrototypeId(int i) {
        String str;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "getThis";
                break;
            case 3:
                str = "getTypeName";
                break;
            case 4:
                str = "getFunction";
                break;
            case 5:
                str = "getFunctionName";
                break;
            case 6:
                str = "getMethodName";
                break;
            case 7:
                str = "getFileName";
                break;
            case 8:
                str = "getLineNumber";
                break;
            case 9:
                str = "getColumnNumber";
                break;
            case 10:
                str = "getEvalOrigin";
                break;
            case 11:
                str = "isToplevel";
                break;
            case 12:
                str = "isEval";
                break;
            case 13:
                str = "isNative";
                break;
            case 14:
                str = "isConstructor";
                break;
            case 15:
                str = "toString";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(CALLSITE_TAG, i, str, 0);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(CALLSITE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return make(scriptable, idFunctionObject);
            case 2:
            case 3:
            case 4:
            case 9:
                return getUndefined();
            case 5:
                return getFunctionName(scriptable2);
            case 6:
                return getNull();
            case 7:
                return getFileName(scriptable2);
            case 8:
                return getLineNumber(scriptable2);
            case 10:
            case 12:
            case 14:
                return getFalse();
            case 15:
                return js_toString(scriptable2);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    public String toString() {
        if (this.element == null) {
            return "";
        }
        return this.element.toString();
    }

    private Object js_toString(Scriptable scriptable) {
        Scriptable scriptable2 = scriptable;
        while (scriptable2 != null && !(scriptable2 instanceof NativeCallSite)) {
            scriptable2 = scriptable2.getPrototype();
        }
        if (scriptable2 == null) {
            return NOT_FOUND;
        }
        NativeCallSite nativeCallSite = (NativeCallSite) scriptable2;
        StringBuilder stringBuilder = new StringBuilder();
        nativeCallSite.element.renderJavaStyle(stringBuilder);
        return stringBuilder.toString();
    }

    private Object getUndefined() {
        return Undefined.instance;
    }

    private Object getNull() {
        return null;
    }

    private Object getFalse() {
        return Boolean.FALSE;
    }

    private Object getFunctionName(Scriptable scriptable) {
        Scriptable scriptable2 = scriptable;
        while (scriptable2 != null && !(scriptable2 instanceof NativeCallSite)) {
            scriptable2 = scriptable2.getPrototype();
        }
        if (scriptable2 == null) {
            return NOT_FOUND;
        }
        NativeCallSite nativeCallSite = (NativeCallSite) scriptable2;
        return nativeCallSite.element == null ? null : nativeCallSite.element.functionName;
    }

    private Object getFileName(Scriptable scriptable) {
        Scriptable scriptable2 = scriptable;
        while (scriptable2 != null && !(scriptable2 instanceof NativeCallSite)) {
            scriptable2 = scriptable2.getPrototype();
        }
        if (scriptable2 == null) {
            return NOT_FOUND;
        }
        NativeCallSite nativeCallSite = (NativeCallSite) scriptable2;
        return nativeCallSite.element == null ? null : nativeCallSite.element.fileName;
    }

    private Object getLineNumber(Scriptable scriptable) {
        Scriptable scriptable2 = scriptable;
        while (scriptable2 != null && !(scriptable2 instanceof NativeCallSite)) {
            scriptable2 = scriptable2.getPrototype();
        }
        if (scriptable2 == null) {
            return NOT_FOUND;
        }
        NativeCallSite nativeCallSite = (NativeCallSite) scriptable2;
        if (nativeCallSite.element == null || nativeCallSite.element.lineNumber < 0) {
            return Undefined.instance;
        }
        return Integer.valueOf(nativeCallSite.element.lineNumber);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r7) {
        /*
        r6 = this;
        r2 = 4;
        r3 = 3;
        r0 = 0;
        r1 = 0;
        r4 = r7.length();
        switch(r4) {
            case 6: goto L_0x0018;
            case 7: goto L_0x0021;
            case 8: goto L_0x0029;
            case 9: goto L_0x000b;
            case 10: goto L_0x0047;
            case 11: goto L_0x0050;
            case 12: goto L_0x000b;
            case 13: goto L_0x0077;
            case 14: goto L_0x000b;
            case 15: goto L_0x00a7;
            default: goto L_0x000b;
        };
    L_0x000b:
        r2 = r1;
        r1 = r0;
    L_0x000d:
        if (r2 == 0) goto L_0x00c6;
    L_0x000f:
        if (r2 == r7) goto L_0x00c6;
    L_0x0011:
        r2 = r2.equals(r7);
        if (r2 != 0) goto L_0x00c6;
    L_0x0017:
        return r0;
    L_0x0018:
        r1 = "isEval";
        r2 = 12;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x0021:
        r1 = "getThis";
        r2 = 2;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x0029:
        r2 = r7.charAt(r0);
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r2 != r3) goto L_0x003a;
    L_0x0031:
        r1 = "isNative";
        r2 = 13;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x003a:
        r3 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r2 != r3) goto L_0x000b;
    L_0x003e:
        r1 = "toString";
        r2 = 15;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x0047:
        r1 = "isToplevel";
        r2 = 11;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x0050:
        r4 = r7.charAt(r2);
        switch(r4) {
            case 105: goto L_0x005a;
            case 116: goto L_0x0062;
            case 117: goto L_0x006a;
            case 121: goto L_0x0071;
            default: goto L_0x0057;
        };
    L_0x0057:
        r2 = r1;
        r1 = r0;
        goto L_0x000d;
    L_0x005a:
        r1 = "getFileName";
        r2 = 7;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x0062:
        r1 = "constructor";
        r2 = 1;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x006a:
        r1 = "getFunction";
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x0071:
        r1 = "getTypeName";
        r2 = r1;
        r1 = r3;
        goto L_0x000d;
    L_0x0077:
        r2 = r7.charAt(r3);
        switch(r2) {
            case 69: goto L_0x0081;
            case 76: goto L_0x008a;
            case 77: goto L_0x0094;
            case 111: goto L_0x009d;
            default: goto L_0x007e;
        };
    L_0x007e:
        r2 = r1;
        r1 = r0;
        goto L_0x000d;
    L_0x0081:
        r1 = "getEvalOrigin";
        r2 = 10;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x008a:
        r1 = "getLineNumber";
        r2 = 8;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x0094:
        r1 = "getMethodName";
        r2 = 6;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x009d:
        r1 = "isConstructor";
        r2 = 14;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x00a7:
        r2 = r7.charAt(r3);
        r3 = 67;
        if (r2 != r3) goto L_0x00b9;
    L_0x00af:
        r1 = "getColumnNumber";
        r2 = 9;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x00b9:
        r3 = 70;
        if (r2 != r3) goto L_0x000b;
    L_0x00bd:
        r1 = "getFunctionName";
        r2 = 5;
        r5 = r1;
        r1 = r2;
        r2 = r5;
        goto L_0x000d;
    L_0x00c6:
        r0 = r1;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeCallSite.findPrototypeId(java.lang.String):int");
    }
}
