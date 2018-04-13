package cn.htjyb.netlib;

import android.content.Context;
import android.text.TextUtils;
import cn.htjyb.netlib.b.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.network.f;
import com.meizu.cloud.pushsdk.pushtracer.constant.TrackerConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.cert.CertPathValidatorException;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.aa;
import okhttp3.u;
import okhttp3.v;
import okhttp3.w;
import okhttp3.w.a;
import okhttp3.y;
import okhttp3.y$a;
import okhttp3.z;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends b {
    private static c b;
    private static final u d = u.a(TrackerConstants.POST_CONTENT_TYPE);
    private static long e;
    private w c = c();

    public static c b() {
        if (b == null) {
            b = new c(BaseApplication.getAppContext());
        }
        return b;
    }

    protected c(Context context) {
        super(context);
    }

    private w c() {
        a aVar = new a();
        aVar.a(cn.xiaochuankeji.tieba.network.custom.a.a.a());
        SSLSocketFactory fVar = new f();
        aVar.a(new cn.xiaochuankeji.tieba.network.custom.a());
        try {
            aVar.a(fVar, f.a());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        aVar.a(10, TimeUnit.SECONDS).b(15, TimeUnit.SECONDS).c(15, TimeUnit.SECONDS);
        return aVar.a();
    }

    private z c(String str) {
        return z.create(d, str);
    }

    public b.a a(d dVar, String str, String str2) {
        y d = new y$a().a(str).a(c(str2)).b("ZYP", "mid=" + e).d();
        b.a aVar = new b.a();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            aa a = this.c.a(d).a();
            aVar.f = a.b();
            aVar.g = new JSONObject();
            if (a.c()) {
                aVar.d = a.g().string();
                if (dVar.d) {
                    try {
                        aVar.g = new JSONObject(aVar.d);
                    } catch (JSONException e) {
                        aVar.g = new JSONObject();
                    }
                }
                a(aVar.g, aVar);
                aVar.a(dVar.d);
            } else {
                aVar.a(a.d());
                aVar.a(false);
            }
        } catch (Throwable e2) {
            aVar.a(false);
            aVar.a = false;
            aVar.a(e2);
            if ((e2 instanceof SSLException) || (e2 instanceof CertPathValidatorException)) {
                cn.xiaochuankeji.tieba.background.utils.c.a.c().D();
            }
        }
        aVar.e = System.currentTimeMillis() - currentTimeMillis;
        return aVar;
    }

    public void b(final String str) {
        if (!TextUtils.isEmpty(str)) {
            cn.xiaochuankeji.tieba.background.a.p().e().execute(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    try {
                        this.b.c.a(new y$a().a(str).d()).a();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public b.a a(d dVar, String str, Collection<b> collection, JSONObject jSONObject) {
        return a(dVar, str, collection, jSONObject, null, 0);
    }

    public b.a a(d dVar, String str, Collection<b> collection, JSONObject jSONObject, g.a aVar, int i) {
        b.a aVar2 = new b.a();
        v.a aVar3 = new v.a();
        if (collection != null && collection.size() > 0) {
            for (b bVar : collection) {
                File file = bVar.a;
                aVar3.a(bVar.b, file.getName(), new cn.xiaochuankeji.tieba.background.upload.a(file, null));
            }
        }
        aVar3.a("json", jSONObject.toString());
        aVar3.a(v.e);
        y d = new y$a().a(str).a(aVar3.a()).b("ZYP", "mid=" + e).d();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            aa a = this.c.a(d).a();
            aVar2.f = a.b();
            aVar2.g = new JSONObject();
            if (a.c()) {
                aVar2.d = a.g().string();
                if (dVar.d) {
                    try {
                        aVar2.g = new JSONObject(aVar2.d);
                    } catch (JSONException e) {
                        aVar2.g = new JSONObject();
                    }
                }
                a(aVar2.g, aVar2);
                aVar2.a(dVar.d);
            } else {
                aVar2.a(a.d());
                aVar2.a(false);
            }
        } catch (Throwable e2) {
            aVar2.a(false);
            aVar2.a = false;
            aVar2.a(e2);
        }
        aVar2.e = System.currentTimeMillis() - currentTimeMillis;
        return aVar2;
    }

    public b.a a(d dVar, String str, HashMap<String, String> hashMap, String str2, JSONObject jSONObject, boolean z, boolean z2, a.a aVar, int i) {
        b.a aVar2 = new b.a();
        long currentTimeMillis = System.currentTimeMillis();
        File a = b.a(str2);
        File parentFile = a.getParentFile();
        if (parentFile == null) {
            parentFile = BaseApplication.getAppContext().getExternalCacheDir();
        }
        if (parentFile == null) {
            parentFile = BaseApplication.getAppContext().getCacheDir();
        }
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        long length = a.length();
        y d = new y$a().b("RANGE", "bytes=" + length + "-").a(str).d();
        try {
            if (this.c == null) {
                this.c = c();
            }
            aa a2 = this.c.a(d).a();
            aVar2.f = a2.b();
            aVar2.g = new JSONObject();
            if (!a2.c()) {
                aVar2.a(a2.d());
                aVar2.a(false);
            }
            int i2 = (int) length;
            if (a2 != null && a2.c()) {
                long contentLength = a2.g().contentLength();
                FileOutputStream fileOutputStream = new FileOutputStream(a, true);
                InputStream byteStream = a2.g().byteStream();
                contentLength += length;
                byte[] bArr = new byte[16384];
                length = 0;
                while (true) {
                    int read = byteStream.read(bArr);
                    if (-1 == read) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    if (aVar != null) {
                        read += i2;
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 > ((long) i) + length) {
                            aVar.a((int) contentLength, read);
                        } else {
                            currentTimeMillis2 = length;
                        }
                        length = currentTimeMillis2;
                        i2 = read;
                    }
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                byteStream.close();
                aVar2.a(false);
                parentFile = new File(str2);
                parentFile.delete();
                File parentFile2 = parentFile.getParentFile();
                if (!parentFile2.exists()) {
                    parentFile2.mkdirs();
                }
                if (!cn.htjyb.c.a.b.a(a, parentFile)) {
                    com.izuiyou.a.a.b.d("CopyFile Failed:    " + str2);
                    aVar2.a("filenotfound_copyfail");
                }
                a.delete();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            aVar2.a(false);
            aVar2.a = false;
            aVar2.a(e);
            com.izuiyou.a.a.b.e(e);
        }
        aVar2.e = System.currentTimeMillis() - currentTimeMillis;
        return aVar2;
    }

    public void a(long j) {
        e = j;
    }

    private void a(JSONObject jSONObject, b.a aVar) {
        int optInt = jSONObject.optInt("ret", 0);
        if (optInt == 1) {
            aVar.c = jSONObject.optJSONObject("data");
            aVar.a = true;
            return;
        }
        aVar.b = optInt;
        aVar.a = false;
        aVar.a(jSONObject.optString("msg"));
    }
}
