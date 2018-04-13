package com.baidu.mobads.openad.d;

import android.os.Build.VERSION;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.c.c;
import com.baidu.mobads.openad.c.d;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.tencent.bugly.Bugly;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class a extends c {
    public static int a = 1024;
    private static final TimeUnit h = TimeUnit.SECONDS;
    private static int i = 5;
    private static BlockingQueue<Runnable> j = new LinkedBlockingQueue();
    private static ThreadPoolExecutor k;
    private String b;
    private AtomicBoolean d;
    private Boolean e;
    private HttpURLConnection f;
    private AtomicBoolean g;

    class a implements Runnable {
        final /* synthetic */ a a;
        private c b;
        private double c;

        public a(a aVar, c cVar, double d) {
            this.a = aVar;
            this.b = cVar;
            this.c = d;
        }

        public void run() {
            InputStream inputStream = null;
            try {
                String str = "";
                if (this.b.c > 0) {
                    Thread.sleep(this.b.c);
                }
                this.a.d.set(true);
                this.a.f = (HttpURLConnection) new URL(this.b.a).openConnection();
                this.a.f.setConnectTimeout((int) this.c);
                this.a.f.setUseCaches(false);
                if (this.b.b != null && this.b.b.length() > 0) {
                    this.a.f.setRequestProperty("User-Agent", this.b.b);
                }
                this.a.f.setRequestProperty("Content-type", this.b.d);
                this.a.f.setRequestProperty("Connection", "keep-alive");
                this.a.f.setRequestProperty("Cache-Control", HeaderConstants.CACHE_CONTROL_NO_CACHE);
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
                }
                if (this.b.e == 1) {
                    this.a.f.setRequestMethod("GET");
                    this.a.f.connect();
                    this.a.f.getHeaderFields();
                    if (!this.a.e.booleanValue()) {
                        inputStream = this.a.f.getInputStream();
                        this.a.dispatchEvent(new d("URLLoader.Load.Complete", a.b(inputStream), this.b.a()));
                    }
                    this.a.f.getResponseCode();
                } else if (this.b.e == 0) {
                    this.a.f.setRequestMethod("POST");
                    this.a.f.setDoInput(true);
                    this.a.f.setDoOutput(true);
                    if (this.b.b() != null) {
                        str = this.b.b().build().getEncodedQuery();
                        OutputStream outputStream = this.a.f.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        bufferedWriter.write(str);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                    }
                    this.a.f.connect();
                    this.a.f.getResponseCode();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th) {
                        XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdURLLoader", th.getMessage());
                    }
                }
                if (this.a.f != null) {
                    try {
                        this.a.f.disconnect();
                        return;
                    } catch (Throwable th2) {
                        IXAdLogger adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                        Object[] objArr = new Object[]{"OAdURLLoader", th2.getMessage()};
                    }
                } else {
                    return;
                }
            } catch (Throwable th22) {
                adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                objArr = new Object[]{"OAdURLLoader", th22.getMessage()};
                adLogger.e(objArr);
                return;
            }
            if (this.a.f != null) {
                this.a.f.disconnect();
            }
        }
    }

    static {
        try {
            k = new ThreadPoolExecutor(i, i, 1, h, j);
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().e(e);
        }
    }

    public a(String str) {
        this.d = new AtomicBoolean(false);
        this.e = Boolean.valueOf(false);
        this.g = new AtomicBoolean();
        this.b = str;
    }

    public a() {
        this(null);
    }

    public void a(c cVar, Boolean bool) {
        this.e = bool;
        a(cVar, 20000.0d);
    }

    public void a(c cVar) {
        a(cVar, 20000.0d);
    }

    public void a(c cVar, double d) {
        try {
            k.execute(new a(this, cVar, d));
        } catch (Exception e) {
        }
    }

    private static String b(InputStream inputStream) {
        String str = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str2 = "";
        while (true) {
            str2 = bufferedReader.readLine();
            if (str2 == null) {
                return str;
            }
            str = str + str2 + "\n";
        }
    }

    public void a() {
        new Thread(new b(this)).start();
    }

    public void dispose() {
        this.g.set(true);
        a();
        super.dispose();
    }
}
