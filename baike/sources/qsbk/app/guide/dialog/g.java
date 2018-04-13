package qsbk.app.guide.dialog;

import java.util.HashMap;

class g extends Thread {
    final /* synthetic */ HashMap a;
    final /* synthetic */ LoginGuideDialog b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0067 in list [B:6:0x003f]
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
        r0 = r5.b;
        r0 = r0.u;
        r1 = r0.obtainMessage();
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r2 = qsbk.app.utils.HttpClient.getIntentce();	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r3 = qsbk.app.Constants.THIRDPARTY_LOGIN;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r4 = r5.a;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r2 = r2.post(r3, r4);	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        qsbk.app.guide.dialog.LoginGuideDialog.a = r0;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0 = qsbk.app.guide.dialog.LoginGuideDialog.a;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r2 = "err";	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0 = r0.get(r2);	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0 = (java.lang.Integer) r0;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r2 = r0.intValue();	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r1.what = r2;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0 = r0.intValue();	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        if (r0 == 0) goto L_0x003d;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
    L_0x0033:
        r0 = qsbk.app.guide.dialog.LoginGuideDialog.a;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r2 = "err_msg";	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r1.obj = r0;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
    L_0x003d:
        if (r1 == 0) goto L_0x0067;
    L_0x003f:
        r0 = r1.what;
        if (r0 != 0) goto L_0x0064;
    L_0x0043:
        r0 = "luolong";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = qsbk.app.guide.dialog.LoginGuideDialog.c;
        r2 = r2.append(r3);
        r3 = " login success";
        r2 = r2.append(r3);
        r2 = r2.toString();
        qsbk.app.utils.DebugUtil.debug(r0, r2);
        r0 = r5.b;
        r0.dismiss();
    L_0x0064:
        r1.sendToTarget();
    L_0x0067:
        return;
    L_0x0068:
        r0 = move-exception;
        r2 = 1;
        r1.what = r2;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r2 = "出现异常，请稍后重试";	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        if (r1 == 0) goto L_0x0067;
    L_0x0075:
        r0 = r1.what;
        if (r0 != 0) goto L_0x009a;
    L_0x0079:
        r0 = "luolong";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = qsbk.app.guide.dialog.LoginGuideDialog.c;
        r2 = r2.append(r3);
        r3 = " login success";
        r2 = r2.append(r3);
        r2 = r2.toString();
        qsbk.app.utils.DebugUtil.debug(r0, r2);
        r0 = r5.b;
        r0.dismiss();
    L_0x009a:
        r1.sendToTarget();
        goto L_0x0067;
    L_0x009e:
        r0 = move-exception;
        r2 = 2;
        r1.what = r2;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r2 = r0.getMessage();	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x0068, QiushibaikeException -> 0x009e, all -> 0x00d6 }
        if (r1 == 0) goto L_0x0067;
    L_0x00ad:
        r0 = r1.what;
        if (r0 != 0) goto L_0x00d2;
    L_0x00b1:
        r0 = "luolong";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = qsbk.app.guide.dialog.LoginGuideDialog.c;
        r2 = r2.append(r3);
        r3 = " login success";
        r2 = r2.append(r3);
        r2 = r2.toString();
        qsbk.app.utils.DebugUtil.debug(r0, r2);
        r0 = r5.b;
        r0.dismiss();
    L_0x00d2:
        r1.sendToTarget();
        goto L_0x0067;
    L_0x00d6:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0101;
    L_0x00d9:
        r2 = r1.what;
        if (r2 != 0) goto L_0x00fe;
    L_0x00dd:
        r2 = "luolong";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = qsbk.app.guide.dialog.LoginGuideDialog.c;
        r3 = r3.append(r4);
        r4 = " login success";
        r3 = r3.append(r4);
        r3 = r3.toString();
        qsbk.app.utils.DebugUtil.debug(r2, r3);
        r2 = r5.b;
        r2.dismiss();
    L_0x00fe:
        r1.sendToTarget();
    L_0x0101:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.guide.dialog.g.run():void");
    }

    g(LoginGuideDialog loginGuideDialog, String str, HashMap hashMap) {
        this.b = loginGuideDialog;
        this.a = hashMap;
        super(str);
    }
}
