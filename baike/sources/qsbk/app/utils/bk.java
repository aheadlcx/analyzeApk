package qsbk.app.utils;

import cz.msebera.android.httpclient.HttpHeaders;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import qsbk.app.utils.UpdateHelper.DownloadListener;

class bk implements Runnable {
    final /* synthetic */ DownloadListener a;
    final /* synthetic */ String b;
    final /* synthetic */ File c;
    final /* synthetic */ UpdateHelper d;

    bk(UpdateHelper updateHelper, DownloadListener downloadListener, String str, File file) {
        this.d = updateHelper;
        this.a = downloadListener;
        this.b = str;
        this.c = file;
    }

    public void run() {
        Throwable e;
        long j = 0;
        int i = 0;
        try {
            if (this.a != null) {
                this.a.onStart(this.b);
            }
            FileUtils.makeSureCanCreateFile(this.c);
            FileOutputStream fileOutputStream = null;
            try {
                HttpURLConnection a = this.d.a(this.b);
                while (a.getResponseCode() / 100 == 3 && i <= 3) {
                    a = this.d.a(a.getHeaderField(HttpHeaders.LOCATION));
                    i++;
                }
                InputStream inputStream = a.getInputStream();
                long contentLength = (long) a.getContentLength();
                if (contentLength < 0) {
                    contentLength = (long) inputStream.available();
                }
                if (inputStream != null) {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(this.c);
                    try {
                        byte[] bArr = new byte[2048];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                            j += (long) read;
                            if (this.a != null) {
                                this.a.onProgress(this.b, j, contentLength);
                            }
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.flush();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (this.a != null) {
                            this.a.onFinished(this.b);
                        }
                        fileOutputStream = fileOutputStream2;
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        try {
                            e.printStackTrace();
                            if (this.a != null) {
                                this.a.onFailure(this.b, e);
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            e = th;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw e;
                    }
                } else if (this.a != null) {
                    this.a.onFailure(this.b, new IOException("Cannot access url " + this.b));
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                if (this.a != null) {
                    this.a.onFailure(this.b, e);
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        } catch (Throwable e4) {
            e4.printStackTrace();
            if (this.a != null) {
                this.a.onFailure(this.b, e4);
            }
        }
    }
}
