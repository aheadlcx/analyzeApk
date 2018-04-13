package com.umeng.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import com.meizu.cloud.pushsdk.notification.model.TimeDisplaySetting;
import com.umeng.analytics.b.f;
import com.umeng.analytics.b.g;
import com.umeng.analytics.d.m;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;

public final class k {
    private static k a = null;
    private static Context b;
    private static String c;
    private a d;

    public static class a {
        private final int a;
        private File b;
        private FilenameFilter c;

        public a(Context context) {
            this(context, ".um");
        }

        public a(Context context, String str) {
            this.a = 10;
            this.c = new FilenameFilter(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean accept(File file, String str) {
                    return str.startsWith("um");
                }
            };
            this.b = new File(context.getFilesDir(), str);
            if (!this.b.exists() || !this.b.isDirectory()) {
                this.b.mkdir();
            }
        }

        public boolean a() {
            File[] listFiles = this.b.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return false;
            }
            return true;
        }

        public void a(b bVar) {
            int i;
            int i2 = 0;
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                final int length = listFiles.length - 10;
                h.b(new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        if (length > 0) {
                            com.umeng.analytics.a.d.a.a(k.b).a((long) length, System.currentTimeMillis(), com.umeng.analytics.a.w);
                        }
                    }
                });
                for (i = 0; i < length; i++) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                bVar.a(this.b);
                i = listFiles.length;
                while (i2 < i) {
                    try {
                        if (bVar.b(listFiles[i2])) {
                            listFiles[i2].delete();
                        }
                    } catch (Throwable th) {
                        listFiles[i2].delete();
                    }
                    i2++;
                }
                bVar.c(this.b);
            }
        }

        public void a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    e.a(new File(this.b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception e) {
                }
            }
        }
    }

    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    public k(Context context) {
        this.d = new a(context);
    }

    public static synchronized k a(Context context) {
        k kVar;
        synchronized (k.class) {
            b = context.getApplicationContext();
            c = context.getPackageName();
            if (a == null) {
                a = new k(context);
            }
            kVar = a;
        }
        return kVar;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Editor edit = j().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public String[] a() {
        SharedPreferences j = j();
        String string = j.getString("au_p", null);
        String string2 = j.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    public void b() {
        j().edit().remove("au_p").remove("au_u").commit();
    }

    public String c() {
        SharedPreferences a = m.a(b);
        if (a != null) {
            return a.getString(g.a, null);
        }
        return null;
    }

    public void a(String str) {
        SharedPreferences a = m.a(b);
        if (a != null) {
            a.edit().putString(g.a, str).commit();
        }
    }

    public String d() {
        SharedPreferences a = m.a(b);
        if (a != null) {
            return a.getString(TimeDisplaySetting.START_SHOW_TIME, null);
        }
        return null;
    }

    public void b(String str) {
        SharedPreferences a = m.a(b);
        if (a != null) {
            a.edit().putString(TimeDisplaySetting.START_SHOW_TIME, str).commit();
        }
    }

    public void a(int i) {
        SharedPreferences a = m.a(b);
        if (a != null) {
            a.edit().putInt("vt", i).commit();
        }
    }

    public int e() {
        SharedPreferences a = m.a(b);
        if (a != null) {
            return a.getInt("vt", 0);
        }
        return 0;
    }

    public void f() {
        b.deleteFile(k());
        b.deleteFile(l());
        f.a(b).a(true, false);
        com.umeng.analytics.a.d.a.a(b).b(new com.umeng.analytics.a.b.a(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void a(Object obj, boolean z) {
                if (!obj.equals(ANConstants.SUCCESS)) {
                }
            }
        });
    }

    public void a(byte[] bArr) {
        this.d.a(bArr);
    }

    public boolean g() {
        return this.d.a();
    }

    public a h() {
        return this.d;
    }

    private SharedPreferences j() {
        return b.getSharedPreferences("mobclick_agent_user_" + c, 0);
    }

    private String k() {
        return "mobclick_agent_header_" + c;
    }

    private String l() {
        SharedPreferences a = m.a(b);
        if (a == null) {
            return "mobclick_agent_cached_" + c + d.a(b);
        }
        int i = a.getInt(com.umeng.analytics.a.B, 0);
        int parseInt = Integer.parseInt(d.a(b));
        if (i == 0 || parseInt == i) {
            return "mobclick_agent_cached_" + c + d.a(b);
        }
        return "mobclick_agent_cached_" + c + i;
    }
}
