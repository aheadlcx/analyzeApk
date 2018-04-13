package com.tencent.bugly.beta.global;

import android.view.View.OnTouchListener;

public class c implements OnTouchListener {
    final int a;
    final Object[] b;

    public c(int i, Object... objArr) {
        this.a = i;
        this.b = objArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r4, android.view.MotionEvent r5) {
        /*
        r3 = this;
        r2 = 0;
        r0 = r3.a;	 Catch:{ Exception -> 0x0019 }
        switch(r0) {
            case 1: goto L_0x0007;
            default: goto L_0x0006;
        };	 Catch:{ Exception -> 0x0019 }
    L_0x0006:
        return r2;
    L_0x0007:
        r0 = r5.getAction();	 Catch:{ Exception -> 0x0019 }
        switch(r0) {
            case 0: goto L_0x0024;
            case 1: goto L_0x000e;
            case 2: goto L_0x0024;
            default: goto L_0x000e;
        };	 Catch:{ Exception -> 0x0019 }
    L_0x000e:
        r0 = r3.b;	 Catch:{ Exception -> 0x0019 }
        r1 = 1;
        r0 = r0[r1];	 Catch:{ Exception -> 0x0019 }
        r0 = (android.graphics.drawable.Drawable) r0;	 Catch:{ Exception -> 0x0019 }
        r4.setBackgroundDrawable(r0);	 Catch:{ Exception -> 0x0019 }
        goto L_0x0006;
    L_0x0019:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.b(r0);
        if (r1 != 0) goto L_0x0006;
    L_0x0020:
        r0.printStackTrace();
        goto L_0x0006;
    L_0x0024:
        r0 = r3.b;	 Catch:{ Exception -> 0x0019 }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ Exception -> 0x0019 }
        r0 = (android.graphics.drawable.Drawable) r0;	 Catch:{ Exception -> 0x0019 }
        r4.setBackgroundDrawable(r0);	 Catch:{ Exception -> 0x0019 }
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.global.c.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
