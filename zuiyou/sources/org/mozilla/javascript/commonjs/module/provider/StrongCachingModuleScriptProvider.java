package org.mozilla.javascript.commonjs.module.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.provider.CachingModuleScriptProviderBase.CachedModuleScript;

public class StrongCachingModuleScriptProvider extends CachingModuleScriptProviderBase {
    private static final long serialVersionUID = 1;
    private final Map<String, CachedModuleScript> modules = new ConcurrentHashMap(16, 0.75f, CachingModuleScriptProviderBase.getConcurrencyLevel());

    public StrongCachingModuleScriptProvider(ModuleSourceProvider moduleSourceProvider) {
        super(moduleSourceProvider);
    }

    protected CachedModuleScript getLoadedModule(String str) {
        return (CachedModuleScript) this.modules.get(str);
    }

    protected void putLoadedModule(String str, ModuleScript moduleScript, Object obj) {
        this.modules.put(str, new CachedModuleScript(moduleScript, obj));
    }
}
