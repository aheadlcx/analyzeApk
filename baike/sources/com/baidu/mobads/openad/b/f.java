package com.baidu.mobads.openad.b;

import android.content.Context;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

public class f extends Observable implements IOAdDownloader, Runnable {
    protected Context a;
    protected URL b;
    protected String c;
    protected String d;
    protected int e;
    protected DownloadStatus f;
    protected int g;
    protected int h;
    private boolean i = false;

    protected f(Context context, URL url, String str, String str2, boolean z) {
        this.a = context;
        this.b = url;
        this.c = str;
        this.i = z;
        if (str2 == null || str2.trim().length() <= 0) {
            String file = url.getFile();
            this.d = file.substring(file.lastIndexOf(47) + 1);
        } else {
            this.d = str2;
        }
        this.e = -1;
        this.f = DownloadStatus.DOWNLOADING;
        this.g = 0;
        this.h = 0;
    }

    @Deprecated
    public void pause() {
    }

    @Deprecated
    public void cancel() {
    }

    @Deprecated
    public void resume() {
    }

    public void start() {
        a(DownloadStatus.DOWNLOADING);
        b();
    }

    public String getURL() {
        return this.b.toString();
    }

    public int getFileSize() {
        return this.e;
    }

    public float getProgress() {
        return Math.abs((((float) this.g) / ((float) this.e)) * 100.0f);
    }

    public DownloadStatus getState() {
        return this.f;
    }

    protected void a(DownloadStatus downloadStatus) {
        this.f = downloadStatus;
        c();
    }

    protected void b() {
        new Thread(this).start();
    }

    protected void a(int i, float f) {
        this.g += i;
        c();
    }

    protected void c() {
        setChanged();
        notifyObservers();
    }

    public String getOutputPath() {
        return this.c + this.d;
    }

    private void d() {
        a(DownloadStatus.ERROR);
    }

