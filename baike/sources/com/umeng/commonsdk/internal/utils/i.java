package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.a;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.e;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import qsbk.app.core.model.User;

public class i {
    public static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            CharSequence g = g(context);
            if (TextUtils.isEmpty(g) || !str.equals(g)) {
                b(context, str);
            }
        }
    }

    public static String a(Context context) {
        String b = b(context);
        if (b == null || b.equals("")) {
            b = g(context);
        }
        if (b == null || b.equals("")) {
            b = c(context);
        }
        if (b == null || b.equals("")) {
            b = d(context);
        }
        if (b == null || b.equals("")) {
            b = e(context);
        }
        if (b == null || b.equals("")) {
            return f(context);
        }
        return b;
    }

    public static String b(Context context) {
        return h(context);
    }

    public static String c(Context context) {
        return c(context, "/.um/.umm.dat");
    }

    public static String d(Context context) {
        return c(context, "/.uxx/.cca.dat");
    }

    public static String e(Context context) {
        return c(context, "/.cc/.adfwe.dat");
    }

    public static String f(Context context) {
        return c(context, "/.a.dat");
    }

    public static String g(Context context) {
        return i(context);
    }

    private static String h(Context context) {
        return a.a(context, g.e, null);
    }

    public static void b(Context context, String str) {
        a(context, str, "/.um/.umm.dat");
        a(context, str, "/.uxx/.cca.dat");
        a(context, str, "/.cc/.adfwe.dat");
        a(context, str, "/.a.dat");
        d(context, str);
    }

    private static void a(Context context, String str, String str2) {
        if (DeviceConfig.checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            try {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState != null && externalStorageState.equalsIgnoreCase("mounted")) {
                    externalStorageState = c(context, str2);
                    if (externalStorageState == null || !externalStorageState.equals(str)) {
                        File file = new File(Environment.getExternalStorageDirectory() + str2);
                        if (!(file.getParentFile() == null || file.getParentFile().exists())) {
                            file.getParentFile().mkdir();
                        }
                        RandomAccessFile randomAccessFile = new RandomAccessFile(Environment.getExternalStorageDirectory() + str2, "rw");
                        randomAccessFile.setLength((long) str.getBytes().length);
                        FileChannel channel = randomAccessFile.getChannel();
                        FileLock tryLock = channel.tryLock();
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        allocate.clear();
                        allocate.put(str.getBytes());
                        allocate.flip();
                        while (allocate.hasRemaining()) {
                            channel.write(allocate);
                        }
                        channel.force(true);
                        if (tryLock != null) {
                            tryLock.release();
                        }
                        channel.close();
                    }
                }
            } catch (Exception e) {
                if (e != null) {
                    e.e("saveFileUmtt:" + e.getMessage());
                }
            }
        }
    }

    private static String c(Context context, String str) {
        String str2 = null;
        try {
            if (DeviceConfig.checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState != null && externalStorageState.equalsIgnoreCase("mounted") && new File(Environment.getExternalStorageDirectory() + str).exists()) {
                    FileChannel channel = new RandomAccessFile(Environment.getExternalStorageDirectory() + str, "rw").getChannel();
                    FileLock tryLock = channel.tryLock();
                    StringBuilder stringBuilder = new StringBuilder();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.clear();
                    while (channel.read(allocate) != -1) {
                        byte[] bArr = new byte[allocate.position()];
                        for (int i = 0; i < allocate.position(); i++) {
                            bArr[i] = allocate.get(i);
                        }
                        stringBuilder.append(new String(bArr));
                        allocate.clear();
                    }
                    if (channel != null) {
                        tryLock.release();
                    }
                    channel.close();
                    str2 = stringBuilder.toString();
                }
            }
        } catch (Exception e) {
            if (e != null) {
                e.e("getFileUmtt:" + e.getMessage());
            }
        }
        return str2;
    }

    private static void d(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("umdat", 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(User.UNDEFINED, null);
            if (string == null || !string.equals(str)) {
                sharedPreferences.edit().putString(User.UNDEFINED, str).commit();
            }
        }
    }

    private static String i(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("umdat", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(User.UNDEFINED, null);
        }
        return null;
    }
}
