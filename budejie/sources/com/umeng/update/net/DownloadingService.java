package com.umeng.update.net;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.SparseArray;
import android.widget.Toast;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import com.umeng.update.util.DeltaUpdate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import u.upd.k;
import u.upd.m;

public class DownloadingService extends Service {
    private static final long C = 8000;
    private static final long D = 500;
    private static Map<a, Messenger> E = new HashMap();
    private static SparseArray<b> F = new SparseArray();
    private static Boolean I = Boolean.valueOf(false);
    static final int a = 1;
    static final int b = 2;
    static final int c = 3;
    static final int d = 4;
    static final int e = 5;
    static final int f = 6;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 4;
    public static final int l = 5;
    public static final int m = 6;
    public static final int n = 7;
    static final int o = 100;
    static final String p = "filename";
    public static boolean r = false;
    private static final String t = DownloadingService.class.getName();
    private static final long w = 104857600;
    private static final long x = 10485760;
    private static final long y = 259200000;
    private static final int z = 3;
    private Context A;
    private Handler B;
    private e G;
    private boolean H = true;
    a q;
    final Messenger s = new Messenger(new c(this));
    private NotificationManager u;
    private c v;

    interface a {
        void a(int i);

        void a(int i, int i2);

        void a(int i, Exception exception);

        void a(int i, String str);
    }

    class b extends Thread {
        final /* synthetic */ DownloadingService a;
        private Context b;
        private boolean c;
        private File d;
        private int e = 0;
        private long f = -1;
        private long g = -1;
        private int h = -1;
        private int i;
        private a j;
        private a k;

        public b(DownloadingService downloadingService, Context context, a aVar, int i, int i2, a aVar2) {
            this.a = downloadingService;
            try {
                long j;
                this.b = context;
                this.k = aVar;
                this.e = i2;
                if (DownloadingService.F.indexOfKey(i) >= 0) {
                    long[] jArr = ((b) DownloadingService.F.get(i)).f;
                    if (jArr != null && jArr.length > 1) {
                        this.f = jArr[0];
                        this.g = jArr[1];
                    }
                }
                this.j = aVar2;
                this.i = i;
                boolean[] zArr = new boolean[1];
                this.d = j.a("/apk", context, zArr);
                this.c = zArr[0];
                if (this.c) {
                    j = DownloadingService.w;
                } else {
                    j = DownloadingService.x;
                }
                j.a(this.d, j, DownloadingService.y);
                this.d = new File(this.d, a(this.k));
            } catch (Exception e) {
                u.upd.b.c(DownloadingService.t, e.getMessage(), e);
                this.j.a(this.i, e);
            }
        }

