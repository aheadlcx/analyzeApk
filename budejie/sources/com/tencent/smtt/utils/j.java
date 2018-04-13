package com.tencent.smtt.utils;

import com.tencent.smtt.utils.e.a;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final class j extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    j(String str, a aVar) {
        this.a = str;
        this.b = aVar;
    }

    public void run() {
        InputStream inputStream;
        Exception e;
        Throwable e2;
        OutputStream outputStream = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://soft.tbs.imtt.qq.com/17421/tbs_res_imtt_tbs_DebugPlugin_DebugPlugin.tbs").openConnection();
            int contentLength = httpURLConnection.getContentLength();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            try {
                outputStream = k.d(new File(this.a));
                byte[] bArr = new byte[8192];
                int i = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    i += read;
                    outputStream.write(bArr, 0, read);
                    this.b.a((i * 100) / contentLength);
                }
                this.b.a();
                try {
                    inputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } catch (Exception e4) {
                e2 = e4;
                try {
                    e2.printStackTrace();
                    this.b.a(e2);
                    try {
                        inputStream.close();
                    } catch (Exception e32) {
                        e32.printStackTrace();
                    }
                    try {
                        outputStream.close();
                    } catch (Exception e5) {
                        e32 = e5;
                    }
                } catch (Throwable th) {
                    e2 = th;
                    try {
                        inputStream.close();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                    try {
                        outputStream.close();
                    } catch (Exception e7) {
                        e7.printStackTrace();
                    }
                    throw e2;
                }
            }
            try {
                outputStream.close();
            } catch (Exception e8) {
                e32 = e8;
                e32.printStackTrace();
            }
        } catch (Exception e9) {
            e2 = e9;
            inputStream = null;
            e2.printStackTrace();
            this.b.a(e2);
            inputStream.close();
            outputStream.close();
        } catch (Throwable th2) {
            e2 = th2;
            inputStream = null;
            inputStream.close();
            outputStream.close();
            throw e2;
        }
    }
}
