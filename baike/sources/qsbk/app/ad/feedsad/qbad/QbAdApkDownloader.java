package qsbk.app.ad.feedsad.qbad;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import com.qiushibaike.statsdk.common.MD5Util;
import java.io.File;
import java.util.HashSet;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.PackageUtil;
import qsbk.app.utils.RemixUtil;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UpdateHelper;
import qsbk.app.utils.UpdateHelper.DownloadListener;

public class QbAdApkDownloader {
    private static QbAdApkDownloader _instance;

    public static class ApkDownloadListener implements DownloadListener {
        public static HashSet<String> isDownloadingURL = new HashSet();
        private int last_progress = 0;
        private String mAppName;
        private final Context mContext;
        private final File mDownloadFile;
        private Handler mHandler = new Handler();
        Notification notification;

        public ApkDownloadListener(Context context, File file, String str) {
            this.mContext = context;
            this.mDownloadFile = file;
            this.mAppName = str;
        }

        private void displayNotificationMessage(String str, String str2, int i) {
            if (this.notification == null) {
                RemoteViews remoteViews = new RemoteViews(QsbkApp.mContext.getPackageName(), R.layout.notification_version);
                remoteViews.setTextViewText(R.id.n_title, "正在下载 " + str2);
                remoteViews.setTextViewText(R.id.n_text, i + "% ");
                remoteViews.setProgressBar(R.id.n_progress, 100, i, true);
                remoteViews.setImageViewResource(R.id.n_icon, str2.equals(RemixUtil.REMIX_NAME) ? R.drawable.remix_ic_launcher : R.drawable.ic_launcher);
                this.notification = new Builder(QsbkApp.mContext).setSmallIcon(R.drawable.notification_icon).setAutoCancel(true).setContent(remoteViews).build();
            }
            this.notification.contentView.setTextViewText(R.id.n_text, i + "%");
            this.notification.contentView.setProgressBar(R.id.n_progress, 100, i, false);
            Notification notification = this.notification;
            notification.flags |= 2;
            ((NotificationManager) QsbkApp.mContext.getSystemService("notification")).notify(getNotificationIdByUrl(str), this.notification);
        }

        public int getNotificationIdByUrl(String str) {
            if (str == null) {
                return 10000;
            }
            return (str.hashCode() % 10000) + 10000;
        }

        public void onStart(String str) {
            this.mHandler.post(new d(this, str));
        }

        public void onProgress(String str, long j, long j2) {
            int i = (int) ((100 * j) / j2);
            if (i - this.last_progress >= 2 || i == 100) {
                this.last_progress = i;
                this.mHandler.post(new e(this, str, i));
            }
        }

        private void cleanNotification(String str) {
            ((NotificationManager) QsbkApp.mContext.getSystemService("notification")).cancel(getNotificationIdByUrl(str));
        }

        public void onFinished(String str) {
            this.mHandler.post(new f(this, str));
        }

        public void onFailure(String str, Throwable th) {
            cleanNotification(str);
        }
    }

    public static synchronized QbAdApkDownloader instance() {
        QbAdApkDownloader qbAdApkDownloader;
        synchronized (QbAdApkDownloader.class) {
            if (_instance == null) {
                _instance = new QbAdApkDownloader();
            }
            qbAdApkDownloader = _instance;
        }
        return qbAdApkDownloader;
    }

    private void QbAdApkDownloader() {
    }

    public File getTmpDownloadFile(String str) {
        return new File(Environment.getExternalStorageDirectory(), MD5Util.md5(str, false) + ".apk.tmp");
    }

    public boolean isDownloadApkExist(String str) {
        return getDownloadedFileByUrl(str).exists();
    }

    public File getDownloadedFileByUrl(String str) {
        return new File(Environment.getExternalStorageDirectory(), MD5Util.md5(str, false) + ".apk");
    }

    public boolean isPackageInstalled(String str) {
        return PackageUtil.getPacageInfo(str) != null;
    }

    public void downloadFile(Context context, String str, String str2) {
        String network = HttpUtils.getNetwork(context);
        if ("unconnect".equals(network)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "网络未链接，暂时不能下载", Integer.valueOf(0)).show();
        } else if ("wifi".equals(network)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已经开始下载 " + str2, Integer.valueOf(0)).show();
            downloadFileImpl(context, str, str2);
        } else {
            new AlertDialog.Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new c(this)).setPositiveButton("确认", new b(this, str2, context, str)).create().show();
        }
    }

    private void downloadFileImpl(Context context, String str, String str2) {
        String trim = str.trim();
        File tmpDownloadFile = getTmpDownloadFile(trim);
        UpdateHelper.getInstance().download(trim, tmpDownloadFile, new ApkDownloadListener(context, tmpDownloadFile, str2));
    }
}