        public void run() {
            boolean z = false;
            this.e = 0;
            try {
                if (this.j != null) {
                    this.j.a(this.i);
                }
                if (this.f > 0) {
                    z = true;
                }
                a(z);
                if (DownloadingService.E.size() <= 0) {
                    this.a.stopSelf();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void a(int i) {
            this.h = i;
        }

        private void a(boolean z) {
            FileOutputStream fileOutputStream;
            InputStream inputStream;
            Exception exception;
            int i;
            RemoteException e;
            Throwable th;
            FileOutputStream fileOutputStream2 = null;
            Object obj = null;
            String name = this.d.getName();
            InputStream inputStream2;
            try {
                FileOutputStream fileOutputStream3 = new FileOutputStream(this.d, true);
                try {
                    if (this.c || j.a(this.d.getAbsolutePath(), 3)) {
                        fileOutputStream = fileOutputStream3;
                    } else {
                        fileOutputStream3.close();
                        fileOutputStream3 = this.b.openFileOutput(name, 32771);
                        try {
                            this.d = this.b.getFileStreamPath(name);
                            fileOutputStream = fileOutputStream3;
                        } catch (Exception e2) {
                            inputStream = null;
                            fileOutputStream2 = fileOutputStream3;
                            exception = e2;
                            try {
                                u.upd.b.c(DownloadingService.t, exception.getMessage(), exception);
                                i = this.e + 1;
                                this.e = i;
                                if (i > 3 || this.k.g) {
                                    a();
                                } else {
                                    u.upd.b.b(DownloadingService.t, "Download Fail out of max repeat count");
                                    ((Messenger) DownloadingService.E.get(this.k)).send(Message.obtain(null, 5, 0, 0));
                                    this.a.v.b(this.b, this.i);
                                    a(exception);
                                    this.a.B.post(new DownloadingService$b$1(this));
                                }
                            } catch (RemoteException e3) {
                                e3.printStackTrace();
                                this.a.v.b(this.b, this.i);
                                a(exception);
                                this.a.B.post(new DownloadingService$b$1(this));
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = fileOutputStream2;
                                inputStream2 = inputStream;
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException e42) {
                                                e42.printStackTrace();
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException e422) {
                                                e422.printStackTrace();
                                            }
                                        }
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e4222) {
                                        e4222.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                    if (fileOutputStream2 != null) {
                                        try {
                                            fileOutputStream2.close();
                                            return;
                                        } catch (IOException e52) {
                                            e52.printStackTrace();
                                            return;
                                        }
                                    }
                                    return;
                                } catch (Throwable th4) {
                                    if (fileOutputStream2 != null) {
                                        try {
                                            fileOutputStream2.close();
                                        } catch (IOException e42222) {
                                            e42222.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (fileOutputStream2 == null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e522) {
                                    e522.printStackTrace();
                                    return;
                                }
                            }
                        } catch (RemoteException e6) {
                            e3 = e6;
                            fileOutputStream = fileOutputStream3;
                            inputStream2 = null;
                            try {
                                this.a.v.b(this.b, this.i);
                                e3.printStackTrace();
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e5222) {
                                        e5222.printStackTrace();
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                                return;
                                            } catch (IOException e52222) {
                                                e52222.printStackTrace();
                                                return;
                                            }
                                        }
                                        return;
                                    } catch (Throwable th5) {
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException e422222) {
                                                e422222.printStackTrace();
                                            }
                                        }
                                    }
                                }
                                if (fileOutputStream == null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e522222) {
                                        e522222.printStackTrace();
                                        return;
                                    }
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            fileOutputStream = fileOutputStream3;
                            inputStream2 = null;
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    }
                    try {
                        u.upd.b.c(DownloadingService.t, String.format("saveAPK: url = %1$15s\t|\tfilename = %2$15s", new Object[]{this.k.c, this.d.getAbsolutePath()}));
                        HttpURLConnection a = a(new URL(this.k.c), this.d);
                        a.connect();
                        inputStream2 = a.getInputStream();
                        if (!z) {
                            long j = 0;
                            try {
                                if (this.d.exists() && this.d.length() > 0) {
                                    j = 0 + this.d.length();
                                }
                                this.f = j;
                                this.g = j + ((long) a.getContentLength());
                                u.upd.b.c(DownloadingService.t, String.format("getFileLength: %1$15s", new Object[]{Long.valueOf(this.f)}));
                                u.upd.b.c(DownloadingService.t, String.format("getConnectionLength: %1$15s", new Object[]{Integer.valueOf(a.getContentLength())}));
                                u.upd.b.c(DownloadingService.t, String.format("getContentLength: %1$15s", new Object[]{Long.valueOf(this.g)}));
                            } catch (Exception e22) {
                                exception = e22;
                                fileOutputStream2 = fileOutputStream;
                                inputStream = inputStream2;
                            } catch (RemoteException e7) {
                                e3 = e7;
                            }
                        }
                        byte[] bArr = new byte[4096];
                        u.upd.b.c(DownloadingService.t, new StringBuilder(String.valueOf(this.k.b)).append("saveAPK getContentLength ").append(String.valueOf(this.g)).toString());
                        b.a(this.b).a(this.k.a, this.k.c);
                        int i2 = 0;
                        while (this.h < 0) {
                            int read = inputStream2.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                            this.f += (long) read;
                            read = i2 + 1;
                            if (i2 % 50 == 0) {
                                if (!u.upd.a.e(this.b)) {
                                    break;
                                } else if (u.upd.a.d(this.b) || !this.k.i) {
                                    i2 = (int) ((((float) this.f) * 100.0f) / ((float) this.g));
                                    if (i2 > 100) {
                                        i2 = 99;
                                    }
                                    if (this.j != null) {
                                        this.j.a(this.i, i2);
                                    }
                                    b(i2);
                                    b.a(this.b).a(this.k.a, this.k.c, i2);
                                } else {
                                    u.upd.b.b(DownloadingService.t, "no wifi");
                                    throw new IOException("no wifi");
                                }
                            }
                            i2 = read;
                        }
                        obj = 1;
                        inputStream2.close();
                        fileOutputStream.close();
                        if (this.h == 1) {
                            b bVar = (b) DownloadingService.F.get(this.i);
                            bVar.f[0] = this.f;
                            bVar.f[1] = this.g;
                            bVar.f[2] = (long) this.e;
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e5222222) {
                                    e5222222.printStackTrace();
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                            return;
                                        } catch (IOException e52222222) {
                                            e52222222.printStackTrace();
                                            return;
                                        }
                                    }
                                    return;
                                } catch (Throwable th8) {
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e4222222) {
                                            e4222222.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e522222222) {
                                    e522222222.printStackTrace();
                                }
                            }
                        } else if (this.h == 2) {
                            this.a.v.a(this.k, this.f, this.g, (long) this.e);
                            this.a.u.cancel(this.i);
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e5222222222) {
                                    e5222222222.printStackTrace();
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                            return;
                                        } catch (IOException e52222222222) {
                                            e52222222222.printStackTrace();
                                            return;
                                        }
                                    }
                                    return;
                                } catch (Throwable th9) {
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e42222222) {
                                            e42222222.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e522222222222) {
                                    e522222222222.printStackTrace();
                                }
                            }
                        } else {
                            if (obj == null) {
                                u.upd.b.b(DownloadingService.t, "Download Fail repeat count=" + this.e);
                                ((Messenger) DownloadingService.E.get(this.k)).send(Message.obtain(null, 5, 0, 0));
                                this.a.v.b(this.b, this.i);
                                if (this.j != null) {
                                    this.j.a(this.i, null);
                                }
                            } else {
                                b();
                                File file = new File(this.d.getParent(), this.d.getName().replace(FileType.TEMP, ""));
                                this.d.renameTo(file);
                                String absolutePath = file.getAbsolutePath();
                                a(file, absolutePath);
                                if (this.j != null) {
                                    this.j.a(this.i, absolutePath);
                                }
                            }
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e5222222222222) {
                                    e5222222222222.printStackTrace();
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                            return;
                                        } catch (IOException e52222222222222) {
                                            e52222222222222.printStackTrace();
                                            return;
                                        }
                                    }
                                    return;
                                } catch (Throwable th10) {
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e422222222) {
                                            e422222222.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e522222222222222) {
                                    e522222222222222.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e222) {
                        exception = e222;
                        inputStream = null;
                        fileOutputStream2 = fileOutputStream;
                        u.upd.b.c(DownloadingService.t, exception.getMessage(), exception);
                        i = this.e + 1;
                        this.e = i;
                        if (i > 3) {
                        }
                        a();
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (fileOutputStream2 == null) {
                            fileOutputStream2.close();
                        }
                    } catch (RemoteException e8) {
                        e3 = e8;
                        inputStream2 = null;
                        this.a.v.b(this.b, this.i);
                        e3.printStackTrace();
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th11) {
                        th = th11;
                        inputStream2 = null;
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e2222) {
                    inputStream = null;
                    fileOutputStream2 = fileOutputStream3;
                    exception = e2222;
                    u.upd.b.c(DownloadingService.t, exception.getMessage(), exception);
                    i = this.e + 1;
                    this.e = i;
                    if (i > 3) {
                    }
                    a();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream2 == null) {
                        fileOutputStream2.close();
                    }
                } catch (RemoteException e9) {
                    e3 = e9;
                    fileOutputStream = fileOutputStream3;
                    inputStream2 = null;
                    this.a.v.b(this.b, this.i);
                    e3.printStackTrace();
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th12) {
                    th = th12;
                    fileOutputStream = fileOutputStream3;
                    inputStream2 = null;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e22222) {
                exception = e22222;
                inputStream = null;
                u.upd.b.c(DownloadingService.t, exception.getMessage(), exception);
                i = this.e + 1;
                this.e = i;
                if (i > 3) {
                }
                a();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            } catch (RemoteException e10) {
                e3 = e10;
                fileOutputStream = null;
                inputStream2 = null;
                this.a.v.b(this.b, this.i);
                e3.printStackTrace();
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (fileOutputStream == null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th13) {
                th = th13;
                fileOutputStream = null;
                inputStream2 = null;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }

        private void a() {
            u.upd.b.c(DownloadingService.t, "wait for repeating Test network repeat count=" + this.e);
            try {
                if (this.k.g) {
                    b bVar = (b) DownloadingService.F.get(this.i);
                    bVar.f[0] = this.f;
                    bVar.f[1] = this.g;
                    bVar.f[2] = (long) this.e;
                    String a = f.a(this.i, f.b);
                    Intent intent = new Intent(this.b, DownloadingService.class);
                    intent.putExtra(f.e, a);
                    this.a.v.a(this.a, intent);
                    this.a.a(this.b.getString(k.c(this.b)));
                    u.upd.b.c(DownloadingService.t, "changed play state button on op-notification.");
                    return;
                }
                Thread.sleep(DownloadingService.C);
                if (this.g < 1) {
                    a(false);
                } else {
                    a(true);
                }
            } catch (Exception e) {
                a(e);
                this.a.v.b(this.b, this.i);
            }
        }

        private void b(int i) throws RemoteException {
            try {
                if (DownloadingService.E.get(this.k) != null) {
                    ((Messenger) DownloadingService.E.get(this.k)).send(Message.obtain(null, 3, i, 0));
                }
            } catch (DeadObjectException e) {
                u.upd.b.b(DownloadingService.t, String.format("Service Client for downloading %1$15s is dead. Removing messenger from the service", new Object[]{this.k.b}));
                DownloadingService.E.put(this.k, null);
            }
        }

        private HttpURLConnection a(URL url, File file) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
            httpURLConnection.addRequestProperty("Connection", "keep-alive");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(10000);
            if (file.exists() && file.length() > 0) {
                u.upd.b.c(DownloadingService.t, String.format(new StringBuilder(String.valueOf(this.k.b)).append(" getFileLength: %1$15s").toString(), new Object[]{Long.valueOf(file.length())}));
                httpURLConnection.setRequestProperty("Range", "bytes=" + file.length() + "-");
            }
            return httpURLConnection;
        }

        private String a(a aVar) {
            String stringBuilder;
            if (this.k.e != null) {
                stringBuilder = new StringBuilder(String.valueOf(this.k.e)).append(".apk.tmp").toString();
            } else {
                stringBuilder = new StringBuilder(String.valueOf(m.a(this.k.c))).append(".apk.tmp").toString();
            }
            if (this.k.a.equalsIgnoreCase("delta_update")) {
                return stringBuilder.replace(".apk", ".patch");
            }
            return stringBuilder;
        }

        private void b() {
            if (this.k.f != null) {
                Map hashMap = new HashMap();
                hashMap.put("dsize", String.valueOf(this.g));
                hashMap.put("dtime", m.a().split(" ")[1]);
                hashMap.put("ptimes", String.valueOf(this.e));
                this.a.v.a(hashMap, true, this.k.f);
            }
        }

        private void a(File file, String str) throws RemoteException {
            u.upd.b.c(DownloadingService.t, "itemMd5 " + this.k.d);
            u.upd.b.c(DownloadingService.t, "fileMd5 " + m.a(file));
            if (this.k.d != null && !this.k.d.equalsIgnoreCase(m.a(file))) {
                if (this.k.a.equalsIgnoreCase("delta_update")) {
                    this.a.u.cancel(this.i);
                    Bundle bundle = new Bundle();
                    bundle.putString(DownloadingService.p, str);
                    Message obtain = Message.obtain();
                    obtain.what = 5;
                    obtain.arg1 = 3;
                    obtain.arg2 = this.i;
                    obtain.setData(bundle);
                    try {
                        if (DownloadingService.E.get(this.k) != null) {
                            ((Messenger) DownloadingService.E.get(this.k)).send(obtain);
                        }
                        this.a.v.b(this.b, this.i);
                        return;
                    } catch (RemoteException e) {
                        this.a.v.b(this.b, this.i);
                        return;
                    }
                }
                ((Messenger) DownloadingService.E.get(this.k)).send(Message.obtain(null, 5, 0, 0));
                if (!this.k.h) {
                    this.a.v.b(this.b, this.i);
                    Notification notification = new Notification(17301634, this.b.getString(k.i(this.b)), System.currentTimeMillis());
                    notification.setLatestEventInfo(this.b, u.upd.a.j(this.b), new StringBuilder(String.valueOf(this.k.b)).append(this.b.getString(k.i(this.b))).toString(), PendingIntent.getActivity(this.b, 0, new Intent(), 0));
                    notification.flags |= 16;
                    this.a.u.notify(this.i, notification);
                }
            }
        }

        private void a(Exception exception) {
            u.upd.b.b(DownloadingService.t, "can not install. " + exception.getMessage());
            if (this.j != null) {
                this.j.a(this.i, exception);
            }
            this.a.v.a(this.k, this.f, this.g, (long) this.e);
        }
    }

    class c extends Handler {
        final /* synthetic */ DownloadingService a;

        c(DownloadingService downloadingService) {
            this.a = downloadingService;
        }

        public void handleMessage(Message message) {
            u.upd.b.c(DownloadingService.t, "IncomingHandler(msg.what:" + message.what + " msg.arg1:" + message.arg1 + " msg.arg2:" + message.arg2 + " msg.replyTo:" + message.replyTo);
            switch (message.what) {
                case 4:
                    Bundle data = message.getData();
                    u.upd.b.c(DownloadingService.t, "IncomingHandler(msg.getData():" + data);
                    a a = a.a(data);
                    Message obtain;
                    if (this.a.v.a(a, DownloadingService.r, message.replyTo)) {
                        u.upd.b.a(DownloadingService.t, a.b + " is already in downloading list. ");
                        int b = this.a.v.b(a);
                        if (b == -1 || ((b) DownloadingService.F.get(b)).a != null) {
                            Toast.makeText(this.a.A, k.b(this.a.A), 0).show();
                            obtain = Message.obtain();
                            obtain.what = 2;
                            obtain.arg1 = 2;
                            obtain.arg2 = 0;
                            try {
                                message.replyTo.send(obtain);
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        String a2 = f.a(b, f.b);
                        Intent intent = new Intent(this.a.A, DownloadingService.class);
                        intent.putExtra(f.e, a2);
                        this.a.v.a(this.a, intent);
                        return;
                    } else if (u.upd.a.e(this.a.getApplicationContext())) {
                        DownloadingService.E.put(a, message.replyTo);
                        obtain = Message.obtain();
                        obtain.what = 1;
                        obtain.arg1 = 1;
                        obtain.arg2 = 0;
                        try {
                            message.replyTo.send(obtain);
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                        this.a.a(a);
                        return;
                    } else {
                        Toast.makeText(this.a.A, k.a(this.a.A), 0).show();
                        obtain = Message.obtain();
                        obtain.what = 2;
                        obtain.arg1 = 4;
                        obtain.arg2 = 0;
                        try {
                            message.replyTo.send(obtain);
                            return;
                        } catch (RemoteException e22) {
                            e22.printStackTrace();
                            return;
                        }
                    }
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        u.upd.b.c(t, "onBind ");
        return this.s.getBinder();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (!(intent == null || intent.getExtras() == null)) {
            this.v.a(this, intent);
        }
        if (VERSION.SDK_INT >= 19 && (this.G.b() || this.H)) {
            try {
                Intent intent2 = new Intent(getApplicationContext(), getClass());
                intent2.setPackage(getPackageName());
                ((AlarmManager) getApplicationContext().getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + 5000, PendingIntent.getService(getApplicationContext(), 1, intent2, 1073741824));
            } catch (Exception e) {
            }
        }
        if (this.H) {
            d();
            this.H = false;
        }
        return 1;
    }

    public void onCreate() {
        super.onCreate();
        if (r) {
            u.upd.b.a = true;
            Debug.waitForDebugger();
        }
        u.upd.b.c(t, "onCreate ");
        this.u = (NotificationManager) getSystemService("notification");
        this.A = this;
        this.G = new e(this.A);
        this.v = new c(F, E, this.G);
        this.B = new Handler(this) {
            final /* synthetic */ DownloadingService a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                int i;
                switch (message.what) {
                    case 5:
                        a aVar = (a) message.obj;
                        i = message.arg2;
                        try {
                            Notification notification;
                            String string = message.getData().getString(DownloadingService.p);
                            j.a(string, 39, -1, -1);
                            u.upd.b.c(DownloadingService.t, "Cancel old notification....");
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.addFlags(268435456);
                            intent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                            PendingIntent activity = PendingIntent.getActivity(this.a.A, 0, intent, 134217728);
                            Notification notification2;
                            if (aVar.h) {
                                notification2 = new Notification(17301634, this.a.A.getString(k.m(this.a.A)), System.currentTimeMillis());
                                notification2.setLatestEventInfo(this.a.A, aVar.b, this.a.A.getString(k.m(this.a.A)), activity);
                                notification = notification2;
                            } else {
                                notification2 = new Notification(17301634, this.a.A.getString(k.k(this.a.A)), System.currentTimeMillis());
                                notification2.setLatestEventInfo(this.a.A, aVar.b, this.a.A.getString(k.k(this.a.A)), activity);
                                notification = notification2;
                            }
                            notification.flags = 16;
                            this.a.u = (NotificationManager) this.a.getSystemService("notification");
                            this.a.u.notify(i + 1, notification);
                            u.upd.b.c(DownloadingService.t, "Show new  notification....");
                            boolean a = this.a.v.a(this.a.A);
                            u.upd.b.c(DownloadingService.t, String.format("isAppOnForeground = %1$B", new Object[]{Boolean.valueOf(a)}));
                            if (a && !aVar.h) {
                                this.a.u.cancel(i + 1);
                                this.a.A.startActivity(intent);
                            }
                            u.upd.b.a(DownloadingService.t, String.format("%1$10s downloaded. Saved to: %2$s", new Object[]{aVar.b, string}));
                            return;
                        } catch (Exception e) {
                            u.upd.b.b(DownloadingService.t, "can not install. " + e.getMessage());
                            this.a.u.cancel(i + 1);
                            return;
                        }
                    case 6:
                        a aVar2 = (a) message.obj;
                        i = message.arg2;
                        String string2 = message.getData().getString(DownloadingService.p);
                        this.a.u.cancel(i);
                        Notification notification3 = new Notification(17301633, this.a.A.getString(k.n(this.a.A)), System.currentTimeMillis());
                        notification3.setLatestEventInfo(this.a.A, u.upd.a.j(this.a.A), this.a.A.getString(k.n(this.a.A)), PendingIntent.getActivity(this.a.A, 0, new Intent(), 134217728));
                        this.a.u.notify(i + 1, notification3);
                        String replace = string2.replace(".patch", ".apk");
                        String a2 = DeltaUpdate.a(this.a);
                        c a3 = this.a.v;
                        a3.getClass();
                        new c$c(a3, this.a.A, i, aVar2, replace).execute(new String[]{a2, replace, string2});
                        return;
                    default:
                        return;
                }
            }
        };
        this.q = new a(this) {
            SparseArray<Long> a = new SparseArray();
            final /* synthetic */ DownloadingService b;

            {
                this.b = r2;
            }

            public void a(int i) {
                int i2 = 0;
                if (DownloadingService.F.indexOfKey(i) >= 0) {
                    b bVar = (b) DownloadingService.F.get(i);
                    long[] jArr = bVar.f;
                    if (jArr != null && jArr[1] > 0) {
                        i2 = (int) ((((float) jArr[0]) / ((float) jArr[1])) * 100.0f);
                        if (i2 > 100) {
                            i2 = 99;
                        }
                    }
                    if (!bVar.e.h) {
                        this.a.put(i, Long.valueOf(-1));
                        c$a a = this.b.v.a(this.b, bVar.e, i, i2);
                        bVar.b = a;
                        this.b.u.notify(i, a.d());
                    }
                }
            }

            public void a(int i, int i2) {
                if (DownloadingService.F.indexOfKey(i) >= 0) {
                    b bVar = (b) DownloadingService.F.get(i);
                    a aVar = bVar.e;
                    long currentTimeMillis = System.currentTimeMillis();
                    if (!aVar.h && currentTimeMillis - ((Long) this.a.get(i)).longValue() > DownloadingService.D) {
                        this.a.put(i, Long.valueOf(currentTimeMillis));
                        c$a c_a = bVar.b;
                        c_a.a(100, i2, false).a(String.valueOf(i2) + "%");
                        this.b.u.notify(i, c_a.d());
                    }
                    u.upd.b.c(DownloadingService.t, String.format("%3$10s Notification: mNotificationId = %1$15s\t|\tprogress = %2$15s", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), aVar.b}));
                }
            }

            public void a(int i, String str) {
                if (DownloadingService.F.indexOfKey(i) >= 0) {
                    b bVar = (b) DownloadingService.F.get(i);
                    if (bVar != null) {
                        a aVar = bVar.e;
                        b.a(this.b.A).a(aVar.a, aVar.c, 100);
                        Bundle bundle = new Bundle();
                        bundle.putString(DownloadingService.p, str);
                        Message obtain;
                        if (aVar.a.equalsIgnoreCase("delta_update")) {
                            obtain = Message.obtain();
                            obtain.what = 6;
                            obtain.arg1 = 1;
                            obtain.obj = aVar;
                            obtain.arg2 = i;
                            obtain.setData(bundle);
                            this.b.B.sendMessage(obtain);
                            return;
                        }
                        obtain = Message.obtain();
                        obtain.what = 5;
                        obtain.arg1 = 1;
                        obtain.obj = aVar;
                        obtain.arg2 = i;
                        obtain.setData(bundle);
                        this.b.B.sendMessage(obtain);
                        obtain = Message.obtain();
                        obtain.what = 5;
                        obtain.arg1 = 1;
                        obtain.arg2 = i;
                        obtain.setData(bundle);
                        try {
                            if (DownloadingService.E.get(aVar) != null) {
                                ((Messenger) DownloadingService.E.get(aVar)).send(obtain);
                            }
                            this.b.v.b(this.b.A, i);
                        } catch (RemoteException e) {
                            this.b.v.b(this.b.A, i);
                        }
                    }
                }
            }

            public void a(int i, Exception exception) {
                if (DownloadingService.F.indexOfKey(i) >= 0) {
                    this.b.v.b(this.b.A, i);
                }
            }
        };
    }

    private void d() {
        for (Integer intValue : this.G.a()) {
            this.u.cancel(intValue.intValue());
        }
    }

    private void a(a aVar) {
        int i = 0;
        u.upd.b.c(t, "startDownload([mComponentName:" + aVar.a + " mTitle:" + aVar.b + " mUrl:" + aVar.c + "])");
        int a = this.v.a(aVar);
        b bVar = new b(this, getApplicationContext(), aVar, a, 0, this.q);
        b bVar2 = new b(aVar, a);
        this.G.a(a);
        bVar2.a(F);
        bVar2.a = bVar;
        bVar.start();
        e();
        if (r) {
            while (i < F.size()) {
                u.upd.b.c(t, "Running task " + ((b) F.valueAt(i)).e.b);
                i++;
            }
        }
    }

    private void a(final String str) {
        synchronized (I) {
            if (!I.booleanValue()) {
                u.upd.b.c(t, "show single toast.[" + str + "]");
                I = Boolean.valueOf(true);
                this.B.post(new Runnable(this) {
                    final /* synthetic */ DownloadingService a;

                    public void run() {
                        Toast.makeText(this.a.A, str, 0).show();
                    }
                });
                this.B.postDelayed(new Runnable(this) {
                    final /* synthetic */ DownloadingService a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        DownloadingService.I = Boolean.valueOf(false);
                    }
                }, 1200);
            }
        }
    }

    public void onDestroy() {
        try {
            b.a(getApplicationContext()).a(259200);
            b.a(getApplicationContext()).finalize();
        } catch (Exception e) {
            u.upd.b.b(t, e.getMessage());
        }
        super.onDestroy();
    }

    private void e() {
        if (r) {
            int size = E.size();
            int size2 = F.size();
            u.upd.b.a(t, "Client size =" + size + "   cacheSize = " + size2);
            if (size != size2) {
                throw new RuntimeException("Client size =" + size + "   cacheSize = " + size2);
            }
        }
    }
}
