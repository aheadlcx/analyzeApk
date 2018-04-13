package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

public abstract class NativeFunction extends BaseFunction {
    static final long serialVersionUID = 8713897114082216401L;

    protected abstract int getLanguageVersion();

    protected abstract int getParamAndVarCount();

    protected abstract int getParamCount();

    protected abstract String getParamOrVarName(int i);

    public final void initScriptFunction(Context context, Scriptable scriptable) {
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
    }

    final String decompile(int i, int i2) {
        String encodedSource = getEncodedSource();
        if (encodedSource == null) {
            return super.decompile(i, i2);
        }
        UintMap uintMap = new UintMap(1);
        uintMap.put(1, i);
        return Decompiler.decompile(encodedSource, i2, uintMap);
    }

    public int getLength() {
        int paramCount = getParamCount();
        if (getLanguageVersion() != 120) {
            return paramCount;
        }
        NativeCall findFunctionActivation = ScriptRuntime.findFunctionActivation(Context.getContext(), this);
        return findFunctionActivation != null ? findFunctionActivation.originalArgs.length : paramCount;
    }

    public int getArity() {
        return getParamCount();
    }

    @Deprecated
    public String jsGet_name() {
        return getFunctionName();
    }

    public String getEncodedSource() {
        return null;
    }

    public DebuggableScript getDebuggableView() {
        return null;
    }

    public Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        throw new EvaluatorException("resumeGenerator() not implemented");
    }

    protected boolean getParamOrVarConst(int i) {
        return false;
    }
}
