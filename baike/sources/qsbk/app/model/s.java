package qsbk.app.model;

import java.util.HashMap;

final class s extends Thread {
    final /* synthetic */ HashMap a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0040 in list [B:6:0x003d]
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
        r5 = this;
        r0 = qsbk.app.model.UserLoginGuideCard.r;
        r1 = r0.obtainMessage();
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r2 = qsbk.app.utils.HttpClient.getIntentce();	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r3 = qsbk.app.Constants.THIRDPARTY_LOGIN;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r4 = r5.a;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r2 = r2.post(r3, r4);	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        qsbk.app.model.UserLoginGuideCard.a = r0;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0 = qsbk.app.model.UserLoginGuideCard.a;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r2 = "err";	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0 = r0.get(r2);	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0 = (java.lang.Integer) r0;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r2 = r0.intValue();	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r1.what = r2;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0 = r0.intValue();	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        if (r0 == 0) goto L_0x003b;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
    L_0x0031:
        r0 = qsbk.app.model.UserLoginGuideCard.a;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r2 = "err_msg";	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r1.obj = r0;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
    L_0x003b:
        if (r1 == 0) goto L_0x0040;
    L_0x003d:
        r1.sendToTarget();
    L_0x0040:
        return;
    L_0x0041:
        r0 = move-exception;
        r2 = 1;
        r1.what = r2;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r2 = "出现异常，请稍后重试";	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        if (r1 == 0) goto L_0x0040;
    L_0x004e:
        r1.sendToTarget();
        goto L_0x0040;
    L_0x0052:
        r0 = move-exception;
        r2 = 2;
        r1.what = r2;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r2 = r0.getMessage();	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0041, QiushibaikeException -> 0x0052, all -> 0x0065 }
        if (r1 == 0) goto L_0x0040;
    L_0x0061:
        r1.sendToTarget();
        goto L_0x0040;
    L_0x0065:
        r0 = move-exception;
        if (r1 == 0) goto L_0x006b;
    L_0x0068:
        r1.sendToTarget();
    L_0x006b:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.model.s.run():void");
    }

    s(String str, HashMap hashMap) {
        this.a = hashMap;
        super(str);
    }
}
