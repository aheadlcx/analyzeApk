package cn.v6.sixrooms.base;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import org.apache.http.entity.mime.MIME;

final class g implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ VLResHandler b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ byte[] e;
    final /* synthetic */ VLHttpClient f;

    g(VLHttpClient vLHttpClient, a aVar, VLResHandler vLResHandler, String str, String str2, byte[] bArr) {
        this.f = vLHttpClient;
        this.a = aVar;
        this.b = vLResHandler;
        this.c = str;
        this.d = str2;
        this.e = bArr;
    }

    public final void run() {
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        Throwable th;
        try {
            if (this.a.c) {
                a(1, "user canceled", null, null, null, this.b);
                this.f.b.remove(this.a.a);
                return;
            }
            this.a.b = 1;
            httpURLConnection = (HttpURLConnection) new URL(this.c).openConnection();
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setRequestProperty("User-Agent", this.f.c);
                httpURLConnection.setRequestProperty("Accept", this.f.d);
                httpURLConnection.setRequestProperty("Accept-Encoding", this.f.e);
                httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, this.d);
                if (this.a.c) {
                    a(1, "user canceled", null, null, null, this.b);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.f.b.remove(this.a.a);
                    return;
                }
                outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(this.e);
                    outputStream.close();
                    this.a.b = 2;
                    int responseCode = httpURLConnection.getResponseCode();
                    String toLowerCase = SixRoomsUtils.V(httpURLConnection.getContentEncoding()).trim().toLowerCase(Locale.getDefault());
                    if (responseCode != 200) {
                        a(4, "server error : " + responseCode, null, null, null, this.b);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        this.f.b.remove(this.a.a);
                    } else if (this.a.c) {
                        a(1, "user canceled", null, null, null, this.b);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        this.f.b.remove(this.a.a);
                    } else {
                        this.a.b = 3;
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            if (toLowerCase.equals("gzip")) {
                                inputStream = new GZIPInputStream(inputStream);
                            }
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            try {
                                byte[] bArr = new byte[4096];
                                while (!this.a.c) {
                                    int read = inputStream.read(bArr);
                                    if (read != -1) {
                                        byteArrayOutputStream.write(bArr, 0, read);
                                    }
                                }
                                if (this.a.c) {
                                    a(1, "user canceled", byteArrayOutputStream, inputStream, null, this.b);
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    this.f.b.remove(this.a.a);
                                    return;
                                }
                                a(0, "succeed", byteArrayOutputStream, inputStream, null, this.b);
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                this.f.b.remove(this.a.a);
                            } catch (Exception e2) {
                                e = e2;
                                outputStream = null;
                                try {
                                    a(2, e.getMessage(), byteArrayOutputStream, inputStream, outputStream, this.b);
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    this.f.b.remove(this.a.a);
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    this.f.b.remove(this.a.a);
                                    throw th;
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            outputStream = null;
                            byteArrayOutputStream = null;
                            a(2, e.getMessage(), byteArrayOutputStream, inputStream, outputStream, this.b);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            this.f.b.remove(this.a.a);
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    inputStream = null;
                    byteArrayOutputStream = null;
                    a(2, e.getMessage(), byteArrayOutputStream, inputStream, outputStream, this.b);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.f.b.remove(this.a.a);
                }
            } catch (Exception e5) {
                e = e5;
                outputStream = null;
                inputStream = null;
                byteArrayOutputStream = null;
                a(2, e.getMessage(), byteArrayOutputStream, inputStream, outputStream, this.b);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                this.f.b.remove(this.a.a);
            }
        } catch (Exception e6) {
            e = e6;
            outputStream = null;
            inputStream = null;
            byteArrayOutputStream = null;
            httpURLConnection = null;
            a(2, e.getMessage(), byteArrayOutputStream, inputStream, outputStream, this.b);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.f.b.remove(this.a.a);
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.f.b.remove(this.a.a);
            throw th;
        }
    }

    private void a(int i, String str, ByteArrayOutputStream byteArrayOutputStream, InputStream inputStream, OutputStream outputStream, VLResHandler vLResHandler) {
        if (byteArrayOutputStream != null) {
            try {
                byteArrayOutputStream.close();
            } catch (Exception e) {
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        this.a.b = 4;
        if (i == 0) {
            vLResHandler.setParam(byteArrayOutputStream.toByteArray());
            vLResHandler.handlerSuccess();
        } else if (i == 1) {
            if (vLResHandler != null) {
                vLResHandler.handlerError(-1, "user canceled");
            }
            if (this.a.d != null) {
                for (VLResHandler vLResHandler2 : this.a.d) {
                    if (vLResHandler2 != null) {
                        vLResHandler2.handlerSuccess();
                    }
                }
            }
        } else if (vLResHandler != null) {
            vLResHandler.handlerError(-3, str);
        }
    }
}
