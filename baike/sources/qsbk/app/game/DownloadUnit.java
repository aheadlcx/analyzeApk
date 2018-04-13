package qsbk.app.game;

import android.text.TextUtils;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.StorageUtils;

public class DownloadUnit {
    public static final int COMPLETED = 3;
    public static final int DOWNLOADING = 1;
    public static final int STOPED = 0;
    public static final int SUSPENDED = 2;
    long a = 0;
    private long b;
    private String c;
    private String d;
    public boolean downloadInNoWifi = false;
    private int e = 0;
    public String url;

    DownloadUnit(String str, long j, int i) {
        LogUtil.d("init download unit:" + str);
        this.url = str;
        this.a = j;
        this.e = i;
    }

    public static File getAppDownloadCacheDir() {
        File cacheDirectory = StorageUtils.getCacheDirectory(QsbkApp.mContext, true);
        File file = new File(cacheDirectory, "download");
        if (file.exists() || file.mkdir()) {
            return file;
        }
        return cacheDirectory;
    }

    public String getDownloadUnitName() {
        return TextUtils.isEmpty(this.c) ? this.url : this.c;
    }

    public String getDownUnitIcon() {
        return this.d;
    }

    public synchronized boolean canDownload() {
        boolean z;
        z = this.e == 0 || this.e == 2;
        return z;
    }

    public void setComplete() {
        LogUtil.d("set complete:" + this.url);
        this.e = 3;
    }

    public boolean isCompleted() {
        return this.e == 3;
    }

    public boolean checkFileFinished() {
        if (new File(getTmpFile()).exists()) {
            return true;
        }
        this.e = 0;
        this.a = 0;
        ContinueDownloader.instance().delaySave();
        return false;
    }

    public boolean isDownloading() {
        return this.e == 1;
    }

    public String getTmpFile() {
        return getFinishFile() + "_tmp";
    }

    public String getFinishFile() {
        return new File(getAppDownloadCacheDir() + MqttTopic.TOPIC_LEVEL_SEPARATOR + Md5.MD5(this.url)).getAbsolutePath();
    }

    public synchronized void setDownloading() {
        LogUtil.d("set downloading:" + this.url);
        this.e = 1;
    }

    public synchronized void setStoped() {
        LogUtil.d("set stoped:" + this.url);
        this.e = 0;
    }

    public void setSuspended() {
        LogUtil.d("set suspended:" + this.url);
        this.e = 2;
    }

    public boolean isSuspended() {
        return this.e == 2;
    }

    public boolean isStoped() {
        return this.e == 0;
    }

    public long getTotal() {
        return this.b;
    }

    public void setTotal(long j) {
        this.b = j;
    }

    public int getState() {
        return this.e;
    }

    public String toString() {
        return "DownloadUnit{total=" + this.b + ", appName='" + this.c + '\'' + ", appIcon='" + this.d + '\'' + ", url='" + this.url + '\'' + ", finished=" + this.a + ", state=" + this.e + '}';
    }

    public String getName() {
        return this.c;
    }

    public void setName(String str) {
        this.c = str;
    }
}
