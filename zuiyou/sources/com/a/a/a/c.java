package com.a.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"SetJavaScriptEnabled"})
public class c extends WebView {
    Map<String, e> a = new HashMap();
    Map<String, a> b = new HashMap();
    a c = new f();
    private final String d = "BridgeWebView";
    private List<g> e = new ArrayList();
    private long f = 0;

    public List<g> getStartupMessage() {
        return this.e;
    }

    public void setStartupMessage(List<g> list) {
        this.e = list;
    }

    public c(Context context) {
        super(context);
    }

    public void setDefaultHandler(a aVar) {
        this.c = aVar;
    }

    void a(String str) {
        Log.e("handlerReturnData", str);
        String c = b.c(str);
        e eVar = (e) this.a.get(c);
        String b = b.b(str);
        if (eVar != null) {
            eVar.a(b);
            this.a.remove(c);
        }
    }

    private void b(String str, String str2, e eVar) {
        g gVar = new g();
        if (!TextUtils.isEmpty(str2)) {
            gVar.d(str2);
        }
        if (eVar != null) {
            Object[] objArr = new Object[1];
            StringBuilder stringBuilder = new StringBuilder();
            long j = this.f + 1;
            this.f = j;
            objArr[0] = stringBuilder.append(j).append("_").append(SystemClock.currentThreadTimeMillis()).toString();
            String format = String.format("JAVA_CB_%s", objArr);
            this.a.put(format, eVar);
            gVar.c(format);
        }
        if (!TextUtils.isEmpty(str)) {
            gVar.e(str);
        }
        b(gVar);
    }

    private void b(g gVar) {
        if (this.e != null) {
            this.e.add(gVar);
        } else {
            a(gVar);
        }
    }

    void a(g gVar) {
        Log.i("dispatchMessage:before", gVar.f().replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2").replaceAll("(?<=[^\\\\])(\")", "\\\\\"").replaceAll("'", "\\\\'"));
        String format = String.format("javascript:ZuiyouJSBridge._handleMessageFromNative('%s');", new Object[]{format});
        Log.i("dispatchMessage:after", format);
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(format);
        }
    }

    void a() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            a("javascript:ZuiyouJSBridge._fetchQueue();", new e(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    try {
                        List f = g.f(str);
                        if (f != null && f.size() != 0) {
                            for (int i = 0; i < f.size(); i++) {
                                g gVar = (g) f.get(i);
                                CharSequence a = gVar.a();
                                if (TextUtils.isEmpty(a)) {
                                    e anonymousClass1;
                                    a aVar;
                                    final Object c = gVar.c();
                                    if (TextUtils.isEmpty(c)) {
                                        c = new e(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void a(String str) {
                                            }
                                        };
                                    } else {
                                        anonymousClass1 = new e(this) {
                                            final /* synthetic */ AnonymousClass1 b;

                                            public void a(String str) {
                                                g gVar = new g();
                                                gVar.a(c);
                                                gVar.b(str);
                                                this.b.a.b(gVar);
                                            }
                                        };
                                    }
                                    if (TextUtils.isEmpty(gVar.e())) {
                                        aVar = this.a.c;
                                    } else {
                                        aVar = (a) this.a.b.get(gVar.e());
                                    }
                                    if (aVar != null) {
                                        aVar.a(gVar.d(), anonymousClass1);
                                    }
                                } else {
                                    ((e) this.a.a.get(a)).a(gVar.b());
                                    this.a.a.remove(a);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(String str, e eVar) {
        loadUrl(str);
        this.a.put(b.a(str), eVar);
    }

    public void a(String str, a aVar) {
        if (aVar != null) {
            this.b.put(str, aVar);
        }
    }

    public void a(String str, String str2, e eVar) {
        b(str, str2, eVar);
    }
}
