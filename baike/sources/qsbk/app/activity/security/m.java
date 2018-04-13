package qsbk.app.activity.security;

import android.os.Message;
import java.util.Map;

class m extends Thread {
    final /* synthetic */ Map a;
    final /* synthetic */ Message b;
    final /* synthetic */ ActionBarSecurityBindActivity c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004b in list [B:6:0x0046]
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
        r0 = r5.c;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = qsbk.app.utils.HttpClient.getIntentce();	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r3 = qsbk.app.Constants.UPDATE_USERINFO;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r4 = r5.a;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = r2.post(r3, r4);	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1.<init>(r2);	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0.s = r1;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0 = r5.c;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0 = r0.s;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = "err";	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0 = r0.get(r1);	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0 = (java.lang.Integer) r0;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = r0.intValue();	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1.what = r2;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0 = r0.intValue();	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        if (r0 == 0) goto L_0x0042;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
    L_0x0032:
        r0 = r5.b;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = r5.c;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = r1.s;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = "err_msg";	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = r1.getString(r2);	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0.obj = r1;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
    L_0x0042:
        r0 = r5.b;
        if (r0 == 0) goto L_0x004b;
    L_0x0046:
        r0 = r5.b;
        r0.sendToTarget();
    L_0x004b:
        return;
    L_0x004c:
        r0 = move-exception;
        r1 = r5.b;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = 1;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1.what = r2;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = "绑定失败，请重试！";	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0 = r5.b;
        if (r0 == 0) goto L_0x004b;
    L_0x005f:
        r0 = r5.b;
        r0.sendToTarget();
        goto L_0x004b;
    L_0x0065:
        r0 = move-exception;
        r1 = r5.b;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = 2;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1.what = r2;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r2 = r0.getMessage();	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r1.obj = r2;	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0.printStackTrace();	 Catch:{ JSONException -> 0x004c, QiushibaikeException -> 0x0065, all -> 0x0080 }
        r0 = r5.b;
        if (r0 == 0) goto L_0x004b;
    L_0x007a:
        r0 = r5.b;
        r0.sendToTarget();
        goto L_0x004b;
    L_0x0080:
        r0 = move-exception;
        r1 = r5.b;
        if (r1 == 0) goto L_0x008a;
    L_0x0085:
        r1 = r5.b;
        r1.sendToTarget();
    L_0x008a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.activity.security.m.run():void");
    }

    m(ActionBarSecurityBindActivity actionBarSecurityBindActivity, String str, Map map, Message message) {
        this.c = actionBarSecurityBindActivity;
        this.a = map;
        this.b = message;
        super(str);
    }
}
