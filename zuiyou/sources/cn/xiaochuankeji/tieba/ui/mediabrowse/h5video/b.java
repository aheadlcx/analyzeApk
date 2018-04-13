package cn.xiaochuankeji.tieba.ui.mediabrowse.h5video;

import android.webkit.WebView;
import com.alibaba.fastjson.JSON;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private final WeakReference<WebView> a;
    private HashMap<String, a> b = new HashMap();

    private static class a {
        final String a;
        final c b;
        final Class c;

        a(String str, c cVar, Class cls) {
            this.a = str;
            this.b = cVar;
            this.c = cls;
        }
    }

    public b(WebView webView) {
        this.a = new WeakReference(webView);
    }

    public void a() {
        this.b.clear();
    }

    public void b() {
        a("window.jsBridge = {\n    queue: [],\n    callback: function() {\n        var d = Array.prototype.slice.call(arguments, 0);\n        var c = d.shift();\n        var e = d.shift();\n        this.queue[c].apply(this, d);\n        if (!e) {\n           delete this.queue[c];\n        }\n    }\n};\nwindow.jsBridge.callNative = function() {\n    var args = Array.prototype.slice.call(arguments, 0);\n    if (args.length != 2) {\n        throw 'only support one argument';\n    }\n    var e = [];\n    for (var h = 1; h < args.length; h++) {\n        var c = args[h];\n        var j = typeof c;\n        e[e.length] = j;\n        if (j == 'function') {\n            var d = a.queue.length;\n            a.queue[d] = c;\n            args[h] = d;\n        }\n    }\n    prompt(JSON.stringify({ method: args.shift(), data: args.shift() }));\n};");
    }

    public boolean a(String str, c cVar, Class cls) {
        if (this.b.containsKey(str)) {
            return false;
        }
        WebView webView = (WebView) this.a.get();
        if (webView == null) {
            return false;
        }
        webView.loadUrl("javascript:" + String.format("if(window.jsBridge.%s === undefined) {   window.jsBridge.%s = function() {\n       return window.jsBridge.callNative.apply(\n                   window.jsBridge.callNative,\n                   ['%s'].concat(Array.prototype.slice.call(arguments, 0)))\n  }\n};", new Object[]{str, str, str}));
        this.b.put(str, new a(str, cVar, cls));
        return true;
    }

    public void a(String str) {
        WebView webView = (WebView) this.a.get();
        if (webView != null) {
            webView.loadUrl("javascript:" + str);
        }
    }

    public boolean b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(PushConstants.MZ_PUSH_MESSAGE_METHOD);
            if (this.b.containsKey(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                a aVar = (a) this.b.get(optString);
                aVar.b.a(JSON.parseObject(optJSONObject.toString(), aVar.c));
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
