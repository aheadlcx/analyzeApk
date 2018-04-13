package qsbk.app.core.web;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.plugin.embed.AccountPlugin;
import qsbk.app.core.web.plugin.embed.ChargePlugin;
import qsbk.app.core.web.plugin.embed.JumpPlugin;
import qsbk.app.core.web.plugin.embed.LivePlugin;
import qsbk.app.core.web.plugin.embed.SharePlugin;
import qsbk.app.core.web.plugin.embed.WebPlugin;

public class NativeJsPluginManager {
    private static NativeJsPluginManager b;
    private Map<String, Class<? extends Plugin>> a = new HashMap();

    private NativeJsPluginManager() {
        registerPlugin("account", AccountPlugin.class);
        registerPlugin("charge", ChargePlugin.class);
        registerPlugin("share", SharePlugin.class);
        registerPlugin("live", LivePlugin.class);
        registerPlugin(WebPlugin.MODEL, WebPlugin.class);
        registerPlugin("jump", JumpPlugin.class);
    }

    public static synchronized NativeJsPluginManager getInstance() {
        NativeJsPluginManager nativeJsPluginManager;
        synchronized (NativeJsPluginManager.class) {
            if (b == null) {
                b = new NativeJsPluginManager();
            }
            nativeJsPluginManager = b;
        }
        return nativeJsPluginManager;
    }

    public void registerPlugin(String str, Class<? extends Plugin> cls) {
        this.a.put(str, cls);
    }

    public void registerPlugin(Map<String, Class<? extends Plugin>> map) {
        this.a.putAll(map);
    }

    public void unregisterPlugin(String str) {
        this.a.remove(str);
    }

    public Map<String, Class<? extends Plugin>> getPluginMap() {
        return this.a;
    }

    public void clear() {
        this.a.clear();
    }
}
