package com.sina.weibo.sdk.cmd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder;

class d {
    private Context a;
    private a b = new a(this, this.a.getMainLooper());

    private class a extends Handler {
        final /* synthetic */ d a;

        public a(d dVar, Looper looper) {
            this.a = dVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    d.b(this.a.a, (c) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public d(Context context) {
        this.a = context.getApplicationContext();
    }

    public boolean doExecutor(c cVar) {
        if (cVar == null || TextUtils.isEmpty(cVar.getNotificationText()) || TextUtils.isEmpty(cVar.getScheme())) {
            return false;
        }
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = cVar;
        this.b.sendMessageDelayed(obtainMessage, cVar.getNotificationDelay());
        return true;
    }

    private static void b(Context context, c cVar) {
        SDKNotificationBuilder.buildUpon().setNotificationContent(cVar.getNotificationText()).setNotificationPendingIntent(c(context, cVar)).setNotificationTitle(cVar.getNotificationTitle()).setTickerText(cVar.getNotificationText()).build(context).show(2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.app.PendingIntent c(android.content.Context r5, com.sina.weibo.sdk.cmd.c r6) {
        /*
        r0 = 0;
        r1 = r6.getScheme();
        r2 = r6.getUrl();
        r3 = r6.getAppPackage();
        r1 = a(r1, r3);
        if (r1 == 0) goto L_0x003a;
    L_0x0013:
        r3 = r5.getPackageManager();
        r4 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r3 = r3.queryIntentActivities(r1, r4);
        if (r3 == 0) goto L_0x003a;
    L_0x001f:
        r3 = r3.isEmpty();
        if (r3 != 0) goto L_0x003a;
    L_0x0025:
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r1 = a(r2);
    L_0x002b:
        if (r1 == 0) goto L_0x0039;
    L_0x002d:
        r0 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r1.setFlags(r0);
        r0 = 0;
        r2 = 134217728; // 0x8000000 float:3.85186E-34 double:6.63123685E-316;
        r0 = android.app.PendingIntent.getActivity(r5, r0, r1, r2);
    L_0x0039:
        return r0;
    L_0x003a:
        r1 = r0;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.cmd.d.c(android.content.Context, com.sina.weibo.sdk.cmd.c):android.app.PendingIntent");
    }

    private static Intent a(String str, String str2) {
        if (TextUtils.isEmpty(str) || !Uri.parse(str).isHierarchical()) {
            return null;
        }
        Uri parse = Uri.parse(str);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(parse);
        intent.setPackage(str2);
        return intent;
    }

    private static Intent a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https")) {
            return null;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(parse);
        return intent;
    }
}
