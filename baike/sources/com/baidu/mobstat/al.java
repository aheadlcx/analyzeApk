package com.baidu.mobstat;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

public class al {
    private static String a = (VERSION.SDK_INT < 9 ? "http://openrcv.baidu.com/1010/bplus.gif" : "https://openrcv.baidu.com/1010/bplus.gif");
    private static al b;
    private Handler c;

    private al() {
        HandlerThread handlerThread = new HandlerThread("LogSender");
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper());
    }

    public static al a() {
        if (b == null) {
            synchronized (al.class) {
                if (b == null) {
                    b = new al();
                }
            }
        }
        return b;
    }

    public void a(Context context, String str) {
        bd.a("data = " + str);
        if (str != null && !"".equals(str)) {
            this.c.post(new am(this, str, context));
        }
    }

    private void a(String str) {
        cu.a("backups/system" + File.separator + "__send_log_data_" + System.currentTimeMillis(), str, false);
    }

    private void a(Context context) {
        if ("mounted".equals(cu.a())) {
            File file = new File(Environment.getExternalStorageDirectory(), "backups/system");
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    try {
                        Arrays.sort(listFiles, new an(this));
                    } catch (Throwable e) {
                        bd.b(e);
                    }
                    int i = 0;
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            String name = file2.getName();
                            if (!TextUtils.isEmpty(name) && name.startsWith("__send_log_data_")) {
                                name = "backups/system" + File.separator + name;
                                String b = cu.b(name);
                                if (b(context, b)) {
                                    cu.c(name);
                                    i = 0;
                                } else {
                                    a(b, name);
                                    i++;
                                    if (i >= 5) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception e) {
                jSONObject = null;
            }
            JSONObject a = v.a(jSONObject);
            if (a != null) {
                v.b(a);
                cu.a(str2, jSONObject.toString(), false);
            }
        }
    }

    private boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            a(context, a, str);
            return true;
        } catch (Throwable e) {
            bd.c(e);
            return false;
        }
    }

    private String a(Context context, String str, String str2) {
        boolean z;
        String stringBuilder;
        if (str.startsWith("https:")) {
            z = false;
        } else {
            z = true;
        }
        HttpURLConnection d = cu.d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty("Content-Encoding", "gzip");
        d.connect();
        try {
            byte[] a;
            OutputStream outputStream = d.getOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
            gZIPOutputStream.write(new byte[]{(byte) 72, (byte) 77, (byte) 48, (byte) 49});
            gZIPOutputStream.write(new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1});
            gZIPOutputStream.write(new byte[]{(byte) 0, (byte) 0, (byte) 3, (byte) -14});
            gZIPOutputStream.write(new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0});
            gZIPOutputStream.write(new byte[]{(byte) 0, (byte) 2});
            if (z) {
                gZIPOutputStream.write(new byte[]{(byte) 0, (byte) 1});
            } else {
                gZIPOutputStream.write(new byte[]{(byte) 0, (byte) 0});
            }
            gZIPOutputStream.write(new byte[]{(byte) 72, (byte) 77, (byte) 48, (byte) 49});
            if (z) {
                a = cs.a();
                byte[] a2 = dc.a(false, cw.a(), a);
                gZIPOutputStream.write(a((long) a2.length, 4));
                gZIPOutputStream.write(a2);
                a = cs.a(a, new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1}, str2.getBytes("utf-8"));
                gZIPOutputStream.write(a((long) a.length, 2));
            } else {
                a = str2.getBytes("utf-8");
            }
            gZIPOutputStream.write(a);
            gZIPOutputStream.close();
            outputStream.close();
            int responseCode = d.getResponseCode();
            int contentLength = d.getContentLength();
            bd.c("code: " + responseCode + "; len: " + contentLength);
            if (responseCode == 200 && contentLength == 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
                StringBuilder stringBuilder2 = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder2.append(readLine);
                }
                stringBuilder = stringBuilder2.toString();
                return stringBuilder;
            }
            throw new IOException("Response code = " + d.getResponseCode());
        } catch (Exception e) {
            stringBuilder = e;
            bd.b((Throwable) stringBuilder);
            return "";
        } finally {
            d.disconnect();
        }
    }

    private static byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[(i - i2) - 1] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        return bArr;
    }
}
