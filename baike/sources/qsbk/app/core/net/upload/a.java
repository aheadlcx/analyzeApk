package qsbk.app.core.net.upload;

import com.qiniu.android.storage.UpCompletionHandler;

class a implements UpCompletionHandler {
    final /* synthetic */ String a;
    final /* synthetic */ Upload b;

    a(Upload upload, String str) {
        this.b = upload;
        this.a = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void complete(java.lang.String r5, com.qiniu.android.http.ResponseInfo r6, org.json.JSONObject r7) {
        /*
        r4 = this;
        r0 = qsbk.app.core.net.upload.Upload.a;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "err:";
        r1 = r1.append(r2);
        r2 = r6.error;
        r1 = r1.append(r2);
        r1 = r1.toString();
        qsbk.app.core.utils.LogUtils.d(r0, r1);
        r1 = r4.a;
        if (r7 == 0) goto L_0x0044;
    L_0x0020:
        r0 = "headurl";
        r0 = r7.optString(r0);
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 != 0) goto L_0x0044;
    L_0x002c:
        r1 = r4.b;
        r1 = r1.c;
        if (r1 == 0) goto L_0x0043;
    L_0x0034:
        r1 = r4.b;
        r1 = r1.c;
        r2 = r6.isOK();
        r3 = r6.error;
        r1.uploadStat(r0, r2, r3, r7);
    L_0x0043:
        return;
    L_0x0044:
        r0 = r1;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.core.net.upload.a.complete(java.lang.String, com.qiniu.android.http.ResponseInfo, org.json.JSONObject):void");
    }
}
