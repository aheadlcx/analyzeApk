package com.sina.weibo.sdk.cmd;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.sina.weibo.BuildConfig;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.auth.WbAppInfo;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder;
import java.io.File;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class b {
    private static final String a = (Environment.getExternalStorageDirectory() + "/Android/org_share_data/");
    private static final String b = b.class.getName();
    private Context c;
    private HandlerThread d;
    private Looper e;
    private a f;
    private boolean g = false;

    private class a extends Handler {
        final /* synthetic */ b a;

        public a(b bVar, Looper looper) {
            this.a = bVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.a((a) message.obj);
                    return;
                case 2:
                    this.a.e.quit();
                    this.a.g = false;
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.sina.weibo.sdk.cmd.a r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0008 in list [B:22:0x0078]
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
        r8 = this;
        r0 = r8.c;
        r0 = a(r0, r9);
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r1 = a;
        r2 = r9.getDownloadUrl();
        r4 = r9.getAppVersion();
        r0 = r8.c;
        r3 = a(r0, r1, r9);
        if (r3 == 0) goto L_0x003a;
    L_0x001b:
        r0 = r3.second;
        if (r0 == 0) goto L_0x003a;
    L_0x001f:
        r0 = r3.first;
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r6 = (long) r0;
        r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x003a;
    L_0x002c:
        r1 = r8.c;
        r0 = r3.second;
        r0 = (java.io.File) r0;
        r0 = r0.getAbsolutePath();
        a(r1, r9, r0);
        goto L_0x0008;
    L_0x003a:
        r0 = r8.c;
        r0 = com.sina.weibo.sdk.utils.NetworkHelper.isWifiValid(r0);
        if (r0 == 0) goto L_0x0008;
    L_0x0042:
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 != 0) goto L_0x0008;
    L_0x0048:
        r3 = "";
        r0 = r8.c;	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r4 = "GET";	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r5 = new com.sina.weibo.sdk.net.WeiboParameters;	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r6 = "";	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r5.<init>(r6);	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r0 = com.sina.weibo.sdk.net.NetUtils.internalGetRedirectUri(r0, r2, r4, r5);	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r2 = a(r0);	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        if (r4 != 0) goto L_0x006b;	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
    L_0x0063:
        r4 = ".apk";	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r4 = r2.endsWith(r4);	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        if (r4 != 0) goto L_0x007e;	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
    L_0x006b:
        r0 = b;	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r1 = "redirectDownloadUrl is illeagle";	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        com.sina.weibo.sdk.utils.LogUtil.e(r0, r1);	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 != 0) goto L_0x0008;
    L_0x0078:
        r0 = r8.c;
        a(r0, r9, r3);
        goto L_0x0008;
    L_0x007e:
        r4 = r8.c;	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r0 = com.sina.weibo.sdk.net.NetUtils.internalDownloadFile(r4, r0, r1, r2);	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0008;
    L_0x008a:
        r1 = r8.c;
        a(r1, r9, r0);
        goto L_0x0008;
    L_0x0091:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ WeiboException -> 0x0091, all -> 0x00a2 }
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 != 0) goto L_0x0008;
    L_0x009b:
        r0 = r8.c;
        a(r0, r9, r3);
        goto L_0x0008;
    L_0x00a2:
        r0 = move-exception;
        r1 = android.text.TextUtils.isEmpty(r3);
        if (r1 != 0) goto L_0x00ae;
    L_0x00a9:
        r1 = r8.c;
        a(r1, r9, r3);
    L_0x00ae:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.cmd.b.a(com.sina.weibo.sdk.cmd.a):void");
    }

    public b(Context context) {
        this.c = context.getApplicationContext();
    }

    private static boolean a(Context context, a aVar) {
        List<String> appPackage = aVar.getAppPackage();
        if (appPackage == null || appPackage.size() == 0 || TextUtils.isEmpty(aVar.getAppSign()) || TextUtils.isEmpty(aVar.getDownloadUrl()) || TextUtils.isEmpty(aVar.getNotificationText())) {
            return false;
        }
        if (appPackage.contains(BuildConfig.APPLICATION_ID)) {
            WbAppInfo wbAppInfo = WeiboAppManager.getInstance(context).getWbAppInfo();
            boolean z = wbAppInfo == null || !wbAppInfo.isLegal();
            return z;
        }
        for (String a : appPackage) {
            if (a(context, a)) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (context.getPackageManager().getPackageInfo(str, 1) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public void start() {
        if (!this.g) {
            this.g = true;
            this.d = new HandlerThread("");
            this.d.start();
            this.e = this.d.getLooper();
            this.f = new a(this, this.e);
        }
    }

    public void stop() {
        if (this.d == null || this.f == null) {
            LogUtil.w(b, "no thread running. please call start method first!");
            return;
        }
        Message obtainMessage = this.f.obtainMessage();
        obtainMessage.what = 2;
        this.f.sendMessage(obtainMessage);
    }

    public boolean doExecutor(a aVar) {
        if (this.d == null || this.f == null) {
            throw new RuntimeException("no thread running. please call start method first!");
        }
        if (aVar != null) {
            Message obtainMessage = this.f.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = aVar;
            this.f.sendMessage(obtainMessage);
        }
        return false;
    }

    private static Pair<Integer, File> a(Context context, String str, a aVar) {
        Object obj = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        int length = listFiles.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            File file2 = listFiles[i];
            String name = file2.getName();
            if (file2.isFile() && name.endsWith(".apk")) {
                PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file2.getAbsolutePath(), 64);
                if (!a(packageArchiveInfo, aVar.getAppPackage(), aVar.getAppSign())) {
                    i3 = i2;
                } else if (packageArchiveInfo.versionCode > i2) {
                    File file3 = file2;
                    i3 = packageArchiveInfo.versionCode;
                    File file4 = file3;
                }
                i++;
                i2 = i3;
            }
            i3 = i2;
            i++;
            i2 = i3;
        }
        return new Pair(Integer.valueOf(i2), obj);
    }

    private static boolean a(PackageInfo packageInfo, List<String> list, String str) {
        boolean z;
        for (String a : list) {
            if (a(packageInfo, a)) {
                z = true;
                break;
            }
        }
        z = false;
        boolean b = b(packageInfo, str);
        if (z && b) {
            return true;
        }
        return false;
    }

    private static boolean a(PackageInfo packageInfo, String str) {
        if (packageInfo == null) {
            return false;
        }
        return str.equals(packageInfo.packageName);
    }

    private static boolean b(PackageInfo packageInfo, String str) {
        if (packageInfo == null) {
            return false;
        }
        if (packageInfo.signatures != null) {
            String str2 = "";
            for (Signature toByteArray : packageInfo.signatures) {
                byte[] toByteArray2 = toByteArray.toByteArray();
                if (toByteArray2 != null) {
                    str2 = MD5.hexdigest(toByteArray2);
                }
            }
            if (str2 != null) {
                return str2.equals(str);
            }
            return false;
        } else if (VERSION.SDK_INT < 11) {
            return true;
        } else {
            return false;
        }
    }

    private static String a(String str) {
        String str2 = "";
        int lastIndexOf = str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        if (lastIndexOf != -1) {
            return str.substring(lastIndexOf + 1, str.length());
        }
        return str2;
    }

    private static void a(Context context, a aVar, String str) {
        SDKNotificationBuilder.buildUpon().setNotificationContent(aVar.getNotificationText()).setNotificationPendingIntent(b(context, str)).setNotificationTitle(c(context, aVar.getNotificationTitle())).setTickerText(aVar.getNotificationText()).build(context).show(1);
    }

    private static PendingIntent b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return PendingIntent.getActivity(context, 0, new Intent(), 16);
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        return PendingIntent.getActivity(context, 0, intent, 16);
    }

    private static String c(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return ResourceManager.getString(context, "Weibo", "微博", "微博");
        }
        return str;
    }
}
