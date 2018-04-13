package qsbk.app.im.voice;

import android.net.Uri;
import android.os.Environment;
import android.support.v4.internal.view.SupportMenu;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import okhttp3.CacheControl$Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request$Builder;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.image.ImageUploader;
import qsbk.app.im.image.UploadTask;
import qsbk.app.model.Laisee;
import qsbk.app.utils.HttpClient;

public class VoiceManager {
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 10000;
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 20000;
    private static final String a = (Environment.getExternalStorageDirectory() + File.separator + LogUtils.DEFAULT_TAG + File.separator + Laisee.SUB_TYPE_VOICE);
    private String b;
    private OkHttpClient c;

    public interface VoiceCallback {
        void onFaiure(String str, String str2, Object obj);

        void onProgress(String str, long j, long j2, Object obj);

        void onStart(String str, Object obj);

        void onSuccess(String str, String str2, Object obj);
    }

    public class VoiceUploadTask extends UploadTask {
        final /* synthetic */ VoiceManager a;
        private AsyncTask b;
        private UploadTaskExecutor c;

        public VoiceUploadTask(VoiceManager voiceManager) {
            this.a = voiceManager;
        }

        public void setTokenTask(AsyncTask asyncTask) {
            this.b = asyncTask;
        }

        public void setUploadTaskExecutor(UploadTaskExecutor uploadTaskExecutor) {
            this.c = uploadTaskExecutor;
        }

        public void cancel(boolean z) {
            if (this.b != null) {
                this.b.cancel(z);
            }
            if (this.c != null) {
                this.c.cancel();
            }
        }
    }

    public VoiceManager(String str) {
        this.b = str;
    }

    public VoiceManager(String str, OkHttpClient okHttpClient) {
        this.b = str;
        this.c = okHttpClient;
    }

    public static String getDir() {
        File file = new File(a);
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        return a;
    }

    public static String getPath(String str) {
        String dir = getDir();
        return dir + File.separator + getFileKey(str);
    }

    public static String getFileKey(String str) {
        if (str == null) {
            return "0";
        }
        return String.valueOf(str.hashCode());
    }

    public static void forceRename(File file, File file2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        IOException e;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        if (file2.exists()) {
            file2.delete();
        }
        if (!file.renameTo(file2)) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[SupportMenu.USER_MASK];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                    } catch (IOException e4) {
                        e = e4;
                        fileInputStream2 = fileInputStream;
                        try {
                            e.printStackTrace();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e5) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e6) {
                                }
                            }
                            file.delete();
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e7) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e8) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e9) {
                    e = e9;
                    fileOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    file.delete();
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                file.delete();
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
            file.delete();
        }
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.c = okHttpClient;
    }

    public UploadTask send(String str, VoiceCallback voiceCallback, Object obj) {
        UploadTask voiceUploadTask = new VoiceUploadTask(this);
        AsyncTask aVar = new a(this, voiceCallback, str, obj, voiceUploadTask);
        voiceUploadTask.setTokenTask(aVar);
        aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return voiceUploadTask;
    }

    private HttpURLConnection a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.connect();
        return httpURLConnection;
    }

    private InputStream b(String str) throws IOException {
        HttpURLConnection a = a(str);
        int i = 0;
        while (a.getResponseCode() / 100 == 3 && i < 3) {
            a = a(a.getHeaderField(HttpHeaders.LOCATION));
            i++;
        }
        return new BufferedInputStream(a.getInputStream(), SupportMenu.USER_MASK);
    }

    private InputStream c(String str) throws IOException {
        if (this.c == null) {
            return b(str);
        }
        Response execute = this.c.newCall(new Request$Builder().cacheControl(new CacheControl$Builder().noStore().build()).url(str).get().build()).execute();
        int code = execute.code();
        if (execute.isSuccessful()) {
            return execute.body().byteStream();
        }
        throw new IOException("Code:" + code);
    }

    public String getPathIfDownload(String str) {
        String path = getPath(str);
        return new File(path).exists() ? path : null;
    }

    public void download(String str, VoiceCallback voiceCallback, Object obj) {
        new b(this, voiceCallback, str, obj).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private String d(String str) {
        String str2 = "";
        try {
            str2 = new JSONObject(HttpClient.getIntentce().get(ImageUploader.im_get_token + str)).optString("uptoken");
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    private UploadTaskExecutor a(String str, String str2, VoiceCallback voiceCallback, Object obj) {
        String str3 = IO.UNDEFINED_KEY;
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(str);
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, str3, Uri.fromFile(new File(str2)), putExtra, new c(this, str2, str, voiceCallback, obj));
    }
}
