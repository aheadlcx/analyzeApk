package qsbk.app.activity;

import java.util.HashMap;

class ad extends Thread {
    final /* synthetic */ HashMap a;
    final /* synthetic */ ActionBarLoginActivity b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0048 in list [B:6:0x0045]
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
        r0 = r6.b;
        r0 = qsbk.app.activity.ActionBarLoginActivity.m(r0);
        r1 = r0.obtainMessage();
        r0 = r6.b;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r3 = qsbk.app.utils.HttpClient.getIntentce();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r4 = qsbk.app.Constants.THIRDPARTY_LOGIN;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r5 = r6.a;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r3 = r3.post(r4, r5);	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2.<init>(r3);	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0.b = r2;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0 = r6.b;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0 = r0.b;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = "err";	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0 = r0.get(r2);	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0 = (java.lang.Integer) r0;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = r0.intValue();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r1.what = r2;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0 = r0.intValue();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        if (r0 == 0) goto L_0x0043;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
    L_0x0037:
        r0 = r6.b;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0 = r0.b;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = "err_msg";	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r1.obj = r0;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
    L_0x0043:
        if (r1 == 0) goto L_0x0048;
    L_0x0045:
        r1.sendToTarget();
    L_0x0048:
        return;
    L_0x0049:
        r0 = move-exception;
        r2 = 1;
        r1.what = r2;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2.<init>();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r3 = r6.b;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r3 = r3.c();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r3 = "出现异常，请稍后重试";	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        if (r1 == 0) goto L_0x0048;
    L_0x006d:
        r1.sendToTarget();
        goto L_0x0048;
    L_0x0071:
        r0 = move-exception;
        r2 = 2;
        r1.what = r2;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r2 = r0.getMessage();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0049, QiushibaikeException -> 0x0071, all -> 0x0084 }
        if (r1 == 0) goto L_0x0048;
    L_0x0080:
        r1.sendToTarget();
        goto L_0x0048;
    L_0x0084:
        r0 = move-exception;
        if (r1 == 0) goto L_0x008a;
    L_0x0087:
        r1.sendToTarget();
    L_0x008a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.activity.ad.run():void");
    }

    ad(ActionBarLoginActivity actionBarLoginActivity, String str, HashMap hashMap) {
        this.b = actionBarLoginActivity;
        this.a = hashMap;
        super(str);
    }
}
