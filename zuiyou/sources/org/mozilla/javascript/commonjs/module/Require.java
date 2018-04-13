package org.mozilla.javascript.commonjs.module;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Require extends BaseFunction {
    private static final ThreadLocal<Map<String, Scriptable>> loadingModuleInterfaces = new ThreadLocal();
    private static final long serialVersionUID = 1;
    private final Map<String, Scriptable> exportedModuleInterfaces = new ConcurrentHashMap();
    private final Object loadLock = new Object();
    private Scriptable mainExports;
    private String mainModuleId = null;
    private final ModuleScriptProvider moduleScriptProvider;
    private final Scriptable nativeScope;
    private final Scriptable paths;
    private final Script postExec;
    private final Script preExec;
    private final boolean sandboxed;

    public Require(Context context, Scriptable scriptable, ModuleScriptProvider moduleScriptProvider, Script script, Script script2, boolean z) {
        this.moduleScriptProvider = moduleScriptProvider;
        this.nativeScope = scriptable;
        this.sandboxed = z;
        this.preExec = script;
        this.postExec = script2;
        setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
        if (z) {
            this.paths = null;
            return;
        }
        this.paths = context.newArray(scriptable, 0);
        defineReadOnlyProperty(this, "paths", this.paths);
    }

    public Scriptable requireMain(Context context, String str) {
        if (this.mainModuleId == null) {
            try {
                if (this.moduleScriptProvider.getModuleScript(context, str, null, null, this.paths) != null) {
                    this.mainExports = getExportedModuleInterface(context, str, null, null, true);
                } else if (!this.sandboxed) {
                    URI uri;
                    try {
                        uri = new URI(str);
                    } catch (URISyntaxException e) {
                        uri = null;
                    }
                    if (uri == null || !uri.isAbsolute()) {
                        File file = new File(str);
                        if (file.isFile()) {
                            uri = file.toURI();
                        } else {
                            throw ScriptRuntime.throwError(context, this.nativeScope, "Module \"" + str + "\" not found.");
                        }
                    }
                    this.mainExports = getExportedModuleInterface(context, uri.toString(), uri, null, true);
                }
                this.mainModuleId = str;
                return this.mainExports;
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Throwable e3) {
                throw new RuntimeException(e3);
            }
        } else if (this.mainModuleId.equals(str)) {
            return this.mainExports;
        } else {
            throw new IllegalStateException("Main module already set to " + this.mainModuleId);
        }
    }

    public void install(Scriptable scriptable) {
        ScriptableObject.putProperty(scriptable, "require", this);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        URI uri = null;
        if (objArr == null || objArr.length < 1) {
            throw ScriptRuntime.throwError(context, scriptable, "require() needs one argument");
        }
        URI uri2;
        String str;
        String str2 = (String) Context.jsToJava(objArr[0], String.class);
        if (!str2.startsWith("./") && !str2.startsWith("../")) {
            uri2 = null;
            str = str2;
        } else if (scriptable2 instanceof ModuleScope) {
            ModuleScope moduleScope = (ModuleScope) scriptable2;
            uri = moduleScope.getBase();
            URI uri3 = moduleScope.getUri();
            uri2 = uri3.resolve(str2);
            if (uri == null) {
                str = uri2.toString();
            } else {
                str = uri.relativize(uri3).resolve(str2).toString();
                if (str.charAt(0) == '.') {
                    if (this.sandboxed) {
                        throw ScriptRuntime.throwError(context, scriptable, "Module \"" + str + "\" is not contained in sandbox.");
                    }
                    str = uri2.toString();
                }
            }
        } else {
            throw ScriptRuntime.throwError(context, scriptable, "Can't resolve relative module ID \"" + str2 + "\" when require() is used outside of a module");
        }
        return getExportedModuleInterface(context, str, uri2, uri, false);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.throwError(context, scriptable, "require() can not be invoked as a constructor");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.Scriptable getExportedModuleInterface(org.mozilla.javascript.Context r10, java.lang.String r11, java.net.URI r12, java.net.URI r13, boolean r14) {
        /*
        r9 = this;
        r0 = r9.exportedModuleInterfaces;
        r0 = r0.get(r11);
        r0 = (org.mozilla.javascript.Scriptable) r0;
        if (r0 == 0) goto L_0x0017;
    L_0x000a:
        if (r14 == 0) goto L_0x0015;
    L_0x000c:
        r0 = new java.lang.IllegalStateException;
        r1 = "Attempt to set main module after it was loaded";
        r0.<init>(r1);
        throw r0;
    L_0x0015:
        r1 = r0;
    L_0x0016:
        return r1;
    L_0x0017:
        r0 = loadingModuleInterfaces;
        r0 = r0.get();
        r0 = (java.util.Map) r0;
        if (r0 == 0) goto L_0x0029;
    L_0x0021:
        r1 = r0.get(r11);
        r1 = (org.mozilla.javascript.Scriptable) r1;
        if (r1 != 0) goto L_0x0016;
    L_0x0029:
        r8 = r9.loadLock;
        monitor-enter(r8);
        r1 = r9.exportedModuleInterfaces;	 Catch:{ all -> 0x0038 }
        r1 = r1.get(r11);	 Catch:{ all -> 0x0038 }
        r1 = (org.mozilla.javascript.Scriptable) r1;	 Catch:{ all -> 0x0038 }
        if (r1 == 0) goto L_0x003b;
    L_0x0036:
        monitor-exit(r8);	 Catch:{ all -> 0x0038 }
        goto L_0x0016;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0038 }
        throw r0;
    L_0x003b:
        r4 = r9.getModule(r10, r11, r12, r13);	 Catch:{ all -> 0x0038 }
        r1 = r9.sandboxed;	 Catch:{ all -> 0x0038 }
        if (r1 == 0) goto L_0x006b;
    L_0x0043:
        r1 = r4.isSandboxed();	 Catch:{ all -> 0x0038 }
        if (r1 != 0) goto L_0x006b;
    L_0x0049:
        r0 = r9.nativeScope;	 Catch:{ all -> 0x0038 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0038 }
        r1.<init>();	 Catch:{ all -> 0x0038 }
        r2 = "Module \"";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0038 }
        r1 = r1.append(r11);	 Catch:{ all -> 0x0038 }
        r2 = "\" is not contained in sandbox.";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0038 }
        r1 = r1.toString();	 Catch:{ all -> 0x0038 }
        r0 = org.mozilla.javascript.ScriptRuntime.throwError(r10, r0, r1);	 Catch:{ all -> 0x0038 }
        throw r0;	 Catch:{ all -> 0x0038 }
    L_0x006b:
        r1 = r9.nativeScope;	 Catch:{ all -> 0x0038 }
        r3 = r10.newObject(r1);	 Catch:{ all -> 0x0038 }
        if (r0 != 0) goto L_0x00a2;
    L_0x0073:
        r1 = 1;
        r7 = r1;
    L_0x0075:
        if (r7 == 0) goto L_0x00bb;
    L_0x0077:
        r0 = new java.util.HashMap;	 Catch:{ all -> 0x0038 }
        r0.<init>();	 Catch:{ all -> 0x0038 }
        r1 = loadingModuleInterfaces;	 Catch:{ all -> 0x0038 }
        r1.set(r0);	 Catch:{ all -> 0x0038 }
        r6 = r0;
    L_0x0082:
        r6.put(r11, r3);	 Catch:{ all -> 0x0038 }
        r0 = r9;
        r1 = r10;
        r2 = r11;
        r5 = r14;
        r1 = r0.executeModuleScript(r1, r2, r3, r4, r5);	 Catch:{ RuntimeException -> 0x00a5 }
        if (r3 == r1) goto L_0x00b9;
    L_0x008f:
        r6.put(r11, r1);	 Catch:{ RuntimeException -> 0x00a5 }
    L_0x0092:
        if (r7 == 0) goto L_0x009f;
    L_0x0094:
        r0 = r9.exportedModuleInterfaces;	 Catch:{ all -> 0x0038 }
        r0.putAll(r6);	 Catch:{ all -> 0x0038 }
        r0 = loadingModuleInterfaces;	 Catch:{ all -> 0x0038 }
        r2 = 0;
        r0.set(r2);	 Catch:{ all -> 0x0038 }
    L_0x009f:
        monitor-exit(r8);	 Catch:{ all -> 0x0038 }
        goto L_0x0016;
    L_0x00a2:
        r1 = 0;
        r7 = r1;
        goto L_0x0075;
    L_0x00a5:
        r0 = move-exception;
        r6.remove(r11);	 Catch:{ all -> 0x00aa }
        throw r0;	 Catch:{ all -> 0x00aa }
    L_0x00aa:
        r0 = move-exception;
        if (r7 == 0) goto L_0x00b8;
    L_0x00ad:
        r1 = r9.exportedModuleInterfaces;	 Catch:{ all -> 0x0038 }
        r1.putAll(r6);	 Catch:{ all -> 0x0038 }
        r1 = loadingModuleInterfaces;	 Catch:{ all -> 0x0038 }
        r2 = 0;
        r1.set(r2);	 Catch:{ all -> 0x0038 }
    L_0x00b8:
        throw r0;	 Catch:{ all -> 0x0038 }
    L_0x00b9:
        r1 = r3;
        goto L_0x0092;
    L_0x00bb:
        r6 = r0;
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.commonjs.module.Require.getExportedModuleInterface(org.mozilla.javascript.Context, java.lang.String, java.net.URI, java.net.URI, boolean):org.mozilla.javascript.Scriptable");
    }

    private Scriptable executeModuleScript(Context context, String str, Scriptable scriptable, ModuleScript moduleScript, boolean z) {
        Object obj = (ScriptableObject) context.newObject(this.nativeScope);
        URI uri = moduleScript.getUri();
        URI base = moduleScript.getBase();
        defineReadOnlyProperty(obj, "id", str);
        if (!this.sandboxed) {
            defineReadOnlyProperty(obj, "uri", uri.toString());
        }
        Scriptable moduleScope = new ModuleScope(this.nativeScope, uri, base);
        moduleScope.put("exports", moduleScope, (Object) scriptable);
        moduleScope.put("module", moduleScope, obj);
        obj.put("exports", obj, scriptable);
        install(moduleScope);
        if (z) {
            defineReadOnlyProperty(this, "main", obj);
        }
        executeOptionalScript(this.preExec, context, moduleScope);
        moduleScript.getScript().exec(context, moduleScope);
        executeOptionalScript(this.postExec, context, moduleScope);
        return ScriptRuntime.toObject(context, this.nativeScope, ScriptableObject.getProperty(obj, "exports"));
    }

    private static void executeOptionalScript(Script script, Context context, Scriptable scriptable) {
        if (script != null) {
            script.exec(context, scriptable);
        }
    }

    private static void defineReadOnlyProperty(ScriptableObject scriptableObject, String str, Object obj) {
        ScriptableObject.putProperty(scriptableObject, str, obj);
        scriptableObject.setAttributes(str, 5);
    }

    private ModuleScript getModule(Context context, String str, URI uri, URI uri2) {
        try {
            ModuleScript moduleScript = this.moduleScriptProvider.getModuleScript(context, str, uri, uri2, this.paths);
            if (moduleScript != null) {
                return moduleScript;
            }
            throw ScriptRuntime.throwError(context, this.nativeScope, "Module \"" + str + "\" not found.");
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw Context.throwAsScriptRuntimeEx(e2);
        }
    }

    public String getFunctionName() {
        return "require";
    }

    public int getArity() {
        return 1;
    }

    public int getLength() {
        return 1;
    }
}
