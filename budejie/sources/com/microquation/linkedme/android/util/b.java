package com.microquation.linkedme.android.util;

import android.os.Environment;
import android.text.TextUtils;
import com.microquation.linkedme.android.a;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class b {
    private static final b a = new b();

    private b() {
    }

    public static b a() {
        return a;
    }

    private String b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String[] split = str.split(",");
        String[] split2 = str2.split(",");
        if (split.length != split2.length) {
            return str2;
        }
        for (int i = 0; i < split2.length; i++) {
            String str3 = split[i];
            String str4 = split2[i];
            if (str4.length() >= str3.length()) {
                split2[i] = str4;
            } else {
                split2[i] = str3;
            }
        }
        return TextUtils.join(",", split2);
    }

    public void a(String str) {
        Closeable fileOutputStream;
        Throwable th;
        Throwable th2;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
            reentrantReadWriteLock.writeLock().lock();
            Closeable closeable = null;
            try {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".lm_device");
                if (!file.exists()) {
                    file.mkdirs();
                }
                fileOutputStream = new FileOutputStream(new File(file, ".lm_device_id"));
                try {
                    d.a((OutputStream) fileOutputStream, str);
                    reentrantReadWriteLock.writeLock().unlock();
                    d.a(fileOutputStream);
                } catch (FileNotFoundException e) {
                    closeable = fileOutputStream;
                    try {
                        com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                        reentrantReadWriteLock.writeLock().unlock();
                        d.a(closeable);
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = closeable;
                        th2 = th;
                        reentrantReadWriteLock.writeLock().unlock();
                        d.a(fileOutputStream);
                        throw th2;
                    }
                } catch (IOException e2) {
                    closeable = fileOutputStream;
                    reentrantReadWriteLock.writeLock().unlock();
                    d.a(closeable);
                } catch (Throwable th4) {
                    th2 = th4;
                    reentrantReadWriteLock.writeLock().unlock();
                    d.a(fileOutputStream);
                    throw th2;
                }
            } catch (FileNotFoundException e3) {
                com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                reentrantReadWriteLock.writeLock().unlock();
                d.a(closeable);
            } catch (IOException e4) {
                reentrantReadWriteLock.writeLock().unlock();
                d.a(closeable);
            } catch (Throwable th32) {
                th = th32;
                fileOutputStream = null;
                th2 = th;
                reentrantReadWriteLock.writeLock().unlock();
                d.a(fileOutputStream);
                throw th2;
            }
        }
    }

    public void a(String str, String str2) {
        Closeable fileOutputStream;
        Throwable th;
        Throwable th2;
        String b = b(str, str2);
        if ("mounted".equals(Environment.getExternalStorageState())) {
            ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
            reentrantReadWriteLock.writeLock().lock();
            Closeable closeable = null;
            try {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".lm_device");
                if (!file.exists()) {
                    file.mkdirs();
                }
                fileOutputStream = new FileOutputStream(new File(file, ".lm_device_info"));
                try {
                    d.a((OutputStream) fileOutputStream, b);
                    reentrantReadWriteLock.writeLock().unlock();
                    d.a(fileOutputStream);
                } catch (FileNotFoundException e) {
                    closeable = fileOutputStream;
                    try {
                        com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                        reentrantReadWriteLock.writeLock().unlock();
                        d.a(closeable);
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = closeable;
                        th2 = th;
                        reentrantReadWriteLock.writeLock().unlock();
                        d.a(fileOutputStream);
                        throw th2;
                    }
                } catch (IOException e2) {
                    closeable = fileOutputStream;
                    reentrantReadWriteLock.writeLock().unlock();
                    d.a(closeable);
                } catch (Throwable th4) {
                    th2 = th4;
                    reentrantReadWriteLock.writeLock().unlock();
                    d.a(fileOutputStream);
                    throw th2;
                }
            } catch (FileNotFoundException e3) {
                com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                reentrantReadWriteLock.writeLock().unlock();
                d.a(closeable);
            } catch (IOException e4) {
                reentrantReadWriteLock.writeLock().unlock();
                d.a(closeable);
            } catch (Throwable th32) {
                th = th32;
                fileOutputStream = null;
                th2 = th;
                reentrantReadWriteLock.writeLock().unlock();
                d.a(fileOutputStream);
                throw th2;
            }
        }
    }

    public String b() {
        Throwable th;
        String str = "";
        if ("mounted".equals(Environment.getExternalStorageState())) {
            ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
            reentrantReadWriteLock.readLock().lock();
            Closeable closeable = null;
            try {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".lm_device");
                String str2 = ".lm_device_id";
                if (!file.exists()) {
                    file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "LMDevice");
                    str2 = "lm_device_id";
                }
                File file2 = new File(file, str2);
                if (file2.exists()) {
                    Closeable fileInputStream = new FileInputStream(file2);
                    try {
                        str = d.a((InputStream) fileInputStream);
                        d.a(fileInputStream);
                        reentrantReadWriteLock.readLock().unlock();
                    } catch (FileNotFoundException e) {
                        closeable = fileInputStream;
                        try {
                            com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                            d.a(closeable);
                            reentrantReadWriteLock.readLock().unlock();
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            d.a(closeable);
                            reentrantReadWriteLock.readLock().unlock();
                            throw th;
                        }
                    } catch (IOException e2) {
                        closeable = fileInputStream;
                        d.a(closeable);
                        reentrantReadWriteLock.readLock().unlock();
                        return str;
                    } catch (Throwable th3) {
                        th = th3;
                        closeable = fileInputStream;
                        d.a(closeable);
                        reentrantReadWriteLock.readLock().unlock();
                        throw th;
                    }
                }
                str = "";
                d.a(null);
                reentrantReadWriteLock.readLock().unlock();
            } catch (FileNotFoundException e3) {
                com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                d.a(closeable);
                reentrantReadWriteLock.readLock().unlock();
                return str;
            } catch (IOException e4) {
                d.a(closeable);
                reentrantReadWriteLock.readLock().unlock();
                return str;
            }
        }
        return str;
    }

    public String c() {
        Throwable th;
        String str = "";
        if ("mounted".equals(Environment.getExternalStorageState())) {
            ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
            reentrantReadWriteLock.readLock().lock();
            Closeable closeable = null;
            try {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".lm_device");
                if (file.exists()) {
                    File file2 = new File(file, ".lm_device_info");
                    if (file2.exists()) {
                        Closeable fileInputStream = new FileInputStream(file2);
                        try {
                            str = d.a((InputStream) fileInputStream);
                            d.a(fileInputStream);
                            reentrantReadWriteLock.readLock().unlock();
                        } catch (FileNotFoundException e) {
                            closeable = fileInputStream;
                            try {
                                com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                                d.a(closeable);
                                reentrantReadWriteLock.readLock().unlock();
                                return str;
                            } catch (Throwable th2) {
                                th = th2;
                                d.a(closeable);
                                reentrantReadWriteLock.readLock().unlock();
                                throw th;
                            }
                        } catch (IOException e2) {
                            closeable = fileInputStream;
                            d.a(closeable);
                            reentrantReadWriteLock.readLock().unlock();
                            return str;
                        } catch (Throwable th3) {
                            th = th3;
                            closeable = fileInputStream;
                            d.a(closeable);
                            reentrantReadWriteLock.readLock().unlock();
                            throw th;
                        }
                    }
                    str = "";
                    d.a(null);
                    reentrantReadWriteLock.readLock().unlock();
                } else {
                    str = "";
                    d.a(null);
                    reentrantReadWriteLock.readLock().unlock();
                }
            } catch (FileNotFoundException e3) {
                com.microquation.linkedme.android.f.b.a(a.a, "应用程序未被授予读写文件权限,但不影响使用!");
                d.a(closeable);
                reentrantReadWriteLock.readLock().unlock();
                return str;
            } catch (IOException e4) {
                d.a(closeable);
                reentrantReadWriteLock.readLock().unlock();
                return str;
            }
        }
        return str;
    }
}
