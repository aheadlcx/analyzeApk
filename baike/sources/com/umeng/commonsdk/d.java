package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.framework.b;
import com.umeng.commonsdk.internal.a;

final class d implements Runnable {
    final /* synthetic */ Context a;

    d(Context context) {
        this.a = context;
    }

    public void run() {
        try {
            Object a = b.a(this.a);
            CharSequence packageName = this.a.getPackageName();
            if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(packageName) && a.equals(packageName)) {
                try {
                    if (UMEnvelopeBuild.isReadyBuild(this.a, UMBusinessType.U_INTERNAL)) {
                        UMWorkDispatch.sendEvent(this.a, a.m, com.umeng.commonsdk.internal.b.a(this.a).a(), null);
                    }
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
