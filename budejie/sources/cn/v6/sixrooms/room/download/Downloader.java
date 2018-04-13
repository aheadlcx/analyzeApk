package cn.v6.sixrooms.room.download;

import cn.v6.sixrooms.bean.DownConfigInfo;
import cn.v6.sixrooms.utils.LogUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {
    private DownConfigInfo a;
    private DownloadListener b;
    private Runnable c = new a(this);

    public interface DownloadListener {
        void onLoadingComplete(DownConfigInfo downConfigInfo);

        void onLoadingFailed(DownConfigInfo downConfigInfo, String str, Exception exception);
    }

    public Downloader(DownConfigInfo downConfigInfo, DownloadListener downloadListener) {
        this.a = downConfigInfo;
        this.b = downloadListener;
    }

    public void startDownload() {
        new Thread(this.c).start();
    }

    public void downloadSync() {
        b(this.a, this.b);
    }

    private static void b(DownConfigInfo downConfigInfo, DownloadListener downloadListener) {
        Closeable inputStream;
        Exception e;
        Throwable th;
        File file;
        Closeable closeable = null;
        String str = downConfigInfo.downUrl;
        String str2 = downConfigInfo.targetPath;
        String str3 = downConfigInfo.targetName;
        LogUtils.d("ContentValues", "downUrl: " + str);
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdir();
        }
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.setConnectTimeout(10000);
            openConnection.setReadTimeout(1000);
            inputStream = openConnection.getInputStream();
            if (inputStream == null) {
                try {
                    LogUtils.e("ContentValues", str + "下载：网络请求InputStream is null");
                    if (downloadListener != null) {
                        downloadListener.onLoadingFailed(downConfigInfo, "下载：网络请求InputStream is null", null);
                    }
                    a(null);
                    a(inputStream);
                    return;
                } catch (MalformedURLException e2) {
                    e = e2;
                    try {
                        LogUtils.e("ContentValues", str + "：" + e.toString());
                        if (downloadListener != null) {
                            downloadListener.onLoadingFailed(downConfigInfo, "MalformedURLException", e);
                        }
                        a(closeable);
                        a(inputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        a(closeable);
                        a(inputStream);
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (downloadListener != null) {
                        downloadListener.onLoadingFailed(downConfigInfo, "Exception", e);
                    }
                    file = new File(str3);
                    if (file.exists()) {
                        LogUtils.e("ContentValues", str + " 下载：" + file.toString() + "发生异常___");
                        file.delete();
                    }
                    a(closeable);
                    a(inputStream);
                }
            }
            byte[] bArr = new byte[1048576];
            File file3 = new File(str2 + str3);
            if (file3.exists()) {
                file3.delete();
            } else {
                file3.createNewFile();
            }
            Closeable fileOutputStream = new FileOutputStream(str2 + str3);
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                } catch (MalformedURLException e4) {
                    e = e4;
                    closeable = fileOutputStream;
                } catch (Exception e5) {
                    e = e5;
                    closeable = fileOutputStream;
                } catch (Throwable th3) {
                    th = th3;
                    closeable = fileOutputStream;
                }
            }
            if (downloadListener != null) {
                downloadListener.onLoadingComplete(downConfigInfo);
            }
            a(fileOutputStream);
            a(inputStream);
        } catch (MalformedURLException e6) {
            e = e6;
            inputStream = null;
            LogUtils.e("ContentValues", str + "：" + e.toString());
            if (downloadListener != null) {
                downloadListener.onLoadingFailed(downConfigInfo, "MalformedURLException", e);
            }
            a(closeable);
            a(inputStream);
        } catch (Exception e7) {
            e = e7;
            inputStream = null;
            e.printStackTrace();
            if (downloadListener != null) {
                downloadListener.onLoadingFailed(downConfigInfo, "Exception", e);
            }
            file = new File(str3);
            if (file.exists()) {
                LogUtils.e("ContentValues", str + " 下载：" + file.toString() + "发生异常___");
                file.delete();
            }
            a(closeable);
            a(inputStream);
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            a(closeable);
            a(inputStream);
            throw th;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
