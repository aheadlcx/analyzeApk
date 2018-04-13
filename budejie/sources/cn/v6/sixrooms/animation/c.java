package cn.v6.sixrooms.animation;

final class c extends Thread {
    final /* synthetic */ GiftAnimationManager a;

    c(GiftAnimationManager giftAnimationManager) {
        this.a = giftAnimationManager;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r10 = this;
        r4 = 3;
        r0 = 2;
        r3 = 1;
        r8 = 30;
        r1 = 0;
        r2 = r10.a;
        r2 = r2.n;
        if (r2 == 0) goto L_0x0162;
    L_0x000e:
        r2 = r10.a;
        r2 = r2.n;
        r2 = r2.getRoomType();
        if (r2 == r4) goto L_0x001c;
    L_0x001a:
        if (r2 != r0) goto L_0x009c;
    L_0x001c:
        r2 = r10.a;
        r2 = r2.m;
        if (r2 == 0) goto L_0x0138;
    L_0x0024:
        r3 = 0;
        r2 = 0;
        r4 = r10.a;	 Catch:{ Exception -> 0x00d1, all -> 0x0107 }
        r4 = r4.f;	 Catch:{ Exception -> 0x00d1, all -> 0x0107 }
        r3 = r4.lockCanvas();	 Catch:{ Exception -> 0x00d1, all -> 0x0107 }
        r4 = 0;
        r5 = android.graphics.PorterDuff.Mode.CLEAR;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r3.drawColor(r4, r5);	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r10.a;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r4.i;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        if (r4 != 0) goto L_0x0053;
    L_0x003e:
        r4 = r10.a;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r5 = new android.graphics.PaintFlagsDrawFilter;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r6 = 0;
        r7 = 3;
        r5.<init>(r6, r7);	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4.i = r5;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r10.a;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r4.i;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r3.setDrawFilter(r4);	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
    L_0x0053:
        r4 = r10.a;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r4.m;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        if (r4 == 0) goto L_0x00a1;
    L_0x005b:
        r4 = r10.a;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r4.m;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r4.length;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        if (r4 <= 0) goto L_0x00a1;
    L_0x0064:
        r4 = r10.a;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r4.m;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r4.length;	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r2 = new int[r4];	 Catch:{ Exception -> 0x0158, all -> 0x0107 }
        r4 = r1;
    L_0x006e:
        r1 = r10.a;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r1.m;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r1.length;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        if (r4 >= r1) goto L_0x00a1;
    L_0x0077:
        r1 = r10.a;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r1.m;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r1[r4];	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r5 = r10.a;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r5 = r5.g;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1.draw(r5, r3, r0);	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r10.a;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r1.m;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r1[r4];	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r1 = r1.isFinish();	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        if (r1 == 0) goto L_0x009f;
    L_0x0096:
        r1 = r4;
    L_0x0097:
        r2[r4] = r1;	 Catch:{ Exception -> 0x015d, all -> 0x0107 }
        r4 = r4 + 1;
        goto L_0x006e;
    L_0x009c:
        r0 = 4;
        goto L_0x001c;
    L_0x009f:
        r1 = -1;
        goto L_0x0097;
    L_0x00a1:
        if (r3 == 0) goto L_0x00b4;
    L_0x00a3:
        r0 = r10.a;	 Catch:{ Exception -> 0x00cc }
        r0 = r0.f;	 Catch:{ Exception -> 0x00cc }
        if (r0 == 0) goto L_0x00b4;
    L_0x00ab:
        r0 = r10.a;	 Catch:{ Exception -> 0x00cc }
        r0 = r0.f;	 Catch:{ Exception -> 0x00cc }
        r0.unlockCanvasAndPost(r3);	 Catch:{ Exception -> 0x00cc }
    L_0x00b4:
        r0 = r10.a;
        cn.v6.sixrooms.animation.GiftAnimationManager.a(r0, r2);
        r0 = r10.a;
        cn.v6.sixrooms.animation.GiftAnimationManager.h(r0);
        r0 = r10.a;
        r0 = r0.c;
        r1 = r10.a;
        r1 = r1.b;
        r0.postDelayed(r1, r8);
    L_0x00cb:
        return;
    L_0x00cc:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b4;
    L_0x00d1:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
    L_0x00d4:
        r0.printStackTrace();	 Catch:{ all -> 0x0154 }
        if (r2 == 0) goto L_0x00ea;
    L_0x00d9:
        r0 = r10.a;	 Catch:{ Exception -> 0x0102 }
        r0 = r0.f;	 Catch:{ Exception -> 0x0102 }
        if (r0 == 0) goto L_0x00ea;
    L_0x00e1:
        r0 = r10.a;	 Catch:{ Exception -> 0x0102 }
        r0 = r0.f;	 Catch:{ Exception -> 0x0102 }
        r0.unlockCanvasAndPost(r2);	 Catch:{ Exception -> 0x0102 }
    L_0x00ea:
        r0 = r10.a;
        cn.v6.sixrooms.animation.GiftAnimationManager.a(r0, r1);
        r0 = r10.a;
        cn.v6.sixrooms.animation.GiftAnimationManager.h(r0);
        r0 = r10.a;
        r0 = r0.c;
        r1 = r10.a;
        r1 = r1.b;
        r0.postDelayed(r1, r8);
        goto L_0x00cb;
    L_0x0102:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00ea;
    L_0x0107:
        r0 = move-exception;
    L_0x0108:
        if (r3 == 0) goto L_0x011b;
    L_0x010a:
        r1 = r10.a;	 Catch:{ Exception -> 0x0133 }
        r1 = r1.f;	 Catch:{ Exception -> 0x0133 }
        if (r1 == 0) goto L_0x011b;
    L_0x0112:
        r1 = r10.a;	 Catch:{ Exception -> 0x0133 }
        r1 = r1.f;	 Catch:{ Exception -> 0x0133 }
        r1.unlockCanvasAndPost(r3);	 Catch:{ Exception -> 0x0133 }
    L_0x011b:
        r1 = r10.a;
        cn.v6.sixrooms.animation.GiftAnimationManager.a(r1, r2);
        r1 = r10.a;
        cn.v6.sixrooms.animation.GiftAnimationManager.h(r1);
        r1 = r10.a;
        r1 = r1.c;
        r2 = r10.a;
        r2 = r2.b;
        r1.postDelayed(r2, r8);
        throw r0;
    L_0x0133:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x011b;
    L_0x0138:
        r0 = r10.a;
        cn.v6.sixrooms.animation.GiftAnimationManager.e(r0);
        r0 = r10.a;
        r0.a = r3;
        r0 = r10.a;
        r0 = r0.j;
        if (r0 == 0) goto L_0x00cb;
    L_0x0149:
        r0 = r10.a;
        r0 = r0.j;
        r0.sendEmptyMessage(r3);
        goto L_0x00cb;
    L_0x0154:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
        goto L_0x0108;
    L_0x0158:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00d4;
    L_0x015d:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00d4;
    L_0x0162:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.animation.c.run():void");
    }
}
