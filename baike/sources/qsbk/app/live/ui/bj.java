package qsbk.app.live.ui;

class bj implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0072 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r10 = this;
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = r10.a;
        r0 = r0.mHandler;
        r0.removeCallbacks(r10);
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r0.T;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r1 = r0.iterator();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x0015:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        if (r0 == 0) goto L_0x0073;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x001b:
        r0 = r1.next();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = (qsbk.app.live.model.LiveGiftMessage) r0;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        if (r0 == 0) goto L_0x0015;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x0023:
        r4 = r0.getShowTime();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r4 = r2 - r4;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r6 = 3200; // 0xc80 float:4.484E-42 double:1.581E-320;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        if (r4 < 0) goto L_0x0015;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x002f:
        r1.remove();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r4 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r4 = r4.R;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r4.add(r0);	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r4 = r0.getGiftCount();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r5 = 999; // 0x3e7 float:1.4E-42 double:4.936E-321;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        if (r4 < r5) goto L_0x0015;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x0041:
        r4 = r0.getGiftId();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r6 = 9;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        if (r0 != 0) goto L_0x0015;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x004b:
        r0 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r0.isMessageOverloadOrLowDevice();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        if (r0 != 0) goto L_0x0015;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x0053:
        r0 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r0.ag;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0.showFullScreenAnimation();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        goto L_0x0015;
    L_0x005b:
        r0 = move-exception;
        r1 = qsbk.app.live.ui.LiveBaseActivity.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r2 = "live show gift msg error";	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        qsbk.app.core.utils.LogUtils.e(r1, r2, r0);	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r10.a;
        r0 = r0.T;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0072;
    L_0x006d:
        r0 = r10.a;
        qsbk.app.live.ui.LiveBaseActivity.f(r0, r10, r8);
    L_0x0072:
        return;
    L_0x0073:
        r2 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r3 = qsbk.app.live.ui.LiveBaseActivity.w(r0);	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r0.R;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r0.isEmpty();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        if (r0 == 0) goto L_0x009a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x0085:
        r0 = 0;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
    L_0x0087:
        qsbk.app.live.ui.LiveBaseActivity.e(r2, r3, r0);	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r10.a;
        r0 = r0.T;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0072;
    L_0x0094:
        r0 = r10.a;
        qsbk.app.live.ui.LiveBaseActivity.f(r0, r10, r8);
        goto L_0x0072;
    L_0x009a:
        r0 = r10.a;	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        r0 = r0.E();	 Catch:{ Exception -> 0x005b, all -> 0x00a1 }
        goto L_0x0087;
    L_0x00a1:
        r0 = move-exception;
        r1 = r10.a;
        r1 = r1.T;
        r1 = r1.isEmpty();
        if (r1 != 0) goto L_0x00b1;
    L_0x00ac:
        r1 = r10.a;
        qsbk.app.live.ui.LiveBaseActivity.f(r1, r10, r8);
    L_0x00b1:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.live.ui.bj.run():void");
    }

    bj(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }
}
