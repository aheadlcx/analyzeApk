package qsbk.app.live.ui;

class bi implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0045 in list [B:13:0x0040]
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
        r6 = this;
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0 = r6.a;
        r0 = r0.mHandler;
        r0.removeCallbacks(r6);
        r0 = r6.a;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r0 = r0.S;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r0 = r0.poll();	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r0 = (qsbk.app.live.model.LiveLoveMessage) r0;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        if (r0 == 0) goto L_0x0036;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
    L_0x0015:
        r1 = r0.getLiveMessageContent();	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        if (r1 == 0) goto L_0x0036;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
    L_0x001b:
        r2 = r6.a;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r2 = r2.isMessageOverloadOrLowDevice();	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        if (r2 == 0) goto L_0x0027;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
    L_0x0023:
        r2 = r1.e;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        if (r2 != 0) goto L_0x0036;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
    L_0x0027:
        r2 = r6.a;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r2 = r2.ab;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r0 = r0.getLoveColor();	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r3 = r1.l;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r1 = r1.e;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r2.addFavor(r0, r3, r1);	 Catch:{ Exception -> 0x0046, all -> 0x005e }
    L_0x0036:
        r0 = r6.a;
        r0 = r0.S;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0045;
    L_0x0040:
        r0 = r6.a;
        qsbk.app.live.ui.LiveBaseActivity.d(r0, r6, r4);
    L_0x0045:
        return;
    L_0x0046:
        r0 = move-exception;
        r1 = qsbk.app.live.ui.LiveBaseActivity.a;	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r2 = "live show love msg error";	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        qsbk.app.core.utils.LogUtils.e(r1, r2, r0);	 Catch:{ Exception -> 0x0046, all -> 0x005e }
        r0 = r6.a;
        r0 = r0.S;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0045;
    L_0x0058:
        r0 = r6.a;
        qsbk.app.live.ui.LiveBaseActivity.d(r0, r6, r4);
        goto L_0x0045;
    L_0x005e:
        r0 = move-exception;
        r1 = r6.a;
        r1 = r1.S;
        r1 = r1.isEmpty();
        if (r1 != 0) goto L_0x006e;
    L_0x0069:
        r1 = r6.a;
        qsbk.app.live.ui.LiveBaseActivity.d(r1, r6, r4);
    L_0x006e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.live.ui.bi.run():void");
    }

    bi(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }
}
