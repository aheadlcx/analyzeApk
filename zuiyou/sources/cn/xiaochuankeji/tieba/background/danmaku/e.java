package cn.xiaochuankeji.tieba.background.danmaku;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import cn.htjyb.netlib.d;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.h.f;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.File;
import java.util.Locale;

public class e {
    private final long a;
    private final String b;
    private String c;
    private cn.htjyb.netlib.a d;
    private a e;

    public interface a {
        void a(e eVar, String str);

        void b(e eVar, String str);
    }

    public e(long j, String str) {
        this.a = j;
        this.b = str;
    }

    public long a() {
        return this.a;
    }

    public boolean b() {
        return new File(c()).exists();
    }

    public String c() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = g();
        }
        return this.c;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void d() {
        if (!b() && !f()) {
            this.d = new cn.htjyb.netlib.a(this.b, cn.xiaochuankeji.tieba.background.a.d(), c(), new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onTaskFinish(d dVar) {
                    this.a.d = null;
                    if (dVar.c.a) {
                        if (this.a.e != null) {
                            this.a.e.a(this.a, this.a.c());
                        }
                    } else if (this.a.e != null) {
                        this.a.e.b(this.a, dVar.c.c());
                    }
                }
            });
            this.d.b();
        }
    }

    public void e() {
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
    }

    public boolean f() {
        return this.d != null;
    }

    private String g() {
        String lastPathSegment;
        try {
            StringBuffer stringBuffer = new StringBuffer(cn.xiaochuankeji.tieba.background.a.e().z());
            stringBuffer.append("sound" + File.separator);
            Uri parse = Uri.parse(this.b.trim());
            lastPathSegment = parse.getLastPathSegment();
            if (lastPathSegment != null) {
                lastPathSegment = lastPathSegment.substring(0, lastPathSegment.length() / 2);
            } else {
                lastPathSegment = cn.htjyb.c.d.c(parse.toString()).substring(0, 16);
            }
            stringBuffer.append(String.format(Locale.US, "%08x", new Object[]{Integer.valueOf(parse.hashCode())}).substring(0, 2) + File.separator);
            new File(stringBuffer.toString()).mkdirs();
            stringBuffer.append(String.format(Locale.US, "%s-%s.%s", new Object[]{r2, lastPathSegment, "m4a"}));
            return stringBuffer.toString();
        } catch (NullPointerException e) {
            File file = null;
            StringBuilder stringBuilder = new StringBuilder();
            if (f.F() && f.G()) {
                file = Environment.getExternalStorageDirectory();
            }
            if (file == null || !file.exists()) {
                stringBuilder.append(";dir_notexist");
            } else {
                lastPathSegment = file.getPath();
                if (!lastPathSegment.endsWith("/")) {
                    lastPathSegment = lastPathSegment + "/";
                }
                lastPathSegment = lastPathSegment + BaseApplication.getAppContext().getPackageName() + "/";
                stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE + lastPathSegment);
                File file2 = new File(lastPathSegment);
                if (!(file2.exists() || file2.mkdirs())) {
                    stringBuilder.append(";mkdirs_failed");
                }
            }
            throw new NullPointerException("danmakuDir=" + stringBuilder.toString());
        }
    }
}
