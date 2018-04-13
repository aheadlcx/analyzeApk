package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.k;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

class bi {
    private static bi a;
    private static FileLock e = null;
    private bj b;
    private boolean c;
    private boolean d;

    private bi() {
    }

    public static bi b() {
        if (a == null) {
            synchronized (bi.class) {
                if (a == null) {
                    a = new bi();
                }
            }
        }
        return a;
    }

    public bj a(boolean z) {
        return z ? this.b : d();
    }

    public FileLock a() {
        return e;
    }

    public synchronized FileLock a(Context context) {
        FileLock fileLock;
        if (e != null) {
            fileLock = e;
        } else {
            FileOutputStream b = k.b(context, true, "tbs_rename_lock.txt");
            if (b != null) {
                e = k.a(context, b);
                if (e == null) {
                }
            }
            fileLock = e;
        }
        return fileLock;
    }

    public synchronized void a(Context context, ai aiVar) {
        Throwable th;
        Object obj = null;
        synchronized (this) {
            Object obj2;
            if (aiVar != null) {
                aiVar.a("x5_core_engine_init_sync", (byte) 2);
            }
            l a = l.a(true);
            a.a(context, false, false, aiVar);
            StringBuilder stringBuilder = new StringBuilder();
            if (aiVar != null) {
                aiVar.a("init_x5_core", (byte) 1);
            }
            if (a.b()) {
                if (!this.d) {
                    if (aiVar != null) {
                        aiVar.a = true;
                    }
                    this.b = new bj(a.a().b());
                    try {
                        this.c = this.b.a();
                        if (!this.c) {
                            stringBuilder.append("can not use X5 by x5corewizard return false");
                        }
                        obj2 = null;
                    } catch (NoSuchMethodException e) {
                        this.c = true;
                        obj2 = null;
                    } catch (Throwable th2) {
                        obj2 = th2;
                        this.c = false;
                        stringBuilder.append("can not use x5 by throwable " + Log.getStackTraceString(obj2));
                    }
                    if (this.c) {
                        CookieManager.getInstance().a();
                    }
                }
                obj2 = null;
            } else {
                this.c = false;
                stringBuilder.append("can not use X5 by !tbs available");
                obj2 = null;
            }
            if (!this.c) {
                TbsLog.e("X5CoreEngine", "mCanUseX5 is false --> report");
                if (a.b() && obj2 == null) {
                    try {
                        DexLoader b = a.a().b();
                        if (b != null) {
                            obj = b.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]);
                        }
                        if (obj instanceof Throwable) {
                            th = (Throwable) obj;
                            stringBuilder.append("#" + th.getMessage() + "; cause: " + th.getCause() + "; th: " + th);
                        }
                        if (obj instanceof String) {
                            stringBuilder.append("failure detail:" + obj);
                        }
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    if (stringBuilder != null) {
                        if (stringBuilder.toString().contains("isPreloadX5Disabled:-10000")) {
                            TbsCoreLoadStat.getInstance().a(context, 408, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + stringBuilder.toString()));
                        }
                    }
                    TbsCoreLoadStat.getInstance().a(context, 407, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + stringBuilder.toString()));
                } else if (a.b()) {
                    TbsCoreLoadStat.getInstance().a(context, 409, new Throwable("mCanUseX5=false, available true, reason: " + obj2));
                } else {
                    TbsCoreLoadStat.getInstance().a(context, 410, new Throwable("mCanUseX5=false, available false, reason: " + obj2));
                }
            } else if (e == null) {
                a(context);
            }
            this.d = true;
            if (aiVar != null) {
                aiVar.a("init_x5_core", (byte) 2);
            }
        }
    }

    public boolean c() {
        return QbSdk.a ? false : this.c;
    }

    public bj d() {
        return QbSdk.a ? null : this.b;
    }

    boolean e() {
        return this.d;
    }
}
