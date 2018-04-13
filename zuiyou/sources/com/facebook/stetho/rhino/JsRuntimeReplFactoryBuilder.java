package com.facebook.stetho.rhino;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import com.umeng.analytics.b.g;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

public class JsRuntimeReplFactoryBuilder {
    private static final String SOURCE_NAME = "chrome";
    private final Set<Class<?>> mClasses = new HashSet();
    private final Context mContext;
    private final Map<String, Function> mFunctions = new HashMap();
    private final Set<String> mPackages = new HashSet();
    private final Map<String, Object> mVariables = new HashMap();

    private static class StethoJsException extends Exception {
        StethoJsException(Throwable th, String str, Object... objArr) {
            if (objArr.length != 0) {
                str = String.format(str, objArr);
            }
            super(str, th);
        }
    }

    public static RuntimeReplFactory defaultFactory(@NonNull Context context) {
        return new JsRuntimeReplFactoryBuilder(context).build();
    }

    public JsRuntimeReplFactoryBuilder(@NonNull Context context) {
        this.mContext = context;
        this.mPackages.add(context.getPackageName());
        this.mVariables.put("$_", org.mozilla.javascript.Context.getUndefinedValue());
    }

    @NonNull
    public JsRuntimeReplFactoryBuilder importClass(@NonNull Class<?> cls) {
        this.mClasses.add(cls);
        return this;
    }

    @NonNull
    public JsRuntimeReplFactoryBuilder importPackage(@NonNull String str) {
        this.mPackages.add(str);
        return this;
    }

    public JsRuntimeReplFactoryBuilder addVariable(@NonNull String str, Object obj) {
        this.mVariables.put(str, obj);
        return this;
    }

    @NonNull
    public JsRuntimeReplFactoryBuilder addFunction(@NonNull String str, @NonNull Function function) {
        this.mFunctions.put(str, function);
        return this;
    }

    public RuntimeReplFactory build() {
        return new RuntimeReplFactory() {
            public RuntimeRepl newInstance() {
                return new JsRuntimeRepl(JsRuntimeReplFactoryBuilder.this.initJsScope());
            }
        };
    }

    @NonNull
    private ScriptableObject initJsScope() {
        try {
            ScriptableObject initJsScope = initJsScope(JsRuntimeRepl.enterJsContext());
            return initJsScope;
        } finally {
            org.mozilla.javascript.Context.exit();
        }
    }

    @NonNull
    private ScriptableObject initJsScope(@NonNull org.mozilla.javascript.Context context) {
        ScriptableObject initStandardObjects = context.initStandardObjects(new ImporterTopLevel(context), false);
        ScriptableObject.putProperty(initStandardObjects, g.aI, org.mozilla.javascript.Context.javaToJS(this.mContext, initStandardObjects));
        try {
            importClasses(context, initStandardObjects);
            importPackages(context, initStandardObjects);
            importConsole(initStandardObjects);
            importVariables(initStandardObjects);
            importFunctions(initStandardObjects);
        } catch (Throwable e) {
            String format = String.format("%s\n%s", new Object[]{e.getMessage(), Log.getStackTraceString(e)});
            LogUtil.e(e, format);
            CLog.writeToConsole(MessageLevel.ERROR, MessageSource.JAVASCRIPT, format);
        }
        return initStandardObjects;
    }

    private void importClasses(@NonNull org.mozilla.javascript.Context context, @NonNull ScriptableObject scriptableObject) throws StethoJsException {
        for (Class name : this.mClasses) {
            String name2 = name.getName();
            org.mozilla.javascript.Context context2;
            ScriptableObject scriptableObject2;
            try {
                context2 = context;
                scriptableObject2 = scriptableObject;
                context2.evaluateString(scriptableObject2, String.format("importClass(%s)", new Object[]{name2}), SOURCE_NAME, 1, null);
            } catch (Exception e) {
                try {
                    context2 = context;
                    scriptableObject2 = scriptableObject;
                    context2.evaluateString(scriptableObject2, String.format("importClass(Packages.%s)", new Object[]{name2}), SOURCE_NAME, 1, null);
                } catch (Throwable e2) {
                    throw new StethoJsException(e2, "Failed to import class: %s", name2);
                }
            }
        }
    }

    private void importPackages(@NonNull org.mozilla.javascript.Context context, @NonNull ScriptableObject scriptableObject) throws StethoJsException {
        for (String str : this.mPackages) {
            org.mozilla.javascript.Context context2;
            ScriptableObject scriptableObject2;
            try {
                context2 = context;
                scriptableObject2 = scriptableObject;
                context2.evaluateString(scriptableObject2, String.format("importPackage(%s)", new Object[]{str}), SOURCE_NAME, 1, null);
            } catch (Throwable e) {
                Throwable th = e;
                try {
                    context2 = context;
                    scriptableObject2 = scriptableObject;
                    context2.evaluateString(scriptableObject2, String.format("importPackage(Packages.%s)", new Object[]{str}), SOURCE_NAME, 1, null);
                } catch (Exception e2) {
                    throw new StethoJsException(th, "Failed to import package: %s", str);
                }
            }
        }
    }

    private void importConsole(@NonNull ScriptableObject scriptableObject) throws StethoJsException {
        try {
            ScriptableObject.defineClass(scriptableObject, JsConsole.class);
            scriptableObject.defineProperty("console", new JsConsole(scriptableObject), 2);
        } catch (Throwable e) {
            throw new StethoJsException(e, "Failed to setup javascript console", new Object[0]);
        }
    }

    private void importVariables(@NonNull ScriptableObject scriptableObject) throws StethoJsException {
        for (Entry entry : this.mVariables.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            try {
                if (!((value instanceof Scriptable) || (value instanceof Undefined))) {
                    value = org.mozilla.javascript.Context.javaToJS(value, scriptableObject);
                }
                ScriptableObject.putProperty(scriptableObject, str, value);
            } catch (Throwable e) {
                throw new StethoJsException(e, "Failed to setup variable: %s", str);
            }
        }
    }

    private void importFunctions(@NonNull ScriptableObject scriptableObject) throws StethoJsException {
        for (Entry entry : this.mFunctions.entrySet()) {
            try {
                ScriptableObject.putProperty(scriptableObject, (String) entry.getKey(), (Function) entry.getValue());
            } catch (Throwable e) {
                throw new StethoJsException(e, "Failed to setup function: %s", r1);
            }
        }
    }
}
