package com.budejie.www.download;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.sprite.ads.internal.log.ADLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class a extends Thread {
    a$b a = a$b.a;
    private a b;
    private boolean c = false;
    private Map<String, a> d;
    private String e;
    private String f;
    private String g;
    private h h;
    private Context i;
    private Handler j = new Handler(Looper.getMainLooper());

    public interface a {
        void a(String str);

        void b(String str);
    }

    public void a(Context context) {
        this.i = context;
    }

    public a(h hVar) {
        this.h = hVar;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(String str) {
        this.e = str;
    }

    public String getUrl() {
        return this.e;
    }

    public void a(Map<String, a> map) {
        this.d = map;
    }

    public void b(String str) {
        this.g = str;
    }

    public void a() {
        this.c = true;
        this.h.a();
        this.d.remove(this.e);
        this.d.remove(this.f);
        if (isAlive()) {
            interrupt();
        }
    }

    public void run() {
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        Exception exception;
        Object obj;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        this.a = a$b.a;
        if (this.b != null) {
            this.b.a(this.e);
        }
        Process.setThreadPriority(10);
        FileOutputStream fileOutputStream2 = null;
        InputStream inputStream2 = null;
        long j = 0;
        try {
            URL url = new URL(i.b(this.e));
            if ("".equals(url)) {
                c("下载失败");
                switch (a$2.a[this.a.ordinal()]) {
                    case 1:
                        if (this.b != null) {
                            this.b.b(this.e);
                        }
                        c("下载完成");
                        this.h.a();
                        b();
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                    case 3:
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                }
                if (fileOutputStream != null) {
                    fileOutputStream.disconnect();
                }
                if (fileOutputStream != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream2.close();
                    return;
                }
                return;
            }
            this.f = d.a("", url.toString());
            if (this.d.containsKey(this.f)) {
                this.j.post(new a$1(this));
                switch (a$2.a[this.a.ordinal()]) {
                    case 1:
                        if (this.b != null) {
                            this.b.b(this.e);
                        }
                        c("下载完成");
                        this.h.a();
                        b();
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                    case 3:
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                }
                if (fileOutputStream != null) {
                    fileOutputStream.disconnect();
                }
                if (fileOutputStream != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream2.close();
                    return;
                }
                return;
            }
            this.d.put(this.f, this);
            this.d.put(this.e, this);
            a((int) null);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection2.setConnectTimeout(30000);
                httpURLConnection2.connect();
                inputStream = httpURLConnection2.getInputStream();
            } catch (Exception e3) {
                httpURLConnection = httpURLConnection2;
                exception = e3;
                obj = fileOutputStream;
                try {
                    exception.printStackTrace();
                    this.a = a$b.d;
                    c("下载失败");
                    switch (a$2.a[this.a.ordinal()]) {
                        case 1:
                            if (this.b != null) {
                                this.b.b(this.e);
                            }
                            c("下载完成");
                            this.h.a();
                            b();
                            this.d.remove(this.e);
                            this.d.remove(this.f);
                            break;
                        case 3:
                            this.d.remove(this.e);
                            this.d.remove(this.f);
                            break;
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                            return;
                        }
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = inputStream2;
                    switch (a$2.a[this.a.ordinal()]) {
                        case 1:
                            if (this.b != null) {
                                this.b.b(this.e);
                            }
                            c("下载完成");
                            this.h.a();
                            b();
                            this.d.remove(this.e);
                            this.d.remove(this.f);
                            break;
                        case 3:
                            this.d.remove(this.e);
                            this.d.remove(this.f);
                            break;
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            throw th;
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Object obj2 = fileOutputStream;
                httpURLConnection = httpURLConnection2;
                th = th3;
                switch (a$2.a[this.a.ordinal()]) {
                    case 1:
                        if (this.b != null) {
                            this.b.b(this.e);
                        }
                        c("下载完成");
                        this.h.a();
                        b();
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                    case 3:
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
            try {
                int contentLength = httpURLConnection2.getContentLength();
                File file = new File(this.g);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream3 = new FileOutputStream(new File(file, this.f));
                try {
                    int i = (contentLength / 1024) / 1024;
                    if (i < 5) {
                        i = 2048;
                    } else if (i < 15) {
                        i = 6144;
                    } else if (i < 35) {
                        i = 14336;
                    } else if (i < 55) {
                        i = 22528;
                    } else if (i < 75) {
                        i = 30720;
                    } else {
                        i = 40960;
                    }
                    byte[] bArr = new byte[i];
                    i = 0;
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        } else if (this.c) {
                            break;
                        } else {
                            j += (long) read;
                            fileOutputStream3.write(bArr, 0, read);
                            fileOutputStream3.flush();
                            if (i > 150 || i == 0) {
                                ADLog.d("正在下载:" + this.f + "   currentSize:" + j);
                                a((int) ((100 * j) / ((long) contentLength)));
                                i = 0;
                            }
                            i++;
                        }
                    }
                    this.a = a$b.c;
                    if (this.c) {
                        this.a = a$b.c;
                    } else {
                        this.a = a$b.b;
                    }
                    switch (a$2.a[this.a.ordinal()]) {
                        case 1:
                            if (this.b != null) {
                                this.b.b(this.e);
                            }
                            c("下载完成");
                            this.h.a();
                            b();
                            this.d.remove(this.e);
                            this.d.remove(this.f);
                            break;
                        case 3:
                            this.d.remove(this.e);
                            this.d.remove(this.f);
                            break;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                            return;
                        }
                    }
                    if (fileOutputStream3 != null) {
                        fileOutputStream3.close();
                    }
                } catch (Exception e32) {
                    fileOutputStream = fileOutputStream3;
                    httpURLConnection = httpURLConnection2;
                    exception = e32;
                    inputStream2 = inputStream;
                } catch (Throwable th32) {
                    fileOutputStream = fileOutputStream3;
                    httpURLConnection = httpURLConnection2;
                    th = th32;
                }
            } catch (Exception e322) {
                httpURLConnection = httpURLConnection2;
                exception = e322;
                inputStream2 = inputStream;
                exception.printStackTrace();
                this.a = a$b.d;
                c("下载失败");
                switch (a$2.a[this.a.ordinal()]) {
                    case 1:
                        if (this.b != null) {
                            this.b.b(this.e);
                        }
                        c("下载完成");
                        this.h.a();
                        b();
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                    case 3:
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (fileOutputStream == null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th322) {
                httpURLConnection = httpURLConnection2;
                th = th322;
                switch (a$2.a[this.a.ordinal()]) {
                    case 1:
                        if (this.b != null) {
                            this.b.b(this.e);
                        }
                        c("下载完成");
                        this.h.a();
                        b();
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                    case 3:
                        this.d.remove(this.e);
                        this.d.remove(this.f);
                        break;
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception = e5;
            obj = fileOutputStream;
            Object obj3 = fileOutputStream;
            exception.printStackTrace();
            this.a = a$b.d;
            c("下载失败");
            switch (a$2.a[this.a.ordinal()]) {
                case 1:
                    if (this.b != null) {
                        this.b.b(this.e);
                    }
                    c("下载完成");
                    this.h.a();
                    b();
                    this.d.remove(this.e);
                    this.d.remove(this.f);
                    break;
                case 3:
                    this.d.remove(this.e);
                    this.d.remove(this.f);
                    break;
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (fileOutputStream == null) {
                fileOutputStream.close();
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = fileOutputStream;
            httpURLConnection = fileOutputStream;
            switch (a$2.a[this.a.ordinal()]) {
                case 1:
                    if (this.b != null) {
                        this.b.b(this.e);
                    }
                    c("下载完成");
                    this.h.a();
                    b();
                    this.d.remove(this.e);
                    this.d.remove(this.f);
                    break;
                case 3:
                    this.d.remove(this.e);
                    this.d.remove(this.f);
                    break;
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private void b() {
        b.a();
        b.b.put(d.a(c.a().b(), this.g + "/" + this.f), this);
        c.a().a(this.f, this.g);
    }

    private void a(int i) {
        this.h.a(this.f, i);
    }

    private void c(String str) {
        this.h.a(this.f, str);
    }
}
