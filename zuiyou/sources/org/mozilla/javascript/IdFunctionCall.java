package org.mozilla.javascript;

public interface IdFunctionCall {
    Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr);
}
