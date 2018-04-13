package org.mozilla.javascript;

class ScriptRuntime$1 extends BaseFunction {
    static final long serialVersionUID = -5891740962154902286L;

    ScriptRuntime$1() {
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        throw ScriptRuntime.typeError0("msg.op.not.allowed");
    }

    public int getLength() {
        return 0;
    }
}
