package com.xiaomi.push.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ag {

    public static class a {
        byte[] a;
        int b;

        public a(byte[] bArr, int i) {
            this.a = bArr;
            this.b = i;
        }
    }

    public static class b {
        public Bitmap a;
        public long b;

        public b(Bitmap bitmap, long j) {
            this.a = bitmap;
            this.b = j;
        }
    }

    private static int a(Context context, InputStream inputStream) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            com.xiaomi.channel.commonutils.logger.b.a("decode dimension failed for bitmap.");
            return 1;
        }
        int round = Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f) * 48.0f);
        return (options.outWidth <= round || options.outHeight <= round) ? 1 : Math.min(options.outWidth / round, options.outHeight / round);
    }

    private static a a(String str) {
        InputStream inputStream;
        Throwable th;
        HttpURLConnection httpURLConnection;
        Throwable th2;
        int i = 102400;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(8000);
                httpURLConnection3.setReadTimeout(20000);
                httpURLConnection3.connect();
                int contentLength = httpURLConnection3.getContentLength();
                if (contentLength > 102400) {
                    com.xiaomi.channel.commonutils.logger.b.a("Bitmap size is too big, max size is 102400  contentLen size is " + contentLength + " from url " + str);
                    com.xiaomi.channel.commonutils.file.a.a(null);
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                contentLength = httpURLConnection3.getResponseCode();
                if (contentLength != 200) {
                    com.xiaomi.channel.commonutils.logger.b.a("Invalid Http Response Code " + contentLength + " received");
                    com.xiaomi.channel.commonutils.file.a.a(null);
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                inputStream = httpURLConnection3.getInputStream();
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (i > 0) {
                        int read = inputStream.read(bArr, 0, 1024);
                        if (read == -1) {
                            break;
                        }
                        i -= read;
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    a aVar;
                    if (i <= 0) {
                        com.xiaomi.channel.commonutils.logger.b.a("length 102400 exhausted.");
                        aVar = new a(null, 102400);
                        com.xiaomi.channel.commonutils.file.a.a(inputStream);
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return aVar;
                    }
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    aVar = new a(toByteArray, toByteArray.length);
                    com.xiaomi.channel.commonutils.file.a.a(inputStream);
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return aVar;
                } catch (Throwable e) {
                    th = e;
                    httpURLConnection = httpURLConnection3;
                    th2 = th;
                } catch (Throwable th3) {
                    th = th3;
                    httpURLConnection2 = httpURLConnection3;
                    th2 = th;
                }
            } catch (Throwable e2) {
                inputStream = null;
                HttpURLConnection httpURLConnection4 = httpURLConnection3;
                th2 = e2;
                httpURLConnection = httpURLConnection4;
                try {
                    com.xiaomi.channel.commonutils.logger.b.a(th2);
                    com.xiaomi.channel.commonutils.file.a.a(inputStream);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable th4) {
                    th2 = th4;
                    httpURLConnection2 = httpURLConnection;
                    com.xiaomi.channel.commonutils.file.a.a(inputStream);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th2;
                }
            } catch (Throwable e22) {
                inputStream = null;
                httpURLConnection2 = httpURLConnection3;
                th2 = e22;
                com.xiaomi.channel.commonutils.file.a.a(inputStream);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th2;
            }
        } catch (IOException e3) {
            th2 = e3;
            httpURLConnection = null;
            inputStream = null;
            com.xiaomi.channel.commonutils.logger.b.a(th2);
            com.xiaomi.channel.commonutils.file.a.a(inputStream);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th5) {
            th2 = th5;
            inputStream = null;
            com.xiaomi.channel.commonutils.file.a.a(inputStream);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th2;
        }
    }

    public static b a(Context context, String str) {
        InputStream byteArrayInputStream;
        Throwable e;
        Throwable th;
        b bVar = new b(null, 0);
        try {
            a a = a(str);
            if (a == null) {
                com.xiaomi.channel.commonutils.file.a.a(null);
                return bVar;
            }
            bVar.b = (long) a.b;
            byte[] bArr = a.a;
            if (bArr != null) {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    int a2 = a(context, byteArrayInputStream);
                    Options options = new Options();
                    options.inSampleSize = a2;
                    bVar.a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.xiaomi.channel.commonutils.logger.b.a(e);
                        com.xiaomi.channel.commonutils.file.a.a(byteArrayInputStream);
                        return bVar;
                    } catch (Throwable th2) {
                        th = th2;
                        com.xiaomi.channel.commonutils.file.a.a(byteArrayInputStream);
                        throw th;
                    }
                }
            }
            byteArrayInputStream = null;
            com.xiaomi.channel.commonutils.file.a.a(byteArrayInputStream);
            return bVar;
        } catch (Exception e3) {
            e = e3;
            byteArrayInputStream = null;
            com.xiaomi.channel.commonutils.logger.b.a(e);
            com.xiaomi.channel.commonutils.file.a.a(byteArrayInputStream);
            return bVar;
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream = null;
            com.xiaomi.channel.commonutils.file.a.a(byteArrayInputStream);
            throw th;
        }
    }

    public static Bitmap b(Context context, String str) {
        InputStream openInputStream;
        InputStream openInputStream2;
        Throwable e;
        Object obj;
        Throwable th;
        Bitmap bitmap = null;
        Uri parse = Uri.parse(str);
        try {
            int a;
            openInputStream = context.getContentResolver().openInputStream(parse);
            try {
                a = a(context, openInputStream);
                openInputStream2 = context.getContentResolver().openInputStream(parse);
            } catch (IOException e2) {
                e = e2;
                obj = bitmap;
                try {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    com.xiaomi.channel.commonutils.file.a.a(openInputStream2);
                    com.xiaomi.channel.commonutils.file.a.a(openInputStream);
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    com.xiaomi.channel.commonutils.file.a.a(openInputStream2);
                    com.xiaomi.channel.commonutils.file.a.a(openInputStream);
                    throw th;
                }
            } catch (Throwable e3) {
                obj = bitmap;
                th = e3;
                com.xiaomi.channel.commonutils.file.a.a(openInputStream2);
                com.xiaomi.channel.commonutils.file.a.a(openInputStream);
                throw th;
            }
            try {
                Options options = new Options();
                options.inSampleSize = a;
                bitmap = BitmapFactory.decodeStream(openInputStream2, null, options);
                com.xiaomi.channel.commonutils.file.a.a(openInputStream2);
            } catch (IOException e4) {
                e3 = e4;
                com.xiaomi.channel.commonutils.logger.b.a(e3);
                com.xiaomi.channel.commonutils.file.a.a(openInputStream2);
                com.xiaomi.channel.commonutils.file.a.a(openInputStream);
                return bitmap;
            }
        } catch (IOException e5) {
            e3 = e5;
            openInputStream = bitmap;
            openInputStream2 = bitmap;
            com.xiaomi.channel.commonutils.logger.b.a(e3);
            com.xiaomi.channel.commonutils.file.a.a(openInputStream2);
            com.xiaomi.channel.commonutils.file.a.a(openInputStream);
            return bitmap;
        } catch (Throwable e32) {
            openInputStream = bitmap;
            openInputStream2 = bitmap;
            th = e32;
            com.xiaomi.channel.commonutils.file.a.a(openInputStream2);
            com.xiaomi.channel.commonutils.file.a.a(openInputStream);
            throw th;
        }
        com.xiaomi.channel.commonutils.file.a.a(openInputStream);
        return bitmap;
    }
}
