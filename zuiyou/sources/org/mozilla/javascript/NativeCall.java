package org.mozilla.javascript;

public final class NativeCall extends IdScriptableObject {
    private static final Object CALL_TAG = "Call";
    private static final int Id_constructor = 1;
    private static final int MAX_PROTOTYPE_ID = 1;
    static final long serialVersionUID = -7471457301304454454L;
    NativeFunction function;
    Object[] originalArgs;
    transient NativeCall parentActivationCall;

    static void init(Scriptable scriptable, boolean z) {
        new NativeCall().exportAsJSClass(1, scriptable, z);
    }

    NativeCall() {
    }

    NativeCall(NativeFunction nativeFunction, Scriptable scriptable, Object[] objArr) {
        this.function = nativeFunction;
        setParentScope(scriptable);
        this.originalArgs = objArr == null ? ScriptRuntime.emptyArgs : objArr;
        int paramAndVarCount = nativeFunction.getParamAndVarCount();
        int paramCount = nativeFunction.getParamCount();
        if (paramAndVarCount != 0) {
            int i = 0;
            while (i < paramCount) {
                defineProperty(nativeFunction.getParamOrVarName(i), i < objArr.length ? objArr[i] : Undefined.instance, 4);
                i++;
            }
        }
        if (!super.has("arguments", this)) {
            defineProperty("arguments", new Arguments(this), 4);
        }
        if (paramAndVarCount != 0) {
            for (int i2 = paramCount; i2 < paramAndVarCount; i2++) {
                String paramOrVarName = nativeFunction.getParamOrVarName(i2);
                if (!super.has(paramOrVarName, this)) {
                    if (nativeFunction.getParamOrVarConst(i2)) {
                        defineProperty(paramOrVarName, Undefined.instance, 13);
                    } else {
                        defineProperty(paramOrVarName, Undefined.instance, 4);
                    }
                }
            }
        }
    }

    public String getClassName() {
        return "Call";
    }

    protected int findPrototypeId(String str) {
        return str.equals("constructor") ? 1 : 0;
    }

    protected void initPrototypeId(int i) {
        if (i == 1) {
            initPrototypeMethod(CALL_TAG, i, "constructor", 1);
            return;
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(CALL_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId != 1) {
            throw new IllegalArgumentException(String.valueOf(methodId));
        } else if (scriptable2 != null) {
            throw Context.reportRuntimeError1("msg.only.from.new", "Call");
        } else {
            ScriptRuntime.checkDeprecated(context, "Call");
            Object nativeCall = new NativeCall();
            nativeCall.setPrototype(getObjectPrototype(scriptable));
            return nativeCall;
        }
    }
}
