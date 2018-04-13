package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a;
import com.umeng.commonsdk.statistics.internal.d;
import qsbk.app.utils.ListViewHelper;

public class Defcon implements d {
    private static Defcon b = null;
    private int a = 0;

    public static synchronized Defcon getService(Context context) {
        Defcon defcon;
        synchronized (Defcon.class) {
            if (b == null) {
                b = new Defcon();
                b.setLevel(Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", "0")).intValue());
            }
            defcon = b;
        }
        return defcon;
    }

    private Defcon() {
    }

    public int getLevel() {
        return this.a;
    }

    public long getReqInterval() {
        switch (this.a) {
            case 1:
                return 14400000;
            case 2:
                return 28800000;
            case 3:
                return 86400000;
            default:
                return 0;
        }
    }

    public long getRetryInterval() {
        return this.a == 0 ? 0 : ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL;
    }

    public void setLevel(int i) {
        if (i >= 0 && i <= 3) {
            this.a = i;
        }
    }

    public boolean isOpen() {
        return this.a != 0;
    }

    public void onImprintChanged(a aVar) {
        setLevel(Integer.valueOf(aVar.a("defcon", String.valueOf(0))).intValue());
    }
}
