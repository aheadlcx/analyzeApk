package qsbk.app.activity.security;

class l extends Thread {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004d in list [B:6:0x004a]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r6 = this;
        r0 = r6.a;
        r0 = r0.b;
        r1 = r0.obtainMessage();
        r0 = r6.a;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r3 = qsbk.app.utils.HttpClient.getIntentce();	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r4 = qsbk.app.Constants.REGISTER;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r5 = r6.a;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r5 = r5.a;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r3 = r3.post(r4, r5);	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r2.<init>(r3);	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0.s = r2;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0 = r6.a;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0 = r0.s;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r2 = "err";	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0 = r0.get(r2);	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0 = (java.lang.Integer) r0;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r2 = r0.intValue();	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r1.what = r2;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0 = r0.intValue();	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        if (r0 == 0) goto L_0x0048;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
    L_0x003a:
        r0 = r6.a;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0 = r0.s;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r2 = "err_msg";	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r1.obj = r0;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
    L_0x0048:
        if (r1 == 0) goto L_0x004d;
    L_0x004a:
        r1.sendToTarget();
    L_0x004d:
        return;
    L_0x004e:
        r0 = move-exception;
        r2 = 1;
        r1.what = r2;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r2 = "返回数据异常，请重试";	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        if (r1 == 0) goto L_0x004d;
    L_0x005b:
        r1.sendToTarget();
        goto L_0x004d;
    L_0x005f:
        r0 = move-exception;
        r2 = 2;
        r1.what = r2;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r2 = r0.getMessage();	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x004e, QiushibaikeException -> 0x005f, all -> 0x0072 }
        if (r1 == 0) goto L_0x004d;
    L_0x006e:
        r1.sendToTarget();
        goto L_0x004d;
    L_0x0072:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0078;
    L_0x0075:
        r1.sendToTarget();
    L_0x0078:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.activity.security.l.run():void");
    }

    l(ActionBarSecurityBindActivity actionBarSecurityBindActivity, String str) {
        this.a = actionBarSecurityBindActivity;
        super(str);
    }
}
