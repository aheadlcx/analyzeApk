package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a;
import com.umeng.commonsdk.statistics.internal.d;

class e implements d {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public void onImprintChanged(a aVar) {
        this.a.g.onImprintChanged(aVar);
        this.a.i.onImprintChanged(aVar);
        this.a.h.onImprintChanged(aVar);
        this.a.a = UMEnvelopeBuild.imprintProperty(this.a.m, "track_list", null);
        try {
            if (!TextUtils.isEmpty(com.umeng.commonsdk.framework.a.a(this.a.m, g.e, null))) {
                try {
                    Class cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                    if (cls != null) {
                        cls.getMethod("updateUMTT", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{this.a.m, r0});
                    }
                } catch (ClassNotFoundException e) {
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
