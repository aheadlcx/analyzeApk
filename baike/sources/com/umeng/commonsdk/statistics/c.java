package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.b;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.l;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy.LatentPolicy;
import com.umeng.commonsdk.statistics.common.ReportPolicy.ReportStrategy;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a;
import com.umeng.commonsdk.statistics.idtracking.e;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;

public class c {
    String a = null;
    private final int b = 1;
    private com.umeng.commonsdk.statistics.internal.c c;
    private ImprintHandler d;
    private e e;
    private a f = null;
    private ABTest g = null;
    private ImLatent h = null;
    private Defcon i = null;
    private long j = 0;
    private int k = 0;
    private int l = 0;
    private Context m;
    private ReportStrategy n = null;

    public c(Context context) {
        this.m = context;
        this.f = ImprintHandler.getImprintService(this.m).b();
        this.g = ABTest.getService(this.m);
        this.i = Defcon.getService(this.m);
        this.h = ImLatent.getService(this.m, StatTracer.getInstance(this.m));
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.m);
        this.j = sharedPreferences.getLong("thtstart", 0);
        this.k = sharedPreferences.getInt("gkvc", 0);
        this.l = sharedPreferences.getInt("ekvc", 0);
        this.a = UMEnvelopeBuild.imprintProperty(this.m, "track_list", null);
        this.d = ImprintHandler.getImprintService(this.m);
        this.d.a(new e(this));
        this.e = e.a(this.m);
        this.c = new com.umeng.commonsdk.statistics.internal.c(this.m);
        this.c.a(StatTracer.getInstance(this.m));
    }

    public boolean a(File file) {
        if (file == null) {
            return false;
        }
        try {
            byte[] a = b.a(file.getPath());
            if (a == null) {
                return false;
            }
            int i;
            com.umeng.commonsdk.statistics.internal.a.a(this.m).b(file.getName());
            a = this.c.a(a, com.umeng.commonsdk.statistics.internal.a.a(this.m).a(file.getName()));
            if (a == null) {
                i = 1;
            } else {
                i = a(a);
            }
            switch (i) {
                case 2:
                    this.e.d();
                    StatTracer.getInstance(this.m).saveSate();
                    break;
                case 3:
                    StatTracer.getInstance(this.m).saveSate();
                    break;
            }
            if (i == 2) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(this.m, th);
            return false;
        }
    }

    private int a(byte[] bArr) {
        l response = new Response();
        try {
            new o(new ad.a()).a(response, bArr);
            if (response.resp_code == 1) {
                this.d.b(response.getImprint());
                this.d.c();
            }
            MLog.i("send log:" + response.getMsg());
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(this.m, th);
        }
        if (response.resp_code == 1) {
            return 2;
        }
        return 3;
    }

    public boolean a() {
        if (!this.i.isOpen()) {
            boolean z = (this.n instanceof LatentPolicy) && this.n.isValid();
            if (!z && this.h.shouldStartLatency()) {
                this.n = new LatentPolicy((int) this.h.getDelayTime());
                return true;
            }
        }
        return false;
    }

    public int b() {
        int delayTime = (int) this.h.getDelayTime();
        return (int) (System.currentTimeMillis() - StatTracer.getInstance(this.m).getLastReqTime());
    }
}
