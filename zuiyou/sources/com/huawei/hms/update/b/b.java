package com.huawei.hms.update.b;

import com.huawei.hms.c.c;
import com.huawei.hms.support.log.a;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.connect.common.Constants;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class b implements d {
    private HttpURLConnection a;
    private volatile int b = -1;

    public void a() {
        this.b = -1;
        if (this.a != null) {
            this.a.disconnect();
        }
    }

    public void b() {
        this.b = 1;
    }

    public int a(String str, InputStream inputStream, OutputStream outputStream) throws IOException, a {
        Throwable th;
        OutputStream outputStream2;
        InputStream inputStream2;
        Throwable th2;
        InputStream inputStream3 = null;
        try {
            a(str);
            this.a.setRequestMethod(Constants.HTTP_POST);
            OutputStream outputStream3 = this.a.getOutputStream();
            try {
                a(inputStream, outputStream3);
                outputStream3.flush();
                int responseCode = this.a.getResponseCode();
                if (responseCode == 200) {
                    inputStream3 = this.a.getInputStream();
                    try {
                        a(new BufferedInputStream(inputStream3, 4096), outputStream);
                        outputStream.flush();
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream2 = outputStream3;
                        inputStream2 = inputStream3;
                        th2 = th;
                        c.a(inputStream2);
                        c.a(outputStream2);
                        throw th2;
                    }
                }
                c.a(inputStream3);
                c.a(outputStream3);
                return responseCode;
            } catch (Throwable th32) {
                th = th32;
                outputStream2 = outputStream3;
                inputStream2 = null;
                th2 = th;
                c.a(inputStream2);
                c.a(outputStream2);
                throw th2;
            }
        } catch (Throwable th322) {
            inputStream2 = null;
            th2 = th322;
            outputStream2 = null;
            c.a(inputStream2);
            c.a(outputStream2);
            throw th2;
        }
    }

    public int a(String str, OutputStream outputStream) throws IOException, a {
        return a(str, outputStream, 0, 0);
    }

    public int a(String str, OutputStream outputStream, int i, int i2) throws IOException, a {
        Throwable th;
        InputStream inputStream;
        Throwable th2;
        InputStream inputStream2 = null;
        try {
            a(str);
            this.a.setRequestMethod(Constants.HTTP_GET);
            if (i > 0) {
                this.a.addRequestProperty("Range", "bytes=" + i + "-" + i2);
            }
            int responseCode = this.a.getResponseCode();
            if ((i > 0 && responseCode == 206) || (i <= 0 && responseCode == 200)) {
                inputStream2 = this.a.getInputStream();
                try {
                    a(new BufferedInputStream(inputStream2, 4096), outputStream);
                    outputStream.flush();
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = inputStream2;
                    th2 = th;
                    c.a(inputStream);
                    throw th2;
                }
            }
            c.a(inputStream2);
            return responseCode;
        } catch (Throwable th32) {
            th = th32;
            inputStream = null;
            th2 = th;
            c.a(inputStream);
            throw th2;
        }
    }

    private void a(String str) throws IOException {
        if (this.b == 0) {
            a.d("HttpRequestHelper", "Not allowed to repeat open http(s) connection.");
        }
        this.a = (HttpURLConnection) new URL(str).openConnection();
        if (this.a instanceof HttpsURLConnection) {
            c.a((HttpsURLConnection) this.a);
        }
        this.a.setConnectTimeout(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH);
        this.a.setReadTimeout(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH);
        this.a.setDoInput(true);
        this.a.setDoOutput(true);
        this.a.setUseCaches(false);
        this.b = 0;
    }

    private void a(InputStream inputStream, OutputStream outputStream) throws IOException, a {
        byte[] bArr = new byte[4096];
        do {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        } while (this.b != 1);
        throw new a("HTTP(s) request was canceled.");
    }
}
