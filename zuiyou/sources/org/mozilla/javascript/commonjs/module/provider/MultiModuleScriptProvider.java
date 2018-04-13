package org.mozilla.javascript.commonjs.module.provider;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;

public class MultiModuleScriptProvider implements ModuleScriptProvider {
    private final ModuleScriptProvider[] providers;

    public MultiModuleScriptProvider(Iterable<? extends ModuleScriptProvider> iterable) {
        List linkedList = new LinkedList();
        for (ModuleScriptProvider add : iterable) {
            linkedList.add(add);
        }
        this.providers = (ModuleScriptProvider[]) linkedList.toArray(new ModuleScriptProvider[linkedList.size()]);
    }

    public ModuleScript getModuleScript(Context context, String str, URI uri, URI uri2, Scriptable scriptable) throws Exception {
        for (ModuleScriptProvider moduleScript : this.providers) {
            ModuleScript moduleScript2 = moduleScript.getModuleScript(context, str, uri, uri2, scriptable);
            if (moduleScript2 != null) {
                return moduleScript2;
            }
        }
        return null;
    }
}
