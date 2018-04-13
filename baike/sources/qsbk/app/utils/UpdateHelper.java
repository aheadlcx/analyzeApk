package qsbk.app.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.service.VersionService;

public final class UpdateHelper {
    private static UpdateHelper a = null;
    private final ExecutorService b = Executors.newSingleThreadExecutor();

    public interface DownloadListener {
        void onFailure(String str, Throwable th);

        void onFinished(String str);

        void onProgress(String str, long j, long j2);

        void onStart(String str);
    }

    public static class SimpleDownLoadListener implements DownloadListener {
        private final Context a;
        private final Intent b = new Intent(this.a, VersionService.class);
        private final File c;

        public SimpleDownLoadListener(Context context, File file) {
            this.a = context;
            this.c = file;
        }

        public void onStart(String str) {
            QsbkApp.loading_process = -1;
            this.a.startService(this.b);
        }

        public void onProgress(String str, long j, long j2) {
            QsbkApp.loading_process = (int) ((100 * j) / j2);
        }

        public void onFinished(String str) {
            this.a.stopService(this.b);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(this.c), "application/vnd.android.package-archive");
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            this.a.startActivity(intent);
        }

        public void onFailure(String str, Throwable th) {
            QsbkApp.loading_process = -1;
            this.a.stopService(this.b);
        }
    }

    private UpdateHelper() {
    }

    public static synchronized UpdateHelper getInstance() {
        UpdateHelper updateHelper;
        synchronized (UpdateHelper.class) {
            if (a == null) {
                a = new UpdateHelper();
            }
            updateHelper = a;
        }
        return updateHelper;
    }

    private HttpURLConnection a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.addRequestProperty("User-Agent", "qiushibalke_" + Constants.localVersionName);
        httpURLConnection.addRequestProperty(HttpHeaders.ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
        return httpURLConnection;
    }

    public void download(String str, File file, DownloadListener downloadListener) {
        this.b.execute(new bk(this, downloadListener, str, file));
    }
}
