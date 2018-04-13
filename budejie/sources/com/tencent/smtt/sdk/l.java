package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class l {
    static boolean a = false;
    private static l c = null;
    private static int f = 0;
    private static int g = 0;
    private static int h = 3;
    private static String j = null;
    private ay b = null;
    private boolean d = false;
    private boolean e = false;
    private File i = null;

    private l() {
    }

    public static l a(boolean z) {
        if (c == null && z) {
            synchronized (l.class) {
                if (c == null) {
                    c = new l();
                }
            }
        }
        return c;
    }

    static void a(int i) {
        f = i;
    }

    private void b(int i) {
        String valueOf = String.valueOf(i);
        Properties properties = new Properties();
        properties.setProperty(j, valueOf);
        try {
            properties.store(new FileOutputStream(new File(this.i, "count.prop")), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static int d() {
        return f;
    }

    private int i() {
        Exception e;
        Throwable th;
        int i = 0;
        BufferedInputStream bufferedInputStream = null;
        BufferedInputStream bufferedInputStream2;
        try {
            File file = new File(this.i, "count.prop");
            if (file.exists()) {
                bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream2);
                    i = Integer.valueOf(properties.getProperty(j, "1")).intValue();
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return i;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } else if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                }
            }
        } catch (Exception e4) {
            e = e4;
            bufferedInputStream2 = bufferedInputStream;
            e.printStackTrace();
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            return i;
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            throw th;
        }
        return i;
    }

    public ay a() {
        return this.d ? this.b : null;
    }

    public synchronized void a(Context context, boolean z, boolean z2, ai aiVar) {
        int i = 1;
        int i2 = 0;
        synchronized (this) {
            TbsLog.addLog(999, null, new Object[0]);
            TbsLog.initIfNeed(context);
            g++;
            if (aiVar != null) {
                aiVar.b = g == 1;
            }
            TbsCoreLoadStat.getInstance().a();
            if (aiVar != null) {
                aiVar.a("tbs_rename_task", (byte) 1);
            }
            aj.a().b(context, g == 1);
            if (aiVar != null) {
                aiVar.a("tbs_rename_task", (byte) 2);
            }
            TbsShareManager.forceToLoadX5ForThirdApp(context, true);
            if (aiVar != null) {
                aiVar.a("can_load_x5", (byte) 1);
            }
            boolean a = QbSdk.a(context, z, z2);
            if (aiVar != null) {
                aiVar.a("can_load_x5", (byte) 2);
            }
            boolean z3 = VERSION.SDK_INT >= 7;
            int i3 = (a && z3) ? 1 : 0;
            if (i3 == 0) {
                String str = "can_load_x5=" + a + "; is_compatible=" + z3;
                TbsLog.e("SDKEngine", "SDKEngine.init canLoadTbs=false; failure: " + str);
                if (!(QbSdk.a && this.d)) {
                    this.d = false;
                    TbsCoreLoadStat.getInstance().a(context, 405, new Throwable(str));
                }
                this.i = aj.j(context);
                this.e = true;
            } else if (!this.d) {
                try {
                    File file;
                    File h;
                    Context d;
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_THIRD_MODE, null, new Object[0]);
                        if (aiVar != null) {
                            aiVar.a("read_core_info", (byte) 1);
                        }
                        boolean h2 = TbsShareManager.h(context);
                        if (aiVar != null) {
                            aiVar.a("read_core_info", (byte) 2);
                        }
                        if (h2) {
                            file = new File(TbsShareManager.b(context));
                            h = aj.a().h(context);
                            d = TbsShareManager.d(context);
                            if (h == null) {
                                this.d = false;
                                QbSdk.a(context, "SDKEngine::useSystemWebView by error_tbs_core_dexopt_dir null!");
                            }
                        } else {
                            this.d = false;
                            QbSdk.a(context, "SDKEngine::useSystemWebView by error_host_unavailable");
                        }
                    } else {
                        TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_SELF_MODE, null, new Object[0]);
                        h = aj.a().h(context);
                        if (!(f == 25436 || f == 25437)) {
                            i = 0;
                        }
                        d = i != 0 ? context.getApplicationContext() : context;
                        if (h == null) {
                            this.d = false;
                            QbSdk.a(context, "SDKEngine::useSystemWebView by tbs_core_share_dir null!");
                        } else {
                            file = h;
                        }
                    }
                    String[] dexLoaderFileList = QbSdk.getDexLoaderFileList(context, d, file.getAbsolutePath());
                    while (i2 < dexLoaderFileList.length) {
                        i2++;
                    }
                    this.b = new ay(context, d, file.getAbsolutePath(), h.getAbsolutePath(), dexLoaderFileList, QbSdk.d, aiVar);
                    this.d = true;
                } catch (Throwable th) {
                    TbsLog.e("SDKEngine", "useSystemWebView by exception: " + th);
                    if (th == null) {
                        TbsCoreLoadStat.getInstance().a(context, 326);
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, 327, th);
                    }
                    this.d = false;
                    QbSdk.a(context, "SDKEngine::useSystemWebView by exception: " + th);
                }
                this.i = aj.j(context);
                this.e = true;
            }
        }
    }

    void a(String str) {
        j = str;
    }

    public boolean b() {
        return this.d;
    }

    boolean b(boolean z) {
        a = z;
        return z;
    }

    ay c() {
        return this.b;
    }

    public String e() {
        return (this.b == null || QbSdk.a) ? "system webview get nothing..." : this.b.a();
    }

    boolean f() {
        if (a) {
            if (j == null) {
                return false;
            }
            int i = i();
            if (i == 0) {
                b(1);
            } else if (i + 1 > h) {
                return false;
            } else {
                b(i + 1);
            }
        }
        return a;
    }

    boolean g() {
        return this.e;
    }

    public boolean h() {
        return QbSdk.useSoftWare();
    }
}
