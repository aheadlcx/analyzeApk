package com.elves.update;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;
import com.budejie.www.http.i;
import com.budejie.www.util.BudejieDownloadHelper;
import com.budejie.www.util.BudejieDownloadHelper.DownloadStatus;
import java.util.ArrayList;

public class DownloadServer extends Service {
    String a;
    String b;
    String c;
    int d;
    ArrayList<String> e = new ArrayList();
    Handler f = new Handler(this) {
        final /* synthetic */ DownloadServer a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                Toast.makeText(this.a, "已经在下载队列中", 1).show();
            } else if (i == 3) {
                r0 = (Bundle) message.obj;
                r1 = r0.getInt("notifyId");
                r0 = r0.getString("fileName");
                d.a(this.a, r0);
                r2 = new Intent("com.budejie.download.successed");
                r2.putExtra("fileName", r0);
                r2.putExtra("notifyId", r1);
                this.a.sendBroadcast(r2);
                BudejieDownloadHelper.a = DownloadStatus.successed;
                i.d(this.a.getApplicationContext(), this.a.d);
                i.e(this.a.getApplicationContext(), this.a.d);
            } else if (i == 4) {
                r0 = (Bundle) message.obj;
                r1 = r0.getInt("notifyId");
                r0 = r0.getString("fileName");
                r2 = new Intent("com.budejie.download.failed");
                r2.putExtra("fileName", r0);
                r2.putExtra("notifyId", r1);
                this.a.sendBroadcast(r2);
                BudejieDownloadHelper.a = DownloadStatus.failed;
            }
        }
    };

    class a extends Thread {
        String a;
        String b;
        String c;
        int d;
        a e;
        final /* synthetic */ DownloadServer f;

        public a(DownloadServer downloadServer, String str, String str2, String str3, int i) {
            this.f = downloadServer;
            if (str.endsWith(".apk")) {
                this.a = str;
            } else {
                this.a = System.currentTimeMillis() + str + ".apk";
            }
            this.b = str2;
            this.c = str3;
            this.d = i;
            this.e = new a(downloadServer);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r13 = this;
            r1 = r13.a;
            r1 = android.text.TextUtils.isEmpty(r1);
            if (r1 == 0) goto L_0x000c;
        L_0x0008:
            r1 = "temp.apk";
            r13.a = r1;
        L_0x000c:
            r1 = r13.a;
            r2 = ".apk";
            r1 = r1.endsWith(r2);
            if (r1 != 0) goto L_0x002d;
        L_0x0016:
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = r13.a;
            r1 = r1.append(r2);
            r2 = ".apk";
            r1 = r1.append(r2);
            r1 = r1.toString();
            r13.a = r1;
        L_0x002d:
            r1 = new java.io.File;
            r2 = r13.b;
            r1.<init>(r2);
            r2 = r1.exists();
            if (r2 != 0) goto L_0x003d;
        L_0x003a:
            r1.mkdirs();
        L_0x003d:
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = r13.b;
            r1 = r1.append(r2);
            r2 = "/";
            r1 = r1.append(r2);
            r2 = r13.a;
            r1 = r1.append(r2);
            r11 = r1.toString();
            r7 = 0;
            r10 = 0;
            r8 = 0;
            r9 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r1 = r13.c;	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r9.<init>(r1);	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r1 = r13.e;	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r2 = r13.d;	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r3 = 100;
            r4 = 1;
            r5 = r13.a;	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r6 = 0;
            r1.a(r2, r3, r4, r5, r6);	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r1 = r9.openConnection();	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r0 = r1;
            r0 = (java.net.HttpURLConnection) r0;	 Catch:{ MalformedURLException -> 0x012c, IOException -> 0x017e, all -> 0x01cf }
            r7 = r0;
            r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
            r7.setConnectTimeout(r1);	 Catch:{ MalformedURLException -> 0x022f, IOException -> 0x017e }
            r7.connect();	 Catch:{ MalformedURLException -> 0x022f, IOException -> 0x017e }
            r8 = r7.getInputStream();	 Catch:{ MalformedURLException -> 0x022f, IOException -> 0x017e }
            r3 = r7.getContentLength();	 Catch:{ MalformedURLException -> 0x0235, IOException -> 0x017e }
            r4 = 0;
            r9 = new java.io.FileOutputStream;	 Catch:{ MalformedURLException -> 0x0235, IOException -> 0x017e }
            r1 = new java.io.File;	 Catch:{ MalformedURLException -> 0x0235, IOException -> 0x017e }
            r2 = r13.b;	 Catch:{ MalformedURLException -> 0x0235, IOException -> 0x017e }
            r5 = r13.a;	 Catch:{ MalformedURLException -> 0x0235, IOException -> 0x017e }
            r1.<init>(r2, r5);	 Catch:{ MalformedURLException -> 0x0235, IOException -> 0x017e }
            r9.<init>(r1);	 Catch:{ MalformedURLException -> 0x0235, IOException -> 0x017e }
            r1 = r3 / 1024;
            r1 = r1 / 1024;
            r2 = 5;
            if (r1 >= r2) goto L_0x00c5;
        L_0x009d:
            r1 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        L_0x009f:
            r10 = new byte[r1];	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r1 = 0;
        L_0x00a2:
            r2 = r8.read(r10);	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r5 = -1;
            if (r2 == r5) goto L_0x00e5;
        L_0x00a9:
            r4 = r4 + r2;
            r5 = 0;
            r9.write(r10, r5, r2);	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r9.flush();	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r2 = 100;
            if (r1 > r2) goto L_0x00b7;
        L_0x00b5:
            if (r1 != 0) goto L_0x00c2;
        L_0x00b7:
            r1 = r13.e;	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r2 = r13.d;	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r5 = r13.a;	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r6 = 0;
            r1.a(r2, r3, r4, r5, r6);	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r1 = 0;
        L_0x00c2:
            r1 = r1 + 1;
            goto L_0x00a2;
        L_0x00c5:
            r2 = 15;
            if (r1 >= r2) goto L_0x00cc;
        L_0x00c9:
            r1 = 6144; // 0x1800 float:8.61E-42 double:3.0355E-320;
            goto L_0x009f;
        L_0x00cc:
            r2 = 35;
            if (r1 >= r2) goto L_0x00d3;
        L_0x00d0:
            r1 = 14336; // 0x3800 float:2.0089E-41 double:7.083E-320;
            goto L_0x009f;
        L_0x00d3:
            r2 = 55;
            if (r1 >= r2) goto L_0x00da;
        L_0x00d7:
            r1 = 22528; // 0x5800 float:3.1568E-41 double:1.11303E-319;
            goto L_0x009f;
        L_0x00da:
            r2 = 75;
            if (r1 >= r2) goto L_0x00e1;
        L_0x00de:
            r1 = 30720; // 0x7800 float:4.3048E-41 double:1.51777E-319;
            goto L_0x009f;
        L_0x00e1:
            r1 = 40960; // 0xa000 float:5.7397E-41 double:2.0237E-319;
            goto L_0x009f;
        L_0x00e5:
            r1 = r13.e;	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r2 = r13.d;	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r5 = r13.a;	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r6 = 0;
            r1.a(r2, r3, r4, r5, r6);	 Catch:{ MalformedURLException -> 0x023b, IOException -> 0x022b, all -> 0x0221 }
            r1 = new android.os.Bundle;
            r1.<init>();
            r2 = "notifyId";
            r3 = r13.d;
            r1.putInt(r2, r3);
            r2 = "fileName";
            r1.putString(r2, r11);
            r2 = r13.f;
            r2 = r2.f;
            r3 = 3;
            r1 = r2.obtainMessage(r3, r1);
            r2 = r13.f;
            r2 = r2.f;
            r2.sendMessage(r1);
            r1 = r13.f;
            r1 = r1.e;
            r2 = r13.c;
            r1.remove(r2);
            r7.disconnect();
            if (r8 == 0) goto L_0x0121;
        L_0x011e:
            r8.close();	 Catch:{ IOException -> 0x0127 }
        L_0x0121:
            if (r9 == 0) goto L_0x0126;
        L_0x0123:
            r9.close();	 Catch:{ IOException -> 0x0127 }
        L_0x0126:
            return;
        L_0x0127:
            r1 = move-exception;
            r1.printStackTrace();
            goto L_0x0126;
        L_0x012c:
            r1 = move-exception;
            r9 = r7;
            r7 = r8;
            r8 = r10;
        L_0x0130:
            r1.printStackTrace();	 Catch:{ all -> 0x0226 }
            r1 = r13.e;
            r2 = r13.d;
            r3 = 2130840775; // 0x7f020cc7 float:1.7286598E38 double:1.0527752237E-314;
            r5 = r13.c;
            r6 = 0;
            r4 = r11;
            r1.a(r2, r3, r4, r5, r6);
            r1 = new android.os.Bundle;
            r1.<init>();
            r2 = "notifyId";
            r3 = r13.d;
            r1.putInt(r2, r3);
            r2 = "fileName";
            r1.putString(r2, r11);
            r2 = r13.f;
            r2 = r2.f;
            r3 = 4;
            r1 = r2.obtainMessage(r3, r1);
            r2 = r13.f;
            r2 = r2.f;
            r2.sendMessage(r1);
            r1 = r13.f;
            r1 = r1.e;
            r2 = r13.c;
            r1.remove(r2);
            r9.disconnect();
            if (r7 == 0) goto L_0x0173;
        L_0x0170:
            r7.close();	 Catch:{ IOException -> 0x0179 }
        L_0x0173:
            if (r8 == 0) goto L_0x0126;
        L_0x0175:
            r8.close();	 Catch:{ IOException -> 0x0179 }
            goto L_0x0126;
        L_0x0179:
            r1 = move-exception;
            r1.printStackTrace();
            goto L_0x0126;
        L_0x017e:
            r1 = move-exception;
        L_0x017f:
            r1.printStackTrace();	 Catch:{ all -> 0x021d }
            r1 = r13.e;
            r2 = r13.d;
            r3 = 2130840775; // 0x7f020cc7 float:1.7286598E38 double:1.0527752237E-314;
            r5 = r13.c;
            r6 = 0;
            r4 = r11;
            r1.a(r2, r3, r4, r5, r6);
            r1 = new android.os.Bundle;
            r1.<init>();
            r2 = "notifyId";
            r3 = r13.d;
            r1.putInt(r2, r3);
            r2 = "fileName";
            r1.putString(r2, r11);
            r2 = r13.f;
            r2 = r2.f;
            r3 = 4;
            r1 = r2.obtainMessage(r3, r1);
            r2 = r13.f;
            r2 = r2.f;
            r2.sendMessage(r1);
            r1 = r13.f;
            r1 = r1.e;
            r2 = r13.c;
            r1.remove(r2);
            r7.disconnect();
            if (r8 == 0) goto L_0x01c2;
        L_0x01bf:
            r8.close();	 Catch:{ IOException -> 0x01c9 }
        L_0x01c2:
            if (r10 == 0) goto L_0x0126;
        L_0x01c4:
            r10.close();	 Catch:{ IOException -> 0x01c9 }
            goto L_0x0126;
        L_0x01c9:
            r1 = move-exception;
            r1.printStackTrace();
            goto L_0x0126;
        L_0x01cf:
            r1 = move-exception;
            r9 = r7;
            r7 = r1;
        L_0x01d2:
            r1 = r13.e;
            r2 = r13.d;
            r3 = 2130840775; // 0x7f020cc7 float:1.7286598E38 double:1.0527752237E-314;
            r5 = r13.c;
            r6 = 0;
            r4 = r11;
            r1.a(r2, r3, r4, r5, r6);
            r1 = new android.os.Bundle;
            r1.<init>();
            r2 = "notifyId";
            r3 = r13.d;
            r1.putInt(r2, r3);
            r2 = "fileName";
            r1.putString(r2, r11);
            r2 = r13.f;
            r2 = r2.f;
            r3 = 4;
            r1 = r2.obtainMessage(r3, r1);
            r2 = r13.f;
            r2 = r2.f;
            r2.sendMessage(r1);
            r1 = r13.f;
            r1 = r1.e;
            r2 = r13.c;
            r1.remove(r2);
            r9.disconnect();
            if (r8 == 0) goto L_0x0212;
        L_0x020f:
            r8.close();	 Catch:{ IOException -> 0x0218 }
        L_0x0212:
            if (r10 == 0) goto L_0x0217;
        L_0x0214:
            r10.close();	 Catch:{ IOException -> 0x0218 }
        L_0x0217:
            throw r7;
        L_0x0218:
            r1 = move-exception;
            r1.printStackTrace();
            goto L_0x0217;
        L_0x021d:
            r1 = move-exception;
            r9 = r7;
            r7 = r1;
            goto L_0x01d2;
        L_0x0221:
            r1 = move-exception;
            r10 = r9;
            r9 = r7;
            r7 = r1;
            goto L_0x01d2;
        L_0x0226:
            r1 = move-exception;
            r10 = r8;
            r8 = r7;
            r7 = r1;
            goto L_0x01d2;
        L_0x022b:
            r1 = move-exception;
            r10 = r9;
            goto L_0x017f;
        L_0x022f:
            r1 = move-exception;
            r9 = r7;
            r7 = r8;
            r8 = r10;
            goto L_0x0130;
        L_0x0235:
            r1 = move-exception;
            r9 = r7;
            r7 = r8;
            r8 = r10;
            goto L_0x0130;
        L_0x023b:
            r1 = move-exception;
            r12 = r8;
            r8 = r9;
            r9 = r7;
            r7 = r12;
            goto L_0x0130;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.elves.update.DownloadServer.a.run():void");
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (intent != null) {
            this.a = intent.getStringExtra("apkName");
            this.b = intent.getStringExtra("apkPath");
            this.c = intent.getStringExtra("url");
            this.d = intent.getIntExtra("adId", 0);
            if (this.e.contains(this.c)) {
                this.f.sendEmptyMessage(2);
                return;
            }
            this.e.add(this.c);
            a();
        }
    }

    public void a() {
        int currentTimeMillis = (int) System.currentTimeMillis();
        BudejieDownloadHelper.a = DownloadStatus.loading;
        new a(this, this.a, this.b, this.c, currentTimeMillis).start();
        i.c(getApplicationContext(), this.d);
    }
}
