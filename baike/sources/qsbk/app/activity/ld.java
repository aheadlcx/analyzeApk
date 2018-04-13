package qsbk.app.activity;

class ld implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ lc b;

    ld(lc lcVar, String str) {
        this.b = lcVar;
        this.a = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r6 = this;
        r5 = 3;
        r0 = qsbk.app.utils.HttpClient.getIntentce();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = new java.lang.StringBuilder;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.<init>();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = qsbk.app.Constants.FILLINFO;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = r1.append(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = "?login=";
        r1 = r1.append(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r6.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r3 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r2, r3);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = r1.append(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = r1.toString();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.get(r1);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = new org.json.JSONObject;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.<init>(r0);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = "err";
        r0 = r1.getInt(r0);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        if (r0 != 0) goto L_0x0175;
    L_0x0037:
        r1 = new java.util.HashMap;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.<init>();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = "login";
        r2 = r6.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = "qq";
        r2 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.j;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.equalsIgnoreCase(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        if (r0 == 0) goto L_0x00e1;
    L_0x0053:
        r0 = "type";
        r2 = 1;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
    L_0x005d:
        r0 = "token";
        r2 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.i;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.f;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        if (r0 == 0) goto L_0x0152;
    L_0x0074:
        r0 = "avatar_type";
        r2 = "local";
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.e;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.getPickedBitmap();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2.<init>();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r3 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ Exception -> 0x013c }
        r4 = 95;
        r0 = r0.compress(r3, r4, r2);	 Catch:{ Exception -> 0x013c }
        if (r0 == 0) goto L_0x00ac;
    L_0x0096:
        r0 = r2.toByteArray();	 Catch:{ Exception -> 0x013c }
        r3 = 2;
        r0 = android.util.Base64.encode(r0, r3);	 Catch:{ Exception -> 0x013c }
        r3 = new java.lang.String;	 Catch:{ Exception -> 0x013c }
        r3.<init>(r0);	 Catch:{ Exception -> 0x013c }
        r0 = "avatar";
        r1.put(r0, r3);	 Catch:{ Exception -> 0x013c }
        r2.close();	 Catch:{ Exception -> 0x013c }
    L_0x00ac:
        r0 = qsbk.app.utils.HttpClient.getIntentce();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = qsbk.app.Constants.FILLINFO;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.post(r2, r1);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = r1.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = r1.m;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1 = r1.obtainMessage();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = 2;
        r1.what = r2;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = new android.os.Bundle;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2.<init>();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r3 = "response";
        r2.putString(r3, r0);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.setData(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.sendToTarget();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
    L_0x00d5:
        r0 = r6.b;
        r0 = r0.a;
        r0 = r0.m;
        r0.sendEmptyMessage(r5);
    L_0x00e0:
        return;
    L_0x00e1:
        r0 = "wb";
        r2 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.j;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.equalsIgnoreCase(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        if (r0 == 0) goto L_0x010d;
    L_0x00f1:
        r0 = "type";
        r2 = 2;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        goto L_0x005d;
    L_0x00fd:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0168 }
        r0 = r6.b;
        r0 = r0.a;
        r0 = r0.m;
        r0.sendEmptyMessage(r5);
        goto L_0x00e0;
    L_0x010d:
        r0 = "openid";
        r2 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.getIntent();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r3 = "openid";
        r2 = r2.getStringExtra(r3);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = "type";
        r2 = 3;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        goto L_0x005d;
    L_0x012c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0168 }
        r0 = r6.b;
        r0 = r0.a;
        r0 = r0.m;
        r0.sendEmptyMessage(r5);
        goto L_0x00e0;
    L_0x013c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        goto L_0x00ac;
    L_0x0142:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0168 }
        r0 = r6.b;
        r0 = r0.a;
        r0 = r0.m;
        r0.sendEmptyMessage(r5);
        goto L_0x00e0;
    L_0x0152:
        r0 = "avatar_type";
        r2 = "url";
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = "avatar";
        r2 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = r2.h;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r1.put(r0, r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        goto L_0x00ac;
    L_0x0168:
        r0 = move-exception;
        r1 = r6.b;
        r1 = r1.a;
        r1 = r1.m;
        r1.sendEmptyMessage(r5);
        throw r0;
    L_0x0175:
        r0 = r6.b;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.a;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.m;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0 = r0.obtainMessage();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = 1;
        r0.what = r2;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2 = new android.os.Bundle;	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2.<init>();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r3 = "msg";
        r4 = "err_msg";
        r1 = r1.optString(r4);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r2.putString(r3, r1);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0.setData(r2);	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        r0.sendToTarget();	 Catch:{ QiushibaikeException -> 0x00fd, JSONException -> 0x012c, UnsupportedEncodingException -> 0x0142 }
        goto L_0x00d5;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.activity.ld.run():void");
    }
}
