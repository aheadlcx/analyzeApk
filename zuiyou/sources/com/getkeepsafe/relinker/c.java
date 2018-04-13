package com.getkeepsafe.relinker;

import android.content.Context;
import android.util.Log;
import com.getkeepsafe.relinker.a.f;
import com.getkeepsafe.relinker.b.a;
import com.getkeepsafe.relinker.b.b;
import com.getkeepsafe.relinker.b.d;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class c {
    protected final Set<String> a;
    protected final b b;
    protected final a c;
    protected boolean d;
    protected boolean e;
    protected d f;

    protected c() {
        this(new d(), new a());
    }

    protected c(b bVar, a aVar) {
        this.a = new HashSet();
        if (bVar == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        } else if (aVar == null) {
            throw new IllegalArgumentException("Cannot pass null library installer");
        } else {
            this.b = bVar;
            this.c = aVar;
        }
    }

    public void a(Context context, String str) {
        a(context, str, null, null);
    }

    public void a(Context context, String str, String str2, com.getkeepsafe.relinker.b.c cVar) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        } else if (e.a(str)) {
            throw new IllegalArgumentException("Given library is either null or empty");
        } else {
            a("Beginning load of %s...", str);
            if (cVar == null) {
                c(context, str, str2);
                return;
            }
            final Context context2 = context;
            final String str3 = str;
            final String str4 = str2;
            final com.getkeepsafe.relinker.b.c cVar2 = cVar;
            new Thread(new Runnable(this) {
                final /* synthetic */ c e;

                public void run() {
                    try {
                        this.e.c(context2, str3, str4);
                        cVar2.a();
                    } catch (Throwable e) {
                        cVar2.a(e);
                    } catch (Throwable e2) {
                        cVar2.a(e2);
                    }
                }
            }).start();
        }
    }

    private void c(Context context, String str, String str2) {
        if (!this.a.contains(str) || this.d) {
            try {
                this.b.a(str);
                this.a.add(str);
                a("%s (%s) was loaded normally!", str, str2);
                return;
            } catch (Throwable e) {
                a("Loading the library normally failed: %s", Log.getStackTraceString(e));
                a("%s (%s) was not loaded normally, re-linking...", str, str2);
                File a = a(context, str, str2);
                if (!a.exists() || this.d) {
                    if (this.d) {
                        a("Forcing a re-link of %s (%s)...", str, str2);
                    }
                    b(context, str, str2);
                    this.c.a(context, this.b.a(), this.b.c(str), a, this);
                }
                try {
                    if (this.e) {
                        for (String d : new f(a).b()) {
                            a(context, this.b.d(d));
                        }
                    }
                } catch (IOException e2) {
                }
                this.b.b(a.getAbsolutePath());
                this.a.add(str);
                a("%s (%s) was re-linked!", str, str2);
                return;
            }
        }
        a("%s already loaded previously!", str);
    }

    protected File a(Context context) {
        return context.getDir(ShareConstants.SO_PATH, 0);
    }

    protected File a(Context context, String str, String str2) {
        String c = this.b.c(str);
        if (e.a(str2)) {
            return new File(a(context), c);
        }
        return new File(a(context), c + "." + str2);
    }

    protected void b(Context context, String str, String str2) {
        File a = a(context);
        File a2 = a(context, str, str2);
        final String c = this.b.c(str);
        File[] listFiles = a.listFiles(new FilenameFilter(this) {
            final /* synthetic */ c b;

            public boolean accept(File file, String str) {
                return str.startsWith(c);
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                if (this.d || !file.getAbsolutePath().equals(a2.getAbsolutePath())) {
                    file.delete();
                }
            }
        }
    }

    public void a(String str, Object... objArr) {
        a(String.format(Locale.US, str, objArr));
    }

    public void a(String str) {
        if (this.f != null) {
            this.f.a(str);
        }
    }
}
