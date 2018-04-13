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
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.util.List;

class AppInstallCmdExecutor implements CmdExecutor<AppInstallCmd> {
    private static final int MESSAGE_DO_CMD = 1;
    private static final int MESSAGE_QUIT_LOOP = 2;
    private static final String TAG = AppInstallCmdExecutor.class.getName();
    private static final String WB_APK_FILE_DIR = (Environment.getExternalStorageDirectory() + "/Android/org_share_data/");
    private boolean isStarted = false;
    private Context mContext;
    private InstallHandler mHandler;
    private Looper mLooper;
    private HandlerThread thread;

    private class InstallHandler extends Handler {
        public InstallHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    AppInstallCmdExecutor.this.handleCmd((AppInstallCmd) message.obj);
                    return;
                case 2:
                    AppInstallCmdExecutor.this.mLooper.quit();
                    AppInstallCmdExecutor.this.isStarted = false;
                    return;
                default:
                    return;
            }
        }
    }

    private static final class NOTIFICATION_CONSTANTS {
        private static final int NOTIFICATIONID = 1;
        private static final String WEIBO = "Weibo";
        private static final String WEIBO_ZH_CN = "微博";
        private static final String WEIBO_ZH_TW = "微博";

        private NOTIFICATION_CONSTANTS() {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleCmd(com.sina.weibo.sdk.cmd.AppInstallCmd r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0008 in list [B:22:0x007d]
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
        r8 = this;
        r0 = r8.mContext;
        r0 = needActivate(r0, r9);
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r1 = WB_APK_FILE_DIR;
        r2 = r9.getDownloadUrl();
        r4 = r9.getAppVersion();
        r0 = r8.mContext;
        r3 = walkDir(r0, r1, r9);
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
        r1 = r8.mContext;
        r0 = r3.second;
        r0 = (java.io.File) r0;
        r0 = r0.getAbsolutePath();
        showNotification(r1, r9, r0);
        goto L_0x0008;
    L_0x003a:
        r0 = r8.mContext;
        r0 = com.sina.weibo.sdk.utils.NetworkHelper.isWifiValid(r0);
        if (r0 == 0) goto L_0x0008;
    L_0x0042:
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 != 0) goto L_0x0008;
    L_0x0048:
        r3 = "";
        r0 = r8.mContext;	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r4 = "GET";	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r5 = new com.sina.weibo.sdk.net.WeiboParameters;	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r6 = "";	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r5.<init>(r6);	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r0 = com.sina.weibo.sdk.net.NetUtils.internalGetRedirectUri(r0, r2, r4, r5);	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r2 = generateSaveFileName(r0);	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        if (r4 != 0) goto L_0x006f;	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
    L_0x0066:
        r4 = ".apk";	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r4 = r2.endsWith(r4);	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        if (r4 != 0) goto L_0x0083;	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
    L_0x006f:
        r0 = TAG;	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r1 = "redirectDownloadUrl is illeagle";	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        com.sina.weibo.sdk.utils.LogUtil.e(r0, r1);	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 != 0) goto L_0x0008;
    L_0x007d:
        r0 = r8.mContext;
        showNotification(r0, r9, r3);
        goto L_0x0008;
    L_0x0083:
        r4 = r8.mContext;	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r0 = com.sina.weibo.sdk.net.NetUtils.internalDownloadFile(r4, r0, r1, r2);	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0008;
    L_0x008f:
        r1 = r8.mContext;
        showNotification(r1, r9, r0);
        goto L_0x0008;
    L_0x0096:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ WeiboException -> 0x0096, all -> 0x00a7 }
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 != 0) goto L_0x0008;
    L_0x00a0:
        r0 = r8.mContext;
        showNotification(r0, r9, r3);
        goto L_0x0008;
    L_0x00a7:
        r0 = move-exception;
        r1 = android.text.TextUtils.isEmpty(r3);
        if (r1 != 0) goto L_0x00b3;
    L_0x00ae:
        r1 = r8.mContext;
        showNotification(r1, r9, r3);
    L_0x00b3:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.cmd.AppInstallCmdExecutor.handleCmd(com.sina.weibo.sdk.cmd.AppInstallCmd):void");
    }

    public AppInstallCmdExecutor(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private static boolean needActivate(Context context, AppInstallCmd appInstallCmd) {
        List<String> appPackage = appInstallCmd.getAppPackage();
        if (appPackage == null || appPackage.size() == 0 || TextUtils.isEmpty(appInstallCmd.getAppSign()) || TextUtils.isEmpty(appInstallCmd.getDownloadUrl()) || TextUtils.isEmpty(appInstallCmd.getNotificationText())) {
            return false;
        }
        if (appPackage.contains("com.sina.weibo")) {
            WeiboInfo weiboInfo = WeiboAppManager.getInstance(context).getWeiboInfo();
            return weiboInfo == null || !weiboInfo.isLegal();
        } else {
            for (String checkApkInstalled : appPackage) {
                if (checkApkInstalled(context, checkApkInstalled)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean checkApkInstalled(Context context, String str) {
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
        if (!this.isStarted) {
            this.isStarted = true;
            this.thread = new HandlerThread("");
            this.thread.start();
            this.mLooper = this.thread.getLooper();
            this.mHandler = new InstallHandler(this.mLooper);
        }
    }

    public void stop() {
        if (this.thread == null || this.mHandler == null) {
            LogUtil.w(TAG, "no thread running. please call start method first!");
            return;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public boolean doExecutor(AppInstallCmd appInstallCmd) {
        if (this.thread == null || this.mHandler == null) {
            throw new RuntimeException("no thread running. please call start method first!");
        }
        if (appInstallCmd != null) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = appInstallCmd;
            this.mHandler.sendMessage(obtainMessage);
        }
        return false;
    }

    private static Pair<Integer, File> walkDir(Context context, String str, AppInstallCmd appInstallCmd) {
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
            if (file2.isFile() && name.endsWith(ShareConstants.PATCH_SUFFIX)) {
                PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file2.getAbsolutePath(), 64);
                if (!isSpecifiedApk(packageArchiveInfo, appInstallCmd.getAppPackage(), appInstallCmd.getAppSign())) {
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

    private static boolean isSpecifiedApk(PackageInfo packageInfo, List<String> list, String str) {
        boolean z;
        for (String checkPackageName : list) {
            if (checkPackageName(packageInfo, checkPackageName)) {
                z = true;
                break;
            }
        }
        z = false;
        boolean checkApkSign = checkApkSign(packageInfo, str);
        if (z && checkApkSign) {
            return true;
        }
        return false;
    }

    private static boolean checkPackageName(PackageInfo packageInfo, String str) {
        if (packageInfo == null) {
            return false;
        }
        return str.equals(packageInfo.packageName);
    }

    private static boolean checkApkSign(PackageInfo packageInfo, String str) {
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

    private static String generateSaveFileName(String str) {
        String str2 = "";
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf != -1) {
            return str.substring(lastIndexOf + 1, str.length());
        }
        return str2;
    }

    private static void showNotification(Context context, AppInstallCmd appInstallCmd, String str) {
        SDKNotificationBuilder.buildUpon().setNotificationContent(appInstallCmd.getNotificationText()).setNotificationPendingIntent(buildInstallApkIntent(context, str)).setNotificationTitle(getNotificationTitle(context, appInstallCmd.getNotificationTitle())).setTickerText(appInstallCmd.getNotificationText()).build(context).show(1);
    }

    private static PendingIntent buildInstallApkIntent(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return PendingIntent.getActivity(context, 0, new Intent(), 16);
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        return PendingIntent.getActivity(context, 0, intent, 16);
    }

    private static String getNotificationTitle(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return ResourceManager.getString(context, "Weibo", "微博", "微博");
        }
        return str;
    }
}
