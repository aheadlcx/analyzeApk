package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.download.a;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.upgrade.BetaGrayStrategy;
import java.io.File;

public class q {
    public static q a = new q();
    public BetaGrayStrategy b;
    public DownloadTask c;
    public final Handler d = new Handler(Looper.getMainLooper());
    private DownloadListener e = new a(4, new Object[]{this, Integer.valueOf(0)});

    public synchronized void a(int i, y yVar, boolean z) {
        this.b = a(yVar);
        if (i == 0 && this.b != null) {
            if (!(this.b == null || this.b.a == null || this.b.a.f == null)) {
                File file = e.E.G;
                if (file != null && file.exists() && com.tencent.bugly.beta.global.a.a(file, this.b.a.f.a, "SHA")) {
                    an.a("patch has downloaded!", new Object[0]);
                    if (!e.E.N && e.E.O <= 3) {
                        an.a("patch has downloaded but not patched execute patch!", new Object[0]);
                        e eVar = e.E;
                        int i2 = eVar.O;
                        eVar.O = i2 + 1;
                        com.tencent.bugly.beta.global.a.a("PATCH_MAX_TIMES", String.valueOf(i2));
                        com.tencent.bugly.beta.global.a.a(file, e.E.H);
                        TinkerManager.getInstance().onDownloadSuccess(e.E.H.getAbsolutePath(), e.E.U);
                    }
                } else {
                    if (e.E.V != null) {
                        u b = this.b.a.b();
                        if (b != null) {
                            this.d.post(new q$1(this, b));
                        }
                    }
                    if ((e.E.X || z) && e.E.Y) {
                        a();
                    }
                }
            }
        }
    }

    public BetaGrayStrategy a(y yVar) {
        BetaGrayStrategy betaGrayStrategy;
        File file;
        BetaGrayStrategy betaGrayStrategy2;
        BetaGrayStrategy betaGrayStrategy3 = (BetaGrayStrategy) com.tencent.bugly.beta.global.a.a("st.bch", BetaGrayStrategy.CREATOR);
        if (betaGrayStrategy3 == null || betaGrayStrategy3.a == null) {
            com.tencent.bugly.beta.global.a.a("st.bch");
            betaGrayStrategy = null;
        } else {
            betaGrayStrategy = betaGrayStrategy3;
        }
        if (yVar != null) {
            if (yVar.n == 1 || betaGrayStrategy == null || TextUtils.isEmpty(betaGrayStrategy.a.m) || !TextUtils.equals(yVar.m, betaGrayStrategy.a.m)) {
                betaGrayStrategy3 = betaGrayStrategy;
            } else {
                p.a.a(new w("recall", System.currentTimeMillis(), (byte) 0, 0, null, betaGrayStrategy.a.m, betaGrayStrategy.a.p, null));
                com.tencent.bugly.beta.global.a.a("st.bch");
                e.E.p.a(betaGrayStrategy.a.f.b, e.E.I.getAbsolutePath(), null, betaGrayStrategy.a.f.a).delete(true);
                file = e.E.H;
                if (file != null && file.exists() && file.delete()) {
                    an.a("delete tmpPatchFile", new Object[0]);
                }
                file = e.E.G;
                if (file != null && file.exists() && file.delete()) {
                    an.a("delete patchFile", new Object[0]);
                }
                betaGrayStrategy3 = null;
                com.tencent.bugly.beta.global.a.a("IS_PATCH_ROLL_BACK", true);
                an.a("patch rollback", new Object[0]);
                if (ap.b(e.E.s)) {
                    TinkerManager.getInstance().onPatchRollback(false);
                } else {
                    TinkerManager.getInstance().onPatchRollback(true);
                }
            }
            if (yVar.n != 1) {
                yVar = null;
                betaGrayStrategy2 = betaGrayStrategy3;
            } else {
                betaGrayStrategy2 = betaGrayStrategy3;
            }
        } else {
            betaGrayStrategy2 = betaGrayStrategy;
        }
        if (yVar != null) {
            Parcelable betaGrayStrategy4 = new BetaGrayStrategy();
            betaGrayStrategy4.a = yVar;
            betaGrayStrategy4.e = System.currentTimeMillis();
            if (betaGrayStrategy2 != null && (!TextUtils.equals(betaGrayStrategy2.a.f.b, yVar.f.b) || (yVar.l != null && TextUtils.equals((CharSequence) yVar.l.get("H1"), "false")))) {
                e.E.M = (String) yVar.l.get("H2");
                e.E.p.a(betaGrayStrategy2.a.f.b, e.E.I.getAbsolutePath(), null, null).delete(true);
                if (betaGrayStrategy2.a.p == 3) {
                    file = e.E.H;
                    if (file != null && file.exists() && file.delete()) {
                        an.a("delete tmpPatchFile", new Object[0]);
                    }
                    file = e.E.G;
                    if (file != null && file.exists() && file.delete()) {
                        e.E.L = "";
                        an.a("delete patchFile", new Object[0]);
                    }
                }
            }
            com.tencent.bugly.beta.global.a.a("st.bch", betaGrayStrategy4);
            an.a("onUpgradeReceived: %s [type: %d]", yVar, Integer.valueOf(yVar.p));
            return betaGrayStrategy4;
        } else if (betaGrayStrategy2 == null || betaGrayStrategy2.a == null || betaGrayStrategy2.a.p != 3) {
            return null;
        } else {
            return betaGrayStrategy2;
        }
    }

    private void a() {
        if (this.b != null) {
            if (this.c == null) {
                this.c = e.E.p.a(this.b.a.f.b, e.E.I.getAbsolutePath(), null, this.b.a.f.a);
            }
            if (this.c != null) {
                this.c.addListener(this.e);
                this.c.setNeededNotify(false);
                this.c.download();
            }
        }
    }
}