    public void run() {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        HttpURLConnection httpURLConnection;
        Exception exception;
        BufferedOutputStream bufferedOutputStream;
        IXAdLogger adLogger;
        Object[] objArr;
        Throwable th;
        HttpURLConnection httpURLConnection2;
        BufferedOutputStream bufferedOutputStream2;
        Throwable th2;
        ByteArrayOutputStream byteArrayOutputStream;
        Exception exception2;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) this.b.openConnection();
            try {
                httpURLConnection3.setConnectTimeout(10000);
                httpURLConnection3.setInstanceFollowRedirects(true);
                httpURLConnection3.connect();
                if (httpURLConnection3.getResponseCode() / 100 != 2) {
                    d();
                }
                int contentLength = httpURLConnection3.getContentLength();
                if (contentLength > 0) {
                    this.e = contentLength;
                }
                File file = new File(this.c);
                if (!file.exists()) {
                    file.mkdirs();
                }
                bufferedInputStream = new BufferedInputStream(httpURLConnection3.getInputStream());
            } catch (Exception e) {
                bufferedInputStream2 = null;
                httpURLConnection = httpURLConnection3;
                exception = e;
                bufferedOutputStream = null;
                try {
                    XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", exception.getMessage());
                    d();
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception exception3) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", exception3.getMessage());
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception exception32) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", exception32.getMessage());
                        }
                    }
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception exception322) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", exception322.getMessage());
                        }
                    }
                    if (httpURLConnection == null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception exception3222) {
                            adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                            objArr = new Object[]{"OAdSimpleFileDownloader", exception3222.getMessage()};
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream;
                    httpURLConnection2 = httpURLConnection;
                    bufferedInputStream = bufferedInputStream2;
                    bufferedOutputStream2 = bufferedOutputStream3;
                    if (bufferedOutputStream2 != null) {
                        try {
                            bufferedOutputStream2.close();
                        } catch (Exception e2) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", e2.getMessage());
                        }
                    }
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e3) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", e3.getMessage());
                        }
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e32) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", e32.getMessage());
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e4) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().w("OAdSimpleFileDownloader", e4.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                bufferedOutputStream2 = null;
                bufferedInputStream = null;
                th2 = th4;
                httpURLConnection2 = httpURLConnection3;
                th = th2;
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
            try {
                bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(getOutputPath() + FileType.TEMP));
                try {
                    byte[] bArr = new byte[10240];
                    if (this.i) {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                    } else {
                        byteArrayOutputStream = null;
                    }
                    int i = 0;
                    while (this.f == DownloadStatus.DOWNLOADING) {
                        try {
                            int read = bufferedInputStream.read(bArr, 0, 10240);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream2.write(bArr, 0, read);
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            i += read;
                            a(read, ((float) i) / ((float) this.e));
                        } catch (Exception e322) {
                            exception2 = e322;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            bufferedOutputStream = bufferedOutputStream2;
                            bufferedInputStream2 = bufferedInputStream;
                            httpURLConnection = httpURLConnection3;
                            exception3222 = exception2;
                        } catch (Throwable th5) {
                            th2 = th5;
                            byteArrayOutputStream2 = byteArrayOutputStream;
                            httpURLConnection2 = httpURLConnection3;
                            th = th2;
                        }
                    }
                    if (byteArrayOutputStream != null) {
                    }
                    if (this.f == DownloadStatus.DOWNLOADING) {
                        a();
                        a(DownloadStatus.COMPLETED);
                    } else if (this.f == DownloadStatus.ERROR) {
                    }
                    if (bufferedOutputStream2 != null) {
                        try {
                            bufferedOutputStream2.close();
                        } catch (Exception e3222) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", e3222.getMessage());
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e42) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", e42.getMessage());
                        }
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e422) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", e422.getMessage());
                        }
                    }
                    if (httpURLConnection3 != null) {
                        try {
                            httpURLConnection3.disconnect();
                        } catch (Exception exception32222) {
                            adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                            objArr = new Object[]{"OAdSimpleFileDownloader", exception32222.getMessage()};
                            adLogger.w(objArr);
                        }
                    }
                } catch (Exception e4222) {
                    exception2 = e4222;
                    bufferedOutputStream = bufferedOutputStream2;
                    bufferedInputStream2 = bufferedInputStream;
                    httpURLConnection = httpURLConnection3;
                    exception32222 = exception2;
                    XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", exception32222.getMessage());
                    d();
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (httpURLConnection == null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th42) {
                    th2 = th42;
                    httpURLConnection2 = httpURLConnection3;
                    th = th2;
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e42222) {
                bufferedInputStream2 = bufferedInputStream;
                httpURLConnection = httpURLConnection3;
                exception32222 = e42222;
                bufferedOutputStream = null;
                XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", exception32222.getMessage());
                d();
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (httpURLConnection == null) {
                    httpURLConnection.disconnect();
                }
            } catch (Throwable th422) {
                bufferedOutputStream2 = null;
                th2 = th422;
                httpURLConnection2 = httpURLConnection3;
                th = th2;
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception32222 = e5;
            bufferedOutputStream = null;
            bufferedInputStream2 = null;
            httpURLConnection = null;
            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdSimpleFileDownloader", exception32222.getMessage());
            d();
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            if (httpURLConnection == null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th6) {
            th = th6;
            bufferedOutputStream2 = null;
            bufferedInputStream = null;
            httpURLConnection2 = null;
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    protected void a() {
        XAdSDKFoundationFacade.getInstance().getIoUtils().renameFile(this.c + this.d + FileType.TEMP, this.c + this.d);
    }

    @Deprecated
    public String getTitle() {
        return null;
    }

    @Deprecated
    public String getPackageName() {
        return null;
    }

    public void removeObservers() {
    }

    @Deprecated
    public String getTargetURL() {
        return null;
    }

    public boolean isPausedManually() {
        return false;
    }

    public void setPausedManually(boolean z) {
    }
}
