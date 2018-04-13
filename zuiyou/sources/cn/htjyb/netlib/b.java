package cn.htjyb.netlib;

import android.content.Context;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import java.io.File;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import okhttp3.internal.connection.RouteException;
import org.json.JSONObject;

public abstract class b {
    private static final byte[] b = new byte[]{(byte) -1, (byte) -40, (byte) -1};
    private static final byte[] c = new byte[]{(byte) -119, (byte) 80, (byte) 78, (byte) 71};
    private static final byte[] d = new byte[]{(byte) 71, (byte) 73, (byte) 70, (byte) 56};
    private static final byte[] e = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 32, (byte) 102, (byte) 116, (byte) 121, (byte) 112};
    private static final byte[] f = new byte[]{(byte) 82, (byte) 73, (byte) 70, (byte) 70};
    private static b g;
    protected volatile cn.htjyb.netlib.a.a a;

    public static class a {
        public boolean a;
        public int b;
        public JSONObject c;
        public String d;
        public long e;
        protected int f;
        protected JSONObject g;
        private String h;
        private Throwable i;
        private String j;
        private String k;

        public a() {
            a();
        }

        public void a() {
            this.a = false;
            this.b = 0;
            this.c = null;
            this.d = null;
            this.e = 0;
            this.h = null;
            this.i = null;
            this.j = null;
            this.f = 0;
            this.k = "";
            this.g = new JSONObject();
            this.c = new JSONObject();
        }

        public void a(boolean z) {
            if (this.h == null) {
                if (z) {
                    d();
                } else {
                    this.a = b();
                }
                if (this.a) {
                    if (z) {
                        this.c = this.g.optJSONObject("data");
                        this.j = this.g.optString("msg");
                        if (this.c == null) {
                            this.c = new JSONObject();
                        }
                    }
                } else if (!NetworkMonitor.a()) {
                    this.b = 1001;
                    this.h = "网络不给力哦~";
                } else if (b()) {
                    this.b = this.g.optInt("ret");
                    this.h = e();
                } else {
                    this.b = this.f;
                    this.h = b(z);
                }
            }
        }

        private void d() {
            int i = 0;
            if (this.g != null) {
                i = this.g.optInt("ret");
            }
            if (i == 1) {
                this.a = true;
            }
        }

        private String b(boolean z) {
            if (401 == this.f) {
                return "服务器认证失败";
            }
            if (400 == this.f) {
                return "请求参数错误";
            }
            if (404 == this.f) {
                return z ? "请求服务不存在" : "请求文件不存在";
            } else {
                return "服务器处理失败，错误码: " + this.f;
            }
        }

        private String e() {
            return this.g.optString("msg");
        }

        protected void a(Throwable th) {
            com.izuiyou.a.a.b.d(th.toString());
            if (NetworkMonitor.a()) {
                String simpleName = th.getClass().getSimpleName();
                if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException) || (th instanceof RouteException)) {
                    simpleName = "网络连接超时";
                } else if ((th instanceof SocketException) || (th instanceof InterruptedException)) {
                    simpleName = "网络异常: " + simpleName;
                }
                if (simpleName.trim().toLowerCase().contains("filenotfound")) {
                    simpleName = simpleName + ":" + th.getMessage();
                }
                if (th instanceof UnknownHostException) {
                    this.b = 1002;
                } else {
                    this.b = 1000;
                }
                if (TextUtils.isEmpty(simpleName)) {
                    simpleName = "未知异常";
                }
                this.h = simpleName;
                this.i = th;
                return;
            }
            this.b = 1001;
            this.h = "网络不给力哦~";
        }

        public void a(String str) {
            this.h = str;
        }

        public boolean b() {
            return 2 == this.f / 100;
        }

        public String c() {
            return this.h;
        }
    }

    public static class b {
        public final File a;
        public final String b;

        public b(File file, String str) {
            this.a = file;
            this.b = str;
        }
    }

    public abstract a a(d dVar, String str, String str2);

    public abstract a a(d dVar, String str, Collection<b> collection, JSONObject jSONObject);

    public abstract a a(d dVar, String str, Collection<b> collection, JSONObject jSONObject, cn.htjyb.netlib.g.a aVar, int i);

    public abstract a a(d dVar, String str, HashMap<String, String> hashMap, String str2, JSONObject jSONObject, boolean z, boolean z2, cn.htjyb.netlib.a.a aVar, int i);

    public abstract void a(long j);

    public abstract void b(String str);

    public static b a() {
        if (g == null) {
            g = new c(BaseApplication.getAppContext());
        }
        return g;
    }

    protected b(Context context) {
    }

    public void a(cn.htjyb.netlib.a.a aVar) {
        this.a = aVar;
    }

    public static File a(String str) {
        return new File(str + ".tmp");
    }
}
