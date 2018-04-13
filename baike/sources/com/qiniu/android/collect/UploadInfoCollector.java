package com.qiniu.android.collect;

import com.qiniu.android.http.UserAgent;
import com.qiniu.android.storage.UpToken;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request$Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadInfoCollector {
    private static UploadInfoCollector f;
    private ExecutorService a = null;
    private final String b = "_qiniu_record_file_hu3z9lo7anx03";
    private File c = null;
    private long d;
    private OkHttpClient e = null;

    public static abstract class RecordMsg {
        public abstract String toRecordMsg();
    }

    private UploadInfoCollector() {
        try {
            c();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static UploadInfoCollector a() {
        if (f == null) {
            f = new UploadInfoCollector();
        }
        return f;
    }

    public static void clean() {
        try {
            a().b();
        } catch (Exception e) {
            e.printStackTrace();
        }
        f = null;
    }

    private void b() {
        try {
            if (this.a != null) {
                this.a.shutdown();
            }
        } catch (Exception e) {
        }
        this.a = null;
        this.e = null;
        try {
            if (this.c != null) {
                this.c.delete();
            } else {
                new File(a(Config.recordDir), "_qiniu_record_file_hu3z9lo7anx03").delete();
            }
        } catch (Exception e2) {
        }
        this.c = null;
    }

    public static void reset() {
        try {
            a().c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c() throws IOException {
        if (Config.isRecord) {
            a(a(Config.recordDir));
        }
        if (!(Config.isRecord || this.a == null)) {
            this.a.shutdown();
        }
        if (!Config.isRecord) {
            return;
        }
        if (this.a == null || this.a.isShutdown()) {
            this.a = Executors.newSingleThreadExecutor();
        }
    }

    private File a(String str) {
        return new File(str);
    }

    private void a(File file) throws IOException {
        if (file == null) {
            throw new IOException("record's dir is not setted");
        } else if (file.exists()) {
            if (file.isDirectory()) {
                this.c = new File(file, "_qiniu_record_file_hu3z9lo7anx03");
                return;
            }
            throw new IOException(file.getAbsolutePath() + " is not a dir");
        } else if (!file.mkdirs()) {
            throw new IOException("mkdir failed: " + file.getAbsolutePath());
        }
    }

    public static void handle(UpToken upToken, RecordMsg recordMsg) {
        try {
            if (Config.isRecord) {
                a().a(upToken, recordMsg);
            }
        } catch (Throwable th) {
        }
    }

    private void a(UpToken upToken, RecordMsg recordMsg) {
        if (this.a != null && !this.a.isShutdown()) {
            this.a.submit(new a(this, recordMsg));
            if (Config.isUpload && upToken != UpToken.NULL) {
                this.a.submit(new b(this, upToken));
            }
        }
    }

    private void b(String str) {
        if (Config.isRecord && this.c.length() < ((long) Config.maxRecordFileSize)) {
            a(this.c, str + "\n", true);
        }
    }

    private void a(UpToken upToken) {
        if (Config.isUpload && this.c.length() > ((long) Config.uploadThreshold)) {
            long time = new Date().getTime();
            if (time > this.d + ((long) ((Config.interval * 60) * 1000))) {
                this.d = time;
                if (b(upToken)) {
                    a(this.c, "", false);
                    a(this.c, "", false);
                }
            }
        }
    }

    private static void a(File file, String str, boolean z) {
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        try {
            fileOutputStream = new FileOutputStream(file, z);
            try {
                fileOutputStream.write(str.getBytes(Charset.forName("UTF-8")));
                fileOutputStream.flush();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e7) {
                e2 = e7;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e8) {
                    }
                }
            }
        } catch (FileNotFoundException e9) {
            e = e9;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e10) {
            e2 = e10;
            fileOutputStream = null;
            e2.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private boolean b(UpToken upToken) {
        try {
            String str = Config.serverURL;
            return a(d().newCall(new Request$Builder().url(str).addHeader("Authorization", "UpToken " + upToken.token).addHeader("User-Agent", UserAgent.instance().getUa(upToken.accessKey)).post(RequestBody.create(MediaType.parse(HTTP.PLAIN_TEXT_TYPE), this.c)).build()).execute());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean a(Response response) {
        return response.isSuccessful() && response.header("X-Reqid") != null;
    }

    private OkHttpClient d() {
        if (this.e == null) {
            Builder builder = new Builder();
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(15, TimeUnit.SECONDS);
            builder.writeTimeout((long) ((((Config.interval / 2) + 1) * 60) - 10), TimeUnit.SECONDS);
            this.e = builder.build();
        }
        return this.e;
    }
}
