package cn.xiaochuankeji.tieba.ui.utils;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.WebSettings;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.d.g;
import cn.xiaochuankeji.tieba.network.custom.a.c;
import cn.xiaochuankeji.tieba.network.e;
import cn.xiaochuankeji.tieba.network.f;
import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.u;
import okhttp3.w;
import okhttp3.y;
import okhttp3.y$a;
import okhttp3.z;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static String[][] a;
    private static String[][] b;
    private a c;
    private b d;
    private StringBuffer e;

    public interface b {
        void a(String str);

        void b(String str);
    }

    private class a extends AsyncTask<Void, String, Void> {
        final /* synthetic */ b a;
        private w b;

        private a(b bVar) {
            this.a = bVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((String[]) objArr);
        }

        protected Void a(Void... voidArr) {
            okhttp3.w.a aVar = new okhttp3.w.a();
            aVar.a(new c());
            SSLSocketFactory fVar = new f();
            aVar.a(new cn.xiaochuankeji.tieba.network.custom.a());
            try {
                aVar.a(fVar, f.a());
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            aVar.a(10, TimeUnit.SECONDS).b(15, TimeUnit.SECONDS).c(15, TimeUnit.SECONDS);
            this.b = aVar.a();
            for (String[] strArr : b.a) {
                int indexOf = strArr[1].indexOf(58);
                String a = a(strArr[1].substring(0, indexOf), Integer.valueOf(strArr[1].substring(indexOf + 1)).intValue());
                publishProgress(new String[]{strArr[0] + a});
            }
            if (TextUtils.isEmpty(cn.xiaochuankeji.tieba.network.b.a().a("api.izuiyou.com"))) {
                publishProgress(new String[]{"httpdns api-ip is null,httpdns maybe does’t work"});
            } else {
                publishProgress(new String[]{"httpdns api-ip connect http  " + a(cn.xiaochuankeji.tieba.network.b.a().a("api.izuiyou.com"), 80)});
                publishProgress(new String[]{"httpdns api-ip connect https " + a(r0, 443)});
                publishProgress(new String[]{"httpdns api-ip http " + a(r0, false)});
                publishProgress(new String[]{"httpdns api-ip https " + a(r0, true)});
            }
            if (TextUtils.isEmpty(e.a().a("api.izuiyou.com"))) {
                publishProgress(new String[]{"smartdns api-ip is null,smartdns maybe does’t work"});
            } else {
                publishProgress(new String[]{"smartdns api-ip connect http  " + a(e.a().a("api.izuiyou.com"), 80)});
                publishProgress(new String[]{"smartdns api-ip connect https " + a(r0, 443)});
                publishProgress(new String[]{"smartdns api-ip http  " + a(r0, false)});
                publishProgress(new String[]{"smartdns api-ip https " + a(r0, true)});
            }
            publishProgress(new String[]{"localdns api http  " + a("api.izuiyou.com", false)});
            publishProgress(new String[]{"localdns api https " + a("api.izuiyou.com", true)});
            if (TextUtils.isEmpty(cn.xiaochuankeji.tieba.network.b.a().a("file.izuiyou.com"))) {
                publishProgress(new String[]{"httpdns file-ip is null,httpdns maybe does’t work"});
            } else {
                publishProgress(new String[]{"httpdns file-ip connect http  " + a(cn.xiaochuankeji.tieba.network.b.a().a("file.izuiyou.com"), 80)});
                publishProgress(new String[]{"httpdns file-ip download " + b(r0, false)});
            }
            if (TextUtils.isEmpty(e.a().a("file.izuiyou.com"))) {
                publishProgress(new String[]{"smartdns file-ip is null,smartdns maybe does’t work"});
            } else {
                publishProgress(new String[]{"smartdns file-ip connect http  " + a(e.a().a("file.izuiyou.com"), 80)});
                publishProgress(new String[]{"smartdns file-ip download " + b(r0, false)});
            }
            for (String[] strArr2 : b.b) {
                a = b(strArr2[1], false);
                publishProgress(new String[]{strArr2[0] + a});
            }
            publishProgress(new String[]{"localdns https file " + b("file.izuiyou.com", true)});
            return null;
        }

        protected void a(String... strArr) {
            for (String a : strArr) {
                this.a.a(a);
            }
        }

        protected void a(Void voidR) {
            if (this.a.d != null) {
                this.a.d.b(this.a.e.toString());
            }
        }

        private String a(String str, int i) {
            StringBuilder stringBuilder = new StringBuilder();
            long currentTimeMillis = System.currentTimeMillis();
            Socket socket = new Socket();
            try {
                socket.setSoTimeout(3000);
                SocketAddress inetSocketAddress = new InetSocketAddress(str, i);
                stringBuilder.append("connect to ").append(inetSocketAddress.getHostName()).append(" ").append(inetSocketAddress.getPort()).append(" ").append(inetSocketAddress.getAddress().getHostAddress()).append(" ... ");
                socket.connect(inetSocketAddress);
                stringBuilder.append("time=").append(System.currentTimeMillis() - currentTimeMillis).append(Parameters.MESSAGE_SEQ);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                stringBuilder.append("exception=").append(e2.toString());
                try {
                    socket.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    socket.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
            return stringBuilder.toString();
        }

        private String b(String str, boolean z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("download host:").append(str);
            stringBuilder.append("  ");
            long currentTimeMillis = System.currentTimeMillis();
            y d = new y$a().a((z ? "https://" : "http://") + str + "/account/avatar/id/24227105/sz/120").a("HOST", "file.izuiyou.com").d();
            InputStream inputStream = null;
            try {
                aa a = this.b.a(d).a();
                if (a.c()) {
                    byte[] bArr = new byte[1024];
                    inputStream = a.g().byteStream();
                    do {
                    } while (inputStream.read(bArr) != -1);
                }
                Object a2 = a.a("Content-Type");
                if (!TextUtils.isEmpty(a2)) {
                    stringBuilder.append("Content-Type=").append(a2);
                    stringBuilder.append(", ");
                }
                stringBuilder.append("status=").append(a.b());
                stringBuilder.append(" time=").append(System.currentTimeMillis() - currentTimeMillis).append(Parameters.MESSAGE_SEQ);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                stringBuilder.append("exception=").append(e2.toString());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                    }
                }
            }
            return stringBuilder.toString();
        }

        public String a(String str, boolean z) {
            y d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("recommend api host:").append(str).append("\n");
            String str2 = (z ? "https://" : "http://") + str + "/index/recommend";
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            try {
                jSONObject.put("offset", 0);
                jSONObject.put("filter", SpeechConstant.PLUS_LOCAL_ALL);
                jSONObject.put("tab", "rec");
                jSONObject.put("auto", 0);
                jSONObject.put("direction", "down");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            z create = z.create(u.a("application/json"), jSONObject.toString());
            if (z) {
                d = new y$a().a(str2).a(create).b("User-Agent").b("User-Agent", this.a.f()).d();
            } else {
                d = new y$a().a(str2).a(create).a("HOST", "api.izuiyou.com").d();
            }
            try {
                aa a = this.b.a(d).a();
                if (a.c()) {
                    ab g = a.g();
                    if (g != null) {
                        stringBuilder.append(" response ret:").append(new JSONObject(g.string()).optInt("ret", 0));
                    }
                }
            } catch (Exception e2) {
                stringBuilder.append("exception:").append(e2.getMessage());
            }
            return stringBuilder.toString();
        }
    }

    public static b a() {
        return new b();
    }

    private b() {
        int i;
        List b = e.a().b();
        a = (String[][]) Array.newInstance(String.class, new int[]{b.size() + 6, 2});
        a[0] = new String[]{"", "www.baidu.com:80"};
        a[1] = new String[]{"", "www.sina.com.cn:80"};
        a[2] = new String[]{"", "www.163.com:80"};
        a[3] = new String[]{"localdns ", "api.izuiyou.com:80"};
        a[4] = new String[]{"localdns ", "api.izuiyou.com:443"};
        a[5] = new String[]{"localdns ", "file.izuiyou.com:80"};
        for (i = 0; i < b.size(); i++) {
            a[i + 6] = new String[]{"smartdns ", ((cn.xiaochuankeji.tieba.network.e.a) b.get(i)).b() + ":80"};
        }
        List c = e.a().c();
        b = (String[][]) Array.newInstance(String.class, new int[]{c.size() + 1, 2});
        b[0] = new String[]{"localdns ", "file.izuiyou.com"};
        for (i = 0; i < c.size(); i++) {
            b[i + 1] = new String[]{"smartdns ", ((cn.xiaochuankeji.tieba.network.e.a) b.get(i)).b() + ":80"};
        }
        this.e = new StringBuffer();
    }

    public void a(b bVar) {
        String str;
        this.d = bVar;
        cn.xiaochuankeji.tieba.d.g.a a = g.a();
        a("是否联网: " + a.a);
        a("联网类型: " + a.c);
        a("本机IP: " + a.b);
        a("本地DNS: " + a.d);
        a("运行商: " + g.b());
        if (e()) {
            str = "是";
        } else {
            str = "否";
        }
        a("是否设置代理: " + str);
        this.c = new a();
        this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void b() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
        this.d = null;
        this.e = null;
    }

    private boolean e() {
        String property = System.getProperty("http.proxyHost");
        String property2 = System.getProperty("http.proxyPort");
        if (property2 == null) {
            property2 = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        return (property == null || Integer.parseInt(property2) == -1) ? false : true;
    }

    private void a(String str) {
        if (this.d != null) {
            this.d.a(str + "\n");
        }
        if (this.e != null) {
            this.e.append(str + "\n");
        }
    }

    private String f() {
        String str = null;
        try {
            if (VERSION.SDK_INT >= 17) {
                str = WebSettings.getDefaultUserAgent(BaseApplication.getAppContext());
            }
        } catch (Exception e) {
            str = System.getProperty("http.agent");
        }
        if (TextUtils.isEmpty(str)) {
            return "none";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt <= '\u001f' || charAt >= '') {
                stringBuilder.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
            } else {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }
}
