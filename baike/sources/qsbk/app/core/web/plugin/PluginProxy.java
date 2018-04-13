package qsbk.app.core.web.plugin;

import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import qsbk.app.core.web.ui.WebInterface;

public class PluginProxy {
    private WebInterface a;
    private WebView b;
    private Map<String, Class<? extends Plugin>> c;
    private Map<String, Plugin> d = new HashMap();

    public PluginProxy(WebInterface webInterface, WebView webView, Map<String, Class<? extends Plugin>> map) {
        this.a = webInterface;
        this.b = webView;
        this.c = map;
        a();
    }

    private void a() {
        try {
            for (String str : this.c.keySet()) {
                a(str, (Plugin) ((Class) this.c.get(str)).newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, Plugin plugin) {
        if (!this.d.containsKey(str)) {
            this.d.put(str, plugin);
        }
    }

    public void exec(String str, String str2, String str3, String str4) {
        Plugin plugin = (Plugin) this.d.get(str);
        Callback callback = new Callback(this.b, str3);
        if (plugin == null) {
            callback.sendResult(1, "model:" + str + " is not exist...", "");
            return;
        }
        plugin.setContext(this.a);
        try {
            plugin.exec(str2, str4, callback);
        } catch (JSONException e) {
            callback.sendResult(1, e.getMessage(), "");
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        for (Plugin onDestroy : this.d.values()) {
            onDestroy.onDestroy();
        }
    }
}
