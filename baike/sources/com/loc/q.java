package com.loc;

import android.content.Context;
import android.database.Cursor;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import qsbk.app.utils.HttpUtils;

public final class q {
    private static String a() {
        String defaultHost;
        try {
            defaultHost = Proxy.getDefaultHost();
        } catch (Throwable th) {
            w.a(th, "ProxyUtil", "getDefHost");
            defaultHost = null;
        }
        return defaultHost == null ? "null" : defaultHost;
    }

    public static java.net.Proxy a(Context context) {
        try {
            return VERSION.SDK_INT >= 11 ? a(context, new URI("http://restapi.amap.com")) : b(context);
        } catch (Throwable th) {
            w.a(th, "ProxyUtil", "getProxy");
            return null;
        }
    }

    private static java.net.Proxy a(Context context, URI uri) {
        if (c(context)) {
            try {
                List select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    return null;
                }
                java.net.Proxy proxy = (java.net.Proxy) select.get(0);
                return (proxy == null || proxy.type() == Type.DIRECT) ? null : proxy;
            } catch (Throwable th) {
                w.a(th, "ProxyUtil", "getProxySelectorCfg");
            }
        }
        return null;
    }

    private static int b() {
        int i = -1;
        try {
            i = Proxy.getDefaultPort();
        } catch (Throwable th) {
            w.a(th, "ProxyUtil", "getDefPort");
        }
        return i;
    }

    private static java.net.Proxy b(Context context) {
        Cursor query;
        String string;
        String a;
        int b;
        Throwable th;
        Cursor cursor;
        int i;
        Throwable th2;
        String toLowerCase;
        String a2;
        Object obj;
        Cursor cursor2;
        int i2;
        if (c(context)) {
            int i3;
            Object obj2;
            try {
                query = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            string = query.getString(query.getColumnIndex("apn"));
                            if (string != null) {
                                string = string.toLowerCase(Locale.US);
                            }
                            if (string != null && string.contains("ctwap")) {
                                a = a();
                                b = b();
                                try {
                                    Object obj3;
                                    if (TextUtils.isEmpty(a) || a.equals("null")) {
                                        obj3 = null;
                                        a = null;
                                    } else {
                                        obj3 = 1;
                                    }
                                    if (obj3 == null) {
                                        try {
                                            a = t.c("QMTAuMC4wLjIwMA==");
                                        } catch (Throwable e) {
                                            th = e;
                                            cursor = query;
                                            i = b;
                                            th2 = th;
                                            w.a(th2, "ProxyUtil", "getHostProxy");
                                            string = n.o(context);
                                            if (string == null) {
                                                toLowerCase = string.toLowerCase(Locale.US);
                                                a2 = a();
                                                b = b();
                                                if (toLowerCase.indexOf("ctwap") != -1) {
                                                    if (!TextUtils.isEmpty(a2)) {
                                                    }
                                                    obj = null;
                                                    if (obj == null) {
                                                        a = t.c("QMTAuMC4wLjIwMA==");
                                                    }
                                                    if (b == -1) {
                                                        b = 80;
                                                    }
                                                } else if (toLowerCase.indexOf(HttpUtils.WAP) != -1) {
                                                    if (!TextUtils.isEmpty(a2)) {
                                                    }
                                                    obj = null;
                                                    string = a;
                                                    if (obj == null) {
                                                        string = t.c("QMTAuMC4wLjE3Mg==");
                                                    }
                                                    a = string;
                                                    b = 80;
                                                }
                                            } else {
                                                b = i;
                                            }
                                            if (cursor == null) {
                                                cursor.close();
                                                i3 = b;
                                            } else {
                                                i3 = b;
                                            }
                                            if (a != null) {
                                                obj2 = 1;
                                                if (obj2 != null) {
                                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                                }
                                                return null;
                                            }
                                            obj2 = null;
                                            if (obj2 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                            }
                                            return null;
                                        } catch (Throwable e2) {
                                            th = e2;
                                            i3 = b;
                                            th2 = th;
                                            w.a(th2, "ProxyUtil", "getHostProxy1");
                                            th2.printStackTrace();
                                            if (query != null) {
                                                query.close();
                                            }
                                            if (a != null) {
                                                obj2 = 1;
                                                if (obj2 != null) {
                                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                                }
                                                return null;
                                            }
                                            obj2 = null;
                                            if (obj2 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                            }
                                            return null;
                                        }
                                    }
                                    if (b == -1) {
                                        b = 80;
                                    }
                                    i3 = b;
                                    if (query != null) {
                                        query.close();
                                    }
                                } catch (Throwable e22) {
                                    a = null;
                                    cursor2 = query;
                                    i = b;
                                    th2 = e22;
                                    cursor = cursor2;
                                    w.a(th2, "ProxyUtil", "getHostProxy");
                                    string = n.o(context);
                                    if (string == null) {
                                        b = i;
                                    } else {
                                        toLowerCase = string.toLowerCase(Locale.US);
                                        a2 = a();
                                        b = b();
                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                            if (TextUtils.isEmpty(a2)) {
                                            }
                                            obj = null;
                                            if (obj == null) {
                                                a = t.c("QMTAuMC4wLjIwMA==");
                                            }
                                            if (b == -1) {
                                                b = 80;
                                            }
                                        } else if (toLowerCase.indexOf(HttpUtils.WAP) != -1) {
                                            if (TextUtils.isEmpty(a2)) {
                                            }
                                            obj = null;
                                            string = a;
                                            if (obj == null) {
                                                string = t.c("QMTAuMC4wLjE3Mg==");
                                            }
                                            a = string;
                                            b = 80;
                                        }
                                    }
                                    if (cursor == null) {
                                        i3 = b;
                                    } else {
                                        cursor.close();
                                        i3 = b;
                                    }
                                    if (a != null) {
                                        obj2 = 1;
                                        if (obj2 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                        }
                                        return null;
                                    }
                                    obj2 = null;
                                    if (obj2 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                    }
                                    return null;
                                } catch (Throwable e222) {
                                    a = null;
                                    i2 = b;
                                    th2 = e222;
                                    i3 = i2;
                                    w.a(th2, "ProxyUtil", "getHostProxy1");
                                    th2.printStackTrace();
                                    if (query != null) {
                                        query.close();
                                    }
                                    if (a != null) {
                                        obj2 = 1;
                                        if (obj2 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                        }
                                        return null;
                                    }
                                    obj2 = null;
                                    if (obj2 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                    }
                                    return null;
                                }
                                if (a != null) {
                                    obj2 = 1;
                                    if (obj2 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                    }
                                }
                                obj2 = null;
                                if (obj2 != null) {
                                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                }
                            } else if (string != null) {
                                if (string.contains(HttpUtils.WAP)) {
                                    a = a();
                                    i3 = b();
                                    try {
                                        if (TextUtils.isEmpty(a) || a.equals("null")) {
                                            obj2 = null;
                                            a = null;
                                        } else {
                                            obj2 = 1;
                                        }
                                        if (obj2 == null) {
                                            try {
                                                a = t.c("QMTAuMC4wLjE3Mg==");
                                            } catch (SecurityException e3) {
                                                th2 = e3;
                                                cursor2 = query;
                                                i = i3;
                                                cursor = cursor2;
                                                try {
                                                    w.a(th2, "ProxyUtil", "getHostProxy");
                                                    string = n.o(context);
                                                    if (string == null) {
                                                        b = i;
                                                    } else {
                                                        toLowerCase = string.toLowerCase(Locale.US);
                                                        a2 = a();
                                                        b = b();
                                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                                            if (TextUtils.isEmpty(a2)) {
                                                            }
                                                            obj = null;
                                                            if (obj == null) {
                                                                a = t.c("QMTAuMC4wLjIwMA==");
                                                            }
                                                            if (b == -1) {
                                                                b = 80;
                                                            }
                                                        } else if (toLowerCase.indexOf(HttpUtils.WAP) != -1) {
                                                            if (TextUtils.isEmpty(a2)) {
                                                            }
                                                            obj = null;
                                                            string = a;
                                                            if (obj == null) {
                                                                string = t.c("QMTAuMC4wLjE3Mg==");
                                                            }
                                                            a = string;
                                                            b = 80;
                                                        }
                                                    }
                                                    if (cursor == null) {
                                                        i3 = b;
                                                    } else {
                                                        try {
                                                            cursor.close();
                                                            i3 = b;
                                                        } catch (Throwable e2222) {
                                                            w.a(e2222, "ProxyUtil", "getHostProxy2");
                                                            e2222.printStackTrace();
                                                            i3 = b;
                                                        }
                                                    }
                                                    if (a != null) {
                                                        try {
                                                            obj2 = 1;
                                                            if (obj2 != null) {
                                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                                            }
                                                        } catch (Throwable th22) {
                                                            w.a(th22, "ProxyUtil", "getHostProxy2");
                                                            th22.printStackTrace();
                                                        }
                                                        return null;
                                                    }
                                                    obj2 = null;
                                                    if (obj2 != null) {
                                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                                    }
                                                    return null;
                                                } catch (Throwable th3) {
                                                    th22 = th3;
                                                    query = cursor;
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable e22222) {
                                                            w.a(e22222, "ProxyUtil", "getHostProxy2");
                                                            e22222.printStackTrace();
                                                        }
                                                    }
                                                    throw th22;
                                                }
                                            } catch (Throwable th4) {
                                                th22 = th4;
                                                try {
                                                    w.a(th22, "ProxyUtil", "getHostProxy1");
                                                    th22.printStackTrace();
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable th222) {
                                                            w.a(th222, "ProxyUtil", "getHostProxy2");
                                                            th222.printStackTrace();
                                                        }
                                                    }
                                                    if (a != null) {
                                                        obj2 = 1;
                                                        if (obj2 != null) {
                                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                                        }
                                                        return null;
                                                    }
                                                    obj2 = null;
                                                    if (obj2 != null) {
                                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                                    }
                                                    return null;
                                                } catch (Throwable th5) {
                                                    th222 = th5;
                                                    if (query != null) {
                                                        query.close();
                                                    }
                                                    throw th222;
                                                }
                                            }
                                        }
                                        if (i3 == -1) {
                                            i3 = 80;
                                        }
                                        if (query != null) {
                                            try {
                                                query.close();
                                            } catch (Throwable th2222) {
                                                w.a(th2222, "ProxyUtil", "getHostProxy2");
                                                th2222.printStackTrace();
                                            }
                                        }
                                    } catch (SecurityException e4) {
                                        th2222 = e4;
                                        a = null;
                                        i2 = i3;
                                        cursor = query;
                                        i = i2;
                                        w.a(th2222, "ProxyUtil", "getHostProxy");
                                        string = n.o(context);
                                        if (string == null) {
                                            toLowerCase = string.toLowerCase(Locale.US);
                                            a2 = a();
                                            b = b();
                                            if (toLowerCase.indexOf("ctwap") != -1) {
                                                if (TextUtils.isEmpty(a2) || a2.equals("null")) {
                                                    obj = null;
                                                } else {
                                                    a = a2;
                                                    obj = 1;
                                                }
                                                if (obj == null) {
                                                    a = t.c("QMTAuMC4wLjIwMA==");
                                                }
                                                if (b == -1) {
                                                    b = 80;
                                                }
                                            } else if (toLowerCase.indexOf(HttpUtils.WAP) != -1) {
                                                if (TextUtils.isEmpty(a2) || a2.equals("null")) {
                                                    obj = null;
                                                    string = a;
                                                } else {
                                                    string = a2;
                                                    obj = 1;
                                                }
                                                if (obj == null) {
                                                    string = t.c("QMTAuMC4wLjE3Mg==");
                                                }
                                                a = string;
                                                b = 80;
                                            }
                                        } else {
                                            b = i;
                                        }
                                        if (cursor == null) {
                                            cursor.close();
                                            i3 = b;
                                        } else {
                                            i3 = b;
                                        }
                                        if (a != null) {
                                            obj2 = 1;
                                            if (obj2 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                            }
                                            return null;
                                        }
                                        obj2 = null;
                                        if (obj2 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                        }
                                        return null;
                                    } catch (Throwable th6) {
                                        th2222 = th6;
                                        a = null;
                                        w.a(th2222, "ProxyUtil", "getHostProxy1");
                                        th2222.printStackTrace();
                                        if (query != null) {
                                            query.close();
                                        }
                                        if (a != null) {
                                            obj2 = 1;
                                            if (obj2 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                            }
                                            return null;
                                        }
                                        obj2 = null;
                                        if (obj2 != null) {
                                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                        }
                                        return null;
                                    }
                                    if (a != null) {
                                        if (a.length() > 0 && i3 != -1) {
                                            obj2 = 1;
                                            if (obj2 != null) {
                                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                            }
                                        }
                                    }
                                    obj2 = null;
                                    if (obj2 != null) {
                                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                                    }
                                }
                            }
                        }
                    } catch (SecurityException e5) {
                        th2222 = e5;
                        cursor = query;
                        a = null;
                        i = -1;
                        w.a(th2222, "ProxyUtil", "getHostProxy");
                        string = n.o(context);
                        if (string == null) {
                            toLowerCase = string.toLowerCase(Locale.US);
                            a2 = a();
                            b = b();
                            if (toLowerCase.indexOf("ctwap") != -1) {
                                if (TextUtils.isEmpty(a2)) {
                                }
                                obj = null;
                                if (obj == null) {
                                    a = t.c("QMTAuMC4wLjIwMA==");
                                }
                                if (b == -1) {
                                    b = 80;
                                }
                            } else if (toLowerCase.indexOf(HttpUtils.WAP) != -1) {
                                if (TextUtils.isEmpty(a2)) {
                                }
                                obj = null;
                                string = a;
                                if (obj == null) {
                                    string = t.c("QMTAuMC4wLjE3Mg==");
                                }
                                a = string;
                                b = 80;
                            }
                        } else {
                            b = i;
                        }
                        if (cursor == null) {
                            cursor.close();
                            i3 = b;
                        } else {
                            i3 = b;
                        }
                        if (a != null) {
                            obj2 = 1;
                            if (obj2 != null) {
                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                            }
                            return null;
                        }
                        obj2 = null;
                        if (obj2 != null) {
                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                        }
                        return null;
                    } catch (Throwable th7) {
                        th2222 = th7;
                        i3 = -1;
                        a = null;
                        w.a(th2222, "ProxyUtil", "getHostProxy1");
                        th2222.printStackTrace();
                        if (query != null) {
                            query.close();
                        }
                        if (a != null) {
                            obj2 = 1;
                            if (obj2 != null) {
                                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                            }
                            return null;
                        }
                        obj2 = null;
                        if (obj2 != null) {
                            return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                        }
                        return null;
                    }
                }
                i3 = -1;
                a = null;
                if (query != null) {
                    query.close();
                }
            } catch (SecurityException e6) {
                th2222 = e6;
                cursor = null;
                i = -1;
                a = null;
                w.a(th2222, "ProxyUtil", "getHostProxy");
                string = n.o(context);
                if (string == null) {
                    b = i;
                } else {
                    toLowerCase = string.toLowerCase(Locale.US);
                    a2 = a();
                    b = b();
                    if (toLowerCase.indexOf("ctwap") != -1) {
                        if (TextUtils.isEmpty(a2)) {
                        }
                        obj = null;
                        if (obj == null) {
                            a = t.c("QMTAuMC4wLjIwMA==");
                        }
                        if (b == -1) {
                            b = 80;
                        }
                    } else if (toLowerCase.indexOf(HttpUtils.WAP) != -1) {
                        if (TextUtils.isEmpty(a2)) {
                        }
                        obj = null;
                        string = a;
                        if (obj == null) {
                            string = t.c("QMTAuMC4wLjE3Mg==");
                        }
                        a = string;
                        b = 80;
                    }
                }
                if (cursor == null) {
                    i3 = b;
                } else {
                    cursor.close();
                    i3 = b;
                }
                if (a != null) {
                    obj2 = 1;
                    if (obj2 != null) {
                        return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                    }
                    return null;
                }
                obj2 = null;
                if (obj2 != null) {
                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                }
                return null;
            } catch (Throwable th8) {
                th2222 = th8;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th2222;
            }
            if (a != null) {
                obj2 = 1;
                if (obj2 != null) {
                    return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
                }
            }
            obj2 = null;
            if (obj2 != null) {
                return new java.net.Proxy(Type.HTTP, InetSocketAddress.createUnresolved(a, i3));
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return n.m(context) == 0;
    }
}
