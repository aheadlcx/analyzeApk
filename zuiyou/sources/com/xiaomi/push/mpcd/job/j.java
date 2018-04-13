package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.misc.h.a;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.mpcd.b;
import com.xiaomi.push.mpcd.c;
import com.xiaomi.push.mpcd.e;
import com.xiaomi.push.mpcd.f;
import com.xiaomi.push.service.am;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.g;
import com.xiaomi.xmpush.thrift.k;
import com.xiaomi.xmpush.thrift.r;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

public class j extends a {
    private Context a;
    private SharedPreferences b;
    private am c;

    public j(Context context) {
        this.a = context;
        this.b = context.getSharedPreferences("mipush_extra", 0);
        this.c = am.a(context);
    }

    private List<k> a(File file) {
        RandomAccessFile randomAccessFile;
        FileLock fileLock;
        RandomAccessFile randomAccessFile2;
        InputStream inputStream;
        Throwable th;
        InputStream inputStream2 = null;
        b b = c.a().b();
        Object a = b == null ? "" : b.a();
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        List<k> arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        synchronized (f.a) {
            FileLock lock;
            try {
                File file2 = new File(this.a.getExternalFilesDir(null), "push_cdata.lock");
                com.xiaomi.channel.commonutils.file.a.a(file2);
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    lock = randomAccessFile.getChannel().lock();
                    try {
                        InputStream fileInputStream = new FileInputStream(file);
                        while (fileInputStream.read(bArr) == 4) {
                            try {
                                int a2 = com.xiaomi.channel.commonutils.misc.b.a(bArr);
                                byte[] bArr2 = new byte[a2];
                                if (fileInputStream.read(bArr2) != a2) {
                                    break;
                                }
                                byte[] a3 = e.a(a, bArr2);
                                if (!(a3 == null || a3.length == 0)) {
                                    org.apache.thrift.a kVar = new k();
                                    au.a(kVar, a3);
                                    arrayList.add(kVar);
                                }
                            } catch (Exception e) {
                                fileLock = lock;
                                randomAccessFile2 = randomAccessFile;
                                inputStream = fileInputStream;
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream2 = fileInputStream;
                            }
                        }
                        if (lock != null) {
                            if (lock.isValid()) {
                                try {
                                    lock.release();
                                } catch (IOException e2) {
                                }
                            }
                        }
                        com.xiaomi.channel.commonutils.file.a.a(fileInputStream);
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                    } catch (Exception e3) {
                        randomAccessFile2 = randomAccessFile;
                        FileLock fileLock2 = lock;
                        inputStream = null;
                        fileLock = fileLock2;
                        if (fileLock != null) {
                            if (fileLock.isValid()) {
                                try {
                                    fileLock.release();
                                } catch (IOException e4) {
                                }
                            }
                        }
                        com.xiaomi.channel.commonutils.file.a.a(inputStream);
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile2);
                        return arrayList;
                    } catch (Throwable th3) {
                        th = th3;
                        if (lock != null) {
                            if (lock.isValid()) {
                                try {
                                    lock.release();
                                } catch (IOException e5) {
                                }
                            }
                        }
                        com.xiaomi.channel.commonutils.file.a.a(inputStream2);
                        com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                        throw th;
                    }
                } catch (Exception e6) {
                    randomAccessFile2 = randomAccessFile;
                    inputStream = null;
                    if (fileLock != null) {
                        if (fileLock.isValid()) {
                            fileLock.release();
                        }
                    }
                    com.xiaomi.channel.commonutils.file.a.a(inputStream);
                    com.xiaomi.channel.commonutils.file.a.a(randomAccessFile2);
                    return arrayList;
                } catch (Throwable th4) {
                    th = th4;
                    lock = null;
                    if (lock != null) {
                        if (lock.isValid()) {
                            lock.release();
                        }
                    }
                    com.xiaomi.channel.commonutils.file.a.a(inputStream2);
                    com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                    throw th;
                }
            } catch (Exception e7) {
                randomAccessFile2 = null;
                inputStream = null;
                if (fileLock != null) {
                    if (fileLock.isValid()) {
                        fileLock.release();
                    }
                }
                com.xiaomi.channel.commonutils.file.a.a(inputStream);
                com.xiaomi.channel.commonutils.file.a.a(randomAccessFile2);
                return arrayList;
            } catch (Throwable th5) {
                th = th5;
                lock = null;
                randomAccessFile = null;
                if (lock != null) {
                    if (lock.isValid()) {
                        lock.release();
                    }
                }
                com.xiaomi.channel.commonutils.file.a.a(inputStream2);
                com.xiaomi.channel.commonutils.file.a.a(randomAccessFile);
                throw th;
            }
        }
        return arrayList;
    }

    private boolean b() {
        return d.e(this.a) ? false : (!d.g(this.a) || d()) ? (!d.h(this.a) || c()) ? d.i(this.a) : true : true;
    }

    private boolean c() {
        boolean z = true;
        if (!this.c.a(g.Upload3GSwitch.a(), true)) {
            return false;
        }
        int max = Math.max(86400, this.c.a(g.Upload3GFrequency.a(), 432000));
        if (Math.abs((System.currentTimeMillis() / 1000) - this.b.getLong("last_upload_data_timestamp", -1)) <= ((long) max)) {
            z = false;
        }
        return z;
    }

    private boolean d() {
        boolean z = true;
        if (!this.c.a(g.Upload4GSwitch.a(), true)) {
            return false;
        }
        int max = Math.max(86400, this.c.a(g.Upload4GFrequency.a(), 259200));
        if (Math.abs((System.currentTimeMillis() / 1000) - this.b.getLong("last_upload_data_timestamp", -1)) <= ((long) max)) {
            z = false;
        }
        return z;
    }

    private void e() {
        Editor edit = this.b.edit();
        edit.putLong("last_upload_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    public int a() {
        return 1;
    }

    public void run() {
        File file = new File(this.a.getExternalFilesDir(null), "push_cdata.data");
        if (d.d(this.a)) {
            if (!b() && file.exists()) {
                List a = a(file);
                if (!com.xiaomi.channel.commonutils.misc.c.a(a)) {
                    int size = a.size();
                    if (size > 4000) {
                        a = a.subList(size - 4000, size);
                    }
                    org.apache.thrift.a acVar = new ac();
                    acVar.a(a);
                    byte[] a2 = com.xiaomi.channel.commonutils.file.a.a(au.a(acVar));
                    ai aiVar = new ai(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
                    aiVar.c(r.DataCollection.W);
                    aiVar.a(a2);
                    b b = c.a().b();
                    if (b != null) {
                        b.a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, null);
                    }
                    e();
                }
                file.delete();
                this.b.edit().remove("ltapn").commit();
            }
        } else if (file.length() > 1863680) {
            file.delete();
        }
    }
}
