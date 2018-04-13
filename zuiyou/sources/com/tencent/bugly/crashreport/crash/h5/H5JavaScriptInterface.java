package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import com.tencent.bugly.crashreport.CrashReport.WebViewInterface;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.umeng.analytics.b.g;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

public class H5JavaScriptInterface {
    private static HashSet<Integer> a = new HashSet();
    private String b = null;
    private Thread c = null;
    private String d = null;
    private Map<String, String> e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(WebViewInterface webViewInterface) {
        if (webViewInterface == null || a.contains(Integer.valueOf(webViewInterface.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        a.add(Integer.valueOf(webViewInterface.hashCode()));
        h5JavaScriptInterface.c = Thread.currentThread();
        h5JavaScriptInterface.d = a(h5JavaScriptInterface.c);
        h5JavaScriptInterface.e = a(webViewInterface);
        return h5JavaScriptInterface;
    }

    private static String a(Thread thread) {
        if (thread == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 2; i < thread.getStackTrace().length; i++) {
            StackTraceElement stackTraceElement = thread.getStackTrace()[i];
            if (!stackTraceElement.toString().contains("crashreport")) {
                stringBuilder.append(stackTraceElement.toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static Map<String, String> a(WebViewInterface webViewInterface) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("[WebView] ContentDescription", "" + webViewInterface.getContentDescription());
        return hashMap;
    }

    private a a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar = new a();
            aVar.a = jSONObject.getString("projectRoot");
            if (aVar.a == null) {
                return null;
            }
            aVar.b = jSONObject.getString(g.aI);
            if (aVar.b == null) {
                return null;
            }
            aVar.c = jSONObject.getString("url");
            if (aVar.c == null) {
                return null;
            }
            aVar.d = jSONObject.getString("userAgent");
            if (aVar.d == null) {
                return null;
            }
            aVar.e = jSONObject.getString("language");
            if (aVar.e == null) {
                return null;
            }
            aVar.f = jSONObject.getString("name");
            if (aVar.f == null || aVar.f.equals("null")) {
                return null;
            }
            String string = jSONObject.getString("stacktrace");
            if (string == null) {
                return null;
            }
            int indexOf = string.indexOf("\n");
            if (indexOf < 0) {
                an.d("H5 crash stack's format is wrong!", new Object[0]);
                return null;
            }
            aVar.h = string.substring(indexOf + 1);
            aVar.g = string.substring(0, indexOf);
            int indexOf2 = aVar.g.indexOf(":");
            if (indexOf2 > 0) {
                aVar.g = aVar.g.substring(indexOf2 + 1);
            }
            aVar.i = jSONObject.getString("file");
            if (aVar.f == null) {
                return null;
            }
            aVar.j = jSONObject.getLong("lineNumber");
            if (aVar.j < 0) {
                return null;
            }
            aVar.k = jSONObject.getLong("columnNumber");
            if (aVar.k < 0) {
                return null;
            }
            an.a("H5 crash information is following: ", new Object[0]);
            an.a("[projectRoot]: " + aVar.a, new Object[0]);
            an.a("[context]: " + aVar.b, new Object[0]);
            an.a("[url]: " + aVar.c, new Object[0]);
            an.a("[userAgent]: " + aVar.d, new Object[0]);
            an.a("[language]: " + aVar.e, new Object[0]);
            an.a("[name]: " + aVar.f, new Object[0]);
            an.a("[message]: " + aVar.g, new Object[0]);
            an.a("[stacktrace]: \n" + aVar.h, new Object[0]);
            an.a("[file]: " + aVar.i, new Object[0]);
            an.a("[lineNumber]: " + aVar.j, new Object[0]);
            an.a("[columnNumber]: " + aVar.k, new Object[0]);
            return aVar;
        } catch (Throwable th) {
            if (an.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static void a(a aVar, Thread thread, Map<String, String> map) {
        if (aVar != null) {
            InnerApi.postH5CrashAsync(thread, aVar.f, aVar.g, aVar.h, map);
        }
    }

    @JavascriptInterface
    public void printLog(String str) {
        an.d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            an.d("Payload from JS is null.", new Object[0]);
            return;
        }
        String b = ap.b(str.getBytes());
        if (this.b == null || !this.b.equals(b)) {
            this.b = b;
            an.d("Handling JS exception ...", new Object[0]);
            a a = a(str);
            if (a == null) {
                an.d("Failed to parse payload.", new Object[0]);
                return;
            }
            Map linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(a.a());
            linkedHashMap.putAll(this.e);
            linkedHashMap.put("Java Stack", this.d);
            a(a, this.c, linkedHashMap);
            return;
        }
        an.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
    }
}
