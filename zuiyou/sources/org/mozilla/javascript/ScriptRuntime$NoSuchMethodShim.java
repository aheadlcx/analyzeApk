package org.mozilla.javascript;

class ScriptRuntime$NoSuchMethodShim implements Callable {
    String methodName;
    Callable noSuchMethodMethod;

    ScriptRuntime$NoSuchMethodShim(Callable callable, String str) {
        this.noSuchMethodMethod = callable;
        this.methodName = str;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return this.noSuchMethodMethod.call(context, scriptable, scriptable2, new Object[]{this.methodName, ScriptRuntime.newArrayLiteral(objArr, null, context, scriptable)});
    }
}
