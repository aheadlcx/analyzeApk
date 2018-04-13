package com.meizu.cloud.pushsdk.notification.android;

import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.Style;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MessageV4;
import com.meizu.cloud.pushsdk.networking.AndroidNetworking;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.util.FileUtil;
import com.meizu.cloud.pushsdk.notification.util.ZipExtractTask;
import com.meizu.cloud.pushsdk.pushtracer.emitter.classic.Executor;
import com.meizu.cloud.pushsdk.util.Connectivity;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import java.io.File;

public class AndroidVideoNotification extends AndroidStandardNotification {
    public AndroidVideoNotification(Context context, PushNotificationBuilder pushNotificationBuilder) {
        super(context, pushNotificationBuilder);
    }

    protected void buildExpandableContent(Builder builder, MessageV3 messageV3) {
        if (MinSdkChecker.isSupportNotificationBuild()) {
            Style bigTextStyle = new BigTextStyle();
            bigTextStyle.setBigContentTitle(messageV3.getTitle());
            bigTextStyle.setSummaryText(messageV3.getContent());
            bigTextStyle.bigText(messageV3.getmNotificationStyle().getExpandableText());
            builder.setStyle(bigTextStyle);
        }
    }

    protected void buildContentView(Notification notification, MessageV3 messageV3) {
        super.buildContentView(notification, messageV3);
        MessageV4 parse = MessageV4.parse(messageV3);
        if (parse.getActVideoSetting() == null || (parse.getActVideoSetting().isWifiDisplay() && !Connectivity.isConnectedWifi(this.context))) {
            a.d("AbstractPushNotification", "only wifi can download act");
            return;
        }
        final String str = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/pushSdkAct/" + messageV3.getUploadDataPackageName();
        String valueOf = String.valueOf(System.currentTimeMillis());
        String actUrl = parse.getActVideoSetting().getActUrl();
        if (!TextUtils.isEmpty(actUrl) && AndroidNetworking.download(actUrl, str, valueOf).build().executeForDownload().isSuccess()) {
            a.a("AbstractPushNotification", "down load " + actUrl + " success");
            actUrl = str + File.separator + "ACT-" + valueOf;
            boolean doUnzipSync = new ZipExtractTask(str + File.separator + valueOf, actUrl).doUnzipSync();
            a.a("AbstractPushNotification", "zip file " + doUnzipSync);
            if (doUnzipSync) {
                Bundle bundle = new Bundle();
                bundle.putString(AIUIConstant.RES_TYPE_PATH, actUrl);
                Bundle bundle2 = new Bundle();
                bundle2.putBundle("big", bundle);
                if (MinSdkChecker.isSupportVideoNotification()) {
                    notification.extras.putBundle("flyme.active", bundle2);
                }
            }
        }
        Executor.execute(new Runnable() {
            public void run() {
                for (File file : FileUtil.listFile(str, String.valueOf(System.currentTimeMillis() - com.umeng.analytics.a.i))) {
                    FileUtil.deleteDirectory(file.getPath());
                    a.a("AbstractPushNotification", "Delete file directory " + file.getName() + "\n");
                }
            }
        });
    }
}
