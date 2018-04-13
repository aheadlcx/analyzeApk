package com.baidu.mobads.openad.b;

import android.content.Context;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class a extends Observable implements IOAdDownloader, Runnable {
    protected Context a;
    protected URL b;
    protected URL c;
    protected String d;
    protected int e;
    protected Boolean f = Boolean.valueOf(true);
    protected String g;
    protected int h;
    protected DownloadStatus i;
    protected volatile int j;
    protected int k;
    protected ArrayList<a> l;
    g m = null;
    private String n;
    private String o;
    private boolean p = false;

    protected class a implements Runnable {
        protected int a;
        protected URL b;
        protected String c;
        protected int d;
        protected int e;
        protected int f;
        protected boolean g;
        protected Thread h;
        final /* synthetic */ a i;
        private volatile boolean j = false;
        private volatile int k = 0;
        private HttpURLConnection l;

        public a(a aVar, int i, URL url, String str, int i2, int i3, int i4) {
            this.i = aVar;
            this.a = i;
            this.b = url;
            this.c = str;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = false;
        }

        public boolean a() {
            return this.g;
        }

        public synchronized void b() {
            this.j = false;
            this.h = new Thread(this);
            this.h.start();
        }

        public synchronized void c() {
            this.j = true;
            this.k++;
        }

        public void a(HttpURLConnection httpURLConnection) {
            this.l = httpURLConnection;
        }

        public void d() {
            if (this.h != null) {
                this.h.join();
                return;
            }
            XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", "Warning: mThread in DownloadThread.waitFinish is null"});
        }

        public void run() {
            RandomAccessFile randomAccessFile;
            BufferedInputStream bufferedInputStream;
            HttpURLConnection httpURLConnection;
            IXAdLogger adLogger;
            Object[] objArr;
            Exception e;
            HttpURLConnection httpURLConnection2;
            BufferedInputStream bufferedInputStream2;
            Throwable th;
            RandomAccessFile randomAccessFile2 = null;
            int i = this.k;
            BufferedInputStream bufferedInputStream3 = null;
            RandomAccessFile randomAccessFile3 = null;
            try {
                if (this.d + this.f >= this.e) {
                    this.g = true;
                    randomAccessFile = null;
                    bufferedInputStream = null;
                    httpURLConnection = null;
                } else {
                    if (this.l == null) {
                        HttpURLConnection httpURLConnection3 = (HttpURLConnection) this.b.openConnection();
                        try {
                            if (this.i.f.booleanValue()) {
                                httpURLConnection3.setRequestProperty("Range", "bytes=" + ((this.d + this.f) + "-" + this.e));
                            } else {
                                this.f = 0;
                            }
                            httpURLConnection3.connect();
                            int responseCode = httpURLConnection3.getResponseCode();
                            if (i != this.k) {
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                                if (null != null) {
                                    try {
                                        randomAccessFile3.close();
                                    } catch (Exception e2) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e2.getMessage()});
                                    }
                                }
                                if (null != null) {
                                    try {
                                        bufferedInputStream3.close();
                                    } catch (Exception e3) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e3.getMessage()});
                                    }
                                }
                                if (httpURLConnection3 != null) {
                                    try {
                                        httpURLConnection3.disconnect();
                                        return;
                                    } catch (Exception e4) {
                                        adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                                        objArr = new Object[]{"DownloadThread", e4.getMessage()};
                                        adLogger.w(objArr);
                                        return;
                                    }
                                }
                                return;
                            } else if (responseCode / 100 != 2) {
                                this.i.b();
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                                if (null != null) {
                                    try {
                                        randomAccessFile3.close();
                                    } catch (Exception e22) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e22.getMessage()});
                                    }
                                }
                                if (null != null) {
                                    try {
                                        bufferedInputStream3.close();
                                    } catch (Exception e32) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e32.getMessage()});
                                    }
                                }
                                if (httpURLConnection3 != null) {
                                    try {
                                        httpURLConnection3.disconnect();
                                        return;
                                    } catch (Exception e42) {
                                        adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                                        objArr = new Object[]{"DownloadThread", e42.getMessage()};
                                        adLogger.w(objArr);
                                        return;
                                    }
                                }
                                return;
                            } else if (httpURLConnection3.getContentType().equals("text/html")) {
                                this.i.b();
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                                if (null != null) {
                                    try {
                                        randomAccessFile3.close();
                                    } catch (Exception e222) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e222.getMessage()});
                                    }
                                }
                                if (null != null) {
                                    try {
                                        bufferedInputStream3.close();
                                    } catch (Exception e322) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e322.getMessage()});
                                    }
                                }
                                if (httpURLConnection3 != null) {
                                    try {
                                        httpURLConnection3.disconnect();
                                        return;
                                    } catch (Exception e422) {
                                        adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                                        objArr = new Object[]{"DownloadThread", e422.getMessage()};
                                        adLogger.w(objArr);
                                        return;
                                    }
                                }
                                return;
                            } else {
                                httpURLConnection = httpURLConnection3;
                            }
                        } catch (Exception e3222) {
                            httpURLConnection2 = httpURLConnection3;
                            e422 = e3222;
                            randomAccessFile = null;
                            try {
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", e422.getMessage());
                                if (i == this.k) {
                                    this.i.b();
                                }
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Exception e4222) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e4222.getMessage()});
                                    }
                                }
                                if (bufferedInputStream2 != null) {
                                    try {
                                        bufferedInputStream2.close();
                                    } catch (Exception e42222) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e42222.getMessage()});
                                    }
                                }
                                if (httpURLConnection2 != null) {
                                    try {
                                        httpURLConnection2.disconnect();
                                    } catch (Exception e422222) {
                                        adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                                        objArr = new Object[]{"DownloadThread", e422222.getMessage()};
                                        adLogger.w(objArr);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                httpURLConnection = httpURLConnection2;
                                bufferedInputStream = bufferedInputStream2;
                                randomAccessFile2 = randomAccessFile;
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (Exception e32222) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e32222.getMessage()});
                                    }
                                }
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (Exception e322222) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e322222.getMessage()});
                                    }
                                }
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e3222222) {
                                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e3222222.getMessage()});
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            bufferedInputStream = null;
                            httpURLConnection = httpURLConnection3;
                            th = th3;
                            XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                            if (randomAccessFile2 != null) {
                                randomAccessFile2.close();
                            }
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    }
                    HttpURLConnection httpURLConnection4 = this.l;
                    try {
                        this.l = null;
                        httpURLConnection = httpURLConnection4;
                    } catch (Exception e5) {
                        e422222 = e5;
                        httpURLConnection2 = httpURLConnection4;
                        randomAccessFile = null;
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", e422222.getMessage());
                        if (i == this.k) {
                            this.i.b();
                        }
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedInputStream = null;
                        httpURLConnection = httpURLConnection4;
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                    try {
                        bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                        try {
                            int i2 = this.d + this.f;
                            XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "tmpStartByte = " + i2);
                            randomAccessFile = new RandomAccessFile(this.c, "rw");
                            try {
                                randomAccessFile.seek((long) i2);
                                byte[] bArr = new byte[102400];
                                while (this.i.i == DownloadStatus.DOWNLOADING) {
                                    int read = bufferedInputStream.read(bArr, 0, 102400);
                                    if (read == -1 || i2 >= this.e || i != this.k) {
                                        break;
                                    }
                                    randomAccessFile.write(bArr, 0, read);
                                    this.f += read;
                                    i2 += read;
                                    this.i.a(read);
                                    synchronized (this) {
                                        if (this.j) {
                                        }
                                    }
                                }
                                if (i2 >= this.e) {
                                    this.g = true;
                                }
                            } catch (Exception e6) {
                                e422222 = e6;
                                bufferedInputStream2 = bufferedInputStream;
                                httpURLConnection2 = httpURLConnection;
                            } catch (Throwable th5) {
                                th = th5;
                                randomAccessFile2 = randomAccessFile;
                            }
                        } catch (Exception e7) {
                            e422222 = e7;
                            randomAccessFile = null;
                            bufferedInputStream2 = bufferedInputStream;
                            httpURLConnection2 = httpURLConnection;
                            XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", e422222.getMessage());
                            if (i == this.k) {
                                this.i.b();
                            }
                            XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                            if (randomAccessFile2 != null) {
                                randomAccessFile2.close();
                            }
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (Exception e8) {
                        e422222 = e8;
                        randomAccessFile = null;
                        httpURLConnection2 = httpURLConnection;
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", e422222.getMessage());
                        if (i == this.k) {
                            this.i.b();
                        }
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        bufferedInputStream = null;
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                }
                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Exception e4222222) {
                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e4222222.getMessage()});
                    }
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e42222222) {
                        XAdSDKFoundationFacade.getInstance().getAdLogger().w(new Object[]{"DownloadThread", e42222222.getMessage()});
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e422222222) {
                        adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                        objArr = new Object[]{"DownloadThread", e422222222.getMessage()};
                    }
                }
            } catch (Exception e9) {
                e422222222 = e9;
                randomAccessFile = null;
                httpURLConnection2 = null;
                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", e422222222.getMessage());
                if (i == this.k) {
                    this.i.b();
                }
                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
            } catch (Throwable th8) {
                th = th8;
                bufferedInputStream = null;
                httpURLConnection = null;
                XAdSDKFoundationFacade.getInstance().getAdLogger().d("DownloadThread", "Thread[" + this.a + "] ver(" + i + ") executed end; isFinished=" + this.g);
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        }
    }

    public boolean isPausedManually() {
        return this.p;
    }

    public void setPausedManually(boolean z) {
        this.p = z;
    }

    public a(Context context, URL url, String str, String str2, int i, String str3, String str4) {
        this.a = context;
        this.b = url;
        this.d = str;
        this.e = i;
        if (str2 == null || str2.trim().length() <= 0) {
            String file = url.getFile();
            this.g = file.substring(file.lastIndexOf(47) + 1);
        } else {
            this.g = str2;
        }
        this.h = -1;
        this.i = DownloadStatus.NONE;
        this.j = 0;
        this.k = 0;
        this.n = str3;
        this.o = str4;
        this.l = new ArrayList();
    }

    public void pause() {
        try {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "execute Pause; state = " + this.i);
            if (this.i == DownloadStatus.DOWNLOADING || this.i == DownloadStatus.ERROR || this.i == DownloadStatus.NONE) {
                if (this.l != null) {
                    for (int i = 0; i < this.l.size(); i++) {
                        if (!((a) this.l.get(i)).a()) {
                            ((a) this.l.get(i)).c();
                        }
                    }
                }
                a(DownloadStatus.PAUSED);
            }
        } catch (Exception e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "pause exception");
            com.baidu.mobads.c.a.a().a("apk download pause failed: " + e.toString());
        }
    }

    public void resume() {
        try {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "execute Resume; state = " + this.i);
            if (this.i == DownloadStatus.PAUSED || this.i == DownloadStatus.ERROR || this.i == DownloadStatus.CANCELLED) {
                a(DownloadStatus.INITING);
                setPausedManually(true);
                new Thread(this).start();
            }
        } catch (Exception e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "resume exception");
            com.baidu.mobads.c.a.a().a("apk download resume failed: " + e.toString());
        }
    }

    public void cancel() {
        try {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "execute Cancel; state = " + this.i);
            if (this.i == DownloadStatus.PAUSED || this.i == DownloadStatus.DOWNLOADING) {
                if (this.l != null) {
                    for (int i = 0; i < this.l.size(); i++) {
                        if (!((a) this.l.get(i)).a()) {
                            ((a) this.l.get(i)).c();
                        }
                    }
                }
                a(DownloadStatus.CANCELLED);
            }
        } catch (Exception e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "cancel exception");
            com.baidu.mobads.c.a.a().a("apk download cancel failed: " + e.toString());
        }
    }

    public String getURL() {
        return this.b.toString();
    }

    public int getFileSize() {
        return this.h;
    }

    public float getProgress() {
        return Math.abs((((float) this.j) / ((float) this.h)) * 100.0f);
    }

    public String getTitle() {
        return this.n;
    }

    public String getPackageName() {
        return this.o;
    }

    public DownloadStatus getState() {
        return this.i;
    }

    protected void a(DownloadStatus downloadStatus) {
        this.i = downloadStatus;
        a();
    }

    public void start() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "execute Start; state = " + this.i);
        if (this.i == DownloadStatus.NONE) {
            a(DownloadStatus.INITING);
            setPausedManually(true);
            new Thread(this).start();
        }
    }

    protected synchronized void a(int i) {
        this.j += i;
        int progress = (int) getProgress();
        if (this.k < progress) {
            this.k = progress;
            a();
        }
    }

    protected void a() {
        setChanged();
        notifyObservers();
    }

    public String getOutputPath() {
        return this.d + this.g;
    }

    protected synchronized void b() {
        this.i = DownloadStatus.ERROR;
        for (int i = 0; i < this.l.size(); i++) {
            if (!((a) this.l.get(i)).a()) {
                ((a) this.l.get(i)).c();
            }
        }
    }

    protected void a(HttpURLConnection httpURLConnection) {
        List arrayList;
        Throwable e;
        RandomAccessFile randomAccessFile;
        List arrayList2;
        int i;
        int i2;
        int round;
        int i3;
        a aVar;
        Object obj;
        List arrayList3;
        Iterator it;
        a aVar2;
        String url = this.c.toString();
        String str = (this.d + this.g) + FileType.TEMP;
        if (this.l.size() == 0) {
            File file = new File(this.d);
            if (!file.exists()) {
                file.mkdirs();
            }
            List list = null;
            File file2 = new File(str);
            if (this.f.booleanValue() && file2.exists() && file2.length() == ((long) this.h)) {
                try {
                    this.m = new g(this.a);
                    List<h> b = this.m.b(url, str);
                    if (b != null && b.size() > 0) {
                        arrayList = new ArrayList();
                        try {
                            HashSet hashSet = new HashSet();
                            for (h hVar : b) {
                                if (!hashSet.contains(Integer.valueOf(hVar.c()))) {
                                    hashSet.add(Integer.valueOf(hVar.c()));
                                    arrayList.add(hVar);
                                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "resume from db: start=" + hVar.d() + ";end =" + hVar.e() + ";complete=" + hVar.a());
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", e);
                            list = arrayList;
                            if (file2.exists()) {
                                file2.delete();
                            }
                            try {
                                file2.createNewFile();
                                randomAccessFile = new RandomAccessFile(file2, "rwd");
                                randomAccessFile.setLength((long) this.h);
                                randomAccessFile.close();
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader.init():  建立完random文件 ts:" + System.currentTimeMillis());
                                arrayList2 = new ArrayList();
                                i = -1;
                                i2 = 0;
                                if (this.e > 1) {
                                    arrayList2.add(new h(1, url, str, 0, this.h, 0));
                                    list = arrayList2;
                                } else {
                                    round = Math.round((((float) this.h) / ((float) this.e)) / 102400.0f) * 102400;
                                    while (i < this.h) {
                                        i3 = i + 1;
                                        if (i + round < this.h) {
                                            i = this.h;
                                        } else {
                                            i += round;
                                        }
                                        i2++;
                                        arrayList2.add(new h(i2, url, str, i3, i, 0));
                                    }
                                    list = arrayList2;
                                }
                                for (h hVar2 : r2) {
                                    aVar = new a(this, hVar2.c(), this.c, hVar2.f(), hVar2.d(), hVar2.e(), hVar2.a());
                                    aVar.a(httpURLConnection);
                                    this.l.add(aVar);
                                }
                                if (this.f.booleanValue()) {
                                    i3 = 0;
                                } else {
                                    i3 = 0;
                                    for (i2 = 0; i2 < this.l.size(); i2++) {
                                        i3 += ((a) this.l.get(i2)).f;
                                    }
                                }
                                this.j = i3;
                                this.k = (int) getProgress();
                                a(DownloadStatus.DOWNLOADING);
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader starts unfinished threads and waits threads end");
                                for (i2 = 0; i2 < this.l.size(); i2++) {
                                    if (((a) this.l.get(i2)).a()) {
                                        ((a) this.l.get(i2)).b();
                                    }
                                }
                                for (i2 = 0; i2 < this.l.size(); i2++) {
                                    if (((a) this.l.get(i2)).a()) {
                                        ((a) this.l.get(i2)).d();
                                    }
                                }
                                if (this.i != DownloadStatus.DOWNLOADING) {
                                    for (i2 = 0; i2 < this.l.size(); i2++) {
                                        if (((a) this.l.get(i2)).a()) {
                                            obj = 1;
                                            break;
                                        }
                                    }
                                    obj = null;
                                    if (obj != null) {
                                        a(this.l);
                                        a(DownloadStatus.COMPLETED);
                                    } else {
                                        a(DownloadStatus.ERROR);
                                    }
                                } else if (this.i != DownloadStatus.ERROR) {
                                    a(DownloadStatus.ERROR);
                                } else if (this.i != DownloadStatus.CANCELLED) {
                                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader is cancelled");
                                } else if (this.i == DownloadStatus.PAUSED) {
                                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader is paused");
                                }
                                if (this.i == DownloadStatus.COMPLETED) {
                                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "save database now");
                                    if (!this.f.booleanValue()) {
                                        try {
                                            if (this.m == null) {
                                                this.m = new g(this.a);
                                            }
                                            arrayList3 = new ArrayList();
                                            it = this.l.iterator();
                                            while (it.hasNext()) {
                                                aVar2 = (a) it.next();
                                                arrayList3.add(new h(aVar2.a, url, str, aVar2.d, aVar2.e, aVar2.f));
                                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "save to db: start=" + aVar2.d + ";end =" + aVar2.e + ";complete=" + aVar2.f);
                                            }
                                            if (this.m.a(url, str)) {
                                                this.m.a(arrayList3);
                                            } else {
                                                this.m.b(arrayList3);
                                            }
                                        } catch (Throwable e3) {
                                            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", e3);
                                            return;
                                        }
                                    }
                                }
                            } catch (Exception e4) {
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", " 建立文件失败:");
                                a(DownloadStatus.ERROR);
                                return;
                            }
                        }
                        list = arrayList;
                    }
                } catch (Throwable e5) {
                    Throwable th = e5;
                    arrayList = null;
                    e3 = th;
                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", e3);
                    list = arrayList;
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.createNewFile();
                    randomAccessFile = new RandomAccessFile(file2, "rwd");
                    randomAccessFile.setLength((long) this.h);
                    randomAccessFile.close();
                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader.init():  建立完random文件 ts:" + System.currentTimeMillis());
                    arrayList2 = new ArrayList();
                    i = -1;
                    i2 = 0;
                    if (this.e > 1) {
                        round = Math.round((((float) this.h) / ((float) this.e)) / 102400.0f) * 102400;
                        while (i < this.h) {
                            i3 = i + 1;
                            if (i + round < this.h) {
                                i += round;
                            } else {
                                i = this.h;
                            }
                            i2++;
                            arrayList2.add(new h(i2, url, str, i3, i, 0));
                        }
                        list = arrayList2;
                    } else {
                        arrayList2.add(new h(1, url, str, 0, this.h, 0));
                        list = arrayList2;
                    }
                    for (h hVar22 : r2) {
                        aVar = new a(this, hVar22.c(), this.c, hVar22.f(), hVar22.d(), hVar22.e(), hVar22.a());
                        aVar.a(httpURLConnection);
                        this.l.add(aVar);
                    }
                    if (this.f.booleanValue()) {
                        i3 = 0;
                        for (i2 = 0; i2 < this.l.size(); i2++) {
                            i3 += ((a) this.l.get(i2)).f;
                        }
                    } else {
                        i3 = 0;
                    }
                    this.j = i3;
                    this.k = (int) getProgress();
                    a(DownloadStatus.DOWNLOADING);
                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader starts unfinished threads and waits threads end");
                    for (i2 = 0; i2 < this.l.size(); i2++) {
                        if (((a) this.l.get(i2)).a()) {
                            ((a) this.l.get(i2)).b();
                        }
                    }
                    for (i2 = 0; i2 < this.l.size(); i2++) {
                        if (((a) this.l.get(i2)).a()) {
                            ((a) this.l.get(i2)).d();
                        }
                    }
                    if (this.i != DownloadStatus.DOWNLOADING) {
                        for (i2 = 0; i2 < this.l.size(); i2++) {
                            if (((a) this.l.get(i2)).a()) {
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj != null) {
                            a(DownloadStatus.ERROR);
                        } else {
                            a(this.l);
                            a(DownloadStatus.COMPLETED);
                        }
                    } else if (this.i != DownloadStatus.ERROR) {
                        a(DownloadStatus.ERROR);
                    } else if (this.i != DownloadStatus.CANCELLED) {
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader is cancelled");
                    } else if (this.i == DownloadStatus.PAUSED) {
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader is paused");
                    }
                    if (this.i == DownloadStatus.COMPLETED) {
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "save database now");
                        if (!this.f.booleanValue()) {
                            if (this.m == null) {
                                this.m = new g(this.a);
                            }
                            arrayList3 = new ArrayList();
                            it = this.l.iterator();
                            while (it.hasNext()) {
                                aVar2 = (a) it.next();
                                arrayList3.add(new h(aVar2.a, url, str, aVar2.d, aVar2.e, aVar2.f));
                                XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "save to db: start=" + aVar2.d + ";end =" + aVar2.e + ";complete=" + aVar2.f);
                            }
                            if (this.m.a(url, str)) {
                                this.m.b(arrayList3);
                            } else {
                                this.m.a(arrayList3);
                            }
                        }
                    }
                }
            }
            if (list == null || list.size() < 1) {
                if (file2.exists()) {
                    file2.delete();
                }
                file2.createNewFile();
                randomAccessFile = new RandomAccessFile(file2, "rwd");
                randomAccessFile.setLength((long) this.h);
                randomAccessFile.close();
                XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader.init():  建立完random文件 ts:" + System.currentTimeMillis());
                arrayList2 = new ArrayList();
                i = -1;
                i2 = 0;
                if (this.e > 1) {
                    round = Math.round((((float) this.h) / ((float) this.e)) / 102400.0f) * 102400;
                    while (i < this.h) {
                        i3 = i + 1;
                        if (i + round < this.h) {
                            i += round;
                        } else {
                            i = this.h;
                        }
                        i2++;
                        arrayList2.add(new h(i2, url, str, i3, i, 0));
                    }
                    list = arrayList2;
                } else {
                    arrayList2.add(new h(1, url, str, 0, this.h, 0));
                    list = arrayList2;
                }
            }
            for (h hVar222 : r2) {
                aVar = new a(this, hVar222.c(), this.c, hVar222.f(), hVar222.d(), hVar222.e(), hVar222.a());
                if (hVar222.d() == 0 && hVar222.a() == 0) {
                    aVar.a(httpURLConnection);
                }
                this.l.add(aVar);
            }
        }
        if (this.f.booleanValue()) {
            i3 = 0;
            for (i2 = 0; i2 < this.l.size(); i2++) {
                i3 += ((a) this.l.get(i2)).f;
            }
        } else {
            i3 = 0;
        }
        this.j = i3;
        this.k = (int) getProgress();
        a(DownloadStatus.DOWNLOADING);
        XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader starts unfinished threads and waits threads end");
        for (i2 = 0; i2 < this.l.size(); i2++) {
            if (((a) this.l.get(i2)).a()) {
                ((a) this.l.get(i2)).b();
            }
        }
        for (i2 = 0; i2 < this.l.size(); i2++) {
            if (((a) this.l.get(i2)).a()) {
                ((a) this.l.get(i2)).d();
            }
        }
        if (this.i != DownloadStatus.DOWNLOADING) {
            for (i2 = 0; i2 < this.l.size(); i2++) {
                if (((a) this.l.get(i2)).a()) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                a(DownloadStatus.ERROR);
            } else {
                a(this.l);
                a(DownloadStatus.COMPLETED);
            }
        } else if (this.i != DownloadStatus.ERROR) {
            a(DownloadStatus.ERROR);
        } else if (this.i != DownloadStatus.CANCELLED) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader is cancelled");
        } else if (this.i == DownloadStatus.PAUSED) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "Downloader is paused");
        }
        if (this.i == DownloadStatus.COMPLETED) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "save database now");
            if (!this.f.booleanValue()) {
                if (this.m == null) {
                    this.m = new g(this.a);
                }
                arrayList3 = new ArrayList();
                it = this.l.iterator();
                while (it.hasNext()) {
                    aVar2 = (a) it.next();
                    arrayList3.add(new h(aVar2.a, url, str, aVar2.d, aVar2.e, aVar2.f));
                    XAdSDKFoundationFacade.getInstance().getAdLogger().d("Downloader", "save to db: start=" + aVar2.d + ";end =" + aVar2.e + ";complete=" + aVar2.f);
                }
                if (this.m.a(url, str)) {
                    this.m.b(arrayList3);
                } else {
                    this.m.a(arrayList3);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r0 = 0;
        r8 = 2;
        r7 = 0;
        r6 = 1;
        r1 = r10.c;
        if (r1 == 0) goto L_0x000c;
    L_0x0008:
        r1 = r10.h;
        if (r1 >= r6) goto L_0x01d2;
    L_0x000c:
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();	 Catch:{ Exception -> 0x01a4, all -> 0x01c8 }
        r1 = r1.getURIUitls();	 Catch:{ Exception -> 0x01a4, all -> 0x01c8 }
        r2 = r10.b;	 Catch:{ Exception -> 0x01a4, all -> 0x01c8 }
        r0 = r1.getHttpURLConnection(r2);	 Catch:{ Exception -> 0x01a4, all -> 0x01c8 }
        r1 = "Range";
        r2 = "bytes=0-";
        r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x01a4 }
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x01a4 }
        r1 = 1;
        r0.setInstanceFollowRedirects(r1);	 Catch:{ Exception -> 0x01a4 }
        r0.connect();	 Catch:{ Exception -> 0x01a4 }
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x01a4 }
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r1 == r2) goto L_0x0039;
    L_0x0035:
        r2 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r1 != r2) goto L_0x0045;
    L_0x0039:
        r1 = 0;
        r0.setInstanceFollowRedirects(r1);	 Catch:{ Exception -> 0x01a4 }
        r0 = r10.b(r0);	 Catch:{ Exception -> 0x01a4 }
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x01a4 }
    L_0x0045:
        r1 = r1 / 100;
        if (r1 == r8) goto L_0x0054;
    L_0x0049:
        r1 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.ERROR;	 Catch:{ Exception -> 0x01a4 }
        r10.a(r1);	 Catch:{ Exception -> 0x01a4 }
        if (r0 == 0) goto L_0x0053;
    L_0x0050:
        r0.disconnect();	 Catch:{ Exception -> 0x0206 }
    L_0x0053:
        return;
    L_0x0054:
        r1 = r0.getContentType();	 Catch:{ Exception -> 0x01a4 }
        r2 = "text/html";
        r1 = r1.equals(r2);	 Catch:{ Exception -> 0x01a4 }
        if (r1 == 0) goto L_0x0084;
    L_0x0060:
        r1 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.ERROR;	 Catch:{ Exception -> 0x01a4 }
        r10.a(r1);	 Catch:{ Exception -> 0x01a4 }
        if (r0 == 0) goto L_0x0053;
    L_0x0067:
        r0.disconnect();	 Catch:{ Exception -> 0x006b }
        goto L_0x0053;
    L_0x006b:
        r0 = move-exception;
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = new java.lang.Object[r8];
        r3 = "Downloader";
        r2[r7] = r3;
        r0 = r0.getMessage();
        r2[r6] = r0;
    L_0x0080:
        r1.w(r2);
        goto L_0x0053;
    L_0x0084:
        r1 = r0.getContentLength();	 Catch:{ Exception -> 0x01a4 }
        if (r1 >= r6) goto L_0x00ab;
    L_0x008a:
        r1 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.ERROR;	 Catch:{ Exception -> 0x01a4 }
        r10.a(r1);	 Catch:{ Exception -> 0x01a4 }
        if (r0 == 0) goto L_0x0053;
    L_0x0091:
        r0.disconnect();	 Catch:{ Exception -> 0x0095 }
        goto L_0x0053;
    L_0x0095:
        r0 = move-exception;
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = new java.lang.Object[r8];
        r3 = "Downloader";
        r2[r7] = r3;
        r0 = r0.getMessage();
        r2[r6] = r0;
        goto L_0x0080;
    L_0x00ab:
        r2 = 5120000; // 0x4e2000 float:7.174648E-39 double:2.529616E-317;
        if (r1 >= r2) goto L_0x00b3;
    L_0x00b0:
        r2 = 1;
        r10.e = r2;	 Catch:{ Exception -> 0x01a4 }
    L_0x00b3:
        r2 = r0.getURL();	 Catch:{ Exception -> 0x01a4 }
        r10.c = r2;	 Catch:{ Exception -> 0x01a4 }
        r2 = "mounted";
        r3 = android.os.Environment.getExternalStorageState();	 Catch:{ Exception -> 0x01a4 }
        r2 = r2.equals(r3);	 Catch:{ Exception -> 0x01a4 }
        if (r2 != 0) goto L_0x00e6;
    L_0x00c5:
        r1 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.ERROR;	 Catch:{ Exception -> 0x01a4 }
        r10.a(r1);	 Catch:{ Exception -> 0x01a4 }
        if (r0 == 0) goto L_0x0053;
    L_0x00cc:
        r0.disconnect();	 Catch:{ Exception -> 0x00d0 }
        goto L_0x0053;
    L_0x00d0:
        r0 = move-exception;
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = new java.lang.Object[r8];
        r3 = "Downloader";
        r2[r7] = r3;
        r0 = r0.getMessage();
        r2[r6] = r0;
        goto L_0x0080;
    L_0x00e6:
        r2 = r10.a;	 Catch:{ Exception -> 0x01a4 }
        r2 = com.baidu.mobads.utils.k.a(r2);	 Catch:{ Exception -> 0x01a4 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01a4 }
        r3.<init>();	 Catch:{ Exception -> 0x01a4 }
        r4 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();	 Catch:{ Exception -> 0x01a4 }
        r4 = r4.getCommonUtils();	 Catch:{ Exception -> 0x01a4 }
        r5 = r10.c;	 Catch:{ Exception -> 0x01a4 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x01a4 }
        r4 = r4.md5(r5);	 Catch:{ Exception -> 0x01a4 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01a4 }
        r4 = ".apk";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x01a4 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01a4 }
        r10.d = r2;	 Catch:{ Exception -> 0x01a4 }
        r10.g = r3;	 Catch:{ Exception -> 0x01a4 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01a4 }
        r4.<init>();	 Catch:{ Exception -> 0x01a4 }
        r2 = r4.append(r2);	 Catch:{ Exception -> 0x01a4 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x01a4 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x01a4 }
        r3 = new java.io.File;	 Catch:{ Exception -> 0x01a4 }
        r3.<init>(r2);	 Catch:{ Exception -> 0x01a4 }
        r2 = r3.exists();	 Catch:{ Exception -> 0x01a4 }
        if (r2 == 0) goto L_0x0154;
    L_0x0131:
        r1 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.COMPLETED;	 Catch:{ Exception -> 0x01a4 }
        r10.a(r1);	 Catch:{ Exception -> 0x01a4 }
        if (r0 == 0) goto L_0x0053;
    L_0x0138:
        r0.disconnect();	 Catch:{ Exception -> 0x013d }
        goto L_0x0053;
    L_0x013d:
        r0 = move-exception;
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = new java.lang.Object[r8];
        r3 = "Downloader";
        r2[r7] = r3;
        r0 = r0.getMessage();
        r2[r6] = r0;
        goto L_0x0080;
    L_0x0154:
        r2 = "Content-Range";
        r2 = r0.getHeaderField(r2);	 Catch:{ Exception -> 0x01a4 }
        if (r2 != 0) goto L_0x017c;
    L_0x015c:
        r2 = "Accept-Ranges";
        r2 = r0.getHeaderField(r2);	 Catch:{ Exception -> 0x01a4 }
        if (r2 == 0) goto L_0x0172;
    L_0x0164:
        r2 = "Accept-Ranges";
        r2 = r0.getHeaderField(r2);	 Catch:{ Exception -> 0x01a4 }
        r3 = "none";
        r2 = r2.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x01a4 }
        if (r2 == 0) goto L_0x017c;
    L_0x0172:
        r2 = 0;
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ Exception -> 0x01a4 }
        r10.f = r2;	 Catch:{ Exception -> 0x01a4 }
        r2 = 1;
        r10.e = r2;	 Catch:{ Exception -> 0x01a4 }
    L_0x017c:
        r2 = r10.h;	 Catch:{ Exception -> 0x01a4 }
        r3 = -1;
        if (r2 != r3) goto L_0x0183;
    L_0x0181:
        r10.h = r1;	 Catch:{ Exception -> 0x01a4 }
    L_0x0183:
        r10.a(r0);	 Catch:{ Exception -> 0x01a4 }
        if (r0 == 0) goto L_0x0053;
    L_0x0188:
        r0.disconnect();	 Catch:{ Exception -> 0x018d }
        goto L_0x0053;
    L_0x018d:
        r0 = move-exception;
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = new java.lang.Object[r8];
        r3 = "Downloader";
        r2[r7] = r3;
        r0 = r0.getMessage();
        r2[r6] = r0;
        goto L_0x0080;
    L_0x01a4:
        r1 = move-exception;
        r1 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.ERROR;	 Catch:{ all -> 0x021d }
        r10.a(r1);	 Catch:{ all -> 0x021d }
        if (r0 == 0) goto L_0x0053;
    L_0x01ac:
        r0.disconnect();	 Catch:{ Exception -> 0x01b1 }
        goto L_0x0053;
    L_0x01b1:
        r0 = move-exception;
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = new java.lang.Object[r8];
        r3 = "Downloader";
        r2[r7] = r3;
        r0 = r0.getMessage();
        r2[r6] = r0;
        goto L_0x0080;
    L_0x01c8:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x01cc:
        if (r1 == 0) goto L_0x01d1;
    L_0x01ce:
        r1.disconnect();	 Catch:{ Exception -> 0x01ed }
    L_0x01d1:
        throw r0;
    L_0x01d2:
        r0 = 0;
        r10.a(r0);	 Catch:{ Exception -> 0x01d8 }
        goto L_0x0053;
    L_0x01d8:
        r0 = move-exception;
        r1 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.ERROR;
        r10.a(r1);
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = "Downloader";
        r1.d(r2, r0);
        goto L_0x0053;
    L_0x01ed:
        r1 = move-exception;
        r2 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r2 = r2.getAdLogger();
        r3 = new java.lang.Object[r8];
        r4 = "Downloader";
        r3[r7] = r4;
        r1 = r1.getMessage();
        r3[r6] = r1;
        r2.w(r3);
        goto L_0x01d1;
    L_0x0206:
        r0 = move-exception;
        r1 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r1 = r1.getAdLogger();
        r2 = new java.lang.Object[r8];
        r3 = "Downloader";
        r2[r7] = r3;
        r0 = r0.getMessage();
        r2[r6] = r0;
        goto L_0x0080;
    L_0x021d:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x01cc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.openad.b.a.run():void");
    }

    protected void a(ArrayList<a> arrayList) {
        XAdSDKFoundationFacade.getInstance().getIoUtils().renameFile(this.d + this.g + FileType.TEMP, this.d + this.g);
    }

    private HttpURLConnection b(HttpURLConnection httpURLConnection) {
        Exception e;
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        while (true) {
            HttpURLConnection httpURLConnection3;
            try {
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode != 302 && responseCode != 301) {
                    return httpURLConnection2;
                }
                this.b = new URL(httpURLConnection2.getHeaderField("Location"));
                httpURLConnection3 = (HttpURLConnection) this.b.openConnection();
                try {
                    httpURLConnection3.setConnectTimeout(10000);
                    httpURLConnection3.setInstanceFollowRedirects(false);
                    httpURLConnection3.setRequestProperty("Range", "bytes=0-");
                    httpURLConnection2 = httpURLConnection3;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                httpURLConnection3 = httpURLConnection2;
                e = exception;
            }
        }
        e.printStackTrace();
        return httpURLConnection3;
    }

    public void removeObservers() {
        deleteObservers();
    }

    public String getTargetURL() {
        if (this.c == null) {
            return null;
        }
        return this.c.toString();
    }
}
