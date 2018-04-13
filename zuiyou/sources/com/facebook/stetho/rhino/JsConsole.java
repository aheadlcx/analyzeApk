package com.facebook.stetho.rhino;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class JsConsole extends ScriptableObject {
    private static final long serialVersionUID = 1;

    public JsConsole(ScriptableObject scriptableObject) {
        setParentScope(scriptableObject);
        Object topLevelProp = ScriptRuntime.getTopLevelProp(scriptableObject, "Console");
        if (topLevelProp != null && (topLevelProp instanceof Scriptable)) {
            Scriptable scriptable = (Scriptable) topLevelProp;
            setPrototype((Scriptable) scriptable.get("prototype", scriptable));
        }
    }

    public String getClassName() {
        return "Console";
    }

    @JSFunction
    public static void log(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        log(objArr);
    }

    private static void log(Object[] objArr) {
        CLog.writeToConsole(MessageLevel.LOG, MessageSource.JAVASCRIPT, JsFormat.parse(objArr));
    }
}
