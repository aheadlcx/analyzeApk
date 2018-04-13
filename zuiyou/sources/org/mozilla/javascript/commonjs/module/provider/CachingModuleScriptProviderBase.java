package org.mozilla.javascript.commonjs.module.provider;

import java.io.Serializable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;

public abstract class CachingModuleScriptProviderBase implements Serializable, ModuleScriptProvider {
    private static final int loadConcurrencyLevel = (Runtime.getRuntime().availableProcessors() * 8);
    private static final int loadLockCount;
    private static final int loadLockMask;
    private static final int loadLockShift;
    private static final long serialVersionUID = 1;
    private final Object[] loadLocks = new Object[loadLockCount];
    private final ModuleSourceProvider moduleSourceProvider;

    public static class CachedModuleScript {
        private final ModuleScript moduleScript;
        private final Object validator;

        public CachedModuleScript(ModuleScript moduleScript, Object obj) {
            this.moduleScript = moduleScript;
            this.validator = obj;
        }

        ModuleScript getModule() {
            return this.moduleScript;
        }

        Object getValidator() {
            return this.validator;
        }
    }

    protected abstract CachedModuleScript getLoadedModule(String str);

    protected abstract void putLoadedModule(String str, ModuleScript moduleScript, Object obj);

    static {
        int i = 0;
        int i2 = 1;
        while (i2 < loadConcurrencyLevel) {
            i++;
            i2 <<= 1;
        }
        loadLockShift = 32 - i;
        loadLockMask = i2 - 1;
        loadLockCount = i2;
    }

    protected CachingModuleScriptProviderBase(ModuleSourceProvider moduleSourceProvider) {
        for (int i = 0; i < this.loadLocks.length; i++) {
            this.loadLocks[i] = new Object();
        }
        this.moduleSourceProvider = moduleSourceProvider;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.commonjs.module.ModuleScript getModuleScript(org.mozilla.javascript.Context r9, java.lang.String r10, java.net.URI r11, java.net.URI r12, org.mozilla.javascript.Scriptable r13) throws java.lang.Exception {
        /*
        r8 = this;
        r1 = r8.getLoadedModule(r10);
        r2 = getValidator(r1);
        if (r11 != 0) goto L_0x0019;
    L_0x000a:
        r0 = r8.moduleSourceProvider;
        r0 = r0.loadSource(r10, r13, r2);
    L_0x0010:
        r3 = org.mozilla.javascript.commonjs.module.provider.ModuleSourceProvider.NOT_MODIFIED;
        if (r0 != r3) goto L_0x0020;
    L_0x0014:
        r0 = r1.getModule();
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r8.moduleSourceProvider;
        r0 = r0.loadSource(r11, r12, r2);
        goto L_0x0010;
    L_0x0020:
        if (r0 != 0) goto L_0x0024;
    L_0x0022:
        r0 = 0;
        goto L_0x0018;
    L_0x0024:
        r3 = r0.getReader();
        r1 = r10.hashCode();	 Catch:{ all -> 0x007a }
        r4 = r8.loadLocks;	 Catch:{ all -> 0x007a }
        r5 = loadLockShift;	 Catch:{ all -> 0x007a }
        r1 = r1 >>> r5;
        r5 = loadLockMask;	 Catch:{ all -> 0x007a }
        r1 = r1 & r5;
        r4 = r4[r1];	 Catch:{ all -> 0x007a }
        monitor-enter(r4);	 Catch:{ all -> 0x007a }
        r1 = r8.getLoadedModule(r10);	 Catch:{ all -> 0x0077 }
        if (r1 == 0) goto L_0x0050;
    L_0x003d:
        r5 = getValidator(r1);	 Catch:{ all -> 0x0077 }
        r2 = equal(r2, r5);	 Catch:{ all -> 0x0077 }
        if (r2 != 0) goto L_0x0050;
    L_0x0047:
        r0 = r1.getModule();	 Catch:{ all -> 0x0077 }
        monitor-exit(r4);	 Catch:{ all -> 0x0077 }
        r3.close();
        goto L_0x0018;
    L_0x0050:
        r2 = r0.getUri();	 Catch:{ all -> 0x0077 }
        r1 = new org.mozilla.javascript.commonjs.module.ModuleScript;	 Catch:{ all -> 0x0077 }
        r5 = r2.toString();	 Catch:{ all -> 0x0077 }
        r6 = 1;
        r7 = r0.getSecurityDomain();	 Catch:{ all -> 0x0077 }
        r5 = r9.compileReader(r3, r5, r6, r7);	 Catch:{ all -> 0x0077 }
        r6 = r0.getBase();	 Catch:{ all -> 0x0077 }
        r1.<init>(r5, r2, r6);	 Catch:{ all -> 0x0077 }
        r0 = r0.getValidator();	 Catch:{ all -> 0x0077 }
        r8.putLoadedModule(r10, r1, r0);	 Catch:{ all -> 0x0077 }
        monitor-exit(r4);	 Catch:{ all -> 0x0077 }
        r3.close();
        r0 = r1;
        goto L_0x0018;
    L_0x0077:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0077 }
        throw r0;	 Catch:{ all -> 0x007a }
    L_0x007a:
        r0 = move-exception;
        r3.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.commonjs.module.provider.CachingModuleScriptProviderBase.getModuleScript(org.mozilla.javascript.Context, java.lang.String, java.net.URI, java.net.URI, org.mozilla.javascript.Scriptable):org.mozilla.javascript.commonjs.module.ModuleScript");
    }

    private static Object getValidator(CachedModuleScript cachedModuleScript) {
        return cachedModuleScript == null ? null : cachedModuleScript.getValidator();
    }

    private static boolean equal(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    protected static int getConcurrencyLevel() {
        return loadLockCount;
    }
}
