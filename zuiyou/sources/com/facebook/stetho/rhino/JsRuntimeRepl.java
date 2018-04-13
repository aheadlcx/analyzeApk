package com.facebook.stetho.rhino;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.stetho.inspector.console.RuntimeRepl;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

class JsRuntimeRepl implements RuntimeRepl {
    @NonNull
    private final ScriptableObject mJsScope;

    JsRuntimeRepl(@NonNull ScriptableObject scriptableObject) {
        this.mJsScope = scriptableObject;
    }

    @Nullable
    public Object evaluate(@NonNull String str) throws Throwable {
        try {
            Object evaluateString = enterJsContext().evaluateString(this.mJsScope, str, "chrome", 1, null);
            ScriptableObject.putProperty(this.mJsScope, "$_", Context.javaToJS(evaluateString, this.mJsScope));
            return Context.jsToJava(evaluateString, Object.class);
        } finally {
            Context.exit();
        }
    }

    @NonNull
    static Context enterJsContext() {
        Context enter = Context.enter();
        try {
            enter.setLanguageVersion(180);
            enter.setOptimizationLevel(-1);
            return enter;
        } catch (RuntimeException e) {
            Context.exit();
            throw e;
        }
    }
}
