package qsbk.app.game;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.comm.ArrayUtils;

public class ContinueDownloader extends BroadcastReceiver implements DownListener {
    public static final String KEY_CACHE = "Continue_Download_Preference";
    public static final int READ_TIME_OUT = 10000;
    public static final int REQUEST_TIME_OUT = 10000;
    private static ContinueDownloader a;
    private boolean b = true;
    private DownListener c;
    private Handler d = new Handler();
    public Runnable delaySaveRunnable = new a(this);
    public HashMap<String, DownListener> downListenerHashMap = new HashMap();
    public HashMap<String, DownloadUnit> downUnitMap = new HashMap();

    public static class ByteRange {
        long a;
        long b;
        long c;

        public ByteRange(long j, long j2, long j3) {
            this.a = j;
            this.b = j2;
            this.c = j3;
        }
    }

    public class DownloadRunnable implements Runnable {
        DownloadUnit a;
        DownListener b;
        final /* synthetic */ ContinueDownloader c;

        DownloadRunnable(ContinueDownloader continueDownloader, DownloadUnit downloadUnit, DownListener downListener) {
            this.c = continueDownloader;
            this.a = downloadUnit;
            this.b = downListener;
        }

        public void run() {
            this.a.setDownloading();
            String str = this.a.url;
            this.b.onProgress(str, 0, 100000);
            String tmpFile = this.a.getTmpFile();
            try {
                long j = this.a.a;
                while (true) {
                    if (this.c.isNetworkOK() || this.a.downloadInNoWifi) {
                        ByteRange urlByRange = ContinueDownloader.getUrlByRange(str, j, tmpFile);
                        if (urlByRange.b + 1 == urlByRange.c) {
                            this.b.onDownload(str, true, null);
                            return;
                        }
                        long j2 = urlByRange.b + 1;
                        this.a.setTotal(urlByRange.c);
                        this.a.a = urlByRange.b + 1;
                        this.b.onProgress(str, urlByRange.b + 1, urlByRange.c);
                        j = j2;
                    } else {
                        LogUtil.d("networ is not wifi and suspend unit:" + str);
                        this.a.setSuspended();
                        this.c.delaySave();
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.b.onDownload(str, false, e.toString());
            }
        }
    }

    private ContinueDownloader() {
        List downloadUnitFromLocalStorage = getDownloadUnitFromLocalStorage();
        if (downloadUnitFromLocalStorage != null) {
            for (int i = 0; i < downloadUnitFromLocalStorage.size(); i++) {
                this.downUnitMap.put(((DownloadUnit) downloadUnitFromLocalStorage.get(i)).url, downloadUnitFromLocalStorage.get(i));
                LogUtil.d("init and put:" + ((DownloadUnit) downloadUnitFromLocalStorage.get(i)).url);
                LogUtil.d("download unit:" + ((DownloadUnit) downloadUnitFromLocalStorage.get(i)).toString());
            }
        }
        b();
        this.b = HttpUtils.isWifi(QsbkApp.mContext);
        if (this.b) {
            this.d.postDelayed(new b(this), 8000);
        }
    }

    public static final long getDownPackLength() {
        if (HttpUtils.isWifi(QsbkApp.mContext)) {
            return PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
    }

    public static ByteRange getUrlByRange(String str, long j, String str2) throws IOException {
        RandomAccessFile randomAccessFile;
        Throwable th;
        HttpURLConnection httpURLConnection = get(str, j, getDownPackLength() + j);
        ByteRange readPart = readPart(httpURLConnection);
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            randomAccessFile = new RandomAccessFile(str2, "rw");
            try {
                randomAccessFile.seek(j);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read <= 0) {
                        break;
                    }
                    randomAccessFile.write(bArr, 0, read);
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return readPart;
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public static String getLocalErrorStr() {
        return "网络链接失败，请稍后再试";
    }

    public static ByteRange readPart(HttpURLConnection httpURLConnection) throws IOException {
        String toLowerCase = httpURLConnection.getHeaderField("Content-Range").toLowerCase();
        LogUtil.d("content range:" + toLowerCase);
        String[] split = toLowerCase.replace("bytes ", "").split(MqttTopic.TOPIC_LEVEL_SEPARATOR, 2);
        toLowerCase = split[1];
        String[] split2 = split[0].split(Constants.ACCEPT_TIME_SEPARATOR_SERVER, 2);
        LogUtil.d("total:" + toLowerCase);
        LogUtil.d("start:" + split2[0]);
        LogUtil.d("end:" + split2[1]);
        if (toLowerCase.equals("*")) {
            toLowerCase = "0";
        }
        return new ByteRange(Long.parseLong(split2[0]), Long.parseLong(split2[1]), Long.parseLong(toLowerCase));
    }

    public static HttpURLConnection get(String str, long j, long j2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Range", "bytes=" + j + Constants.ACCEPT_TIME_SEPARATOR_SERVER + j2);
        return httpURLConnection;
    }

    public static synchronized ContinueDownloader instance() {
        ContinueDownloader continueDownloader;
        synchronized (ContinueDownloader.class) {
            if (a == null) {
                LogUtil.d("init instaance");
                a = new ContinueDownloader();
            }
            continueDownloader = a;
        }
        return continueDownloader;
    }

    public synchronized boolean isNetworkOK() {
        return this.b;
    }

    public synchronized void onReceive(Context context, Intent intent) {
        LogUtil.d("receive broadcast");
        if (HttpUtils.isWifi(QsbkApp.mContext)) {
            this.b = true;
            a();
        } else {
            this.b = false;
        }
    }

    private void a() {
        for (DownloadUnit downloadUnit : this.downUnitMap.values()) {
            if (downloadUnit.isSuspended()) {
                download(downloadUnit.url, downloadUnit.getName(), null, true);
                LogUtil.d("network is ok and resume unit:" + downloadUnit.url);
            }
        }
    }

    public DownloadUnit getDownloadUnit(String str) {
        return (DownloadUnit) this.downUnitMap.get(str);
    }

    public void registerListener(DownListener downListener) {
        this.c = downListener;
    }

    public void unRegisterListener(DownListener downListener) {
        if (this.c == downListener) {
            this.c = null;
        }
    }

    public void download(String str, String str2, DownListener downListener, boolean z) {
        DownloadUnit downloadUnit = (DownloadUnit) this.downUnitMap.get(str);
        if (downloadUnit == null) {
            downloadUnit = new DownloadUnit(str, 0, 0);
            downloadUnit.setName(str2);
            LogUtil.d("download and put url:" + str);
            this.downUnitMap.put(str, downloadUnit);
        }
        Object network = HttpUtils.getNetwork(QsbkApp.mContext);
        Object[] objArr = new String[]{"2G", "3G", "4G"};
        if (!z && ArrayUtils.find(objArr, network) >= 0) {
            downloadUnit.downloadInNoWifi = true;
        }
        if (!downloadUnit.isCompleted()) {
            if (downListener != null) {
                this.downListenerHashMap.put(downloadUnit.url, downListener);
            }
            if (downloadUnit.canDownload()) {
                downloadUnit.setDownloading();
                AsyncTask.THREAD_POOL_EXECUTOR.execute(new DownloadRunnable(this, downloadUnit, this));
                return;
            }
            LogUtil.d(str + "is downloading");
        } else if (downListener != null) {
            this.d.post(new c(this, downListener, str));
        }
    }

    public synchronized void onDownload(String str, boolean z, String str2) {
        DownloadUnit downloadUnit = (DownloadUnit) this.downUnitMap.get(str);
        if (downloadUnit != null) {
            if (z) {
                downloadUnit.setComplete();
                LogUtil.d("unit:" + downloadUnit.toString());
                a(downloadUnit);
            } else {
                downloadUnit.setSuspended();
            }
            delaySave();
        }
        DownListener downListener = (DownListener) this.downListenerHashMap.get(str);
        if (downListener != null) {
            this.d.post(new d(this, downListener, str, z, str2));
        }
        LogUtil.d("on Downloaded:" + str);
        if (this.c != null) {
            this.d.post(new e(this, str, z, str2));
        }
    }

    private void a(DownloadUnit downloadUnit) {
        this.d.post(new f(this, downloadUnit));
    }

    public synchronized void onProgress(String str, long j, long j2) {
        DownListener downListener = (DownListener) this.downListenerHashMap.get(str);
        if (downListener != null) {
            this.d.post(new g(this, downListener, str, j, j2));
        }
        if (((DownloadUnit) this.downUnitMap.get(str)) != null) {
            delaySave();
        }
        LogUtil.d("on progress:" + str + " current:" + j + " total:" + j2);
        if (this.c != null) {
            this.d.post(new h(this, str, j, j2));
        }
    }

    public void delaySave() {
        this.d.removeCallbacks(this.delaySaveRunnable);
        this.d.postDelayed(this.delaySaveRunnable, 1500);
    }

    public void saveDownloadUnitToMap() {
        JSONArray jSONArray = new JSONArray();
        for (String str : this.downUnitMap.keySet()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", ((DownloadUnit) this.downUnitMap.get(str)).url);
                jSONObject.put("finished", ((DownloadUnit) this.downUnitMap.get(str)).a);
                LogUtil.d("state:" + ((DownloadUnit) this.downUnitMap.get(str)).getState());
                jSONObject.put("state", ((DownloadUnit) this.downUnitMap.get(str)).getState());
                jSONObject.put("total", ((DownloadUnit) this.downUnitMap.get(str)).getTotal());
                jSONObject.put("app_name", ((DownloadUnit) this.downUnitMap.get(str)).getName());
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SharePreferenceUtils.setSharePreferencesValue(KEY_CACHE, jSONArray.toString());
    }

    public List<DownloadUnit> getDownloadUnitFromLocalStorage() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(KEY_CACHE);
        LogUtil.d("cache:" + sharePreferencesValue);
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(sharePreferencesValue);
            List<DownloadUnit> linkedList = new LinkedList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (!(optJSONObject == null || optJSONObject.optString("url") == null || optJSONObject.optLong("finished") == 0)) {
                    DownloadUnit downloadUnit = new DownloadUnit(optJSONObject.optString("url"), optJSONObject.optLong("finished"), optJSONObject.optInt("state", 0));
                    downloadUnit.setTotal(optJSONObject.optLong("total"));
                    if (downloadUnit.isDownloading()) {
                        downloadUnit.setSuspended();
                    }
                    downloadUnit.setName(optJSONObject.optString("app_name"));
                    linkedList.add(downloadUnit);
                }
            }
            return linkedList;
        } catch (Exception e) {
            return null;
        }
    }

    private void b() {
        QsbkApp.mContext.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
