package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

final class InterpretedFunction extends NativeFunction implements Script {
    static final long serialVersionUID = 541475680333911468L;
    InterpreterData idata;
    SecurityController securityController;
    Object securityDomain;

    private InterpretedFunction(InterpreterData interpreterData, Object obj) {
        Object dynamicSecurityDomain;
        this.idata = interpreterData;
        SecurityController securityController = Context.getContext().getSecurityController();
        if (securityController != null) {
            dynamicSecurityDomain = securityController.getDynamicSecurityDomain(obj);
        } else if (obj != null) {
            throw new IllegalArgumentException();
        } else {
            dynamicSecurityDomain = null;
        }
        this.securityController = securityController;
        this.securityDomain = dynamicSecurityDomain;
    }

    private InterpretedFunction(InterpretedFunction interpretedFunction, int i) {
        this.idata = interpretedFunction.idata.itsNestedFunctions[i];
        this.securityController = interpretedFunction.securityController;
        this.securityDomain = interpretedFunction.securityDomain;
    }

    static InterpretedFunction createScript(InterpreterData interpreterData, Object obj) {
        return new InterpretedFunction(interpreterData, obj);
    }

    static InterpretedFunction createFunction(Context context, Scriptable scriptable, InterpreterData interpreterData, Object obj) {
        InterpretedFunction interpretedFunction = new InterpretedFunction(interpreterData, obj);
        interpretedFunction.initScriptFunction(context, scriptable);
        return interpretedFunction;
    }

    static InterpretedFunction createFunction(Context context, Scriptable scriptable, InterpretedFunction interpretedFunction, int i) {
        InterpretedFunction interpretedFunction2 = new InterpretedFunction(interpretedFunction, i);
        interpretedFunction2.initScriptFunction(context, scriptable);
        return interpretedFunction2;
    }

    public String getFunctionName() {
        return this.idata.itsName == null ? "" : this.idata.itsName;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (ScriptRuntime.hasTopCall(context)) {
            return Interpreter.interpret(this, context, scriptable, scriptable2, objArr);
        }
        return ScriptRuntime.doTopCall(this, context, scriptable, scriptable2, objArr);
    }

    public Object exec(Context context, Scriptable scriptable) {
        if (!isScript()) {
            throw new IllegalStateException();
        } else if (ScriptRuntime.hasTopCall(context)) {
            return Interpreter.interpret(this, context, scriptable, scriptable, ScriptRuntime.emptyArgs);
        } else {
            return ScriptRuntime.doTopCall(this, context, scriptable, scriptable, ScriptRuntime.emptyArgs);
        }
    }

    public boolean isScript() {
        return this.idata.itsFunctionType == 0;
    }

    public String getEncodedSource() {
        return Interpreter.getEncodedSource(this.idata);
    }

    public DebuggableScript getDebuggableView() {
        return this.idata;
    }

    public Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        return Interpreter.resumeGenerator(context, scriptable, i, obj, obj2);
    }

    protected int getLanguageVersion() {
        return this.idata.languageVersion;
    }

    protected int getParamCount() {
        return this.idata.argCount;
    }

    protected int getParamAndVarCount() {
        return this.idata.argNames.length;
    }

    protected String getParamOrVarName(int i) {
        return this.idata.argNames[i];
    }

    protected boolean getParamOrVarConst(int i) {
        return this.idata.argIsConst[i];
    }
}
