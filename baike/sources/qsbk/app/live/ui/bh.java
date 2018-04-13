package qsbk.app.live.ui;

class bh implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00b9 in list [B:51:0x00f4]
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
        r3 = 1;
        r2 = 0;
        r0 = r10.a;
        r0 = r0.mHandler;
        r1 = r10.a;
        r1 = qsbk.app.live.ui.LiveBaseActivity.w(r1);
        r0.removeCallbacks(r1);
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r6 = r0.R;	 Catch:{ Exception -> 0x00e2 }
        monitor-enter(r6);	 Catch:{ Exception -> 0x00e2 }
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.P;	 Catch:{ Exception -> 0x00e2 }
        r7 = r0.size();	 Catch:{ Exception -> 0x00e2 }
        r4 = r2;	 Catch:{ Exception -> 0x00e2 }
        r5 = r2;	 Catch:{ Exception -> 0x00e2 }
    L_0x001e:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.R;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.isEmpty();	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x007e;	 Catch:{ Exception -> 0x00e2 }
    L_0x0028:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.R;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.poll();	 Catch:{ Exception -> 0x00e2 }
        r0 = (qsbk.app.live.model.LiveMessage) r0;	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x01a4;	 Catch:{ Exception -> 0x00e2 }
    L_0x0034:
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = qsbk.app.live.ui.LiveBaseActivity.a(r1, r0);	 Catch:{ Exception -> 0x00e2 }
        if (r1 != 0) goto L_0x01a4;	 Catch:{ Exception -> 0x00e2 }
    L_0x003c:
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.P;	 Catch:{ Exception -> 0x00e2 }
        r8 = r1.size();	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;	 Catch:{ Exception -> 0x00e2 }
        if (r8 <= 0) goto L_0x0053;	 Catch:{ Exception -> 0x00e2 }
    L_0x0047:
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.P;	 Catch:{ Exception -> 0x00e2 }
        r9 = r8 + -1;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.get(r9);	 Catch:{ Exception -> 0x00e2 }
        r1 = (qsbk.app.live.model.LiveMessage) r1;	 Catch:{ Exception -> 0x00e2 }
    L_0x0053:
        r9 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = qsbk.app.live.ui.LiveBaseActivity.a(r9, r0, r1);	 Catch:{ Exception -> 0x00e2 }
        if (r1 == 0) goto L_0x0074;	 Catch:{ Exception -> 0x00e2 }
    L_0x005b:
        if (r7 != r8) goto L_0x01a1;	 Catch:{ Exception -> 0x00e2 }
    L_0x005d:
        r1 = r3;	 Catch:{ Exception -> 0x00e2 }
    L_0x005e:
        r2 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.P;	 Catch:{ Exception -> 0x00e2 }
        r8 = r8 + -1;	 Catch:{ Exception -> 0x00e2 }
        r2.set(r8, r0);	 Catch:{ Exception -> 0x00e2 }
        r2 = r5;	 Catch:{ Exception -> 0x00e2 }
    L_0x0068:
        r0 = r0.isHistoryCommentMessage();	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x019d;	 Catch:{ Exception -> 0x00e2 }
    L_0x006e:
        r0 = r1;	 Catch:{ Exception -> 0x00e2 }
        r1 = r3;	 Catch:{ Exception -> 0x00e2 }
    L_0x0070:
        r4 = r1;	 Catch:{ Exception -> 0x00e2 }
        r5 = r2;	 Catch:{ Exception -> 0x00e2 }
        r2 = r0;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x001e;	 Catch:{ Exception -> 0x00e2 }
    L_0x0074:
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.P;	 Catch:{ Exception -> 0x00e2 }
        r1.add(r0);	 Catch:{ Exception -> 0x00e2 }
        r1 = r2;	 Catch:{ Exception -> 0x00e2 }
        r2 = r3;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0068;	 Catch:{ Exception -> 0x00e2 }
    L_0x007e:
        if (r4 == 0) goto L_0x00ba;	 Catch:{ Exception -> 0x00e2 }
    L_0x0080:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.O;	 Catch:{ Exception -> 0x00e2 }
        r0.notifyDataSetChanged();	 Catch:{ Exception -> 0x00e2 }
    L_0x0087:
        monitor-exit(r6);	 Catch:{ Exception -> 0x00e2 }
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = qsbk.app.live.ui.LiveBaseActivity.c(r0);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x0126;	 Catch:{ Exception -> 0x00e2 }
    L_0x0090:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.U;	 Catch:{ Exception -> 0x00e2 }
        r1 = 8;	 Catch:{ Exception -> 0x00e2 }
        r0.setVisibility(r1);	 Catch:{ Exception -> 0x00e2 }
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        qsbk.app.live.ui.LiveBaseActivity.x(r0);	 Catch:{ Exception -> 0x00e2 }
    L_0x009e:
        r0 = r10.a;
        r0 = r0.R;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x00b9;
    L_0x00a8:
        r0 = r10.a;
        r1 = r10.a;
        r1 = qsbk.app.live.ui.LiveBaseActivity.w(r1);
        r2 = r10.a;
        r2 = r2.E();
        qsbk.app.live.ui.LiveBaseActivity.c(r0, r1, r2);
    L_0x00b9:
        return;
    L_0x00ba:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.P;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.size();	 Catch:{ Exception -> 0x00e2 }
        r1 = 400; // 0x190 float:5.6E-43 double:1.976E-321;	 Catch:{ Exception -> 0x00e2 }
        if (r0 <= r1) goto L_0x00cb;	 Catch:{ Exception -> 0x00e2 }
    L_0x00c6:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0.removeOldMessages();	 Catch:{ Exception -> 0x00e2 }
    L_0x00cb:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.P;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.size();	 Catch:{ Exception -> 0x00e2 }
        if (r0 != r7) goto L_0x0106;	 Catch:{ Exception -> 0x00e2 }
    L_0x00d5:
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.O;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0 + -1;	 Catch:{ Exception -> 0x00e2 }
        r1.notifyItemChanged(r0);	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0087;	 Catch:{ Exception -> 0x00e2 }
    L_0x00df:
        r0 = move-exception;	 Catch:{ Exception -> 0x00e2 }
        monitor-exit(r6);	 Catch:{ Exception -> 0x00e2 }
        throw r0;	 Catch:{ Exception -> 0x00e2 }
    L_0x00e2:
        r0 = move-exception;
        r1 = qsbk.app.live.ui.LiveBaseActivity.a;	 Catch:{ all -> 0x015e }
        r2 = "live show msg error";	 Catch:{ all -> 0x015e }
        qsbk.app.core.utils.LogUtils.e(r1, r2, r0);	 Catch:{ all -> 0x015e }
        r0 = r10.a;
        r0 = r0.R;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x00b9;
    L_0x00f4:
        r0 = r10.a;
        r1 = r10.a;
        r1 = qsbk.app.live.ui.LiveBaseActivity.w(r1);
        r2 = r10.a;
        r2 = r2.E();
        qsbk.app.live.ui.LiveBaseActivity.c(r0, r1, r2);
        goto L_0x00b9;
    L_0x0106:
        if (r0 <= r7) goto L_0x011d;
    L_0x0108:
        if (r2 == 0) goto L_0x0113;
    L_0x010a:
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.O;	 Catch:{ Exception -> 0x00e2 }
        r2 = r7 + -1;	 Catch:{ Exception -> 0x00e2 }
        r1.notifyItemChanged(r2);	 Catch:{ Exception -> 0x00e2 }
    L_0x0113:
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.O;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0 - r7;	 Catch:{ Exception -> 0x00e2 }
        r1.notifyItemRangeInserted(r7, r0);	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0087;	 Catch:{ Exception -> 0x00e2 }
    L_0x011d:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.O;	 Catch:{ Exception -> 0x00e2 }
        r0.notifyDataSetChanged();	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0087;
    L_0x0126:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.L;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.getVisibility();	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x017b;	 Catch:{ Exception -> 0x00e2 }
    L_0x0130:
        if (r5 == 0) goto L_0x017b;	 Catch:{ Exception -> 0x00e2 }
    L_0x0132:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.isMessageOverloadOrLowDevice();	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x017b;	 Catch:{ Exception -> 0x00e2 }
    L_0x013a:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.U;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;	 Catch:{ Exception -> 0x00e2 }
        r0.setVisibility(r1);	 Catch:{ Exception -> 0x00e2 }
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.mHandler;	 Catch:{ Exception -> 0x00e2 }
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = qsbk.app.live.ui.LiveBaseActivity.d(r1);	 Catch:{ Exception -> 0x00e2 }
        r0.removeCallbacks(r1);	 Catch:{ Exception -> 0x00e2 }
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = qsbk.app.live.ui.LiveBaseActivity.d(r1);	 Catch:{ Exception -> 0x00e2 }
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;	 Catch:{ Exception -> 0x00e2 }
        qsbk.app.live.ui.LiveBaseActivity.b(r0, r1, r2);	 Catch:{ Exception -> 0x00e2 }
        goto L_0x009e;
    L_0x015e:
        r0 = move-exception;
        r1 = r10.a;
        r1 = r1.R;
        r1 = r1.isEmpty();
        if (r1 != 0) goto L_0x017a;
    L_0x0169:
        r1 = r10.a;
        r2 = r10.a;
        r2 = qsbk.app.live.ui.LiveBaseActivity.w(r2);
        r3 = r10.a;
        r4 = r3.E();
        qsbk.app.live.ui.LiveBaseActivity.c(r1, r2, r4);
    L_0x017a:
        throw r0;
    L_0x017b:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.L;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.getVisibility();	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x009e;	 Catch:{ Exception -> 0x00e2 }
    L_0x0185:
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.U;	 Catch:{ Exception -> 0x00e2 }
        r1 = 8;	 Catch:{ Exception -> 0x00e2 }
        r0.setVisibility(r1);	 Catch:{ Exception -> 0x00e2 }
        r0 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.mHandler;	 Catch:{ Exception -> 0x00e2 }
        r1 = r10.a;	 Catch:{ Exception -> 0x00e2 }
        r1 = qsbk.app.live.ui.LiveBaseActivity.d(r1);	 Catch:{ Exception -> 0x00e2 }
        r0.removeCallbacks(r1);	 Catch:{ Exception -> 0x00e2 }
        goto L_0x009e;
    L_0x019d:
        r0 = r1;
        r1 = r4;
        goto L_0x0070;
    L_0x01a1:
        r1 = r2;
        goto L_0x005e;
    L_0x01a4:
        r0 = r2;
        r1 = r4;
        r2 = r5;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.live.ui.bh.run():void");
    }

    bh(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }
}
